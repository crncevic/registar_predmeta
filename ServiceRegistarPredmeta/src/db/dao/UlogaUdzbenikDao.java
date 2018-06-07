/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.UlogaUdzbenik;

/**
 *
 * @author Petar
 */
public abstract class UlogaUdzbenikDao extends AbstractDao {

    public UlogaUdzbenikDao() throws Exception {
        super();
    }
    
     public abstract UlogaUdzbenik nadjiUloguNaUdzbenikuZaId(int ulogaId) throws Exception;

}
