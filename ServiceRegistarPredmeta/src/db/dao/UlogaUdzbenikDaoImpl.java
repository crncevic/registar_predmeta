/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.OpstiDomenskiObjekat;
import domen.UlogaUdzbenik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class UlogaUdzbenikDaoImpl extends AbstractDao {

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

    @Override
    public synchronized UlogaUdzbenik vratiPoId(int ulogaId) throws Exception {
        try {
            UlogaUdzbenik uu = new UlogaUdzbenik();
            uu.setUlogaId(ulogaId);
            return (UlogaUdzbenik) dbbr.vratiPoId(uu);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja uloge_udzbenik  sa id:" + ulogaId + " .Greska:" + e.getMessage());
        }
    }

    public synchronized UlogaUdzbenik vratiUloguNaUdzbenikuZaNaziv(String naziv) throws Exception {

        try {
            String upit = "SELECT * FROM uloga_udzbenik WHERE naziv=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setString(1, naziv);

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
            throw new Exception("Dogodila se greska prilikom pretrazivanja uloge_udzbenik  sa nazivom" + naziv + " .Greska:" + e.getMessage());
        }

    }

    @Override
    public OpstiDomenskiObjekat kreiraj(OpstiDomenskiObjekat odo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OpstiDomenskiObjekat azuriraj(OpstiDomenskiObjekat odo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OpstiDomenskiObjekat obrisi(OpstiDomenskiObjekat odo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}