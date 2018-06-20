/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petar
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected DbBroker dbbr;

//    public AbstractGenericOperation(DatabaseRepository db) {
//        this.db = db;
//    }
    public ApstraktnaGenerickaOperacija() {
        try {
            dbbr = DbBroker.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(ApstraktnaGenerickaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object opsteIzvrsenje(OpstiDomenskiObjekat odo) throws Exception {
        try {
            izvrsiValidaciju(odo);
            try {

                return izvrsi(odo);

            } catch (Exception e) {

                throw new Exception("Greska: " + e.getMessage());
            }
        } catch (Exception e) {
            throw e;
        }

    }

    protected abstract void izvrsiValidaciju(OpstiDomenskiObjekat odo) throws Exception;

    protected abstract Object izvrsi(OpstiDomenskiObjekat odo) throws Exception;

    protected void commitTransakcije() throws Exception {
        dbbr.commitTransakcije();
    }

    protected void rollbackTransakcije() throws Exception {
        dbbr.rollackTransakcije();
    }
}
