/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Equip;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Skill;
import com.soyomaker.data.model.Status;
import com.soyomaker.data.model.Vocation;
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
public class VocationDao extends Dao<Vocation> {

    @Override
    public void load() {
//        System.out.println("vocation load");
        AppData.getInstance().getLogger().v("vocation load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/vocation.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int vocationSum = dis.readInt();
            Vocation vocation = null;
            for (int i = 0; i < vocationSum; i++) {
                vocation = new Vocation();
                vocation.setIndex(dis.readInt());
                vocation.name = dis.readUTF();
                int equipN = dis.readInt();
                for (int j = 0; j < equipN; j++) {
                    Equip equip = (Equip) AppData.getInstance().getCurProject().getDataManager().getModel(Model.EQUIP, dis.readInt());
                    vocation.equips.add(equip);
                }
                int itemN = dis.readInt();
                for (int j = 0; j < itemN; j++) {
                    Item item = (Item) AppData.getInstance().getCurProject().getDataManager().getModel(Model.ITEM, dis.readInt());
                    vocation.items.add(item);
                }
                int skillN = dis.readInt();
                for (int j = 0; j < skillN; j++) {
                    Skill skill = (Skill) AppData.getInstance().getCurProject().getDataManager().getModel(Model.SKILL, dis.readInt());
                    vocation.skills.add(skill);
                }
                int attrN = dis.readInt();
                for (int j = 0; j < attrN; j++) {
                    Attribute attr = new Attribute();
                    attr.id = dis.readInt();
                    attr.value = dis.readInt();
                    vocation.attrs.add(attr);
                }
                int statusN = dis.readInt();
                for (int j = 0; j < statusN; j++) {
                    Status status = (Status) AppData.getInstance().getCurProject().getDataManager().getModel(Model.STATUS, dis.readInt());
                    status.value = dis.readInt();
                    vocation.status.add(status);
                }
                AppData.getInstance().getCurProject().getDataManager().saveModel(Model.VOCATION, vocation);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
//            System.out.println("没有可加载的Vocation");
            AppData.getInstance().getLogger().e("没有可加载的Vocation");
        }
    }

    private boolean saveLua() {
        Vocation[] vocations = this.getModels(Vocation.class);
        Vocation vocation = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            vocation = vocations[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", vocation.getIndex());
            lt.addNode("\n");
            lt.addNode("name", vocation.name);
            lt.addNode("\n");
            LuaTable eqs = new LuaTable();
            for (int j = 0; j < vocation.equips.size(); j++) {
                eqs.addNode(vocation.equips.get(j).getIndex());
            }
            lt.addNode("equips", eqs);
            lt.addNode("\n");
            LuaTable its = new LuaTable();
            for (int j = 0; j < vocation.items.size(); j++) {
                its.addNode(vocation.items.get(j).getIndex());
            }
            lt.addNode("items", its);
            lt.addNode("\n");
            LuaTable sks = new LuaTable();
            for (int j = 0; j < vocation.skills.size(); j++) {
                sks.addNode(vocation.skills.get(j).getIndex());
            }
            lt.addNode("skills", sks);
            lt.addNode("\n");
            LuaTable attrs = new LuaTable();
            if (!vocation.attrs.isEmpty()) {
                attrs.addNode("\n");
            }
            for (int j = 0; j < vocation.attrs.size(); j++) {
                LuaTable attr = new LuaTable();
                attr.addNode("index", vocation.attrs.get(j).id);
                attr.addNode("value", vocation.attrs.get(j).value);
                attrs.addNode("[" + vocation.attrs.get(j).id + "]", attr);
                if (j != vocation.attrs.size() - 1) {
                    attrs.addNode("\n");
                }
            }
            lt.addNode("attributes", attrs);
            lt.addNode("\n");
            LuaTable status = new LuaTable();
            if (!vocation.status.isEmpty()) {
                status.addNode("\n");
            }
            for (int j = 0; j < vocation.status.size(); j++) {
                LuaTable statu = new LuaTable();
                statu.addNode("index", vocation.status.get(j).getIndex());
                statu.addNode("value", vocation.status.get(j).value);
                status.addNode("[" + vocation.status.get(j).getIndex() + "]", statu);
                if (j != vocation.status.size() - 1) {
                    status.addNode("\n");
                }
            }
            lt.addNode("buffs", status);
            lts.addNode("[" + vocation.getIndex() + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("globalDictionary.vocations", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/vocation.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("vocation.gat 写入失败");
            AppData.getInstance().getLogger().e("vocation.gat 写入失败");
            return false;
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/vocation.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Vocation[] vocations = this.getModels(Vocation.class);
            Vocation vocation = null;
            for (int i = 0; i < size(); i++) {
                vocation = vocations[i];
                dos.writeInt(vocation.getIndex());
                dos.writeUTF(vocation.name);
                dos.writeInt(vocation.equips.size());
                for (int j = 0; j < vocation.equips.size(); j++) {
                    dos.writeInt(vocation.equips.get(j).getIndex());
                }
                dos.writeInt(vocation.items.size());
                for (int j = 0; j < vocation.items.size(); j++) {
                    dos.writeInt(vocation.items.get(j).getIndex());
                }
                dos.writeInt(vocation.skills.size());
                for (int j = 0; j < vocation.skills.size(); j++) {
                    dos.writeInt(vocation.skills.get(j).getIndex());
                }
                dos.writeInt(vocation.attrs.size());
                for (int j = 0; j < vocation.attrs.size(); j++) {
                    dos.writeInt(vocation.attrs.get(j).id);
                    dos.writeInt(vocation.attrs.get(j).value);
                }
                dos.writeInt(vocation.status.size());
                for (int j = 0; j < vocation.status.size(); j++) {
                    dos.writeInt(vocation.status.get(j).getIndex());
                    dos.writeInt(vocation.status.get(j).value);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("vocation.gat 写入失败!");
            AppData.getInstance().getLogger().e("vocation.gat 写入失败");
            return false;
        }
    }

    @Override
    public boolean save() {
//        System.out.println("vocation save");
        AppData.getInstance().getLogger().v("vocation save");
        boolean value = false;
        if (saveLua() && saveBin()) {
            value = true;
        }
        return value;
    }
}
