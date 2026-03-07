/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.login;

import domen.Instruktor;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Luka
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija{
    
    Instruktor instruktor;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<Instruktor> sviInstruktori=broker.getAll((Instruktor)objekat, null);
        System.out.println("LoginOperacija" + sviInstruktori);
        for (Instruktor i : sviInstruktori) {
            if (i.equals((Instruktor)objekat)) {
                instruktor=i;
                return;
            }
        }
        instruktor=null;
        
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }
    
}
