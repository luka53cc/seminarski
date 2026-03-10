/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zapisnici;

import domen.StavkaZapisnika;
import domen.Zapisnik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class UcitajStavkeZapisnikaSO extends ApstraktnaGenerickaOperacija{
    List<StavkaZapisnika> stavke;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " join zapisnik on stavkazapisnika.idZapisnik = zapisnik.idZapisnik join usluga on stavkazapisnika.idUsluga=usluga.idUsluga WHERE stavkazapisnika.idZapisnik="+(int)objekat+" ";
        stavke = broker.getAll(new StavkaZapisnika(), uslov);
    }

    public List<StavkaZapisnika> getStavke() {
        return stavke;
    }
    
    
}
