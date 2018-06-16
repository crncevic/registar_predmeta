/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.PredmetNaStudijskomProgramuDao;
import domen.PredmetNaStudijskomProgramu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class PredmetNaStudijskomProgramuDaoImpl extends PredmetNaStudijskomProgramuDao {

    private static PredmetNaStudijskomProgramuDaoImpl instance;

    private PredmetNaStudijskomProgramuDaoImpl() throws Exception {
        super();
    }

    public static PredmetNaStudijskomProgramuDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new PredmetNaStudijskomProgramuDaoImpl();
        }
        return instance;
    }

    @Override
    public PredmetNaStudijskomProgramu kreirajPredmetNaStudijskomProgramu(PredmetNaStudijskomProgramu predmetNaStudijskomProgramu) throws Exception {
        try {
            String upit = "INSERT INTO predmet_na_studijskom_programu(predmetId,studijski_programId,espb,statusId) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, predmetNaStudijskomProgramu.getPredmet().getPredmetId());
            ps.setInt(2, predmetNaStudijskomProgramu.getStudijskiProgram().getStudijskiProgramId());
            ps.setInt(3, predmetNaStudijskomProgramu.getEspb());
            ps.setInt(4, predmetNaStudijskomProgramu.getStatus().getStatusId());

            ps.executeUpdate();
            dbbr.commitTransakcije();
            ps.close();

            return vratiPredmetNaStudijskomProgramuZaId(predmetNaStudijskomProgramu.getPredmet().getPredmetId(), predmetNaStudijskomProgramu.getStudijskiProgram().getStudijskiProgramId());
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom kreiranja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

    public PredmetNaStudijskomProgramu vratiPredmetNaStudijskomProgramuZaId(int predmetId, int studijskiProgramId) throws Exception {
        try {
            String upit = "SELECT * FROM predmet_na_studijskom_programu WHERE predmetId=? AND studijski_programId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, predmetId);
            ps.setInt(2, studijskiProgramId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PredmetNaStudijskomProgramu pnsp = new PredmetNaStudijskomProgramu();
                pnsp.setPredmet(PredmetDaoImpl.getInstance().pronadjiPredmetPoId(rs.getInt("predmetId")));
                pnsp.setStatus(StatusDaoImpl.getInstance().vratiStatusZaId(rs.getInt("statusId")));
                pnsp.setStudijskiProgram(StudijskiProgramDaoImpl.getInstance().vratiStudijkiProgramZaId(rs.getInt("studijski_programId")));
                pnsp.setEspb(rs.getInt("espb"));

                rs.close();
                ps.close();

                return pnsp;

            }

            rs.close();
            ps.close();
            return null;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pronalazenja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

    @Override
    public List<PredmetNaStudijskomProgramu> vratiPredmetZaStudijskiProgram(int studijskiProgramId) throws Exception {
        try {

            String upit = "SELECT * FROM predmet_na_studijskom_programu WHERE studijski_programId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, studijskiProgramId);
            List<PredmetNaStudijskomProgramu> predmeti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PredmetNaStudijskomProgramu pnsp = new PredmetNaStudijskomProgramu();

                pnsp.setPredmet(PredmetDaoImpl.getInstance().pronadjiPredmetPoId(rs.getInt("predmetId")));
                pnsp.setStatus(StatusDaoImpl.getInstance().vratiStatusZaId(rs.getInt("statusId")));
                pnsp.setStudijskiProgram(StudijskiProgramDaoImpl.getInstance().vratiStudijkiProgramZaId(rs.getInt("studijski_programId")));
                pnsp.setEspb(rs.getInt("espb"));

                predmeti.add(pnsp);
            }

            rs.close();
            ps.close();

            return predmeti;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pronalazenja predmeta za studijski program sa id:" + studijskiProgramId + ". Greska:" + e.getMessage());
        }
    }

    @Override
    public PredmetNaStudijskomProgramu azurirajPredmetNaStudijskomProgramu(PredmetNaStudijskomProgramu pnsp) throws Exception {
        try {
            String upit = "UPDATE predmet_na_studijskom_programu SET espb=?,statusId=? "
                    + " WHERE predmetId=? AND studijski_programId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, pnsp.getEspb());
            ps.setInt(2, pnsp.getStatus().getStatusId());
            ps.setInt(3, pnsp.getPredmet().getPredmetId());
            ps.setInt(4, pnsp.getStudijskiProgram().getStudijskiProgramId());

            ps.executeUpdate();
            dbbr.commitTransakcije();

            ps.close();

            return vratiPredmetNaStudijskomProgramuZaId(pnsp.getPredmet().getPredmetId(), pnsp.getStudijskiProgram().getStudijskiProgramId());
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

    @Override
    public PredmetNaStudijskomProgramu obrisiPredmetNaStudijskomProgramu(int predmetId, int studijskiProgramId) throws Exception {
        try {
            PredmetNaStudijskomProgramu pnsp = vratiPredmetNaStudijskomProgramuZaId(predmetId, studijskiProgramId);

            String upit = "DELETE FROM predmet_na_studijskom_programu WHERE predmetId=? AND studijski_programId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, predmetId);
            ps.setInt(2, studijskiProgramId);

            ps.executeUpdate();

            dbbr.commitTransakcije();
            ps.close();

            return pnsp;
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

}
