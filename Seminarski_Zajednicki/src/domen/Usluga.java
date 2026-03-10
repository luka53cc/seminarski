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
public class Usluga implements ApstraktniDomenskiObjekat{
    private int idUsluga;
    private String opisUsluge;
    private int trajanjeUsluge;
    private double cenaUsluge;

    public Usluga() {
    }

    @Override
    public String toString() {
        return "Usluga{" + "idUsluga=" + idUsluga + ", opisUsluge=" + opisUsluge + ", trajanjeUsluge=" + trajanjeUsluge + ", cenaUsluge=" + cenaUsluge + '}';
    }

    public Usluga(int idUsluga, String opisUsluge, int trajanjeUsluge, double cenaUsluge) {
        this.idUsluga = idUsluga;
        this.opisUsluge = opisUsluge;
        this.trajanjeUsluge = trajanjeUsluge;
        this.cenaUsluge = cenaUsluge;
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
        final Usluga other = (Usluga) obj;
        return Objects.equals(this.idUsluga, other.idUsluga);
    }

    public int getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(int idUsluga) {
        this.idUsluga = idUsluga;
    }

    public String getOpisUsluge() {
        return opisUsluge;
    }

    public void setOpisUsluge(String opisUsluge) {
        this.opisUsluge = opisUsluge;
    }

    public int getTrajanjeUsluge() {
        return trajanjeUsluge;
    }

    public void setTrajanjeUsluge(int trajanjeUsluge) {
        this.trajanjeUsluge = trajanjeUsluge;
    }

    public double getCenaUsluge() {
        return cenaUsluge;
    }

    public void setCenaUsluge(double cenaUsluge) {
        this.cenaUsluge = cenaUsluge;
    }

    @Override
    public String vratiNazivTabele() {
        return "usluga";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "opisUsluge,trajanjeUsluge,cenaUsluge";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+opisUsluge+"',"+trajanjeUsluge+","+cenaUsluge;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "usluga.idUsluga="+idUsluga;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "opisUsluge='"+opisUsluge+"', trajanjeUsluge="+trajanjeUsluge+", cenaUsluge="+cenaUsluge;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {            
            int id=rs.getInt("usluga.idUsluga");
            String opis=rs.getString("usluga.opisUsluge");
            int trajanje=rs.getInt("usluga.trajanjeUsluge");
            double cena=rs.getDouble("usluga.cenaUsluge");
            
            Usluga u = new Usluga(id, opis, trajanje, cena);
            lista.add(u);
        }
        
        
        
        return lista;    
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
