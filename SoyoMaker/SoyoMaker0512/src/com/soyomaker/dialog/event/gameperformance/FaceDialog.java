/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FaceDialog.java
 *
 * Created on 2011-5-14, 18:00:51
 */
package com.soyomaker.dialog.event.gameperformance;

import com.soyomaker.dialog.AbCommandDialog;
import com.soyomaker.dialog.ScriptDialog;
import com.soyomaker.model.map.CommandFactory;

/**
 *
 * @author Administrator
 */
public class FaceDialog extends AbCommandDialog {

    /** Creates new form FaceDialog
     * @param parent 
     * @param modal
     * @param typeId
     */
    public FaceDialog(ScriptDialog parent, boolean modal, int typeId) {
        super(parent, modal, typeId);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        upRadioButton = new javax.swing.JRadioButton();
        downRadioButton = new javax.swing.JRadioButton();
        leftRadioButton = new javax.swing.JRadioButton();
        rightRadioButton = new javax.swing.JRadioButton();
        okButton = new javax.swing.JButton();
        cancleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FaceDialog.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("面向"));
        jPanel1.setName("jPanel1"); // NOI18N

        buttonGroup1.add(upRadioButton);
        upRadioButton.setSelected(true);
        upRadioButton.setText("上");
        upRadioButton.setName("upRadioButton"); // NOI18N

        buttonGroup1.add(downRadioButton);
        downRadioButton.setText("下");
        downRadioButton.setName("downRadioButton"); // NOI18N

        buttonGroup1.add(leftRadioButton);
        leftRadioButton.setText("左");
        leftRadioButton.setName("leftRadioButton"); // NOI18N

        buttonGroup1.add(rightRadioButton);
        rightRadioButton.setText("右");
        rightRadioButton.setName("rightRadioButton"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(upRadioButton)
                .addGap(45, 45, 45)
                .addComponent(downRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(leftRadioButton)
                .addGap(43, 43, 43)
                .addComponent(rightRadioButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upRadioButton)
                    .addComponent(rightRadioButton)
                    .addComponent(leftRadioButton)
                    .addComponent(downRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancleButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancleButton)
                    .addComponent(okButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        int face = 0;
        if (upRadioButton.isSelected()) {
            face = 0;
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "globalData.proxy:changeFace(0)");
        } else if (downRadioButton.isSelected()) {
            face = 1;
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "globalData.proxy:changeFace(1)");
        } else if (leftRadioButton.isSelected()) {
            face = 2;
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "globalData.proxy:changeFace(2)");
        } else if (rightRadioButton.isSelected()) {
            face = 3;
//            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(), "globalData.proxy:changeFace(3)");
        }
        mCommand = CommandFactory.createCommand(mTypeId);
        mCommand.addParameter("" + face);
        System.out.println(mCommand);
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancleButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancleButton;
    private javax.swing.JRadioButton downRadioButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton leftRadioButton;
    private javax.swing.JButton okButton;
    private javax.swing.JRadioButton rightRadioButton;
    private javax.swing.JRadioButton upRadioButton;
    // End of variables declaration//GEN-END:variables
}