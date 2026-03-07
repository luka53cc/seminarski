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
public class InLi implements ApstraktniDomenskiObjekat{
    private Instruktor instruktor;
    private Licenca licenca;
    private Date datumIzdavanja;
    private Date datumIsteka;

    public InLi() {
    }

    public InLi(Instruktor instruktor, Licenca licenca, Date datumIzdavanja, Date datumIsteka) {
        this.instruktor = instruktor;
        this.licenca = licenca;
        this.datumIzdavanja = datumIzdavanja;
        this.datumIsteka = datumIsteka;
    }

    @Override
    public String toString() {
        return "InLi{" + "isntruktor=" + instruktor + ", licenca=" + licenca + ", datumIzdavanja=" + datumIzdavanja + ", datumIsteka=" + datumIsteka + '}';
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
        final InLi other = (InLi) obj;
        if (!Objects.equals(this.instruktor, other.instruktor)) {
            return false;
        }
        if (!Objects.equals(this.licenca, other.licenca)) {
            return false;
        }
        if (!Objects.equals(this.datumIzdavanja, other.datumIzdavanja)) {
            return false;
        }
        return Objects.equals(this.datumIsteka, other.datumIsteka);
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Licenca getLicenca() {
        return licenca;
    }

    public void setLicenca(Licenca licenca) {
        this.licenca = licenca;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    @Override
    public String vratiNazivTabele() {
        return "inli";    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idInstruktor,idLicenca,datumIzdavanja,datumIsteka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return instruktor.getIdInstruktor()+","+licenca.getIdLicenca()+",'"+datumIzdavanja+"','"+datumIsteka+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "inli.idInstruktor="+instruktor.getIdInstruktor()+"and inli.idLicenca="+licenca.getIdLicenca();
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "idInstruktor="+instruktor.getIdInstruktor()+", idLicenca="+licenca.getIdLicenca()+", datumIzdavanja='"+datumIzdavanja+"', datumIsteka='"+datumIsteka+"'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {            
            Long idI=rs.getLong("inli.idInstruktor");
            Long idL=rs.getLong("inli.idLicenca");
            Date datum=rs.getDate("inli.datumIzdavanja");
            Date datumEnd=rs.getDate("inli.datumIsteka");
            Instruktor i = new Instruktor(idI, null, null, null, null, null);
            Licenca l = new Licenca(idL, null);
            InLi in = new InLi(i, l, datum, datumEnd);
            lista.add(in);
        }
        
        
        
        return lista;
        
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
