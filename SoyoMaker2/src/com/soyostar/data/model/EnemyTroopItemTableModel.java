/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.model;

import com.soyostar.data.DataManager;
import com.soyostar.dialog.DataEditorDialog;
import com.soyostar.project.Project;
import com.soyostar.proxy.SoftProxy;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class EnemyTroopItemTableModel extends AbstractTableModel {

    private DataEditorDialog ded;

    /**
     * 
     * @param ded
     */
    public EnemyTroopItemTableModel(DataEditorDialog ded) {
        this.ded = ded;
    }
    private static final String ENEMYTROOPITEM_COLUMN_NAME[] = {
        "ID", "名称"
    };
    private static final Class ENEMYTROOPITEM_COLUMN_CLASS[] = {
        Integer.class, String.class
    };

    @Override
    public String getColumnName(int c) {
        return ENEMYTROOPITEM_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return ENEMYTROOPITEM_COLUMN_CLASS[c];
    }

    @Override
    public int getColumnCount() {
        return ENEMYTROOPITEM_COLUMN_NAME.length;
    }
    private DataManager data = SoftProxy.getInstance().getCurProject().getDataManager();

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (ded.getCurSelectEnemyTroopIndex() < 0 || ded.getCurSelectEnemyTroopIndex() >= data.getModels(Model.ENEMYTROOP).length) {
            return null;
        }
        EnemyTroop troop = (EnemyTroop) data.getModels(Model.ENEMYTROOP)[ded.getCurSelectEnemyTroopIndex()];
        switch (columnIndex) {
            case 0:
                return troop.itemList.get(rowIndex).getIndex();
            case 1:
                return troop.itemList.get(rowIndex).name;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

//    @Override
//    public void setValueAt(Object v, int r, int c) {
//    }
    @Override
    public int getRowCount() {
        if (ded.getCurSelectEnemyTroopIndex() < 0 || ded.getCurSelectEnemyTroopIndex() >= data.getModels(Model.ENEMYTROOP).length) {
            return 0;
        }
        EnemyTroop enemyTroop = (EnemyTroop) data.getModels(Model.ENEMYTROOP)[ded.getCurSelectEnemyTroopIndex()];
        return enemyTroop.itemList.size();
    }
}
