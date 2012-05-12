/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Action;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Condition;
import com.soyomaker.data.model.Enemy;
import com.soyomaker.data.model.Equip;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Status;
import com.soyomaker.data.model.Treasure;
import com.soyomaker.log.LogPrinter;
import com.soyomaker.log.LogPrinterFactory;
import com.soyomaker.lua.LuaFileUtil;
import com.soyomaker.lua.LuaNode;
import com.soyomaker.lua.LuaTable;
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
public class EnemyDao extends Dao<Enemy> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void load() throws FileNotFoundException, IOException {
        printer.v("enemy load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
//        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/enemy.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int enemySum = dis.readInt();
            Enemy enemy = null;
            for (int i = 0; i < enemySum; i++) {
                enemy = new Enemy();
                enemy.setIndex(dis.readInt());
                enemy.name = dis.readUTF();
                enemy.intro = dis.readUTF();
                enemy.battleImg = dis.readUTF();
                enemy.lev = dis.readInt();
                enemy.maxHp = dis.readInt();
                enemy.maxSp = dis.readInt();
                enemy.stre = dis.readInt();
                enemy.inte = dis.readInt();
                enemy.agil = dis.readInt();
                enemy.dex = dis.readInt();
                enemy.body = dis.readInt();
                enemy.luck = dis.readInt();
                enemy.exp = dis.readInt();
                enemy.money = dis.readInt();
                int equipN = dis.readInt();
                for (int j = 0; j < equipN; j++) {
                    int kind = dis.readInt();
                    Equip equip = (Equip) AppData.getInstance().getCurProject().getDataManager().getModel(Model.EQUIP, dis.readInt());
                    equip.equipType = kind;
                    equip.rate = dis.readInt();
                    enemy.equips.add(equip);
                }
                int treasureN = dis.readInt();
                for (int j = 0; j < treasureN; j++) {
                    int type = dis.readInt();
                    switch (type) {
                        case Treasure.TREASURE_ITEM:
                            Item item = (Item) AppData.getInstance().getCurProject().getDataManager().getModel(Model.ITEM, dis.readInt());
                            item.rate = dis.readInt();
                            enemy.treasures.add(item);
                            break;
                        case Treasure.TREASURE_EQUIP:
                            Equip equip = (Equip) AppData.getInstance().getCurProject().getDataManager().getModel(Model.EQUIP, dis.readInt());
                            equip.rate = dis.readInt();
                            enemy.treasures.add(equip);
                            break;
                    }
                }
                int actionN = dis.readInt();
                for (int j = 0; j < actionN; j++) {
                    Action action = new Action();
                    int conditionN = dis.readInt();
                    for (int k = 0; k < conditionN; k++) {
                        Condition con = new Condition();
                        con.conditionType = dis.readInt();
                        int pars = dis.readInt();
                        for (int m = 0; m < pars; m++) {
                            con.paras.add(dis.readInt());
                        }
                        action.conds.add(con);
                    }
                    action.actionType = dis.readInt();
                    int actionParaN = dis.readInt();
                    for (int k = 0; k < actionParaN; k++) {
                        action.paras.add(dis.readInt());
                    }
                    action.rate = dis.readInt();
                    enemy.actions.add(action);
                }
                int attrN = dis.readInt();
                for (int j = 0; j < attrN; j++) {
                    Attribute attr = new Attribute();
                    attr.id = dis.readInt();
                    attr.value = dis.readInt();
                    enemy.attributes.add(attr);
                }
                int statusN = dis.readInt();
                for (int j = 0; j < statusN; j++) {
                    Status status = (Status) AppData.getInstance().getCurProject().getDataManager().getModel(Model.STATUS, dis.readInt());
                    status.value = dis.readInt();
                    enemy.status.add(status);
                }
                AppData.getInstance().getCurProject().getDataManager().saveModel(Model.ENEMY, enemy);
            }
            dis.close();
            fis.close();
//        } catch (IOException e) {
////            System.out.println("没有可加载的Enemy");
//            printer.e("没有可加载的Enemy");
//        }
    }

    private boolean saveLua() {
        Enemy[] enemys = this.getModels(Enemy.class);
        Enemy enemy = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            enemy = enemys[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", Configuration.Prefix.ENEMY_MASK + enemy.getIndex() + 1);
            lt.addNode("\n");
            lt.addNode("name", enemy.name);
            lt.addNode("\n");
            lt.addNode("intro", enemy.intro);
            lt.addNode("\n");
            if (enemy.battleImg == null || enemy.battleImg.equals("")) {
                lt.addNode("battleImg", "nil");
            } else {
                lt.addNode("battleImg", "/image/battler/" + enemy.battleImg);
            }
            lt.addNode("\n");
            lt.addNode("lev", enemy.lev);
            lt.addNode("\n");
            lt.addNode("maxHp", enemy.maxHp);
            lt.addNode("\n");
            lt.addNode("maxSp", enemy.maxSp);
            lt.addNode("\n");
            lt.addNode("stre", enemy.stre);
            lt.addNode("\n");
            lt.addNode("inte", enemy.inte);
            lt.addNode("\n");
            lt.addNode("agil", enemy.agil);
            lt.addNode("\n");
            lt.addNode("dext", enemy.dex);
            lt.addNode("\n");
            lt.addNode("vita", enemy.body);
            lt.addNode("\n");
            lt.addNode("luck", enemy.luck);
            lt.addNode("\n");
            lt.addNode("exp", enemy.exp);
            lt.addNode("\n");
            lt.addNode("money", enemy.money);
            lt.addNode("\n");
            LuaTable treas = new LuaTable();
            if (!enemy.treasures.isEmpty()) {
                treas.addNode("\n");
            }
            for (int j = 0; j < enemy.treasures.size(); j++) {
                LuaTable trea = new LuaTable();
                if (enemy.treasures.get(j) instanceof Item) {
                    trea.addNode("type", Treasure.TREASURE_ITEM);
                    trea.addNode("index", Configuration.Prefix.ITEM_MASK + enemy.treasures.get(j).getIndex() + 1);
                    trea.addNode("rate", enemy.treasures.get(j).rate);
                    treas.addNode("[" + (Configuration.Prefix.ITEM_MASK + enemy.treasures.get(j).getIndex() + 1) + "]", trea);
                } else {
                    trea.addNode("type", Treasure.TREASURE_EQUIP);
                    trea.addNode("index", Configuration.Prefix.EQUIP_MASK + enemy.treasures.get(j).getIndex() + 1);
                    trea.addNode("rate", enemy.treasures.get(j).rate);
                    treas.addNode("[" + (Configuration.Prefix.EQUIP_MASK + enemy.treasures.get(j).getIndex() + 1) + "]", trea);
                }

                if (j != enemy.treasures.size() - 1) {
                    treas.addNode("\n");
                }
            }
            lt.addNode("treasures", treas);
            lt.addNode("\n");
            LuaTable eqs = new LuaTable();
            if (!enemy.equips.isEmpty()) {
                eqs.addNode("\n");
            }
            for (int j = 0; j < enemy.equips.size(); j++) {
                LuaTable eq = new LuaTable();
                eq.addNode("kind", enemy.equips.get(j).equipType);
                eq.addNode("index", Configuration.Prefix.EQUIP_MASK + enemy.equips.get(j).getIndex() + 1);
                eq.addNode("rate", enemy.equips.get(j).rate);
                eqs.addNode("[" + (Configuration.Prefix.EQUIP_MASK + enemy.equips.get(j).getIndex() + 1) + "]", eq);
                if (j != enemy.equips.size() - 1) {
                    eqs.addNode("\n");
                }
            }
            lt.addNode("equips", eqs);
            lt.addNode("\n");
            LuaTable acts = new LuaTable();
            if (!enemy.actions.isEmpty()) {
                acts.addNode("\n");
            }
            for (int j = 0; j < enemy.actions.size(); j++) {
                LuaTable act = new LuaTable();
                LuaTable conds = new LuaTable();
                for (int k = 0; k < enemy.actions.get(j).conds.size(); k++) {
                    LuaTable cond = new LuaTable();
                    cond.addNode("conditionType", enemy.actions.get(j).conds.get(k).conditionType + 1);
                    LuaTable paras = new LuaTable();
                    for (int m = 0; m < enemy.actions.get(j).conds.get(k).paras.size(); m++) {
                        paras.addNode(enemy.actions.get(j).conds.get(k).paras.get(m));
                    }
                    cond.addNode("parameters", paras);
                    conds.addNode("[" + (enemy.actions.get(j).conds.get(k).conditionType + 1) + "]", cond);
                }
                act.addNode("conditions", conds);
                LuaTable pars = new LuaTable();
                for (int m = 0; m < enemy.actions.get(j).paras.size(); m++) {
                    pars.addNode(enemy.actions.get(j).paras.get(m));
                }
                act.addNode("parameters", pars);
                act.addNode("actionType", enemy.actions.get(j).actionType);
                act.addNode("rate", enemy.actions.get(j).rate);
                acts.addNode("[" + enemy.actions.get(j).actionType + "]", act);
                if (j != enemy.actions.size() - 1) {
                    acts.addNode("\n");
                }
            }
            lt.addNode("actions", acts);
            lt.addNode("\n");
            LuaTable attrs = new LuaTable();
            if (!enemy.attributes.isEmpty()) {
                attrs.addNode("\n");
            }
            for (int j = 0; j < enemy.attributes.size(); j++) {
                LuaTable attr = new LuaTable();
                attr.addNode("index", Configuration.Prefix.ATTRIBUTE_MASK + enemy.attributes.get(j).id + 1);
                attr.addNode("value", enemy.attributes.get(j).value);
                attrs.addNode("[" + (Configuration.Prefix.ATTRIBUTE_MASK + enemy.attributes.get(j).id + 1) + "]", attr);
                if (j != enemy.attributes.size() - 1) {
                    attrs.addNode("\n");
                }
            }
            lt.addNode("attributes", attrs);
            lt.addNode("\n");
            LuaTable status = new LuaTable();
            if (!enemy.status.isEmpty()) {
                status.addNode("\n");
            }
            for (int j = 0; j < enemy.status.size(); j++) {
                LuaTable statu = new LuaTable();
                statu.addNode("index", Configuration.Prefix.STATUS_MASK + enemy.status.get(j).getIndex() + 1);
                statu.addNode("value", enemy.status.get(j).value);
                status.addNode("[" + (Configuration.Prefix.STATUS_MASK + enemy.status.get(j).getIndex() + 1) + "]", statu);
                if (j != enemy.status.size() - 1) {
                    status.addNode("\n");
                }
            }
            lt.addNode("buffs", status);
            lts.addNode("[" + (Configuration.Prefix.ENEMY_MASK + enemy.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("Dictionary.enemys", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/enemy.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("enemy.gat 写入失败");
            printer.e("enemy.gat 写入失败");
            return false;
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/enemy.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);

            dos.writeInt(size());
            Enemy[] enemys = this.getModels(Enemy.class);
            Enemy enemy = null;
            for (int i = 0; i < size(); i++) {
                enemy = enemys[i];
                dos.writeInt(enemy.getIndex());
                dos.writeUTF(enemy.name);
                dos.writeUTF(enemy.intro);
                dos.writeUTF(enemy.battleImg);
                dos.writeInt(enemy.lev);
                dos.writeInt(enemy.maxHp);
                dos.writeInt(enemy.maxSp);
                dos.writeInt(enemy.stre);
                dos.writeInt(enemy.inte);
                dos.writeInt(enemy.agil);
                dos.writeInt(enemy.dex);
                dos.writeInt(enemy.body);
                dos.writeInt(enemy.luck);
                dos.writeInt(enemy.exp);
                dos.writeInt(enemy.money);
                dos.writeInt(enemy.equips.size());
                for (int j = 0; j < enemy.equips.size(); j++) {
                    dos.writeInt(enemy.equips.get(j).equipType);
                    dos.writeInt(enemy.equips.get(j).getIndex());
                    dos.writeInt(enemy.equips.get(j).rate);
                }
                dos.writeInt(enemy.treasures.size());
                for (int j = 0; j < enemy.treasures.size(); j++) {
                    if (enemy.treasures.get(j) instanceof Item) {
                        dos.writeInt(Treasure.TREASURE_ITEM);
                    } else {
                        dos.writeInt(Treasure.TREASURE_EQUIP);
                    }
                    dos.writeInt(enemy.treasures.get(j).getIndex());
                    dos.writeInt(enemy.treasures.get(j).rate);
                }
                dos.writeInt(enemy.actions.size());
                for (int j = 0; j < enemy.actions.size(); j++) {
                    Action action = enemy.actions.get(j);
                    dos.writeInt(action.conds.size());
                    for (int k = 0; k < action.conds.size(); k++) {
                        Condition con = action.conds.get(k);
                        dos.writeInt(con.conditionType);
                        dos.writeInt(con.paras.size());
                        for (int m = 0; m < con.paras.size(); m++) {
                            dos.writeInt(con.paras.get(m));
                        }
                    }
                    dos.writeInt(action.actionType);
                    dos.writeInt(action.paras.size());
                    for (int k = 0; k < action.paras.size(); k++) {
                        dos.writeInt(action.paras.get(k));
                    }
                    dos.writeInt(action.rate);
                }
                dos.writeInt(enemy.attributes.size());
                for (int j = 0; j < enemy.attributes.size(); j++) {
                    dos.writeInt(enemy.attributes.get(j).id);
                    dos.writeInt(enemy.attributes.get(j).value);
                }
                dos.writeInt(enemy.status.size());
                for (int j = 0; j < enemy.status.size(); j++) {
                    dos.writeInt(enemy.status.get(j).getIndex());
                    dos.writeInt(enemy.status.get(j).value);
                }
            }
            dos.close();
            fos.close();
            return true;
        } catch (IOException e) {
//            System.out.println("enemy.gat writeError!");
            printer.e("enemy.gat 写入失败");
            return false;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean save() {
//        System.out.println("enemy save");
        printer.v("enemy save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("enemy save");
        boolean value = false;
        if (saveBin()) {
            value = true;
        }
        return value;
    }
}
