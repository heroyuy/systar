package model;

import java.util.Vector;

//玩家和敌人身上保存一张物品表，用于保存角色、敌人当前所拥有的物品
public class EnemyList extends Vector {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void addEnemy(Enemy enemy) {
        addElement(enemy);
    }

    public void delEnemy(int index) {
        //删除技能列表中指定技能编号的技能
        removeElementAt(index);
    }

    public Enemy enemyAt(int num) {
        //获取技能表中指定位置的技能
        return (Enemy) elementAt(num);
    }

    public Enemy getEnemy(int index) {
        //返回技能列表中指定技能编号的技能
        Enemy enemy = null;
        if (hasEnemy(index)) {
            for (int i = 0; i < size(); i++) {
                enemy = (Enemy) elementAt(i);
                if (enemy.getIndex() == index) {
                    break;
                }
            }
        }
        return enemy;

    }

    public int getMaxIndex() {
        int max = 0;
        Enemy enemy = null;
        for (int i = 0; i < size(); i++) {
            enemy = enemyAt(i);
            if (enemy.getIndex() > max) {
                max = enemy.getIndex();
            }
        }
        return max;
    }

    public boolean hasEnemy(int index) {
        //检查是否有指定编号的技能
        boolean judge = false;
        Enemy enemy = null;
        for (int i = 0; i < size(); i++) {
            enemy = (Enemy) elementAt(i);
            if (enemy.getIndex() == index) {
                judge = true;
                break;
            }
        }
        return judge;
    }
}
