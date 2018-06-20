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
import domen.VrstaINivoStudija;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiSvePredmete extends ApstraktnaGenerickaOperacija {

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

            String upit = "SELECT * FROM predmet";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<Predmet> predmeti = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Predmet predmet = new Predmet();
                predmet = (Predmet) predmet.napraviDomenskiObjekat(rs);
                ApstraktnaGenerickaOperacija vratiVrstuINivoStudijaZaId = new VratiVrstuINivoStudijaZaId();

                predmet.setVrstaINivoStudija((VrstaINivoStudija) vratiVrstuINivoStudijaZaId.opsteIzvrsenje(predmet.getVrstaINivoStudija()));

                // ucitavanje tematskih celina za predmet
//                String upit3 = "SELECT * FROM tematska_celina WHERE predmetId=?";
//                PreparedStatement ps3 = dbbr.getConnection().prepareStatement(upit3);
//                ps3.setInt(1, predmet.getPredmetId());
//                List<TematskaCelina> sadrzajTematskeCeline = new ArrayList<>();
//
//                ResultSet rs3 = ps3.executeQuery();
//
//                while (rs3.next()) {
//                    TematskaCelina tematskaCelina = new TematskaCelina();
//                    tematskaCelina.setTematskaCelinaId(rs3.getInt("tematska_celinaId"));
//                    tematskaCelina.setTipNastaveId(rs3.getInt("tip_nastaveId"));
//                    tematskaCelina.setPredmetId(rs3.getInt("predmetId"));
//                    tematskaCelina.setNaziv(rs3.getString("naziv"));
//                    tematskaCelina.setOpis(rs3.getString("opis"));
//
//                    sadrzajTematskeCeline.add(tematskaCelina);
//
//                }
//
//                predmet.setSadrzajTematskeCeline(sadrzajTematskeCeline);
//
//                rs3.close();
//                ps3.close();
                //ucitavanje udzbenika za predmet
                String upit4 = "SELECT * FROM udzbenik_na_predmetu WHERE predmetId=?";
                PreparedStatement ps4 = dbbr.getConnection().prepareStatement(upit4);
                ps4.setInt(1, predmet.getPredmetId());
                ResultSet rs4 = ps4.executeQuery();

                List<Udzbenik> udzbenici = new ArrayList<>();

                while (rs4.next()) {
                    ApstraktnaGenerickaOperacija vratiUdzbenikZaid = new VratiUdzbenikZaId();
                    Udzbenik u1 = new Udzbenik();
                    u1.setUdzbenikId(rs4.getInt("udzbenikId"));
                    Udzbenik udzbenik = (Udzbenik) vratiUdzbenikZaid.opsteIzvrsenje(u1);
                    udzbenici.add(udzbenik);
                }

                predmet.setUdzbenici(udzbenici);

                rs4.close();
                ps4.close();

                String upit5 = "SELECT * FROM nastavnik_na_predmetu WHERE predmetId=?";
                PreparedStatement ps5 = dbbr.getConnection().prepareStatement(upit5);
                ps5.setInt(1, predmet.getPredmetId());

                ResultSet rs5 = ps5.executeQuery();

                List<NastavnikNaPredmetu> nastavniciNaPredmetu = new ArrayList<>();

                while (rs5.next()) {
                    NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();
                    nnp = (NastavnikNaPredmetu) nnp.napraviDomenskiObjekat(rs5);

                    nastavniciNaPredmetu.add(nnp);
                }

                predmet.setNastavnici(nastavniciNaPredmetu);

                rs5.close();
                ps5.close();

                predmeti.add(predmet);
            }

            rs.close();
            ps.close();
            commitTransakcije();
            return predmeti;

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom vracanja svih predmeta iz baze.Greska:" + e.getMessage());
        }
    }

}
