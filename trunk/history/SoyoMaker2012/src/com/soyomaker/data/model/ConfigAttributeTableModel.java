/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class ConfigAttributeTableModel extends AbstractTableModel {

    private Config config;

    /**
     *
     * @param config
     */
    public ConfigAttributeTableModel(Config config) {
        this.config = config;
    }
    private static final String COLUMN_NAME[] = {
        "ID", "名称", "描述"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class
    };

    public int getRowCount() {
        return config.system.attributes.size();
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
        Attribute attr = config.system.attributes.get(r);
        if (attr != null) {
            switch (c) {
                case 1:
                    attr.name = v.toString();
                    break;
                case 2:
                    attr.description = v.toString();
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Attribute attr = config.system.attributes.get(rowIndex);
        if (attr != null) {
            switch (columnIndex) {
                case 0:
                    return rowIndex;
                case 1:
                    return attr.name;
                case 2:
                    return attr.description;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
