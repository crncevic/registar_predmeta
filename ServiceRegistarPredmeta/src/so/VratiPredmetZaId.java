/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Nastavnik;
import domen.NastavnikNaPredmetu;
import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import domen.TipNastave;
import domen.Udzbenik;
import domen.VrstaINivoStudija;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiPredmetZaId extends ApstraktnaGenerickaOperacija {

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

            Predmet predmetFromDb = (Predmet) dbbr.vratiPoId(predmet);
            ApstraktnaGenerickaOperacija vratiVrstuINivoStudijaZaId = new VratiVrstuINivoStudijaZaId();
            predmet.setVrstaINivoStudija((VrstaINivoStudija) vratiVrstuINivoStudijaZaId.opsteIzvrsenje(predmetFromDb.getVrstaINivoStudija()));

            if (predmetFromDb != null) {
                // ucitavanje tematskih celina za predmet
//                String upit3 = "SELECT * FROM tematska_celina WHERE predmetId=?";
//                PreparedStatement ps3 = dbbr.getConnection().prepareStatement(upit3);
//                ps3.setInt(1, predmetId);
//                List<TematskaCelina> sadrzajTematskeCeline = new ArrayList<>();
//
//                ResultSet rs3 = ps3.executeQuery();
//
//                while (rs3.next()) {
//                    TematskaCelina tematskaCelina = new TematskaCelina();
//                    tematskaCelina.setTematskaCelinaId(rs3.getInt("tematska_celinaId"));
////                    tematskaCelina.setTipNastaveId(rs3.getInt("tip_nastaveId"));
////                    tematskaCelina.setPredmetId(rs3.getInt("predmetId"));
//                    tematskaCelina.setNaziv(rs3.getString("naziv"));
//                    tematskaCelina.setOpis(rs3.getString("opis"));
//
//                    sadrzajTematskeCeline.add(tematskaCelina);
//
//                }
//
//                predmetFromDb.setSadrzajTematskeCeline(sadrzajTematskeCeline);

                //ucitavanje udzbenika za predmet
                String upit4 = "SELECT * FROM udzbenik_na_predmetu WHERE predmetId=?";
                PreparedStatement ps4 = dbbr.getConnection().prepareStatement(upit4);
                ps4.setInt(1, predmetFromDb.getPredmetId());
                List<Udzbenik> udzbenici = new ArrayList<>();

                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    ApstraktnaGenerickaOperacija vratiUdzbenikZaid = new VratiUdzbenikZaId();
                    Udzbenik u1 = new Udzbenik();
                    u1.setUdzbenikId(rs4.getInt("udzbenikId"));
                    Udzbenik udzbenik = (Udzbenik) vratiUdzbenikZaid.opsteIzvrsenje(u1);
                    udzbenici.add(udzbenik);
                }

                predmetFromDb.setUdzbenici(udzbenici);

                String upit5 = "SELECT * FROM nastavnik_na_predmetu WHERE predmetId=?";
                PreparedStatement ps5 = dbbr.getConnection().prepareStatement(upit5);
                ps5.setInt(1, predmetFromDb.getPredmetId());

                ResultSet rs5 = ps5.executeQuery();

                List<NastavnikNaPredmetu> nastavniciNaPredmetu = new ArrayList<>();

                while (rs5.next()) {
                    NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();

                    ApstraktnaGenerickaOperacija vratiNastavnikaZaId = new VratiNastavnikaZaId();
                    Nastavnik nastavnik = new Nastavnik();
                    nastavnik.setNastavnikId(rs5.getInt("nastavnikId"));
                    nnp.setNastavnik((Nastavnik) vratiNastavnikaZaId.opsteIzvrsenje(nastavnik));

                    nnp.setPredmet(predmetFromDb);

                    ApstraktnaGenerickaOperacija vratiTipNastavezaId = new VratiTipNastaveZaId();

                    TipNastave tipNastave = new TipNastave();
                    tipNastave.setTipNastaveId(rs5.getInt("tipNastaveId"));
                    nnp.setTipNastave((TipNastave) vratiTipNastavezaId.opsteIzvrsenje(tipNastave));

                    nastavniciNaPredmetu.add(nnp);
                }

                predmetFromDb.setNastavnici(nastavniciNaPredmetu);

                // rs3.close();
                rs4.close();
                rs5.close();

                //  ps3.close();
                ps4.close();
                ps5.close();
                commitTransakcije();
                return predmetFromDb;

            } else {

                return null;
            }

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Doodila se greska prilikom pretrazivanja predmeta sa id:" + predmet.getPredmetId() + ". Greska:" + e.getMessage());
        }
    }

}
