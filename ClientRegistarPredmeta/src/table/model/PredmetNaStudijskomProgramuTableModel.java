/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.PredmetNaStudijskomProgramu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class PredmetNaStudijskomProgramuTableModel extends AbstractTableModel {

    private List<PredmetNaStudijskomProgramu> predmetiNaStudijskomProgramu;
    private String[] columnNames = new String[]{"rb", "naziv", "status", "espb"};

    public PredmetNaStudijskomProgramuTableModel(List<PredmetNaStudijskomProgramu> predmetiNaStudijskomProgramu) {
        this.predmetiNaStudijskomProgramu = predmetiNaStudijskomProgramu;
    }

    @Override
    public int getRowCount() {
        return predmetiNaStudijskomProgramu.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PredmetNaStudijskomProgramu pnsp = predmetiNaStudijskomProgramu.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return pnsp.getPredmet().getNaziv();
            case 2:
                return pnsp.getStatus().getNaziv();
            case 3:
                return pnsp.getEspb();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public List<PredmetNaStudijskomProgramu> getPredmetiNaStudijskomProgramu() {
        return predmetiNaStudijskomProgramu;
    }

    public void setPredmetiNaStudijskomProgramu(List<PredmetNaStudijskomProgramu> predmetiNaStudijskomProgramu) {
        this.predmetiNaStudijskomProgramu = predmetiNaStudijskomProgramu;
        fireTableDataChanged();
    }

}
