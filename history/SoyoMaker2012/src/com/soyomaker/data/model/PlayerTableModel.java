/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import com.soyomaker.AppData;
import com.soyomaker.data.DataManager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class PlayerTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "名称", "介绍", "初始等级", "最大等级", "职业"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class, Integer.class, Integer.class, Object.class
    };
    private DataManager data = AppData.getInstance().getCurProject().getDataManager();

    public int getRowCount() {
        return data.size(Model.PLAYER);
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
        if (col == 0 || col == 5) {
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        super.setValueAt(v, r, c);
        Player player = (Player) data.getModels(Model.PLAYER)[r];
        if (player != null) {
            switch (c) {
                case 1:
                    player.name = v.toString();
                    break;
                case 2:
                    player.intro = v.toString();
                    break;
                case 3:
                    player.startLev = Integer.parseInt(v.toString());
                    break;
                case 4:
                    player.maxLev = Integer.parseInt(v.toString());
                    break;
                case 5:
                    for (int i = 0; i < AppData.getInstance().getCurProject().getDataManager().getModels(Model.VOCATION).length; i++) {
                        if (((Vocation) AppData.getInstance().getCurProject().getDataManager().getModels(Model.VOCATION)[i]).name.equals(v.toString())) {
                            player.vocationIndex = i;
                        }
                    }
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = (Player) data.getModels(Model.PLAYER)[rowIndex];
        if (player != null) {
            switch (columnIndex) {
                case 0:
                    return player.getIndex();
                case 1:
                    return player.name;
                case 2:
                    return player.intro;
                case 3:
                    return player.startLev;
                case 4:
                    return player.maxLev;
                case 5:
                    if (player.vocationIndex != -1) {
                        return AppData.getInstance().getCurProject().getDataManager().getModels(Model.VOCATION)[player.vocationIndex];
                    }
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
