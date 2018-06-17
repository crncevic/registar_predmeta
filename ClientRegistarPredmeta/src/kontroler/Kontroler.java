/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.OsobaUVeziSaUdzbenikom;
import domen.Korisnik;
import domen.Predmet;
import domen.Udzbenik;
import form.FMain;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import javax.swing.JOptionPane;
import session.Session;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;
import transfer.util.IOperation;
import transfer.util.IStatus;

/**
 *
 * @author Petar
 */
public class Kontroler {

    private static Kontroler instance;
    FMain fMain;

    private Kontroler() throws Exception {
        fMain = FMain.getInstance();
    }

    public static Kontroler getInstance() throws Exception {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public void posaljiZahtev(int operation, Object data) throws Exception {
        try {
            Socket socket = Session.getInstance().getSocket();
            RequestObject request = new RequestObject();
            request.setOperation(operation);
            request.setData(data);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(request);
            out.flush();
        } catch (SocketException se) {
            Session.getInstance().getSocket().close();
            Session.getInstance().getMap().remove("ulogovani_korisnik");
            fMain.getUlogovaniKorisnikLabel().setText("");
            fMain.omoguceSamoKonekcijuNaServer();
            JOptionPane.showMessageDialog(fMain, "<html><font color=#ffffff>Doslo je do prekida konekcije sa serverom! Pokusajte da se konektujete opet.</font></html>","Prekid konekcije",JOptionPane.ERROR_MESSAGE);
        }

    }

    public Object primiOdgovor() throws Exception {
        try {
            Socket socket = Session.getInstance().getSocket();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ResponseObject response = (ResponseObject) in.readObject();
            int code = response.getCode();

            if (code == IStatus.OK) {
                return response.getData();
            } else {
                throw new Exception("Greska:" + response.getMessage());
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

}
