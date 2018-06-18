/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;


import domen.OpstiDomenskiObjekat;
import domen.VrstaINivoStudija;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VrstaINivoStudijaDaoImpl extends AbstractDao {

    private static VrstaINivoStudijaDaoImpl instance;

    private VrstaINivoStudijaDaoImpl() throws Exception {
        super();
    }

    public static VrstaINivoStudijaDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new VrstaINivoStudijaDaoImpl();
        }
        return instance;
    }


    public synchronized VrstaINivoStudija vratiPoId(int vrstaId) throws Exception {
        try {
            VrstaINivoStudija vins = new VrstaINivoStudija();
            vins.setVrstaINivoId(vrstaId);
            return (VrstaINivoStudija) dbbr.vratiPoId(vins);
        } catch (Exception ex) {
            System.out.println("Dogodila se greska prilikom vracanja vrste i nivoa studija za id:" + vrstaId);
            throw new Exception("Dogodila se greska prilikom vracanja vrste i nivoa studija za id:" + vrstaId + ". Greska:" + ex.getMessage());
        }
    }


    public synchronized List<VrstaINivoStudija> vratiSveVrsteStudija() throws Exception {
        try {
            String upit = "SELECT * FROM vrsta_i_nivo_studija";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<VrstaINivoStudija> vrste = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VrstaINivoStudija vins = new VrstaINivoStudija();

                vins.setVrstaINivoId(rs.getInt("vrstaId"));
                vins.setNaziv(rs.getString("naziv"));

                vrste.add(vins);
            }

            rs.close();
            ps.close();

            return vrste;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja svih vrsta i nivoa studija. Greska:" + e.getMessage());
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
