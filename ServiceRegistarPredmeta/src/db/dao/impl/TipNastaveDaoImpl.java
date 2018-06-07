/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.TipNastaveDao;
import domen.TipNastave;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class TipNastaveDaoImpl extends TipNastaveDao {

    private static TipNastaveDaoImpl instance;

    private TipNastaveDaoImpl() throws Exception {
        super();
    }

    public static TipNastaveDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new TipNastaveDaoImpl();
        }
        return instance;
    }

    @Override
    public TipNastave pronadjiTipNastavePoId(int tipId) throws Exception {
        try {
            String upit = "SELECT * FROM tip_nastave WHERE tip_nastaveId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, tipId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TipNastave tn = new TipNastave();
                tn.setTipNastaveId(rs.getInt("tip_nastaveId"));
                tn.setNaziv(rs.getString("naziv"));

                rs.close();
                ps.close();

                return tn;
            }

            rs.close();
            ps.close();

            return null;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja tipa nastave.Greska:" + e.getMessage());

        }
    }

}
