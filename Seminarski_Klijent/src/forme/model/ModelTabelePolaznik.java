/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Polaznik;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class ModelTabelePolaznik extends AbstractTableModel {

    List<Polaznik> lista;
    String[] kolone ={"id","Ime i prezime","JMBG","Datum rodjenja","Kategorija"};

    public ModelTabelePolaznik(List<Polaznik> lista) {
        this.lista = lista;
    }
    
    
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    public List<Polaznik> getLista() {
        return lista;
    }

    public void setLista(List<Polaznik> lista) {
        this.lista = lista;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Polaznik p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getIdPolaznik();
            case 1:
                return p.getImePrezimePolaznika();
            case 2:
                return p.getJmbgPolaznika();
            case 3:
                SimpleDateFormat formatZaBazu = new SimpleDateFormat("yyyy-MM-dd");
                String datum = formatZaBazu.format(p.getDatumrodjenjaPolaznika());
                return datum;
            case 4:
                return p.getKategorija().getNazivKategorije();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
}
