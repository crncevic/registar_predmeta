/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.net.Socket;
import javax.swing.JOptionPane;
import session.Session;

/**
 *
 * @author Petar
 */
public class FServerKonekcija extends javax.swing.JDialog {

    FMain fMain;

    /**
     * Creates new form FServerKonekcija
     */
    public FServerKonekcija(java.awt.Frame parent, boolean modal, FMain fMain) {
        super(parent, modal);
        initComponents();
        centrirajFormu();
        this.fMain = fMain;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTxtIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTxtPort = new javax.swing.JTextField();
        jBtnKonekcija = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Server IP:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Konekcija sa serverom");

        jLabel3.setText("Server port:");

        jBtnKonekcija.setText("Konektuj se");
        jBtnKonekcija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKonekcijaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(89, 89, 89))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnKonekcija, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTxtIP)
                            .addComponent(jTxtPort, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTxtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jBtnKonekcija)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnKonekcijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKonekcijaActionPerformed
        try {
            String ip = jTxtIP.getText().trim();
            int port = Integer.parseInt(jTxtPort.getText().trim());

            Socket socket = new Socket(ip, port);
            Session.getInstance().setSocket(socket);
            fMain.omoguciPristupSistemu();
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dogodila se greska prilikom povezivanja na server");
        }
    }//GEN-LAST:event_jBtnKonekcijaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnKonekcija;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTxtIP;
    private javax.swing.JTextField jTxtPort;
    // End of variables declaration//GEN-END:variables

    private void centrirajFormu() {
        setLocationRelativeTo(null);
    }
}
