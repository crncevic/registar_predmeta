/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.PredmetNaStudijskomProgramu;

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
}
