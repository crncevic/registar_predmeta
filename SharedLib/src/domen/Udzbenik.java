/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Petar
 */
public class Udzbenik implements OpstiDomenskiObjekat, Serializable {

    private int udzbenikId;
    private String naziv;
    private List<OsobaUVeziSaUdzbenikom> osobeUVeziSaUdzbenikom;
    private int godinaIzdanja;
    private String izdavac;
    private String stampa;
    private int rbrIzdanja;
    private int tiraz;
    private int isbn;

    public Udzbenik() {
    }

    public Udzbenik(String naziv, List<OsobaUVeziSaUdzbenikom> autori, int godinaIzdanja, String izdavac, String stampa, int rbrIzdanja, int tiraz, int isbn) {
        this.naziv = naziv;
        this.osobeUVeziSaUdzbenikom = autori;
        this.godinaIzdanja = godinaIzdanja;
        this.izdavac = izdavac;

        this.stampa = stampa;
        this.rbrIzdanja = rbrIzdanja;
        this.tiraz = tiraz;
        this.isbn = isbn;
    }

    public Udzbenik(int udzbenikId, String naziv, List<OsobaUVeziSaUdzbenikom> autori, int godinaIzdanja, String izdavac, String stampa, int rbrIzdanja, int tiraz, int isbn) {
        this.udzbenikId = udzbenikId;
        this.naziv = naziv;
        this.osobeUVeziSaUdzbenikom = autori;
        this.godinaIzdanja = godinaIzdanja;
        this.izdavac = izdavac;

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

    public List<OsobaUVeziSaUdzbenikom> getOsobeUVeziSaUdzbenikom() {
        return osobeUVeziSaUdzbenikom;
    }

    public void setOsobeUVeziSaUdzbenikom(List<OsobaUVeziSaUdzbenikom> osobeUVeziSaUdzbenikom) {
        this.osobeUVeziSaUdzbenikom = osobeUVeziSaUdzbenikom;
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
        return "Udzbenik{" + "udzbenikId=" + udzbenikId + ", naziv=" + naziv + ", osobeUVeziSaUdzbenikom=" + osobeUVeziSaUdzbenikom + ", godinaIzdanja=" + godinaIzdanja + ", izdavac=" + izdavac + ", stampa=" + stampa + ", rbrIzdanja=" + rbrIzdanja + ", tiraz=" + tiraz + ", isbn=" + isbn + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "udzbenik";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'," + getGodinaIzdanja() + ",'" + getIzdavac() + "','" + getStampa() + "'," + getRbrIzdanja() + "," + getTiraz() + "," + getIsbn();
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "udzbenikId=" + getUdzbenikId();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String postaviVrednostAtributa() {
        return "naziv='" + getNaziv() + "',godina_izdanja=" + getGodinaIzdanja() + ",izdavac='" + getIzdavac() + "',stampa='" + getStampa() + "',rbr_izdanja=" + getRbrIzdanja() + ",tiraz=" + getTiraz() + ",isbn=" + getIsbn();
    }

    @Override
    public OpstiDomenskiObjekat napraviDomenskiObjekat(ResultSet rs) throws Exception {
        return new Udzbenik(rs.getInt("udzbenikId"), rs.getString("naziv"), null, rs.getInt("godina_izdanja"), rs.getString("izdavac"), rs.getString("stampa"), rs.getInt("rbr_izdanja"), rs.getInt("tiraz"), rs.getInt("isbn"));
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "naziv,godina_izdanja,izdavac,stampa,rbr_izdanja,tiraz,isbn";
    }

}
