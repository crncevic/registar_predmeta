/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.OsobaUVeziSaUdzbenikom;
import domen.Udzbenik;

/**
 *
 * @author Petar
 */
public class KreirajUdzbenik extends ApstraktnaGenerickaOperacija {

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

            int udzbenikId = dbbr.kreiraj(udzbenik);

            for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                osoba.setUdzbenikId(udzbenikId);
                dbbr.kreiraj(osoba);
            }

           commitTransakcije();
            
            ApstraktnaGenerickaOperacija vratiUdzbenikPoId = new VratiUdzbenikZaId();
            return vratiUdzbenikPoId.opsteIzvrsenje(udzbenik);
        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom kreiranja udzbenika! Greska:" + e.getMessage());
        }
    }
}


