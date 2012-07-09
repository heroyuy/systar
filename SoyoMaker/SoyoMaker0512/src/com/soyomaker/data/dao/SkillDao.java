/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Cost;
import com.soyomaker.data.model.Effect;
import com.soyomaker.data.model.Factor;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Skill;
import com.soyomaker.data.model.Buff;
import com.soyomaker.data.model.Skill.BuffInfo;
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
public class SkillDao extends Dao<Skill> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public void load() throws FileNotFoundException, IOException {
        printer.v("skill load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;

        f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/skill.gat");
        fis = new FileInputStream(f);
        dis = new DataInputStream(fis);
        int skillSum = dis.readInt();
        Skill skill = null;
        for (int i = 0; i < skillSum; i++) {
            skill = new Skill();
            skill.setIndex(dis.readInt());
            skill.name = dis.readUTF();
            skill.intro = dis.readUTF();
            skill.icon = dis.readUTF();
            skill.lev = dis.readInt();
            skill.target = dis.readInt();
            skill.type = dis.readInt();
            skill.userAniIndex = dis.readInt();
            skill.targetAniIndex = dis.readInt();
            skill.attackDistance = dis.readInt();
            skill.attributeId = dis.readInt();
            skill.eventIndex = dis.readInt();
            int effectN = dis.readInt();
            for (int j = 0; j < effectN; j++) {
                Effect effect = new Effect();
                effect.effectType = dis.readInt();
                effect.effectName = dis.readUTF();
                effect.effectValue = dis.readInt();
                skill.effects.add(effect);
            }
            int statusN = dis.readInt();
            for (int j = 0; j < statusN; j++) {
                Buff status = (Buff) AppData.getInstance().getCurProject().getDataManager().getModel(Model.STATUS, dis.readInt());
                int rate = dis.readInt();
                BuffInfo buffInfo = new BuffInfo();
                buffInfo.buff = status;
                buffInfo.rate = rate;
                skill.status.add(buffInfo);
            }
            AppData.getInstance().getCurProject().getDataManager().saveModel(Model.SKILL, skill);
        }
        dis.close();
        fis.close();
        f = null;
    }

    private boolean saveLua() {
        Skill[] skills = this.getModels(Skill.class);
        Skill skill = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            LuaTable lt = new LuaTable();
            skill = skills[i];
            lt.addNode("\n");
            lt.addNode("index", Configuration.Prefix.SKILL_MASK + skill.getIndex() + 1);
            lt.addNode("\n");
            lt.addNode("name", skill.name);
            lt.addNode("\n");
            lt.addNode("desc", skill.intro);
            lt.addNode("\n");
            if (skill.icon == null || skill.icon.equals("")) {
                lt.addNode("icon", "nil");
            } else {
                lt.addNode("icon", "/image/icon/skill/" + skill.icon);
            }
            lt.addNode("\n");
            lt.addNode("targetType", skill.target);
            lt.addNode("\n");
            lt.addNode("type", skill.type);
            lt.addNode("\n");
            lt.addNode("lev", skill.lev);
            lt.addNode("\n");
            if (skill.userAniIndex == -1) {
                lt.addNode("useAniIndex", -1);
            } else {
                lt.addNode("useAniIndex", Configuration.Prefix.ANIMATION_MASK + skill.userAniIndex + 1);
            }
            lt.addNode("\n");
            if (skill.targetAniIndex == -1) {
                lt.addNode("targetAniIndex", -1);
            } else {
                lt.addNode("targetAniIndex", Configuration.Prefix.ANIMATION_MASK + skill.targetAniIndex + 1);
            }
            lt.addNode("\n");
            lt.addNode("eventIndex", skill.eventIndex);
            lt.addNode("\n");
            lt.addNode("attackDistance", skill.attackDistance);
            lt.addNode("\n");
            lt.addNode("attributeId", Configuration.Prefix.ATTRIBUTE_MASK + skill.attributeId + 1);
            lt.addNode("\n");
            LuaTable efs = new LuaTable();
            if (!skill.effects.isEmpty()) {
                efs.addNode("\n");
            }
            for (int j = 0; j < skill.effects.size(); j++) {
                LuaTable ef = new LuaTable();
                ef.addNode("index", skill.effects.get(j).effectType + 1);
                ef.addNode("name", skill.effects.get(j).effectName);
                ef.addNode("value", skill.effects.get(j).effectValue);
                efs.addNode("[" + (skill.effects.get(j).effectType + 1) + "]", ef);
                if (j != skill.effects.size() - 1) {
                    efs.addNode("\n");
                }
            }
            lt.addNode("datas", efs);
            lt.addNode("\n");
            LuaTable status = new LuaTable();
            if (!skill.status.isEmpty()) {
                status.addNode("\n");
            }
            for (int j = 0; j < skill.status.size(); j++) {
                LuaTable statu = new LuaTable();
                statu.addNode("id", Configuration.Prefix.STATUS_MASK + skill.status.get(j).buff.getIndex() + 1);
                statu.addNode("value", skill.status.get(j).rate);
                status.addNode("[" + (Configuration.Prefix.STATUS_MASK + skill.status.get(j).buff.getIndex() + 1) + "]", statu);
                if (j != skill.status.size() - 1) {
                    status.addNode("\n");
                }
            }
            lt.addNode("buffs", status);
            lts.addNode("[" + (Configuration.Prefix.SKILL_MASK + skill.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("Dictionary.skills", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/skill.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("skill.gat 写入失败");
            printer.e("skill.gat 写入失败");
            return false;
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/skill.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Skill[] skills = this.getModels(Skill.class);
            Skill skill = null;
            for (int i = 0; i < size(); i++) {
                skill = skills[i];
                dos.writeInt(skill.getIndex());
                dos.writeUTF(skill.name);
                dos.writeUTF(skill.intro);
                dos.writeUTF(skill.icon);
                dos.writeInt(skill.lev);
                dos.writeInt(skill.target);
                dos.writeInt(skill.type);
                dos.writeInt(skill.userAniIndex);
                dos.writeInt(skill.targetAniIndex);
                dos.writeInt(skill.attackDistance);
                dos.writeInt(skill.attributeId);
                dos.writeInt(skill.eventIndex);
                dos.writeInt(skill.effects.size());
                for (int j = 0; j < skill.effects.size(); j++) {
                    dos.writeInt(skill.effects.get(j).effectType);
                    dos.writeUTF(skill.effects.get(j).effectName);
                    dos.writeInt(skill.effects.get(j).effectValue);
                }
                dos.writeInt(skill.status.size());
                for (int j = 0; j < skill.status.size(); j++) {
                    dos.writeInt(skill.status.get(j).buff.getIndex());
                    dos.writeInt(skill.status.get(j).rate);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("skill.gat 写入失败!");
            printer.e("skill.gat 写入失败");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean save() {
//        System.out.println("skill save");
        printer.v("skill save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("skill save");
        boolean value = false;
        if (saveBin()) {
            value = true;
        }
        return value;
    }
}
