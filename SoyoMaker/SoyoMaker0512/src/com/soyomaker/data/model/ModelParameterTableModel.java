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
public class ModelParameterTableModel extends AbstractTableModel {

    private ArrayList<Parameter> paras;

    /**
     *
     * @param paras
     */
    public ModelParameterTableModel(ArrayList<Parameter> paras) {
        this.paras = paras;
    }
    private static final String COLUMN_NAME[] = {
        "能力名称", "参数值"
    };
    private static final Class COLUMN_CLASS[] = {
        String.class, Integer.class
    };

    public int getRowCount() {
        return paras.size();
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
        if (col == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        super.setValueAt(v, r, c);
        Parameter para = paras.get(r);
        if (para != null) {
            switch (c) {
                case 0:
                    for (int i = 0; i < Parameter.types.length; i++) {
                        if (Parameter.types[i].equals(v.toString())) {
                            para.type = i;
                        }
                    }
                    break;
                case 1:
                    para.value = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Parameter para = paras.get(rowIndex);
        if (para != null) {
            switch (columnIndex) {
                case 0:
                    return Parameter.types[para.type];
                case 1:
                    return para.value;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
