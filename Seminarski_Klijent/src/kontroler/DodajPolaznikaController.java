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
import modovi.FormaModPolaznikEnum;

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
                    
                    Polaznik p =new Polaznik(0, imeP, jmbg, datumRodjenja, kat);
                    Komunikacija.getInstance().dodajPolaznika(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    dpf.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Greska", "Greska", JOptionPane.ERROR_MESSAGE);
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
                    String imeP=dpf.getjTextFieldIP().getText().trim();
                    String jmbg = dpf.getjTextFieldJMBG().getText().trim();
                    String datum = dpf.getjTextFieldDate().getText().trim();
                    Kategorija kat = (Kategorija) dpf.getjComboBoxKategorija().getSelectedItem();
                    int id = Integer.parseInt(dpf.getjTextFieldID().getText().trim());
                    Date datumRodjenja = Date.valueOf(datum);
                    
                    Polaznik p =new Polaznik(id, imeP, jmbg, datumRodjenja, kat);
                    Komunikacija.getInstance().izmeniPolaznika(p);
                    JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    dpf.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Greska", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                
            }
        });
  
    }    

    public void otvoriFormu(FormaModPolaznikEnum mod) {
        pripremiFormu(mod);
        dpf.setVisible(true);
        if (mod==FormaModPolaznikEnum.DODAJ) {
            JOptionPane.showMessageDialog(dpf, "Sistem je kreirao polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void pripremiFormu(FormaModPolaznikEnum mod) {
        dpf.getjComboBoxKategorija().removeAllItems();
        List<Kategorija> kategorije = Komunikacija.getInstance().ucitajKategorije();
        for (Kategorija k : kategorije) {
            dpf.getjComboBoxKategorija().addItem(k);
        }
        if (mod==FormaModPolaznikEnum.DODAJ) {
            dpf.getjButtonDodaj().setVisible(true);
            dpf.getjButtonIzmeni().setVisible(false);
            dpf.getjTextFieldID().setVisible(false);
            dpf.getjLabelID().setVisible(false);
        }else {
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
            dpf.getjTextFieldID().setText(p.getIdPolaznik()+"");
        }
        
        
    }
    
}
