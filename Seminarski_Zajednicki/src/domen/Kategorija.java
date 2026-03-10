/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Luka
 */
public class Kategorija implements ApstraktniDomenskiObjekat{
    private int idKategorija;
    private String nazivKategorije;
    private String opisKategorije;

    public Kategorija() {
    }

    public Kategorija(int idKategorija, String nazivKategorije, String opisKategorije) {
        this.idKategorija = idKategorija;
        this.nazivKategorije = nazivKategorije;
        this.opisKategorije = opisKategorije;
    }

    @Override
    public String toString() {
        return  nazivKategorije;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKategorija);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Kategorija other = (Kategorija) obj;
        return this.idKategorija == other.idKategorija;
    }

    public int getIdKategorija() {
        return idKategorija;
    }

    public void setIdKategorija(int idKategorija) {
        this.idKategorija = idKategorija;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public String getOpisKategorije() {
        return opisKategorije;
    }

    public void setOpisKategorije(String opisKategorije) {
        this.opisKategorije = opisKategorije;
    }

    @Override
    public String vratiNazivTabele() {
        return "kategorija";    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivKategorije,opisKategorije";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "kategorija.idKategorija="+idKategorija;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kategorija.idKategorija="+idKategorija;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "nazivKategorije='"+nazivKategorije+"', opisKategorije='"+opisKategorije+"'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {            
            int id=rs.getInt("kategorija.idKategorija");
            String naziv=rs.getString("kategorija.nazivKategorije");
            String opis=rs.getString("kategorija.opisKategorije");
            
            Kategorija k = new Kategorija(id, naziv, opis);
            lista.add(k);
        }
        
        
        
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
