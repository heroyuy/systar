/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Equip;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Skill;
import com.soyomaker.data.model.Vocation;
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
public class VocationDao extends Dao<Vocation> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    @Override
    public void load() throws FileNotFoundException, IOException {
        printer.v("vocation load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/vocation.gat");
        fis = new FileInputStream(f);
        dis = new DataInputStream(fis);
        int vocationSum = dis.readInt();
        Vocation vocation = null;
        for (int i = 0; i < vocationSum; i++) {
            vocation = new Vocation();
            vocation.setIndex(dis.readInt());
            vocation.name = dis.readUTF();
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
            AppData.getInstance().getCurProject().getDataManager().saveModel(Model.VOCATION, vocation);
        }
        dis.close();
        fis.close();
        f = null;
    }

    private boolean saveLua() {
        Vocation[] vocations = this.getModels(Vocation.class);
        Vocation vocation = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            vocation = vocations[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("id", Configuration.Prefix.VOCATION_MASK + vocation.getIndex() + 1);
            lt.addNode("\n");
            lt.addNode("name", vocation.name);
            lt.addNode("\n");
            LuaTable its = new LuaTable();
            for (int j = 0; j < vocation.items.size(); j++) {
                its.addNode(Configuration.Prefix.ITEM_MASK + vocation.items.get(j).getIndex() + 1);
            }
            lt.addNode("itemList", its);
            lt.addNode("\n");
            LuaTable sks = new LuaTable();
            for (int j = 0; j < vocation.skills.size(); j++) {
                sks.addNode(Configuration.Prefix.SKILL_MASK + vocation.skills.get(j).getIndex() + 1);
            }
            lt.addNode("skillList", sks);
            lt.addNode("\n");
            lts.addNode("[" + (Configuration.Prefix.VOCATION_MASK + vocation.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("Dictionary.vocations", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/vocation.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("vocation.gat 写入失败");
            printer.e("vocation.gat 写入失败");
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
                dos.writeInt(vocation.items.size());
                for (int j = 0; j < vocation.items.size(); j++) {
                    dos.writeInt(vocation.items.get(j).getIndex());
                }
                dos.writeInt(vocation.skills.size());
                for (int j = 0; j < vocation.skills.size(); j++) {
                    dos.writeInt(vocation.skills.get(j).getIndex());
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("vocation.gat 写入失败!");
            printer.e("vocation.gat 写入失败");
            return false;
        }
    }

    @Override
    public boolean save() {
//        System.out.println("vocation save");
        printer.v("vocation save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("vocation save");
        boolean value = false;
        if (saveBin()) {
            value = true;
        }
        return value;
    }
}
