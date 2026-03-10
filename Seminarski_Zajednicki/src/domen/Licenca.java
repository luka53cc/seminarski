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
public class Licenca implements ApstraktniDomenskiObjekat{
    private int idLicenca;
    private String nazivLicence;

    public Licenca() {
    }

    public Licenca(int idLicenca, String nazivLicence) {
        this.idLicenca = idLicenca;
        this.nazivLicence = nazivLicence;
    }

    @Override
    public String toString() {
        return  nazivLicence ;
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
        final Licenca other = (Licenca) obj;
        return Objects.equals(this.idLicenca, other.idLicenca);
    }

   


    public int getIdLicenca() {
        return idLicenca;
    }

    public void setIdLicenca(int idLicenca) {
        this.idLicenca = idLicenca;
    }

    public String getNazivLicence() {
        return nazivLicence;
    }

    public void setNazivLicence(String nazivLicence) {
        this.nazivLicence = nazivLicence;
    }

    @Override
    public String vratiNazivTabele() {
        return "licenca";    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivLicence";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivLicence+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "licenca.idLicenca="+idLicenca;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "nazivLicence='"+nazivLicence+"'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {            
            int id=rs.getInt("licenca.idLicenca");
            String naziv=rs.getString("licenca.nazivLicence");
            
            Licenca l = new Licenca(id, naziv);
            lista.add(l);
        }
        
        
        
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
