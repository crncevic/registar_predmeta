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
public class ObrisiPredmetNastudijskomProgramu extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof PredmetNaStudijskomProgramu) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        PredmetNaStudijskomProgramu pnsp1 = (PredmetNaStudijskomProgramu) odo; 
        try {
            ApstraktnaGenerickaOperacija vratiPredmetNaStudjskomProgramuZaId = new VratiPredmetNaStudijskomPragramuZaId();
            
            PredmetNaStudijskomProgramu pnsp = (PredmetNaStudijskomProgramu) vratiPredmetNaStudjskomProgramuZaId.opsteIzvrsenje(pnsp1);

            dbbr.obrisi(pnsp);
            commitTransakcije();
            return pnsp;
        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

}
