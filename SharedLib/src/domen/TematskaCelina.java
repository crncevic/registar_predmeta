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
public class TematskaCelina implements OpstiDomenskiObjekat {

    private int tipNastaveId;
    private int predmetId;
    private int stavkaNastaveId;
    private int tematskaCelinaId;
    private String naziv;

    public TematskaCelina() {
    }

    public TematskaCelina(int tipNastaveId, int predmetId, int stavkaNastaveId, int tematskaCelinaId, String naziv) {
        this.tipNastaveId = tipNastaveId;
        this.predmetId = predmetId;
        this.stavkaNastaveId = stavkaNastaveId;
        this.tematskaCelinaId = tematskaCelinaId;
        this.naziv = naziv;
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

    public int getTematskaCelinaId() {
        return tematskaCelinaId;
    }

    public void setTematskaCelinaId(int tematskaCelinaId) {
        this.tematskaCelinaId = tematskaCelinaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "TematskaCelina{" + "tipNastaveId=" + tipNastaveId + ", predmetId=" + predmetId + ", stavkaNastaveId=" + stavkaNastaveId + ", tematskaCelinaId=" + tematskaCelinaId + ", naziv=" + naziv + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "tematska_celina";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return getTipNastaveId() + "," + getPredmetId() + "," + getStavkaNastaveId() + ",'" + getNaziv() + "'";
    }

}
