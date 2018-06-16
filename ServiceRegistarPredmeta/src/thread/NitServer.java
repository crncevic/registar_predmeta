/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import constants.Constants;
import db.SettingsLoader;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Petar
 */
public class NitServer extends Thread {

    private ServerSocket serverSocket;
    private List<NitKlijent> klijenti;
    private int maxBrojKlijenata;

    public NitServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        klijenti = new ArrayList<>();
        postaviMaxBrojKlijenata();

    }

    @Override
    public void run() {

        if (klijenti.size() < maxBrojKlijenata) {
            try {
                while (!isInterrupted()) {
                    System.out.println("Cekanje na novog klijenta");
                    Socket socket = serverSocket.accept();
                    System.out.println("Klijent se povezao na portu br:" + socket.getPort());
                    NitKlijent klijent = new NitKlijent(socket);
                    klijenti.add(klijent);
                }

            } catch (Exception ex) {

            }
        }
        System.out.println("Serverska nit prekinula sa radom!");

    }

    private void postaviMaxBrojKlijenata() {
        try {
            maxBrojKlijenata = Integer.parseInt(SettingsLoader.getInstance().getValue(Constants.MAX_CLIENTS));
        } catch (Exception e) {
            maxBrojKlijenata = 10;
            System.out.println("Dogodila se greska prilikom iscitavanja max broja klijenata iz fajla. Max broj klijenata je postavljen na 10");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public List<NitKlijent> getKlijenti() {
        return klijenti;
    }
    
    

}
