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
public class StavkaNastave implements OpstiDomenskiObjekat,Serializable {

    private int tipNastaveId;
    private int predmetId;
    private int stavkaNastaveId;
    private int rbrNedelje;

    public StavkaNastave() {
    }

    public StavkaNastave(int tipNastaveId, int predmetId, int stavkaNastaveId, int rbrNedelje) {
        this.tipNastaveId = tipNastaveId;
        this.predmetId = predmetId;
        this.stavkaNastaveId = stavkaNastaveId;
        this.rbrNedelje = rbrNedelje;
    }

    public int getTipNastaveId() {
        return tipNastaveId;
    }

    public void setTipNastaveId(int tipNastaveId) {
        this.tipNastaveId = tipNastaveId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public int getStavkaNastaveId() {
        return stavkaNastaveId;
    }

    public void setStavkaNastaveId(int stavkaNastaveId) {
        this.stavkaNastaveId = stavkaNastaveId;
    }

    public int getRbrNedelje() {
        return rbrNedelje;
    }

    public void setRbrNedelje(int rbrNedelje) {
        this.rbrNedelje = rbrNedelje;
    }

    @Override
    public String toString() {
        return "StavkaNastave{" + "tipNastaveId=" + tipNastaveId + ", predmetId=" + predmetId + ", stavkaNastaveId=" + stavkaNastaveId + ", rbrNedelje=" + rbrNedelje + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "stavka_nastave";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return getPredmetId() + "," + getTipNastaveId() + "," + getRbrNedelje();
    }

}
