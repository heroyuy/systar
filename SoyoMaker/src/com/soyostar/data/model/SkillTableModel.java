/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.model;

//import com.soyostar.data.Data;
import com.soyostar.data.DataManager;
import com.soyostar.project.Project;
import com.soyostar.proxy.SoftProxy;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class SkillTableModel extends AbstractTableModel {

    private static final String SKILL_COLUMN_NAME[] = {
        "ID", "名称"
    };
    private static final Class SKILL_COLUMN_CLASS[] = {
        Integer.class, String.class
    };

    @Override
    public String getColumnName(int c) {
        return SKILL_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return SKILL_COLUMN_CLASS[c];
    }

    @Override
    public int getColumnCount() {
        return SKILL_COLUMN_NAME.length;
    }
    private DataManager data = SoftProxy.getInstance().getCurProject().getDataManager();

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Skill skill = (Skill) data.getModels(Model.SKILL)[rowIndex];
        if (skill != null) {
            switch (columnIndex) {
                case 0:
                    return skill.getIndex();
                case 1:
                    return skill.name;
            }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
//        if (col == 1) {
//            return true;
//        }
        return false;
    }

//    @Override
//    public void setValueAt(Object v, int r, int c) {
//        super.setValueAt(v, r, c);
//        this.fireTableCellUpdated(r, c);
//    }
    @Override
    public int getRowCount() {
        return data.size(Model.SKILL);
    }
}
