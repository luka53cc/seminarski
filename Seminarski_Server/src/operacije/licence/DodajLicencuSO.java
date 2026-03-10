/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.licence;

import controller.Controller;
import domen.InLi;
import domen.Licenca;
import java.sql.Date;
import java.time.LocalDate;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class DodajLicencuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        LocalDate datum = LocalDate.now();
        LocalDate datum2 = datum.plusYears(5);
        InLi inli = new InLi(Controller.getInstance().getUlogovan(), (Licenca)objekat, Date.valueOf(datum), Date.valueOf(datum2));
        System.out.println(inli);
        broker.add(inli);
    }
    
}
