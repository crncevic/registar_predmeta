/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Udzbenik;

/**
 *
 * @author Petar
 */
public class VratiUdzbenikZaId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Udzbenik) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        Udzbenik udzbenik = (Udzbenik) odo;
        try {

            return (Udzbenik) dbbr.vratiPoId(udzbenik);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom trazenja udzbenika sa id:" + udzbenik.getUdzbenikId() + " Greska: " + e.getMessage());
        }
    }

}
