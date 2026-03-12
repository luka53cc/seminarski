/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 *
 * @author Luka
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties konf;

    private Konfiguracija() {
        konf = new Properties();
        try {
            konf.load(new FileInputStream("C:\\PS_K1\\seminarski\\Seminarski_Server\\config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Konfiguracija getInstance(){
        if (instance==null) {
            instance = new Konfiguracija();
        }return instance;
    }

    public String getProperty(String key){
        return konf.getProperty(key, "N/A");
    }
    
    public void setProperty(String key,String value){
        konf.setProperty(key, value);
    }
    
    public void sacuvajIzmene(){
        try {
            konf.store(new FileOutputStream("C:\\PS_K1\\seminarski\\Seminarski_Server\\config.properties"),null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
}
