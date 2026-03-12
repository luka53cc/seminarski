/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Instruktor;
import domen.StavkaZapisnika;
import domen.Zapisnik;
import forme.DodajPolaznikaForma;
import forme.DodajStavkuForma;
import forme.DodajStavkuFormaOld;

import forme.DodajZapisnikForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazPolaznikaForma;
import forme.PrikazZapisnikaForma;
import forme.UbaciLicencuForma;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kontroler.DodajPolaznikaController;
import kontroler.DodajStavkuController;
import kontroler.DodajStavku2Controller;
import kontroler.DodajZapisnikController;
import kontroler.GlavnaFormaController;
import kontroler.LoginController;
import kontroler.PrikazPolaznikaController;
import kontroler.PrikazZapisnikaController;
import kontroler.UbaciLicencuController;
import modovi.FormaModEnum;

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
    private DodajZapisnikController dzController;
    private DodajStavkuController dszController;
    private UbaciLicencuController ulController;
    private DodajStavku2Controller dsdController;
    
    private Zapisnik selektovan;
    
    
    
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
    public void otvoriDodajPolaznikaFormu() {
        dpController = new DodajPolaznikaController(new DodajPolaznikaForma());
        dpController.otvoriFormu(FormaModEnum.DODAJ);
    }
    public void otvoriPrikazZapisnikaFormu() {
        pzController = new PrikazZapisnikaController(new PrikazZapisnikaForma());
        pzController.otvoriFormu();
        
    }

    public Zapisnik getSelektovan() {
        return selektovan;
    }

    public void setSelektovan(Zapisnik selektovan) {
        this.selektovan = selektovan;
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

    public void otvoriIzmeniPolaznikaFormu() {
        dpController = new DodajPolaznikaController(new DodajPolaznikaForma());
        dpController.otvoriFormu(FormaModEnum.IZMENI);
        
    }

    public void osveziFormu() {
        pzController.pripremiFormu();
    }

    public void otvoriIzmeniZapisnikFormu() {
        dzController = new DodajZapisnikController(new DodajZapisnikForma());
        dzController.otvoriFormu(FormaModEnum.IZMENI);
    }

    public void otvoriDodajZapisnikFormu() {
        dzController = new DodajZapisnikController(new DodajZapisnikForma());
        dzController.otvoriFormu(FormaModEnum.DODAJ);
    }

    public void otvoriIzmeniStavkuFormu(StavkaZapisnika sz) {
        dszController = new DodajStavkuController(new DodajStavkuFormaOld(null,sz,2));
        dszController.otvoriFormu(FormaModEnum.IZMENI);
    }

    public void otvoriDodajStavkuFormu(Zapisnik z) {
        dszController = new DodajStavkuController(new DodajStavkuFormaOld(z,null,1));
        dszController.otvoriFormu(FormaModEnum.DODAJ);
    }

    public void otvoriUbaciLicencuFormu() {
        ulController = new UbaciLicencuController(new UbaciLicencuForma());
        ulController.otvoriFormu();
    }

    public void otvoriDodajStavkuDialog(Zapisnik zapisnik) {
        dsdController= new DodajStavku2Controller(new DodajStavkuForma(zapisnik,null,true));
        dsdController.otvoriFormu(true);
    }

    public void otvoriDodajStavkuZaKreirajZapisnik(Zapisnik zapisnik,List<StavkaZapisnika> stavke) {
        dsdController= new DodajStavku2Controller(new DodajStavkuForma(zapisnik,stavke,true));
        dsdController.otvoriFormu(false);
    }
    
    
}
