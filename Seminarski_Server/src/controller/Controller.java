/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Instruktor;
import operacije.login.LoginOperacija;

/**
 *
 * @author Luka
 */
public class Controller {
    private static Controller instance;

    private Controller() {
        
    }
    
    public static Controller getInstance(){
        if (instance==null) {
            instance = new Controller();
        }
        return instance;
    }

    public Instruktor login(Instruktor i) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(i, null);
        System.out.println("klasa Controller: "+operacija.getInstruktor());
        return operacija.getInstruktor();
    }
}
