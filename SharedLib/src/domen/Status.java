/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class Status implements Serializable, OpstiDomenskiObjekat {

    private int statusId;
    private String naziv;

    public Status() {
    }

    public Status(int statusId, String naziv) {
        this.statusId = statusId;
        this.naziv = naziv;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Status other = (Status) obj;
        if (this.statusId != other.statusId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiImeKlase() {
        return "status";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "statusId=" + getStatusId();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public String postaviVrednostAtributa() {
        return "naziv='" + getNaziv() + "'";
    }

    @Override
    public OpstiDomenskiObjekat napraviDomenskiObjekat(ResultSet rs) throws Exception {
        return new Status(rs.getInt("statusId"), rs.getString("naziv"));
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "naziv";
    }

}
