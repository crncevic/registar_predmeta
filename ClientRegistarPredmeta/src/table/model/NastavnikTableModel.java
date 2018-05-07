/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.Nastavnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class NastavnikTableModel extends AbstractTableModel {

    private List<Nastavnik> nastavnici;
    private String [] columnNames = new String[]{"id","ime","prezime","zvanje","kabinet","e-posta","telefon"}; 

    public NastavnikTableModel(List<Nastavnik> nastavnici) {
        this.nastavnici = nastavnici;
    }

    @Override
    public int getRowCount() {
        return nastavnici != null ? nastavnici.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Nastavnik nastavnik = nastavnici.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return nastavnik.getNastavnikId();
            case 1:
                return nastavnik.getIme();
            case 2:
                return nastavnik.getPrezime();
            case 3:
                return nastavnik.getZvanje();
            case 4:
                return nastavnik.getKabinet();
            case 5:
                return nastavnik.getePosta();
            case 6:
                return nastavnik.getTelefon();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
       return columnNames[column];
    }

}
