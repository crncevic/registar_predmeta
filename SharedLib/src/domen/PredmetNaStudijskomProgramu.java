/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author Petar
 */
public class PredmetNaStudijskomProgramu implements OpstiDomenskiObjekat {

    private int predmetId;
    private int studijskiProgramId;
    private String status;
    private int espb;

    public PredmetNaStudijskomProgramu() {
    }

    public PredmetNaStudijskomProgramu(int predmetId, int studijskiProgramId, String status, int espb) {
        this.predmetId = predmetId;
        this.studijskiProgramId = studijskiProgramId;
        this.status = status;
        this.espb = espb;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public int getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(int studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    @Override
    public String toString() {
        return "PredmetNaStudijskomProgramu{" + "predmetId=" + predmetId + ", studijskiProgramId=" + studijskiProgramId + ", status=" + status + ", espb=" + espb + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "predmet_na_studijskom_programu";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return getPredmetId() + "," + getStudijskiProgramId() + ",'" + getStatus() + "'," + getEspb();
    }

}
