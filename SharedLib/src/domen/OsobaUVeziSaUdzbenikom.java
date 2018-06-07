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
public class Autor implements OpstiDomenskiObjekat , Serializable{

    private int autorId;
    private int udzbenikId;
    private String ime;
    private String prezime;
    private String titula;

    public Autor() {
    }

    public Autor(int id, int udzbenikId, String ime, String prezime, String titula) {
        this.autorId = id;
        this.ime = ime;
        this.prezime = prezime;
        this.titula = titula;
        this.udzbenikId = udzbenikId;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getUdzbenikId() {
        return udzbenikId;
    }

    public void setUdzbenikId(int udzbenikId) {
        this.udzbenikId = udzbenikId;
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
        return "Autor{" + "id=" + autorId + ", ime=" + ime + ", prezime=" + prezime + ", titula=" + titula + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "autor";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "" + getUdzbenikId() + ",'" + getIme() + "','" + getPrezime() + "','" + getTitula() + "'";
    }

}
