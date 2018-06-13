/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import constants.Constants;
import db.SettingsLoader;
import java.io.IOException;
import thread.NitServer;

/**
 *
 * @author Petar
 */
public class Server {

    public Thread nitServer;

    public Server() throws IOException {
        inicijalizujNitServer();
    }

    private void inicijalizujNitServer() throws IOException {
        int port = Integer.parseInt(SettingsLoader.getInstance().getValue(Constants.APPLICATION_PORT));
        nitServer = new NitServer(port);
    }

    public void start() throws IOException {
        nitServer.start();
    }

    public void stop() {
        nitServer.interrupt();
    }

}
