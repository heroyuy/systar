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
public class ModelActionTableModel extends AbstractTableModel {

    private ArrayList<Action> actions;

    /**
     *
     * @param actions
     */
    public ModelActionTableModel(ArrayList<Action> actions) {
        this.actions = actions;
    }
    private static final String COLUMN_NAME[] = {
        "行动类型", "触发率%"
    };
    private static final Class COLUMN_CLASS[] = {
        String.class, Integer.class
    };

    public int getRowCount() {
        return actions.size();
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
        Action attr = actions.get(r);
        if (attr != null) {
            switch (c) {
                case 0:
                    for (int i = 0; i < Action.types.length; i++) {
                        if (Action.types[i].equals(v.toString())) {
                            attr.actionType = i;
                        }
                    }
                    break;
                case 1:
                    attr.rate = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Action attr = actions.get(rowIndex);
        if (attr != null) {
            switch (columnIndex) {
                case 0:
                    return Action.types[attr.actionType];
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
