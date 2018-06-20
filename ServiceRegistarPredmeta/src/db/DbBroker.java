/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constants.Constants;
import domen.OpstiDomenskiObjekat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class DbBroker {

    private Connection connection;
    private static DbBroker instance;

    private DbBroker() throws Exception {
        ucitajDriver();
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static DbBroker getInstance() throws Exception {
        if (instance == null) {
            instance = new DbBroker();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void ucitajDriver() throws IOException, ClassNotFoundException {
        try {
            Class.forName(SettingsLoader.getInstance().getValue(Constants.DRIVER));
        } catch (ClassNotFoundException cnfe) {
            throw new ClassNotFoundException("Nema odgovarajuceg drajvera");
        }
    }

    public void commitTransakcije() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new SQLException("Neuspesan commit transakcije! Greska: " + e.getMessage());
        }
    }

    public void rollackTransakcije() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new SQLException("Neuspesan rollback transakcije! Greska: " + e.getMessage());
        }
    }

    public void raskiniKonekciju() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new SQLException("Dogodila se greska prilikom zatvaranja konekcije!");
        }
    }

    public Connection vratiKonekciju() {
        return connection;
    }

    public int kreiraj(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "INSERT INTO " + odo.vratiImeKlase() + "(" + odo.vratiNaziveAtributaZaKreiraj() + ") VALUES(" + odo.vratiVrednostiAtributa() + ")";
            PreparedStatement ps = connection.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);

            int brojRedova = ps.executeUpdate();

            if (brojRedova > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                int id = 0;
                if (rs.next()) {
                    id = rs.getInt(1);
                }

                rs.close();
                ps.close();

                return id;
            }

            throw new Exception("Neuspesno kreiranje tabeli " + odo.vratiImeKlase());
        } catch (Exception e) {
            throw new Exception("Neuspesno kreiranje tabeli " + odo.vratiImeKlase());
        }
    }

    public boolean azuriraj(OpstiDomenskiObjekat odo) {
        try {
            String upit = "UPDATE " + odo.vratiImeKlase() + " SET " + odo.postaviVrednostAtributa() + " WHERE " + odo.vratiUslovZaNadjiSlog() + "";
            PreparedStatement ps = connection.prepareStatement(upit);

            int brojRedova = ps.executeUpdate();

            ps.close();

            if (brojRedova > 0) {
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean obrisi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String upit = "DELETE FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
            PreparedStatement ps = connection.prepareStatement(upit);
            int brojRedova = ps.executeUpdate();

            ps.close();
            if (brojRedova > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom brisanja iz tabele " + odo.vratiImeKlase());
        }
    }

    public OpstiDomenskiObjekat vratiPoId(OpstiDomenskiObjekat odo) throws Exception {
        try {

            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
            PreparedStatement ps = connection.prepareStatement(upit);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return odo.napraviDomenskiObjekat(rs);
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja entiteta iz tabele " + odo.vratiImeKlase() + ". Greska" + e.getMessage());
        }
    }

}
