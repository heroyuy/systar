package model;

import java.util.Vector;

//
public class EnemyTroopList extends Vector {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void addEnemyTroop(EnemyTroop EnemyTroop) {
        addElement(EnemyTroop);
    }

    public void delEnemyTroop(int index) {
        //删除技能列表中指定技能编号的技能
        removeElementAt(index);
    }

    public EnemyTroop EnemyTroopAt(int num) {
        //获取技能表中指定位置的技能
        return (EnemyTroop) elementAt(num);
    }

    public EnemyTroop getEnemyTroop(int index) {
        //返回技能列表中指定技能编号的技能
        EnemyTroop EnemyTroop = null;
        if (hasEnemyTroop(index)) {
            for (int i = 0; i < size(); i++) {
                EnemyTroop = (EnemyTroop) elementAt(i);
                if (EnemyTroop.index == index) {
                    break;
                }
            }
        }
        return EnemyTroop;

    }

    public int getMaxIndex() {
        int max = 0;
        EnemyTroop EnemyTroop = null;
        for (int i = 0; i < size(); i++) {
            EnemyTroop = EnemyTroopAt(i);
            if (EnemyTroop.index > max) {
                max = EnemyTroop.index;
            }
        }
        return max;
    }

    public boolean hasEnemyTroop(int index) {
        //检查是否有指定编号的技能
        boolean judge = false;
        EnemyTroop EnemyTroop = null;
        for (int i = 0; i < size(); i++) {
            EnemyTroop = (EnemyTroop) elementAt(i);
            if (EnemyTroop.index == index) {
                judge = true;
                break;
            }
        }
        return judge;
    }
}
