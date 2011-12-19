/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.data.model.Condition;
import com.soyomaker.data.model.Enemy;
import com.soyomaker.data.model.EnemyTroop;
import com.soyomaker.data.model.Event;
import com.soyomaker.data.model.Model;
import com.soyostar.lua.LuaNode;
import com.soyostar.lua.LuaTable;
import com.soyostar.lua.util.LuaFileUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class EnemyTroopDao extends Dao<EnemyTroop> {

    /**
     *
     */
    public void load() {
//        System.out.println("enemytroop load");
        AppData.getInstance().getLogger().v("enemytroop load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
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
                    enemyTroop.enemys.add(enemy);
                }
                int eventN = dis.readInt();
                for (int j = 0; j < eventN; j++) {
                    Event event = new Event();
                    int conditionN = dis.readInt();
                    for (int k = 0; k < conditionN; k++) {
                        Condition con = new Condition();
                        con.conditionType = dis.readInt();
                        int pars = dis.readInt();
                        for (int m = 0; m < pars; m++) {
                            con.paras.add(dis.readInt());
                        }
                        event.conditions.add(con);
                    }
                    event.eventType = dis.readInt();
                    event.scriptIndex = dis.readInt();
                    enemyTroop.events.add(event);
                }
                AppData.getInstance().getCurProject().getDataManager().saveModel(Model.ENEMYTROOP, enemyTroop);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
//            System.out.println("没有可加载的EnemyTroop");
            AppData.getInstance().getLogger().v("没有可加载的EnemyTroop");
        }
    }

    private boolean saveLua() {
        EnemyTroop[] enemyTroops = this.getModels(EnemyTroop.class);
        EnemyTroop enemyTroop = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            enemyTroop = enemyTroops[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", enemyTroop.getIndex());
            lt.addNode("\n");
            lt.addNode("name", enemyTroop.name);
            lt.addNode("\n");
            LuaTable ens = new LuaTable();
            for (int j = 0; j < enemyTroop.enemys.size(); j++) {
                ens.addNode(enemyTroop.enemys.get(j).getIndex());
            }
            LuaTable evs = new LuaTable();
            if (!enemyTroop.events.isEmpty()) {
                evs.addNode("\n");
            }
            for (int j = 0; j < enemyTroop.events.size(); j++) {
                LuaTable ev = new LuaTable();
                LuaTable conds = new LuaTable();
                for (int k = 0; k < enemyTroop.events.get(j).conditions.size(); k++) {
                    LuaTable cond = new LuaTable();
                    cond.addNode("conditionType", enemyTroop.events.get(j).conditions.get(k).conditionType);
                    LuaTable paras = new LuaTable();
                    for (int m = 0; m < enemyTroop.events.get(j).conditions.get(k).paras.size(); m++) {
                        paras.addNode(enemyTroop.events.get(j).conditions.get(k).paras.get(m));
                    }
                    cond.addNode("parameters", paras);
                    conds.addNode(cond);
                }
                ev.addNode("eventType", enemyTroop.events.get(j).eventType);
                ev.addNode("scriptIndex", enemyTroop.events.get(j).scriptIndex);
                evs.addNode(ev);
                evs.addNode("\n");
            }
            lt.addNode("enemysIndex", ens);
            lts.addNode("[" + enemyTroop.getIndex() + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("globalDictionary.enemytroops", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/enemytroop.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("enemytroop.gat 写入失败");
            AppData.getInstance().getLogger().e("enemytroop.gat 写入失败");
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
                }
                dos.writeInt(enemyTroop.events.size());
                for (int j = 0; j < enemyTroop.events.size(); j++) {
                    Event event = enemyTroop.events.get(j);
                    dos.writeInt(event.conditions.size());
                    for (int k = 0; k < event.conditions.size(); k++) {
                        Condition con = event.conditions.get(k);
                        dos.writeInt(con.conditionType);
                        dos.writeInt(con.paras.size());
                        for (int m = 0; m < con.paras.size(); m++) {
                            dos.writeInt(con.paras.get(m));
                        }
                    }
                    dos.writeInt(event.eventType);
                    dos.writeInt(event.scriptIndex);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("enemytroop.gat writeError!");
            AppData.getInstance().getLogger().e("enemytroop.gat writeError!");
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
        AppData.getInstance().getLogger().v("enemytroop save");
        boolean value = false;
        if (saveLua() && saveBin()) {
            value = true;
        }
        return value;
    }
}
