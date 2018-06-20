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
public class VratiSveNastavnike extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Nastavnik) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT * FROM nastavnik";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<Nastavnik> nastavnici = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Nastavnik nastavnik = new Nastavnik();
                nastavnici.add((Nastavnik) nastavnik.napraviDomenskiObjekat(rs));
            }

            rs.close();
            ps.close();
            commitTransakcije();
            return nastavnici;
        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom ucitavanja svih nastavnika. Greska:" + e.getMessage());
        }
    }

}
