/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Kategorija;
import domen.Polaznik;
import forme.PrikazPolaznikaForma;
import forme.model.ModelTabelePolaznik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author Luka
 */
public class PrikazPolaznikaController {
    private final PrikazPolaznikaForma ppf;
        
    public PrikazPolaznikaController(PrikazPolaznikaForma ppf){
        this.ppf=ppf;
        addAtionListener();
    }

    private void addAtionListener() {
        ppf.addbtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getjTablePolaznici().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje polaznika", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(ppf, "Sistem je nasao polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabelePolaznik mtp = (ModelTabelePolaznik) ppf.getjTablePolaznici().getModel();
                    Polaznik p = mtp.getLista().get(red);
                    try {
                        Komunikacija.getInstance().obrisiPolaznika(p);
                        JOptionPane.showMessageDialog(ppf, "Sistem je obrisao polaznika", "Uspeh",JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ppf, "Sistem ne moze da obrise polaznika", "Greska",JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        
        ppf.addbtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getjTablePolaznici().getSelectedRow();
                if (red==-1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne moze da nadje polaznika", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    //JOptionPane.showMessageDialog(ppf, "Sistem je nasao polaznika", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabelePolaznik mtp = (ModelTabelePolaznik) ppf.getjTablePolaznici().getModel();
                    Polaznik p = mtp.getLista().get(red);
                    
                    Koordinator.getInstance().dodajParam("polaznik", p);
                    Koordinator.getInstance().otvoriIzmeniPacijentaFormu();
                    
                    
                }
            }
        });
        ppf.addbtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imeP = ppf.getjTextFieldIP().getText().trim();
                String jmbg = ppf.getjTextFieldJMBG().getText().trim();
                String datumStr = ppf.getjTextFieldDate().getText().trim();
                Kategorija kat = (Kategorija) ppf.getjComboBoxKategorija().getSelectedItem();

                java.sql.Date datumRodjenja = null;

                try {
                    if (!datumStr.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        java.util.Date utilDate = sdf.parse(datumStr);

                        // Konverzija u java.sql.Date za bazu
                        datumRodjenja = new java.sql.Date(utilDate.getTime());
                    }

                    ModelTabelePolaznik mtp = (ModelTabelePolaznik) ppf.getjTablePolaznici().getModel();

                    mtp.pretrazi(imeP, jmbg, kat, datumRodjenja);

                } catch (ParseException ex) {
                    // Ako korisnik unese loš format datuma
                    JOptionPane.showMessageDialog(ppf, "Unesite datum u formatu dd.MM.yyyy");
                } catch (Exception ex) {
                    // Hvatanje ostalih grešaka (npr. ClassCastException za tabelu)
                    ex.printStackTrace();
                }
                    
                
            }
        });
        ppf.addbtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osveziFormu();
            }
        });
  
    }    

    public void otvoriFormu() {
        pripremiFormu();
        ppf.setVisible(true);
    }

    public void pripremiFormu() {
        ppf.getjComboBoxKategorija().removeAllItems();
        List<Kategorija> kategorije = Komunikacija.getInstance().ucitajKategorije();
        for (Kategorija k : kategorije) {
            ppf.getjComboBoxKategorija().addItem(k);
        }

        List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
        ModelTabelePolaznik mtp = new ModelTabelePolaznik(polaznici);
        ppf.getjTablePolaznici().setModel(mtp);
    }

    public void osveziFormu() {
    try {
        pripremiFormu(); 
    } catch (Exception e) {
        JOptionPane.showMessageDialog(ppf, "Greška pri osvežavanju: " + e.getMessage());
    }    }
    
}
