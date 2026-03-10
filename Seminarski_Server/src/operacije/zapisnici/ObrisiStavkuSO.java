/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zapisnici;

import domen.StavkaZapisnika;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class ObrisiStavkuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat==null || !(objekat instanceof StavkaZapisnika)) {
            throw new Exception("Sistem ne moze da nadje stavku");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((StavkaZapisnika)objekat);
    }
    
}
