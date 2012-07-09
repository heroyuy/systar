/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SkillPanel.java
 *
 * Created on 2011-9-3, 17:32:19
 */
package com.soyomaker.data.ui;

import com.soyomaker.AppData;
import com.soyomaker.data.DataManager;
import com.soyomaker.data.listener.DataChangeListener;
import com.soyomaker.data.listener.DataChangedEvent;
import com.soyomaker.data.model.Config;
import com.soyomaker.data.model.Enemy;
import com.soyomaker.data.model.EnemyTroop;
import com.soyomaker.data.model.Equip;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Player;
import com.soyomaker.data.model.Skill;
import com.soyomaker.data.model.SkillTableModel;
import com.soyomaker.data.model.Buff;
import com.soyomaker.data.model.Vocation;
import com.soyomaker.widget.JTabelComboBoxRender;
import com.soyomaker.widget.JTableComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Administrator
 */
public class SkillPanel extends javax.swing.JPanel implements DataChangeListener {

    /** Creates new form SkillPanel */
    public SkillPanel() {
        initComponents();
        AppData.getInstance().getCurProject().getDataManager().addDataChangeListener(this);
        TableColumnModel tcm = skillTable.getColumnModel();

        TableColumn targetC = tcm.getColumn(5);
        JTabelComboBoxRender strT = new JTabelComboBoxRender(Skill.targets);
        JTableComboBoxEditor steT = new JTableComboBoxEditor(Skill.targets);
        targetC.setCellRenderer(strT);
        targetC.setCellEditor(steT);
        TableColumn limitC = tcm.getColumn(6);
        JTabelComboBoxRender strL = new JTabelComboBoxRender(Skill.types);
        JTableComboBoxEditor steL = new JTableComboBoxEditor(Skill.types);
        limitC.setCellRenderer(strL);
        limitC.setCellEditor(steL);
    }

    /**
     *
     */
    public void refresh() {
    }

//    private ImageIcon[] listFileName(String path) {
//
//        File f = new File(AppData.getInstance().getCurProject().getPath() + File.separator + path);
//        String[] s = f.list();
//        ImageIcon[] ii = new ImageIcon[s.length];
//        for (int i = 0; i < s.length; i++) {
//            ii[i] = new ImageIcon(AppData.getInstance().getCurProject().getPath() + File.separator + path + File.separator
//                    + s[i], s[i]);
//        }
//        return ii;
//    }
    private SkillTableModel skillTableModel = new SkillTableModel();

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        skillTable = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        addSkillButton = new javax.swing.JButton();
        editSkillButton = new javax.swing.JButton();
        removeSkillButton = new javax.swing.JButton();

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        skillTable.setModel(skillTableModel);
        skillTable.setName("skillTable"); // NOI18N
        skillTable.setRowHeight(20);
        jScrollPane1.setViewportView(skillTable);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        addSkillButton.setText("添加技能");
        addSkillButton.setFocusable(false);
        addSkillButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addSkillButton.setName("addSkillButton"); // NOI18N
        addSkillButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addSkillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSkillButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addSkillButton);

        editSkillButton.setText("编辑技能");
        editSkillButton.setFocusable(false);
        editSkillButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editSkillButton.setName("editSkillButton"); // NOI18N
        editSkillButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editSkillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSkillButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(editSkillButton);

        removeSkillButton.setText("删除技能");
        removeSkillButton.setFocusable(false);
        removeSkillButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeSkillButton.setName("removeSkillButton"); // NOI18N
        removeSkillButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeSkillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSkillButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeSkillButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addSkillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSkillButtonActionPerformed
        // TODO add your handling code here:
        Skill skill = new Skill();
        skill.setIndex(AppData.getInstance().getCurProject().getDataManager().allocateIndex(Model.SKILL));
        AppData.getInstance().getCurProject().getDataManager().saveModel(Model.SKILL, skill);
        EditSkillDialog eid = new EditSkillDialog(this, true, skill);
        eid.setVisible(true);
    }//GEN-LAST:event_addSkillButtonActionPerformed

    private void editSkillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSkillButtonActionPerformed
        // TODO add your handling code here:
        int id = skillTable.getSelectedRow();
        if (id < 0 || id >= AppData.getInstance().getCurProject().getDataManager().size(Model.SKILL)) {
            return;
        }
        Skill skill = (Skill) AppData.getInstance().getCurProject().getDataManager().getModels(Model.SKILL)[id];
        EditSkillDialog eid = new EditSkillDialog(this, true, skill);
        eid.setVisible(true);
    }//GEN-LAST:event_editSkillButtonActionPerformed

    private void removeSkillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSkillButtonActionPerformed
        // TODO add your handling code here:
        int id = skillTable.getSelectedRow();
        if (id < 0 || id >= AppData.getInstance().getCurProject().getDataManager().size(Model.SKILL)) {
            return;
        }
        AppData.getInstance().getCurProject().getDataManager().removeModel(Model.SKILL, AppData.getInstance().getCurProject().getDataManager().getModels(Model.SKILL)[id].getIndex());
    }//GEN-LAST:event_removeSkillButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSkillButton;
    private javax.swing.JButton editSkillButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton removeSkillButton;
    private javax.swing.JTable skillTable;
    // End of variables declaration//GEN-END:variables

    public void configChanged(DataChangedEvent sce, Config config) {
    }

    public void playerAdded(DataChangedEvent sce, Player player) {
    }

    public void playerRemoved(DataChangedEvent sce, int id) {
    }

    public void vocationAdded(DataChangedEvent sce, Vocation vocation) {
    }

    public void vocationRemoved(DataChangedEvent sce, int id) {
    }

    public void statusAdded(DataChangedEvent sce, Buff status) {
    }

    public void statusRemoved(DataChangedEvent sce, int id) {
    }

    public void skillAdded(DataChangedEvent sce, Skill skill) {
        skillTable.updateUI();
    }

    public void skillRemoved(DataChangedEvent sce, int id) {
        skillTable.updateUI();
    }

    public void enemyAdded(DataChangedEvent sce, Enemy enemy) {
    }

    public void enemyRemoved(DataChangedEvent sce, int id) {
    }

    public void enemyTroopAdded(DataChangedEvent sce, EnemyTroop enemytroop) {
    }

    public void enemyTroopRemoved(DataChangedEvent sce, int id) {
    }

    public void equipAdded(DataChangedEvent sce, Equip equip) {
    }

    public void equipRemoved(DataChangedEvent sce, int id) {
    }

    public void itemAdded(DataChangedEvent sce, Item item) {
    }

    public void itemRemoved(DataChangedEvent sce, int id) {
    }
}
