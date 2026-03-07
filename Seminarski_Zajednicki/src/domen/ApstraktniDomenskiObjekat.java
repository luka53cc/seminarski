/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.*;
/**
 *
 * @author Luka
 */
public interface ApstraktniDomenskiObjekat extends Serializable{
    
    public String vratiNazivTabele();
    
    public String vratiKoloneZaUbacivanje();
    
    public String vratiVrednostiZaUbacivanje();
    
    public String vratiPrimarniKljuc();
    
    public String vratiVrednostZaIzmenu();
    
    public List<ApstraktniDomenskiObjekat> vratiListuIzRs(ResultSet rs) throws Exception;
    
    public ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception;

    

}
