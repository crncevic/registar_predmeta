/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.Predmet;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class PredmetDao extends AbstractDao {

    public PredmetDao() throws Exception {
        super();
    }

    public abstract Predmet kreirajPredmet(Predmet predmet) throws Exception;

    public abstract Predmet pronadjiPredmetPoId(int predmetId) throws Exception;

    public abstract List<Predmet> vratiSvePredmete() throws Exception;

    public abstract Predmet azurirajPredmet(Predmet predmet) throws Exception;

}
