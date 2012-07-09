/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import com.soyomaker.data.model.Enemy.ItemInfo;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class ModelItemTableModel extends AbstractTableModel {

    private ArrayList<ItemInfo> treas;

    /**
     *
     * @param status
     */
    public ModelItemTableModel(ArrayList<ItemInfo> status) {
        this.treas = status;
    }
    private static final String COLUMN_NAME[] = {
        "宝物名称", "掉落率"
    };
    private static final Class COLUMN_CLASS[] = {
        String.class, Integer.class
    };

    public int getRowCount() {
        return treas.size();
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
        switch (c) {
            case 0:
                treas.get(r).item.name = v.toString();
                break;
            case 1:
                treas.get(r).rate = Integer.parseInt(v.toString());
                break;
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return treas.get(rowIndex).item.name;
            case 1:
                return treas.get(rowIndex).rate;
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
