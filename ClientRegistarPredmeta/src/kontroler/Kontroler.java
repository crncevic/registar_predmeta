/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import db.DbBroker;
import domen.Autor;
import domen.Korisnik;
import domen.Recenzent;
import domen.Udzbenik;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Petar
 */
public class Kontroler {

    private static Kontroler instance;
    private DbBroker dbBroker;

    private Kontroler() throws Exception {
        dbBroker = DbBroker.getInstance();
    }

    public static Kontroler getInstance() throws Exception {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public List<Udzbenik> vratiSveUdzbenike() throws Exception {

        return dbBroker.vratiSveUdzbenike();

    }

    public Udzbenik kreirajUdzbenik(Udzbenik udzbenik) throws Exception {

        return dbBroker.kreirajUdzbenik(udzbenik);

    }

    public Udzbenik AzurirajUdzbenik(Udzbenik udzbenik) throws Exception {

        return dbBroker.azurirajUdzbenik(udzbenik);

    }

    public Udzbenik pronadjiUdzbenikPoId(int id) throws Exception {

        return dbBroker.pronadjiUdzbenikPoId(id);

    }

    public Udzbenik pronadjiUdzbenikePoNazivu(String naziv) throws Exception {

        return dbBroker.pronadjiUdzbenikPoNazivu(naziv);

    }

    public Udzbenik ObrisiUdzbenik(int id) throws Exception {

        return dbBroker.obrisiUdzbenik(id);

    }

    public List<Autor> vratiSveAutoreZaUdzbenik(int udzbenikId) throws SQLException {
        return dbBroker.vratiAutoreZaUdzbenik(udzbenikId);
    }

    public List<Recenzent> vratiSveRecenzenteZaUdzbenik(int udzbenikId) throws SQLException {
        return dbBroker.vratiRecenzenteZaUdzbenik(udzbenikId);
    }

    public Korisnik vratiKorisnika(String username, String password) throws Exception {
        return dbBroker.vratiKorisnika(username, password);
    }
}
