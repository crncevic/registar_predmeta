/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiSveStatuse extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Status) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT * FROM status";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<Status> statusi = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Status status = new Status();
                status.setStatusId(rs.getInt("statusId"));
                status.setNaziv(rs.getString("naziv"));

                statusi.add(status);
            }

            rs.close();
            ps.close();
            commitTransakcije();

            return statusi;

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom vracanja svih statusa. Greska: " + e.getMessage());
        }
    }

}
