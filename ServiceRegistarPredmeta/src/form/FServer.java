/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import constants.Constants;
import db.SettingsLoader;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import server.Server;
import thread.NitServer;

/**
 *
 * @author Petar
 */
public class FServer extends javax.swing.JFrame {

    NitServer nitServer;
    int port;

    /**
     * Creates new form FServer
     */
    public FServer() {
        initComponents();
        centrirajFormu();
        initForm();
        try {
            port = Integer.valueOf(SettingsLoader.getInstance().getValue(Constants.APPLICATION_PORT));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nije moguce ucitati broj porta! Program se prekida!");
            dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnServerStart = new javax.swing.JButton();
        jBtnServerStop = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuKonfiguracija = new javax.swing.JMenu();
        jMenuItemKonekcijaSaBazom = new javax.swing.JMenuItem();
        jMenuItemServer = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBtnServerStart.setText("Server start");
        jBtnServerStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnServerStartActionPerformed(evt);
            }
        });

        jBtnServerStop.setText("Server stop");
        jBtnServerStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnServerStopActionPerformed(evt);
            }
        });

        jMenuKonfiguracija.setText("Konfiguracija");

        jMenuItemKonekcijaSaBazom.setText("Konekcija sa bazom");
        jMenuItemKonekcijaSaBazom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKonekcijaSaBazomActionPerformed(evt);
            }
        });
        jMenuKonfiguracija.add(jMenuItemKonekcijaSaBazom);

        jMenuItemServer.setText("Server");
        jMenuItemServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServerActionPerformed(evt);
            }
        });
        jMenuKonfiguracija.add(jMenuItemServer);

        jMenuBar1.add(jMenuKonfiguracija);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnServerStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(jBtnServerStop)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(204, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnServerStart)
                    .addComponent(jBtnServerStop))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnServerStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnServerStartActionPerformed
        try {
            if (nitServer == null || !nitServer.isAlive()) {
                nitServer = new NitServer(port);
                nitServer.start();
            } else {
                nitServer.interrupted();
            }
            jBtnServerStart.setEnabled(false);
            jBtnServerStop.setEnabled(true);
            jMenuKonfiguracija.setEnabled(false);

            JOptionPane.showMessageDialog(this, "Server uspesno pokrenut ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dogodila se greska kod pokretanja servera! Greska: " + e.getMessage());
        }
    }//GEN-LAST:event_jBtnServerStartActionPerformed

    private void jMenuItemServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServerActionPerformed
        JDialog fKonfiguracijaServera = new FKonfiguracijaServera(this, true);
        fKonfiguracijaServera.setVisible(true);
    }//GEN-LAST:event_jMenuItemServerActionPerformed

    private void jBtnServerStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnServerStopActionPerformed
        try {
            if (nitServer != null || !nitServer.isInterrupted()) {
                nitServer.getServerSocket().close();
                nitServer.interrupt();
                System.out.println(nitServer.isInterrupted() + "");
            }
            jBtnServerStop.setEnabled(false);
            jBtnServerStart.setEnabled(true);
            jMenuKonfiguracija.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dogodila se greska prilikom zaustavljanja servera.");
        }
    }//GEN-LAST:event_jBtnServerStopActionPerformed

    private void jMenuItemKonekcijaSaBazomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKonekcijaSaBazomActionPerformed
        JDialog fKonfiguracijaBaze = new FKonfiguracijaBaze(this, true);
        fKonfiguracijaBaze.setVisible(true);
    }//GEN-LAST:event_jMenuItemKonekcijaSaBazomActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnServerStart;
    private javax.swing.JButton jBtnServerStop;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemKonekcijaSaBazom;
    private javax.swing.JMenuItem jMenuItemServer;
    private javax.swing.JMenu jMenuKonfiguracija;
    // End of variables declaration//GEN-END:variables

    private void centrirajFormu() {
        setLocationRelativeTo(null);
    }

    private void initForm() {
        jBtnServerStop.setEnabled(false);
    }

}
