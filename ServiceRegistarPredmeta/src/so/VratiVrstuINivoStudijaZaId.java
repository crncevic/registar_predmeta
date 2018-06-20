/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.VrstaINivoStudija;

/**
 *
 * @author Petar
 */
public class VratiVrstuINivoStudijaZaId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof VrstaINivoStudija) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        VrstaINivoStudija vins1 = (VrstaINivoStudija) odo;
        try {

            return (VrstaINivoStudija) dbbr.vratiPoId(vins1);
        } catch (Exception ex) {
            System.out.println("Dogodila se greska prilikom vracanja vrste i nivoa studija za id:" + vins1.getVrstaINivoId());
            throw new Exception("Dogodila se greska prilikom vracanja vrste i nivoa studija za id:" + vins1.getVrstaINivoId() + ". Greska:" + ex.getMessage());
        }
    }

}
