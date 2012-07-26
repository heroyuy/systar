/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import com.soyomaker.AppData;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class AttributeFactorTableModel extends AbstractTableModel {

    private Attribute attribute;

    /**
     *
     * @param attr
     */
    public AttributeFactorTableModel(Attribute attr) {
        this.attribute = attr;
    }
    private static final String COLUMN_NAME[] = {
        "属性ID", "名称", "相克系数"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class
    };

    public int getRowCount() {
        return attribute.factors.size();
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
        if (col == 2) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        super.setValueAt(v, r, c);
        AttributeFactor af = attribute.factors.get(r);
        if (af != null) {
            switch (c) {
                case 2:
                    af.value = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        AttributeFactor af = attribute.factors.get(rowIndex);
        if (af != null) {
            switch (columnIndex) {
                case 0:
                    return rowIndex;
                case 1:
                    Config config = (Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
                    return config.system.attributes.get(af.targetId).name;
                case 2:
                    return af.value;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
