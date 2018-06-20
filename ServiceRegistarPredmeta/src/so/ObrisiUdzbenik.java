/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.OsobaUVeziSaUdzbenikom;
import domen.Udzbenik;
import domen.UdzbenikNaPredmetu;

/**
 *
 * @author Petar
 */
public class ObrisiUdzbenik extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Udzbenik) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        Udzbenik u1 = (Udzbenik) odo;
        try {
            ApstraktnaGenerickaOperacija vratiUdzbenikZaId = new VratiUdzbenikZaId();
            Udzbenik udzbenik = (Udzbenik) vratiUdzbenikZaId.opsteIzvrsenje(u1);

            OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
            ouvsu.setUdzbenikId(u1.getUdzbenikId());
            dbbr.obrisi(ouvsu);

            UdzbenikNaPredmetu unp = new UdzbenikNaPredmetu();
            unp.setUdbenikId(u1.getUdzbenikId());
            dbbr.obrisi(unp);

            dbbr.obrisi(udzbenik);
            commitTransakcije();
            return udzbenik;

        } catch (Exception e) {
           rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom brisanja udzbenika sa id:" + u1.getUdzbenikId() + " Greska: " + e.getMessage());
        }
    }

}
