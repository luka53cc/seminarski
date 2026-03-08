/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Instruktor;
import forme.DodajPolaznikaForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazPolaznikaForma;
import kontroler.DodajPolaznikaController;
import kontroler.GlavnaFormaController;
import kontroler.LoginController;
import kontroler.PrikazPolaznikaController;

/**
 *
 * @author Luka
 */
public class Koordinator {
    private Instruktor ulogovan;
    private static Koordinator instance;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazPolaznikaController ppController;
    private DodajPolaznikaController dpController;
    private Koordinator() {
        
    }
    
    public static Koordinator getInstance(){
        if (instance==null) {
            instance = new Koordinator();
        }
        return instance;
    }    

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }
    public void otvoriPrikazPolaznikaFormu() {
        ppController = new PrikazPolaznikaController(new PrikazPolaznikaForma());
        ppController.otvoriFormu();
        
    }
    public void otvoriDodajPacijentaFormu() {
        dpController = new DodajPolaznikaController(new DodajPolaznikaForma());
        dpController.otvoriFormu();
    }


    public Instruktor getUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(Instruktor ulogovan) {
        this.ulogovan = ulogovan;
    }

    public void osveziTabeluPolaznika() {
        ppController.pripremiFormu();
    }



    
    
    
}
