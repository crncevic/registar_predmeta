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
public class NastavnikNaPredmetu implements Serializable {

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
    
    
}
