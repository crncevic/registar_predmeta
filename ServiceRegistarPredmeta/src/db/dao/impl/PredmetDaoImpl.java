/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.PredmetDao;
import domen.NastavnikNaPredmetu;
import domen.Predmet;
import domen.TematskaCelina;
import domen.Udzbenik;
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

            String upit = "SELECT * FROM predmet WHERE predmetId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);

            ps.setInt(1, predmetId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Predmet predmet = new Predmet();
                predmet.setPredmetId(rs.getInt("predmetId"));
                predmet.setNaziv(rs.getString("naziv"));
                predmet.setBrCasovaPredavanjaNedeljno(rs.getInt("br_casova_predavanja_nedeljno"));
                predmet.setBrCasovaVezbiNedeljno(rs.getInt("br_casova_vezbi_nedeljno"));
                predmet.setOstaliCasovi(rs.getInt("ostali_casovi"));
                predmet.setDrugiObliciNastave(rs.getString("drugi_oblici_nastave"));
                predmet.setStudijskiIstrazivackiRad(rs.getString("studijski_istrazivacki_rad"));
                predmet.setCilj(rs.getString("cilj"));
                predmet.setIshod(rs.getString("ishod"));
                predmet.setUslov(rs.getString("uslov"));
                predmet.setVrstaINivoStudija(VrstaINivoStudijaDaoImpl
                        .getInstance()
                        .vratiVrstuINivoStudijaZaId(rs.getInt("vrsta_i_nivo_studija")));
                predmet.setSadrzajTekst(rs.getString("sadrzaj_tekst"));

                // ucitavanje tematskih celina za predmet
                String upit3 = "SELECT * FROM tematska_celina WHERE predmetId=?";
                PreparedStatement ps3 = dbbr.getConnection().prepareStatement(upit3);
                ps3.setInt(1, predmetId);
                List<TematskaCelina> sadrzajTematskeCeline = new ArrayList<>();

                ResultSet rs3 = ps3.executeQuery();

                while (rs3.next()) {
                    TematskaCelina tematskaCelina = new TematskaCelina();
                    tematskaCelina.setTematskaCelinaId(rs3.getInt("tematska_celinaId"));
                    tematskaCelina.setTipNastaveId(rs3.getInt("tip_nastaveId"));
                    tematskaCelina.setPredmetId(rs3.getInt("predmetId"));
                    tematskaCelina.setNaziv(rs3.getString("naziv"));
                    tematskaCelina.setOpis(rs3.getString("opis"));

                    sadrzajTematskeCeline.add(tematskaCelina);

                }

                predmet.setSadrzajTematskeCeline(sadrzajTematskeCeline);

                //ucitavanje udzbenika za predmet
                String upit4 = "SELECT * FROM udzbenik WHERE predmetId=?";
                PreparedStatement ps4 = dbbr.getConnection().prepareStatement(upit4);
                ps4.setInt(1, predmetId);
                List<Udzbenik> udzbenici = new ArrayList<>();

                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    Udzbenik udzbenik = new Udzbenik();
                    udzbenik.setUdzbenikId(rs4.getInt("udzbenikId"));
                    udzbenik.setNaziv(rs4.getString("naziv"));
                    udzbenik.setOsobeUVeziSaUdzbenikom(OsobaUVeziSaUdzbenikomDaoImpl.getInstance().vratiOsobeZaUdzbenik(udzbenik.getUdzbenikId()));
                    udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                    udzbenik.setIzdavac(rs.getString("izdavac"));
                    udzbenik.setStampa(rs.getString("stampa"));
                    udzbenik.setTiraz(rs.getInt("tiraz"));
                    udzbenik.setRbrIzdanja(rs.getInt("rbr_izdanja"));
                    udzbenik.setIsbn(rs.getInt("isbn"));

                    udzbenici.add(udzbenik);
                }

                predmet.setUdzbenici(udzbenici);

                String upit5 = "SELECT * FROM nastavnik_na_predmetu WHERE predmetId=?";
                PreparedStatement ps5 = dbbr.getConnection().prepareStatement(upit5);
                ps5.setInt(1, predmetId);

                ResultSet rs5 = ps5.executeQuery();

                List<NastavnikNaPredmetu> nastavniciNaPredmetu = new ArrayList<>();

                while (rs5.next()) {
                    NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();
                    nnp.setNastavnik(NastavnikDaoImpl.getInstance().vratiNastavnikaZaId(rs.getInt("nastavnikId")));
                    nnp.setPredmet(predmet);
                    nnp.setTipNastave(TipNastaveDaoImpl.getInstance().pronadjiTipNastavePoId(rs.getInt("tipNastaveId")));

                    nastavniciNaPredmetu.add(nnp);
                }

                predmet.setNastavnici(nastavniciNaPredmetu);

                rs.close();
                rs3.close();
                rs4.close();
                rs5.close();
                ps.close();
                ps3.close();
                ps4.close();
                ps5.close();

                return predmet;

            } else {
                rs.close();
                ps.close();
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Doodila se greska prilikom pretrazivanja predmeta sa id:" + predmetId + ". Greska:" + e.getMessage());
        }

    }

    public Predmet kreirajPredmet(Predmet predmet) throws Exception {
        try {
            String upit = "INSERT INTO predmet(predmetId,naziv,br_casova_predavanja_nedeljno,br_casova_vezbi_nedeljno,ostali_casovi,drugi_oblici_nastave,studijski_istrazivacki_rad,cilj,ishod,uslov,vrsta_i_nivo_studija,sadrzaj_tekst)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, predmet.getPredmetId());
            ps.setString(2, predmet.getNaziv());
            ps.setInt(3, predmet.getBrCasovaPredavanjaNedeljno());
            ps.setInt(4, predmet.getBrCasovaVezbiNedeljno());
            ps.setInt(5, predmet.getOstaliCasovi());
            ps.setString(6, predmet.getDrugiObliciNastave());
            ps.setString(7, predmet.getStudijskiIstrazivackiRad());
            ps.setString(8, predmet.getCilj());
            ps.setString(9, predmet.getIshod());
            ps.setString(10, predmet.getUslov());
            ps.setInt(11, predmet.getVrstaINivoStudija().getVrstaINivoId());
            ps.setString(12, predmet.getSadrzajTekst());

            ps.executeUpdate();

            String upit2 = "INSERT INTO nastavnik_na_predmetu(nastavnikId,predmetId,tipNastaveId) VALUES(?,?,?)";
            PreparedStatement ps2 = dbbr.getConnection().prepareStatement(upit2);
            for (NastavnikNaPredmetu nastavnikNaPredmetu : predmet.getNastavnici()) {
                ps2.setInt(1, nastavnikNaPredmetu.getNastavnik().getNastavnikId());
                ps2.setInt(2, predmet.getPredmetId());
                ps2.setInt(3, nastavnikNaPredmetu.getTipNastave().getTipNastaveId());

                ps2.executeUpdate();
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
            String upit4 = "INSERT INTO udzbenik_na_predmetu(udzbenikId,predmetId) VALUES(?,?)";
            PreparedStatement ps4 = dbbr.getConnection().prepareStatement(upit4);
            for (Udzbenik udzbenik : predmet.getUdzbenici()) {
                ps4.setInt(1, udzbenik.getUdzbenikId());
                ps4.setInt(2, predmet.getPredmetId());

                ps4.executeUpdate();
            }

            dbbr.getConnection().commit();
            System.out.println("Predmet sa id:" + predmet.getPredmetId() + " uspesno sacuvan u bazi!");

            ps.close();
            ps2.close();
            //ps3.close();
            ps4.close();

            return pronadjiPredmetPoId(predmet.getPredmetId());

        } catch (Exception e) {
            dbbr.getConnection().rollback();
            throw new Exception("Dogodila se greska prilikom cuvanja predmeta u bazi.Greska:" + e.getMessage());
        }
    }

}
