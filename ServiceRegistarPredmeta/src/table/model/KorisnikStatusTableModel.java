/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.Korisnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import session.Session;

/**
 *
 * @author Petar
 */
public class KorisnikStatusTableModel extends AbstractTableModel {

    private List<Korisnik> korisnici;
    private String[] columnNames = new String[]{"rb", "username", "status"};

    public KorisnikStatusTableModel(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    @Override
    public int getRowCount() {
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik korisnik = korisnici.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return korisnik.getUsername();
            case 2:
                return korisnik.getStatus();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void proveriStanje() {
        for (Korisnik korisnik : korisnici) {
            if (Session.getInstance().getMap().containsKey(korisnik.getUsername())) {
                korisnik.setStatus("aktivan");
            } else {
                korisnik.setStatus("nije aktivan");
            }
        }

        fireTableDataChanged();
    }

}
