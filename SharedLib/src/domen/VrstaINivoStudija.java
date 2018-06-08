/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Petar
 */
public class VrstaINivoStudija implements Serializable{
    private int vrstaINivoId;
    private String naziv;

    public VrstaINivoStudija() {
    }

    public VrstaINivoStudija(int vrstaINivoId, String naziv) {
        this.vrstaINivoId = vrstaINivoId;
        this.naziv = naziv;
    }

    public int getVrstaINivoId() {
        return vrstaINivoId;
    }

    public void setVrstaINivoId(int vrstaINivoId) {
        this.vrstaINivoId = vrstaINivoId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
}
