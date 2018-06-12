/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.StudijskiProgram;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class StudijskiProgramDao extends AbstractDao {

    public StudijskiProgramDao() throws Exception {
        super();
    }

    public abstract List<StudijskiProgram> vratiSveStudijskePrograme() throws Exception;

    public abstract StudijskiProgram vratiStudijkiProgramZaId(int studijskiProgramId) throws Exception;

}
