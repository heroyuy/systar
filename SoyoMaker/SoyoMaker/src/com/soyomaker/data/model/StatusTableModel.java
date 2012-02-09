/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import com.soyomaker.AppData;
import com.soyomaker.data.DataManager;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class StatusTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "名称", "介绍", "图标", "类型", "等级"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class, ImageIcon.class, Object.class, Integer.class
    };
    private DataManager data = AppData.getInstance().getCurProject().getDataManager();

    public int getRowCount() {
        return data.size(Model.STATUS);
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
        if (col == 0 || col == 3) {
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        super.setValueAt(v, r, c);
        Status status = (Status) data.getModels(Model.STATUS)[r];
        if (status != null) {
            switch (c) {
                case 1:
                    status.name = v.toString();
                    break;
                case 2:
                    status.description = v.toString();
                    break;
//                case 3:
//                    status.icon = v.toString();
//                    break;
                case 4:
                    for (int i = 0; i < Status.kinds.length; i++) {
                        if (Status.kinds[i].equals(v.toString())) {
                            status.type = i;
                        }
                    }
                    break;
                case 5:
                    status.lev = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }
    private ImageIcon[] statusIcons = DataManager.listStatusIconName();

    public Object getValueAt(int rowIndex, int columnIndex) {
        Status status = (Status) data.getModels(Model.STATUS)[rowIndex];
        if (status != null) {
            switch (columnIndex) {
                case 0:
                    return status.getIndex();
                case 1:
                    return status.name;
                case 2:
                    return status.description;
                case 3:

                    for (int i = 0; i < statusIcons.length; i++) {
                        if (statusIcons[i].getDescription().equals(status.icon)) {
                            return statusIcons[i];
                        }
                    }
                    return null;
                case 4:
                    return Status.kinds[status.type];
                case 5:
                    return status.lev;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
