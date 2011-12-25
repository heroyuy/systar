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
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Skill;
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
public class SkillDao extends Dao<Skill> {

    /**
     *
     */
    @Override
    public void load() {
//        System.out.println("skill load");
        AppData.getInstance().getLogger().v("skill load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
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
                skill.limit = dis.readInt();
                skill.userAniIndex = dis.readInt();
                skill.targetAniIndex = dis.readInt();
                skill.menuUseSound = dis.readUTF();
                skill.eventIndex = dis.readInt();
                int costN = dis.readInt();
                for (int j = 0; j < costN; j++) {
                    Cost cost = new Cost();
                    cost.costType = dis.readInt();
                    cost.costName = dis.readUTF();
                    cost.costValue = dis.readInt();
                    skill.costs.add(cost);
                }
                int effectN = dis.readInt();
                for (int j = 0; j < effectN; j++) {
                    Effect effect = new Effect();
                    effect.effectType = dis.readInt();
                    effect.effectName = dis.readUTF();
                    effect.effectValue = dis.readInt();
                    skill.effects.add(effect);
                }
                int factorN = dis.readInt();
                for (int j = 0; j < factorN; j++) {
                    Factor factor = new Factor();
                    factor.factorType = dis.readInt();
                    factor.factorName = dis.readUTF();
                    factor.factorValue = dis.readInt();
                    skill.factors.add(factor);
                }
                int attrN = dis.readInt();
                for (int j = 0; j < attrN; j++) {
                    Attribute attr = new Attribute();
                    attr.id = dis.readInt();
                    attr.value = dis.readInt();
                    skill.attributes.add(attr);
                }
                int statusN = dis.readInt();
                for (int j = 0; j < statusN; j++) {
                    Status status = (Status) AppData.getInstance().getCurProject().getDataManager().getModel(Model.STATUS, dis.readInt());
                    status.value = dis.readInt();
                    skill.status.add(status);
                }
                AppData.getInstance().getCurProject().getDataManager().saveModel(Model.SKILL, skill);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
//            System.out.println("没有可加载的Skill");
            AppData.getInstance().getLogger().e("没有可加载的Skill");
        }
    }

    private boolean saveBin() {
        Skill[] skills = this.getModels(Skill.class);
        Skill skill = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            LuaTable lt = new LuaTable();
            skill = skills[i];
            lt.addNode("\n");
            lt.addNode("index", skill.getIndex());
            lt.addNode("\n");
            lt.addNode("name", skill.name);
            lt.addNode("\n");
            lt.addNode("intro", skill.intro);
            lt.addNode("\n");
            if (skill.icon == null || skill.icon.equals("")) {
                lt.addNode("icon", "nil");
            } else {
                lt.addNode("icon", "/image/icon/skill/" + skill.icon);
            }
            lt.addNode("\n");
            lt.addNode("target", skill.target);
            lt.addNode("\n");
            lt.addNode("limit", skill.limit);
            lt.addNode("\n");
            lt.addNode("lev", skill.lev);
            lt.addNode("\n");
            lt.addNode("useAniIndex", skill.userAniIndex);
            lt.addNode("\n");
            lt.addNode("targetAniIndex", skill.targetAniIndex);
            lt.addNode("\n");
            if (skill.menuUseSound == null || skill.menuUseSound.equals("")) {
                lt.addNode("sound", "nil");
            } else {
                lt.addNode("sound", "/audio/sound/" + skill.menuUseSound);
            }
            lt.addNode("\n");
            lt.addNode("eventIndex", skill.eventIndex);
            lt.addNode("\n");
            LuaTable efs = new LuaTable();
            if (!skill.effects.isEmpty()) {
                efs.addNode("\n");
            }
            for (int j = 0; j < skill.effects.size(); j++) {
                LuaTable ef = new LuaTable();
                ef.addNode("index", skill.effects.get(j).effectType);
                ef.addNode("name", skill.effects.get(j).effectName);
                ef.addNode("value", skill.effects.get(j).effectValue);
                efs.addNode("[" + skill.effects.get(j).effectType + "]", ef);
                if (j != skill.effects.size() - 1) {
                    efs.addNode("\n");
                }
            }
            lt.addNode("effects", efs);
            lt.addNode("\n");
            LuaTable cos = new LuaTable();
            if (!skill.costs.isEmpty()) {
                cos.addNode("\n");
            }
            for (int j = 0; j < skill.costs.size(); j++) {
                LuaTable co = new LuaTable();
                co.addNode("index", skill.costs.get(j).costType);
                co.addNode("name", skill.costs.get(j).costName);
                co.addNode("value", skill.costs.get(j).costValue);
                cos.addNode("[" + skill.costs.get(j).costType + "]", co);
                if (j != skill.costs.size() - 1) {
                    cos.addNode("\n");
                }
            }
            lt.addNode("costs", cos);
            lt.addNode("\n");
            LuaTable fas = new LuaTable();
            if (!skill.factors.isEmpty()) {
                fas.addNode("\n");
            }
            for (int j = 0; j < skill.factors.size(); j++) {
                LuaTable fa = new LuaTable();
                fa.addNode("index", skill.factors.get(j).factorType);
                fa.addNode("name", skill.factors.get(j).factorName);
                fa.addNode("value", skill.factors.get(j).factorValue);
                fas.addNode("[" + skill.factors.get(j).factorType + "]", fa);
                if (j != skill.factors.size() - 1) {
                    fas.addNode("\n");
                }
            }
            lt.addNode("factors", fas);
            lt.addNode("\n");
            LuaTable attrs = new LuaTable();
            if (!skill.attributes.isEmpty()) {
                attrs.addNode("\n");
            }
            for (int j = 0; j < skill.attributes.size(); j++) {
                LuaTable attr = new LuaTable();
                attr.addNode("index", skill.attributes.get(j).id);
                attr.addNode("value", skill.attributes.get(j).value);
                attrs.addNode("[" + skill.attributes.get(j).id + "]", attr);
                if (j != skill.attributes.size() - 1) {
                    attrs.addNode("\n");
                }
            }
            lt.addNode("attributes", attrs);
            lt.addNode("\n");
            LuaTable status = new LuaTable();
            if (!skill.status.isEmpty()) {
                status.addNode("\n");
            }
            for (int j = 0; j < skill.status.size(); j++) {
                LuaTable statu = new LuaTable();
                statu.addNode("index", skill.status.get(j).getIndex());
                statu.addNode("value", skill.status.get(j).value);
                status.addNode("[" + skill.status.get(j).getIndex() + "]", statu);
                if (j != skill.status.size() - 1) {
                    status.addNode("\n");
                }
            }
            lt.addNode("buffs", status);
            lts.addNode("[" + skill.getIndex() + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("globalDictionary.skills", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/skill.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("skill.gat 写入失败");
            AppData.getInstance().getLogger().e("skill.gat 写入失败");
            return false;
        }
    }

    private boolean saveLua() {
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
                dos.writeInt(skill.limit);
                dos.writeInt(skill.userAniIndex);
                dos.writeInt(skill.targetAniIndex);
                dos.writeUTF(skill.menuUseSound);
                dos.writeInt(skill.eventIndex);
                dos.writeInt(skill.costs.size());
                for (int j = 0; j < skill.costs.size(); j++) {
                    dos.writeInt(skill.costs.get(j).costType);
                    dos.writeUTF(skill.costs.get(j).costName);
                    dos.writeInt(skill.costs.get(j).costValue);
                }
                dos.writeInt(skill.effects.size());
                for (int j = 0; j < skill.effects.size(); j++) {
                    dos.writeInt(skill.effects.get(j).effectType);
                    dos.writeUTF(skill.effects.get(j).effectName);
                    dos.writeInt(skill.effects.get(j).effectValue);
                }
                dos.writeInt(skill.factors.size());
                for (int j = 0; j < skill.factors.size(); j++) {
                    dos.writeInt(skill.factors.get(j).factorType);
                    dos.writeUTF(skill.factors.get(j).factorName);
                    dos.writeInt(skill.factors.get(j).factorValue);
                }
                dos.writeInt(skill.attributes.size());
                for (int j = 0; j < skill.attributes.size(); j++) {
                    dos.writeInt(skill.attributes.get(j).id);
                    dos.writeInt(skill.attributes.get(j).value);
                }
                dos.writeInt(skill.status.size());
                for (int j = 0; j < skill.status.size(); j++) {
                    dos.writeInt(skill.status.get(j).getIndex());
                    dos.writeInt(skill.status.get(j).value);
                }
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("skill.gat 写入失败!");
            AppData.getInstance().getLogger().e("skill.gat 写入失败");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean save() {
//        System.out.println("skill save");
        AppData.getInstance().getLogger().v("skill save");
        boolean value = false;
        if (saveLua() && saveBin()) {
            value = true;
        }
        return value;
    }
}
