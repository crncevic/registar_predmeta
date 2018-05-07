package table.model;

import domen.Autor;
import domen.Recenzent;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableRow;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Petar
 */
public class AutorTableModel extends AbstractTableModel {

    private List<Autor> autori = new ArrayList<>();
    private String[] columnNames = new String[]{"rb", "Ime", "Prezime", "Titula"};

    public AutorTableModel(List<Autor> list) {
        autori = list;
    }

    @Override
    public int getRowCount() {
        return autori != null ? autori.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Autor autor = autori.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return autor.getAutorId();
            case 1:
                return autor.getIme();
            case 2:
                return autor.getPrezime();
            case 3:
                return autor.getTitula();
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
        Autor autor = autori.get(rowIndex);

        switch (columnIndex) {
            case 1:
                autor.setIme((String) aValue);
            case 2:
                autor.setPrezime((String) aValue);
            case 3:
                autor.setTitula((String) aValue);
            default:
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 1 || columnIndex == 2 || columnIndex == 3);
    }

    public void dodajNovogAutora() {
        if (autori.isEmpty()) {
            autori.add(new Autor());
            fireTableDataChanged();
            return;
        }

        if (autori.get(autori.size() - 1).getIme().trim().length() != 0 || autori.get(autori.size() - 1).getPrezime().trim().length() != 0) {
            autori.add(new Autor());
            fireTableDataChanged();
        }
    }

    public void obrisiAutora(int selectedIndex) {
        autori.remove(selectedIndex);
        postaviRedneBrojeve();
        fireTableDataChanged();

    }

    private void postaviRedneBrojeve() {
        for (int i = 1; i <= autori.size(); i++) {
            autori.get(i - 1).setAutorId(i);
        }

    }

    public void postaviRecenzente(List<Autor> autoriNovi) {
        autori.clear();
        for (Autor autor : autoriNovi) {
            autori.add(autor);
        }
        postaviRedneBrojeve();
        fireTableDataChanged();
    }

    public List<Autor> vratiSveAutore() {
        return autori;
    }

    public void obrisiSveAutore() {
        autori.clear();
        fireTableDataChanged();
    }

}
