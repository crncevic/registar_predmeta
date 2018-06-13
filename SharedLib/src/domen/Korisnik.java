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
public class Korisnik implements OpstiDomenskiObjekat, Serializable {

    private int korisnikId;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private Uloga uloga;
    private String status;

    public Korisnik() {
    }

    public Korisnik(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Korisnik(String ime, String prezime, String username, String password, Uloga uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.uloga = uloga;
    }

    public Korisnik(int korisnikId, String ime, String prezime, String username, String password, Uloga uloga) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.uloga = uloga;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "korisnikId=" + korisnikId + ", ime=" + ime + ", prezime=" + prezime + ", username=" + username + ", password=" + password + '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String vratiImeKlase() {
        return "korisnik";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getIme() + "','" + getPrezime() + "','" + getUsername() + "','" + getPassword() + "'";
    }

}
