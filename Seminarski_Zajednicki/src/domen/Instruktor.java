/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Luka
 */
public class Instruktor implements ApstraktniDomenskiObjekat{
    private Long idInstruktor;
    private String imePrezimeInstruktora;
    private String korisnickoIme;
    private String sifra;
    private String brojTelefona;
    private Date datumZaposlenja;

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Instruktor other = (Instruktor) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String toString() {
        return "Ime prezime: " + imePrezimeInstruktora;
    }

    public Instruktor() {
    }

    public Instruktor(Long idInstruktor, String imePrezimeInstruktora, String korisnickoIme, String sifra, String brojTelefona, Date datumZaposlenja) {
        this.idInstruktor = idInstruktor;
        this.imePrezimeInstruktora = imePrezimeInstruktora;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.brojTelefona = brojTelefona;
        this.datumZaposlenja = datumZaposlenja;
    }

    public Long getIdInstruktor() {
        return idInstruktor;
    }

    public void setIdInstruktor(Long idInstruktor) {
        this.idInstruktor = idInstruktor;
    }

    public String getImePrezimeInstruktora() {
        return imePrezimeInstruktora;
    }

    public void setImePrezimeInstruktora(String imePrezimeInstruktora) {
        this.imePrezimeInstruktora = imePrezimeInstruktora;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    @Override
    public String vratiNazivTabele() {
        return "instruktor";    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezimeInstruktora,korisnickoIme,sifra,brojTelefona,datumZaposlenja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+imePrezimeInstruktora+"','"+korisnickoIme+"','"+sifra+"','"+brojTelefona+"','"+datumZaposlenja+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "instruktor.idInstruktor="+idInstruktor;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "imePrezimeInstruktora='"+imePrezimeInstruktora+"', korisnickoIme='"+korisnickoIme+"', sifra='"+sifra+"', brojtelefona='"+brojTelefona+"', datumZaposlenja='"+datumZaposlenja+"'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {            
            Long id=rs.getLong("instruktor.idInstruktor");
            Date datum=rs.getDate("instruktor.datumZaposlenja");
            String imeP=rs.getString("instruktor.imePrezimeInstruktora");
            String korIme=rs.getString("instruktor.korisnickoIme");
            String sif=rs.getString("instruktor.sifra");
            String brTel=rs.getString("instruktor.brojTelefona");
            
            Instruktor i = new Instruktor(id, imeP, korIme, sif, brTel, datum);
            lista.add(i);
        }
        
        
        
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
