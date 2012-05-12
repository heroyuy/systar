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
        "ID", "名称", "介绍", "图标", "限制等级", "使用目标", "使用限制", "是否可消耗", "价格"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class, ImageIcon.class, Integer.class, Object.class, Object.class, Boolean.class, Integer.class
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
//                case 3:
//                    item.icon = ((ImageIcon) v).getDescription();
//                    break;
                case 4:
                    item.lev = Integer.parseInt(v.toString());
                    break;
                case 5:
                    for (int i = 0; i < Item.targets.length; i++) {
                        if (Item.targets[i].equals(v.toString())) {
                            item.target = i;
                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < Item.limits.length; i++) {
                        if (Item.limits[i].equals(v.toString())) {
                            item.limit = i;
                        }
                    }
                    break;
                case 7:
                    item.consumable = Boolean.parseBoolean(v.toString());
                    break;
                case 8:
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
//                    ImageIcon[] itemIcons = DataManager.listItemIconName();
                    for (int i = 0; i < itemIcons.length; i++) {
                        if (itemIcons[i].getDescription().equals(item.icon)) {
                            return itemIcons[i];
                        }
                    }
                    return null;
                case 4:
                    return item.lev;
                case 5:
                    return Item.targets[item.target];
                case 6:
                    return Item.limits[item.limit];
                case 7:
                    return item.consumable;
                case 8:
                    return item.price;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
