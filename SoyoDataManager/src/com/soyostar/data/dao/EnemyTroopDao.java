package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.EnemyTroop;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public class EnemyTroopDao extends Dao<EnemyTroop> {

    public void load() {
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/enemytroop.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);

            int enemyTroopSum = dis.readInt();
            EnemyTroop enemyTroop = null;
            for (int i = 0; i < enemyTroopSum; i++) {
                enemyTroop = new EnemyTroop();
                enemyTroop.setIndex(dis.readInt());
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
                saveModel(enemyTroop);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("enemytroop.gat readError!");
        }
    }

    public void save() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/enemytroop.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);

            dos.writeInt(size());
            EnemyTroop[] enemyTroops = this.getModels();
            EnemyTroop enemyTroop = null;
            for (int i = 0; i < size(); i++) {
                enemyTroop = enemyTroops[i];
                dos.writeInt(enemyTroop.getIndex());
                dos.writeUTF(enemyTroop.name);
                dos.writeInt(enemyTroop.siteA);
                dos.writeInt(enemyTroop.siteB);
                dos.writeInt(enemyTroop.siteC);
                dos.writeInt(enemyTroop.siteD);
                dos.writeInt(enemyTroop.itemList.length);
                for (int j = 0; j < enemyTroop.itemList.length; j++) {
                    dos.writeInt(enemyTroop.itemList[j]);

                }
            }
            dos.close();
            fos.close();
            f = null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("enemytroop.gat writeError!");
        }
    }
//            //单元测试
//    public static void main(String[] args) {
//        new EnemyTroopDao().load();
//    }
}
