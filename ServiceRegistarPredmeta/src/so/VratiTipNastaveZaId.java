/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.TipNastave;

/**
 *
 * @author Petar
 */
public class VratiTipNastaveZaId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof TipNastave) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        TipNastave tipNastave = (TipNastave) odo;
        try {
            return (TipNastave) dbbr.vratiPoId(tipNastave);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja tipa nastave.Greska:" + e.getMessage());

        }
    }

}
