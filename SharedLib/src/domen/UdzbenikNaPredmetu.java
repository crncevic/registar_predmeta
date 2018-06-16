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
public class UdzbenikNaPredmetu implements Serializable, OpstiDomenskiObjekat {

    private int predmetId;
    private int udbenikId;

    public UdzbenikNaPredmetu() {
    }

    public UdzbenikNaPredmetu(int predmetId, int udbenikId) {
        this.predmetId = predmetId;
        this.udbenikId = udbenikId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public int getUdbenikId() {
        return udbenikId;
    }

    public void setUdbenikId(int udbenikId) {
        this.udbenikId = udbenikId;
    }

    @Override
    public String vratiImeKlase() {
        return "udzbenik_na_predmetu";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return udbenikId + "," + predmetId;
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "predmetId=" + getPredmetId();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "predmetId=" + getPredmetId();
    }

    @Override
    public String postaviVrednostAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OpstiDomenskiObjekat napraviDomenskiObjekat(ResultSet rs) throws Exception {
        return new UdzbenikNaPredmetu(rs.getInt("predmetId"), rs.getInt("udzbenikId"));
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "udzbenikId,predmetId";
    }

}
