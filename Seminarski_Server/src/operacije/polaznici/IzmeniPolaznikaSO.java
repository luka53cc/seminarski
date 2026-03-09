/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.polaznici;

import domen.Polaznik;
import java.util.Date;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class IzmeniPolaznikaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //vidi dopuni mzd
        if (objekat==null || !(objekat instanceof Polaznik)) {
            throw new Exception("Sistemu nije prosledjen polaznik");
        }
        Polaznik p = (Polaznik) objekat;
        if (p.getImePrezimePolaznika()==null || p.getImePrezimePolaznika().isBlank()
                || p.getJmbgPolaznika()==null || p.getJmbgPolaznika().length()!=13
                || p.getKategorija().getIdKategorija()==null || p.getKategorija().getIdKategorija()<0 ||p.getKategorija().getIdKategorija()>10||
                p.getDatumrodjenjaPolaznika().after(new Date())) {
            throw new Exception("Greska u vrednosti atributa");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Polaznik)objekat);
    }
    
}
