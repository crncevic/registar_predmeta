/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;

/**
 *
 * @author Petar
 */
public interface OpstiDomenskiObjekat {
    public String vratiImeKlase();
    public String vratiVrednostiAtributa();
    public String postaviVrednostAtributa();
    public String vratiUslovZaNadjiSlog();
    public String vratiUslovZaNadjiSlogove();
    public String vratiNaziveAtributaZaKreiraj();
    public OpstiDomenskiObjekat napraviDomenskiObjekat(ResultSet rs) throws Exception;
}
