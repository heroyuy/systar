/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class ModelTreasureTableModel extends AbstractTableModel {

    private ArrayList<Treasure> treas;

    /**
     *
     * @param status
     */
    public ModelTreasureTableModel(ArrayList<Treasure> status) {
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
        Treasure attr = treas.get(r);
        if (attr != null) {
            switch (c) {
                case 0:
                    attr.name = v.toString();
                    break;
                case 1:
                    attr.rate = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Treasure attr = treas.get(rowIndex);
        if (attr != null) {
            switch (columnIndex) {
                case 0:
                    return attr.name;
                case 1:
                    return attr.rate;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
