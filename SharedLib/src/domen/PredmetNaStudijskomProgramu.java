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
public class PredmetNaStudijskomProgramu implements OpstiDomenskiObjekat, Serializable {

    private Predmet predmet;
    private StudijskiProgram studijskiProgram;
    private Status status;
    private int espb;

    public PredmetNaStudijskomProgramu() {
    }

    public PredmetNaStudijskomProgramu(Predmet predmet, StudijskiProgram studijskiProgram, Status status, int espb) {
        this.predmet = predmet;
        this.studijskiProgram = studijskiProgram;
        this.status = status;
        this.espb = espb;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    @Override
    public String vratiImeKlase() {
        return "predmet_na_studijskom_programu";
    }

    @Override
    public String vratiVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
