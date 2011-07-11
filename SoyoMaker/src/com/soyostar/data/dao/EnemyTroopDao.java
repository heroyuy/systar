package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.EnemyTroop;
import com.soyostar.data.model.Item;
import com.soyostar.data.model.Model;
import com.soyostar.project.Project;
import com.soyostar.proxy.SoftProxy;
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

    /**
     * 
     */
    public void load() {
        System.out.println("enemytroop load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(SoftProxy.getInstance().getCurProject().getPath() + "/data/enemytroop.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);

            int enemyTroopSum = dis.readInt();
            EnemyTroop enemyTroop = null;
            for (int i = 0; i < enemyTroopSum; i++) {
                enemyTroop = new EnemyTroop();
                enemyTroop.setIndex(dis.readInt());
                enemyTroop.name = dis.readUTF();
                enemyTroop.siteA = dis.readInt();
//                System.out.println("siteA" + enemyTroop.siteA);
                enemyTroop.siteB = dis.readInt();
//                System.out.println("siteB" + enemyTroop.siteB);
                enemyTroop.siteC = dis.readInt();
//                System.out.println("siteC" + enemyTroop.siteC);
                enemyTroop.siteD = dis.readInt();
//                System.out.println("siteD" + enemyTroop.siteD);
                int itemSum = dis.readInt();
//                enemyTroop.itemList = new int[itemSum];
                for (int j = 0; j < itemSum; j++) {
                    enemyTroop.itemList.add((Item) SoftProxy.getInstance().getCurProject().getDataManager().getModel(Model.ITEM, dis.readInt()));
                }
                SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.ENEMYTROOP, enemyTroop);
//                saveModel(enemyTroop);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
            System.out.println("没有可加载的EnemyTroop");
        }
    }

    /**
     * 
     * @return
     */
    @Override
    public boolean save() {
        System.out.println("enemytroop save");
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(SoftProxy.getInstance().getCurProject().getPath() + "/data/enemytroop.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);

            dos.writeInt(size());
            EnemyTroop[] enemyTroops = this.getModels(EnemyTroop.class);
            EnemyTroop enemyTroop = null;
            for (int i = 0; i < size(); i++) {
                enemyTroop = enemyTroops[i];
                dos.writeInt(enemyTroop.getIndex());
                dos.writeUTF(enemyTroop.name);
                dos.writeInt(enemyTroop.siteA);
//                System.out.println("siteA" + enemyTroop.siteA);
                dos.writeInt(enemyTroop.siteB);
//                System.out.println("siteB" + enemyTroop.siteB);
                dos.writeInt(enemyTroop.siteC);
//                System.out.println("siteC" + enemyTroop.siteC);
                dos.writeInt(enemyTroop.siteD);
//                System.out.println("siteD" + enemyTroop.siteD);
                dos.writeInt(enemyTroop.itemList.size());
                for (int j = 0; j < enemyTroop.itemList.size(); j++) {
                    dos.writeInt(enemyTroop.itemList.get(j).getIndex());
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
            System.out.println("enemytroop.gat writeError!");
            return false;
        }
    }
//            //单元测试
//    public static void main(String[] args) {
//        new EnemyTroopDao().load();
//    }
}
