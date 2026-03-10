/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Instruktor;
import domen.Kategorija;
import domen.Licenca;
import domen.Polaznik;
import domen.StavkaZapisnika;
import domen.Usluga;
import domen.Zapisnik;
import java.util.List;
import operacije.kategorije.UcitajKategorijeSO;
import operacije.licence.DodajLicencuSO;
import operacije.licence.UcitajLicenceSO;
import operacije.login.LoginOperacijaSO;
import operacije.polaznici.IzmeniPolaznikaSO;
import operacije.polaznici.KreirajPolaznikaSO;
import operacije.polaznici.ObrisiPolaznikaSO;
import operacije.polaznici.UcitajPolaznikeSO;
import operacije.usluge.UcitajUslugeSO;
import operacije.zapisnici.IzmeniStavkuSO;
import operacije.zapisnici.IzmeniZapisnikSO;
import operacije.zapisnici.KreirajStavkuSO;
import operacije.zapisnici.KreirajZapisnikSO;
import operacije.zapisnici.ObrisiStavkuSO;
import operacije.zapisnici.ObrisiZapisnikSO;
import operacije.zapisnici.UcitajStavkeZapisnikaSO;
import operacije.zapisnici.UcitajZapisnikeSO;

/**
 *
 * @author Luka
 */
public class Controller {
    private static Controller instance;
    private Instruktor ulogovan;
    private Controller() {
        
    }
    
    public static Controller getInstance(){
        if (instance==null) {
            instance = new Controller();
        }
        return instance;
    }

    public Instruktor getUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(Instruktor ulogovan) {
        this.ulogovan = ulogovan;
    }

    public Instruktor login(Instruktor i) throws Exception {
        LoginOperacijaSO operacija = new LoginOperacijaSO();
        operacija.izvrsi(i, null);
        System.out.println("klasa Controller: "+operacija.getInstruktor());
        ulogovan=operacija.getInstruktor();
        return operacija.getInstruktor();
    }

    public List<Polaznik> ucitajPolaznike() throws Exception {
        UcitajPolaznikeSO operacija = new UcitajPolaznikeSO();
        operacija.izvrsi(null, null);
        System.out.println("klasa Controller: "+operacija.getPolaznici());

        return operacija.getPolaznici();
    }

    public void obrisiPolaznika(Polaznik p) throws Exception {
        ObrisiPolaznikaSO operacija = new ObrisiPolaznikaSO();
        operacija.izvrsi(p, null);
        
    }

    public List<Kategorija> ucitajKategorije() throws Exception {
        UcitajKategorijeSO operacija = new UcitajKategorijeSO();
        operacija.izvrsi(null, null);
        System.out.println("klasa Controller: "+operacija.getKategorije());

        return operacija.getKategorije();
        
    }

    public void dodajPolaznika(Polaznik p) throws Exception {
        KreirajPolaznikaSO operacija = new KreirajPolaznikaSO();
        operacija.izvrsi(p, null);
        
    }

    public void izmeniPolaznika(Polaznik p) throws Exception {
        IzmeniPolaznikaSO operacija = new IzmeniPolaznikaSO();
        operacija.izvrsi(p, null);

    }

    public List<Zapisnik> ucitajZapisnike() throws Exception {
        UcitajZapisnikeSO operacija = new UcitajZapisnikeSO();
        operacija.izvrsi(null, null);
        System.out.println("klasa Controller: "+operacija.getZapisnici());

        return operacija.getZapisnici();
     
    }

    public List<StavkaZapisnika> ucitajStavke(int id) throws Exception {
        UcitajStavkeZapisnikaSO operacija = new UcitajStavkeZapisnikaSO();
        operacija.izvrsi(id, null);
        System.out.println("klasa Controller: "+operacija.getStavke());

        return operacija.getStavke();
     
    }

    public void dodajZapisnik(Zapisnik z) throws Exception {
        KreirajZapisnikSO operacija = new KreirajZapisnikSO();
        operacija.izvrsi(z, null);
    }

    public void izmeniZapisnik(Zapisnik z) throws Exception {
        IzmeniZapisnikSO operacija = new IzmeniZapisnikSO();
        operacija.izvrsi(z, null);
    }

    public void obrisiZapisnik(Zapisnik p) throws Exception {
        ObrisiZapisnikSO operacija = new ObrisiZapisnikSO();
        operacija.izvrsi(p, null);
    }

    public void obrisiStavkuZapisnika(StavkaZapisnika p) throws Exception {
        ObrisiStavkuSO operacija = new ObrisiStavkuSO();
        operacija.izvrsi(p, null);
    }

    public void dodajStavku(StavkaZapisnika p) throws Exception {
        KreirajStavkuSO operacija = new KreirajStavkuSO();
        operacija.izvrsi(p, null);
    }

    public void izmeniStavku(StavkaZapisnika p) throws Exception {
        IzmeniStavkuSO operacija = new IzmeniStavkuSO();
        operacija.izvrsi(p, null);
        
    }

    public List<Usluga> ucitajUsluge() throws Exception {
        UcitajUslugeSO operacija = new UcitajUslugeSO();
        operacija.izvrsi(null, null);
        return operacija.getUsluge();
    }

    public List<Licenca> ucitajLicence() throws Exception {
        UcitajLicenceSO operacija = new UcitajLicenceSO();
        operacija.izvrsi(null, null);
        return operacija.getLicence();
    }

    public void dodajLicencu(Licenca l) throws Exception {
        DodajLicencuSO operacija = new DodajLicencuSO();
        operacija.izvrsi(l, null);
    }




}
