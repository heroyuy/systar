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
public class ModelCostTableModel extends AbstractTableModel {

    private ArrayList<Cost> costs;

    /**
     *
     * @param costs
     */
    public ModelCostTableModel(ArrayList<Cost> costs) {
        this.costs = costs;
    }
    private static final String COLUMN_NAME[] = {
        "消耗类型", "消耗名称", "消耗值"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class
    };

    public int getRowCount() {
        return costs.size();
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
        Cost cost = costs.get(r);
        if (cost != null) {
            switch (c) {
//                case 0:
//                    for (int i = 0; i < Cost.costs.length; i++) {
//                        if (Cost.costs[i].equals(v.toString())) {
//                            cost.costType = i;
//                        }
//                    }
//                    break;
                case 1:
                    cost.costName = v.toString();
                    break;
                case 2:
                    cost.costValue = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Cost cost = costs.get(rowIndex);
        if (cost != null) {
            switch (columnIndex) {
                case 0:
//                    return Cost.costs[cost.costType];
                    return cost.costType;
                case 1:
                    return cost.costName;
                case 2:
                    return cost.costValue;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
