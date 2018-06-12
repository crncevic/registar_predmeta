/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.StudijskiProgramDao;
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
public class StudijskiProgramDaoImpl extends StudijskiProgramDao {

    private static StudijskiProgramDaoImpl instance;

    private StudijskiProgramDaoImpl() throws Exception {
        super();
    }

    public static StudijskiProgramDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new StudijskiProgramDaoImpl();
        }
        return instance;
    }

    @Override
    public List<StudijskiProgram> vratiSveStudijskePrograme() throws Exception {
        try {
            String upit = "SELECT * FROM studijski_program";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<StudijskiProgram> studijskiProgrami = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StudijskiProgram studijskiProgram = new StudijskiProgram();
                studijskiProgram.setStudijskiProgramId(rs.getInt("studijskiProgramId"));
                studijskiProgram.setNaziv(rs.getString("naziv"));

                studijskiProgrami.add(studijskiProgram);
            }

            rs.close();
            ps.close();

            return studijskiProgrami;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja svih studijskih programa. Greska:" + e.getMessage());
        }
    }

    @Override
    public StudijskiProgram vratiStudijkiProgramZaId(int studijskiProgramId) throws Exception {
        try {

            String upit = "SELECT * FROM studijski_program WHERE studijskiProgramId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, studijskiProgramId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                StudijskiProgram studijskiProgram = new StudijskiProgram();
                studijskiProgram.setStudijskiProgramId(rs.getInt("studijskiProgramId"));
                studijskiProgram.setNaziv(rs.getString("naziv"));

                rs.close();
                ps.close();

                return studijskiProgram;
            }

            rs.close();
            ps.close();

            return null;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja studijskog programa sa id:" + studijskiProgramId + ". Greska:" + e.getMessage());
        }
    }

   

}
