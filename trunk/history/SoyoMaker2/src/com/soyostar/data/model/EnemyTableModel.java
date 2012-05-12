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
public class EnemyTableModel extends AbstractTableModel {

    private static final String ENEMY_COLUMN_NAME[] = {
        "ID", "名称"
    };
    private static final Class ENEMY_COLUMN_CLASS[] = {
        Integer.class, String.class
    };

    @Override
    public String getColumnName(int c) {
        return ENEMY_COLUMN_NAME[c];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return ENEMY_COLUMN_CLASS[c];
    }

    @Override
    public int getColumnCount() {
        return ENEMY_COLUMN_NAME.length;
    }
    private DataManager data = SoftProxy.getInstance().getCurProject().getDataManager();

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Enemy enemy = (Enemy) data.getModels(Model.ENEMY)[rowIndex];
        switch (columnIndex) {
            case 0:
                return enemy.getIndex();
            case 1:
                return enemy.name;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

//    @Override
//    public void setValueAt(Object v, int r, int c) {
//    }
    @Override
    public int getRowCount() {
        return data.size(Model.ENEMY);
    }
}
