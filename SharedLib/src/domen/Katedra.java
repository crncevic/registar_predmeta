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
public class Katedra implements OpstiDomenskiObjekat,Serializable {

    private int katedraId;
    private String naziv;
    private Nastavnik sef;
    private Nastavnik zamenikSefa;
    private Nastavnik sekretar;

    public Katedra() {
    }

    public Katedra(int katedraId, String naziv, Nastavnik sef, Nastavnik zamenikSefa, Nastavnik sekretar) {
        this.katedraId = katedraId;
        this.naziv = naziv;
        this.sef = sef;
        this.zamenikSefa = zamenikSefa;
        this.sekretar = sekretar;
    }

    public int getKatedraId() {
        return katedraId;
    }

    public void setKatedraId(int katedraId) {
        this.katedraId = katedraId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Nastavnik getSef() {
        return sef;
    }

    public void setSef(Nastavnik sef) {
        this.sef = sef;
    }

    public Nastavnik getZamenikSefa() {
        return zamenikSefa;
    }

    public void setZamenikSefa(Nastavnik zamenikSefa) {
        this.zamenikSefa = zamenikSefa;
    }

    public Nastavnik getSekretar() {
        return sekretar;
    }

    public void setSekretar(Nastavnik sekretar) {
        this.sekretar = sekretar;
    }

    @Override
    public String toString() {
        return "Katedra{" + "katedraId=" + katedraId + ", naziv=" + naziv + ", sef=" + sef + ", zamenikSefa=" + zamenikSefa + ", sekretar=" + sekretar + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "katedra";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'," + getSef().getNastavnikId() + "," + getZamenikSefa().getNastavnikId() + "," + getSekretar().getNastavnikId();
    }

}
