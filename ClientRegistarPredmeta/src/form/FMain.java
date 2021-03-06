/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domen.Korisnik;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import kontroler.Kontroler;
import session.Session;
import transfer.util.IOperation;

/**
 *
 * @author Petar
 */
public class FMain extends javax.swing.JFrame {

    private static FMain instance;

    /**
     * Creates new form FMain
     */
    private FMain() {
        initComponents();
        maksimizirajFormu();
        postaviStatusBar();
        omoguceSamoKonekcijuNaServer();
        postaviBojuPozadine();
        postaviBojuSlovaMenuBar();

    }

    public static FMain getInstance() {
        if (instance == null) {
            instance = new FMain();
        }
        return instance;
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
        jLabelUlogovaniKorisnik = new javax.swing.JLabel();
        jBtnLogout = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuUdzbenik = new javax.swing.JMenu();
        jMenuKreirajUdzbenik = new javax.swing.JMenuItem();
        jMenuItemPretraziUdzbenik = new javax.swing.JMenuItem();
        jMenuPredmet = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuPretrazi = new javax.swing.JMenuItem();
        jMenuStudijskiProgram = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuPristupSistemu = new javax.swing.JMenu();
        jMenuItemLogin = new javax.swing.JMenuItem();
        jMenuItemKonektujSe = new javax.swing.JMenuItem();
        jMenuNastavnici = new javax.swing.JMenu();
        jMenuPronadjiNastavnike = new javax.swing.JMenuItem();
        jMenuAutor = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glavna forma");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ulogovani korisnik:");

        jLabelUlogovaniKorisnik.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUlogovaniKorisnik.setText(" ");

        jBtnLogout.setBackground(new java.awt.Color(51, 204, 255));
        jBtnLogout.setForeground(new java.awt.Color(255, 255, 255));
        jBtnLogout.setText("Logout");
        jBtnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLogoutActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(255, 102, 0));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));

        jMenuUdzbenik.setForeground(new java.awt.Color(255, 255, 255));
        jMenuUdzbenik.setText("Udzbenik");

        jMenuKreirajUdzbenik.setBackground(new java.awt.Color(255, 102, 0));
        jMenuKreirajUdzbenik.setForeground(new java.awt.Color(255, 255, 255));
        jMenuKreirajUdzbenik.setText("Kreiraj");
        jMenuKreirajUdzbenik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuKreirajUdzbenikActionPerformed(evt);
            }
        });
        jMenuUdzbenik.add(jMenuKreirajUdzbenik);

        jMenuItemPretraziUdzbenik.setBackground(new java.awt.Color(255, 102, 0));
        jMenuItemPretraziUdzbenik.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItemPretraziUdzbenik.setText("Pretrazi");
        jMenuItemPretraziUdzbenik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPretraziUdzbenikActionPerformed(evt);
            }
        });
        jMenuUdzbenik.add(jMenuItemPretraziUdzbenik);

        jMenuBar1.add(jMenuUdzbenik);

        jMenuPredmet.setForeground(new java.awt.Color(255, 255, 255));
        jMenuPredmet.setText("Predmet");

        jMenuItem1.setBackground(new java.awt.Color(255, 102, 0));
        jMenuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setText("Kreiraj");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuPredmet.add(jMenuItem1);

        jMenuPretrazi.setBackground(new java.awt.Color(255, 102, 0));
        jMenuPretrazi.setForeground(new java.awt.Color(255, 255, 255));
        jMenuPretrazi.setText("Pretrazi");
        jMenuPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPretraziActionPerformed(evt);
            }
        });
        jMenuPredmet.add(jMenuPretrazi);

        jMenuBar1.add(jMenuPredmet);

        jMenuStudijskiProgram.setForeground(new java.awt.Color(255, 255, 255));
        jMenuStudijskiProgram.setText("Studijski program");

        jMenuItem2.setBackground(new java.awt.Color(255, 102, 0));
        jMenuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setText("Dodaj predmet");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuStudijskiProgram.add(jMenuItem2);

        jMenuItem3.setBackground(new java.awt.Color(255, 102, 0));
        jMenuItem3.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setText("Pretrazi predmete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuStudijskiProgram.add(jMenuItem3);

        jMenuBar1.add(jMenuStudijskiProgram);

        jMenuPristupSistemu.setForeground(new java.awt.Color(255, 255, 255));
        jMenuPristupSistemu.setText("Pristup sistemu");

        jMenuItemLogin.setBackground(new java.awt.Color(255, 102, 0));
        jMenuItemLogin.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItemLogin.setText("Login");
        jMenuItemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoginActionPerformed(evt);
            }
        });
        jMenuPristupSistemu.add(jMenuItemLogin);

        jMenuItemKonektujSe.setBackground(new java.awt.Color(255, 102, 0));
        jMenuItemKonektujSe.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItemKonektujSe.setText("Konektuj se");
        jMenuItemKonektujSe.setEnabled(false);
        jMenuItemKonektujSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKonektujSeActionPerformed(evt);
            }
        });
        jMenuPristupSistemu.add(jMenuItemKonektujSe);

        jMenuBar1.add(jMenuPristupSistemu);

        jMenuNastavnici.setForeground(new java.awt.Color(255, 255, 255));
        jMenuNastavnici.setText("Nastavnici");

        jMenuPronadjiNastavnike.setBackground(new java.awt.Color(255, 102, 0));
        jMenuPronadjiNastavnike.setForeground(new java.awt.Color(255, 255, 255));
        jMenuPronadjiNastavnike.setText("Pronadji nastavnike");
        jMenuPronadjiNastavnike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPronadjiNastavnikeActionPerformed(evt);
            }
        });
        jMenuNastavnici.add(jMenuPronadjiNastavnike);

        jMenuBar1.add(jMenuNastavnici);

        jMenuAutor.setForeground(new java.awt.Color(255, 255, 255));
        jMenuAutor.setText("O autoru");
        jMenuAutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAutorMouseClicked(evt);
            }
        });
        jMenuAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAutorActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenuAutor);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1150, 1150, 1150)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnLogout)
                    .addComponent(jLabelUlogovaniKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelUlogovaniKorisnik))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLogout)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuKreirajUdzbenikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuKreirajUdzbenikActionPerformed
        JDialog fUdzbenik = new FUdzbenik(this, true);
        fUdzbenik.setVisible(true);

        statusLabel.setText("Use case: Kreiranje udzbenika!");
    }//GEN-LAST:event_jMenuKreirajUdzbenikActionPerformed

    private void jMenuItemPretraziUdzbenikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPretraziUdzbenikActionPerformed
        JDialog fSelectUdzbenik = new FSelectUdzbenik(this, true);
        fSelectUdzbenik.setVisible(true);

        statusLabel.setText("Use case: Pretrazivanje udzbenika!");
    }//GEN-LAST:event_jMenuItemPretraziUdzbenikActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFrame fPredmet = new FPredmet(this);
        fPredmet.setVisible(true);

        statusLabel.setText("Use case: Kreiranje predmeta!");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPretraziActionPerformed
        JDialog fSelectPredmet = new FSelectPredmet(this, true);
        fSelectPredmet.setVisible(true);
        statusLabel.setText("Use case: Pretrazivanje predmeta!");

    }//GEN-LAST:event_jMenuPretraziActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JDialog fPredmetNaSmeru = new FPredmetNaSmeru(this, true);
        fPredmetNaSmeru.setVisible(true);

        statusLabel.setText("Use case: Kreiranje predmeta na studijskom programu!");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JDialog fSelectPredmetNaSmeru = new FSelectPredmetNaSmeru(this, true);
        fSelectPredmetNaSmeru.setVisible(true);

        statusLabel.setText("Use case: Pretrazivanje predmeta na studijskom programu!");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jBtnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLogoutActionPerformed
        try {
            if (Session.getInstance().getMap().containsKey("ulogovani_korisnik")) {
                Korisnik korisnik = (Korisnik) Session.getInstance().getMap().get("ulogovani_korisnik");
                Kontroler.getInstance().posaljiZahtev(IOperation.LOGOUT, korisnik);
                Korisnik k = (Korisnik) Kontroler.getInstance().primiOdgovor();
                if (k != null) {
                    Session.getInstance().getMap().remove("ulogovani_korisnik");
                    jLabelUlogovaniKorisnik.setText("");
                    onemoguciMenijeLogout();
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dovidjenja " + k.getIme() + "</font></html>");
                } else {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Neuspesan logout !</font></html>");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>" + ex.getMessage() + "</font></html>");
        }
    }//GEN-LAST:event_jBtnLogoutActionPerformed

    private void jMenuItemKonektujSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKonektujSeActionPerformed
        JDialog fServerKonekcija = new FServerKonekcija(this, true, this);
        fServerKonekcija.setVisible(true);
    }//GEN-LAST:event_jMenuItemKonektujSeActionPerformed

    private void jMenuItemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoginActionPerformed
        JDialog fLogin = new FLogin(this, true);
        fLogin.setVisible(true);
    }//GEN-LAST:event_jMenuItemLoginActionPerformed

    private void jMenuAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAutorActionPerformed

    }//GEN-LAST:event_jMenuAutorActionPerformed

    private void jMenuAutorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAutorMouseClicked
        if (Session.getInstance().getMap().containsKey("ulogovani_korisnik")) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Autor ove aplikacije je Petar Crnčević, student četvrte godine.</font></html>", "Autor", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jMenuAutorMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            UIManager ui = new UIManager();
            ui.put("OptionPane.background", new Color(51, 0, 102));
            ui.put("Panel.background", new Color(51, 0, 102));
            ui.put("Panel.foreground", new Color(255, 255, 255));
            ui.put("Label.foreground", new Color(255, 255, 255));
        } catch (Exception e) {

        }
    }//GEN-LAST:event_formWindowActivated

    private void jMenuPronadjiNastavnikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPronadjiNastavnikeActionPerformed
        JDialog fSelectNastavnik = new FSelectNastavnik(this, true);
        fSelectNastavnik.setVisible(true);
        
         statusLabel.setText("Use case: Pretraga nastavnika!");
    }//GEN-LAST:event_jMenuPronadjiNastavnikeActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelUlogovaniKorisnik;
    private javax.swing.JMenu jMenuAutor;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemKonektujSe;
    private javax.swing.JMenuItem jMenuItemLogin;
    private javax.swing.JMenuItem jMenuItemPretraziUdzbenik;
    private javax.swing.JMenuItem jMenuKreirajUdzbenik;
    private javax.swing.JMenu jMenuNastavnici;
    private javax.swing.JMenu jMenuPredmet;
    private javax.swing.JMenuItem jMenuPretrazi;
    private javax.swing.JMenu jMenuPristupSistemu;
    private javax.swing.JMenuItem jMenuPronadjiNastavnike;
    private javax.swing.JMenu jMenuStudijskiProgram;
    private javax.swing.JMenu jMenuUdzbenik;
    // End of variables declaration//GEN-END:variables
    private JPanel statusPanel;
    private JLabel statusLabel;

    private void maksimizirajFormu() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void postaviStatusBar() {
        setLayout(new BorderLayout());

        statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusPanel, BorderLayout.SOUTH);

        statusPanel.setPreferredSize(new Dimension(getWidth(), 30));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusLabel = new JLabel("Status: This is main form!");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusLabel.setForeground(Color.white);
        statusPanel.add(statusLabel);
        statusPanel.setBackground(new Color(51, 0, 102));

    }

    public void postaviUlogovanogKorisnika() {
        try {
            if (Session.getInstance().getMap().containsKey("ulogovani_korisnik")) {
                Korisnik korisnik = (Korisnik) Session.getInstance().getMap().get("ulogovani_korisnik");
                jLabelUlogovaniKorisnik.setText(korisnik.getIme() + " " + korisnik.getPrezime());
            } else {
                jLabelUlogovaniKorisnik.setText("-");
            }
        } catch (Exception e) {
            System.out.println("Dogodila se greska prilikom postavljanja ulogovanog korisnika. Greska:" + e.getMessage());
        }
    }

    public void omoguciMenije() {
        if (Session.getInstance().getSocket() != null && !Session.getInstance().getSocket().isClosed()) {
            jMenuPredmet.setEnabled(true);
            jMenuUdzbenik.setEnabled(true);
            jMenuPristupSistemu.setEnabled(true);
            jMenuStudijskiProgram.setEnabled(true);
            jMenuNastavnici.setEnabled(true);

            jMenuAutor.setEnabled(true);
            jMenuItemLogin.setEnabled(false);
            jBtnLogout.setEnabled(true);
        } else {
            omoguceSamoKonekcijuNaServer();
        }

    }

    private void onemoguciMenije() {
        try {
            if (Session.getInstance().getSocket() != null && !Session.getInstance().getSocket().isClosed()) {
                jMenuPredmet.setEnabled(false);
                jMenuUdzbenik.setEnabled(false);
                jMenuStudijskiProgram.setEnabled(false);
                jMenuNastavnici.setEnabled(false);

                jMenuPristupSistemu.setEnabled(true);
                jBtnLogout.setEnabled(false);
                jMenuAutor.setEnabled(false);
            } else {
                omoguceSamoKonekcijuNaServer();
            }

        } catch (Exception e) {
            System.out.println("Dogodila se greska prilikom onemogucivanja menija");
        }
    }

    void omoguciPristupSistemu() {
        if (Session.getInstance().getSocket() != null && !Session.getInstance().getSocket().isClosed()) {
            jMenuPristupSistemu.setEnabled(true);
            jMenuItemLogin.setEnabled(true);
            jMenuItemKonektujSe.setEnabled(false);
            jBtnLogout.setEnabled(false);
        } else {
            omoguceSamoKonekcijuNaServer();
        }
    }

    private void onemoguciMenijeLogout() {
        if (Session.getInstance().getSocket() != null && !Session.getInstance().getSocket().isClosed()) {
            jMenuPredmet.setEnabled(false);
            jMenuUdzbenik.setEnabled(false);

            jMenuItemLogin.setEnabled(true);
            jBtnLogout.setEnabled(false);
            jMenuNastavnici.setEnabled(false);

            jMenuStudijskiProgram.setEnabled(false);
            jMenuAutor.setEnabled(false);
            jMenuItemKonektujSe.setEnabled(false);
        } else {
            omoguceSamoKonekcijuNaServer();
        }
    }

    public JLabel getUlogovaniKorisnikLabel() {
        return jLabelUlogovaniKorisnik;
    }

    public void omoguceSamoKonekcijuNaServer() {
        jMenuPredmet.setEnabled(false);
        jMenuUdzbenik.setEnabled(false);
        jMenuStudijskiProgram.setEnabled(false);
        jMenuAutor.setEnabled(false);
        jMenuNastavnici.setEnabled(false);

        jMenuPristupSistemu.setEnabled(true);
        jMenuItemLogin.setEnabled(false);
        jBtnLogout.setEnabled(false);
        jMenuItemKonektujSe.setEnabled(true);

    }

    private void postaviBojuPozadine() {
        this.getContentPane().setBackground(new java.awt.Color(51, 0, 102));
    }

    private void postaviBojuSlovaMenuBar() {
        jMenuBar1.getComponent().setForeground(Color.white);
    }

}
