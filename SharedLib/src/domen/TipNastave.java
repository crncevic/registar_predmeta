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
public class TipNastave implements OpstiDomenskiObjekat, Serializable {

    private int tipNastaveId;
    private String naziv;

    public TipNastave() {
    }

    public TipNastave(String naziv) {
        this.naziv = naziv;
    }

    public TipNastave(int tipNastaveId, String naziv) {
        this.tipNastaveId = tipNastaveId;
        this.naziv = naziv;
    }

    public int getTipNastaveId() {
        return tipNastaveId;
    }

    public void setTipNastaveId(int tipNastaveId) {
        this.tipNastaveId = tipNastaveId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "TipNastave{" + "tipNastaveId=" + tipNastaveId + ", naziv=" + naziv + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "tip_nastave";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "tip_nastaveId=" + getTipNastaveId();
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
        return new TipNastave(rs.getInt("tip_nastaveId"), rs.getString("naziv"));
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "naziv";
    }

}
