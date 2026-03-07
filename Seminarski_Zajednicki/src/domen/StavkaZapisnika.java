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
    private Long rb;
    private String tekst;
    private int trajanjeStavke;
    private Usluga usluga;

    public StavkaZapisnika() {
    }

    public StavkaZapisnika(Zapisnik zapisnik, Long rb, String tekst, int trajanjeStavke, Usluga usluga) {
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
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaZapisnika other = (StavkaZapisnika) obj;
        return Objects.equals(this.rb, other.rb);
    }

    public Zapisnik getZapisnik() {
        return zapisnik;
    }

    public void setZapisnik(Zapisnik zapisnik) {
        this.zapisnik = zapisnik;
    }

    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
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
            Long idZ=rs.getLong("stavkazapisnika.idZapisnik");
            Long rb=rs.getLong("stavkazapisnika.rb");
            String tekst=rs.getString("stavkazapisnika.tekst");
            int trajanje=rs.getInt("stavkazapisnika.trajanjeStavke");
            Long idU=rs.getLong("stavkazapisnika.idUsluga");
            
            Zapisnik z = new Zapisnik(idZ, null, null, 0, null, null, null);
            Usluga u = new Usluga(idU, null, 0, 0);
            
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
