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
public class Uloga implements Serializable, OpstiDomenskiObjekat {

    private int ulogaId;
    private String naziv;

    public Uloga() {
    }

    public Uloga(int ulogaId, String naziv) {
        this.ulogaId = ulogaId;
        this.naziv = naziv;
    }

    public int getUlogaId() {
        return ulogaId;
    }

    public void setUlogaId(int ulogaId) {
        this.ulogaId = ulogaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String vratiImeKlase() {
        return "uloga";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "ulogaId=" + getUlogaId();
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
        return new Uloga(rs.getInt("ulogaId"), rs.getString("naziv"));
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "naziv";
    }

}
