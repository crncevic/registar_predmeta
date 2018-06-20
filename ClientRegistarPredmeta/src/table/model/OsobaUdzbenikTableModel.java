package table.model;

import domen.OsobaUVeziSaUdzbenikom;
import domen.UlogaUdzbenik;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableRow;
import javax.swing.table.AbstractTableModel;
import kontroler.Kontroler;
import transfer.util.IOperation;

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

    private List<OsobaUVeziSaUdzbenikom> osobe;
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
                return rowIndex + 1;
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
                String ime = ((String) aValue).trim();

                if (ime.length() < 3) {
                    return;
                }

                char[] niz = ime.toCharArray();

                for (char c : niz) {
                    if (!Character.isAlphabetic(c) && c != '-' && c != ' ') {
                        return;
                    }
                }

                autor.setIme(ime);
                break;
            case 2:

                String prezime = ((String) aValue).trim();

                if (prezime.length() < 3) {
                    return;
                }

                char[] niz2 = prezime.toCharArray();

                for (char c : niz2) {
                    if (!Character.isAlphabetic(c) && c != '-' && c != ' ') {
                        return;
                    }
                }
                autor.setPrezime(prezime);
                break;
            case 3:
                String titula = ((String) aValue).trim();

                if (titula.length() < 3) {
                    return;
                }

                char[] niz3 = titula.toCharArray();

                for (char c : niz3) {
                    if (!Character.isAlphabetic(c) && c != '-' && c != ' ') {
                        return;
                    }
                }

                autor.setTitula(titula);
                break;
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

    public void dodajNovuOsobuZaUdzbenik(String uloga) throws Exception {
        OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
        UlogaUdzbenik uu = new UlogaUdzbenik();
        uu.setNaziv(uloga);
        Kontroler.getInstance().posaljiZahtev(IOperation.VRATI_ULOGU_UDZBENIK_PO_NAZIVU, uu);
        UlogaUdzbenik ulogaUdzbenik = (UlogaUdzbenik) Kontroler.getInstance().primiOdgovor();
        ouvsu.setUlogaUdzbenik(ulogaUdzbenik);
        if (osobe.isEmpty()) {

            osobe.add(ouvsu);
            fireTableDataChanged();
            return;
        }

        if (osobe.get(osobe.size() - 1).getIme().trim().length() != 0 || osobe.get(osobe.size() - 1).getPrezime().trim().length() != 0) {
            osobe.add(ouvsu);
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
