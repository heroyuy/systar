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
public class EnemyTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "名称", "介绍", "等级", "攻击距离", "经验", "金钱"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class
    };
    private DataManager data = AppData.getInstance().getCurProject().getDataManager();

    public int getRowCount() {
        return data.size(Model.ENEMY);
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
        if (col == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        super.setValueAt(v, r, c);
        Enemy enemy = (Enemy) data.getModels(Model.ENEMY)[r];
        if (enemy != null) {
            switch (c) {
                case 1:
                    enemy.name = v.toString();
                    break;
                case 2:
                    enemy.intro = v.toString();
                    break;
                case 3:
                    enemy.lev = Integer.parseInt(v.toString());
                    break;
                case 4:
                    enemy.attackDistance = Integer.parseInt(v.toString());
                    break;
                case 5:
                    enemy.exp = Integer.parseInt(v.toString());
                    break;
                case 6:
                    enemy.money = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Enemy enemy = (Enemy) data.getModels(Model.ENEMY)[rowIndex];
        if (enemy != null) {
            switch (columnIndex) {
                case 0:
                    return enemy.getIndex();
                case 1:
                    return enemy.name;
                case 2:
                    return enemy.intro;
                case 3:
                    return enemy.lev;
                case 4:
                    return enemy.attackDistance;
                case 5:
                    return enemy.exp;
                case 6:
                    return enemy.money;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
