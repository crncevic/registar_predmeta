package table.model;

import domen.OsobaUVeziSaUdzbenikom;

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
public class OsobaUdzbenikTableModel extends AbstractTableModel {

    private List<OsobaUVeziSaUdzbenikom> osobe = new ArrayList<>();
    private String[] columnNames = new String[]{"rb", "Ime", "Prezime", "Titula"};

    public OsobaUdzbenikTableModel(List<OsobaUVeziSaUdzbenikom> list) {
        osobe = list;
    }

    @Override
    public int getRowCount() {
        return osobe != null ? osobe.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OsobaUVeziSaUdzbenikom autor = osobe.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex;
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
        OsobaUVeziSaUdzbenikom autor = osobe.get(rowIndex);

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

    public void obrisiAutora(int selectedIndex) {
        osobe.remove(selectedIndex);
        fireTableDataChanged();

    }

    public void postaviRecenzente(List<OsobaUVeziSaUdzbenikom> autoriNovi) {
        osobe.clear();
        for (OsobaUVeziSaUdzbenikom autor : autoriNovi) {
            osobe.add(autor);
        }
        fireTableDataChanged();
    }

    public List<OsobaUVeziSaUdzbenikom> vratiSveAutore() {
        List<OsobaUVeziSaUdzbenikom> autori = new ArrayList<>();
        for (OsobaUVeziSaUdzbenikom ouvsu : osobe) {
            if (ouvsu.getUlogaUdzbenik().getNaziv().equalsIgnoreCase("autor")) {
                autori.add(ouvsu);
            }
        }

        return autori;
    }

    public List<OsobaUVeziSaUdzbenikom> vratiSveRecenzente() {
        List<OsobaUVeziSaUdzbenikom> recenzenti = new ArrayList<>();
        for (OsobaUVeziSaUdzbenikom ouvsu : osobe) {
            if (ouvsu.getUlogaUdzbenik().getNaziv().equalsIgnoreCase("recenzent")) {
                recenzenti.add(ouvsu);
            }
        }

        return recenzenti;
    }

    public void dodajNovuOsobuZaUdzbenik() {
        if (osobe.isEmpty()) {
            osobe.add(new OsobaUVeziSaUdzbenikom());
            fireTableDataChanged();
            return;
        }

        if (osobe.get(osobe.size() - 1).getIme().trim().length() != 0 || osobe.get(osobe.size() - 1).getPrezime().trim().length() != 0) {
            osobe.add(new OsobaUVeziSaUdzbenikom());
            fireTableDataChanged();
        }
    }

    public void obrisiOsobuNaUdzbeniku(int selectedIndex) {
        osobe.remove(selectedIndex);
        fireTableDataChanged();
    }

    public void obrisiSveOsobeNaUdzbeniku() {
        osobe.clear();
        fireTableDataChanged();
    }

}
