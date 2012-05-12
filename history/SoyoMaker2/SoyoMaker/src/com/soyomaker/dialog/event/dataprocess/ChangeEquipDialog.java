/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChangeDialog.java
 *
 * Created on 2011-6-13, 16:57:24
 */
package com.soyomaker.dialog.event.dataprocess;

import com.soyomaker.dialog.ScriptDialog;
import com.soyomaker.data.model.Equip;

/**
 *
 * @author Administrator
 */
public class ChangeEquipDialog extends javax.swing.JDialog {

    /** Creates new form ChangeDialog
     * @param parent 
     * @param modal 
     */
    public ChangeEquipDialog(ScriptDialog parent, boolean modal) {
        super(parent, modal);
        sd = parent;
        initComponents();
        setLocationRelativeTo(null);
    }
    private ScriptDialog sd;

    /**
     * 
     * @param type
     * @param id
     */
    public void operateEquip(byte type, int id) {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        helmComboBox = new javax.swing.JComboBox();
        okButton = new javax.swing.JButton();
        cancleButton = new javax.swing.JButton();
        shieldComboBox = new javax.swing.JComboBox();
        weaponComboBox = new javax.swing.JComboBox();
        armourComboBox = new javax.swing.JComboBox();
        bootsComboBox = new javax.swing.JComboBox();
        jewelryComboBox = new javax.swing.JComboBox();
        helmRadioButton = new javax.swing.JRadioButton();
        shieldRadioButton = new javax.swing.JRadioButton();
        weaponRadioButton = new javax.swing.JRadioButton();
        armourRadioButton = new javax.swing.JRadioButton();
        bootsRadioButton = new javax.swing.JRadioButton();
        jewelryRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ChangeEquipDialog.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setResizable(false);

        helmComboBox.setName("helmComboBox"); // NOI18N

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

        shieldComboBox.setName("shieldComboBox"); // NOI18N

        weaponComboBox.setName("weaponComboBox"); // NOI18N

        armourComboBox.setName("armourComboBox"); // NOI18N

        bootsComboBox.setName("bootsComboBox"); // NOI18N

        jewelryComboBox.setName("jewelryComboBox"); // NOI18N

        buttonGroup1.add(helmRadioButton);
        helmRadioButton.setSelected(true);
        helmRadioButton.setText("头盔");
        helmRadioButton.setName("helmRadioButton"); // NOI18N

        buttonGroup1.add(shieldRadioButton);
        shieldRadioButton.setText("盾牌");
        shieldRadioButton.setName("shieldRadioButton"); // NOI18N

        buttonGroup1.add(weaponRadioButton);
        weaponRadioButton.setText("武器");
        weaponRadioButton.setName("weaponRadioButton"); // NOI18N

        buttonGroup1.add(armourRadioButton);
        armourRadioButton.setText("铠甲");
        armourRadioButton.setName("armourRadioButton"); // NOI18N

        buttonGroup1.add(bootsRadioButton);
        bootsRadioButton.setText("战靴");
        bootsRadioButton.setName("bootsRadioButton"); // NOI18N

        buttonGroup1.add(jewelryRadioButton);
        jewelryRadioButton.setText("饰品");
        jewelryRadioButton.setName("jewelryRadioButton"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helmRadioButton)
                    .addComponent(shieldRadioButton)
                    .addComponent(weaponRadioButton)
                    .addComponent(armourRadioButton)
                    .addComponent(bootsRadioButton)
                    .addComponent(jewelryRadioButton)
                    .addComponent(okButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jewelryComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bootsComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(armourComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(weaponComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(shieldComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(helmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cancleButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(helmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(helmRadioButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shieldRadioButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weaponComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weaponRadioButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(armourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(armourRadioButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bootsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bootsRadioButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jewelryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jewelryRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancleButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
//        if (helmRadioButton.isSelected()) {
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "operateEquip(" + Equip.helm + "," + helmComboBox.getSelectedIndex() + ");");
//        }
//        if (shieldRadioButton.isSelected()) {
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "operateEquip(" + Equip.shield + "," + shieldComboBox.getSelectedIndex() + ");");
//        }
//        if (weaponRadioButton.isSelected()) {
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "operateEquip(" + Equip.weapon + "," + weaponComboBox.getSelectedIndex() + ");");
//        }
//        if (armourRadioButton.isSelected()) {
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "operateEquip(" + Equip.armour + "," + armourComboBox.getSelectedIndex() + ");");
//        }
//        if (bootsRadioButton.isSelected()) {
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "operateEquip(" + Equip.boots + "," + bootsComboBox.getSelectedIndex() + ");");
//        }
//        if (jewelryRadioButton.isSelected()) {
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "operateEquip(" + Equip.jewelry + "," + jewelryComboBox.getSelectedIndex() + ");");
//        }
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
                ChangeEquipDialog dialog = new ChangeEquipDialog(null, true);
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
    private javax.swing.JComboBox armourComboBox;
    private javax.swing.JRadioButton armourRadioButton;
    private javax.swing.JComboBox bootsComboBox;
    private javax.swing.JRadioButton bootsRadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancleButton;
    private javax.swing.JComboBox helmComboBox;
    private javax.swing.JRadioButton helmRadioButton;
    private javax.swing.JComboBox jewelryComboBox;
    private javax.swing.JRadioButton jewelryRadioButton;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox shieldComboBox;
    private javax.swing.JRadioButton shieldRadioButton;
    private javax.swing.JComboBox weaponComboBox;
    private javax.swing.JRadioButton weaponRadioButton;
    // End of variables declaration//GEN-END:variables
}
