/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Kategorija;
import domen.Polaznik;
import domen.StavkaZapisnika;
import domen.Zapisnik;
import forme.PrikazZapisnikaForma;
import forme.model.ModelTabeleStavkaZapisnika;
import forme.model.ModelTabeleZapisnici;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Luka
 */
public class PrikazZapisnikaController {
        private final PrikazZapisnikaForma pzf;
        
    public PrikazZapisnikaController(PrikazZapisnikaForma pzf){
        this.pzf=pzf;
        addAtionListener();
        addMouseListener();
    }

    private void addAtionListener() {
        
    }
    private void addMouseListener() {
        pzf.getjTableZapisnici().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pzf.getjTableZapisnici().getSelectedRow();
                if (red!= -1) {
                    ModelTabeleZapisnici mtz = (ModelTabeleZapisnici) pzf.getjTableZapisnici().getModel();
                    Zapisnik z = mtz.getLista().get(red);
                    List<StavkaZapisnika> stavke = Komunikacija.getInstance().ucitajStavke(z.getIdZapisnik());
                    ModelTabeleStavkaZapisnika mtsz=new ModelTabeleStavkaZapisnika(stavke);
                    pzf.getjTableStavke().setModel(mtsz);
                }
            }
        });
    }    
    
    public void otvoriFormu() {
        pripremiFormu();
        pzf.setVisible(true);
    }

    public void pripremiFormu() {

        List<Zapisnik> zapisnici = Komunikacija.getInstance().ucitajZapisnike();
        ModelTabeleZapisnici mtz = new ModelTabeleZapisnici(zapisnici);
        pzf.getjTableZapisnici().setModel(mtz);
        
        List<StavkaZapisnika> stavke = new ArrayList<>();
        ModelTabeleStavkaZapisnika mtsz = new ModelTabeleStavkaZapisnika(stavke);
        pzf.getjTableStavke().setModel(mtsz);
        
        
    }

    public void osveziFormu() {
    try {
        pripremiFormu(); 
    } catch (Exception e) {
        JOptionPane.showMessageDialog(pzf, "Greška pri osvežavanju: " + e.getMessage());
    }    
    }


    
    
}
