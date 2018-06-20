/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.StudijskiProgram;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiSveStudijskePrograme extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof StudijskiProgram) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT * FROM studijski_program";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<StudijskiProgram> studijskiProgrami = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StudijskiProgram studijskiProgram = new StudijskiProgram();
                studijskiProgram.setStudijskiProgramId(rs.getInt("studijskiProgramId"));
                studijskiProgram.setNaziv(rs.getString("naziv"));

                studijskiProgrami.add(studijskiProgram);
            }

            rs.close();
            ps.close();
            commitTransakcije();
            return studijskiProgrami;

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom vracanja svih studijskih programa. Greska:" + e.getMessage());
        }
    }

}
