/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.Korisnik;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class KorisnikDao extends AbstractDao {

    public KorisnikDao() throws Exception {
        super();
    }

    public  abstract Korisnik vratiKorisnika(String username, String password) throws Exception;

    public abstract List<Korisnik> vratiSveKorisnike() throws Exception;
}
