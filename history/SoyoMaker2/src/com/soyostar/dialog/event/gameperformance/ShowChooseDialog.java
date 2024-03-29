/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChooseDialog.java
 *
 * Created on 2011-6-12, 20:22:57
 */
package com.soyostar.dialog.event.gameperformance;

import com.soyostar.dialog.ScriptDialog;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class ShowChooseDialog extends javax.swing.JDialog {

    /** Creates new form ChooseDialog
     * @param parent
     * @param modal  
     */
    public ShowChooseDialog(ScriptDialog parent, boolean modal) {
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
        oneChooseTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        twoChooseTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        threeChooseTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fourChooseTextField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ShowChooseDialog.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setResizable(false);

        jLabel1.setText("选择项1");
        jLabel1.setName("jLabel1"); // NOI18N

        oneChooseTextField.setText("是");
        oneChooseTextField.setName("oneChooseTextField"); // NOI18N

        jLabel2.setText("选择项2");
        jLabel2.setName("jLabel2"); // NOI18N

        twoChooseTextField.setText("否");
        twoChooseTextField.setName("twoChooseTextField"); // NOI18N

        jLabel3.setText("选择项3");
        jLabel3.setName("jLabel3"); // NOI18N

        threeChooseTextField.setName("threeChooseTextField"); // NOI18N

        jLabel4.setText("选择项4");
        jLabel4.setName("jLabel4"); // NOI18N

        fourChooseTextField.setName("fourChooseTextField"); // NOI18N

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
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(okButton)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                        .addComponent(cancleButton))
                    .addComponent(fourChooseTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(threeChooseTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(twoChooseTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(oneChooseTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(oneChooseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(twoChooseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(threeChooseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fourChooseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        ArrayList v = new ArrayList();
        if (!"".equals(fourChooseTextField.getText())) {
            v.add(fourChooseTextField.getText());
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "}");
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "");
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "if(选择项数组[选择项].equals(\"" + fourChooseTextField.getText() + "\")){");
        }
        if (!"".equals(threeChooseTextField.getText())) {
            v.add(threeChooseTextField.getText());
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "}");
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "");
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "if(选择项数组[选择项].equals(\"" + threeChooseTextField.getText() + "\")){");
        }
        if (!"".equals(twoChooseTextField.getText())) {
            v.add(twoChooseTextField.getText());
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "}");
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "");
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "if(选择项数组[选择项].equals(\"" + twoChooseTextField.getText() + "\")){");
        }
        if (!"".equals(oneChooseTextField.getText())) {
            v.add(oneChooseTextField.getText());
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "}");
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "");
            sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
                "if(选择项数组[选择项].equals(\"" + oneChooseTextField.getText() + "\")){");
        }
        sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
            "int 选择项 = showDialog(选择项数组);");
        StringBuilder sb = new StringBuilder();
        sb.append("String[] 选择项数组 ={");
        for (int i = v.size() - 1; i >= 0; i--) {
            if (i == 0) {
                sb.append("\"").append(v.get(i)).append("\"");
            } else {
                sb.append("\"").append(v.get(i)).append("\"").append(",");
            }
        }
        sb.append("};");
        sd.insertScriptData(sd.npcPane.eventTable.getSelectedRow(),
            sb.toString());
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
                ShowChooseDialog dialog = new ShowChooseDialog(null, true);
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
    private javax.swing.JTextField fourChooseTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField oneChooseTextField;
    private javax.swing.JTextField threeChooseTextField;
    private javax.swing.JTextField twoChooseTextField;
    // End of variables declaration//GEN-END:variables
}
