/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.NastavnikNaPredmetu;

/**
 *
 * @author Petar
 */
public abstract class NastavnikNaPredmetuDao extends AbstractDao {

    public NastavnikNaPredmetuDao() throws Exception {
        super();
    }

    public abstract NastavnikNaPredmetu vratiSveNastavnikeNaPredmetuZaPredmetId(int predmetID) throws Exception;

}
