/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.OsobaUVeziSaUdzbenikom;
import domen.Udzbenik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiUdzbenikZaNaziv extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Udzbenik) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        Udzbenik u1 = (Udzbenik) odo;
        try {
            String upit = "SELECT * FROM udzbenik WHERE naziv='" + u1.getNaziv() + "'";
            PreparedStatement preparedStatement = dbbr.getConnection().prepareStatement(upit);
            ResultSet rs = preparedStatement.executeQuery(upit);

            if (rs.next()) {

                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                ApstraktnaGenerickaOperacija vratiOsobeZaUdzbenik = new VratiOsobeZaUdzbenik();
                udzbenik.setOsobeUVeziSaUdzbenikom((List<OsobaUVeziSaUdzbenikom>) vratiOsobeZaUdzbenik.opsteIzvrsenje(udzbenik));
                udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                udzbenik.setIzdavac(rs.getString("izdavac"));
                udzbenik.setStampa(rs.getString("stampa"));
                udzbenik.setTiraz(rs.getInt("tiraz"));
                udzbenik.setRbrIzdanja(rs.getInt("rbr_izdanja"));
                udzbenik.setIsbn(rs.getInt("isbn"));

                return udzbenik;
            }

            rs.close();
            preparedStatement.close();

            return null;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja udzbenika po nazivu: "
                    + u1.getNaziv() + "Greska: " + e.getMessage());
        }
    }

}
