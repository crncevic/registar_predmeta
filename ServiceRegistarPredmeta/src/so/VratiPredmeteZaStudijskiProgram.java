/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;


import domen.OpstiDomenskiObjekat;
import domen.Predmet;
import domen.PredmetNaStudijskomProgramu;
import domen.Status;
import domen.StudijskiProgram;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class VratiPredmeteZaStudijskiProgram extends ApstraktnaGenerickaOperacija{

    @Override
    protected void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception {
        
    }

    @Override
    protected Object izvrsi(OpstiDomenskiObjekat odo) throws Exception {
        StudijskiProgram sp1 = (StudijskiProgram) odo;
        try {

            String upit = "SELECT * FROM predmet_na_studijskom_programu WHERE studijski_programId=?";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            ps.setInt(1, sp1.getStudijskiProgramId());
            List<PredmetNaStudijskomProgramu> predmeti = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            ApstraktnaGenerickaOperacija vratiStatusZaId = new VratiStatusZaId();
            ApstraktnaGenerickaOperacija vratiStudijskiProgramZaId = new VratiStudijskiProgramZaId();
            
            while (rs.next()) {
                PredmetNaStudijskomProgramu pnsp = new PredmetNaStudijskomProgramu();

                ApstraktnaGenerickaOperacija vratiPredmetZaId= new VratiPredmetZaId();
                Predmet predmet = new Predmet();
                predmet.setPredmetId(rs.getInt("predmetId"));
                
                pnsp.setPredmet((Predmet) vratiPredmetZaId.opsteIzvrsenje(predmet));
                pnsp.setStatus((Status) vratiStatusZaId.opsteIzvrsenje(new Status(rs.getInt("statusId"), null)));
                pnsp.setStudijskiProgram((StudijskiProgram) vratiStudijskiProgramZaId.opsteIzvrsenje(new StudijskiProgram(rs.getInt("studijski_programId"),null)));
                pnsp.setEspb(rs.getInt("espb"));

                predmeti.add(pnsp);
            }

            rs.close();
            ps.close();
            commitTransakcije();
            return predmeti;

        } catch (Exception e) {
            rollbackTransakcije();
            throw new Exception("Dogodila se greska prilikom pronalazenja predmeta za studijski program sa id:" + sp1.getStudijskiProgramId() + ". Greska:" + e.getMessage());
        }
    }
    
}
