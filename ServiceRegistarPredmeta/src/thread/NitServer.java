/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petar
 */
public class NitServer extends Thread {

    ServerSocket serverSocket;
    List<Thread> klijenti;

    public NitServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("Cekanje na novog klijenta");
            try {
                System.out.println("Cekanje na novog klijenta");
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao");
                Thread klijent = new NitKlijent(socket);
                klijenti.add(klijent);

            } catch (IOException ex) {
                Logger.getLogger(NitServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
