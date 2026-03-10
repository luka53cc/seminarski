/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zapisnici;

import domen.Polaznik;
import domen.Zapisnik;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class ObrisiZapisnikSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat==null || !(objekat instanceof Zapisnik)) {
            throw new Exception("Sistem ne moze da nadje zapisnik");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Zapisnik)objekat);
    }
    
}
