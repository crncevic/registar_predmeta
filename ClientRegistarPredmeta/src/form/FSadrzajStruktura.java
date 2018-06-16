/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domen.TematskaCelina;
import domen.TipNastave;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import session.Session;

/**
 *
 * @author Petar
 */
public class FSadrzajStruktura extends javax.swing.JDialog {

    private TipNastave tipNastave;
    TematskaCelina tematskaCelina;
    int brojac;

    /**
     * Creates new form FSadrzajStruktura
     */
    public FSadrzajStruktura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        centrirajFormu();
        postaviKoreniElement();
        tipNastave = Session.getInstance().getTipNastave();
        brojac = 1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeSadrzaj = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        jTxtNazivTematskeJedinice = new javax.swing.JTextField();
        jBtnDodajTematskuJedinicu = new javax.swing.JButton();
        jTxtNazivPodtematskeJedinice = new javax.swing.JTextField();
        jBtnDodajPodtematskuJedinicu = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jBtnDelete = new javax.swing.JButton();
        jBtnSacuvaj = new javax.swing.JButton();
        jBtnSacuvajOpisNJ = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaOpisNJ = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sadrzaj  struktura");
        setResizable(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Sadrzaj");
        jTreeSadrzaj.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTreeSadrzaj);

        jLabel1.setText("Naziv tematske jedinice:");

        jBtnDodajTematskuJedinicu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnDodajTematskuJedinicu.setText(">>");
        jBtnDodajTematskuJedinicu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDodajTematskuJedinicuActionPerformed(evt);
            }
        });

        jBtnDodajPodtematskuJedinicu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnDodajPodtematskuJedinicu.setText(">>");
        jBtnDodajPodtematskuJedinicu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDodajPodtematskuJedinicuActionPerformed(evt);
            }
        });

        jLabel2.setText("Naziv podtematske jedinice:");

        jBtnDelete.setText("Obrisi");
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteActionPerformed(evt);
            }
        });

        jBtnSacuvaj.setText("Sacuvaj");
        jBtnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSacuvajActionPerformed(evt);
            }
        });

        jBtnSacuvajOpisNJ.setText("Sacuvaj opis");
        jBtnSacuvajOpisNJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSacuvajOpisNJActionPerformed(evt);
            }
        });

        jTextAreaOpisNJ.setColumns(20);
        jTextAreaOpisNJ.setRows(5);
        jScrollPane2.setViewportView(jTextAreaOpisNJ);

        jLabel3.setText("Unesite opis za selektovanu nastavnu jedinicu:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBtnSacuvajOpisNJ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnSacuvaj)
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jBtnDelete)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jTxtNazivTematskeJedinice, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jBtnDodajTematskuJedinicu))
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtNazivPodtematskeJedinice, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBtnDodajPodtematskuJedinicu))
                                    .addComponent(jScrollPane2)))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtNazivTematskeJedinice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnDodajTematskuJedinicu))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtNazivPodtematskeJedinice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnDodajPodtematskuJedinicu))
                        .addGap(18, 18, 18)
                        .addComponent(jBtnDelete)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnSacuvaj)
                            .addComponent(jBtnSacuvajOpisNJ))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnDodajTematskuJedinicuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDodajTematskuJedinicuActionPerformed
        DefaultTreeModel dtm = (DefaultTreeModel) jTreeSadrzaj.getModel();
        DefaultMutableTreeNode koren = (DefaultMutableTreeNode) dtm.getRoot();

        String tematskaJedinicaNaziv = jTxtNazivTematskeJedinice.getText();

        if (tematskaJedinicaNaziv.trim().length() > 0) {

            String opis = jTextAreaOpisNJ.getText().trim();
            TematskaCelina tm = new TematskaCelina();
            tm.setNadredjenaTematskaCelina(null);
            tm.setNaziv(tematskaJedinicaNaziv);
            tm.setOpis(opis);
            // tm.setTipNastaveId(tipNastave.getTipNastaveId());
            tm.setTematskaCelinaId(brojac++);
            DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(tm);
            System.out.println(dmtn.getUserObject().getClass());
            koren.add(new DefaultMutableTreeNode(tm));
            jTxtNazivTematskeJedinice.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Morate uneti naziv tematske jedinice");
        }
    }//GEN-LAST:event_jBtnDodajTematskuJedinicuActionPerformed

    private void jBtnDodajPodtematskuJedinicuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDodajPodtematskuJedinicuActionPerformed
        DefaultTreeModel dtm = (DefaultTreeModel) jTreeSadrzaj.getModel();
        DefaultMutableTreeNode selektovaniCvor = (DefaultMutableTreeNode) jTreeSadrzaj.getLastSelectedPathComponent();

        String podtematskaJedinicaNaziv = jTxtNazivPodtematskeJedinice.getText();
        if (podtematskaJedinicaNaziv.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Morate uneti naziv  podtematske jedinice");
            return;
        }

        String opis = jTextAreaOpisNJ.getText().trim();

        if (selektovaniCvor != null) {
            if (!selektovaniCvor.isRoot()) {

                TematskaCelina tematskaCelina = new TematskaCelina();
                tematskaCelina.setNaziv(podtematskaJedinicaNaziv);
                tematskaCelina.setTematskaCelinaId(brojac++);
                //   tematskaCelina.setTipNastaveId(tipNastave.getTipNastaveId());
                tematskaCelina.setOpis(opis);
                TreePath path = new TreePath(selektovaniCvor.getParent());
                tematskaCelina.setNadredjenaTematskaCelina((TematskaCelina) ((DefaultMutableTreeNode) path.getLastPathComponent()).getUserObject());

                DefaultMutableTreeNode podtematskJedinica = new DefaultMutableTreeNode(tematskaCelina);

                dtm.insertNodeInto(podtematskJedinica, selektovaniCvor, selektovaniCvor.getChildCount());
                jTxtNazivPodtematskeJedinice.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Izabrali ste koreni cvor. Morate izbrati konkretnu tematsku jedinicu! ");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Niste selektovali nijednu tematsku jedinicu! ");
        }


    }//GEN-LAST:event_jBtnDodajPodtematskuJedinicuActionPerformed

    private void jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteActionPerformed
        DefaultTreeModel dtm = (DefaultTreeModel) jTreeSadrzaj.getModel();
        DefaultMutableTreeNode selektovaniCvor = (DefaultMutableTreeNode) jTreeSadrzaj.getLastSelectedPathComponent();

        if (selektovaniCvor == null) {
            JOptionPane.showMessageDialog(this, "Niste selektovali nijednu tematsku jedinicu za brisanje");
            return;
        }

        if (selektovaniCvor.isRoot()) {
            JOptionPane.showMessageDialog(this, "Ne mozete izbrisati koreni element! ");
            return;
        }

        dtm.removeNodeFromParent(selektovaniCvor);


    }//GEN-LAST:event_jBtnDeleteActionPerformed

    private void jBtnSacuvajOpisNJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSacuvajOpisNJActionPerformed
        DefaultTreeModel dtm = (DefaultTreeModel) jTreeSadrzaj.getModel();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTreeSadrzaj.getLastSelectedPathComponent();

        String opis = jTextAreaOpisNJ.getText().trim();

        if (selectedNode == null) {
            JOptionPane.showMessageDialog(this, "Morate selektovati nastavnu jedinicu za koju zelite da unesete opis!");
            return;
        } else if (opis.length() == 0) {
            JOptionPane.showMessageDialog(this, "Opis ne moze biti prazan!");
            return;
        }
    }//GEN-LAST:event_jBtnSacuvajOpisNJActionPerformed

    private void jBtnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSacuvajActionPerformed

    }//GEN-LAST:event_jBtnSacuvajActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnDodajPodtematskuJedinicu;
    private javax.swing.JButton jBtnDodajTematskuJedinicu;
    private javax.swing.JButton jBtnSacuvaj;
    private javax.swing.JButton jBtnSacuvajOpisNJ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaOpisNJ;
    private javax.swing.JTree jTreeSadrzaj;
    private javax.swing.JTextField jTxtNazivPodtematskeJedinice;
    private javax.swing.JTextField jTxtNazivTematskeJedinice;
    // End of variables declaration//GEN-END:variables

    private void centrirajFormu() {
        setLocationRelativeTo(null);
    }

    private void postaviKoreniElement() {
        TematskaCelina tm = new TematskaCelina();
        //   tematskaCelina
    }
}
