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
public class AzurirajUdzbenik extends ApstraktnaGenerickaOperacija {

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
            dbbr.azuriraj(udzbenik);

            OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
            ouvsu.setUdzbenikId(udzbenik.getUdzbenikId());
            dbbr.obrisi(ouvsu);

            for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                osoba.setUdzbenikId(udzbenik.getUdzbenikId());
                dbbr.kreiraj(osoba);
            }

            commitTransakcije();

            ApstraktnaGenerickaOperacija vratiUdzbenikZaId = new VratiUdzbenikZaId();
            return vratiUdzbenikZaId.opsteIzvrsenje(udzbenik);

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja udzbenika sa id:"
                    + +udzbenik.getUdzbenikId() + ". Greska: " + e.getMessage());
        }
    }

}
