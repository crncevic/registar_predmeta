/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Status;

/**
 *
 * @author Petar
 */
public class VratiStatusZaId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Status) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        Status status = (Status) odo;
        try {

            return (Status) dbbr.vratiPoId(status);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja statusa sa id:" + status.getStatusId() + ". Greska:" + e.getMessage());
        }
    }

}
