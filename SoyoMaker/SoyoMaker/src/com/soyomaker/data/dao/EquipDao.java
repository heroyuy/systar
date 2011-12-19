/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Effect;
import com.soyomaker.data.model.Equip;
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
public class EquipDao extends Dao<Equip> {

    /**
     *
     */
    @Override
    public void load() {
//        System.out.println("equip load");
        AppData.getInstance().getLogger().v("equip load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/equip.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int equipSum = dis.readInt();
            Equip equip = null;
            for (int i = 0; i < equipSum; i++) {
                equip = new Equip();
                equip.setIndex(dis.readInt());
                equip.name = dis.readUTF();
                equip.intro = dis.readUTF();
                equip.icon = dis.readUTF();
                equip.lev = dis.readInt();
                equip.equipType = dis.readInt();
                equip.userAniIndex = dis.readInt();
                equip.targetAniIndex = dis.readInt();
                equip.price = dis.readInt();
                int effectN = dis.readInt();
                for (int j = 0; j < effectN; j++) {
                    Effect effect = new Effect();
                    effect.effectType = dis.readInt();
                    effect.effectName = dis.readUTF();
                    effect.effectValue = dis.readInt();
                    equip.effects.add(effect);
                }
                int attrN = dis.readInt();
                for (int j = 0; j < attrN; j++) {
                    Attribute attr = new Attribute();
                    attr.id = dis.readInt();
                    attr.value = dis.readInt();
                    equip.attributes.add(attr);
                }
                int statusN = dis.readInt();
                for (int j = 0; j < statusN; j++) {
                    int id = dis.readInt();
                    Status status = (Status) AppData.getInstance().getCurProject().getDataManager().getModel(Model.STATUS, id);
                    status.value = dis.readInt();
                    equip.status.add(status);
                }
                AppData.getInstance().getCurProject().getDataManager().saveModel(Model.EQUIP, equip);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
//            System.out.println("没有可加载的Equip");
            AppData.getInstance().getLogger().e("没有可加载的Equip");
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/equip.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Equip[] equips = this.getModels(Equip.class);
            Equip equip = null;
            for (int i = 0; i < size(); i++) {
                equip = equips[i];
                dos.writeInt(equip.getIndex());
                dos.writeUTF(equip.name);
                dos.writeUTF(equip.intro);
                dos.writeUTF(equip.icon);
                dos.writeInt(equip.lev);
                dos.writeInt(equip.equipType);
                dos.writeInt(equip.userAniIndex);
                dos.writeInt(equip.targetAniIndex);
                dos.writeInt(equip.price);
                dos.writeInt(equip.effects.size());
                for (int j = 0; j < equip.effects.size(); j++) {
                    dos.writeInt(equip.effects.get(j).effectType);
                    dos.writeUTF(equip.effects.get(j).effectName);
                    dos.writeInt(equip.effects.get(j).effectValue);
                }
                dos.writeInt(equip.attributes.size());
                for (int j = 0; j < equip.attributes.size(); j++) {
                    dos.writeInt(equip.attributes.get(j).id);
                    dos.writeInt(equip.attributes.get(j).value);
                }
                dos.writeInt(equip.status.size());
                for (int j = 0; j < equip.status.size(); j++) {
                    dos.writeInt(equip.status.get(j).getIndex());
                    dos.writeInt(equip.status.get(j).value);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("equip.gat 写入失败!");
            AppData.getInstance().getLogger().e("equip.gat 写入失败!");
            return false;
        }
    }

    private boolean saveLua() {
        Equip[] equips = this.getModels(Equip.class);
        Equip equip = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            equip = equips[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", equip.getIndex());
            lt.addNode("\n");
            lt.addNode("name", equip.name);
            lt.addNode("\n");
            lt.addNode("intro", equip.intro);
            lt.addNode("\n");
            if (equip.icon == null || equip.icon.equals("")) {
                lt.addNode("icon", "nil");
            } else {
                lt.addNode("icon", "/image/icon/equip/" + equip.icon);
            }
            lt.addNode("\n");
            lt.addNode("kind", equip.equipType);
            lt.addNode("\n");
            lt.addNode("lev", equip.lev);
            lt.addNode("\n");
            lt.addNode("useAniIndex", equip.userAniIndex);
            lt.addNode("\n");
            lt.addNode("targetAniIndex", equip.targetAniIndex);
            lt.addNode("\n");
            lt.addNode("price", equip.price);
            lt.addNode("\n");
            LuaTable efs = new LuaTable();
            if (!equip.effects.isEmpty()) {
                efs.addNode("\n");
            }
            for (int j = 0; j < equip.effects.size(); j++) {
                LuaTable ef = new LuaTable();
                ef.addNode("index", equip.effects.get(j).effectType);
                ef.addNode("name", equip.effects.get(j).effectName);
                ef.addNode("value", equip.effects.get(j).effectValue);
                efs.addNode(ef);
                if (j != equip.effects.size() - 1) {
                    efs.addNode("\n");
                }
            }
            lt.addNode("effects", efs);
            lt.addNode("\n");
            LuaTable attrs = new LuaTable();
            if (!equip.attributes.isEmpty()) {
                attrs.addNode("\n");
            }
            for (int j = 0; j < equip.attributes.size(); j++) {
                LuaTable attr = new LuaTable();
                attr.addNode("index", equip.attributes.get(j).id);
                attr.addNode("value", equip.attributes.get(j).value);
                attrs.addNode(attr);
                if (j != equip.attributes.size() - 1) {
                    attrs.addNode("\n");
                }
            }
            lt.addNode("attributes", attrs);
            lt.addNode("\n");
            LuaTable status = new LuaTable();
            if (!equip.status.isEmpty()) {
                status.addNode("\n");
            }
            for (int j = 0; j < equip.status.size(); j++) {
                LuaTable statu = new LuaTable();
                statu.addNode("index", equip.status.get(j).getIndex());
                statu.addNode("value", equip.status.get(j).value);
                status.addNode(statu);
                if (j != equip.status.size() - 1) {
                    status.addNode("\n");
                }
            }
            lt.addNode("status", status);
            lts.addNode("[" + equip.getIndex() + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("globalDictionary.equips", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/equip.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("equip.gat 写入失败");
            AppData.getInstance().getLogger().e("equip.gat 写入失败!");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean save() {
//        System.out.println("equip save");
        AppData.getInstance().getLogger().v("equip save");
        boolean value = false;
        if (saveLua() && saveBin()) {
            value = true;
        }
        return value;
    }
}
