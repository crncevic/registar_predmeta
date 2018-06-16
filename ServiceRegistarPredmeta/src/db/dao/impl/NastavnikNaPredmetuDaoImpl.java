/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.NastavnikNaPredmetuDao;
import domen.NastavnikNaPredmetu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class NastavnikNaPredmetuDaoImpl extends NastavnikNaPredmetuDao {

    private static NastavnikNaPredmetu instance;

    private NastavnikNaPredmetuDaoImpl() throws Exception {
        super();
    }

    public static NastavnikNaPredmetu getInstance() {
        if (instance == null) {
            instance = new NastavnikNaPredmetu();
        }
        return instance;
    }

    @Override
    public NastavnikNaPredmetu vratiSveNastavnikeNaPredmetuZaPredmetId(int predmetID) throws Exception {
        String upit = "SELECT * FROM nastavnik_na_predmetu WHERE predmetId=?";
        PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
        ps.setInt(1, predmetID);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();
            nnp.setPredmet(PredmetDaoImpl.getInstance().pronadjiPredmetPoId(rs.getInt("predmetId")));
            nnp.setTipNastave(TipNastaveDaoImpl.getInstance().pronadjiTipNastavePoId(rs.getInt("tipNastaveId")));
            nnp.setNastavnik(NastavnikDaoImpl.getInstance().vratiNastavnikaZaId(rs.getInt("nastavnikId")));

            rs.close();
            ps.close();

            return nnp;
        }

        rs.close();
        ps.close();
        return null;
    }

}
