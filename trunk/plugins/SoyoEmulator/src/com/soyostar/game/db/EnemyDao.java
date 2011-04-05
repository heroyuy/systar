/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Enemy;
import com.soyostar.ui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class EnemyDao implements Dao {

    private DataInputStream dis = null;
    private HashMap enemys = null;

    public Enemy getEnemy(int index) {
        return (Enemy) enemys.get(index);
    }

    public Enemy[] getEnemyList() {
        return (Enemy[]) enemys.values().toArray();
    }

    public void load() {
        enemys = new HashMap();
        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_ENEMY, 0);
        try {
            int enemySum = dis.readInt();
            Enemy enemy = null;
            for (int i = 0; i < enemySum; i++) {
                enemy = new Enemy();
                enemy.index = dis.readInt();
                enemy.name = dis.readUTF();
                enemy.intro = dis.readUTF();
                enemy.BattImg = Image.createImage("product/image/battler/" + dis.readUTF());
                enemy.agil = dis.readInt();
                enemy.stre = dis.readInt();
                enemy.inte = dis.readInt();
                enemy.maxHp = dis.readInt();
                enemy.maxSp = dis.readInt();
                System.out.println("maxSp " + enemy.maxSp);
                enemy.lev = dis.readInt();
                enemy.atk = dis.readInt();
                enemy.def = dis.readInt();
                enemy.exp = dis.readInt();
                enemy.money = dis.readInt();
                enemy.hp = enemy.maxHp;
                enemy.sp = enemy.maxSp;
                int skillSum = dis.readInt();
                System.out.println("skillSum: " + skillSum);
                enemy.skillList = new int[skillSum];
                for (int j = 0; j < skillSum; j++) {
                    enemy.skillList[j] = dis.readInt();
                    System.out.println("enemy.skillList[j] " + enemy.skillList[j]);
                }
                enemys.put(enemy.index, enemy);
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("enemy.gat readError!");
        }
    }

    public void save() {
    }
}
