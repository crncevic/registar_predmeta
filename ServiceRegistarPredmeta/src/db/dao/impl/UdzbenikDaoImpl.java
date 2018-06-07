/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.UdzbenikDao;
import domen.OsobaUVeziSaUdzbenikom;
import domen.Udzbenik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            String upit = "INSERT INTO udzbenik(naziv,godina_izdanja,izdavac,stampa,rbr_izdanja,tiraz,isbn) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = dbbr.getConnection().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, udzbenik.getNaziv());
            preparedStatement.setInt(2, udzbenik.getGodinaIzdanja());
            preparedStatement.setString(3, udzbenik.getIzdavac());
            preparedStatement.setString(4, udzbenik.getStampa());
            preparedStatement.setInt(5, udzbenik.getRbrIzdanja());
            preparedStatement.setInt(6, udzbenik.getTiraz());
            preparedStatement.setInt(7, udzbenik.getIsbn());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            int udzbenikId;
            if (rs.next()) {
                udzbenikId = rs.getInt(1);
                udzbenik.setUdzbenikId(udzbenikId);
            } else {
                throw new Exception("Id is not generated!");
            }
            String upit2 = "INSERT INTO osoba_u_vezi_sa_udzbenikom(osobaId,ime,prezime,titula,ulogaId,udzbenikId) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps2 = dbbr.getConnection().prepareStatement(upit2);
            for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                ps2.setInt(1, osoba.getOsobaId());
                ps2.setString(2, osoba.getIme());
                ps2.setString(3, osoba.getPrezime());
                ps2.setString(4, osoba.getTitula());
                ps2.setInt(5, osoba.getUlogaUdzbenik().getUlogaId());
                ps2.setInt(6, osoba.getUdzbenikId());
                ps2.executeUpdate();
            }

            dbbr.commitTransakcije();
            preparedStatement.close();
            ps2.close();

            return pronadjiUdzbenikPoId(udzbenikId);
        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom kreiranja udzbenika! Greska:" + e.getMessage());
        }
    }

    @Override
    public Udzbenik azurirajUdzbenik(Udzbenik udzbenik) throws Exception {
        try {
            String upit = "UPDATE udzbenik SET naziv=?,godina_izdanja=?,izdavac=?,stampa=?,rbr_izdanja=?,tiraz=?,isbn=?"
                    + " WHERE udzbenikId=?";
            PreparedStatement preparedStatement = dbbr.getConnection().prepareStatement(upit);
            preparedStatement.setString(1, udzbenik.getNaziv());
            preparedStatement.setInt(2, udzbenik.getGodinaIzdanja());
            preparedStatement.setString(3, udzbenik.getIzdavac());
            preparedStatement.setString(4, udzbenik.getStampa());
            preparedStatement.setInt(5, udzbenik.getRbrIzdanja());
            preparedStatement.setInt(6, udzbenik.getTiraz());
            preparedStatement.setInt(7, udzbenik.getIsbn());
            preparedStatement.setInt(8, udzbenik.getUdzbenikId());
            preparedStatement.executeUpdate();

            String upit2 = "DELETE FROM osoba_u_vezi_sa_udzbenikom WHERE udzbenikId=?";
            PreparedStatement ps2 = dbbr.getConnection().prepareStatement(upit2);
            ps2.setInt(1, udzbenik.getUdzbenikId());
            ps2.executeUpdate();

            String upit3 = "INSERT INTO osoba_u_vezi_sa_udzbenikom(osobaId,ime,prezime,titula,ulogaId,udzbenikId) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps3 = dbbr.getConnection().prepareStatement(upit3);
            for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                ps3.setInt(1, osoba.getOsobaId());
                ps3.setString(2, osoba.getIme());
                ps3.setString(3, osoba.getPrezime());
                ps3.setString(4, osoba.getTitula());
                ps3.setInt(5, osoba.getUlogaUdzbenik().getUlogaId());
                ps3.setInt(6, osoba.getUdzbenikId());
                ps3.executeUpdate();
            }

            dbbr.commitTransakcije();
            preparedStatement.close();
            ps2.close();
            ps3.close();

            System.out.println("Udzbenik je uspesno azuriran");

            return pronadjiUdzbenikPoId(udzbenik.getUdzbenikId());

        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja udzbenika sa id:"
                    + +udzbenik.getUdzbenikId() + ". Greska: " + e.getMessage());
        }
    }

    @Override
    public Udzbenik obrisiUdzbenik(int id) throws Exception {
        try {
            String upit = "DELETE FROM udzbenik WHERE udzbenikId=?";
            PreparedStatement preparedStatement = dbbr.getConnection().prepareStatement(upit);
            preparedStatement.setInt(1, id);

            Udzbenik udzbenik = pronadjiUdzbenikPoId(id);

            if (udzbenik != null) {
                preparedStatement.executeUpdate();

                String upit2 = "DELETE FROM autor WHERE udzbenikId=?";
                PreparedStatement ps2 = dbbr.getConnection().prepareStatement(upit2);
                ps2.setInt(1, udzbenik.getUdzbenikId());
                ps2.executeUpdate();

                String upit3 = "DELETE FROM recenzent WHERE udzbenikId=?";
                PreparedStatement ps3 = dbbr.getConnection().prepareStatement(upit3);
                ps3.setInt(1, udzbenik.getUdzbenikId());
                ps3.executeUpdate();

                dbbr.commitTransakcije();
                preparedStatement.close();
                ps2.close();
                ps3.close();
                return udzbenik;
            }

            throw new Exception("Udzbenik sa id:" + id + " ne postoji!");

        } catch (Exception e) {
            dbbr.rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom brisanja udzbenika sa id:" + id + " Greska: " + e.getMessage());
        }
    }

    @Override
    public Udzbenik pronadjiUdzbenikPoId(int udzbenikId) throws Exception {
        try {
            String upit = "SELECT * FROM udzbenik WHERE udzbenikId=" + udzbenikId;
            Statement statement = dbbr.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);

            if (rs.next()) {
                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                udzbenik.setOsobeUVeziSaUdzbenikom(OsobaUVeziSaUdzbenikomDaoImpl.getInstance().vratiOsobeZaUdzbenik(udzbenikId));
                udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                udzbenik.setIzdavac(rs.getString("izdavac"));
                udzbenik.setStampa(rs.getString("stampa"));
                udzbenik.setTiraz(rs.getInt("tiraz"));
                udzbenik.setRbrIzdanja(rs.getInt("rbr_izdanja"));
                udzbenik.setIsbn(rs.getInt("isbn"));

                rs.close();
                statement.close();

                return udzbenik;
            } else {
                return null;
            }
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
