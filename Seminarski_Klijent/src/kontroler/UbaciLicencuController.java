package kontroler;

import domen.Licenca;
import forme.UbaciLicencuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

public class UbaciLicencuController {
    private final UbaciLicencuForma ulf;

    public UbaciLicencuController(UbaciLicencuForma ulf) {
        this.ulf = ulf;
        addAtionListener();
    }

    private void addAtionListener() {
        ulf.addbtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    Licenca l = (Licenca) ulf.getjComboBox1().getSelectedItem();

                    if (l == null) {
                        JOptionPane.showMessageDialog(ulf, "Morate izabrati licencu", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Komunikacija.getInstance().dodajLicencuZaInstruktora(l);
                    JOptionPane.showMessageDialog(ulf, "Sistem je zapamtio licencu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    ulf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ulf, "Sistem ne moze da zapamti licencu", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public void otvoriFormu() {
        pripremiFormu();
        ulf.setVisible(true);
    }

    public void pripremiFormu() {
        ulf.getjComboBox1().removeAllItems();
        List<Licenca> licence = Komunikacija.getInstance().ucitajLicence();
        for (Licenca k : licence) {
            ulf.getjComboBox1().addItem(k);
        }
    }
}