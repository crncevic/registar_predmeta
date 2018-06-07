/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.KorisnikDao;
import domen.Korisnik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class KorisnikDaoImpl extends KorisnikDao {

    private static KorisnikDaoImpl instance;

    private KorisnikDaoImpl() throws Exception {
        super();
    }

    public static KorisnikDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new KorisnikDaoImpl();
        }
        return instance;
    }

    @Override
    public Korisnik vratiKorisnika(String username, String password) throws Exception {

        try {
            String query = "SELECT * FROM korisnik WHERE username=? AND password=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Korisnik korisnik = new Korisnik();
                korisnik.setKorisnikId(rs.getInt("korisnikId"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setUsername(rs.getString("username"));
                korisnik.setPassword(rs.getString("password"));
                rs.close();
                ps.close();

                return korisnik;
            } else {
                rs.close();
                ps.close();
                return null;
            }
        } catch (Exception e) {
            throw e;
        }

    }

}
