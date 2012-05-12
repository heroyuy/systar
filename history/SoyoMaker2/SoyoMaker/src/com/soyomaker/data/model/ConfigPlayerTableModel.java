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
public class ConfigPlayerTableModel extends AbstractTableModel {

    private Config config;

    /**
     *
     * @param config
     */
    public ConfigPlayerTableModel(Config config) {
        this.config = config;
    }
    private static final String COLUMN_NAME[] = {
        "ID", "名称"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class
    };

    public int getRowCount() {
        return config.system.initPlayers.size();
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
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = config.system.initPlayers.get(rowIndex);
        if (player != null) {
            switch (columnIndex) {
                case 0:
                    return player.getIndex();
                case 1:
                    return player.name;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
