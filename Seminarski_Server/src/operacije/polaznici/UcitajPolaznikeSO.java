/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.polaznici;

import domen.Polaznik;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class UcitajPolaznikeSO extends ApstraktnaGenerickaOperacija{
    List<Polaznik> polaznici;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String ostatak = " JOIN kategorija ON polaznik.idKategorija=kategorija.idKategorija ";
        polaznici = broker.getAll(new Polaznik(), ostatak);
    }

    public List<Polaznik> getPolaznici() {
        return polaznici;
    }
    
}
