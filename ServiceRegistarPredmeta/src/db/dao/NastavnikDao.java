/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.Nastavnik;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class NastavnikDao extends AbstractDao {

    public NastavnikDao() throws Exception {
        super();
    }

    public abstract List<Nastavnik> vratiSveNastavnike() throws Exception;

    public abstract Nastavnik vratiNastavnikaZaId(int nastavnikId) throws Exception;
}
