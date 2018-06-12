/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.Status;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class StatusDao extends AbstractDao{

    public StatusDao() throws Exception {
        super();
    }
    
    public abstract List<Status> vratiSveStatuse() throws Exception;
    
    public abstract Status vratiStatusZaId(int statusId) throws Exception;
    
    
    
}
