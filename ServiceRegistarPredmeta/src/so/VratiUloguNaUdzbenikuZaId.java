/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.UlogaUdzbenik;

/**
 *
 * @author Petar
 */
public class VratiUloguNaUdzbenikuZaId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof UlogaUdzbenik) {

        } else {
            throw new Exception("Greska u parametru");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        UlogaUdzbenik uu1 = (UlogaUdzbenik) odo;
        try {

            return (UlogaUdzbenik) dbbr.vratiPoId(uu1);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja uloge_udzbenik  sa id:" + uu1.getUlogaId() + " .Greska:" + e.getMessage());
        }
    }

}
