/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import com.soyomaker.data.DataManager;
import com.soyomaker.AppData;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class SkillTableModel extends AbstractTableModel {

    private static final String COLUMN_NAME[] = {
        "ID", "名称", "介绍", "图标", "限制等级", "使用目标", "技能类型"
    };
    private static final Class COLUMN_CLASS[] = {
        Integer.class, String.class, String.class, ImageIcon.class, Integer.class, Object.class, Object.class
    };
    private DataManager data = AppData.getInstance().getCurProject().getDataManager();

    public int getRowCount() {
        return data.size(Model.SKILL);
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
        Skill skill = (Skill) data.getModels(Model.SKILL)[r];
        if (skill != null) {
            switch (c) {
                case 1:
                    skill.name = v.toString();
                    break;
                case 2:
                    skill.intro = v.toString();
                    break;
                case 4:
                    skill.lev = Integer.parseInt(v.toString());
                    break;
                case 5:
                    for (int i = 0; i < Skill.targets.length; i++) {
                        if (Skill.targets[i].contains(v.toString())) {
                            skill.target = i;
                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < Skill.types.length; i++) {
                        if (Skill.types[i].contains(v.toString())) {
                            skill.type = i;
                        }
                    }
                    break;
            }
        }
        this.fireTableCellUpdated(r, c);
    }
    private ImageIcon[] skillIcons = DataManager.listSkillIconName();

    public Object getValueAt(int rowIndex, int columnIndex) {
        Skill skill = (Skill) data.getModels(Model.SKILL)[rowIndex];
        if (skill != null) {
            switch (columnIndex) {
                case 0:
                    return skill.getIndex();
                case 1:
                    return skill.name;
                case 2:
                    return skill.intro;
                case 3:
                    for (int i = 0; i < skillIcons.length; i++) {
                        if (skillIcons[i].getDescription().equals(skill.icon)) {
                            return skillIcons[i];
                        }
                    }
                    return null;
                case 4:
                    return skill.lev;
                case 5:
                    return Skill.targets[skill.target];
                case 6:
                    return Skill.types[skill.type];
            }
        }
        return null;
    }

    public int getColumnCount() {
        return COLUMN_NAME.length;
    }
}
