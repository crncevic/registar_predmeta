/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domen.TematskaCelina;
import domen.TipNastave;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Petar
 */
public class Session {

    private static Session instance;
    private Map<String, Object> map;
    private Socket socket;
    private List<TematskaCelina> tematskeCelineStruktura;
    private String tematskeCelineTekst;
    private TipNastave tipNastave;

    private Session() {
        map = new HashMap<String, Object>();
        tematskeCelineStruktura = new ArrayList<>();
        tematskeCelineTekst = "";
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;

    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setTematskeCelineStruktura(List<TematskaCelina> tematskeCelineStruktura) {
        this.tematskeCelineStruktura = tematskeCelineStruktura;
    }

    public List<TematskaCelina> getTematskeCelineStruktura() {
        return tematskeCelineStruktura;
    }

    public String getTematskeCelineTekst() {
        return tematskeCelineTekst;
    }

    public void setTematskeCelineTekst(String tematskeCelineTekst) {
        this.tematskeCelineTekst = tematskeCelineTekst;
    }

    public TipNastave getTipNastave() {
        return tipNastave;
    }

    public void setTipNastave(TipNastave tipNastave) {
        this.tipNastave = tipNastave;
    }

}
