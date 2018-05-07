/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.Obaveza;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class ObavezaTableModel extends AbstractTableModel {

    private List<Obaveza> obaveze;
    private String[] columnNames = new String[]{"id", "naziv", "tip", "broj poena"};

    public ObavezaTableModel(List<Obaveza> obaveze) {
        this.obaveze = obaveze;
    }

    @Override
    public int getRowCount() {
        return obaveze != null ? obaveze.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Obaveza obaveza = obaveze.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return obaveza.getObavezaId();
            case 1:
                return obaveza.getNaziv();
            case 2:
                return obaveza.getTip();
            case 3:
                return obaveza.getBrojPoena();

            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}
