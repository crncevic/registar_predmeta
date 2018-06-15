/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class StudijskiProgram implements OpstiDomenskiObjekat, Serializable {

    private int studijskiProgramId;
    private String naziv;

    public StudijskiProgram() {
    }

    public StudijskiProgram(int studijskiProgramId, String naziv) {
        this.studijskiProgramId = studijskiProgramId;
        this.naziv = naziv;
    }

    public int getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(int studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "StudijskiProgram{" + "studijskiProgramId=" + studijskiProgramId + ", naziv=" + naziv + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "studijski_program";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudijskiProgram other = (StudijskiProgram) obj;
        if (this.studijskiProgramId != other.studijskiProgramId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "studijskiProgramId=" + getStudijskiProgramId();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String postaviVrednostAtributa() {
        return "naziv='" + getNaziv() + "'";
    }

    @Override
    public OpstiDomenskiObjekat napraviDomenskiObjekat(ResultSet rs) throws Exception {
        return new StudijskiProgram(rs.getInt("studijskiProgramId"), rs.getString("naziv"));
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "naziv";
    }

}
