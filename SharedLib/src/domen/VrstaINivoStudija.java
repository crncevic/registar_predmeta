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
public class VrstaINivoStudija implements Serializable, OpstiDomenskiObjekat {

    private int vrstaINivoId;
    private String naziv;

    public VrstaINivoStudija() {
    }

    public VrstaINivoStudija(int vrstaINivoId, String naziv) {
        this.vrstaINivoId = vrstaINivoId;
        this.naziv = naziv;
    }

    public int getVrstaINivoId() {
        return vrstaINivoId;
    }

    public void setVrstaINivoId(int vrstaINivoId) {
        this.vrstaINivoId = vrstaINivoId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final VrstaINivoStudija other = (VrstaINivoStudija) obj;
        if (this.vrstaINivoId != other.vrstaINivoId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiImeKlase() {
        return "vrsta_i_nivo_studija";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "vrstaId=" + getVrstaINivoId();
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
        return new VrstaINivoStudija(rs.getInt("vrstaId"), rs.getString("naziv"));
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "naziv";
    }

}
