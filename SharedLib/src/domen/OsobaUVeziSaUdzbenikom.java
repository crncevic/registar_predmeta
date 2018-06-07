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
public class OsobaUVeziSaUdzbenikom implements OpstiDomenskiObjekat, Serializable {

    private int osobaId;
    private String ime;
    private String prezime;
    private String titula;
    private int udzbenikId;
    private UlogaUdzbenik ulogaUdzbenik;

    public OsobaUVeziSaUdzbenikom() {
    }

    public OsobaUVeziSaUdzbenikom(int osobaId, String ime, String prezime, String titula, int udzbenikId, UlogaUdzbenik ulogaUdzbenik) {
        this.osobaId = osobaId;
        this.ime = ime;
        this.prezime = prezime;
        this.titula = titula;
        this.udzbenikId = udzbenikId;
        this.ulogaUdzbenik = ulogaUdzbenik;
    }

    public int getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(int osobaId) {
        this.osobaId = osobaId;
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

    public UlogaUdzbenik getUlogaUdzbenik() {
        return ulogaUdzbenik;
    }

    public void setUlogaUdzbenik(UlogaUdzbenik ulogaUdzbenik) {
        this.ulogaUdzbenik = ulogaUdzbenik;
    }

    public int getUdzbenikId() {
        return udzbenikId;
    }

    public void setUdzbenikId(int udzbenikId) {
        this.udzbenikId = udzbenikId;
    }

    @Override
    public String toString() {
        return "OsobaUVeziSaUdzbenikom{" + "id=" + osobaId + ", ime=" + ime + ", prezime=" + prezime + ", titula=" + titula + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "osoba_u_vezi_sa_udzbenikom";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "" + getOsobaId() + ",'" + getIme() + "','" + getPrezime() + "','" + getTitula() + "'";
    }

}
