/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.Udzbenik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class UdzbenikSkraceniTableModel extends AbstractTableModel {

    private List<Udzbenik> udzbenici;
    private String[] columnNames = new String[]{"rb", "naziv"};

    public UdzbenikSkraceniTableModel(List<Udzbenik> udzbenici) {
        this.udzbenici = udzbenici;
    }

    @Override
    public int getRowCount() {
        return udzbenici.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Udzbenik udzbenik = udzbenici.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return udzbenik.getNaziv();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void dodajUdzbenik(Udzbenik udzbenik) {
        for (Udzbenik u : udzbenici) {
            if (u.getNaziv().equalsIgnoreCase(udzbenik.getNaziv())) {
                return;
            }
        }

        udzbenici.add(udzbenik);
        fireTableDataChanged();
    }

    public void obrisiNastavnikaSaPredmeta(int selektovaniRed) {
        udzbenici.remove(selektovaniRed);
        fireTableDataChanged();
    }

    public List<Udzbenik> getUdzbenici() {
        return udzbenici;
    }

}
