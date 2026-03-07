/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Luka
 */
public class Server extends Thread{
    ServerSocket serverSocket;
    boolean kraj=false;
    List<ObradaKlijentskihZahteva> klijenti;

    public Server() {
        klijenti=new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            while (!kraj) {
                Socket s = serverSocket.accept();
                System.out.println("Klijent je povezan");
                
                ObradaKlijentskihZahteva okz =new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
                
            }
        } catch (IOException ex) {
            if (!kraj) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "Greska na serveru!", ex);
            } else {
                System.out.println("Server je zaustavljen (ocekivano zatvaranje socketa).");
            }        
        }
    }

    
    public void zaustaviServer(){
        kraj=true;
        try {
            for (ObradaKlijentskihZahteva k : klijenti) {
                k.prekiniNit();
            }
            
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
