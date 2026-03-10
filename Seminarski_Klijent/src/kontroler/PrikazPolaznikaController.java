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
                    Koordinator.getInstance().otvoriIzmeniPolaznikaFormu();
                    
                    
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

                Date datumRodjenja = null;

                if (!datumStr.isEmpty()) {
                    try {
                        datumRodjenja = Date.valueOf(datumStr);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ppf,
                                "Datum mora biti u formatu yyyy-MM-dd",
                                "Greška",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                ModelTabelePolaznik mtp =
                        (ModelTabelePolaznik) ppf.getjTablePolaznici().getModel();

                mtp.pretrazi(imeP, jmbg, datumRodjenja, kat);

            }
        });
        ppf.addbtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
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
        Kategorija sve = new Kategorija(0, "sve kategorije",null);
        ppf.getjComboBoxKategorija().addItem(sve);
        for (Kategorija k : kategorije) {
            ppf.getjComboBoxKategorija().addItem(k);
        }
        ppf.getjComboBoxKategorija().setSelectedIndex(0);

        List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
        ModelTabelePolaznik mtp = new ModelTabelePolaznik(polaznici);
        ppf.getjTablePolaznici().setModel(mtp);
    }

    
}
