/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Polaznik;
import forme.PrikazPolaznikaForma;
import forme.model.ModelTabelePolaznik;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

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
  
    }    

    public void otvoriFormu() {
        pripremiFormu();
        ppf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Polaznik> polaznici = Komunikacija.getInstance().ucitajPolaznike();
        ModelTabelePolaznik mtp = new ModelTabelePolaznik(polaznici);
        ppf.getjTablePolaznici().setModel(mtp);
    }
    
}
