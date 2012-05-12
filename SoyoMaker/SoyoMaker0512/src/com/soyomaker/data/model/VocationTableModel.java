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
public class VocationTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "名称", "可用装备数", "可用物品数", "可用技能数", "可用属性数", "可用状态数"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
    };
    private DataManager data = AppData.getInstance().getCurProject().getDataManager();

    public int getRowCount() {
        return data.size(Model.VOCATION);
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
        Vocation vocation = (Vocation) data.getModels(Model.VOCATION)[r];
        if (vocation != null) {
            switch (c) {
                case 1:
                    vocation.name = v.toString();
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Vocation vocation = (Vocation) data.getModels(Model.VOCATION)[rowIndex];
        if (vocation != null) {
            switch (columnIndex) {
                case 0:
                    return vocation.getIndex();
                case 1:
                    return vocation.name;
                case 2:
                    return vocation.equips.size();
                case 3:
                    return vocation.items.size();
                case 4:
                    return vocation.skills.size();
                case 5:
                    return vocation.attrs.size();
                case 6:
                    return vocation.status.size();
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
