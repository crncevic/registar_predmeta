/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.TipNastave;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiSveTipoveNastave extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof TipNastave) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
         try {
            String upit = "SELECT * FROM tip_nastave";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<TipNastave> tipoviNastave = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TipNastave tn = new TipNastave();
                tn.setTipNastaveId(rs.getInt("tip_nastaveId"));
                tn.setNaziv(rs.getString("naziv"));

                tipoviNastave.add(tn);
            }

            rs.close();
            ps.close();
            commitTransakcije();
            return tipoviNastave;

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom vracanja svih tipova nastave.Greska:" + e.getMessage());
        }
    }

}
