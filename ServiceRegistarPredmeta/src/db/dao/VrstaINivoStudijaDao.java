/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.VrstaINivoStudija;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class VrstaINivoStudijaDao extends AbstractDao {

    public VrstaINivoStudijaDao() throws Exception {
        super();
    }

    public abstract VrstaINivoStudija vratiVrstuINivoStudijaZaId(int vrstaId) throws Exception;
    
    public abstract List<VrstaINivoStudija> vratiSveVrsteStudija() throws Exception;

}
