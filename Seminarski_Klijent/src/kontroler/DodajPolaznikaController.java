package kontroler;

import domen.Kategorija;
import domen.Polaznik;
import forme.DodajPolaznikaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import modovi.FormaModEnum;

public class DodajPolaznikaController {
    private final DodajPolaznikaForma dpf;

    public DodajPolaznikaController(DodajPolaznikaForma dpf) {
        this.dpf = dpf;
        addAtionListener();
    }

    private boolean validirajJMBG(String jmbg) {
        if (jmbg.length() != 13 || !jmbg.matches("\\d+")) {
            JOptionPane.showMessageDialog(dpf, "JMBG mora imati tacno 13 cifara", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int dd = Integer.parseInt(jmbg.substring(0, 2));
        int mm = Integer.parseInt(jmbg.substring(2, 4));

        if (dd < 1 || dd > 31) {
            JOptionPane.showMessageDialog(dpf, "JMBG sadrzi neispravan dan", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (mm < 1 || mm > 12) {
            JOptionPane.showMessageDialog(dpf, "JMBG sadrzi neispravan mesec", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int[] c = new int[13];
        for (int i = 0; i < 13; i++) {
            c[i] = Character.getNumericValue(jmbg.charAt(i));
        }

        int zbir = 7 * (c[0] + c[6]) +
                   6 * (c[1] + c[7]) +
                   5 * (c[2] + c[8]) +
                   4 * (c[3] + c[9]) +
                   3 * (c[4] + c[10]) +
                   2 * (c[5] + c[11]);

        int ostatak = zbir % 11;
        int k;

        if (ostatak == 0) {
            k = 0;
        } else {
            k = 11 - ostatak;
        }

        if (k > 9) {
            JOptionPane.showMessageDialog(dpf, "JMBG nije ispravan", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (k != c[12]) {
            JOptionPane.showMessageDialog(dpf, "Kontrolna cifra JMBG nije ispravna", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean validiraj(String imeP, String jmbg, String datum) {
        if (imeP.isEmpty()) {
            JOptionPane.showMessageDialog(dpf, "Ime i prezime ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (jmbg.isEmpty()) {
            JOptionPane.showMessageDialog(dpf, "JMBG ne sme biti prazan", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validirajJMBG(jmbg)) {
            return false;
        }
        if (datum.isEmpty()) {
            JOptionPane.showMessageDialog(dpf, "Datum rodjenja ne sme biti prazan", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Date datumRodjenja = Date.valueOf(datum);
            if (datumRodjenja.after(new Date(System.currentTimeMillis()))) {
                JOptionPane.showMessageDialog(dpf, "Datum rodjenja mora biti u proslosti", "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dpf, "Format datuma mora biti YYYY-MM-DD", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void addAtionListener() {
        dpf.addbtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    String imeP = dpf.getjTextFieldIP().getText().trim();
                    String jmbg = dpf.getjTextFieldJMBG().getText().trim();
                    String datum = dpf.getjTextFieldDate().getText().trim();

                    if (!validiraj(imeP, jmbg, datum)) return;

                    Kategorija kat = (Kategorija) dpf.getjComboBoxKategorija().getSelectedItem();
                    Date datumRodjenja = Date.valueOf(datum);
                    Polaznik p = new Polaznik(0, imeP, jmbg, datumRodjenja, kat);
                    Komunikacija.getInstance().dodajPolaznika(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dpf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne moze da zapamti polaznika", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        dpf.addbtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    String imeP = dpf.getjTextFieldIP().getText().trim();
                    String jmbg = dpf.getjTextFieldJMBG().getText().trim();
                    String datum = dpf.getjTextFieldDate().getText().trim();

                    if (!validiraj(imeP, jmbg, datum)) return;

                    Kategorija kat = (Kategorija) dpf.getjComboBoxKategorija().getSelectedItem();
                    int id = Integer.parseInt(dpf.getjTextFieldID().getText().trim());
                    Date datumRodjenja = Date.valueOf(datum);
                    Polaznik p = new Polaznik(id, imeP, jmbg, datumRodjenja, kat);
                    Komunikacija.getInstance().izmeniPolaznika(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dpf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne moze da zapamti polaznika", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public void otvoriFormu(FormaModEnum mod) {
        pripremiFormu(mod);
        dpf.setVisible(true);
        if (mod == FormaModEnum.DODAJ) {
            JOptionPane.showMessageDialog(dpf, "Sistem je kreirao polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void pripremiFormu(FormaModEnum mod) {
        dpf.getjComboBoxKategorija().removeAllItems();
        List<Kategorija> kategorije = Komunikacija.getInstance().ucitajKategorije();
        for (Kategorija k : kategorije) {
            dpf.getjComboBoxKategorija().addItem(k);
        }
        if (mod == FormaModEnum.DODAJ) {
            dpf.getjButtonDodaj().setVisible(true);
            dpf.getjButtonIzmeni().setVisible(false);
            dpf.getjTextFieldID().setVisible(false);
            dpf.getjLabelID().setVisible(false);
        } else {
            dpf.getjButtonDodaj().setVisible(false);
            dpf.getjButtonIzmeni().setVisible(true);
            dpf.getjTextFieldID().setVisible(true);
            dpf.getjTextFieldID().setEnabled(false);
            dpf.getjLabelID().setVisible(true);

            Polaznik p = (Polaznik) Koordinator.getInstance().vratiParam("polaznik");
            dpf.getjTextFieldIP().setText(p.getImePrezimePolaznika());
            dpf.getjTextFieldJMBG().setText(p.getJmbgPolaznika());
            dpf.getjTextFieldDate().setText(p.getDatumrodjenjaPolaznika().toString());
            dpf.getjComboBoxKategorija().setSelectedItem(p.getKategorija());
            dpf.getjTextFieldID().setText(p.getIdPolaznik() + "");
        }
    }
}