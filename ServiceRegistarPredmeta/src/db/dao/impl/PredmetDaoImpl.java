/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.PredmetDao;
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
                rs.close();
                rs3.close();
                rs4.close();
                ps.close();
                ps3.close();
                ps4.close();

                return predmet;

            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Doodila se greska prilikom pretrazivanja predmeta sa id:" + predmetId + ". Greska:" + e.getMessage());
        }

    }

}
