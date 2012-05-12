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
public class ModelConditionTableModel extends AbstractTableModel {

    private ArrayList<Condition> conditions;

    /**
     *
     * @param conditions
     */
    public ModelConditionTableModel(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }
    private static final String COLUMN_NAME[] = {
        "解除条件类型", "解除条件名称", "参数"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class
    };

    public int getRowCount() {
        return conditions.size();
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
        Condition cond = conditions.get(r);
        if (cond != null) {
            switch (c) {
                case 0:
//                    for (int i = 0; i < Condition.statusConditionTypes.length; i++) {
//                        if (Condition.statusConditionTypes[i].equals(v.toString())) {
//                            cond.conditionType = i;
//                        }
//                    }
                    cond.conditionType = Integer.parseInt(v.toString());
                    break;
                case 1:
                    cond.conditionName = v.toString();
                    break;
                case 2:
                    cond.para = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Condition cond = conditions.get(rowIndex);
        if (cond != null) {
            switch (columnIndex) {
                case 0:
                    return cond.conditionType;
                case 1:
                    return cond.conditionName;
                case 2:
                    return cond.para;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
