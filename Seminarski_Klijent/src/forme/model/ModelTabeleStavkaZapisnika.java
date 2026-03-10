/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaZapisnika;
import domen.Zapisnik;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class ModelTabeleStavkaZapisnika extends AbstractTableModel {
    List<StavkaZapisnika> lista = new ArrayList<>();
    String[] kolone = {"rd","tekst","trajanje","Usluga"};

    public ModelTabeleStavkaZapisnika(List<StavkaZapisnika> lista) {
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

    public List<StavkaZapisnika> getLista() {
        return lista;
    }

    public void setLista(List<StavkaZapisnika> lista) {
        this.lista = lista;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaZapisnika z = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getRb()+"";
            case 1:
                return z.getTekst();
            case 2:
                return z.getTrajanjeStavke()+"";
            case 3:
                return z.getUsluga().getIdUsluga();

            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }}
