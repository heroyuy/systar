/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import com.soyomaker.AppData;
import com.soyomaker.data.DataManager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class EnemyTroopTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "名称", "敌人总数"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class
    };
    private DataManager data = AppData.getInstance().getCurProject().getDataManager();

    public int getRowCount() {
        return data.size(Model.ENEMYTROOP);
    }

    @Override
    public String getColumnName(int c) {
        return COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return COLUMN_CLASS[c];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        super.setValueAt(v, r, c);
        EnemyTroop enemyTroop = (EnemyTroop) data.getModels(Model.ENEMYTROOP)[r];
        if (enemyTroop != null) {
            switch (c) {
                case 1:
                    enemyTroop.name = v.toString();
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        EnemyTroop enemyTroop = (EnemyTroop) data.getModels(Model.ENEMYTROOP)[rowIndex];
        if (enemyTroop != null) {
            switch (columnIndex) {
                case 0:
                    return enemyTroop.getIndex();
                case 1:
                    return enemyTroop.name;
                case 2:
                    return enemyTroop.enemys.size();
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
