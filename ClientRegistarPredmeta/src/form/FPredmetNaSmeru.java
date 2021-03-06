/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domen.Predmet;
import domen.PredmetNaStudijskomProgramu;
import domen.Status;
import domen.StudijskiProgram;
import java.awt.Color;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kontroler.Kontroler;
import renderer.PredmetRenderer;
import renderer.StatusRenderer;
import renderer.StudijskiProgramRenderer;
import session.Session;
import transfer.util.IOperation;

/**
 *
 * @author Petar
 */
public class FPredmetNaSmeru extends javax.swing.JDialog {

    /**
     * Creates new form FPredmetNaSmeru
     */
    public FPredmetNaSmeru(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        centerForm();
        popuniStudijskiProgramCombo();
        popuniPredmetiCombo();
        popuniStatusCombo();
        pripremiFormu(FormMode.NEW);
        postaviBojuPozadine();
    }

    public FPredmetNaSmeru(java.awt.Frame parent, boolean modal, PredmetNaStudijskomProgramu pnsp) {
        super(parent, modal);
        initComponents();
        centerForm();
        popuniStudijskiProgramCombo();
        popuniPredmetiCombo();
        popuniStatusCombo();
        postaviPredmetNaStudijskomProgramu(pnsp);
        pripremiFormu(FormMode.VIEW);
        postaviBojuPozadine();
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
        jComboSmerovi = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboPredmeti = new javax.swing.JComboBox<>();
        jSpinnerESPB = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboStatus = new javax.swing.JComboBox<>();
        jBtnObrisi = new javax.swing.JButton();
        jBtnAzuriraj = new javax.swing.JButton();
        jBtnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Predmeti na studijskom programu");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Izaberite std program:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Izaberite predmet:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Broj ESPB:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Status:");

        jBtnObrisi.setText("Obrisi");
        jBtnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnObrisiActionPerformed(evt);
            }
        });

        jBtnAzuriraj.setText("Azuriraj");
        jBtnAzuriraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAzurirajActionPerformed(evt);
            }
        });

        jBtnSacuvaj.setText("Sacuvaj");
        jBtnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSacuvajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jBtnSacuvaj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jBtnAzuriraj)
                        .addGap(27, 27, 27)
                        .addComponent(jBtnObrisi))
                    .addComponent(jComboPredmeti, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboSmerovi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSpinnerESPB, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboSmerovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboPredmeti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jSpinnerESPB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnObrisi)
                    .addComponent(jBtnAzuriraj)
                    .addComponent(jBtnSacuvaj))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAzurirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAzurirajActionPerformed
        try {
            if (Session.getInstance().getMap().get("ulogovani_korisnik") != null) {
                StudijskiProgram studijskiProgram = (StudijskiProgram) jComboSmerovi.getSelectedItem();
                Predmet predmet = (Predmet) jComboPredmeti.getSelectedItem();
                Status status = (Status) jComboStatus.getSelectedItem();

                int espb;

                try {
                    jSpinnerESPB.commitEdit();
                    espb = (int) jSpinnerESPB.getValue();

                    if (espb < 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dozvoljeno je unos samo celih nenegaticnih brojeva!</font></html>");
                    return;
                }

                PredmetNaStudijskomProgramu pnsp = new PredmetNaStudijskomProgramu(predmet, studijskiProgram, status, espb);

                Kontroler.getInstance().posaljiZahtev(IOperation.AZURIRAJ_PREDMET_NA_STUDIJSKOM_PROGRAMU, pnsp);
                PredmetNaStudijskomProgramu azuriranPredmetNaStudijskomProgramu = (PredmetNaStudijskomProgramu) Kontroler.getInstance().primiOdgovor();

                if (azuriranPredmetNaStudijskomProgramu != null) {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Predmet: " + predmet.getNaziv() + " je uspesno azuriran na studijskom programu:" + azuriranPredmetNaStudijskomProgramu.getStudijskiProgram().getNaziv() + "</font></html>");

                    dispose();
                    JDialog fSelectPredmetNaSmeru = new FSelectPredmetNaSmeru(FMain.getInstance(), true);
                    fSelectPredmetNaSmeru.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dogodila se greska prilikom azuriranja!</font></html>");
                }
            } else {
                JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Molim vas ulogujte se!</font></html>");
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dogodila se greska prilikom azuriranja!</font></html>");
        }
    }//GEN-LAST:event_jBtnAzurirajActionPerformed

    private void jBtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSacuvajActionPerformed
        try {
            if (Session.getInstance().getMap().get("ulogovani_korisnik") != null) {
                StudijskiProgram studijskiProgram = (StudijskiProgram) jComboSmerovi.getSelectedItem();
                Predmet predmet = (Predmet) jComboPredmeti.getSelectedItem();
                Status status = (Status) jComboStatus.getSelectedItem();

                Kontroler.getInstance().posaljiZahtev(IOperation.VRATI_PREDMETE_ZA_STUDIJSKI_PROGRAM, studijskiProgram);
                List<PredmetNaStudijskomProgramu> list = (List<PredmetNaStudijskomProgramu>) Kontroler.getInstance().primiOdgovor();

                for (PredmetNaStudijskomProgramu predmetNaStudijskomProgramu : list) {
                    if (predmetNaStudijskomProgramu.getPredmet().equals(predmet)) {
                        JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Predmet " + predmet.getNaziv() + " vec postoji na tom studijskom programu!</font></html>");
                        return;
                    }
                }

                int espb;

                try {
                    jSpinnerESPB.commitEdit();
                    espb = (int) jSpinnerESPB.getValue();

                    if (espb < 0 || espb > 50) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dozvoljeno je unos samo celih nenegaticnih brojeva!</font></html>");
                    return;
                }

                PredmetNaStudijskomProgramu pnsp = new PredmetNaStudijskomProgramu(predmet, studijskiProgram, status, espb);

                Kontroler.getInstance().posaljiZahtev(IOperation.KREIRAJ_PREDMET_NA_STUDIJSKOM_PROGRAMU, pnsp);
                PredmetNaStudijskomProgramu kreiranPredmetNaStudijskomProgramu = (PredmetNaStudijskomProgramu) Kontroler.getInstance().primiOdgovor();

                if (kreiranPredmetNaStudijskomProgramu != null) {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Sistem je zapamtio predmet na studijskom programu!</font></html>");
                    dispose();
                    JDialog fSelectPredmetNaSmeru = new FSelectPredmetNaSmeru(FMain.getInstance(), true);
                    fSelectPredmetNaSmeru.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dogodila se greska prilikom azuriranja!</font></html>");
                }
            } else {
                JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Molim vas ulogujte se!</font></html>");
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dogodila se greska prilikom kreiranja!</font></html>");
            dispose();
        }
    }//GEN-LAST:event_jBtnSacuvajActionPerformed

    private void jBtnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnObrisiActionPerformed
        try {
            if (Session.getInstance().getMap().get("ulogovani_korisnik") != null) {
                StudijskiProgram studijskiProgram = (StudijskiProgram) jComboSmerovi.getSelectedItem();
                Predmet predmet = (Predmet) jComboPredmeti.getSelectedItem();
                Status status = (Status) jComboStatus.getSelectedItem();

                int espb;

                try {
                    jSpinnerESPB.commitEdit();
                    espb = (int) jSpinnerESPB.getValue();

                    if (espb < 0 || espb > 50) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dozvoljeno je unos samo celih nenegaticnih brojeva!</font></html>");
                    return;
                }

                PredmetNaStudijskomProgramu pnsp = new PredmetNaStudijskomProgramu(predmet, studijskiProgram, status, espb);

                Kontroler.getInstance().posaljiZahtev(IOperation.OBRISI_PREDMET_NA_STUDIJSKOM_PROGRAMU, pnsp);
                PredmetNaStudijskomProgramu obrisanPredmetNaStdProgramu = (PredmetNaStudijskomProgramu) Kontroler.getInstance().primiOdgovor();

                if (obrisanPredmetNaStdProgramu != null) {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Predmet: " + predmet.getNaziv() + " je uspesno obrisan  sa studijskog programa: " + studijskiProgram.getNaziv() + "</font></html>");

                    dispose();
                    JDialog fSelectPredmetNaSmeru = new FSelectPredmetNaSmeru(FMain.getInstance(), true);
                    fSelectPredmetNaSmeru.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dogodila se greska prilikom brisanja!</font></html>");
                }
            } else {
                JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Molim vas ulogujte se!</font></html>");
                dispose();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dogodila se greska prilikom brisanja!</font></html>");
        }
    }//GEN-LAST:event_jBtnObrisiActionPerformed

    /**
     * @param args the command line arguments
     */
    private void centerForm() {
        setLocationRelativeTo(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAzuriraj;
    private javax.swing.JButton jBtnObrisi;
    private javax.swing.JButton jBtnSacuvaj;
    private javax.swing.JComboBox<Object> jComboPredmeti;
    private javax.swing.JComboBox<Object> jComboSmerovi;
    private javax.swing.JComboBox<Object> jComboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner jSpinnerESPB;
    // End of variables declaration//GEN-END:variables

    private void popuniStudijskiProgramCombo() {
        try {
            Kontroler.getInstance().posaljiZahtev(IOperation.VRATI_SVE_STUDIJSKE_PROGRAME, new StudijskiProgram());
            List<StudijskiProgram> studijskiProgrami = (List<StudijskiProgram>) Kontroler.getInstance().primiOdgovor();

            for (StudijskiProgram studijskiProgram : studijskiProgrami) {
                jComboSmerovi.addItem(studijskiProgram);
            }

            jComboSmerovi.setRenderer(new StudijskiProgramRenderer());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>" + e.getMessage() + "</font></html>");
        }
    }

    private void popuniPredmetiCombo() {
        try {
            Kontroler.getInstance().posaljiZahtev(IOperation.VRATI_SVE_PREDMETE, new Predmet());
            List<Predmet> predmeti = (List<Predmet>) Kontroler.getInstance().primiOdgovor();

            for (Predmet predmet : predmeti) {
                jComboPredmeti.addItem(predmet);
            }

            jComboPredmeti.setRenderer(new PredmetRenderer());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>" + e.getMessage() + "</font></html>");
        }
    }

    private void popuniStatusCombo() {
        try {
            Kontroler.getInstance().posaljiZahtev(IOperation.VRATI_SVE_STATUSE, new Status());
            List<Status> statusi = (List<Status>) Kontroler.getInstance().primiOdgovor();

            for (Status status : statusi) {
                jComboStatus.addItem(status);
            }

            jComboStatus.setRenderer(new StatusRenderer());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>" + e.getMessage() + "</font></html>");
        }
    }

    private void pripremiFormu(FormMode formMode) {
        switch (formMode) {
            case NEW:
                jComboPredmeti.setEnabled(true);
                jComboSmerovi.setEnabled(true);
                jBtnObrisi.setEnabled(false);
                jBtnAzuriraj.setEnabled(false);
                jBtnSacuvaj.setEnabled(true);

                jBtnSacuvaj.setForeground(Color.white);
                jBtnSacuvaj.setBackground(Color.green);
                break;
            case VIEW:
                jComboPredmeti.setEnabled(false);
                jComboSmerovi.setEnabled(false);
                jBtnObrisi.setEnabled(true);
                jBtnAzuriraj.setEnabled(true);
                jBtnSacuvaj.setEnabled(false);

                jBtnAzuriraj.setForeground(Color.white);
                jBtnAzuriraj.setBackground(new Color(204, 204, 0));

                jBtnObrisi.setForeground(Color.white);
                jBtnObrisi.setBackground(Color.red);

                break;

        }
    }

    private void postaviPredmetNaStudijskomProgramu(PredmetNaStudijskomProgramu pnsp) {
        try {
            Kontroler.getInstance().posaljiZahtev(IOperation.PRONADJI_PREDMET_NA_STUDIJSKOM_PROGRAMU_ZA_ID, pnsp);
            PredmetNaStudijskomProgramu predmet = (PredmetNaStudijskomProgramu) Kontroler.getInstance().primiOdgovor();

            jComboPredmeti.setSelectedItem(predmet.getPredmet());
            jComboStatus.setSelectedItem(predmet.getStatus());
            jComboSmerovi.setSelectedItem(predmet.getStudijskiProgram());
            jSpinnerESPB.setValue(predmet.getEspb());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>" + e.getMessage() + "</font></html>");
        }
    }

    private void postaviBojuPozadine() {
        this.getContentPane().setBackground(new java.awt.Color(51, 0, 102));
    }
}
