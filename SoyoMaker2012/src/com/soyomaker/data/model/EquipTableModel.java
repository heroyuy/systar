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
public class EquipTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "名称", "介绍", "图标", "限制等级", "装备类型", "价格"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class, ImageIcon.class, Integer.class, Object.class, Integer.class
    };
    private DataManager data = AppData.getInstance().getCurProject().getDataManager();

    public int getRowCount() {
        return data.size(Model.EQUIP);
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
        Equip equip = (Equip) data.getModels(Model.EQUIP)[r];
        if (equip != null) {
            switch (c) {
                case 1:
                    equip.name = v.toString();
                    break;
                case 2:
                    equip.intro = v.toString();
                    break;
//                case 3:
//                    equip.icon = ((ImageIcon) v).getDescription();
//                    break;
                case 4:
                    equip.lev = Integer.parseInt(v.toString());
                    break;
                case 5:
                    for (int i = 0; i < Equip.kinds.length; i++) {
                        if (Equip.kinds[i].equals(v.toString())) {
                            equip.equipType = i;
                        }
                    }
                    break;
                case 6:
                    equip.price = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }
    private ImageIcon[] equipIcons = DataManager.listEquipIconName();

    public Object getValueAt(int rowIndex, int columnIndex) {
        Equip equip = (Equip) data.getModels(Model.EQUIP)[rowIndex];
        if (equip != null) {
            switch (columnIndex) {
                case 0:
                    return equip.getIndex();
                case 1:
                    return equip.name;
                case 2:
                    return equip.intro;
                case 3:

                    for (int i = 0; i < equipIcons.length; i++) {
                        if (equipIcons[i].getDescription().equals(equip.icon)) {
                            return equipIcons[i];
                        }
                    }
                    return null;
                case 4:
                    return equip.lev;
                case 5:
                    return Equip.kinds[equip.equipType];
                case 6:
                    return equip.price;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
