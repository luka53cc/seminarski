/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.DBRepository;

import java.sql.*;
import domen.ApstraktniDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbRepository;
import repository.db.DbConnectionFactory;

/**
 *
 * @author Luka
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat>{

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param,String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov !=null) {
            //valjda ovako
            upit+=" WHERE ";
            upit+=uslov;
        }
        System.out.println(upit);
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        
        lista =  param.vratiListuIzRs(rs);
        
        rs.close();
        st.close();
        return lista;    
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO "+param.vratiNazivTabele()+" ("+param.vratiKoloneZaUbacivanje()+") VALUES ("+param.vratiVrednostiZaUbacivanje()+")";
        System.out.println(upit);
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE "+param.vratiNazivTabele()+" SET "+param.vratiVrednostZaIzmenu();
        System.out.println(upit);
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
 
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();

    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {// dopuniiiii
        return null;
    }
    
}
