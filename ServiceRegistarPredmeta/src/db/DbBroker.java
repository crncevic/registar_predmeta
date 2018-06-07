/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import constants.Constants;
import domen.OsobaUVeziSaUdzbenikom;
import domen.Korisnik;
import domen.Obaveza;

import domen.Predmet;
import domen.TematskaCelina;
import domen.TipNastave;
import domen.Udzbenik;
import domen.UlogaUdzbenik;
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
                udzbenik.setOsobeUVeziSaUdzbenikom(vratiOsobeZaUdzbenik(udzbenik.getUdzbenikId()));
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
            String upit2 = "INSERT INTO osoba_u_vezi_sa_udzbenikom(osobaId,ime,prezime,titula,ulogaId,udzbenikId) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps2 = connection.prepareStatement(upit2);
            for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                ps2.setInt(1, osoba.getOsobaId());
                ps2.setString(2, osoba.getIme());
                ps2.setString(3, osoba.getPrezime());
                ps2.setString(4, osoba.getTitula());
                ps2.setInt(5, osoba.getUlogaUdzbenik().getUlogaId());
                ps2.setInt(6, osoba.getUdzbenikId());
                ps2.executeUpdate();
            }

            commitTransakcije();
            preparedStatement.close();
            ps2.close();

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

            String upit2 = "DELETE FROM osoba_u_vezi_sa_udzbenikom WHERE udzbenikId=?";
            PreparedStatement ps2 = connection.prepareStatement(upit2);
            ps2.setInt(1, udzbenik.getUdzbenikId());
            ps2.executeUpdate();

            String upit3 = "INSERT INTO osoba_u_vezi_sa_udzbenikom(osobaId,ime,prezime,titula,ulogaId,udzbenikId) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps3 = connection.prepareStatement(upit3);
            for (OsobaUVeziSaUdzbenikom osoba : udzbenik.getOsobeUVeziSaUdzbenikom()) {
                ps3.setInt(1, osoba.getOsobaId());
                ps3.setString(2, osoba.getIme());
                ps3.setString(3, osoba.getPrezime());
                ps3.setString(4, osoba.getTitula());
                ps3.setInt(5, osoba.getUlogaUdzbenik().getUlogaId());
                ps3.setInt(6, osoba.getUdzbenikId());
                ps3.executeUpdate();
            }

            commitTransakcije();
            preparedStatement.close();
            ps2.close();
            ps3.close();

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

    public Udzbenik pronadjiUdzbenikPoId(int udzbenikId) throws Exception {
        try {
            String upit = "SELECT * FROM udzbenik WHERE udzbenikId=" + udzbenikId;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);

            if (rs.next()) {
                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                udzbenik.setOsobeUVeziSaUdzbenikom(vratiOsobeZaUdzbenik(udzbenikId));
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

    public Udzbenik pronadjiUdzbenikPoNazivu(String naziv) throws Exception {
        try {
            String upit = "SELECT * FROM udzbenik WHERE naziv='" + naziv + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(upit);
            ResultSet rs = preparedStatement.executeQuery(upit);

            if (rs.next()) {

                Udzbenik udzbenik = new Udzbenik();
                udzbenik.setUdzbenikId(rs.getInt("udzbenikId"));
                udzbenik.setNaziv(rs.getString("naziv"));
                udzbenik.setOsobeUVeziSaUdzbenikom(vratiOsobeZaUdzbenik(udzbenik.getUdzbenikId()));
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

    public List<OsobaUVeziSaUdzbenikom> vratiOsobeZaUdzbenik(int udzbenikId) throws SQLException, Exception {
        try {
            String upit = "SELECT * FROM osoba_u_vezi_sa_udzbenikom where udzbenikId=?";
            List<OsobaUVeziSaUdzbenikom> osobeUVeziSaUdzbenikom = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, udzbenikId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
                ouvsu.setOsobaId(rs.getInt("osobaId"));
                ouvsu.setUdzbenikId(rs.getInt("udzbenikId"));
                ouvsu.setIme(rs.getString("ime"));
                ouvsu.setPrezime(rs.getString("prezime"));
                ouvsu.setUlogaUdzbenik(nadjiUloguNaUdzbenikuZaId(rs.getInt("ulogaId")));
                ouvsu.setTitula(rs.getString("titula"));
                osobeUVeziSaUdzbenikom.add(ouvsu);
            }

            rs.close();
            ps.close();

            return osobeUVeziSaUdzbenikom;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom vracanja svih osoba vezanih za udzbenik.Greska:" + ex.getMessage());
        }
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

    public Predmet pronadjiPredmetPoId(int predmetId) throws Exception {
        try {

            String upit = "SELECT * FROM predmet WHERE predmetId=?";
            PreparedStatement ps = connection.prepareStatement(upit);

            ps.setInt(1, predmetId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Predmet predmet = new Predmet();
                predmet.setPredmetId(rs.getInt("predmetId"));
                predmet.setNaziv(rs.getString("naziv"));
                predmet.setBrCasovaPredavanjaNedeljno(rs.getInt("br_casova_predavanja_nedeljno"));
                predmet.setBrCasovaVezbiNedeljno(rs.getInt("br_casova_vezbi_nedeljno"));
                predmet.setOstaliCasovi(rs.getInt("ostali_casovi"));
                predmet.setDrugiObliciNastave(rs.getString("drugi_oblici_nastave"));
                predmet.setStudijskiIstrazivackiRad(rs.getString("studijski_istrazivacki_rad"));
                predmet.setCilj(rs.getString("cilj"));
                predmet.setIshod(rs.getString("ishod"));
                predmet.setUslov(rs.getString("uslov"));
                predmet.setVrstaINivoStudija(rs.getString("vrsta_i_nivo_studija"));
                predmet.setSadrzajTekst(rs.getString("sadrzaj_tekst"));

                // ucitavanje obaveza za predmet
                String upit2 = "SELECT * FROM obaveza WHERE predmetId=?";
                PreparedStatement ps2 = connection.prepareStatement(upit2);
                ps2.setInt(1, predmetId);
                List<Obaveza> obaveze = new ArrayList<>();

                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    Obaveza obaveza = new Obaveza();
                    obaveza.setObavezaId(rs2.getInt("obavezaId"));
                    obaveza.setPredmetId(rs2.getInt("predmetId"));
                    obaveza.setTip(rs2.getString("tip"));
                    obaveza.setNaziv(rs2.getString("naziv"));
                    obaveza.setBrojPoena(rs2.getInt("broj_poena"));

                    obaveze.add(obaveza);

                }

                predmet.setObaveze(obaveze);

                // ucitavanje tematskih celina za predmet
                String upit3 = "SELECT * FROM tematska_celina WHERE predmetId=?";
                PreparedStatement ps3 = connection.prepareStatement(upit3);
                ps3.setInt(1, predmetId);
                List<TematskaCelina> sadrzajTematskeCeline = new ArrayList<>();

                ResultSet rs3 = ps3.executeQuery();

                while (rs3.next()) {
                    TematskaCelina tematskaCelina = new TematskaCelina();
                    tematskaCelina.setTematskaCelinaId(rs3.getInt("tematska_celinaId"));
                    tematskaCelina.setTipNastaveId(rs3.getInt("tip_nastaveId"));
                    tematskaCelina.setPredmetId(rs3.getInt("predmetId"));
                    tematskaCelina.setNaziv(rs3.getString("naziv"));
                    tematskaCelina.setOpis(rs3.getString("opis"));

                    sadrzajTematskeCeline.add(tematskaCelina);

                }

                predmet.setSadrzajTematskeCeline(sadrzajTematskeCeline);

                //ucitavanje udzbenika za predmet
                String upit4 = "SELECT * FROM udzbenik WHERE predmetId=?";
                PreparedStatement ps4 = connection.prepareStatement(upit4);
                ps4.setInt(1, predmetId);
                List<Udzbenik> udzbenici = new ArrayList<>();

                ResultSet rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    Udzbenik udzbenik = new Udzbenik();
                    udzbenik.setUdzbenikId(rs4.getInt("udzbenikId"));
                    udzbenik.setNaziv(rs4.getString("naziv"));
                    udzbenik.setOsobeUVeziSaUdzbenikom(vratiOsobeZaUdzbenik(udzbenik.getUdzbenikId()));
                    udzbenik.setGodinaIzdanja(rs.getInt("godina_izdanja"));
                    udzbenik.setIzdavac(rs.getString("izdavac"));
                    udzbenik.setStampa(rs.getString("stampa"));
                    udzbenik.setTiraz(rs.getInt("tiraz"));
                    udzbenik.setRbrIzdanja(rs.getInt("rbr_izdanja"));
                    udzbenik.setIsbn(rs.getInt("isbn"));

                    udzbenici.add(udzbenik);
                }

                predmet.setUdzbenici(udzbenici);
                rs.close();
                rs2.close();
                rs3.close();
                rs4.close();
                ps.close();
                ps2.close();
                ps3.close();
                ps4.close();

                return predmet;

            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Doodila se greska prilikom pretrazivanja predmeta sa id:" + predmetId + ". Greska:" + e.getMessage());
        }

    }

    private TipNastave pronadjiTipNastavePoId(int tipId) throws Exception {
        try {
            String upit = "SELECT * FROM tip_nastave WHERE tip_nastaveId=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, tipId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TipNastave tn = new TipNastave();
                tn.setTipNastaveId(rs.getInt("tip_nastaveId"));
                tn.setNaziv(rs.getString("naziv"));

                rs.close();
                ps.close();

                return tn;
            }

            rs.close();
            ps.close();

            return null;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja tipa nastave.Greska:" + e.getMessage());

        }
    }

    private OsobaUVeziSaUdzbenikom vratiOsobuUVeziSaUdbenikomZaId(int osobaId) throws Exception {
        try {
            String upit = "SELECT * FROM osoba_u_vezi_sa_udzbenikom WHERE osobaId=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, osobaId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                OsobaUVeziSaUdzbenikom ouvsu = new OsobaUVeziSaUdzbenikom();
                ouvsu.setOsobaId(rs.getInt("osobaId"));
                ouvsu.setIme(rs.getString("ime"));
                ouvsu.setPrezime(rs.getString("prezime"));
                ouvsu.setTitula(rs.getString("titula"));

                rs.close();
                ps.close();

                return ouvsu;
            }

            rs.close();
            ps.close();
            return null;
        } catch (Exception ex) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja osobe koja je u vezi sa udzbenikom sa id:" + osobaId + " .Greska:" + ex.getMessage());
        }
    }

    private UlogaUdzbenik nadjiUloguNaUdzbenikuZaId(int ulogaId) throws Exception {
        try {
            String upit = "SELECT * FROM uloga_udzbenik WHERE ulogaId=?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, ulogaId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UlogaUdzbenik ulogaUdzbenik = new UlogaUdzbenik();
                ulogaUdzbenik.setUlogaId(rs.getInt("ulogaId"));
                ulogaUdzbenik.setNaziv(rs.getString("naziv"));
                rs.close();
                ps.close();

                return ulogaUdzbenik;
            }
            rs.close();
            ps.close();

            return null;
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja uloge_udzbenik  sa id:" + ulogaId + " .Greska:" + e.getMessage());
        }
    }
}
