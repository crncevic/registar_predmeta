/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.Autor;
import domen.Recenzent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class RecenzentTableModel extends AbstractTableModel {

    private List<Recenzent> recenzenti = new ArrayList<>();
    private String[] columnNames = new String[]{"rb", "Ime", "Prezime", "Titula"};

    public RecenzentTableModel(List<Recenzent> list) {
        recenzenti = list;
    }

    @Override
    public int getRowCount() {
        return recenzenti != null ? recenzenti.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recenzent recenzent = recenzenti.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return recenzent.getRecenzentId();
            case 1:
                return recenzent.getIme();
            case 2:
                return recenzent.getPrezime();
            case 3:
                return recenzent.getTitula();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Recenzent recenzent = recenzenti.get(rowIndex);

        switch (columnIndex) {
            case 1:
                recenzent.setIme((String) aValue);
            case 2:
                recenzent.setPrezime((String) aValue);
            case 3:
                recenzent.setTitula((String) aValue);
            default:
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
         return (columnIndex == 1 || columnIndex == 2 || columnIndex == 3);
    }

    public void dodajNovogRecenzenta() {
        if (recenzenti.isEmpty()) {
            recenzenti.add(new Recenzent());
            fireTableDataChanged();
            return;
        }

        if (recenzenti.get(recenzenti.size() - 1).getIme().trim().length() != 0 || recenzenti.get(recenzenti.size() - 1).getPrezime().trim().length() != 0) {
            recenzenti.add(new Recenzent());
            fireTableDataChanged();
        }
    }

    public void obrisiRecenzenta(int selectedIndex) {
        recenzenti.remove(selectedIndex);
        postaviRedneBrojeve();
        fireTableDataChanged();

    }

    private void postaviRedneBrojeve() {
        for (int i = 1; i <= recenzenti.size(); i++) {
            recenzenti.get(i - 1).setRecenzentId(i);
        }

    }

    public void postaviRecenzente(List<Recenzent> recenzentiNovi) {
        recenzenti.clear();
        for (Recenzent recenzent : recenzentiNovi) {
            recenzenti.add(recenzent);
        }
        postaviRedneBrojeve();
        fireTableDataChanged();
    }

    public void obrisiSveRecenzente() {
        recenzenti.clear();
        fireTableDataChanged();
    }

    public List<Recenzent> vratiSveRecenzente() {
        return recenzenti;
    }

}
