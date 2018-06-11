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
public class UdzbenikNaPredmetu implements Serializable {

    private int predmetId;
    private int udbenikId;

    public UdzbenikNaPredmetu() {
    }

    public UdzbenikNaPredmetu(int predmetId, int udbenikId) {
        this.predmetId = predmetId;
        this.udbenikId = udbenikId;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public int getUdbenikId() {
        return udbenikId;
    }

    public void setUdbenikId(int udbenikId) {
        this.udbenikId = udbenikId;
    }

}
