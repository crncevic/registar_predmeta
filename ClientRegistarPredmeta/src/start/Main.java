/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import form.FLogin;
import form.FMain;
import form.FUdzbenik;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import session.Session;

/**
 *
 * @author Petar
 */
public class Main {

    public static void main(String[] args) {

        JFrame fMain = FMain.getInstance();
        fMain.setVisible(true);

    }
}
