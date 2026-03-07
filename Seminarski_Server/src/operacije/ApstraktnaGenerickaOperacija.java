/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import repository.Repository;
import repository.*;
import repository.db.DbRepository;
import repository.db.DBRepository.DbRepositoryGeneric;

/**
 *
 * @author Luka
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }
    
    public final void izvrsi(Object objekat,String kljuc)throws Exception{
        try {
            preduslovi(objekat);
            zaposniTransakciju();
            izvrsiOperaciju(objekat,kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
            ugasiKonekciju();
        }
    }

    protected abstract void preduslovi(Object objekat) throws Exception;

    private void zaposniTransakciju() throws Exception {
        ((DbRepository) broker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DbRepository) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        ((DbRepository) broker).rollback();
    }

    private void ugasiKonekciju() throws Exception {
        ((DbRepository) broker).disconnect();
    }

    protected abstract void izvrsiOperaciju(Object objekat, String kljuc)throws Exception;
    
    
}
