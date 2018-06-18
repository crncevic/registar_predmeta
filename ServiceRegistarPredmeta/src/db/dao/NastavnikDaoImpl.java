/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;


import domen.Nastavnik;
import domen.OpstiDomenskiObjekat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class NastavnikDaoImpl extends AbstractDao {

    private static NastavnikDaoImpl instance;

    private NastavnikDaoImpl() throws Exception {
        super();
    }

    public static NastavnikDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new NastavnikDaoImpl();
        }
        return instance;
    }

    public synchronized List<Nastavnik> vratiSveNastavnike() throws Exception {
        try {
            String upit = "SELECT * FROM nastavnik";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<Nastavnik> nastavnici = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Nastavnik nastavnik = new Nastavnik();
                nastavnici.add((Nastavnik) nastavnik.napraviDomenskiObjekat(rs));
            }

            rs.close();
            ps.close();

            return nastavnici;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja svih nastavnika. Greska:" + e.getMessage());
        }
    }

    @Override
    public synchronized Nastavnik vratiPoId(int nastavnikId) throws Exception {
        try {
            Nastavnik nastavnik = new Nastavnik();
            nastavnik.setNastavnikId(nastavnikId);
            return (Nastavnik) dbbr.vratiPoId(nastavnik);

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom ucitavanja  nastavnika sa id:" + nastavnikId + ". Greska:" + e.getMessage());
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
