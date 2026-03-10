/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Instruktor;
import domen.Kategorija;
import domen.Licenca;
import domen.Polaznik;
import domen.StavkaZapisnika;
import domen.Usluga;
import domen.Zapisnik;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author Luka
 */
public class Komunikacija {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    
    
    private static Komunikacija instance;

    private Komunikacija() {
        
    }
    
    public static Komunikacija getInstance(){
        if (instance==null) {
            instance = new Komunikacija();
        }
        return instance;
    }
   
    
    public void konekcija(){
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Nije povezan server");
        }
    }

    public Instruktor login(String korIme, String sifra) {
        Instruktor i = new Instruktor();
        i.setKorisnickoIme(korIme);
        i.setSifra(sifra);
        Zahtev zahtev = new Zahtev(Operacije.LOGIN, i);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        i=(Instruktor) odg.getOdgovor();
        return i;
    }

    public List<Polaznik> ucitajPolaznike() {
        Zahtev z = new Zahtev(Operacije.UCITAJ_POLAZNIKE, null);
        List<Polaznik> polaznici = new ArrayList<>();
        
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        polaznici= (List<Polaznik>) odg.getOdgovor();
        
        return polaznici;
    }

    public void obrisiPolaznika(Polaznik p) throws Exception {
        Zahtev z = new Zahtev(Operacije.OBRISI_POLAZNIKA, p);
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            
        }else{
            throw new Exception("greska");
        }
        
    }

    public List<Kategorija> ucitajKategorije() {
        Zahtev z = new Zahtev(Operacije.UCITAJ_KATEGORIJE, null);
        List<Kategorija> kategorije = new ArrayList<>();
        
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        kategorije= (List<Kategorija>) odg.getOdgovor();
        
        return kategorije;
        
    }

    public void dodajPolaznika(Polaznik p) throws Exception {
        Zahtev z = new Zahtev(Operacije.DODAJ_POLAZNIKA, p);
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            
        }else{
            throw (Exception)odg.getOdgovor();
        }
       
    }

    public void izmeniPolaznika(Polaznik p) throws Exception {
        Zahtev z = new Zahtev(Operacije.IZMENI_POLAZNIKA, p);
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            Koordinator.getInstance().osveziFormu();
        }else{
            throw (Exception)odg.getOdgovor();
        }
       



    }

    public List<Zapisnik> ucitajZapisnike() {
        Zahtev z = new Zahtev(Operacije.UCITAJ_ZAPISNIKE, null);
        List<Zapisnik> zapisnici = new ArrayList<>();
        
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        zapisnici= (List<Zapisnik>) odg.getOdgovor();
        
        return zapisnici;
    }

    public List<StavkaZapisnika> ucitajStavke(int id) {
        Zahtev z = new Zahtev(Operacije.UCITAJ_STAVKE, id);
        List<StavkaZapisnika> stavke = new ArrayList<>();
        
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        stavke= (List<StavkaZapisnika>) odg.getOdgovor();
        
        return stavke;
    }

    public void dodajZapisnik(Zapisnik z) throws Exception {
        Zahtev zh = new Zahtev(Operacije.DODAJ_ZAPISNIK, z);
        posiljalac.posalji(zh);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            
        }else{
            throw (Exception)odg.getOdgovor();
        }
    }

    public void izmeniZapisnik(Zapisnik z) throws Exception {
        Zahtev zh = new Zahtev(Operacije.IZMENI_ZAPISNIK, z);
        posiljalac.posalji(zh);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            Koordinator.getInstance().osveziFormu();
        }else{
            throw (Exception)odg.getOdgovor();
        }
    }

    public void obrisiZapisnik(Zapisnik p) throws Exception {
        Zahtev z = new Zahtev(Operacije.OBRISI_ZAPISNIK, p);
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            
        }else{
            throw new Exception("greska");
        }
    }

    public void obrisiStavkuZapisnika(StavkaZapisnika p) throws Exception {
        Zahtev z = new Zahtev(Operacije.OBRISI_STAVKU_ZAPISNIKA, p);
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            
        }else{
            throw new Exception("greska");
        }
    }

    public void dodajStavku(StavkaZapisnika sz) throws Exception {
        Zahtev z = new Zahtev(Operacije.DODAJ_STAVKU_ZAPISNIKA,sz);
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            
        }else{
            throw new Exception("greska");
        }
    }

    public void izmeniStavku(StavkaZapisnika sz) throws Exception {
        Zahtev z = new Zahtev(Operacije.IZMENI_STAVKU_ZAPISNIKA,sz);
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            
        }else{
            throw new Exception("greska");
        }


    }

    public List<Usluga> ucitajUsluge() {
        Zahtev z = new Zahtev(Operacije.UCITAJ_USLUGE, null);
        List<Usluga> zapisnici = new ArrayList<>();
        
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        zapisnici= (List<Usluga>) odg.getOdgovor();
        
        return zapisnici;


    }

    public List<Licenca> ucitajLicence() {
        Zahtev z = new Zahtev(Operacije.UCITAJ_LICENCE, null);
        List<Licenca> zapisnici = new ArrayList<>();
        
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        zapisnici= (List<Licenca>) odg.getOdgovor();
        
        return zapisnici;
    }

    public void dodajLicencuZaInstruktora(Licenca l) throws Exception {
        Zahtev z = new Zahtev(Operacije.DODAJ_LICENCU,l);
        posiljalac.posalji(z);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor()==null ) {
            
        }else{
            throw new Exception("greska");
        }

    }
    
    
    
    
    
    
    
}
