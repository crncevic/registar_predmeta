/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.Udzbenik;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class UdzbenikDao extends AbstractDao {

    public UdzbenikDao() throws Exception {
        super();
    }

    abstract public List<Udzbenik> vratiSveUdzbenike() throws Exception;

    abstract public Udzbenik kreirajUdzbenik(Udzbenik udzbenik) throws Exception;

    abstract public Udzbenik azurirajUdzbenik(Udzbenik udzbenik) throws Exception;

    abstract public Udzbenik obrisiUdzbenik(int id) throws Exception;

    abstract public Udzbenik pronadjiUdzbenikPoId(int udzbenikId) throws Exception;

    abstract public Udzbenik pronadjiUdzbenikPoNazivu(String naziv) throws Exception;
}
