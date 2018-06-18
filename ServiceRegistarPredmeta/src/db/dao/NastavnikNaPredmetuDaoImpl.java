/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.NastavnikNaPredmetu;
import domen.OpstiDomenskiObjekat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public class NastavnikNaPredmetuDaoImpl extends AbstractDao {

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

    public synchronized NastavnikNaPredmetu vratiSveNastavnikeNaPredmetuZaPredmetId(int predmetID) throws Exception {
        try {
            String upit = "SELECT * FROM nastavnik_na_predmetu WHERE predmetId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, predmetID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                NastavnikNaPredmetu nnp = new NastavnikNaPredmetu();

                rs.close();
                ps.close();

                NastavnikNaPredmetu nastavnikNaPredmetu = (NastavnikNaPredmetu) nnp.napraviDomenskiObjekat(rs);
                nastavnikNaPredmetu.setPredmet(PredmetDaoImpl.getInstance().vratiPoId(nastavnikNaPredmetu.getPredmet().getPredmetId()));
                nastavnikNaPredmetu.setTipNastave(TipNastaveDaoImpl.getInstance().vratiPoId(nastavnikNaPredmetu.getTipNastave().getTipNastaveId()));
            }

            rs.close();
            ps.close();
            return null;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom vracanja svih nasavnika na premdetu. Greska:" + ex.getMessage());
        }
    }

    @Override
    public OpstiDomenskiObjekat vratiPoId(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
