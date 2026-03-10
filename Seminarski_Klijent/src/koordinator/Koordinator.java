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
import forme.PrikazZapisnikaForma;
import java.util.HashMap;
import java.util.Map;
import kontroler.DodajPolaznikaController;
import kontroler.GlavnaFormaController;
import kontroler.LoginController;
import kontroler.PrikazPolaznikaController;
import kontroler.PrikazZapisnikaController;
import modovi.FormaModPolaznikEnum;

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
    private PrikazZapisnikaController pzController;
    
    
    private Map<String, Object> parametri;
    
    private Koordinator() {
        parametri=new HashMap<>();
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
        dpController.otvoriFormu(FormaModPolaznikEnum.DODAJ);
    }
    public void otvoriPrikazZapisnikaFormu() {
        pzController = new PrikazZapisnikaController(new PrikazZapisnikaForma());
        pzController.otvoriFormu();
        
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


    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriIzmeniPacijentaFormu() {
        dpController = new DodajPolaznikaController(new DodajPolaznikaForma());
        dpController.otvoriFormu(FormaModPolaznikEnum.IZMENI);
        
    }

    public void osveziFormu() {
        ppController.osveziFormu();
    }
    
    
}
