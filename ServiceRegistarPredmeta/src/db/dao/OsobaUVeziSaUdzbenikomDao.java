/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dao;

import domen.OsobaUVeziSaUdzbenikom;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Petar
 */
public abstract class OsobaUVeziSaUdzbenikomDao extends AbstractDao {

    public OsobaUVeziSaUdzbenikomDao() throws Exception {
        super();
    }

    public abstract List<OsobaUVeziSaUdzbenikom> vratiOsobeZaUdzbenik(int udzbenikId) throws SQLException, Exception;

    public abstract OsobaUVeziSaUdzbenikom vratiOsobuUVeziSaUdbenikomZaId(int osobaId) throws Exception;
}
