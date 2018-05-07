/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;

/**
 *
 * @author Petar
 */
public class Server {

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(9009);
            System.out.println("Server je spreman i ceka klijenta");
            Socket socket = serverSocket.accept();
            System.out.println("Klijent se povezao");
            zapocniKomunikaciju(socket);

        } catch (Exception e) {
            System.out.println("Doslo je do greske prilikom povezivanja klijenta na server!Error:"+e.getMessage());
        }
    }

    private void zapocniKomunikaciju(Socket socket) throws IOException, ClassNotFoundException {
        while (true) {
            //prihvati zahtev od klijenta
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            RequestObject requestObject = (RequestObject) input.readObject();

            //obradi zahtev
            switch (requestObject.getOperation()) {

            }
            //posalji odgovor
            ResponseObject responseObject = new ResponseObject();

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(responseObject);
            output.flush();
        }
    }
}
