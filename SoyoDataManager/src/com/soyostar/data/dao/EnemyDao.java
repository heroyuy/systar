/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.Enemy;
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
public class EnemyDao extends Dao<Enemy> {

    public void load() {
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/enemy.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int enemySum = dis.readInt();
            Enemy enemy = null;
            for (int i = 0; i < enemySum; i++) {
                enemy = new Enemy();
                enemy.setIndex(dis.readInt());
                enemy.name = dis.readUTF();
                enemy.intro = dis.readUTF();
                enemy.headImg = "/image/battler/" + dis.readUTF();
                enemy.agil = dis.readInt();
                enemy.stre = dis.readInt();
                enemy.inte = dis.readInt();
                enemy.maxHp = dis.readInt();
                enemy.maxSp = dis.readInt();
                enemy.lev = dis.readInt();
                enemy.atk = dis.readInt();
                enemy.def = dis.readInt();
                enemy.exp = dis.readInt();
                enemy.money = dis.readInt();
                int skillSum = dis.readInt();
                enemy.skillList = new int[skillSum];
                for (int j = 0; j < skillSum; j++) {
                    enemy.skillList[j] = dis.readInt();
                }
                saveModel(enemy);
            }
            dis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("enemy.gat readError!");
        }
    }

    public void save() {

        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/enemy.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);

            dos.writeInt(size());
            Enemy[] enemys = this.getModels();
            Enemy enemy = null;
            for (int i = 0; i < size(); i++) {
                enemy = enemys[i];
                dos.writeInt(enemy.getIndex());
                dos.writeUTF(enemy.name);
                dos.writeUTF(enemy.intro);
                dos.writeUTF(enemy.headImg);
                dos.writeInt(enemy.agil);
                dos.writeInt(enemy.stre);
                dos.writeInt(enemy.inte);
                dos.writeInt(enemy.maxHp);
                dos.writeInt(enemy.maxSp);
                dos.writeInt(enemy.lev);
                dos.writeInt(enemy.atk);
                dos.writeInt(enemy.def);
                dos.writeInt(enemy.exp);
                dos.writeInt(enemy.money);
                dos.writeInt(enemy.skillList.length);
                for (int j = 0; j < enemy.skillList.length; j++) {
                    dos.writeInt(enemy.skillList[j]);
                }
            }
            dos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("enemy.gat writeError!");
        }
    }
//    //单元测试
//    public static void main(String[] args) {
//        new EnemyDao().load();
//    }
}
