/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import form.FServer;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Petar
 */
public class Start {

    public static void main(String[] args) throws IOException {
        JFrame fServer = new FServer();
        fServer.setVisible(true);
    }
}
