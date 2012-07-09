/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Condition;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Parameter;
import com.soyomaker.data.model.Buff;
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
public class BuffDao extends Dao<Buff> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    @Override
    public void load() throws FileNotFoundException, IOException {
        printer.v("status load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;

        f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/status.gat");
        fis = new FileInputStream(f);
        dis = new DataInputStream(fis);
        int statusSum = dis.readInt();
        Buff status = null;
        for (int i = 0; i < statusSum; i++) {
            status = new Buff();
            status.setIndex(dis.readInt());
            status.limitType = dis.readInt();
            status.name = dis.readUTF();
            status.description = dis.readUTF();
            status.icon = dis.readUTF();
            status.lev = dis.readInt();
            status.aniIndex = dis.readInt();
            status.type = dis.readInt();
            status.typeParam = dis.readInt();
            int paraN = dis.readInt();
            for (int j = 0; j < paraN; j++) {
                Parameter para = new Parameter();
                para.type = dis.readInt();
                para.name = dis.readUTF();
                para.rule = dis.readInt();
                para.value = dis.readInt();
                status.paras.add(para);
            }
            int conN = dis.readInt();
            for (int j = 0; j < conN; j++) {
                Condition cond = new Condition();
                cond.conditionType = dis.readInt();
                cond.conditionName = dis.readUTF();
                cond.para = dis.readInt();
                status.conds.add(cond);
            }
            AppData.getInstance().getCurProject().getDataManager().saveModel(Model.STATUS, status);
        }
        dis.close();
        fis.close();
        f = null;
    }

    private boolean saveLua() {
        Buff[] status = this.getModels(Buff.class);
        Buff statu = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            statu = status[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", Configuration.Prefix.STATUS_MASK + statu.getIndex() + 1);
            lt.addNode("\n");
            lt.addNode("limitType", statu.limitType);
            lt.addNode("\n");
            lt.addNode("name", statu.name);
            lt.addNode("\n");
            lt.addNode("desc", statu.description);
            lt.addNode("\n");
            if (statu.icon == null || statu.icon.equals("")) {
                lt.addNode("icon", "nil");
            } else {
                lt.addNode("icon", "/image/icon/status/" + statu.icon);
            }
            lt.addNode("\n");
            if (statu.aniIndex == -1) {
                lt.addNode("aniIndex", -1);
            } else {
                lt.addNode("aniIndex", Configuration.Prefix.ANIMATION_MASK + statu.aniIndex);
            }
            lt.addNode("\n");
            lt.addNode("type", statu.type);
            lt.addNode("\n");
            lt.addNode("typeParam", statu.typeParam);
            lt.addNode("\n");
            LuaTable pas = new LuaTable();
            if (!statu.paras.isEmpty()) {
                pas.addNode("\n");
            }
            for (int j = 0; j < statu.paras.size(); j++) {
                LuaTable rc = new LuaTable();
                rc.addNode("paramType", statu.paras.get(j).type + 1);
                rc.addNode("paramName", statu.paras.get(j).name);
                rc.addNode("paramRule", statu.paras.get(j).rule);
                rc.addNode("paramValue", statu.paras.get(j).value);
                pas.addNode("[" + (statu.paras.get(j).type + 1) + "]", rc);
                if (j != statu.paras.size() - 1) {
                    pas.addNode("\n");
                }
            }
            lt.addNode("datas", pas);
            lt.addNode("\n");
            lts.addNode("[" + (Configuration.Prefix.STATUS_MASK + statu.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("Dictionary.buffs", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/status.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("status.gat 写入失败");
            printer.e("status.gat 写入失败");
            return false;
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/status.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Buff[] status = this.getModels(Buff.class);
            Buff statu = null;
            for (int i = 0; i < size(); i++) {
                statu = status[i];
                dos.writeInt(statu.getIndex());
                dos.writeInt(statu.limitType);
                dos.writeUTF(statu.name);
                dos.writeUTF(statu.description);
                dos.writeUTF(statu.icon);
                dos.writeInt(statu.lev);
                dos.writeInt(statu.aniIndex);
                dos.writeInt(statu.type);
                dos.writeInt(statu.typeParam);
                dos.writeInt(statu.paras.size());
                for (int j = 0; j < statu.paras.size(); j++) {
                    Parameter para = statu.paras.get(j);
                    dos.writeInt(para.type);
                    dos.writeUTF(para.name);
                    dos.writeInt(para.rule);
                    dos.writeInt(para.value);
                }
                dos.writeInt(statu.conds.size());
                for (int j = 0; j < statu.conds.size(); j++) {
                    Condition cond = statu.conds.get(j);
                    dos.writeInt(cond.conditionType);
                    dos.writeUTF(cond.conditionName);
                    dos.writeInt(cond.para);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("status.gat 写入失败!");
            printer.e("status.gat 写入失败");
            return false;
        }
    }

    @Override
    public boolean save() {
//        System.out.println("status save");
        printer.v("status save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("status save");
        boolean value = false;
        if (saveBin()) {
            value = true;
        }
        return value;
    }
}
