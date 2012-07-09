/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import com.soyomaker.data.model.Skill.BuffInfo;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class ModelStatusTableModel extends AbstractTableModel {

    private ArrayList<BuffInfo> status;

    /**
     *
     * @param status
     */
    public ModelStatusTableModel(ArrayList<BuffInfo> status) {
        this.status = status;
    }
    private static final String COLUMN_NAME[] = {
        "状态名称", "状态有效度"
    };
    private static final Class COLUMN_CLASS[] = {
        String.class, Integer.class
    };

    public int getRowCount() {
        return status.size();
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
        BuffInfo attr = status.get(r);
        if (attr != null) {
            switch (c) {
                case 1:
                    attr.rate = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        BuffInfo attr = status.get(rowIndex);
        if (attr != null) {
            switch (columnIndex) {
                case 0:
                    return attr.buff.name;
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
