/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

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
public class OsobaUVeziSaUdzbenikomDaoImpl extends AbstractDao {

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

    public synchronized List<OsobaUVeziSaUdzbenikom> vratiOsobeZaUdzbenik(int udzbenikId) throws SQLException, Exception {
        try {

            String upit = "SELECT * FROM osoba_u_vezi_sa_udzbenikom where udzbenikId=?";
            List<OsobaUVeziSaUdzbenikom> osobeUVeziSaUdzbenikom = new ArrayList<>();
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, udzbenikId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
                 OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom = (OsobaUVeziSaUdzbenikom) ouvsu.napraviDomenskiObjekat(rs);
                osobaUVeziSaUdzbenikom.setUlogaUdzbenik(UlogaUdzbenikDaoImpl.getInstance().vratiPoId(osobaUVeziSaUdzbenikom.getUlogaUdzbenik().getUlogaId()));

                osobeUVeziSaUdzbenikom.add(osobaUVeziSaUdzbenikom);
            }

            rs.close();
            ps.close();

            return osobeUVeziSaUdzbenikom;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom vracanja svih osoba vezanih za udzbenik.Greska:" + ex.getMessage());
        }
    }

    @Override
    public synchronized OsobaUVeziSaUdzbenikom vratiPoId(int osobaId) throws Exception {
        try {
            String upit = "SELECT * FROM osoba_u_vezi_sa_udzbenikom WHERE osobaId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, osobaId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();

                rs.close();
                ps.close();

                OsobaUVeziSaUdzbenikom osobaUVeziSaUdzbenikom = (OsobaUVeziSaUdzbenikom) ouvsu.napraviDomenskiObjekat(rs);
                osobaUVeziSaUdzbenikom.setUlogaUdzbenik(UlogaUdzbenikDaoImpl.getInstance().vratiPoId(osobaUVeziSaUdzbenikom.getUlogaUdzbenik().getUlogaId()));
            }

            rs.close();
            ps.close();
            return null;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja osobe koja je u vezi sa udzbenikom sa id:" + osobaId + " .Greska:" + ex.getMessage());
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
