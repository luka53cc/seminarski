/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Instruktor;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Luka
 */
public class LoginController {
    
    private final LoginForma lf;
    
    public LoginController(LoginForma lf){
        this.lf=lf;
        addAtionListeners();
    }

    private void addAtionListeners() {
        
        lf.loginActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String korIme=lf.getjTextField1().getText().trim();
                String sifra = String.valueOf(lf.getjPasswordField1().getPassword()).trim();
            
                Komunikacija.getInstance().konekcija();
                Instruktor i = Komunikacija.getInstance().login(korIme,sifra);
                
                if (i==null) {
                    JOptionPane.showMessageDialog(lf, "Nije uspesno ulogovan", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                JOptionPane.showMessageDialog(lf, "Uspesno ulogovan", "USpeh", JOptionPane.INFORMATION_MESSAGE);
                lf.dispose();
                }
                    
                
            }
            
        });
        
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
    
}
