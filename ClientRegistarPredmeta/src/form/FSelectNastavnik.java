/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domen.Nastavnik;
import java.awt.Color;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import kontroler.Kontroler;
import table.model.NastavnikTableModel;
import transfer.util.IOperation;

/**
 *
 * @author Petar
 */
public class FSelectNastavnik extends javax.swing.JDialog {

    /**
     * Creates new form FSelectNastavnik
     */
    public FSelectNastavnik(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        centrirajFormu();
        postaviBojuPozadine();
        postaviTableModel();
        pripremiTabeluZaSortiranje();
        postaviSirinuKolona();
              
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
        jTxtImeIPrezime = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblNastavnici = new javax.swing.JTable();
        jBtnPrikazi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pretraga nastavnika");

        jPanel1.setBackground(new java.awt.Color(51, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pronadjite nastavnika", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Unesite ime i prezime :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTxtImeIPrezime)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxtImeIPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTblNastavnici.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTblNastavnici);

        jBtnPrikazi.setBackground(new java.awt.Color(51, 204, 255));
        jBtnPrikazi.setForeground(new java.awt.Color(255, 255, 255));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnPrikazi)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnPrikazi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPrikaziActionPerformed
        try {
            int selektovaniRed = jTblNastavnici.getSelectedRow();

            if (selektovaniRed >= 0) {
                JDialog fNastavnik = new FNastavnik(FMain.getInstance(), true, (int) jTblNastavnici.getValueAt(selektovaniRed, 0));
                fNastavnik.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Niste selektovali nijednog nastavnika!</font></html>");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>" + e.getMessage() + "</font></html>");
        }
    }//GEN-LAST:event_jBtnPrikaziActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnPrikazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblNastavnici;
    private javax.swing.JTextField jTxtImeIPrezime;
    // End of variables declaration//GEN-END:variables

    private void centrirajFormu() {
        setLocationRelativeTo(null);
    }

    private void postaviBojuPozadine() {
        this.getContentPane().setBackground(new Color(51, 0, 102));
    }

    private void postaviTableModel() {
        try {
            Kontroler.getInstance().posaljiZahtev(IOperation.VRATI_SVE_NASTAVNIKE, null);
            List<Nastavnik> nastavnici = (List<Nastavnik>) Kontroler.getInstance().primiOdgovor();
            TableModel ntm = new NastavnikTableModel(nastavnici);
            jTblNastavnici.setModel(ntm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Dogodila se greska prilikom postavljanja table modela</font></html>");
        }
    }

    private void pripremiTabeluZaSortiranje() {
        try {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTblNastavnici.getModel());
            jTblNastavnici.setRowSorter(rowSorter);

            jTxtImeIPrezime.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String filterPredmet = jTxtImeIPrezime.getText();

                    if (filterPredmet.length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterPredmet));
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    String filterPredmet = jTxtImeIPrezime.getText();

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
            JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>" + e.getMessage() + "</font></html>");
        }
    }

    private void postaviSirinuKolona() {
        try {
          jTblNastavnici.getColumnModel().getColumn(0).setPreferredWidth(15);
        } catch (Exception e) {
          
        }
    }
}
