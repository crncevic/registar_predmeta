/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.NastavnikNaPredmetu;
import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import domen.UdzbenikNaPredmetu;
import java.sql.PreparedStatement;

/**
 *
 * @author Petar
 */
public class ObrisiPredmet extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Predmet) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        Predmet predmet = (Predmet) odo;
        try {
            ApstraktnaGenerickaOperacija vratiPredmetZaId = new VratiPredmetZaId();
            predmet = (Predmet) vratiPredmetZaId.opsteIzvrsenje(predmet);

            NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();
            nnp.setPredmet(predmet);
            dbbr.obrisi(nnp);

            UdzbenikNaPredmetu unp = new UdzbenikNaPredmetu();
            unp.setPredmetId(predmet.getPredmetId());
            dbbr.obrisi(unp);

//            TematskaCelina tc = new TematskaCelina();
//            tc.setPredmet(predmet);
//            dbbr.obrisi(tc);
            String upit = "DELETE FROM predmet_na_studijskom_programu WHERE predmetId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, predmet.getPredmetId());

            ps.executeUpdate();

            dbbr.obrisi(predmet);

            commitTransakcije();

            return predmet;

        } catch (Exception e) {
          rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom brisanja predmeta sa id" + predmet.getPredmetId() + " .Greska:" + e.getMessage());
        }
    }

}
