/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.StudijskiProgram;

/**
 *
 * @author Petar
 */
public class VratiStudijskiProgramZaId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof StudijskiProgram) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        StudijskiProgram studijskiProgram = (StudijskiProgram) odo;
        try {

            return (StudijskiProgram) dbbr.vratiPoId(studijskiProgram);

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja studijskog programa sa id:" + studijskiProgram.getStudijskiProgramId() + ". Greska:" + e.getMessage());
        }
    }

}
