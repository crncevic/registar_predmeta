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
public class Obaveza implements OpstiDomenskiObjekat,Serializable {

    private int obavezaId;
    private int predmetId;
    private String tip;
    private String naziv;
    private int brojPoena;

    public Obaveza() {
    }

    public Obaveza(int predmetId, String tip, String naziv, int brojPoena) {
        this.predmetId = predmetId;
        this.tip = tip;
        this.naziv = naziv;
        this.brojPoena = brojPoena;
    }

    public Obaveza(int obavezaId, int predmetId, String tip, String naziv, int brojPoena) {
        this.obavezaId = obavezaId;
        this.predmetId = predmetId;
        this.tip = tip;
        this.naziv = naziv;
        this.brojPoena = brojPoena;
    }

    public int getObavezaId() {
        return obavezaId;
    }

    public void setObavezaId(int obavezaId) {
        this.obavezaId = obavezaId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(int brojPoena) {
        this.brojPoena = brojPoena;
    }

    @Override
    public String toString() {
        return "Obaveza{" + "obavezaId=" + obavezaId + ", predmetId=" + predmetId + ", tip=" + tip + ", naziv=" + naziv + ", brojPoena=" + brojPoena + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "obaveza";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "" + getPredmetId() + ",'" + getTip() + "','" + getNaziv() + "'," + getBrojPoena();
    }

}
