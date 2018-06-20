/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.VrstaINivoStudija;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiSveVrsteStudija extends ApstraktnaGenerickaOperacija{

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if(odo instanceof VrstaINivoStudija){
            
        }else{
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "SELECT * FROM vrsta_i_nivo_studija";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<VrstaINivoStudija> vrste = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VrstaINivoStudija vins = new VrstaINivoStudija();

                vins.setVrstaINivoId(rs.getInt("vrstaId"));
                vins.setNaziv(rs.getString("naziv"));

                vrste.add(vins);
            }

            rs.close();
            ps.close();
            commitTransakcije();
            return vrste;
        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom vracanja svih vrsta i nivoa studija. Greska:" + e.getMessage());
        }
    }
    
}
