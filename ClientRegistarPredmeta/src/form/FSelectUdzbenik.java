/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domen.Udzbenik;
import java.awt.Dimension;
import java.awt.Window;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import kontroler.Kontroler;
import table.model.UdzbenikTableModel;
import transfer.util.IOperation;

/**
 *
 * @author Petar
 */
public class FSelectUdzbenik extends javax.swing.JDialog {

    public FSelectUdzbenik(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        postaviTableModel();
        centrirajFormu();
        promeniSirinuKolona();
        pripremiTabeluZaSortiranje();
        postaviBojuPozadine();
    }

    /**
     * Creates new form FSelectUdzbenik
     */
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
        jTxtNazivUdzbenika = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblUdzbenici = new javax.swing.JTable();
        jBtnPrikazi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pretrazivanje udzbenika");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 0, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Pronadjite udzbenik po nazivu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Unesite naziv udzbenika:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTxtNazivUdzbenika)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTxtNazivUdzbenika, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTblUdzbenici.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTblUdzbenici);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 1123, Short.MAX_VALUE)
                        .addComponent(jBtnPrikazi))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jBtnPrikazi)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPrikaziActionPerformed
        try {
            int selectedRow = jTblUdzbenici.getSelectedRow();
            if (selectedRow >= 0) {
                UdzbenikTableModel utm = (UdzbenikTableModel) jTblUdzbenici.getModel();
                int udzbenikId = (int) utm.getValueAt(selectedRow, 0);
                dispose();
                JDialog fUdzbenik = new FUdzbenik(FMain.getInstance(), true, udzbenikId);
                fUdzbenik.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "<html><font color=#ffffff>Niste selektovali nijedan predmet!</font></html>");
            }
        } catch (Exception ex) {
            System.out.println("<html><font color=#ffffff>Dogodila se greska prilikom pregledanja udzbenika!</font></html>");
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
    private javax.swing.JTable jTblUdzbenici;
    private javax.swing.JTextField jTxtNazivUdzbenika;
    // End of variables declaration//GEN-END:variables

    private void postaviTableModel() {
        try {
            Kontroler.getInstance().posaljiZahtev(IOperation.VRATI_SVE_UDZBENIKE, new Udzbenik());
            List<Udzbenik> udzbenici = (List<Udzbenik>) Kontroler.getInstance().primiOdgovor();
            TableModel utm = new UdzbenikTableModel(udzbenici);
            jTblUdzbenici.setModel(utm);
        } catch (Exception e) {
            System.out.println("<html><font color=#ffffff>Dogodila se greska prilikom postavljanja table modela!</font></html>");
        }
    }

    private void centrirajFormu() {
        setLocationRelativeTo(null);
    }

    private void promeniSirinuKolona() {
        try{
        jTblUdzbenici.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTblUdzbenici.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTblUdzbenici.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTblUdzbenici.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTblUdzbenici.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTblUdzbenici.getColumnModel().getColumn(5).setPreferredWidth(200);
        jTblUdzbenici.getColumnModel().getColumn(6).setPreferredWidth(50);
        jTblUdzbenici.getColumnModel().getColumn(7).setPreferredWidth(10);
        jTblUdzbenici.getColumnModel().getColumn(8).setPreferredWidth(15);
        jTblUdzbenici.getColumnModel().getColumn(9).setPreferredWidth(80);
        }catch(Exception ex){
            
        }

    }

    private void pripremiTabeluZaSortiranje() {
        try {
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(jTblUdzbenici.getModel());
            jTblUdzbenici.setRowSorter(rowSorter);

            jTxtNazivUdzbenika.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String filterUdzbenik = jTxtNazivUdzbenika.getText();

                    if (filterUdzbenik.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterUdzbenik));
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    String filterUdzbenik = jTxtNazivUdzbenika.getText();

                    if (filterUdzbenik.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterUdzbenik));
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        } catch (Exception e) {
            System.out.println("<html><font color=#ffffff>Dogodila se greska kod postavljanja row sort-era</font></html>");
        }
    }

    private void postaviBojuPozadine() {
        this.getContentPane().setBackground(new java.awt.Color(51, 0, 102));
    }

}
