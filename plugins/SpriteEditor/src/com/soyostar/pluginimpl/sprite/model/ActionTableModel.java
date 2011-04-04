/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class ActionTableModel extends AbstractTableModel {

    private static final String ACTION_COLUMN_NAME[] = {
        "序号", "名称", "序列数量"
    };
    private static final Class ACTION_COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class
    };

    /**
     *
     */
    public ActionTableModel() {
    }

    @Override
    public String getColumnName(int c) {
        return ACTION_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return ACTION_COLUMN_CLASS[c];
    }

    public int getColumnCount() {
        return ACTION_COLUMN_NAME.length;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 1:
                return true;
        }
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return Animation.getInstance().getAction(rowIndex).getName();
            case 2:
                return Animation.getInstance().getAction(rowIndex).getSequencesCount();
        }
        return null;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        switch (c) {
            case 1:
                Animation.getInstance().getAction(r).setName((String) v);
                break;
        }
        this.fireTableCellUpdated(r, c);
    }

    public int getRowCount() {
        return Animation.getInstance().getActionsCount();
    }
}
