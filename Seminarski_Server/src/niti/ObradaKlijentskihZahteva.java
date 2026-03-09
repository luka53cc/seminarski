/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Instruktor;
import domen.Kategorija;
import domen.Polaznik;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
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
                    case UCITAJ_POLAZNIKE:
                        List<Polaznik> polaznici = Controller.getInstance().ucitajPolaznike();
                        odgovor.setOdgovor(polaznici);
                        break;
                    case OBRISI_POLAZNIKA:
                        try {
                            Polaznik p = (Polaznik) zahtev.getParam();
                            Controller.getInstance().obrisiPolaznika(p);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_KATEGORIJE:
                        List<Kategorija> kategorije = Controller.getInstance().ucitajKategorije();
                        odgovor.setOdgovor(kategorije);
                        break;
                    case DODAJ_POLAZNIKA:
                        try {
                            Polaznik p = (Polaznik) zahtev.getParam();
                            Controller.getInstance().dodajPolaznika(p);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_POLAZNIKA:
                        try {
                            Polaznik p1 = (Polaznik) zahtev.getParam();
                            Controller.getInstance().izmeniPolaznika(p1);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
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
