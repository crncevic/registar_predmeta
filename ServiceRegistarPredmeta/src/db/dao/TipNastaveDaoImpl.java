/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;


import domen.OpstiDomenskiObjekat;
import domen.TipNastave;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class TipNastaveDaoImpl extends AbstractDao {

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
    public synchronized TipNastave vratiPoId(int tipId) throws Exception {
        try {
            TipNastave tipNastave = new TipNastave();
            tipNastave.setTipNastaveId(tipId);

            return (TipNastave) dbbr.vratiPoId(tipNastave);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja tipa nastave.Greska:" + e.getMessage());

        }
    }


    public synchronized List<TipNastave> vratiSveTipoveNastave() throws Exception {
        try {
            String upit = "SELECT * FROM tip_nastave";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<TipNastave> tipoviNastave = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TipNastave tn = new TipNastave();
                tn.setTipNastaveId(rs.getInt("tip_nastaveId"));
                tn.setNaziv(rs.getString("naziv"));

                tipoviNastave.add(tn);
            }

            rs.close();
            ps.close();
            return tipoviNastave;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja svih tipova nastave.Greska:" + e.getMessage());
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