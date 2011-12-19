/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Condition;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Parameter;
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
public class StatusDao extends Dao<Status> {

    @Override
    public void load() {
//        System.out.println("status load");
        AppData.getInstance().getLogger().v("status load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/status.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int statusSum = dis.readInt();
            Status status = null;
            for (int i = 0; i < statusSum; i++) {
                status = new Status();
                status.setIndex(dis.readInt());
                status.type = dis.readInt();
                status.name = dis.readUTF();
                status.description = dis.readUTF();
                status.icon = dis.readUTF();
                status.lev = dis.readInt();
                status.aniIndex = dis.readInt();
                status.lastType = dis.readInt();
                status.lastValue = dis.readInt();
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
                int attrN = dis.readInt();
                for (int j = 0; j < attrN; j++) {
                    Attribute attr = new Attribute();
                    attr.id = dis.readInt();
                    attr.value = dis.readInt();
                    status.attrs.add(attr);
                }
                int statusN = dis.readInt();
                for (int j = 0; j < statusN; j++) {
                    Status statu = new Status();//不直接用datamanager里的，是由于可能为空
                    statu.setIndex(dis.readInt());
                    statu.value = dis.readInt();
                    status.status.add(statu);
                }
                AppData.getInstance().getCurProject().getDataManager().saveModel(Model.STATUS, status);
            }
            //更新子状态
            for (int i = 0; i < AppData.getInstance().getCurProject().getDataManager().getModels(Model.STATUS).length; i++) {
                Status statu = (Status) AppData.getInstance().getCurProject().getDataManager().getModels(Model.STATUS)[i];
                for (int j = 0; j < statu.status.size(); j++) {
                    int id = statu.status.get(j).getIndex();
                    int value = statu.status.get(j).value;
                    Status sta = (Status) AppData.getInstance().getCurProject().getDataManager().getModel(Model.STATUS, id);
                    sta.value = value;
                    statu.status.set(j, sta);
                }
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
//            System.out.println("没有可加载的Status");
            AppData.getInstance().getLogger().e("没有可加载的Status");
        }
    }

    private boolean saveLua() {
        Status[] status = this.getModels(Status.class);
        Status statu = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            statu = status[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", statu.getIndex());
            lt.addNode("\n");
            lt.addNode("type", statu.type);
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
            lt.addNode("aniIndex", statu.aniIndex);
            lt.addNode("\n");
            lt.addNode("lastType", statu.lastType);
            lt.addNode("\n");
            lt.addNode("lastValue", statu.lastValue);
            lt.addNode("\n");
            LuaTable pas = new LuaTable();
            if (!statu.paras.isEmpty()) {
                pas.addNode("\n");
            }
            for (int j = 0; j < statu.paras.size(); j++) {
                LuaTable rc = new LuaTable();
                rc.addNode("paramType", statu.paras.get(j).type);
                rc.addNode("paramName", statu.paras.get(j).name);
                rc.addNode("paramRule", statu.paras.get(j).rule);
                rc.addNode("paramValue", statu.paras.get(j).value);
                pas.addNode(rc);
                if (j != statu.paras.size() - 1) {
                    pas.addNode("\n");
                }
            }
            lt.addNode("parameters", pas);
            lt.addNode("\n");
            LuaTable rcs = new LuaTable();
            if (!statu.conds.isEmpty()) {
                rcs.addNode("\n");
            }
            for (int j = 0; j < statu.conds.size(); j++) {
                LuaTable rc = new LuaTable();
                rc.addNode("conditionType", statu.conds.get(j).conditionType);
                rc.addNode("conditionName", statu.conds.get(j).conditionName);
                rc.addNode("param", statu.conds.get(j).para);
                rcs.addNode(rc);
                if (j != statu.conds.size() - 1) {
                    rcs.addNode("\n");
                }
            }
            lt.addNode("conditions", rcs);
            lt.addNode("\n");
            LuaTable attrs = new LuaTable();
            if (!statu.attrs.isEmpty()) {
                attrs.addNode("\n");
            }
            for (int j = 0; j < statu.attrs.size(); j++) {
                LuaTable attr = new LuaTable();
                attr.addNode("index", statu.attrs.get(j).id);
                attr.addNode("value", statu.attrs.get(j).value);
                attrs.addNode(attr);
                if (j != statu.attrs.size() - 1) {
                    attrs.addNode("\n");
                }
            }
            lt.addNode("attributes", attrs);
            lt.addNode("\n");
            LuaTable sts = new LuaTable();
            if (!statu.status.isEmpty()) {
                sts.addNode("\n");
            }
            for (int j = 0; j < statu.status.size(); j++) {
                LuaTable st = new LuaTable();
                st.addNode("index", statu.status.get(j).getIndex());
                st.addNode("value", statu.status.get(j).value);
                sts.addNode(st);
                if (j != statu.status.size() - 1) {
                    sts.addNode("\n");
                }
            }
            lt.addNode("status", sts);
            lts.addNode("[" + statu.getIndex() + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("globalDictionary.statuses", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/status.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("status.gat 写入失败");
            AppData.getInstance().getLogger().e("status.gat 写入失败");
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
            Status[] status = this.getModels(Status.class);
            Status statu = null;
            for (int i = 0; i < size(); i++) {
                statu = status[i];
                dos.writeInt(statu.getIndex());
                dos.writeInt(statu.type);
                dos.writeUTF(statu.name);
                dos.writeUTF(statu.description);
                dos.writeUTF(statu.icon);
                dos.writeInt(statu.lev);
                dos.writeInt(statu.aniIndex);
                dos.writeInt(statu.lastType);
                dos.writeInt(statu.lastValue);
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
                dos.writeInt(statu.attrs.size());
                for (int j = 0; j < statu.attrs.size(); j++) {
                    dos.writeInt(statu.attrs.get(j).id);
                    dos.writeInt(statu.attrs.get(j).value);
                }
                dos.writeInt(statu.status.size());
                for (int j = 0; j < statu.status.size(); j++) {
                    dos.writeInt(statu.status.get(j).getIndex());
                    dos.writeInt(statu.status.get(j).value);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("status.gat 写入失败!");
            AppData.getInstance().getLogger().e("status.gat 写入失败");
            return false;
        }
    }

    @Override
    public boolean save() {
//        System.out.println("status save");
        AppData.getInstance().getLogger().v("status save");
        boolean value = false;
        if (saveLua() && saveBin()) {
            value = true;
        }
        return value;
    }
}
