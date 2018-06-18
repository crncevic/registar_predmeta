/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;

/**
 *
 * @author Petar
 */
public abstract class AbstractDao {

    protected DbBroker dbbr;

    public AbstractDao() throws Exception {
        dbbr = DbBroker.getInstance();
    }

    public abstract OpstiDomenskiObjekat vratiPoId(int id) throws Exception;

    public abstract OpstiDomenskiObjekat kreiraj(OpstiDomenskiObjekat odo) throws Exception;

    public abstract OpstiDomenskiObjekat azuriraj(OpstiDomenskiObjekat odo) throws Exception;

    public abstract OpstiDomenskiObjekat obrisi(OpstiDomenskiObjekat odo) throws Exception;

}
