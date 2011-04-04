/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class FrameTableModel extends AbstractTableModel {

    private static final String FRAME_COLUMN_NAME[] = {
        "序号", "帧名"
    };
    private static final Class FRAME_COLUMN_CLASS[] = {
        Integer.class, String.class
    };

    @Override
    public String getColumnName(int c) {
        return FRAME_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return FRAME_COLUMN_CLASS[c];
    }

    public int getColumnCount() {
        return FRAME_COLUMN_NAME.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return Animation.getInstance().getFrame(rowIndex).getName();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 1:
                return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        switch (c) {
            case 1:
                Animation.getInstance().getFrame(r).setName((String) v);
                break;
        }
        this.fireTableCellUpdated(r, c);
    }

    public int getRowCount() {
        return Animation.getInstance().getFramesCount();
    }
}
