/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Nastavnik;
import domen.OpstiDomenskiObjekat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiNastavnikaZaId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Nastavnik) {

        } else {
            throw new Exception("Greska u paramteru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        Nastavnik n = (Nastavnik) odo;  
        try {
            Nastavnik nastavnik = new Nastavnik();
            nastavnik.setNastavnikId(n.getNastavnikId());
            commitTransakcije();
            return (Nastavnik) dbbr.vratiPoId(nastavnik);

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom ucitavanja  nastavnika sa id:" + n.getNastavnikId() + ". Greska:" + e.getMessage());
        }
    }

}
