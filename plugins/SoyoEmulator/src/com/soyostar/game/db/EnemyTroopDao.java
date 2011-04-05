/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.EnemyTroop;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class EnemyTroopDao implements Dao {

    private DataInputStream dis = null;
    private HashMap enemyTroops = null;

    public EnemyTroop getEnemyTroop(int index) {
        return (EnemyTroop) enemyTroops.get(index);
    }

    public EnemyTroop[] getEnemyTroopList() {
        return (EnemyTroop[]) enemyTroops.values().toArray();
    }

    public void load() {
        enemyTroops = new HashMap();
        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_ENEMYTROOP, 0);
        EnemyTroop[] enemyTroopList = null;
        try {
            int enemyTroopSum = dis.readInt();
            EnemyTroop enemyTroop = null;
            for (int i = 0; i < enemyTroopSum; i++) {
                enemyTroop = new EnemyTroop();
                enemyTroop.index = dis.readInt();
                enemyTroop.name = dis.readUTF();
                enemyTroop.siteA = dis.readInt();
                enemyTroop.siteB = dis.readInt();
                enemyTroop.siteC = dis.readInt();
                enemyTroop.siteD = dis.readInt();
                int itemSum = dis.readInt();
                enemyTroop.itemList = new int[itemSum];
                for (int j = 0; j < itemSum; j++) {
                    enemyTroop.itemList[j] = dis.readInt();

                }
                enemyTroops.put(enemyTroop.index, enemyTroop);
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("enemytroop.gat readError!");
        }
    }

    public void save() {
    }
}
