/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Kategorija;
import domen.Polaznik;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class ModelTabelePolaznik extends AbstractTableModel {
private List<Polaznik> sviPolaznici; // Ovo je originalna lista iz baze
    List<Polaznik> lista;
    String[] kolone ={"id","Ime i prezime","JMBG","Datum rodjenja","Kategorija"};

    public ModelTabelePolaznik(List<Polaznik> lista) {
        this.lista = lista;
        this.sviPolaznici = new ArrayList<>(lista); // Kopija za resetovanje
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

    public void pretrazi(String imeP, String jmbg, Date datumRodjenja, Kategorija kat) {
        this.lista = sviPolaznici.stream()
                // filtriraj po datumu
                .filter(p -> datumRodjenja == null || p.getDatumrodjenjaPolaznika().equals(datumRodjenja))
                // filtriraj po imenu
                .filter(p -> imeP == null || imeP.isBlank() || p.getImePrezimePolaznika().toLowerCase().contains(imeP.toLowerCase()))
                // filtriraj po jmbg
                .filter(p -> jmbg == null || jmbg.isBlank() || p.getJmbgPolaznika().contains(jmbg))
                // filtriraj po kategoriji po ID-u
                .filter(p -> kat == null || kat.getIdKategorija() == 0 || 
                             (p.getKategorija() != null && p.getKategorija().getIdKategorija() == kat.getIdKategorija()))
                .collect(Collectors.toList());

        fireTableDataChanged();
    }
    
    
    
}
