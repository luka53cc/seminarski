/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author Luka
 */
public class DodajZapisnikController {
    List<StavkaZapisnika> stavke=new ArrayList<>();
    private final DodajZapisnikForma dzf;
    Zapisnik z;
        
    public DodajZapisnikController(DodajZapisnikForma dzf){
        this.z = new Zapisnik();
        this.dzf=dzf;
        addAtionListener();
        dzf.getjButtonSacuvaj().setEnabled(false);
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
                    z.setDatumEvidentiranja(datumE);
                    z.setTekst(tekst);
                    z.setUkupnoTrajanje(trajanje);
                    z.setInstruktor(Koordinator.getInstance().getUlogovan());
                    z.setPolaznik(polaznik);
                    z.setStavkeZapisnika(stavke);
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
        dzf.addbtnSacuvajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvaj(e);
            }

            private void sacuvaj(ActionEvent e) {
                try {
                    int trajanje = Integer.parseInt(dzf.getjTextFieldTrajanje().getText().trim());
                    String datum = dzf.getjTextFieldDate().getText().trim();
                    Polaznik polaznik = (Polaznik) dzf.getjComboBoxPolaznik().getSelectedItem();
                    String tekst = dzf.getjTextArea1().getText().trim();
                    Date datumE = Date.valueOf(datum);
                    Zapisnik z = new Zapisnik(0, datumE,tekst , trajanje, Koordinator.getInstance().getUlogovan(), polaznik, stavke);
                    
                    Komunikacija.getInstance().dodajZapisnik(z);
                    JOptionPane.showMessageDialog(dzf, "Sistem je zapamtio zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                    dzf.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dzf, "Greska", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }
        });
        dzf.addbtnDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                Koordinator.getInstance().otvoriDodajStavkuDialog(z);
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
                if (red==-1) {
                    JOptionPane.showMessageDialog(dzf, "izaberite red", "greska", JOptionPane.ERROR_MESSAGE);
                }
                stavke.remove(red);
                dzf.getjButtonSacuvaj().setEnabled(true);


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
        ModelTabeleStavkaZapisnika mtsz = new ModelTabeleStavkaZapisnika(stavke);
        dzf.getjTableStavke().setModel(mtsz);

        
        
    }
    }
