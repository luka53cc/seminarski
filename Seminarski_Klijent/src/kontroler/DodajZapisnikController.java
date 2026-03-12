package kontroler;

import domen.Polaznik;
import domen.StavkaZapisnika;
import domen.Zapisnik;
import forme.DodajZapisnikForma;
import forme.model.ModelTabeleStavkaZapisnika;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import modovi.FormaModEnum;

public class DodajZapisnikController {
    List<StavkaZapisnika> stavke = new ArrayList<>();
    private final DodajZapisnikForma dzf;
    Zapisnik z;

    public DodajZapisnikController(DodajZapisnikForma dzf) {
        this.z = new Zapisnik();
        this.dzf = dzf;
        addAtionListener();
        dzf.getjButtonSacuvaj().setEnabled(false);
    }

    private int izracunajUkupnoTrajanje() {
        int ukupno = 0;
        for (StavkaZapisnika sz : stavke) {
            ukupno += sz.getTrajanjeStavke();
        }
        return ukupno;
    }

    // provjeri da tekst sadrzi opis bar jedne usluge od stavki
    private boolean tekstSadrziUsluge(String tekst) {
        if (stavke.isEmpty()) return true; // ako nema stavki preskoci provjeru
        for (StavkaZapisnika sz : stavke) {
            if (sz.getUsluga() != null && tekst.contains(sz.getUsluga().getOpisUsluge())) {
                return true;
            }
        }
        return false;
    }

    private boolean validiraj(String datum, String tekst, Polaznik polaznik) {
        if (datum.isEmpty()) {
            JOptionPane.showMessageDialog(dzf, "Datum evidentiranja ne sme biti prazan", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Date.valueOf(datum);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dzf, "Format datuma mora biti YYYY-MM-DD", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tekst.isEmpty()) {
            JOptionPane.showMessageDialog(dzf, "Tekst zapisnika ne sme biti prazan", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!tekstSadrziUsluge(tekst)) {
            JOptionPane.showMessageDialog(dzf, "Tekst zapisnika mora sadrzati opis bar jedne usluge", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (polaznik == null) {
            JOptionPane.showMessageDialog(dzf, "Morate izabrati polaznika", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void addAtionListener() {
        dzf.addbtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    String datum = dzf.getjTextFieldDate().getText().trim();
                    Polaznik polaznik = (Polaznik) dzf.getjComboBoxPolaznik().getSelectedItem();
                    String tekst = dzf.getjTextArea1().getText().trim();

                    if (!validiraj(datum, tekst, polaznik)) return;

                    int trajanje = izracunajUkupnoTrajanje();
                    Date datumE = Date.valueOf(datum);
                    z.setDatumEvidentiranja(datumE);
                    z.setTekst(tekst);
                    z.setUkupnoTrajanje(trajanje);
                    z.setInstruktor(Koordinator.getInstance().getUlogovan());
                    z.setPolaznik(polaznik);
                    Koordinator.getInstance().otvoriDodajStavkuZaKreirajZapisnik(z, stavke);
                    z.setStavkeZapisnika(stavke);
                    Komunikacija.getInstance().dodajZapisnik(z);
                    JOptionPane.showMessageDialog(dzf, "Sistem je zapamtio zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dzf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dzf, "Sistem ne moze da zapamti zapisnik", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        dzf.addbtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    String datum = dzf.getjTextFieldDate().getText().trim();
                    Polaznik polaznik = (Polaznik) dzf.getjComboBoxPolaznik().getSelectedItem();
                    String tekst = dzf.getjTextArea1().getText().trim();

                    if (!validiraj(datum, tekst, polaznik)) return;

                    int id = Integer.parseInt(dzf.getjTextFieldID().getText().trim());
                    int trajanje = izracunajUkupnoTrajanje();
                    Date datumE = Date.valueOf(datum);
                    Zapisnik z = new Zapisnik(id, datumE, tekst, trajanje,
                            Koordinator.getInstance().getUlogovan(), polaznik, null);
                    Komunikacija.getInstance().izmeniZapisnik(z);
                    JOptionPane.showMessageDialog(dzf, "Sistem je zapamtio zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dzf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dzf, "Sistem ne moze da zapamti zapisnik", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        dzf.addbtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj(e);
            }

            private void sacuvaj(ActionEvent e) {
                try {
                    String datum = dzf.getjTextFieldDate().getText().trim();
                    Polaznik polaznik = (Polaznik) dzf.getjComboBoxPolaznik().getSelectedItem();
                    String tekst = dzf.getjTextArea1().getText().trim();

                    if (!validiraj(datum, tekst, polaznik)) return;

                    int trajanje = izracunajUkupnoTrajanje();
                    Date datumE = Date.valueOf(datum);
                    Zapisnik z = new Zapisnik(0, datumE, tekst, trajanje,
                            Koordinator.getInstance().getUlogovan(), polaznik, stavke);
                    Komunikacija.getInstance().dodajZapisnik(z);
                    JOptionPane.showMessageDialog(dzf, "Sistem je zapamtio zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dzf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dzf, "Sistem ne moze da zapamti zapisnik", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        dzf.addbtnDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Koordinator.getInstance().otvoriDodajStavkuZaKreirajZapisnik(z, stavke);
                ModelTabeleStavkaZapisnika mtsz = new ModelTabeleStavkaZapisnika(stavke);
                dzf.getjTableStavke().setModel(mtsz);
                // azuriraj trajanje automatski
                dzf.getjTextFieldTrajanje().setText(String.valueOf(izracunajUkupnoTrajanje()));
                dzf.getjButtonSacuvaj().setEnabled(true);
            }
        });

        dzf.addbtnObrisiStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                int red = dzf.getjTableStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(dzf, "Izaberite red", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                stavke.remove(red);
                ModelTabeleStavkaZapisnika mtsz = new ModelTabeleStavkaZapisnika(stavke);
                dzf.getjTableStavke().setModel(mtsz);
                // azuriraj trajanje automatski
                dzf.getjTextFieldTrajanje().setText(String.valueOf(izracunajUkupnoTrajanje()));
                dzf.getjButtonSacuvaj().setEnabled(true);
            }
        });
    }

    public void otvoriFormu(FormaModEnum mod) {
        pripremiFormu(mod);
        dzf.setVisible(true);
        if (mod == FormaModEnum.DODAJ) {
            JOptionPane.showMessageDialog(dzf, "Sistem je kreirao zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void pripremiFormu(FormaModEnum mod) {
        dzf.getjComboBoxPolaznik().removeAllItems();
        List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
        for (Polaznik k : polaznici) {
            dzf.getjComboBoxPolaznik().addItem(k);
        }
        dzf.getjTextFieldInstruktor().setText(Koordinator.getInstance().getUlogovan().getImePrezimeInstruktora());
        dzf.getjTextFieldInstruktor().setEnabled(false);
        // onemogući trajanje - automatski se racuna
        dzf.getjTextFieldTrajanje().setEnabled(false);
        dzf.getjTextFieldTrajanje().setText("0");
        ModelTabeleStavkaZapisnika mtsz = new ModelTabeleStavkaZapisnika(stavke);
        dzf.getjTableStavke().setModel(mtsz);
    }
}