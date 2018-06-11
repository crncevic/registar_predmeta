/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.model;

import domen.Predmet;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class PredmetTableModel extends AbstractTableModel {

    private List<Predmet> predmeti;
    private String[] columnNames = new String[]{
        "id", "naziv", "br. casova predavanja nedeljno", "br. casova vezbi nedeljno", "ostali casovi", "drugi oblici nastave",
        "studijski istrazivacki rad", "cilj", "ishod", "uslov", "vrsta i nivo studija", "sadrzaj tekst"
    };

    public PredmetTableModel(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    @Override
    public int getRowCount() {
        return predmeti.size();
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Predmet predmet = predmeti.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return predmet.getPredmetId();
            case 1:
                return predmet.getNaziv();
            case 2:
                return predmet.getBrCasovaPredavanjaNedeljno();
            case 3:
                return predmet.getBrCasovaVezbiNedeljno();
            case 4:
                return predmet.getOstaliCasovi();
            case 5:
                return predmet.getDrugiObliciNastave();
            case 6:
                return predmet.getStudijskiIstrazivackiRad();
            case 7:
                return predmet.getCilj();
            case 8:
                return predmet.getIshod();
            case 9:
                return predmet.getUslov();
            case 10:
                return predmet.getVrstaINivoStudija().getNaziv();
            case 11:
                return predmet.getSadrzajTekst();
            default:
                return "N/A";

        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}
