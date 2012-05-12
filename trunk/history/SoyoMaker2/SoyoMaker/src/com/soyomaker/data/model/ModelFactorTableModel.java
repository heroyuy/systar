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
public class ModelFactorTableModel extends AbstractTableModel {

    private ArrayList<Factor> factors;

    /**
     *
     * @param factors
     */
    public ModelFactorTableModel(ArrayList<Factor> factors) {
        this.factors = factors;
    }
    private static final String COLUMN_NAME[] = {
        "因数类型", "因数名称", "因数值"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class
    };

    public int getRowCount() {
        return factors.size();
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
        if(col == 0){
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        super.setValueAt(v, r, c);
        Factor factor = factors.get(r);
        if (factor != null) {
            switch (c) {
                case 1:
                    factor.factorName = v.toString();
//                    for (int i = 0; i < Factor.factors.length; i++) {
//                        if (Factor.factors[i].equals(v.toString())) {
//                            factor.factorType = i;
//                        }
//                    }
                    break;
                case 2:
                    factor.factorValue = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Factor effect = factors.get(rowIndex);
        if (effect != null) {
            switch (columnIndex) {
                case 0:
                    return effect.factorType;
                case 1:
                    return effect.factorName;
                case 2:
                    return effect.factorValue;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
