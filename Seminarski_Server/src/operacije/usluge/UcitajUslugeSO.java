/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.usluge;

import domen.Usluga;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class UcitajUslugeSO extends ApstraktnaGenerickaOperacija{
    List<Usluga> usluge;
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        usluge = broker.getAll(new Usluga(), null);
        
    }
    public List<Usluga> getUsluge() {
        return usluge;
    }
}
