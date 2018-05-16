/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

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

}
