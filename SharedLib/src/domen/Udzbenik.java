/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.List;

/**
 *
 * @author Petar
 */
public class Udzbenik implements OpstiDomenskiObjekat {

    private int udzbenikId;
    private String naziv;
    private List<Autor> autori;
    private int godinaIzdanja;
    private String izdavac;
    private List<Recenzent> recenzenti;
    private String stampa;
    private int rbrIzdanja;
    private int tiraz;
    private int isbn;

    public Udzbenik() {
    }

    public Udzbenik(String naziv, List<Autor> autori, int godinaIzdanja, String izdavac, List<Recenzent> recenzenti, String stampa, int rbrIzdanja, int tiraz, int isbn) {
        this.naziv = naziv;
        this.autori = autori;
        this.godinaIzdanja = godinaIzdanja;
        this.izdavac = izdavac;
        this.recenzenti = recenzenti;
        this.stampa = stampa;
        this.rbrIzdanja = rbrIzdanja;
        this.tiraz = tiraz;
        this.isbn = isbn;
    }

    public Udzbenik(int udzbenikId, String naziv, List<Autor> autori, int godinaIzdanja, String izdavac, List<Recenzent> recenzenti, String stampa, int rbrIzdanja, int tiraz, int isbn) {
        this.udzbenikId = udzbenikId;
        this.naziv = naziv;
        this.autori = autori;
        this.godinaIzdanja = godinaIzdanja;
        this.izdavac = izdavac;
        this.recenzenti = recenzenti;
        this.stampa = stampa;
        this.rbrIzdanja = rbrIzdanja;
        this.tiraz = tiraz;
        this.isbn = isbn;
    }

    public int getUdzbenikId() {
        return udzbenikId;
    }

    public void setUdzbenikId(int udzbenikId) {
        this.udzbenikId = udzbenikId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Autor> getAutori() {
        return autori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public List<Recenzent> getRecenzenti() {
        return recenzenti;
    }

    public void setRecenzenti(List<Recenzent> recenzenti) {
        this.recenzenti = recenzenti;
    }

    public String getStampa() {
        return stampa;
    }

    public void setStampa(String stampa) {
        this.stampa = stampa;
    }

    public int getRbrIzdanja() {
        return rbrIzdanja;
    }

    public void setRbrIzdanja(int rbrIzdanja) {
        this.rbrIzdanja = rbrIzdanja;
    }

    public int getTiraz() {
        return tiraz;
    }

    public void setTiraz(int tiraz) {
        this.tiraz = tiraz;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Udzbenik{" + "udzbenikId=" + udzbenikId + ", naziv=" + naziv + ", autori=" + autori + ", godinaIzdanja=" + godinaIzdanja + ", izdavac=" + izdavac + ", recenzenti=" + recenzenti + ", stampa=" + stampa + ", rbrIzdanja=" + rbrIzdanja + ", tiraz=" + tiraz + ", isbn=" + isbn + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "udzbenik";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'," + getGodinaIzdanja() + ",'" + getIzdavac() + "','" + getStampa() + "'," + getRbrIzdanja() + "," + getTiraz() + "," + getIsbn();
    }

}
