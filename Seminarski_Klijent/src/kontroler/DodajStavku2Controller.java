package kontroler;

import domen.StavkaZapisnika;
import domen.Usluga;
import forme.DodajStavkuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import modovi.FormaModEnum;

public class DodajStavku2Controller {
    private final DodajStavkuForma dsf;

    public DodajStavku2Controller(DodajStavkuForma dsf) {
        this.dsf = dsf;
        addAtionListener();
    }

    private boolean validiraj(String trajanjeStr, Usluga u) {
        if (trajanjeStr.isEmpty()) {
            JOptionPane.showMessageDialog(dsf, "Trajanje ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int trajanje = Integer.parseInt(trajanjeStr);
            if (trajanje <= 0) {
                JOptionPane.showMessageDialog(dsf, "Trajanje mora biti vece od 0", "Greska", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dsf, "Trajanje mora biti broj", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (u == null) {
            JOptionPane.showMessageDialog(dsf, "Morate izabrati uslugu", "Greska", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void addAtionListener() {
        dsf.addbtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String trajanjeStr = dsf.getjTextFieldTrajanje().getText().trim();
                    Usluga u = (Usluga) dsf.getjComboBox1().getSelectedItem();
                    String tekst = dsf.getjTextArea1().getText().trim();

                    if (!validiraj(trajanjeStr, u)) return;

                    int trajanje = Integer.parseInt(trajanjeStr);
                    StavkaZapisnika sz = new StavkaZapisnika(dsf.getZapisnik(), 0, tekst, trajanje, u);
                    dsf.getStavke().add(sz);
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti stavku zapisnika", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        dsf.addbtnDodaj2ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String trajanjeStr = dsf.getjTextFieldTrajanje().getText().trim();
                    Usluga u = (Usluga) dsf.getjComboBox1().getSelectedItem();
                    String tekst = dsf.getjTextArea1().getText().trim();

                    if (!validiraj(trajanjeStr, u)) return;

                    int trajanje = Integer.parseInt(trajanjeStr);
                    StavkaZapisnika sz = new StavkaZapisnika(dsf.getZapisnik(), 0, tekst, trajanje, u);
                    dsf.getStavke().add(sz);
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti stavku zapisnika", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public void otvoriFormu(boolean mod) {
        pripremiFormu(mod);
        dsf.setVisible(true);
    }

    public void pripremiFormu(boolean mod) {
        dsf.getjComboBox1().removeAllItems();
        List<Usluga> usluge = Komunikacija.getInstance().ucitajUsluge();
        for (Usluga k : usluge) {
            dsf.getjComboBox1().addItem(k);
        }
        if (mod) {
            dsf.getjButtonDodaj().setVisible(true);
            dsf.getjButtonDodaj2().setVisible(false);
        } else {
            dsf.getjButtonDodaj().setVisible(false);
            dsf.getjButtonDodaj2().setVisible(true);
        }
    }
}