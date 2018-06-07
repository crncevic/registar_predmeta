/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.UlogaUdzbenikDao;
import domen.UlogaUdzbenik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class UlogaUdzbenikDaoImpl extends UlogaUdzbenikDao {

    private static UlogaUdzbenikDaoImpl instance;

    private UlogaUdzbenikDaoImpl() throws Exception {
        super();
    }

    public static UlogaUdzbenikDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new UlogaUdzbenikDaoImpl();
        }
        return instance;
    }

    public UlogaUdzbenik nadjiUloguNaUdzbenikuZaId(int ulogaId) throws Exception {
        try {
            String upit = "SELECT * FROM uloga_udzbenik WHERE ulogaId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, ulogaId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UlogaUdzbenik ulogaUdzbenik = new UlogaUdzbenik();
                ulogaUdzbenik.setUlogaId(rs.getInt("ulogaId"));
                ulogaUdzbenik.setNaziv(rs.getString("naziv"));
                rs.close();
                ps.close();

                return ulogaUdzbenik;
            }
            rs.close();
            ps.close();

            return null;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja uloge_udzbenik  sa id:" + ulogaId + " .Greska:" + e.getMessage());
        }
    }
}
