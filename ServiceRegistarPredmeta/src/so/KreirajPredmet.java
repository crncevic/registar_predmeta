/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.NastavnikNaPredmetu;
import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import domen.Udzbenik;
import domen.UdzbenikNaPredmetu;

/**
 *
 * @author Petar
 */
public class KreirajPredmet extends ApstraktnaGenerickaOperacija {

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        if (odo instanceof Predmet) {

        } else {
            throw new Exception("Greska u parametru!");
        }
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            Predmet predmet = (Predmet) odo;

            int predmetId = dbbr.kreiraj(predmet);
            predmet.setPredmetId(predmetId);

            for (NastavnikNaPredmetu nastavnikNaPredmetu : predmet.getNastavnici()) {
                nastavnikNaPredmetu.setPredmet(predmet);
                dbbr.kreiraj(nastavnikNaPredmetu);
            }

//            String upit3 = "INSERT INTO tematska_celina(predmetId,tip_NastaveId,nadredjena_tematska_celinaId,naziv,opis)";
//            PreparedStatement ps3 = dbbr.getConnection().prepareStatement(upit3);
//            for (TematskaCelina tematskaCelina : predmet.getSadrzajTematskeCeline()) {
//                ps3.setInt(1, tematskaCelina.getPredmetId());
//                ps3.setInt(2, tematskaCelina.getTipNastaveId());
//                ps3.setInt(3, tematskaCelina.getNadredjenaTematskaCelina() != null ? tematskaCelina.getNadredjenaTematskaCelina() : null);
//                ps3.setString(4, tematskaCelina.getNaziv());
//                ps.setString(5, tematskaCelina.getOpis());
//                
//                ps3.executeUpdate();
//            }
            for (Udzbenik udzbenik : predmet.getUdzbenici()) {
                UdzbenikNaPredmetu unp = new UdzbenikNaPredmetu(predmetId, udzbenik.getUdzbenikId());
                dbbr.kreiraj(unp);
            }

            commitTransakcije();
            System.out.println("Predmet sa id:" + predmet.getPredmetId() + " uspesno sacuvan u bazi!");

            ApstraktnaGenerickaOperacija vratiPredmetPoId = new VratiPredmetZaId();
            return vratiPredmetPoId.opsteIzvrsenje(predmet);

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom cuvanja predmeta u bazi.Greska:" + e.getMessage());
        }
    }

}
