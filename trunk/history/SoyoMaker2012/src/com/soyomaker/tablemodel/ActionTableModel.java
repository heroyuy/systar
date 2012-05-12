/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.tablemodel;

import com.soyomaker.model.animation.Action;
import com.soyomaker.AppData;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class ActionTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "开始帧", "效果", "音效"
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
        Action act = data.getCurProject().getAnimation(data.getCurrentAnimationIndex()).getAction(rowIndex);
        if (act != null) {
            switch (columnIndex) {
                case 0:
                    return act.getFrameId();
                case 1:
                    switch (act.getType()) {
                        case Action.NOTHING:
                            return "无";
                        case Action.PLAY_DISAPPEAR:
                            return "对象消失";
                        case Action.PLAY_TWINKLE:
                            return "对象闪烁";
                        case Action.SCEEN_TWINKLE:
                            return "屏幕闪烁";
                    }
                case 2:
                    return act.getMusicFile();
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object v, int r, int c) {
        //Action act = data.getCurProject().getAnimation(data.getCurrentAnimationIndex()).getAction(r);
        this.fireTableCellUpdated(r, c);
    }

    @Override
    public int getRowCount() {
        if (data.getCurProject().getAnimation(data.getCurrentAnimationIndex()) == null) {
            return 0;
        }
        return data.getCurProject().getAnimation(data.getCurrentAnimationIndex()).getActions().size();
    }
}
