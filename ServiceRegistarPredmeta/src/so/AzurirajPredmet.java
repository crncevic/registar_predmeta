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
public class AzurirajPredmet extends ApstraktnaGenerickaOperacija {

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

            dbbr.azuriraj(predmet);

            NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();
            nnp.setPredmet(predmet);
            dbbr.obrisi(nnp);

            for (NastavnikNaPredmetu nastavnikNaPredmetu : predmet.getNastavnici()) {
                dbbr.kreiraj(nastavnikNaPredmetu);
            }

            UdzbenikNaPredmetu unp = new UdzbenikNaPredmetu();
            unp.setPredmetId(predmet.getPredmetId());
            dbbr.obrisi(unp);

            for (Udzbenik udzbenik : predmet.getUdzbenici()) {
                unp.setUdbenikId(udzbenik.getUdzbenikId());
                dbbr.kreiraj(unp);
            }

//            String upit6 = "DELETE FROM tematska_celina WHERE predmetId=?";
//            PreparedStatement ps6 = dbbr.getConnection().prepareStatement(upit6);
//            ps6.setInt(1, predmet.getPredmetId());
//            ps6.executeUpdate();
//
//            ps6.close();
//
//            String upit7 = "INSERT INTO tematska_celina(predmetId,tip_nastaveId,nadredjena_tematska_celinaId,"
//                    + "naziv,opis) VALUES (?,?,?,?,?)";
//            PreparedStatement ps7 = dbbr.getConnection().prepareStatement(upit7);
//            for (TematskaCelina tematskaCelina : predmet.getSadrzajTematskeCeline()) {
//                ps7.setInt(1, predmet.getPredmetId());
//                ps7.setInt(2, tematskaCelina.getTipNastaveId());
//                ps7.setInt(3, tematskaCelina.getNadredjenaTematskaCelina().getTematskaCelinaId());
//                ps7.setString(4, tematskaCelina.getNaziv());
//                ps7.setString(5, tematskaCelina.getOpis());
//                
//                ps7.executeUpdate();
//            }
//            
//            ps7.close();
            commitTransakcije();
            ApstraktnaGenerickaOperacija vratiPredmetZaId = new VratiPredmetZaId();

            return vratiPredmetZaId.opsteIzvrsenje(predmet);
        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja predmeta sa id" + predmet.getPredmetId() + " .Greska:" + e.getMessage());
        }
    }

}
