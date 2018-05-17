/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constants.Constants;
import domen.Autor;
import domen.Korisnik;
import domen.Recenzent;
import domen.Udzbenik;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

    public List<Udzbenik> vratiSveUdzbenike() throws Exception {
        try {
            String upit = "SELECT * FROM udzbenik";
            List<Udzbenik> udzbenici = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);

            while (rs.next()) {
                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                udzbenik.setAutori(vratiAutoreZaUdzbenik(udzbenik.getUdzbenikId()));
                udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                udzbenik.setIzdavac(rs.getString("izdavac"));
                udzbenik.setRecenzenti(vratiRecenzenteZaUdzbenik(udzbenik.getUdzbenikId()));
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

    public Udzbenik kreirajUdzbenik(Udzbenik udzbenik) throws Exception {
        try {

            String upit = "INSERT INTO udzbenik(naziv,godina_izdanja,izdavac,stampa,rbr_izdanja,tiraz,isbn) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);

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
            String upit2 = "INSERT INTO autor(udzbenikId,ime,prezime,titula) VALUES (?,?,?,?)";
            PreparedStatement ps2 = connection.prepareStatement(upit2);
            for (Autor autor : udzbenik.getAutori()) {
                ps2.setInt(1, udzbenikId);
                ps2.setString(2, autor.getIme());
                ps2.setString(3, autor.getPrezime());
                ps2.setString(4, autor.getTitula());
                ps2.executeUpdate();
            }

            String upit3 = "INSERT INTO recenzent(recenzentId,ime,prezime,titula) VALUES (?,?,?,?)";
            PreparedStatement ps3 = connection.prepareStatement(upit3);
            for (Recenzent recenzent : udzbenik.getRecenzenti()) {
                ps3.setInt(1, udzbenikId);
                ps3.setString(2, recenzent.getIme());
                ps3.setString(3, recenzent.getPrezime());
                ps3.setString(4, recenzent.getTitula());
                ps3.executeUpdate();
            }

            commitTransakcije();
            preparedStatement.close();
            ps2.close();
            ps3.close();

            return pronadjiUdzbenikPoId(udzbenikId);
        } catch (Exception e) {
            rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom kreiranja udzbenika! Greska:" + e.getMessage());
        }
    }

    public Udzbenik azurirajUdzbenik(Udzbenik udzbenik) throws Exception {
        try {
            String upit = "UPDATE udzbenik SET naziv=?,godina_izdanja=?,izdavac=?,stampa=?,rbr_izdanja=?,tiraz=?,isbn=?"
                    + " WHERE udzbenikId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(upit);
            preparedStatement.setString(1, udzbenik.getNaziv());
            preparedStatement.setInt(2, udzbenik.getGodinaIzdanja());
            preparedStatement.setString(3, udzbenik.getIzdavac());
            preparedStatement.setString(4, udzbenik.getStampa());
            preparedStatement.setInt(5, udzbenik.getRbrIzdanja());
            preparedStatement.setInt(6, udzbenik.getTiraz());
            preparedStatement.setInt(7, udzbenik.getIsbn());
            preparedStatement.setInt(8, udzbenik.getUdzbenikId());
            preparedStatement.executeUpdate();

            String upit2 = "DELETE FROM autor WHERE udzbenikId=?";
            PreparedStatement ps2 = connection.prepareStatement(upit2);
            ps2.setInt(1, udzbenik.getUdzbenikId());
            ps2.executeUpdate();

            String upit3 = "DELETE FROM recenzent WHERE udzbenikId=?";
            PreparedStatement ps3 = connection.prepareStatement(upit3);
            ps3.setInt(1, udzbenik.getUdzbenikId());
            ps3.executeUpdate();

            String upit4 = "INSERT INTO autor(udzbenikId,ime,prezime,titula) VALUES (?,?,?,?)";
            PreparedStatement ps4 = connection.prepareStatement(upit4);
            for (Autor autor : udzbenik.getAutori()) {
                ps4.setInt(1, udzbenik.getUdzbenikId());
                ps4.setString(2, autor.getIme());
                ps4.setString(3, autor.getPrezime());
                ps4.setString(4, autor.getTitula());
                ps4.executeUpdate();
            }

            String upit5 = "INSERT INTO recenzent(recenzentId,ime,prezime,titula) VALUES (?,?,?,?)";
            PreparedStatement ps5 = connection.prepareStatement(upit2);
            for (Recenzent recenzent : udzbenik.getRecenzenti()) {
                ps5.setInt(1, udzbenik.getUdzbenikId());
                ps5.setString(2, recenzent.getIme());
                ps5.setString(3, recenzent.getPrezime());
                ps5.setString(4, recenzent.getTitula());
                ps5.executeUpdate();
            }

            commitTransakcije();
            preparedStatement.close();
            ps2.close();
            ps3.close();
            ps4.close();
            ps5.close();

            System.out.println("Udzbenik je uspesno azuriran");

            return pronadjiUdzbenikPoId(udzbenik.getUdzbenikId());

        } catch (Exception e) {
            rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom azuriranja udzbenika sa id:"
                    + +udzbenik.getUdzbenikId() + ". Greska: " + e.getMessage());
        }
    }

    public Udzbenik obrisiUdzbenik(int id) throws Exception {
        try {
            String upit = "DELETE FROM udzbenik WHERE udzbenikId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(upit);
            preparedStatement.setInt(1, id);

            Udzbenik udzbenik = pronadjiUdzbenikPoId(id);

            if (udzbenik != null) {
                preparedStatement.executeUpdate();

                String upit2 = "DELETE FROM autor WHERE udzbenikId=?";
                PreparedStatement ps2 = connection.prepareStatement(upit2);
                ps2.setInt(1, udzbenik.getUdzbenikId());
                ps2.executeUpdate();

                String upit3 = "DELETE FROM recenzent WHERE udzbenikId=?";
                PreparedStatement ps3 = connection.prepareStatement(upit3);
                ps3.setInt(1, udzbenik.getUdzbenikId());
                ps3.executeUpdate();

                commitTransakcije();
                preparedStatement.close();
                ps2.close();
                ps3.close();
                return udzbenik;
            }

            throw new Exception("Udzbenik sa id:" + id + " ne postoji!");

        } catch (Exception e) {
            rollackTransakcije();
            throw new Exception("Dogodila se greska prilikom brisanja udzbenika sa id:" + id + " Greska: " + e.getMessage());
        }
    }

    public Udzbenik pronadjiUdzbenikPoId(int id) throws Exception {
        try {
            String upit = "SELECT * FROM udzbenik WHERE udzbenikId=" + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);

            if (rs.next()) {
                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                udzbenik.setAutori(vratiAutoreZaUdzbenik(id));
                udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                udzbenik.setIzdavac(rs.getString("izdavac"));
                udzbenik.setRecenzenti(vratiRecenzenteZaUdzbenik(id));
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
            throw new Exception("Dogodila se greska prilikom trazenja udzbenika sa id:" + id + " Greska: " + e.getMessage());

        }

    }

    public Udzbenik pronadjiUdzbenikPoNazivu(String naziv) throws Exception {
        try {
            String upit = "SELECT * FROM udzbenik WHERE naziv='" + naziv + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(upit);
            ResultSet rs = preparedStatement.executeQuery(upit);

            if (rs.next()) {

                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                udzbenik.setAutori(vratiAutoreZaUdzbenik(udzbenik.getUdzbenikId()));
                udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                udzbenik.setIzdavac(rs.getString("izdavac"));
                udzbenik.setRecenzenti(vratiRecenzenteZaUdzbenik(udzbenik.getUdzbenikId()));
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

    public List<Autor> vratiAutoreZaUdzbenik(int udzbenikId) throws SQLException {
        String upit = "SELECT * FROM autor WHERE udzbenikId=?";
        List<Autor> autori = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, udzbenikId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Autor autor = new Autor();
            autor.setAutorId(rs.getInt("autorId"));
            autor.setUdzbenikId(rs.getInt("udzbenikId"));
            autor.setIme(rs.getString("ime"));
            autor.setPrezime(rs.getString("prezime"));
            autor.setTitula(rs.getString("titula"));

            autori.add(autor);
        }

        ps.close();
        return autori;

    }

    public List<Recenzent> vratiRecenzenteZaUdzbenik(int udzbenikId) throws SQLException {
        String upit = "SELECT * FROM recenzent WHERE udzbenikId=?";
        List<Recenzent> recenzenti = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, udzbenikId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Recenzent recenzent = new Recenzent();
            recenzent.setRecenzentId(rs.getInt("recenzentId"));
            recenzent.setUdzbenikId(rs.getInt("udzbenikId"));
            recenzent.setIme(rs.getString("ime"));
            recenzent.setPrezime(rs.getString("prezime"));
            recenzent.setTitula(rs.getString("titula"));

            recenzenti.add(recenzent);
        }

        ps.close();
        return recenzenti;

    }

    public Korisnik vratiKorisnika(String username, String password) throws Exception {
        try {
            String query = "SELECT * FROM korisnik WHERE username=? AND password=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Korisnik korisnik = new Korisnik();
                korisnik.setKorisnikId(rs.getInt("korisnikId"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setUsername(rs.getString("username"));
                korisnik.setPassword(rs.getString("password"));
                rs.close();
                ps.close();

                return korisnik;
            } else {
                rs.close();
                ps.close();
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
