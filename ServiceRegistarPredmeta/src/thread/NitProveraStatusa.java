/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.util.logging.Level;
import java.util.logging.Logger;
import table.model.KorisnikStatusTableModel;

/**
 *
 * @author Petar
 */
public class NitProveraStatusa extends Thread {

    KorisnikStatusTableModel kstm;

    public NitProveraStatusa(KorisnikStatusTableModel kstm) {
        this.kstm = kstm;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(2000);
                kstm.proveriStanje();
            } catch (InterruptedException ex) {
                Logger.getLogger(NitProveraStatusa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
