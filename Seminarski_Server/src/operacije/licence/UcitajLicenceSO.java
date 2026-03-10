/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.licence;

import domen.Licenca;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class UcitajLicenceSO extends ApstraktnaGenerickaOperacija{
    List<Licenca> licence;
    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        licence = broker.getAll(new Licenca(), null);
        
    }
    public List<Licenca> getLicence() {
        return licence;
    }
}
