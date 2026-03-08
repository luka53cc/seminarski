/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kategorije;

import domen.Kategorija;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class UcitajKategorijeSO extends ApstraktnaGenerickaOperacija{
    List<Kategorija> kategorije = new ArrayList<>();
    @Override
    protected void preduslovi(Object objekat) throws Exception {
 
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        kategorije = broker.getAll(new Kategorija(), null);
    }

    public List<Kategorija> getKategorije() {
        return kategorije;
    }
    
}
