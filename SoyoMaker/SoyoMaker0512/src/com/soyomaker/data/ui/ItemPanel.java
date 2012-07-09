/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemPanel.java
 *
 * Created on 2011-9-3, 23:40:40
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
import com.soyomaker.data.model.ItemTableModel;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Player;
import com.soyomaker.data.model.Skill;
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
public class ItemPanel extends javax.swing.JPanel implements DataChangeListener {

    /** Creates new form ItemPanel */
    public ItemPanel() {
        initComponents();
        AppData.getInstance().getCurProject().getDataManager().addDataChangeListener(this);
        TableColumnModel tcm = itemTable.getColumnModel();
//        ImageIcon[] itemIcons = DataManager.listItemIconName();
//        TableColumn iconC = tcm.getColumn(3);
//        JTabelComboBoxRender strI = new JTabelComboBoxRender(itemIcons);
//        JTableComboBoxEditor steI = new JTableComboBoxEditor(itemIcons);
//        iconC.setCellRenderer(strI);
//        iconC.setCellEditor(steI);

        TableColumn targetC = tcm.getColumn(5);
        JTabelComboBoxRender strT = new JTabelComboBoxRender(Item.types);
        JTableComboBoxEditor steT = new JTableComboBoxEditor(Item.types);
        targetC.setCellRenderer(strT);
        targetC.setCellEditor(steT);
    }
    private ItemTableModel itm = new ItemTableModel();

    /**
     *
     */
    public void refresh() {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        addItemButton = new javax.swing.JButton();
        editItemButton = new javax.swing.JButton();
        removeItemButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        addItemButton.setText("添加物品");
        addItemButton.setFocusable(false);
        addItemButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addItemButton.setName("addItemButton"); // NOI18N
        addItemButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addItemButton);

        editItemButton.setText("编辑物品");
        editItemButton.setFocusable(false);
        editItemButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editItemButton.setName("editItemButton"); // NOI18N
        editItemButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(editItemButton);

        removeItemButton.setText("删除物品");
        removeItemButton.setFocusable(false);
        removeItemButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeItemButton.setName("removeItemButton"); // NOI18N
        removeItemButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeItemButton);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        itemTable.setModel(itm);
        itemTable.setName("itemTable"); // NOI18N
        itemTable.setRowHeight(20);
        jScrollPane1.setViewportView(itemTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        // TODO add your handling code here:
        Item item = new Item();
        item.setIndex(AppData.getInstance().getCurProject().getDataManager().allocateIndex(Model.ITEM));
        AppData.getInstance().getCurProject().getDataManager().saveModel(Model.ITEM, item);
        EditItemDialog eid = new EditItemDialog(this, true, item);
        eid.setVisible(true);
    }//GEN-LAST:event_addItemButtonActionPerformed

    private void editItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemButtonActionPerformed
        // TODO add your handling code here:
        int id = itemTable.getSelectedRow();
        if (id < 0 || id >= AppData.getInstance().getCurProject().getDataManager().size(Model.ITEM)) {
            return;
        }
        Item item = (Item) AppData.getInstance().getCurProject().getDataManager().getModels(Model.ITEM)[id];
        EditItemDialog eid = new EditItemDialog(this, true, item);
        eid.setVisible(true);
    }//GEN-LAST:event_editItemButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        // TODO add your handling code here:
        int id = itemTable.getSelectedRow();
        if (id < 0 || id >= AppData.getInstance().getCurProject().getDataManager().size(Model.ITEM)) {
            return;
        }
        AppData.getInstance().getCurProject().getDataManager().removeModel(Model.ITEM, AppData.getInstance().getCurProject().getDataManager().getModels(Model.ITEM)[id].getIndex());
    }//GEN-LAST:event_removeItemButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton editItemButton;
    private javax.swing.JTable itemTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton removeItemButton;
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
    }

    public void skillRemoved(DataChangedEvent sce, int id) {
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
        itemTable.updateUI();
    }

    public void itemRemoved(DataChangedEvent sce, int id) {
        itemTable.updateUI();
    }
}
