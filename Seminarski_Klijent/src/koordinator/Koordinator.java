/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import forme.LoginForma;
import kontroler.LoginController;

/**
 *
 * @author Luka
 */
public class Koordinator {
    private static Koordinator instance;
    private LoginController loginController;
    private Koordinator() {
        
    }
    
    public static Koordinator getInstance(){
        if (instance==null) {
            instance = new Koordinator();
        }
        return instance;
    }    

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }
}
