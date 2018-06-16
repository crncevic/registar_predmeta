/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.OsobaUVeziSaUdzbenikomDao;
import domen.Nastavnik;
import domen.OpstiDomenskiObjekat;
import domen.OsobaUVeziSaUdzbenikom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class OsobaUVeziSaUdzbenikomDaoImpl extends OsobaUVeziSaUdzbenikomDao {

    private static OsobaUVeziSaUdzbenikomDaoImpl instance;

    private OsobaUVeziSaUdzbenikomDaoImpl() throws Exception {
        super();
    }

    public static OsobaUVeziSaUdzbenikomDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new OsobaUVeziSaUdzbenikomDaoImpl();
        }
        return instance;
    }

    @Override
    public List<OsobaUVeziSaUdzbenikom> vratiOsobeZaUdzbenik(int udzbenikId) throws SQLException, Exception {
        try {

            String upit = "SELECT * FROM osoba_u_vezi_sa_udzbenikom where udzbenikId=?";
            List<OsobaUVeziSaUdzbenikom> osobeUVeziSaUdzbenikom = new ArrayList<>();
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, udzbenikId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();

                osobeUVeziSaUdzbenikom.add((OsobaUVeziSaUdzbenikom) ouvsu.napraviDomenskiObjekat(rs));
            }

            rs.close();
            ps.close();

            return osobeUVeziSaUdzbenikom;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom vracanja svih osoba vezanih za udzbenik.Greska:" + ex.getMessage());
        }
    }

    @Override
    public OsobaUVeziSaUdzbenikom vratiOsobuUVeziSaUdbenikomZaId(int osobaId) throws Exception {
        try {
            String upit = "SELECT * FROM osoba_u_vezi_sa_udzbenikom WHERE osobaId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, osobaId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();

                rs.close();
                ps.close();

                return (OsobaUVeziSaUdzbenikom) ouvsu.napraviDomenskiObjekat(rs);
            }

            rs.close();
            ps.close();
            return null;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja osobe koja je u vezi sa udzbenikom sa id:" + osobaId + " .Greska:" + ex.getMessage());
        }
    }

}
