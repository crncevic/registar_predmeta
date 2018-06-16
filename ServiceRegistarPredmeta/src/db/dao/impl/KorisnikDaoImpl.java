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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class KorisnikDaoImpl extends KorisnikDao {

    private static KorisnikDaoImpl instance;

    private KorisnikDaoImpl() throws Exception {
        super();
    }

    public  static KorisnikDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new KorisnikDaoImpl();
        }
        return instance;
    }

    @Override
    public synchronized Korisnik vratiKorisnika(String username, String password) throws Exception {

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

            throw new Exception("Dogodila se greska prilikom pretrazivanja korisnika.Greska: " + e.getMessage());
        }

    }

    @Override
    public synchronized List<Korisnik> vratiSveKorisnike() throws Exception {
        try {
           
            String upit = "SELECT * FROM korisnik";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<Korisnik> korisnici = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Korisnik korisnik = new Korisnik();
                korisnik.setKorisnikId(rs.getInt("korisnikId"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setUsername(rs.getString("username"));
                korisnik.setPassword(rs.getString("password"));

                korisnici.add(korisnik);
            }

            rs.close();
            ps.close();

            return korisnici;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja svih korisnika.Greska: " + e.getMessage());
        }
    }

}
