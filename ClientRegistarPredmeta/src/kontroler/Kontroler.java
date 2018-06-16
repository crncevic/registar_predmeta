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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
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
    private Socket socket;

    private Kontroler() throws Exception {
        socket = Session.getInstance().getSocket();
    }

    public static Kontroler getInstance() throws Exception {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public void posaljiZahtev(int operation, Object data) throws Exception {

        RequestObject request = new RequestObject();
        request.setOperation(operation);
        request.setData(data);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

    }

    public Object primiOdgovor() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ResponseObject response = (ResponseObject) in.readObject();
            int code = response.getCode();

            if (code == IStatus.OK) {
                return response.getData();
            } else {
                throw new Exception("Dogodila se greska u komunikaciji");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    
}
