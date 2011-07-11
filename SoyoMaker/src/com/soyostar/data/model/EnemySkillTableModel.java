/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.model;

import com.soyostar.data.DataManager;
import com.soyostar.dialog.DataEditorDialog;
import com.soyostar.project.Project;
import com.soyostar.proxy.SoftProxy;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrator
 */
public class EnemySkillTableModel extends AbstractTableModel {

    private DataEditorDialog ded;

    /**
     * 
     * @param ded
     */
    public EnemySkillTableModel(DataEditorDialog ded) {
        this.ded = ded;
    }
    private static final String ENEMYSKILL_COLUMN_NAME[] = {
        "ID", "名称"
    };
    private static final Class ENEMYSKILL_COLUMN_CLASS[] = {
        Integer.class, String.class
    };

    @Override
    public String getColumnName(int c) {
        return ENEMYSKILL_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return ENEMYSKILL_COLUMN_CLASS[c];
    }

    @Override
    public int getColumnCount() {
        return ENEMYSKILL_COLUMN_NAME.length;
    }
    private DataManager data = SoftProxy.getInstance().getCurProject().getDataManager();

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (ded.getCurSelectEnemyIndex() < 0 || ded.getCurSelectEnemyIndex() >= data.getModels(Model.ENEMY).length) {
            return null;
        }
        Enemy enemy = (Enemy) data.getModels(Model.ENEMY)[ded.getCurSelectEnemyIndex()];

        switch (columnIndex) {
            case 0:
                return enemy.skillList.get(rowIndex).getIndex();
            case 1:
                return enemy.skillList.get(rowIndex).name;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public int getRowCount() {
        if (ded.getCurSelectEnemyIndex() < 0 || ded.getCurSelectEnemyIndex() >= data.getModels(Model.ENEMY).length) {
            return 0;
        }
        Enemy enemy = (Enemy) data.getModels(Model.ENEMY)[ded.getCurSelectEnemyIndex()];
        return enemy.skillList.size();
    }
}
