/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Effect;
import com.soyomaker.data.model.Equip;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Status;
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
public class EquipDao extends Dao<Equip> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    /**
     *
     * @throws FileNotFoundException 
     * @throws IOException
     */
    @Override
    public void load() throws FileNotFoundException, IOException {
        printer.v("equip load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;

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
            printer.e("equip.gat 写入失败!");
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
            lt.addNode("index", Configuration.Prefix.EQUIP_MASK + equip.getIndex() + 1);
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
            if (equip.userAniIndex == -1) {
                lt.addNode("useAniIndex", -1);
            } else {
                lt.addNode("useAniIndex", Configuration.Prefix.ANIMATION_MASK + equip.userAniIndex);
            }
            lt.addNode("\n");
            if (equip.targetAniIndex == -1) {
                lt.addNode("targetAniIndex", -1);
            } else {
                lt.addNode("targetAniIndex", Configuration.Prefix.ANIMATION_MASK + equip.targetAniIndex);
            }
            lt.addNode("\n");
            lt.addNode("price", equip.price);
            lt.addNode("\n");
            LuaTable efs = new LuaTable();
            if (!equip.effects.isEmpty()) {
                efs.addNode("\n");
            }
            for (int j = 0; j < equip.effects.size(); j++) {
                LuaTable ef = new LuaTable();
                ef.addNode("index", equip.effects.get(j).effectType + 1);
                ef.addNode("name", equip.effects.get(j).effectName);
                ef.addNode("value", equip.effects.get(j).effectValue);
                efs.addNode("[" + (equip.effects.get(j).effectType + 1) + "]", ef);
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
                attr.addNode("index", Configuration.Prefix.ATTRIBUTE_MASK + equip.attributes.get(j).id + 1);
                attr.addNode("value", equip.attributes.get(j).value);
                attrs.addNode("[" + (Configuration.Prefix.ATTRIBUTE_MASK + equip.attributes.get(j).id + 1) + "]", attr);
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
                statu.addNode("index", Configuration.Prefix.STATUS_MASK + equip.status.get(j).getIndex() + 1);
                statu.addNode("value", equip.status.get(j).value);
                status.addNode("[" + (Configuration.Prefix.STATUS_MASK + equip.status.get(j).getIndex() + 1) + "]", statu);
                if (j != equip.status.size() - 1) {
                    status.addNode("\n");
                }
            }
            lt.addNode("buffs", status);
            lts.addNode("[" + (Configuration.Prefix.EQUIP_MASK + equip.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("Dictionary.equips", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/equip.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("equip.gat 写入失败");
            printer.e("equip.gat 写入失败!");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean save() {
//        System.out.println("equip save");
        printer.v("equip save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("equip save");
        boolean value = false;
        if (saveBin()) {
            value = true;
        }
        return value;
    }
}
