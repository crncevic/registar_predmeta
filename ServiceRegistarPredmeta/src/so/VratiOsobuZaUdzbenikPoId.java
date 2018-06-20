/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.OsobaUVeziSaUdzbenikom;
import domen.UlogaUdzbenik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class VratiOsobuZaUdzbenikPoId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof OsobaUVeziSaUdzbenikom) {

        } else {
            throw new Exception("Greska u parametru");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        OsobaUVeziSaUdzbenikom o1 = (OsobaUVeziSaUdzbenikom) odo;
        try {
            String upit = "SELECT * FROM osoba_u_vezi_sa_udzbenikom WHERE osobaId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, o1.getOsobaId());

            ResultSet rs = ps.executeQuery();

            ApstraktnaGenerickaOperacija vratiUloguNaUdzbenikuZaId = new VratiUloguNaUdzbenikuZaId();

            if (rs.next()) {
                OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();

                rs.close();
                ps.close();

                OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom = (OsobaUVeziSaUdzbenikom) ouvsu.napraviDomenskiObjekat(rs);
                osobaUVeziSaUdzbenikom.setUlogaUdzbenik((UlogaUdzbenik) vratiUloguNaUdzbenikuZaId.opsteIzvrsenje(osobaUVeziSaUdzbenikom.getUlogaUdzbenik()));
                commitTransakcije();
                return osobaUVeziSaUdzbenikom;
            }

            rs.close();
            ps.close();
            return null;
        } catch (Exception ex) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom pretrazivanja osobe koja je u vezi sa udzbenikom sa id:" + o1.getOsobaId() + " .Greska:" + ex.getMessage());
        }
    }

}
