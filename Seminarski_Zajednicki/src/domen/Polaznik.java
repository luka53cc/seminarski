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
public class Polaznik implements ApstraktniDomenskiObjekat{
    private Long idPolaznik;
    private String imePrezimePolaznika;
    private String jmbgPolaznika;
    private Date datumrodjenjaPolaznika;
    private Kategorija kategorija;

    public Polaznik() {
    }

    public Polaznik(Long idPolaznik, String imePrezimePolaznika, String jmbgPolaznika, Date datumrodjenjaPolaznika, Kategorija kategorija) {
        this.idPolaznik = idPolaznik;
        this.imePrezimePolaznika = imePrezimePolaznika;
        this.jmbgPolaznika = jmbgPolaznika;
        this.datumrodjenjaPolaznika = datumrodjenjaPolaznika;
        this.kategorija = kategorija;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Polaznik other = (Polaznik) obj;
        return Objects.equals(this.idPolaznik, other.idPolaznik);
    }

    @Override
    public String toString() {
        return  imePrezimePolaznika;
    }

    public Long getIdPolaznik() {
        return idPolaznik;
    }

    public void setIdPolaznik(Long idPolaznik) {
        this.idPolaznik = idPolaznik;
    }

    public String getImePrezimePolaznika() {
        return imePrezimePolaznika;
    }

    public void setImePrezimePolaznika(String imePrezimePolaznika) {
        this.imePrezimePolaznika = imePrezimePolaznika;
    }

    public String getJmbgPolaznika() {
        return jmbgPolaznika;
    }

    public void setJmbgPolaznika(String jmbgPolaznika) {
        this.jmbgPolaznika = jmbgPolaznika;
    }

    public Date getDatumrodjenjaPolaznika() {
        return datumrodjenjaPolaznika;
    }

    public void setDatumrodjenjaPolaznika(Date datumrodjenjaPolaznika) {
        this.datumrodjenjaPolaznika = datumrodjenjaPolaznika;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String vratiNazivTabele() {
        return "polaznik";    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezimePolaznika,jmbgPolaznika,datumRodjenjaPolaznika,idKategorija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+imePrezimePolaznika+"','"+jmbgPolaznika+"','"+datumrodjenjaPolaznika+"',"+kategorija.getIdKategorija();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "polaznik.idPolaznik="+idPolaznik;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "imePrezimePolaznika='"+imePrezimePolaznika+"', jmbgPolaznika='"+jmbgPolaznika+"', datumRodjenjaPolaznika='"+datumrodjenjaPolaznika+"', idkategorija="+kategorija.getIdKategorija();
                
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {            
            Long id=rs.getLong("polaznik.idPolaznik");
            Date datum=rs.getDate("polaznik.datumRodjenjaPolaznika");
            String imeP=rs.getString("polaznik.imePrezimePolaznika");
            String jmbg=rs.getString("polaznik.jmbgPolaznika");
            Long idKat=rs.getLong("polaznik.idKategorija");
            Kategorija k = new Kategorija(idKat, null, null);
            Polaznik p = new Polaznik(id, imeP, jmbg, datum, k);
            lista.add(p);
        }
        
        
        
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
