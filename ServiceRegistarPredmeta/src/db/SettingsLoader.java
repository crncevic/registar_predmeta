/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Petar
 */
public class SettingsLoader {

    private static SettingsLoader instance;
    private Properties propeties;

    private SettingsLoader() throws FileNotFoundException, IOException {
        loadProperties();
    }

    private void loadProperties() throws FileNotFoundException, IOException {
        try {
            FileInputStream fis = new FileInputStream("db.properties");
            propeties = new Properties();
            propeties.load(fis);
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("Dogodila se greska prilikom ucitavanja fajla db.properties. Greska:" + fnfe.getMessage());
        }
    }
    
    public String getValue(String key){
        return propeties.getProperty(key);
    }
    
    public void setValue(String key,String value){
        propeties.setProperty(key, value);
    }

    public static SettingsLoader getInstance() throws IOException {
        if (instance == null) {
            instance = new SettingsLoader();
        }
        return instance;
    }
}
