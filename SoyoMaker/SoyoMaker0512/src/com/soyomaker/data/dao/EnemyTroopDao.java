/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Enemy;
import com.soyomaker.data.model.EnemyTroop;
import com.soyomaker.data.model.Model;
import com.soyomaker.log.LogPrinter;
import com.soyomaker.log.LogPrinterFactory;
import com.soyomaker.lua.LuaFileUtil;
import com.soyomaker.lua.LuaNode;
import com.soyomaker.lua.LuaTable;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class EnemyTroopDao extends Dao<EnemyTroop> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    /**
     *
     * @throws FileNotFoundException 
     * @throws IOException
     */
    public void load() throws FileNotFoundException, IOException {
//        System.out.println("enemytroop load");
        printer.v("enemytroop load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;

        f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/enemytroop.gat");
        fis = new FileInputStream(f);
        dis = new DataInputStream(fis);
        int enemyTroopSum = dis.readInt();
        EnemyTroop enemyTroop = null;
        for (int i = 0; i < enemyTroopSum; i++) {
            enemyTroop = new EnemyTroop();
            enemyTroop.setIndex(dis.readInt());
            enemyTroop.name = dis.readUTF();
            int enemyN = dis.readInt();
            for (int j = 0; j < enemyN; j++) {
                Enemy enemy = (Enemy) AppData.getInstance().getCurProject().getDataManager().getModel(Model.ENEMY, dis.readInt());
                int x = dis.readInt();
                int y = dis.readInt();
                Point point = new Point();
                point.x = x;
                point.y = y;
                enemyTroop.enemys.add(enemy);
                enemyTroop.points.add(point);
            }
            AppData.getInstance().getCurProject().getDataManager().saveModel(Model.ENEMYTROOP, enemyTroop);
        }
        dis.close();
        fis.close();
        f = null;
    }

    private boolean saveLua() {
        EnemyTroop[] enemyTroops = this.getModels(EnemyTroop.class);
        EnemyTroop enemyTroop = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            enemyTroop = enemyTroops[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", Configuration.Prefix.ENEMYTROOP_MASK + enemyTroop.getIndex() + 1);
            lt.addNode("\n");
            lt.addNode("name", enemyTroop.name);
            lt.addNode("\n");
            LuaTable ens = new LuaTable();
            for (int j = 0; j < enemyTroop.enemys.size(); j++) {
                ens.addNode("id", Configuration.Prefix.ENEMY_MASK + enemyTroop.enemys.get(j).getIndex() + 1);
                ens.addNode("x", enemyTroop.points.get(j).x);
                ens.addNode("y", enemyTroop.points.get(j).y);
            }
            lt.addNode("enemyList", ens);
            lts.addNode("[" + (Configuration.Prefix.ENEMYTROOP_MASK + enemyTroop.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("Dictionary.enemytroops", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/enemytroop.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("enemytroop.gat 写入失败");
            printer.e("enemytroop.gat 写入失败");
            return false;
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/enemytroop.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);

            dos.writeInt(size());
            EnemyTroop[] enemyTroops = this.getModels(EnemyTroop.class);
            EnemyTroop enemyTroop = null;
            for (int i = 0; i < size(); i++) {
                enemyTroop = enemyTroops[i];
                dos.writeInt(enemyTroop.getIndex());
                dos.writeUTF(enemyTroop.name);
                dos.writeInt(enemyTroop.enemys.size());
                for (int j = 0; j < enemyTroop.enemys.size(); j++) {
                    dos.writeInt(enemyTroop.enemys.get(j).getIndex());
                    dos.writeInt(enemyTroop.points.get(j).x);
                    dos.writeInt(enemyTroop.points.get(j).y);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("enemytroop.gat writeError!");
            printer.e("enemytroop.gat writeError!");
            return false;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean save() {
//        System.out.println("enemytroop save");
        printer.v("enemytroop save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("enemytroop save");
        boolean value = false;
        if (saveBin()) {
            value = true;
        }
        return value;
    }
}
