/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Kategorija;
import domen.Polaznik;
import forme.DodajPolaznikaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author Luka
 */
public class DodajPolaznikaController {
    private final DodajPolaznikaForma dpf;
        
    public DodajPolaznikaController(DodajPolaznikaForma dpf){
        this.dpf=dpf;
        addAtionListener();
    }

    private void addAtionListener() {
        dpf.addbtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    String imeP=dpf.getjTextFieldIP().getText().trim();
                    String jmbg = dpf.getjTextFieldJMBG().getText().trim();
                    String datum = dpf.getjTextFieldDate().getText().trim();
                    Kategorija kat = (Kategorija) dpf.getjComboBoxKategorija().getSelectedItem();
                    Date datumRodjenja = Date.valueOf(datum);
                    
                    Polaznik p =new Polaznik(Long.MIN_VALUE, imeP, jmbg, datumRodjenja, kat);
                    Komunikacija.getInstance().dodajPolaznika(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    Koordinator.getInstance().osveziTabeluPolaznika();

                    dpf.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Greska", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                
            }
        });
  
    }    

    public void otvoriFormu() {
        pripremiFormu();
        dpf.setVisible(true);
        JOptionPane.showMessageDialog(dpf, "Sistem je kreirao polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

    }

    public void pripremiFormu() {
        dpf.getjComboBoxKategorija().removeAllItems();
        List<Kategorija> kategorije = Komunikacija.getInstance().ucitajKategorije();
        for (Kategorija k : kategorije) {
            dpf.getjComboBoxKategorija().addItem(k);
        }
    }
    
}
