/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.PredmetDao;
import domen.NastavnikNaPredmetu;
import domen.Predmet;
import domen.PredmetNaStudijskomProgramu;
import domen.TematskaCelina;
import domen.Udzbenik;
import domen.UdzbenikNaPredmetu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class PredmetDaoImpl extends PredmetDao {

    private static PredmetDaoImpl instance;

    private PredmetDaoImpl() throws Exception {
        super();
    }

    public static PredmetDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new PredmetDaoImpl();
        }
        return instance;
    }

    @Override
    public Predmet pronadjiPredmetPoId(int predmetId) throws Exception {
        try {

            Predmet predmet = new Predmet();
            predmet.setPredmetId(predmetId);
            Predmet predmetFromDb = (Predmet) dbbr.vratiPoId(predmet);

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
                ps4.setInt(1, predmetId);
                List<Udzbenik> udzbenici = new ArrayList<>();

                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    Udzbenik udzbenik = UdzbenikDaoImpl.getInstance().pronadjiUdzbenikPoId(rs4.getInt("udzbenikId"));
                    udzbenici.add(udzbenik);
                }

                predmetFromDb.setUdzbenici(udzbenici);

                String upit5 = "SELECT * FROM nastavnik_na_predmetu WHERE predmetId=?";
                PreparedStatement ps5 = dbbr.getConnection().prepareStatement(upit5);
                ps5.setInt(1, predmetId);

                ResultSet rs5 = ps5.executeQuery();

                List<NastavnikNaPredmetu> nastavniciNaPredmetu = new ArrayList<>();

                while (rs5.next()) {
                    NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();
                    nnp.setNastavnik(NastavnikDaoImpl.getInstance().vratiNastavnikaZaId(rs5.getInt("nastavnikId")));
                    nnp.setPredmet(predmetFromDb);
                    nnp.setTipNastave(TipNastaveDaoImpl.getInstance().pronadjiTipNastavePoId(rs5.getInt("tipNastaveId")));

                    nastavniciNaPredmetu.add(nnp);
                }

                predmetFromDb.setNastavnici(nastavniciNaPredmetu);

                // rs3.close();
                rs4.close();
                rs5.close();

                //  ps3.close();
                ps4.close();
                ps5.close();

                return predmetFromDb;

            } else {

                return null;
            }

        } catch (Exception e) {
            throw new Exception("Doodila se greska prilikom pretrazivanja predmeta sa id:" + predmetId + ". Greska:" + e.getMessage());
        }

    }

    @Override
    public Predmet kreirajPredmet(Predmet predmet) throws Exception {
        try {
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

            dbbr.getConnection().commit();
            System.out.println("Predmet sa id:" + predmet.getPredmetId() + " uspesno sacuvan u bazi!");

            return pronadjiPredmetPoId(predmetId);

        } catch (Exception e) {
            dbbr.getConnection().rollback();
            throw new Exception("Dogodila se greska prilikom cuvanja predmeta u bazi.Greska:" + e.getMessage());
        }
    }

    @Override
    public List<Predmet> vratiSvePredmete() throws Exception {
        try {

            String upit = "SELECT * FROM predmet";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<Predmet> predmeti = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Predmet predmet = new Predmet();
                predmet = (Predmet) predmet.napraviDomenskiObjekat(rs);

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
                    Udzbenik udzbenik = UdzbenikDaoImpl.getInstance().pronadjiUdzbenikPoId(rs4.getInt("udzbenikId"));
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

            return predmeti;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja svih predmeta iz baze.Greska:" + e.getMessage());
        }
    }

    @Override
    public Predmet azurirajPredmet(Predmet predmet) throws Exception {
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
            dbbr.getConnection().commit();

            return pronadjiPredmetPoId(predmet.getPredmetId());
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja predmeta sa id" + predmet.getPredmetId() + " .Greska:" + e.getMessage());
        }
    }

    public Predmet obrisiPredmet(int predmetId) throws Exception {
        Predmet predmet;
        try {

            predmet = pronadjiPredmetPoId(predmetId);

            NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();
            nnp.setPredmet(predmet);
            dbbr.obrisi(nnp);

            UdzbenikNaPredmetu unp = new UdzbenikNaPredmetu();
            unp.setPredmetId(predmetId);
            dbbr.obrisi(unp);

//            TematskaCelina tc = new TematskaCelina();
//            tc.setPredmet(predmet);
//            dbbr.obrisi(tc);
            String upit = "DELETE FROM predmet_na_studijskom_programu WHERE predmetId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, predmetId);

            ps.executeUpdate();

            dbbr.obrisi(predmet);

            dbbr.getConnection().commit();

            return predmet;

        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom brisanja predmeta sa id" + predmetId + " .Greska:" + e.getMessage());
        }
    }

}
