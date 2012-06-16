/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.tablemodel;

import com.soyomaker.AppData;
import com.soyomaker.model.animation.Action;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class ActionTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "音效", "光影"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class
    };

    /**
     *
     */
    public ActionTableModel() {
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
    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
    private AppData data = AppData.getInstance();

    public Object getValueAt(int rowIndex, int columnIndex) {
        Action action = data.getCurrentAnimation().getAction(rowIndex);
        if (action != null) {
            switch (columnIndex) {
                case 0:
                    return rowIndex;
                case 1:
                    return action.getMusicName();
                case 2:
                    return Action.EFFECTS[action.getEffectType()];
            }
        }
        return null;
    }

    @Override
    public int getRowCount() {
        if (data.getCurrentAnimation() == null) {
            return 0;
        }
        return data.getCurrentAnimation().getActions().size();
    }
}
