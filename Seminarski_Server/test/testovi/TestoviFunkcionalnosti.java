package testovi;


import domen.Instruktor;
import domen.Kategorija;
import domen.Polaznik;
import domen.StavkaZapisnika;
import domen.Usluga;
import domen.Zapisnik;
import operacije.login.LoginOperacijaSO;
import operacije.zapisnici.KreirajZapisnikSO;
import repository.db.DBRepository.DbRepositoryGeneric;
import repository.db.DbConnectionFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TestoviFunkcionalnosti {

    private Instruktor napraviInstruktora(String korIme, String sifra) {
        Instruktor i = new Instruktor();
        i.setKorisnickoIme(korIme);
        i.setSifra(sifra);
        return i;
    }

    private Zapisnik napraviZapisnik(Instruktor instruktor, Polaznik polaznik) {
        Zapisnik z = new Zapisnik();
        z.setDatumEvidentiranja(Date.valueOf("2026-01-01"));
        z.setTekst("Hazmat obuka polaznika");
        z.setUkupnoTrajanje(60);
        z.setInstruktor(instruktor);
        z.setPolaznik(polaznik);
        z.setStavkeZapisnika(new ArrayList<>());
        return z;
    }

    @Test
    public void testLoginIspravniKredencijali() {
        try {
            DbConnectionFactory.getInstance().getConnection();
            Instruktor unos = napraviInstruktora("Radenko", "Radenko1!");
            LoginOperacijaSO so = new LoginOperacijaSO();
            so.izvrsi(unos, null);
            Instruktor rezultat = so.getInstruktor();
            assertNotNull("Instruktor treba da bude pronadjen", rezultat);
            assertEquals("Korisnicko ime treba da se poklapa", "Radenko", rezultat.getKorisnickoIme());
        } catch (Exception e) {
            fail("Nije ocekivano da dodje do greske: " + e.getMessage());
        }
    }

    @Test
    public void testLoginPogresnaLozinka() {
        try {
            DbConnectionFactory.getInstance().getConnection();
            Instruktor unos = napraviInstruktora("Radenko", "pogresnaLozinka");
            LoginOperacijaSO so = new LoginOperacijaSO();
            so.izvrsi(unos, null);
            Instruktor rezultat = so.getInstruktor();
            assertNull("Instruktor ne treba da bude pronadjen sa pogresnom lozinkom", rezultat);
        } catch (Exception e) {
            fail("Nije ocekivano da dodje do greske: " + e.getMessage());
        }
    }

    @Test
    public void testLoginNepostojeceKorisnickoIme() {
        try {
            DbConnectionFactory.getInstance().getConnection();
            Instruktor unos = napraviInstruktora("nepostojeci_korisnik", "sifra123");
            LoginOperacijaSO so = new LoginOperacijaSO();
            so.izvrsi(unos, null);
            Instruktor rezultat = so.getInstruktor();
            assertNull("Instruktor ne treba da bude pronadjen", rezultat);
        } catch (Exception e) {
            fail("Nije ocekivano da dodje do greske: " + e.getMessage());
        }
    }

    @Test
    public void testKreirajZapisnikBezStavki() {
        try {
            DbConnectionFactory.getInstance().getConnection();
            Instruktor instruktor = napraviInstruktora("Radenko", "Radenko1!");
            instruktor.setIdInstruktor(1);
            Kategorija kat = new Kategorija();
            kat.setIdKategorija(1);
            Polaznik polaznik = new Polaznik();
            polaznik.setIdPolaznik(1);
            polaznik.setKategorija(kat);
            Zapisnik z = napraviZapisnik(instruktor, polaznik);
            KreirajZapisnikSO so = new KreirajZapisnikSO();
            so.izvrsi(z, null);
            assertTrue("Generisani ID mora biti veci od 0", z.getIdZapisnik() > 0);
        } catch (Exception e) {
            fail("Nije ocekivano da dodje do greske: " + e.getMessage());
        }
    }

    @Test
    public void testKreirajZapisnikSaStavkama() {
        try {
            DbConnectionFactory.getInstance().getConnection();
            Instruktor instruktor = napraviInstruktora("Radenko", "Radenko1!");
            instruktor.setIdInstruktor(1);
            Kategorija kat = new Kategorija();
            kat.setIdKategorija(1);
            Polaznik polaznik = new Polaznik();
            polaznik.setIdPolaznik(1);
            polaznik.setKategorija(kat);
            Zapisnik z = napraviZapisnik(instruktor, polaznik);
            Usluga u = new Usluga();
            u.setIdUsluga(1);
            u.setOpisUsluge("Hazmat");
            StavkaZapisnika sz = new StavkaZapisnika();
            sz.setTekst("Hazmat obuka");
            sz.setTrajanjeStavke(60);
            sz.setUsluga(u);
            z.getStavkeZapisnika().add(sz);
            KreirajZapisnikSO so = new KreirajZapisnikSO();
            so.izvrsi(z, null);
            assertTrue("Generisani ID mora biti veci od 0", z.getIdZapisnik() > 0);
            assertEquals("Stavka treba da ima ispravan idZapisnik", z.getIdZapisnik(), sz.getZapisnik().getIdZapisnik());
        } catch (Exception e) {
            fail("Nije ocekivano da dodje do greske: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllInstruktori() {
        try {
            DbConnectionFactory.getInstance().getConnection();
            DbRepositoryGeneric repo = new DbRepositoryGeneric();
            List rezultat = repo.getAll(new Instruktor(), null);
            assertNotNull("Lista ne sme biti null", rezultat);
            assertTrue("Lista mora imati bar jednog instruktora", rezultat.size() > 0);
        } catch (Exception e) {
            fail("Nije ocekivano da dodje do greske: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllSaUslovom() {
        try {
            DbConnectionFactory.getInstance().getConnection();
            DbRepositoryGeneric repo = new DbRepositoryGeneric();
            List rezultat = repo.getAll(new Instruktor(), " WHERE korisnickoIme='Radenko'");
            assertNotNull("Lista ne sme biti null", rezultat);
            assertEquals("Ocekujemo tacno jednog instruktora", 1, rezultat.size());
        } catch (Exception e) {
            fail("Nije ocekivano da dodje do greske: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllPraznaLista() {
        try {
            DbConnectionFactory.getInstance().getConnection();
            DbRepositoryGeneric repo = new DbRepositoryGeneric();
            List rezultat = repo.getAll(new Instruktor(), " WHERE korisnickoIme='nepostojeci'");
            assertNotNull("Lista ne sme biti null", rezultat);
            assertEquals("Ocekujemo praznu listu", 0, rezultat.size());
        } catch (Exception e) {
            fail("Nije ocekivano da dodje do greske: " + e.getMessage());
        }
    }
}