/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.PredmetNaStudijskomProgramu;

/**
 *
 * @author Petar
 */
public class KreirajPredmetNaStudijskomProgramu extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof PredmetNaStudijskomProgramu) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = (PredmetNaStudijskomProgramu) odo;

        try {

            dbbr.kreiraj(predmetNaStudijskomProgramu);

            commitTransakcije();

            ApstraktnaGenerickaOperacija vratiPredmetNaStudijskomPragramuZaId = new VratiPredmetNaStudijskomPragramuZaId();
            return vratiPredmetNaStudijskomPragramuZaId.opsteIzvrsenje(predmetNaStudijskomProgramu);

        } catch (Exception e) {

            rollbackTransakcije();

            throw new Exception("Dogodila se greska prilikom kreiranja predmeta na studijskom programu. Greska:" + e.getMessage());

        }
    }

}
