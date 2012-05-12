/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Cost;
import com.soyomaker.data.model.Effect;
import com.soyomaker.data.model.Factor;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Status;
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
public class ItemDao extends Dao<Item> {

    /**
     *
     */
    @Override
    public void load() {
//        System.out.println("item load");
        AppData.getInstance().getLogger().v("item load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/item.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int itemSum = dis.readInt();
            Item item = null;
            for (int i = 0; i < itemSum; i++) {
                item = new Item();
                item.setIndex(dis.readInt());
                item.name = dis.readUTF();
                item.intro = dis.readUTF();
                item.icon = dis.readUTF();
                item.lev = dis.readInt();
                item.target = dis.readInt();
                item.limit = dis.readInt();
                item.price = dis.readInt();
                item.consumable = dis.readBoolean();
                item.userAniIndex = dis.readInt();
                item.targetAniIndex = dis.readInt();
                item.menuUseSound = dis.readUTF();
                item.eventIndex = dis.readInt();
                int costN = dis.readInt();
                for (int j = 0; j < costN; j++) {
                    Cost cost = new Cost();
                    cost.costType = dis.readInt();
                    cost.costName = dis.readUTF();
                    cost.costValue = dis.readInt();
                    item.costs.add(cost);
                }
                int effectN = dis.readInt();
                for (int j = 0; j < effectN; j++) {
                    Effect effect = new Effect();
                    effect.effectType = dis.readInt();
                    effect.effectName = dis.readUTF();
                    effect.effectValue = dis.readInt();
                    item.effects.add(effect);
                }
                int factorN = dis.readInt();
                for (int j = 0; j < factorN; j++) {
                    Factor factor = new Factor();
                    factor.factorType = dis.readInt();
                    factor.factorName = dis.readUTF();
                    factor.factorValue = dis.readInt();
                    item.factors.add(factor);
                }
                int attrN = dis.readInt();
                for (int j = 0; j < attrN; j++) {
                    Attribute attr = new Attribute();
                    attr.id = dis.readInt();
                    attr.value = dis.readInt();
                    item.attributes.add(attr);
                }
                int statusN = dis.readInt();
                for (int j = 0; j < statusN; j++) {
                    Status status = (Status) AppData.getInstance().getCurProject().getDataManager().getModel(Model.STATUS, dis.readInt());
                    status.value = dis.readInt();
                    item.status.add(status);
                }
                AppData.getInstance().getCurProject().getDataManager().saveModel(Model.ITEM, item);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
//            System.out.println("没有可加载的Item");
            AppData.getInstance().getLogger().e("没有可加载的Item");
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/item.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Item[] items = this.getModels(Item.class);
            Item item = null;
            for (int i = 0; i < size(); i++) {
                item = items[i];
                dos.writeInt(item.getIndex());
                dos.writeUTF(item.name);
                dos.writeUTF(item.intro);
                dos.writeUTF(item.icon);
                dos.writeInt(item.lev);
                dos.writeInt(item.target);
                dos.writeInt(item.limit);
                dos.writeInt(item.price);
                dos.writeBoolean(item.consumable);
                dos.writeInt(item.userAniIndex);
                dos.writeInt(item.targetAniIndex);
                dos.writeUTF(item.menuUseSound);
                dos.writeInt(item.eventIndex);
                dos.writeInt(item.costs.size());
                for (int j = 0; j < item.costs.size(); j++) {
                    dos.writeInt(item.costs.get(j).costType);
                    dos.writeUTF(item.costs.get(j).costName);
                    dos.writeInt(item.costs.get(j).costValue);
                }
                dos.writeInt(item.effects.size());
                for (int j = 0; j < item.effects.size(); j++) {
                    dos.writeInt(item.effects.get(j).effectType);
                    dos.writeUTF(item.effects.get(j).effectName);
                    dos.writeInt(item.effects.get(j).effectValue);
                }
                dos.writeInt(item.factors.size());
                for (int j = 0; j < item.factors.size(); j++) {
                    dos.writeInt(item.factors.get(j).factorType);
                    dos.writeUTF(item.factors.get(j).factorName);
                    dos.writeInt(item.factors.get(j).factorValue);
                }
                dos.writeInt(item.attributes.size());
                for (int j = 0; j < item.attributes.size(); j++) {
                    dos.writeInt(item.attributes.get(j).id);
                    dos.writeInt(item.attributes.get(j).value);
                }
                dos.writeInt(item.status.size());
                for (int j = 0; j < item.status.size(); j++) {
                    dos.writeInt(item.status.get(j).getIndex());
                    dos.writeInt(item.status.get(j).value);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("item.gat 写入失败!");
            AppData.getInstance().getLogger().e("item.gat 写入失败!");
            return false;
        }
    }

    private boolean saveLua() {
        Item[] items = this.getModels(Item.class);
        Item item = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            item = items[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", item.getIndex() + 1);
            lt.addNode("\n");
            lt.addNode("name", item.name);
            lt.addNode("\n");
            lt.addNode("intro", item.intro);
            lt.addNode("\n");
            if (item.icon == null || item.icon.equals("")) {
                lt.addNode("icon", "nil");
            } else {
                lt.addNode("icon", "/image/icon/item/" + item.icon);
            }
            lt.addNode("\n");
            lt.addNode("target", item.target);
            lt.addNode("\n");
            lt.addNode("limit", item.limit);
            lt.addNode("\n");
            lt.addNode("lev", item.lev);
            lt.addNode("\n");
            if (item.userAniIndex == -1) {
                lt.addNode("useAniIndex", -1);
            } else {
                lt.addNode("useAniIndex", item.userAniIndex);
            }
            lt.addNode("\n");
            if (item.targetAniIndex == -1) {
                lt.addNode("targetAniIndex", -1);
            } else {
                lt.addNode("targetAniIndex", item.targetAniIndex);
            }
            lt.addNode("\n");
            lt.addNode("price", item.price);
            lt.addNode("\n");
            lt.addNode("consumable", item.consumable);
            lt.addNode("\n");
            if (item.menuUseSound == null || item.menuUseSound.equals("")) {
                lt.addNode("sound", "nil");
            } else {
                lt.addNode("sound", "/audio/sound/" + item.menuUseSound);
            }
            lt.addNode("\n");
            lt.addNode("eventIndex", item.eventIndex);
            lt.addNode("\n");
            LuaTable efs = new LuaTable();
            if (!item.effects.isEmpty()) {
                efs.addNode("\n");
            }
            for (int j = 0; j < item.effects.size(); j++) {
                LuaTable ef = new LuaTable();
                ef.addNode("index", item.effects.get(j).effectType + 1);
                ef.addNode("name", item.effects.get(j).effectName);
                ef.addNode("value", item.effects.get(j).effectValue);
                efs.addNode("[" + (item.effects.get(j).effectType + 1) + "]", ef);
                if (j != item.effects.size() - 1) {
                    efs.addNode("\n");
                }
            }
            lt.addNode("effects", efs);
            lt.addNode("\n");
            LuaTable cos = new LuaTable();
            if (!item.costs.isEmpty()) {
                cos.addNode("\n");
            }
            for (int j = 0; j < item.costs.size(); j++) {
                LuaTable co = new LuaTable();
                co.addNode("index", item.costs.get(j).costType + 1);
                co.addNode("name", item.costs.get(j).costName);
                co.addNode("value", item.costs.get(j).costValue);
                cos.addNode("[" + (item.costs.get(j).costType + 1) + "]", co);
                if (j != item.costs.size() - 1) {
                    cos.addNode("\n");
                }
            }
            lt.addNode("costs", cos);
            lt.addNode("\n");
            LuaTable fas = new LuaTable();
            if (!item.factors.isEmpty()) {
                fas.addNode("\n");
            }
            for (int j = 0; j < item.factors.size(); j++) {
                LuaTable fa = new LuaTable();
                fa.addNode("index", item.factors.get(j).factorType + 1);
                fa.addNode("name", item.factors.get(j).factorName);
                fa.addNode("value", item.factors.get(j).factorValue);
                fas.addNode("[" + (item.factors.get(j).factorType + 1) + "]", fa);
                if (j != item.factors.size() - 1) {
                    fas.addNode("\n");
                }
            }
            lt.addNode("factors", fas);
            lt.addNode("\n");
            LuaTable attrs = new LuaTable();
            if (!item.attributes.isEmpty()) {
                attrs.addNode("\n");
            }
            for (int j = 0; j < item.attributes.size(); j++) {
                LuaTable attr = new LuaTable();
                attr.addNode("index", item.attributes.get(j).id + 1);
                attr.addNode("value", item.attributes.get(j).value);
                attrs.addNode("[" + (item.attributes.get(j).id + 1) + "]", attr);
                if (j != item.attributes.size() - 1) {
                    attrs.addNode("\n");
                }
            }
            lt.addNode("attributes", attrs);
            lt.addNode("\n");
            LuaTable status = new LuaTable();
            if (!item.status.isEmpty()) {
                status.addNode("\n");
            }
            for (int j = 0; j < item.status.size(); j++) {
                LuaTable statu = new LuaTable();
                statu.addNode("index", item.status.get(j).getIndex() + 1);
                statu.addNode("value", item.status.get(j).value);
                status.addNode("[" + (item.status.get(j).getIndex() + 1) + "]", statu);
                if (j != item.status.size() - 1) {
                    status.addNode("\n");
                }
            }
            lt.addNode("buffs", status);
            lts.addNode("[" + (item.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("globalDictionary.items", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/item.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("item.gat 写入失败");
            AppData.getInstance().getLogger().e("item.gat 写入失败!");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean save() {
//        System.out.println("item save");
        AppData.getInstance().getLogger().v("item save");
        boolean value = false;
        if (saveLua() && saveBin()) {
            value = true;
        }
        return value;
    }
}
