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
public class TematskaCelina implements OpstiDomenskiObjekat, Serializable {
    
    private int tematskaCelinaId;
    private int tipNastaveId;
    private int predmetId;
    
    private TematskaCelina nadredjenaTematskaCelina;
    private String naziv;
    private String opis;

    public TematskaCelina() {
    }

    public TematskaCelina(int tipNastaveId, int predmetId, int tematskaCelinaId, TematskaCelina nadredjenaTematskaCelina, String naziv, String opis) {
        this.tipNastaveId = tipNastaveId;
        this.predmetId = predmetId;
        this.tematskaCelinaId = tematskaCelinaId;
        this.nadredjenaTematskaCelina = nadredjenaTematskaCelina;
        this.naziv = naziv;
        this.opis = opis;
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

    public TematskaCelina getNadredjenaTematskaCelina() {
        return nadredjenaTematskaCelina;
    }

    public void setNadredjenaTematskaCelina(TematskaCelina nadredjenaTematskaCelina) {
        this.nadredjenaTematskaCelina = nadredjenaTematskaCelina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "TematskaCelina{" + "tipNastaveId=" + tipNastaveId + ", predmetId=" + predmetId + ",  tematskaCelinaId=" + tematskaCelinaId + ", nadredjenaTematskaJedinicaId=" + nadredjenaTematskaCelina + ", naziv=" + naziv + ", opis=" + opis + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "tematska_celina";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return getTematskaCelinaId()+","+ getTipNastaveId() + "," + getPredmetId() + ","+getNadredjenaTematskaCelina().getTematskaCelinaId()+"'" + getNaziv() + "','"+getOpis()+"'";
    }

}
