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
public class TematskaCelina implements OpstiDomenskiObjekat, Serializable {

    private int tematskaCelinaId;
    private TipNastave tipNastave;
    private Predmet predmet;

    private TematskaCelina nadredjenaTematskaCelina;
    private String naziv;
    private String opis;

    public TematskaCelina() {
    }

    public TematskaCelina(int tematskaCelinaId, TipNastave tipNastave, Predmet predmet, TematskaCelina nadredjenaTematskaCelina, String naziv, String opis) {
        this.tematskaCelinaId = tematskaCelinaId;
        this.tipNastave = tipNastave;
        this.predmet = predmet;
        this.nadredjenaTematskaCelina = nadredjenaTematskaCelina;
        this.naziv = naziv;
        this.opis = opis;
    }

    public int getTematskaCelinaId() {
        return tematskaCelinaId;
    }

    public void setTematskaCelinaId(int tematskaCelinaId) {
        this.tematskaCelinaId = tematskaCelinaId;
    }

    public TipNastave getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastave tipNastave) {
        this.tipNastave = tipNastave;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public TematskaCelina getNadredjenaTematskaCelina() {
        return nadredjenaTematskaCelina;
    }

    public void setNadredjenaTematskaCelina(TematskaCelina nadredjenaTematskaCelina) {
        this.nadredjenaTematskaCelina = nadredjenaTematskaCelina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String vratiImeKlase() {
        return "tematska_celina";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return tipNastave.getTipNastaveId() + "," + predmet.getPredmetId() + "," + getNadredjenaTematskaCelina().getTematskaCelinaId() + ",'" + getNaziv() + "','" + getOpis() + "'";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "tematska_celinaId=" + getTematskaCelinaId() + " AND predmetId=" + predmet.getPredmetId() + " AND tip_nastaveId=" + tipNastave.getTipNastaveId();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String postaviVrednostAtributa() {
        return "nadredjena_tematska_celinaId=" + getNadredjenaTematskaCelina().getTematskaCelinaId() + ",naziv='" + getNaziv() + "',opis='" + getOpis() + "'";
    }

    @Override
    public OpstiDomenskiObjekat napraviDomenskiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "predmetId,tip_nastaveId,nadredjena_tematska_celinaId,naziv,opis";
    }

}
