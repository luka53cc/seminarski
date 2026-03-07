/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Instruktor;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luka
 */
public class Komunikacija {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    
    
    private static Komunikacija instance;

    private Komunikacija() {
        
    }
    
    public static Komunikacija getInstance(){
        if (instance==null) {
            instance = new Komunikacija();
        }
        return instance;
    }
   
    
    public void konekcija(){
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Nije povezan server");
        }
    }

    public Instruktor login(String korIme, String sifra) {
        Instruktor i = new Instruktor();
        i.setKorisnickoIme(korIme);
        i.setSifra(sifra);
        Zahtev zahtev = new Zahtev(Operacije.LOGIN, i);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        i=(Instruktor) odg.getOdgovor();
        return i;
    }
    
    
    
    
    
    
    
}
