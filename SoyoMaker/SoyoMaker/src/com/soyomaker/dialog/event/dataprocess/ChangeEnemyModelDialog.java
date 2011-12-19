/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChangeEnemyModelDialog.java
 *
 * Created on 2011-6-13, 17:02:43
 */
package com.soyomaker.dialog.event.dataprocess;

import com.soyomaker.dialog.ScriptDialog;

/**
 *
 * @author Administrator
 */
public class ChangeEnemyModelDialog extends javax.swing.JDialog {

    /** Creates new form ChangeEnemyModelDialog
     * @param parent 
     * @param modal 
     */
    public ChangeEnemyModelDialog(ScriptDialog parent, boolean modal) {
        super(parent, modal);
        sd = parent;
        initComponents();
        setLocationRelativeTo(null);
    }
    private ScriptDialog sd;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        enemyComboBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();
        cancleButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        enemyNameComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ChangeEnemyModelDialog.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setResizable(false);

        jLabel1.setText("敌人");
        jLabel1.setName("jLabel1"); // NOI18N

        enemyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "全部队伍", "A", "B", "C", "D" }));
        enemyComboBox.setName("enemyComboBox"); // NOI18N

        okButton.setText("确定");
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancleButton.setText("取消");
        cancleButton.setName("cancleButton"); // NOI18N
        cancleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("变身");
        jLabel3.setName("jLabel3"); // NOI18N

        enemyNameComboBox.setName("enemyNameComboBox"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(enemyComboBox, 0, 197, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addComponent(enemyNameComboBox, 0, 197, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(cancleButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enemyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(enemyNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancleButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        switch (enemyComboBox.getSelectedIndex()) {
            case 0:
                sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "setEnemy(" + 0 + "," + enemyNameComboBox.getSelectedIndex() + ");");
                sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "setEnemy(" + 1 + "," + enemyNameComboBox.getSelectedIndex() + ");");
                sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "setEnemy(" + 2 + "," + enemyNameComboBox.getSelectedIndex() + ");");
                sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "setEnemy(" + 3 + "," + enemyNameComboBox.getSelectedIndex() + ");");
                break;
            default:
                sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "setEnemy(" + (enemyComboBox.getSelectedIndex() - 1) + "," + enemyNameComboBox.getSelectedIndex() + ");");
                break;
        }
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cancleButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ChangeEnemyModelDialog dialog = new ChangeEnemyModelDialog(null, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancleButton;
    private javax.swing.JComboBox enemyComboBox;
    private javax.swing.JComboBox enemyNameComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}