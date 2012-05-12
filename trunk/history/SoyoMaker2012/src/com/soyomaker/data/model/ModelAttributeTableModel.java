/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import com.soyomaker.AppData;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class ModelAttributeTableModel extends AbstractTableModel {

    private ArrayList<Attribute> attributes;

    /**
     *
     * @param attributes
     */
    public ModelAttributeTableModel(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }
    private static final String COLUMN_NAME[] = {
        "属性类型", "属性值"
    };
    private static final Class COLUMN_CLASS[] = {
        String.class, Integer.class
    };

    public int getRowCount() {
        return attributes.size();
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
//        super.setValueAt(v, r, c);
        Attribute attr = attributes.get(r);
        if (attr != null) {
            switch (c) {
                case 0:
                    ArrayList<Attribute> attrs = ((Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0]).system.attributes;
                    for (int i = 0; i < attrs.size(); i++) {
                        if (attrs.get(i).name.equals(v.toString())) {
                            attr.id = i;
                        }
                    }
                    break;
                case 1:
                    attr.value = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Attribute attr = attributes.get(rowIndex);
        if (attr != null) {
            switch (columnIndex) {
                case 0:
                    return ((Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0]).system.attributes.get(attr.id).name;
                case 1:
                    return attr.value;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
