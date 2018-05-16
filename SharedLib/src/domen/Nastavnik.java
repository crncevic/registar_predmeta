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
public class Nastavnik implements OpstiDomenskiObjekat,Serializable {

    private int nastavnikId;
    private String ime;
    private String prezime;
    private String zvanje;
    private String telefon;
    private String kabinet;
    private String ePosta;

    public Nastavnik() {
    }

    public Nastavnik(int nastavnikId, String ime, String prezime, String zvanje, String telefon, String kabinet, String ePosta) {
        this.nastavnikId = nastavnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
        this.telefon = telefon;
        this.kabinet = kabinet;
        this.ePosta = ePosta;
    }

    public int getNastavnikId() {
        return nastavnikId;
    }

    public void setNastavnikId(int nastavnikId) {
        this.nastavnikId = nastavnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKabinet() {
        return kabinet;
    }

    public void setKabinet(String kabinet) {
        this.kabinet = kabinet;
    }

    public String getePosta() {
        return ePosta;
    }

    public void setePosta(String ePosta) {
        this.ePosta = ePosta;
    }

    @Override
    public String toString() {
        return "Nastavnik{" + "nastavnikId=" + nastavnikId + ", ime=" + ime + ", prezime=" + prezime + ", zvanje=" + zvanje + ", telefon=" + telefon + ", kabinet=" + kabinet + ", ePosta=" + ePosta + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "nastavnik";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getIme() + "','" + getPrezime() + ",'" + getZvanje() + "','" + getTelefon() + "','" + getKabinet() + "','" + getePosta() + "'";
    }

}
