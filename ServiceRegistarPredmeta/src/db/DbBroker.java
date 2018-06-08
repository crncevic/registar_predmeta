/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constants.Constants;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

    

    
    

    
       
    

   
  
}
