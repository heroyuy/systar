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
public class ModelSkillTableModel extends AbstractTableModel {

    private ArrayList<Skill> treas;

    /**
     *
     * @param status
     */
    public ModelSkillTableModel(ArrayList<Skill> status) {
        this.treas = status;
    }
    private static final String COLUMN_NAME[] = {
        "技能名称"
    };
    private static final Class COLUMN_CLASS[] = {
        String.class
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
                treas.get(r).name = v.toString();
                break;
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return treas.get(rowIndex).name;
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
