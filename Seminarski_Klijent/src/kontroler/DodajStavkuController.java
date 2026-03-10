/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Polaznik;
import domen.StavkaZapisnika;
import domen.Usluga;
import domen.Zapisnik;
import forme.DodajStavkuForma;
import forme.DodajZapisnikForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import modovi.FormaModEnum;

/**
 *
 * @author Luka
 */
public class DodajStavkuController {

    private final DodajStavkuForma dsf;

    public DodajStavkuController(DodajStavkuForma dsf) {
        this.dsf = dsf;
        addAtionListener();
    }

    private void addAtionListener() {
        dsf.addbtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    int trajanje = Integer.parseInt(dsf.getjTextFieldTrajanje().getText().trim());
                    Usluga u = (Usluga) dsf.getjComboBox1().getSelectedItem();
                    String tekst = dsf.getjTextArea1().getText().trim();
                    StavkaZapisnika sz = new StavkaZapisnika(Koordinator.getInstance().getSelektovan(), 0, tekst, trajanje, u);

                    Komunikacija.getInstance().dodajStavku(sz);
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio stavku", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    dsf.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Greska u nekom od polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }
        });
        dsf.addbtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                try {
                    int trajanje = Integer.parseInt(dsf.getjTextFieldTrajanje().getText().trim());
                    Usluga u = (Usluga) dsf.getjComboBox1().getSelectedItem();
                    String tekst = dsf.getjTextArea1().getText().trim();
                    StavkaZapisnika sz = new StavkaZapisnika(Koordinator.getInstance().getSelektovan(), 0, tekst, trajanje, u);

                    Komunikacija.getInstance().izmeniStavku(sz);
                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio stavku", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    dsf.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Greska u nekom od polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }
        });

    }

    public void otvoriFormu(FormaModEnum mod) {
        pripremiFormu(mod);
        dsf.setVisible(true);
        if (mod == FormaModEnum.DODAJ) {
            JOptionPane.showMessageDialog(dsf, "Sistem je kreirao zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void pripremiFormu(FormaModEnum mod) {
        dsf.getjComboBox1().removeAllItems();
        List<Usluga> usluge = Komunikacija.getInstance().ucitajUsluge();
        for (Usluga k : usluge) {
            dsf.getjComboBox1().addItem(k);
        }
        if (mod == FormaModEnum.DODAJ) {
            dsf.getjButtonDodaj().setVisible(true);
            dsf.getjButtonIzmeni().setVisible(false);
            dsf.getjComboBox1().setEnabled(true);

        } else {
            dsf.getjButtonDodaj().setVisible(false);
            dsf.getjButtonIzmeni().setVisible(true);

            StavkaZapisnika p = (StavkaZapisnika) Koordinator.getInstance().vratiParam("stavkazapisnika");
            dsf.getjTextFieldTrajanje().setText(p.getTrajanjeStavke() + "");
            dsf.getjComboBox1().setSelectedItem(p.getUsluga());
            dsf.getjTextArea1().setText(p.getTekst());
            dsf.getjComboBox1().setEnabled(false);
        }

    }
}
