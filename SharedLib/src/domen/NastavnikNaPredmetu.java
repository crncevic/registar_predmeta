/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import db.dao.impl.NastavnikDaoImpl;
import db.dao.impl.PredmetDaoImpl;
import db.dao.impl.TipNastaveDaoImpl;
import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class NastavnikNaPredmetu implements Serializable, OpstiDomenskiObjekat {

    private Predmet predmet;
    private Nastavnik nastavnik;
    private TipNastave tipNastave;

    public NastavnikNaPredmetu() {
    }

    public NastavnikNaPredmetu(Predmet predmet, Nastavnik nastavnik, TipNastave tipNastave) {
        this.predmet = predmet;
        this.nastavnik = nastavnik;
        this.tipNastave = tipNastave;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public TipNastave getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastave tipNastave) {
        this.tipNastave = tipNastave;
    }

    @Override
    public String vratiImeKlase() {
        return "nastavnik_na_predmetu";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return nastavnik.getNastavnikId() + "," + predmet.getPredmetId() + "," + tipNastave.getTipNastaveId();
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "predmetId=" + predmet.getPredmetId();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        return "predmetId=" + getPredmet().getPredmetId();
    }

    @Override
    public String postaviVrednostAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OpstiDomenskiObjekat napraviDomenskiObjekat(ResultSet rs) throws Exception {
        return new NastavnikNaPredmetu(PredmetDaoImpl.getInstance().pronadjiPredmetPoId(rs.getInt("predmetId")), NastavnikDaoImpl.getInstance().vratiNastavnikaZaId(rs.getInt("nastavnikId")), TipNastaveDaoImpl.getInstance().pronadjiTipNastavePoId(rs.getInt("tipNastaveId")));
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "nastavnikId,predmetId,tipNastaveId";
    }

}
