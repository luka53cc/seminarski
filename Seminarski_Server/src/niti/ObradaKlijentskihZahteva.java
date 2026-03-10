package niti;

import controller.Controller;
import domen.Instruktor;
import domen.Kategorija;
import domen.Licenca;
import domen.Polaznik;
import domen.StavkaZapisnika;
import domen.Usluga;
import domen.Zapisnik;
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
public class ObradaKlijentskihZahteva extends Thread {
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                
                // Ako primalac vrati null, klijent je prekinuo vezu
                if (zahtev == null) {
                    //System.out.println("Klijent je prekinuo vezu.");
                    break; 
                }

                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Instruktor i = (Instruktor) zahtev.getParam();
                        i = Controller.getInstance().login(i);
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
                    case UCITAJ_ZAPISNIKE:
                        List<Zapisnik> zapisnici = Controller.getInstance().ucitajZapisnike();
                        odgovor.setOdgovor(zapisnici);
                        break;
                    case UCITAJ_STAVKE:
                        List<StavkaZapisnika> stavke = Controller.getInstance().ucitajStavke((int) zahtev.getParam());
                        odgovor.setOdgovor(stavke);
                        break;
                    case UCITAJ_USLUGE:
                        List<Usluga> usluge = Controller.getInstance().ucitajUsluge();
                        odgovor.setOdgovor(usluge);
                        break;
                    case UCITAJ_LICENCE:
                        List<Licenca> licence = Controller.getInstance().ucitajLicence();
                        odgovor.setOdgovor(licence);
                        break;
                        
                    case DODAJ_ZAPISNIK:
                        try {
                            Zapisnik z = (Zapisnik) zahtev.getParam();
                            Controller.getInstance().dodajZapisnik(z);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_ZAPISNIK:
                        try {
                            Zapisnik z1 = (Zapisnik) zahtev.getParam();
                            Controller.getInstance().izmeniZapisnik(z1);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case OBRISI_ZAPISNIK:
                        try {
                            Zapisnik p = (Zapisnik) zahtev.getParam();
                            Controller.getInstance().obrisiZapisnik(p);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case OBRISI_STAVKU_ZAPISNIKA:
                        try {
                            StavkaZapisnika p = (StavkaZapisnika) zahtev.getParam();
                            Controller.getInstance().obrisiStavkuZapisnika(p);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_STAVKU_ZAPISNIKA:
                        try {
                            StavkaZapisnika p = (StavkaZapisnika) zahtev.getParam();
                            Controller.getInstance().dodajStavku(p);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case IZMENI_STAVKU_ZAPISNIKA:
                        try {
                            StavkaZapisnika p = (StavkaZapisnika) zahtev.getParam();
                            Controller.getInstance().izmeniStavku(p);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_LICENCU:
                        try {
                            Licenca l = (Licenca) zahtev.getParam();
                            Controller.getInstance().dodajLicencu(l);
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
                System.out.println("Nit za klijenta se gasi zbog greske: " + ex.getMessage());
                break; 
            }
        }
        
        prekiniNit();
    }

    public void prekiniNit() {
        kraj = true;
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}