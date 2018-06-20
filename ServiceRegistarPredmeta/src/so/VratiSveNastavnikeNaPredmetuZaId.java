/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;


import domen.NastavnikNaPredmetu;
import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import domen.TipNastave;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class VratiSveNastavnikeNaPredmetuZaId extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof NastavnikNaPredmetu) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        NastavnikNaPredmetu nnp1 = (NastavnikNaPredmetu) odo;
        try {
            String upit = "SELECT * FROM nastavnik_na_predmetu WHERE predmetId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, nnp1.getPredmet().getPredmetId());

            ResultSet rs = ps.executeQuery();

            ApstraktnaGenerickaOperacija vratiTipNastaveZaId = new VratiTipNastaveZaId();
            
            if (rs.next()) {
                NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();

                rs.close();
                ps.close();

                ApstraktnaGenerickaOperacija vratiPredmetZaId = new VratiPredmetZaId();
                
                
                NastavnikNaPredmetu nastavnikNaPredmetu = (NastavnikNaPredmetu) nnp.napraviDomenskiObjekat(rs);
                nastavnikNaPredmetu.setPredmet((Predmet) vratiPredmetZaId.opsteIzvrsenje(nastavnikNaPredmetu.getPredmet()));
                nastavnikNaPredmetu.setTipNastave((TipNastave) vratiTipNastaveZaId.opsteIzvrsenje(nastavnikNaPredmetu.getTipNastave()));
                commitTransakcije();
                return nastavnikNaPredmetu;
            }

            rs.close();
            ps.close();
            return null;
        } catch (Exception ex) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom vracanja svih nasavnika na premdetu. Greska:" + ex.getMessage());
        }
    }

}
