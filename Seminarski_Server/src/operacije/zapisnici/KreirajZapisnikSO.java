/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zapisnici;

import domen.StavkaZapisnika;
import domen.Zapisnik;
import operacije.ApstraktnaGenerickaOperacija;
import repository.db.DBRepository.DbRepositoryGeneric;

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
        Zapisnik z = (Zapisnik) objekat;

        DbRepositoryGeneric dbBroker = (DbRepositoryGeneric) broker;
        int generisaniId = dbBroker.addAndReturnKey(z);
        z.setIdZapisnik(generisaniId);

        System.out.println("Generisani ID: " + generisaniId);
        System.out.println(z.getStavkeZapisnika());

        for (StavkaZapisnika sz : z.getStavkeZapisnika()) {
            sz.setZapisnik(z); // sada ima pravi ID
            broker.add(sz);
        }
    }
    
}
