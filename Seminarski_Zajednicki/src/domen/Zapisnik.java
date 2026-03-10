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
public class Zapisnik implements ApstraktniDomenskiObjekat{
    
    private int idZapisnik;
    private Date datumEvidentiranja;
    private String tekst;
    private int ukupnoTrajanje;
    private Instruktor instruktor;
    private Polaznik polaznik;
    private List<StavkaZapisnika> stavkeZapisnika;

    public Zapisnik() {
    }

    public Zapisnik(int idZapisnik, Date datumEvidentiranja, String tekst, int ukupnoTrajanje, Instruktor instruktor, Polaznik polaznik, List<StavkaZapisnika> stavkeZapisnika) {
        this.idZapisnik = idZapisnik;
        this.datumEvidentiranja = datumEvidentiranja;
        this.tekst = tekst;
        this.ukupnoTrajanje = ukupnoTrajanje;
        this.instruktor = instruktor;
        this.polaznik = polaznik;
        this.stavkeZapisnika = stavkeZapisnika;
    }

    @Override
    public String toString() {
        return "Zapisnik{" + "idZapisnik=" + idZapisnik + ", datumEvidentiranja=" + datumEvidentiranja + ", tekst=" + tekst + ", ukupnoTrajanje=" + ukupnoTrajanje + ", isntruktor=" + instruktor + ", polaznik=" + polaznik + ", stavkeZapisnika=" + stavkeZapisnika + '}';
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
        final Zapisnik other = (Zapisnik) obj;
        return Objects.equals(this.idZapisnik, other.idZapisnik);
    }

    public int getIdZapisnik() {
        return idZapisnik;
    }

    public void setIdZapisnik(int idZapisnik) {
        this.idZapisnik = idZapisnik;
    }

    public Date getDatumEvidentiranja() {
        return datumEvidentiranja;
    }

    public void setDatumEvidentiranja(Date datumEvidentiranja) {
        this.datumEvidentiranja = datumEvidentiranja;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getUkupnoTrajanje() {
        return ukupnoTrajanje;
    }

    public void setUkupnoTrajanje(int ukupnoTrajanje) {
        this.ukupnoTrajanje = ukupnoTrajanje;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public List<StavkaZapisnika> getStavkeZapisnika() {
        return stavkeZapisnika;
    }

    public void setStavkeZapisnika(List<StavkaZapisnika> stavkeZapisnika) {
        this.stavkeZapisnika = stavkeZapisnika;
    }

        
//////////////////////////////////////////////////////////
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "zapisnik";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumEvidentiranja,tekst,ukupnoTrajanje,idInstruktor,idPolaznik";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+datumEvidentiranja+"','"+tekst+"',"+ukupnoTrajanje+","+instruktor.getIdInstruktor()+","+polaznik.getIdPolaznik();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zapisnik.idZapisnik="+idZapisnik;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumEvidentiranja='"+datumEvidentiranja+"', tekst='"+tekst+"', ukupnoTrajanje="+ukupnoTrajanje+", idInstruktor="+instruktor.getIdInstruktor()+", idPolaznik="+polaznik.getIdPolaznik();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {            
            int idZap=rs.getInt("zapisnik.idZapisnik");
            Date datum=rs.getDate("zapisnik.datumEvidentiranja");
            String tekst=rs.getString("zapisnik.tekst");
            int trajanje=rs.getInt("zapisnik.ukupnoTrajanje");
            int idIns=rs.getInt("zapisnik.idInstruktor");
            int idPol=rs.getInt("zapisnik.idPolaznik");
            
            Instruktor i = new Instruktor(idIns, null, null, null, null, null);
            Polaznik p = new Polaznik(idPol, null, null, null, null);
            
            Zapisnik z = new Zapisnik(idZap, datum, tekst, trajanje, i, p, stavkeZapisnika);
            lista.add(z);
            //dovrsi tj proveri
        }
        
        
        
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
