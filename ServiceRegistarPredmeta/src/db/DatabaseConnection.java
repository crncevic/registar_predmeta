/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constants.Constants;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Petar
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws Exception {

        try {
            connection = DriverManager.getConnection(
                    SettingsLoader.getInstance().getValue(Constants.URL),
                    SettingsLoader.getInstance().getValue(Constants.USER),
                    SettingsLoader.getInstance().getValue(Constants.PASSWORD));
            connection.setAutoCommit(false);
            System.out.println("Konekcija uspesno uspostavljena");
        } catch (Exception e) {
            throw new Exception("Neuspesno uspostavljanje konekcije. Greska: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() throws Exception {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
