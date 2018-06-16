/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.PredmetNaStudijskomProgramuDao;
import domen.Predmet;
import domen.PredmetNaStudijskomProgramu;
import domen.StudijskiProgram;
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
    public synchronized PredmetNaStudijskomProgramu kreirajPredmetNaStudijskomProgramu(PredmetNaStudijskomProgramu predmetNaStudijskomProgramu) throws Exception {
        try {
            dbbr.kreiraj(predmetNaStudijskomProgramu);

            dbbr.commitTransakcije();

            return vratiPredmetNaStudijskomProgramuZaId(predmetNaStudijskomProgramu.getPredmet().getPredmetId(), predmetNaStudijskomProgramu.getStudijskiProgram().getStudijskiProgramId());
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom kreiranja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

    @Override
    public synchronized PredmetNaStudijskomProgramu vratiPredmetNaStudijskomProgramuZaId(int predmetId, int studijskiProgramId) throws Exception {
        try {

            Predmet p = new Predmet();
            p.setPredmetId(predmetId);
            StudijskiProgram sp = new StudijskiProgram();
            sp.setStudijskiProgramId(studijskiProgramId);
            PredmetNaStudijskomProgramu pnsp = new PredmetNaStudijskomProgramu(p, sp, null, 0);

            return (PredmetNaStudijskomProgramu) dbbr.vratiPoId(pnsp);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pronalazenja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

    @Override
    public synchronized List<PredmetNaStudijskomProgramu> vratiPredmetZaStudijskiProgram(int studijskiProgramId) throws Exception {
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
    public synchronized PredmetNaStudijskomProgramu azurirajPredmetNaStudijskomProgramu(PredmetNaStudijskomProgramu pnsp) throws Exception {
        try {
            dbbr.azuriraj(pnsp);

            dbbr.commitTransakcije();

            return vratiPredmetNaStudijskomProgramuZaId(pnsp.getPredmet().getPredmetId(), pnsp.getStudijskiProgram().getStudijskiProgramId());
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

    @Override
    public synchronized PredmetNaStudijskomProgramu obrisiPredmetNaStudijskomProgramu(int predmetId, int studijskiProgramId) throws Exception {
        try {
            PredmetNaStudijskomProgramu pnsp = vratiPredmetNaStudijskomProgramuZaId(predmetId, studijskiProgramId);

            dbbr.obrisi(pnsp);
            dbbr.commitTransakcije();
            return pnsp;
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja predmeta na studijskom programu. Greska:" + e.getMessage());
        }
    }

}
