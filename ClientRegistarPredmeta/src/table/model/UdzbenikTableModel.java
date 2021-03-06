/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.OsobaUVeziSaUdzbenikom;
import domen.Udzbenik;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.Kontroler;

/**
 *
 * @author Petar
 */
public class UdzbenikTableModel extends AbstractTableModel {

    private List<Udzbenik> udzbenici = new ArrayList<>();
    private String[] columnNames = new String[]{"id", "naziv", "autori", "godina izdanja", "izdavac", "recenzenti", "stampa", "rbr_izdanja", "tiraz", "ISBN"};

    public UdzbenikTableModel(List<Udzbenik> udzbenici) {
        this.udzbenici = udzbenici;
    }

    @Override
    public int getRowCount() {
        return udzbenici.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Udzbenik udzbenik = udzbenici.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return udzbenik.getUdzbenikId();
            case 1:
                return udzbenik.getNaziv();
            case 2:
                String autori = "";
                for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                    if (osoba.getUlogaUdzbenik().getNaziv().equalsIgnoreCase("autor")) {
                        autori += osoba.getIme() + " " + osoba.getPrezime() + " " + osoba.getTitula() + ",";
                    }
                }
                return autori;
            case 3:
                return udzbenik.getGodinaIzdanja();
            case 4:
                return udzbenik.getIzdavac();
            case 5:
                String recenzenti = "";
                for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                    if (osoba.getUlogaUdzbenik().getNaziv().equalsIgnoreCase("recenzent")) {
                        recenzenti += osoba.getIme() + " " + osoba.getPrezime() + " " + osoba.getTitula() + ",";
                    }
                }
                return recenzenti;

            case 6:
                return udzbenik.getStampa();
            case 7:
                return udzbenik.getRbrIzdanja();
            case 8:
                return udzbenik.getTiraz();
            case 9:
                return udzbenik.getIsbn();
            default:
                return "N/A";

        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}
