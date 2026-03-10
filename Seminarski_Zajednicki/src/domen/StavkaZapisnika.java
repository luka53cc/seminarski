/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Luka
 */
public class StavkaZapisnika implements ApstraktniDomenskiObjekat{
    private Zapisnik zapisnik;
    private int rb;
    private String tekst;
    private int trajanjeStavke;
    private Usluga usluga;

    public StavkaZapisnika() {
    }

    public StavkaZapisnika(Zapisnik zapisnik, int rb, String tekst, int trajanjeStavke, Usluga usluga) {
        this.zapisnik = zapisnik;
        this.rb = rb;
        this.tekst = tekst;
        this.trajanjeStavke = trajanjeStavke;
        this.usluga = usluga;
    }

    @Override
    public String toString() {
        return "StavkaZapisnika{" + "zapisnik=" + zapisnik + ", rb=" + rb + ", tekst=" + tekst + ", trajanjeStavke=" + trajanjeStavke + ", usluga=" + usluga + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.rb;
        hash = 79 * hash + Objects.hashCode(this.zapisnik != null ? this.zapisnik.getIdZapisnik() : 0);
        return hash;   
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final StavkaZapisnika other = (StavkaZapisnika) obj;

        // Provera rednog broja
        if (this.rb != other.rb) {
            return false;
        }

        // Provera da li su oba zapisnika null ili oba nisu null
        if (this.zapisnik == null && other.zapisnik == null) {
            return true;
        }
        if (this.zapisnik == null || other.zapisnik == null) {
            return false;
        }

        // Provera ID-ja zapisnika (ovo je ključno!)
        return Objects.equals(this.zapisnik.getIdZapisnik(), other.zapisnik.getIdZapisnik());
        
    }

  

    public Zapisnik getZapisnik() {
        return zapisnik;
    }

    public void setZapisnik(Zapisnik zapisnik) {
        this.zapisnik = zapisnik;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getTrajanjeStavke() {
        return trajanjeStavke;
    }

    public void setTrajanjeStavke(int trajanjeStavke) {
        this.trajanjeStavke = trajanjeStavke;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkazapisnika";    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idZapisnik,tekst,trajanjeStavke,idUsluga";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return zapisnik.getIdZapisnik()+",'"+tekst+"',"+trajanjeStavke+","+usluga.getIdUsluga();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkazapisnika.idZapisnik="+zapisnik.getIdZapisnik()+"and stavkazapisnika.rb="+rb;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "idZapisnik="+zapisnik.getIdZapisnik()+", tekst='"+tekst+"', trajanjeStavke="+trajanjeStavke+", idUsluga="+usluga.getIdUsluga();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {            
            int idZ=rs.getInt("stavkazapisnika.idZapisnik");
            int rb=rs.getInt("stavkazapisnika.rb");
            String tekst=rs.getString("stavkazapisnika.tekst");
            int trajanje=rs.getInt("stavkazapisnika.trajanjeStavke");
            int idU=rs.getInt("stavkazapisnika.idUsluga");
            
            Zapisnik z = new Zapisnik(idZ, null, null, 0, null, null, null);
            z.setDatumEvidentiranja(rs.getDate("zapisnik.datumEvidentiranja"));
            z.setTekst(rs.getString("zapisnik.tekst"));
            z.setUkupnoTrajanje(rs.getInt("zapisnik.ukupnoTrajanje"));
            z.setInstruktor(new Instruktor(rs.getInt("zapisnik.instruktor"), null, null, null, null, null));
            z.setPolaznik(new Polaznik(rs.getInt("zapisnik.polaznik"), null, null, null, null));
            
            Usluga u = new Usluga(idU, null, 0, 0);
            u.setOpisUsluge(rs.getString("usluga.opisUsluge"));
            u.setTrajanjeUsluge(rs.getInt("usluga.trajanjeUsluge"));
            u.setCenaUsluge(rs.getDouble("usluga.cenaUsluge"));
            StavkaZapisnika sz = new StavkaZapisnika(z, rb, tekst, trajanje, u);
            lista.add(sz);
            
            
            
            //vrv ne valja
            
        }
        
        return lista;    
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
