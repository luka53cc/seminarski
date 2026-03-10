/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Polaznik;
import domen.Zapisnik;
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
public class DodajZapisnikController {
    private final DodajZapisnikForma dzf;
        
    public DodajZapisnikController(DodajZapisnikForma dzf){
        this.dzf=dzf;
        addAtionListener();
    }

    private void addAtionListener() {
        dzf.addbtnDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    int trajanje = Integer.parseInt(dzf.getjTextFieldTrajanje().getText().trim());
                    String datum = dzf.getjTextFieldDate().getText().trim();
                    Polaznik polaznik = (Polaznik) dzf.getjComboBoxPolaznik().getSelectedItem();
                    String tekst = dzf.getjTextArea1().getText().trim();
                    Date datumE = Date.valueOf(datum);
                    Zapisnik z = new Zapisnik(0, datumE,tekst , trajanje, Koordinator.getInstance().getUlogovan(), polaznik, null);
                    
                    Komunikacija.getInstance().dodajZapisnik(z);
                    JOptionPane.showMessageDialog(dzf, "Sistem je zapamtio zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    dzf.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dzf, "Greska", "Greska", JOptionPane.ERROR_MESSAGE);
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
                    int id = Integer.parseInt(dzf.getjTextFieldID().getText().trim());
                    int trajanje = Integer.parseInt(dzf.getjTextFieldTrajanje().getText().trim());
                    String datum = dzf.getjTextFieldDate().getText().trim();
                    Polaznik polaznik = (Polaznik) dzf.getjComboBoxPolaznik().getSelectedItem();
                    String tekst = dzf.getjTextArea1().getText().trim();
                    Date datumE = Date.valueOf(datum);
                    Zapisnik z = new Zapisnik(id, datumE,tekst , trajanje, Koordinator.getInstance().getUlogovan(), polaznik, null);
                    
                    Komunikacija.getInstance().izmeniZapisnik(z);
                    JOptionPane.showMessageDialog(dzf, "Sistem je zapamtio zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    dzf.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dzf, "Greska u nekom od polja", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                
            }
        });
  
    }    

    public void otvoriFormu(FormaModEnum mod) {
        pripremiFormu(mod);
        dzf.setVisible(true);
        if (mod==FormaModEnum.DODAJ) {
            JOptionPane.showMessageDialog(dzf, "Sistem je kreirao zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void pripremiFormu(FormaModEnum mod) {
        dzf.getjComboBoxPolaznik().removeAllItems();
        List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
        for (Polaznik k : polaznici) {
            dzf.getjComboBoxPolaznik().addItem(k);
        }
        if (mod==FormaModEnum.DODAJ) {
            dzf.getjButtonDodaj().setVisible(true);
            dzf.getjButtonIzmeni().setVisible(false);
        }else {
            dzf.getjButtonDodaj().setVisible(false);
            dzf.getjButtonIzmeni().setVisible(true);
            dzf.getjTextFieldID().setVisible(true);
            dzf.getjTextFieldID().setEnabled(false);
            dzf.getjTextFieldInstruktor().setEnabled(false);
            dzf.getjTextFieldID().setEnabled(false);

            Zapisnik p = (Zapisnik) Koordinator.getInstance().vratiParam("zapisnik");
            dzf.getjTextFieldInstruktor().setText(Koordinator.getInstance().getUlogovan().getImePrezimeInstruktora());
            dzf.getjTextFieldInstruktor().setEnabled(false);
            dzf.getjTextFieldTrajanje().setText(p.getUkupnoTrajanje()+"");
            dzf.getjTextFieldDate().setText(p.getDatumEvidentiranja().toString());
            dzf.getjComboBoxPolaznik().setSelectedItem(p.getPolaznik());
            dzf.getjTextArea1().setText(p.getTekst());
            dzf.getjTextFieldID().setText(p.getIdZapisnik()+"");
        }
        
        
    }
    }
