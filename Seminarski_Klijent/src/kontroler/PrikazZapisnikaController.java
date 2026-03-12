/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Kategorija;
import domen.Polaznik;
import domen.StavkaZapisnika;
import domen.Zapisnik;
import forme.PrikazZapisnikaForma;
import forme.model.ModelTabelePolaznik;
import forme.model.ModelTabeleStavkaZapisnika;
import forme.model.ModelTabeleZapisnici;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author Luka
 */
public class PrikazZapisnikaController {
        private final PrikazZapisnikaForma pzf;
        
    public PrikazZapisnikaController(PrikazZapisnikaForma pzf){
        this.pzf=pzf;
        addAtionListener();
        addMouseListener();
    }

    private void addAtionListener() {
        pzf.addbtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableZapisnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzf, "Sistem ne moze da nadje zapisnik", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pzf, "Sistem je nasao zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleZapisnici mtp = (ModelTabeleZapisnici) pzf.getjTableZapisnici().getModel();
                    Zapisnik p = mtp.getLista().get(red);
                    try {
                        Komunikacija.getInstance().obrisiZapisnik(p);
                        JOptionPane.showMessageDialog(pzf, "Sistem je obrisao zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pzf, "Sistem ne moze da obrise zapisnik", "Greska", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });

        pzf.addbtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableZapisnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzf, "Sistem ne moze da nadje zapisnik", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    //JOptionPane.showMessageDialog(ppf, "Sistem je nasao zapisnik", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleZapisnici mtp = (ModelTabeleZapisnici) pzf.getjTableZapisnici().getModel();
                    Zapisnik p = mtp.getLista().get(red);

                    Koordinator.getInstance().dodajParam("zapisnik", p);
                    Koordinator.getInstance().otvoriIzmeniZapisnikFormu();

                }
            }
        });
        pzf.addbtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tekst = pzf.getjTextFieldTekst().getText().trim();
                int trajanje = 0;
                try {
                    trajanje = Integer.parseInt(pzf.getjTextFieldTrajanje().getText().trim());
                } catch (Exception ex) {
                }
                String datumStr = pzf.getjTextFieldDate().getText().trim();

                Date datumEvidentiranja = null;
                if (!datumStr.isEmpty()) {
                    try {
                        datumEvidentiranja = Date.valueOf(datumStr);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pzf,
                                "Datum mora biti u formatu yyyy-MM-dd",
                                "Greška",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                ModelTabeleZapisnici mtp = (ModelTabeleZapisnici) pzf.getjTableZapisnici().getModel();
                mtp.pretrazi(tekst, trajanje, datumEvidentiranja);

                if (mtp.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(pzf,
                            "Sistem ne može da nađe zapisnike po zadatim kriterijumima",
                            "Informacija",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pzf,
                            "Sistem je našao zapisnike po zadatim kriterijumima",
                            "Informacija",
                            JOptionPane.INFORMATION_MESSAGE);
                    /*JOptionPane.showMessageDialog(pzf,
                            "Sistem je našao zapisnik",
                            "Informacija",
                            JOptionPane.INFORMATION_MESSAGE);*/
                }
            }
        });
        pzf.addbtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pzf.getjTextFieldDate().setText("");
                pzf.getjTextFieldTekst().setText("");
                pzf.getjTextFieldTrajanje().setText("");
                pripremiFormu();
            }
        });
        
        
        //stavkee
        
        
        
        pzf.addbtnObrisiStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzf, "Sistem ne moze da nadje stavku", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pzf, "Sistem je nasao stavku", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleStavkaZapisnika mtp = (ModelTabeleStavkaZapisnika) pzf.getjTableStavke().getModel();
                    StavkaZapisnika p = mtp.getLista().get(red);
                    try {
                        Komunikacija.getInstance().obrisiStavkuZapisnika(p);
                        JOptionPane.showMessageDialog(pzf, "Sistem je obrisao stavku", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pzf, "Sistem ne moze da obrise stavku", "Greska", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });

        pzf.addbtnIzmeniStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pzf.getjTableStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pzf, "Sistem ne moze da nadje stavku", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    int red2 = pzf.getjTableZapisnici().getSelectedRow();
                    ModelTabeleZapisnici mtz = (ModelTabeleZapisnici) pzf.getjTableZapisnici().getModel();
                    Zapisnik z = mtz.getLista().get(red2);
                    
                    ModelTabeleStavkaZapisnika mtp = (ModelTabeleStavkaZapisnika) pzf.getjTableStavke().getModel();
                    StavkaZapisnika p = mtp.getLista().get(red);
                    p.setZapisnik(z);
                    System.out.println(Integer.parseInt(pzf.getjTableStavke().getValueAt(red, 0)+""));
                    p.setRb(Integer.parseInt(pzf.getjTableStavke().getValueAt(red, 0)+""));

                    Koordinator.getInstance().dodajParam("stavkazapisnika", p);
                    Koordinator.getInstance().otvoriIzmeniStavkuFormu(p);

                }
            }
        });
        pzf.addbtnDodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red2 = pzf.getjTableZapisnici().getSelectedRow();
                ModelTabeleZapisnici mtz = (ModelTabeleZapisnici) pzf.getjTableZapisnici().getModel();
                Zapisnik z = mtz.getLista().get(red2);
                Koordinator.getInstance().otvoriDodajStavkuFormu(z);
            }
        });
        
        
        
        
        
        
        
    }
    private void addMouseListener() {
        pzf.getjTableZapisnici().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pzf.getjTableZapisnici().getSelectedRow();
                if (red!= -1) {
                    ModelTabeleZapisnici mtz = (ModelTabeleZapisnici) pzf.getjTableZapisnici().getModel();
                    Zapisnik z = mtz.getLista().get(red);
                    List<StavkaZapisnika> stavke = Komunikacija.getInstance().ucitajStavke(z.getIdZapisnik());
                    ModelTabeleStavkaZapisnika mtsz=new ModelTabeleStavkaZapisnika(stavke);
                    pzf.getjTableStavke().setModel(mtsz);
                    Koordinator.getInstance().setSelektovan(z);
                }
            }
        });
    }    
    
    public void otvoriFormu() {
        pripremiFormu();
        pzf.setVisible(true);
    }

    public void pripremiFormu() {

        List<Zapisnik> zapisnici = Komunikacija.getInstance().ucitajZapisnike();
        ModelTabeleZapisnici mtz = new ModelTabeleZapisnici(zapisnici);
        pzf.getjTableZapisnici().setModel(mtz);
        
        List<StavkaZapisnika> stavke = new ArrayList<>();
        ModelTabeleStavkaZapisnika mtsz = new ModelTabeleStavkaZapisnika(stavke);
        pzf.getjTableStavke().setModel(mtsz);
        
        
    }

    public void osveziFormu() {
    try {
        pripremiFormu(); 
    } catch (Exception e) {
        JOptionPane.showMessageDialog(pzf, "Greška pri osvežavanju: " + e.getMessage());
    }    
    }


    
    
}
