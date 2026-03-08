/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Instruktor;
import forme.GlavnaForma;
import koordinator.Koordinator;

/**
 *
 * @author Luka
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;
        
    public GlavnaFormaController(GlavnaForma gf){
        this.gf=gf;
        addAtionListeners();
    }

    private void addAtionListeners() {
        
  
    }    

    public void otvoriFormu() {
        gf.setVisible(true);
        Instruktor ulogovan = Koordinator.getInstance().getUlogovan();
        gf.getjLabelUlogovan().setText(ulogovan.getImePrezimeInstruktora());
    }
}
