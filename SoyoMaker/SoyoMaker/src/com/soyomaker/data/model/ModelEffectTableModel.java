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
public class ModelEffectTableModel extends AbstractTableModel {

    private ArrayList<Effect> effects;

    /**
     *
     * @param effects
     */
    public ModelEffectTableModel(ArrayList<Effect> effects) {
        this.effects = effects;
    }
    private static final String COLUMN_NAME[] = {
        "效果类型", "效果名称", "效果值"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, Integer.class
    };

    public int getRowCount() {
        return effects.size();
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
        super.setValueAt(v, r, c);
        Effect effect = effects.get(r);
        if (effect != null) {
            switch (c) {
//                case 0:
//                    for (int i = 0; i < Effect.effects.length; i++) {
//                        if (Effect.effects[i].equals(v.toString())) {
//                            effect.effectType = i;
//                        }
//                    }
//                    break;
                case 1:
                    effect.effectName = v.toString();
                    break;
                case 2:
                    effect.effectValue = Integer.parseInt(v.toString());
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Effect effect = effects.get(rowIndex);
        if (effect != null) {
            switch (columnIndex) {
                case 0:
//                    return Effect.effects[effect.effectType];
                    return effect.effectType;
                case 1:
                    return effect.effectName;
                case 2:
                    return effect.effectValue;
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
