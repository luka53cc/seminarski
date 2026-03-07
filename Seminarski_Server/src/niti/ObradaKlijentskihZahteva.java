/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Instruktor;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.*;


/**
 *
 * @author Luka
 */
public class ObradaKlijentskihZahteva extends Thread{
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj=false;
    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket=socket;
        posiljalac = new Posiljalac(socket);
        primalac= new Primalac(socket);
    }
    
    
    
    @Override
    public void run() {
        while (!kraj) {            
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                
                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Instruktor i = (Instruktor) zahtev.getParam();
                        // moze i sa novim ovim instruktorom
                        i =Controller.getInstance().login(i);
                        odgovor.setOdgovor(i);
                        break;
                    default:
                        System.out.println("GRESKA, OPERACIJA NE POSTOJI");
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void prekiniNit(){
        kraj=true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
    
}
