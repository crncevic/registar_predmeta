/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domen.Predmet;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import kontroler.Kontroler;
import table.model.PredmetTableModel;
import transfer.util.IOperation;

/**
 *
 * @author Petar
 */
public class FSelectPredmet extends javax.swing.JDialog {

    /**
     * Creates new form FSelectPredmet
     */
    public FSelectPredmet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        centrirajFormu();
        popuniTabelu();
        postaviSirinuKolona();
        pripremiTabeluZaSortiranje();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTxtNazivPredmeta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblPredmeti = new javax.swing.JTable();
        jBtnPrikazi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pretraga predmeta");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pronadjite predmet po nazivu"));

        jLabel1.setText("Unesite naziv predmeta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtNazivPredmeta)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtNazivPredmeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTblPredmeti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTblPredmeti);

        jBtnPrikazi.setText("Prikazi");
        jBtnPrikazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPrikaziActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnPrikazi, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnPrikazi)
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPrikaziActionPerformed
        try {
            int selectedRow = jTblPredmeti.getSelectedRow();

            if (selectedRow >= 0) {
                int predmetId = (int) jTblPredmeti.getValueAt(selectedRow, 0);
                dispose();
                JFrame fPredmet = new FPredmet(FMain.getInstance(), predmetId);
                fPredmet.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Niste selektovali nijedan predmet!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jBtnPrikaziActionPerformed

    private void centrirajFormu() {
        setLocationRelativeTo(null);
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnPrikazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblPredmeti;
    private javax.swing.JTextField jTxtNazivPredmeta;
    // End of variables declaration//GEN-END:variables

    private void popuniTabelu() {
        try {
            Kontroler.getInstance().posaljiZahtev(IOperation.VRATI_SVE_PREDMETE, null);
            List<Predmet> predmeti = (List<Predmet>) Kontroler.getInstance().primiOdgovor();

            TableModel ptm = new PredmetTableModel(predmeti);
            jTblPredmeti.setModel(ptm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void postaviSirinuKolona() {
        jTblPredmeti.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTblPredmeti.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    private void pripremiTabeluZaSortiranje() {
        try {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTblPredmeti.getModel());
            jTblPredmeti.setRowSorter(rowSorter);

            jTxtNazivPredmeta.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String filterPredmet = jTxtNazivPredmeta.getText();

                    if (filterPredmet.length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterPredmet));
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    String filterPredmet = jTxtNazivPredmeta.getText();

                    if (filterPredmet.length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterPredmet));
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}