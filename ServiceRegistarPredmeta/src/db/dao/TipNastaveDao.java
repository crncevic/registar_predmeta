/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.TipNastave;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class TipNastaveDao extends AbstractDao {

    public TipNastaveDao() throws Exception {
        super();
    }

    public abstract TipNastave pronadjiTipNastavePoId(int tipId) throws Exception;
    
    public abstract List<TipNastave> vratiSveTipoveNastave() throws Exception;

}
