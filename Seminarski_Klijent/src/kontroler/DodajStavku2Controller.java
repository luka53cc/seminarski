/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.StavkaZapisnika;
import domen.Usluga;
import forme.DodajStavkuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import modovi.FormaModEnum;

/**
 *
 * @author Luka
 */
public class DodajStavku2Controller {

    private final DodajStavkuForma dsf;

    public DodajStavku2Controller(DodajStavkuForma dsf) {
        this.dsf = dsf;
        addAtionListener();
    }

    private void addAtionListener() {
        // jButtonDodaj - mod=true, zapisnik already exists in DB
        dsf.addbtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int trajanje = Integer.parseInt(dsf.getjTextFieldTrajanje().getText().trim());
                    Usluga u = (Usluga) dsf.getjComboBox1().getSelectedItem();
                    String tekst = dsf.getjTextArea1().getText().trim();
                    StavkaZapisnika sz = new StavkaZapisnika(dsf.getZapisnik(), 0, tekst, trajanje, u);
                    dsf.getStavke().add(sz);
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Greska u nekom od polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // jButtonDodaj2 - mod=false, new zapisnik being created
        dsf.addbtnDodaj2ActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int trajanje = Integer.parseInt(dsf.getjTextFieldTrajanje().getText().trim());
                    Usluga u = (Usluga) dsf.getjComboBox1().getSelectedItem();
                    String tekst = dsf.getjTextArea1().getText().trim();
                    StavkaZapisnika sz = new StavkaZapisnika(dsf.getZapisnik(), 0, tekst, trajanje, u);
                    dsf.getStavke().add(sz); // adds to the same stavke reference from DodajZapisnikController
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Greska u nekom od polja", "Greska", JOptionPane.ERROR_MESSAGE);
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
        if (mod==true) {
            dsf.getjButtonDodaj().setVisible(true);
            dsf.getjButtonDodaj2().setVisible(false);
        }else{
            dsf.getjButtonDodaj().setVisible(false);
            dsf.getjButtonDodaj2().setVisible(true);
        }


    }
}