/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import com.soyostar.pluginimpl.sprite.data.Proxy;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class SequenceTableModel extends AbstractTableModel {

    private static final String SEQUENCE_COLUMN_NAME[] = {
        "序号", "使用帧", "持续时间"
    };
    private static final Class SEQUENCE_COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class
    };

    /**
     *
     */
    public SequenceTableModel() {
    }

    @Override
    public String getColumnName(int c) {
        return SEQUENCE_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return SEQUENCE_COLUMN_CLASS[c];
    }

    public int getColumnCount() {
        return SEQUENCE_COLUMN_NAME.length;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 2:
                return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        Sequence seq = Animation.getInstance().getAction(Proxy.getInstance().getMainDialog().getCurrentActionIndex()).
            getSequence(r);
        switch (c) {
            case 2:
                seq.setDelay((Integer) v);
                break;
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Sequence seq = Animation.getInstance().getAction(Proxy.getInstance().getMainDialog().getCurrentActionIndex()).
            getSequence(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return seq.getFrame().getName();
            case 2:
                return seq.getDelay();

        }
        return null;
    }

    public int getRowCount() {
        if (Proxy.getInstance().getMainDialog().getCurrentActionIndex() == -1) {
            return 0;
        }
        return Animation.getInstance().getAction(Proxy.getInstance().getMainDialog().getCurrentActionIndex()).
            getSequencesCount();
    }
}
