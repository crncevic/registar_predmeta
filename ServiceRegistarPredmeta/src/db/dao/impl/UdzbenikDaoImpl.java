/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.UdzbenikDao;
import domen.OsobaUVeziSaUdzbenikom;
import domen.Udzbenik;
import domen.UdzbenikNaPredmetu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class UdzbenikDaoImpl extends UdzbenikDao {

    private static UdzbenikDaoImpl instance;

    private UdzbenikDaoImpl() throws Exception {
        super();
    }

    public static UdzbenikDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new UdzbenikDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Udzbenik> vratiSveUdzbenike() throws Exception {
        try {
            String upit = "SELECT * FROM udzbenik";
            List<Udzbenik> udzbenici = new ArrayList<>();

            Statement statement = dbbr.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);

            while (rs.next()) {
                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                udzbenik.setOsobeUVeziSaUdzbenikom(OsobaUVeziSaUdzbenikomDaoImpl.getInstance().vratiOsobeZaUdzbenik(udzbenik.getUdzbenikId()));
                udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                udzbenik.setIzdavac(rs.getString("izdavac"));
                udzbenik.setStampa(rs.getString("stampa"));
                udzbenik.setRbrIzdanja(rs.getInt("rbr_izdanja"));
                udzbenik.setIsbn(rs.getInt("isbn"));
                udzbenik.setTiraz(rs.getInt("tiraz"));

                udzbenici.add(udzbenik);
            }

            statement.close();
            return udzbenici;

        } catch (Exception e) {

            throw e;
        }
    }

    @Override
    public Udzbenik kreirajUdzbenik(Udzbenik udzbenik) throws Exception {
        try {

            int udzbenikId = dbbr.kreiraj(udzbenik);

            for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                osoba.setUdzbenikId(udzbenikId);
                dbbr.kreiraj(osoba);
            }

            dbbr.commitTransakcije();

            return pronadjiUdzbenikPoId(udzbenikId);
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom kreiranja udzbenika! Greska:" + e.getMessage());
        }
    }

    @Override
    public Udzbenik azurirajUdzbenik(Udzbenik udzbenik) throws Exception {
        try {
            dbbr.azuriraj(udzbenik);

            OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
            ouvsu.setUdzbenikId(udzbenik.getUdzbenikId());
            dbbr.obrisi(ouvsu);

            for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                osoba.setUdzbenikId(udzbenik.getUdzbenikId());
                dbbr.kreiraj(osoba);
            }

            dbbr.commitTransakcije();

            return pronadjiUdzbenikPoId(udzbenik.getUdzbenikId());

        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja udzbenika sa id:"
                    + +udzbenik.getUdzbenikId() + ". Greska: " + e.getMessage());
        }
    }

    @Override
    public Udzbenik obrisiUdzbenik(int udzbenikId) throws Exception {
        try {
            Udzbenik udzbenik = pronadjiUdzbenikPoId(udzbenikId);

            OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
            ouvsu.setUdzbenikId(udzbenikId);
            dbbr.obrisi(ouvsu);

            UdzbenikNaPredmetu unp = new UdzbenikNaPredmetu();
            unp.setUdbenikId(udzbenikId);
            dbbr.obrisi(unp);

            dbbr.obrisi(udzbenik);

            return udzbenik;

        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom brisanja udzbenika sa id:" + udzbenikId + " Greska: " + e.getMessage());
        }
    }

    @Override
    public Udzbenik pronadjiUdzbenikPoId(int udzbenikId) throws Exception {
        try {
            Udzbenik udzbenik = new Udzbenik();
            udzbenik.setUdzbenikId(udzbenikId);
            return (Udzbenik) dbbr.vratiPoId(udzbenik);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom trazenja udzbenika sa id:" + udzbenikId + " Greska: " + e.getMessage());
        }

    }

    @Override
    public Udzbenik pronadjiUdzbenikPoNazivu(String naziv) throws Exception {
        try {
            String upit = "SELECT * FROM udzbenik WHERE naziv='" + naziv + "'";
            PreparedStatement preparedStatement = dbbr.getConnection().prepareStatement(upit);
            ResultSet rs = preparedStatement.executeQuery(upit);

            if (rs.next()) {

                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                udzbenik.setOsobeUVeziSaUdzbenikom(OsobaUVeziSaUdzbenikomDaoImpl.getInstance().vratiOsobeZaUdzbenik(udzbenik.getUdzbenikId()));
                udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                udzbenik.setIzdavac(rs.getString("izdavac"));
                udzbenik.setStampa(rs.getString("stampa"));
                udzbenik.setTiraz(rs.getInt("tiraz"));
                udzbenik.setRbrIzdanja(rs.getInt("rbr_izdanja"));
                udzbenik.setIsbn(rs.getInt("isbn"));

                return udzbenik;
            }

            rs.close();
            preparedStatement.close();

            return null;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja udzbenika po nazivu: "
                    + naziv + "Greska: " + e.getMessage());
        }
    }

}
