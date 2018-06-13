/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.PredmetNaStudijskomProgramu;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class PredmetNaStudijskomProgramuDao extends AbstractDao {

    public PredmetNaStudijskomProgramuDao() throws Exception {
        super();
    }

    public abstract PredmetNaStudijskomProgramu kreirajPredmetNaStudijskomProgramu(PredmetNaStudijskomProgramu predmetNaStudijskomProgramu) throws Exception;

    public abstract PredmetNaStudijskomProgramu vratiPredmetNaStudijskomProgramuZaId(int predmetId, int studijskiProgramId) throws Exception;

    public abstract List<PredmetNaStudijskomProgramu> vratiPredmetZaStudijskiProgram(int i) throws Exception;

    public abstract PredmetNaStudijskomProgramu azurirajPredmetNaStudijskomProgramu(PredmetNaStudijskomProgramu pnsp) throws Exception;

    public abstract PredmetNaStudijskomProgramu obrisiPredmetNaStudijskomProgramu(int predmetId, int studijskiProgramId) throws Exception;
}
