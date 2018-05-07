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
public class Recenzent implements OpstiDomenskiObjekat {

    private int recenzentId;
    private int udzbenikId;
    private String ime;
    private String prezime;
    private String titula;

    public Recenzent() {
    }

    public Recenzent(int recenzentId, int udzbenikId, String ime, String prezime, String titula) {
        this.recenzentId = recenzentId;
        this.ime = ime;
        this.prezime = prezime;
        this.titula = titula;
        this.udzbenikId = udzbenikId;
    }

    public int getRecenzentId() {
        return recenzentId;
    }

    public void setRecenzentId(int recenzentId) {
        this.recenzentId = recenzentId;
    }

    public int getUdzbenikId() {
        return udzbenikId;
    }

    public void setUdzbenikId(int udzbenikId) {
        this.udzbenikId = udzbenikId;
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

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }

    @Override
    public String toString() {
        return "Recenzent{" + "recenzentId=" + recenzentId + ", ime=" + ime + ", prezime=" + prezime + ", titula=" + titula + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "recenzent";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "" + getUdzbenikId() + ",'" + getIme() + "','" + getPrezime() + "','" + getTitula() + "'";
    }

}
