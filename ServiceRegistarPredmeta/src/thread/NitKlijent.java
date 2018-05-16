package thread;

import db.DbBroker;
import domen.Udzbenik;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;
import transfer.util.IOperation;
import transfer.util.IStatus;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Petar
 */
public class NitKlijent extends Thread {

    Socket socket;

    public NitKlijent(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                //prihvati zahtev od klijenta
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                RequestObject requestObject = (RequestObject) input.readObject();
                ResponseObject responseObject = new ResponseObject();
                //obradi zahtev
                switch (requestObject.getOperation()) {
                    case IOperation.VRATI_SVE_UDZBENIKE:
                        try {
                            List<Udzbenik> udzbenici = DbBroker.getInstance().vratiSveUdzbenike();
                            responseObject.setData(udzbenici);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                }
                //posalji odgovor

                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(responseObject);
                output.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
