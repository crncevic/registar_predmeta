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
public class TipNastave implements OpstiDomenskiObjekat {

    private int tipNastaveId;
    private String naziv;

    public TipNastave() {
    }

    public TipNastave(String naziv) {
        this.naziv = naziv;
    }

    public TipNastave(int tipNastaveId, String naziv) {
        this.tipNastaveId = tipNastaveId;
        this.naziv = naziv;
    }

    public int getTipNastaveId() {
        return tipNastaveId;
    }

    public void setTipNastaveId(int tipNastaveId) {
        this.tipNastaveId = tipNastaveId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "TipNastave{" + "tipNastaveId=" + tipNastaveId + ", naziv=" + naziv + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "tip_nastave";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'";
    }

}
