package kontroler;

import domen.Instruktor;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

public class LoginController {
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addAtionListeners();
    }

    private void addAtionListeners() {
        lf.loginActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String korIme = lf.getjTextField1().getText().trim();
                String sifra = String.valueOf(lf.getjPasswordField1().getPassword()).trim();

                if (korIme.isEmpty()) {
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime ne sme biti prazno", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (sifra.isEmpty()) {
                    JOptionPane.showMessageDialog(lf, "Sifra ne sme biti prazna", "Greska", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Komunikacija.getInstance().konekcija();
                    Instruktor i = Komunikacija.getInstance().login(korIme, sifra);

                    if (i == null) {
                        // SK8 - alternativni scenario 5.1
                        JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Koordinator.getInstance().setUlogovan(i);
                        // SK8 - korak 5
                        JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra su ispravni", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            // SK8 - korak 6
                            Koordinator.getInstance().otvoriGlavnuFormu();
                            lf.dispose();
                        } catch (Exception ex) {
                            // SK8 - alternativni scenario 6.1
                            JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni", "Greska", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
}