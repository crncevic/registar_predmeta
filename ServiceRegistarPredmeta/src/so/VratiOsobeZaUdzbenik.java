/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.OsobaUVeziSaUdzbenikom;
import domen.Udzbenik;
import domen.UlogaUdzbenik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiOsobeZaUdzbenik extends ApstraktnaGenerickaOperacija {

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

            String upit = "SELECT * FROM osoba_u_vezi_sa_udzbenikom where udzbenikId=?";
            List<OsobaUVeziSaUdzbenikom> osobeUVeziSaUdzbenikom = new ArrayList<>();
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, udzbenik.getUdzbenikId());
            ResultSet rs = ps.executeQuery();
            
            ApstraktnaGenerickaOperacija vratiUloguNaUdzbenikuZaId = new VratiUloguNaUdzbenikuZaId();

            while (rs.next()) {
                OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
                OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom = (OsobaUVeziSaUdzbenikom) ouvsu.napraviDomenskiObjekat(rs);
                osobaUVeziSaUdzbenikom.setUlogaUdzbenik((UlogaUdzbenik) vratiUloguNaUdzbenikuZaId.opsteIzvrsenje(osobaUVeziSaUdzbenikom.getUlogaUdzbenik()));

                osobeUVeziSaUdzbenikom.add(osobaUVeziSaUdzbenikom);
            }

            rs.close();
            ps.close();
            
            commitTransakcije();

            return osobeUVeziSaUdzbenikom;
        } catch (Exception ex) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom vracanja svih osoba vezanih za udzbenik.Greska:" + ex.getMessage());
        }
    }

}
