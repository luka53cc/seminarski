/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zapisnici;

import domen.Zapisnik;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class KreirajZapisnikSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Zapisnik)objekat);
    }
    
}
