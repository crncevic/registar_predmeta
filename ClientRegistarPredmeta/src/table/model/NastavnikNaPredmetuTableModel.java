/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.Nastavnik;
import domen.NastavnikNaPredmetu;
import domen.TipNastave;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class NastavnikNaPredmetuTableModel extends AbstractTableModel {

    public List<NastavnikNaPredmetu> nastavniciNaPredmetu;
    public String[] columnNames = new String[]{"nastavnik", "tip nastave"};

    public NastavnikNaPredmetuTableModel(List<NastavnikNaPredmetu> nastavniciNaPredmetu) {
        this.nastavniciNaPredmetu = nastavniciNaPredmetu;
    }

    @Override
    public int getRowCount() {
        return nastavniciNaPredmetu.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NastavnikNaPredmetu nnp = nastavniciNaPredmetu.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return nnp.getNastavnik().getIme() + " " + nnp.getNastavnik().getPrezime() + " " + nnp.getNastavnik().getZvanje();
            case 1:
                return nnp.getTipNastave().getNaziv();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void dodajNastavnikaNaPredmet(Nastavnik nastavnik, TipNastave tipNastave) {
        for (NastavnikNaPredmetu nastavnikNaPredmetu : nastavniciNaPredmetu) {
            if (nastavnikNaPredmetu.getNastavnik().equals(nastavnik)
                    && nastavnikNaPredmetu.getTipNastave().getNaziv().equalsIgnoreCase(tipNastave.getNaziv())) {
                return;
            }
        }

        nastavniciNaPredmetu.add(new NastavnikNaPredmetu(null, nastavnik, tipNastave));
        fireTableDataChanged();

    }

    public void obrisiNastavnikaSaPredmeta(int selektovaniRed) {
        nastavniciNaPredmetu.remove(selektovaniRed);
        fireTableDataChanged();
    }

    public List<NastavnikNaPredmetu> getNastavniciNaPredmetu() {
        return nastavniciNaPredmetu;
    }

}
