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
public class ItemTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "名称", "介绍", "图标", "限制等级", "类型", "价格"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class, ImageIcon.class, Integer.class, Object.class, Integer.class
    };
    private DataManager data = AppData.getInstance().getCurProject().getDataManager();

    public int getRowCount() {
        return data.size(Model.ITEM);
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
        Item item = (Item) data.getModels(Model.ITEM)[r];
        if (item != null) {
            switch (c) {
                case 1:
                    item.name = v.toString();
                    break;
                case 2:
                    item.intro = v.toString();
                    break;
                case 4:
                    item.lev = Integer.parseInt(v.toString());
                    break;
                case 5:
                    for (int i = 0; i < Item.types.length; i++) {
                        if (Item.types[i].equals(v.toString())) {
                            item.type = i;
                        }
                    }
                    break;
                case 6:
                    item.price = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }
    private ImageIcon[] itemIcons = DataManager.listItemIconName();

    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = (Item) data.getModels(Model.ITEM)[rowIndex];
        if (item != null) {
            switch (columnIndex) {
                case 0:
                    return item.getIndex();
                case 1:
                    return item.name;
                case 2:
                    return item.intro;
                case 3:
                    for (int i = 0; i < itemIcons.length; i++) {
                        if (itemIcons[i].getDescription().equals(item.icon)) {
                            return itemIcons[i];
                        }
                    }
                    return null;
                case 4:
                    return item.lev;
                case 5:
                    return Item.types[item.type];
                case 6:
                    return item.price;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
