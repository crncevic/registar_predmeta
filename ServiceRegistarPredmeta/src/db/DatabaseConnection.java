/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import settings.util.SettingsLoader;

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
                    SettingsLoader.getInstance().getValue("url"),
                    SettingsLoader.getInstance().getValue("user"),
                    SettingsLoader.getInstance().getValue("password"));
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
