/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Zapisnik;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class ModelTabeleZapisnici extends AbstractTableModel{
    
    private List<Zapisnik> sviZapisnici;
    List<Zapisnik> lista = new ArrayList<>();
    String[] kolone = {"id","datum evidentiranja","tekst","ukupno trajanje","instruktor","polaznik"};

    public ModelTabeleZapisnici(List<Zapisnik> lista) {
        this.lista = lista;
        this.sviZapisnici = new ArrayList<>(lista);
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    public List<Zapisnik> getLista() {
        return lista;
    }

    public void setLista(List<Zapisnik> lista) {
        this.lista = lista;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zapisnik z = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getIdZapisnik();
            case 1:
                return z.getDatumEvidentiranja();
            case 2:
                return z.getTekst();
            case 3:
                return z.getUkupnoTrajanje()+"";
            case 4:
                return z.getInstruktor().getImePrezimeInstruktora();
            case 5:
                return z.getPolaznik().getImePrezimePolaznika();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String tekst, int trajanje, Date datumEvidentiranja) {
        this.lista = sviZapisnici.stream()
                .filter(z -> datumEvidentiranja == null || z.getDatumEvidentiranja().equals(datumEvidentiranja))
                .filter(z -> tekst == null || tekst.isBlank() || 
                        (z.getTekst() != null && z.getTekst().toLowerCase().contains(tekst.toLowerCase())))
                .filter(z -> trajanje <= 0 || z.getUkupnoTrajanje() == trajanje)
                .collect(Collectors.toList());

        fireTableDataChanged();    
    }

    
    
}
