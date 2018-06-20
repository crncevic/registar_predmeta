/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.UlogaUdzbenik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class VratiUloguNaUdzbenikuZaNaziv extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof UlogaUdzbenik) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        UlogaUdzbenik uu1 = (UlogaUdzbenik) odo;
        try {
            String upit = "SELECT * FROM uloga_udzbenik WHERE naziv=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setString(1, uu1.getNaziv());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UlogaUdzbenik ulogaUdzbenik = new UlogaUdzbenik();
                ulogaUdzbenik.setUlogaId(rs.getInt("ulogaId"));
                ulogaUdzbenik.setNaziv(rs.getString("naziv"));

                rs.close();
                ps.close();

                return ulogaUdzbenik;
            }

            rs.close();
            ps.close();
            commitTransakcije();
            return null;

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom pretrazivanja uloge_udzbenik  sa nazivom" + uu1.getNaziv() + " .Greska:" + e.getMessage());
        }
    }

}
