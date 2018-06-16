/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao.impl;

import db.dao.StatusDao;
import domen.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petar
 */
public class StatusDaoImpl extends StatusDao {

    private static StatusDaoImpl instance;

    private StatusDaoImpl() throws Exception {
        super();
    }

    public static StatusDaoImpl getInstance() throws Exception {
        if (instance == null) {
            instance = new StatusDaoImpl();
        }
        return instance;
    }

    @Override
    public synchronized List<Status> vratiSveStatuse() throws Exception {
        try {
            String upit = "SELECT * FROM status";
            PreparedStatement ps = dbbr.getConnection().prepareStatement(upit);
            List<Status> statusi = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Status status = new Status();
                status.setStatusId(rs.getInt("statusId"));
                status.setNaziv(rs.getString("naziv"));

                statusi.add(status);
            }

            rs.close();
            ps.close();

            return statusi;

        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom vracanja svih statusa. Greska: " + e.getMessage());
        }
    }

    @Override
    public synchronized Status vratiStatusZaId(int statusId) throws Exception {
        try {
            Status s = new Status();
            s.setStatusId(statusId);

            return (Status) dbbr.vratiPoId(s);
        } catch (Exception e) {
            throw new Exception("Dogodila se greska prilikom pretrazivanja statusa sa id:" + statusId + ". Greska:" + e.getMessage());
        }
    }

}
