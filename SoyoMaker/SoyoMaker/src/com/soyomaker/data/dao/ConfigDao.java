/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.data.model.Attribute;
import com.soyomaker.data.model.Config;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Player;
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
public class ConfigDao extends Dao<Config> {

    /**
     *
     */
    @Override
    public void load() {
//        System.out.println("config load");
        AppData.getInstance().getLogger().v("config load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        Config config = null;
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/config.gat");
            if (!f.exists()) {
                //为了兼容前面版本，以后会去掉
                f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/system.gat");
            }
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            config = new Config();
            config.term.name = dis.readUTF();
            config.term.help = dis.readUTF();
            config.term.about = dis.readUTF();
            config.term.money = dis.readUTF();
            int playerN = dis.readInt();
            for (int i = 0; i < playerN; i++) {
                config.system.initPlayers.add((Player) AppData.getInstance().getCurProject().getDataManager().getModel(Model.PLAYER, dis.readInt()));
            }
            int attrN = dis.readInt();
            for (int i = 0; i < attrN; i++) {
                Attribute attr = new Attribute();
                attr.id = dis.readInt();
                attr.name = dis.readUTF();
                attr.description = dis.readUTF();
                config.system.attributes.add(attr);
            }
            config.system.curMapIndex = dis.readInt();
            config.system.row = dis.readInt();
            config.system.col = dis.readInt();
            config.system.face = dis.readByte();
            config.term.hp = dis.readUTF();
            config.term.sp = dis.readUTF();
            config.term.atk = dis.readUTF();
            config.term.def = dis.readUTF();
            config.term.magicAtk = dis.readUTF();
            config.term.magicDef = dis.readUTF();
            config.term.stre = dis.readUTF();
            config.term.inte = dis.readUTF();
            config.term.agil = dis.readUTF();
            config.term.dext = dis.readUTF();
            config.term.body = dis.readUTF();
            config.term.luck = dis.readUTF();
            config.term.helm = dis.readUTF();
            config.term.armour = dis.readUTF();
            config.term.weapon = dis.readUTF();
            config.term.shield = dis.readUTF();
            config.term.boots = dis.readUTF();
            config.term.jewelry = dis.readUTF();
            config.term.item = dis.readUTF();
            config.term.equip = dis.readUTF();
            config.term.skill = dis.readUTF();
            config.system.skin = dis.readUTF();
            config.system.startAniIndex = dis.readInt();
            config.system.titleBackground = dis.readUTF();
            config.system.titleMusic = dis.readUTF();
            config.system.startBattleSound = dis.readUTF();
            config.system.battleMusic = dis.readUTF();
            config.system.winBattleSound = dis.readUTF();
            config.system.escapeSound = dis.readUTF();
            config.system.endAniIndex = dis.readInt();
            config.system.selectedSound = dis.readUTF();
            config.system.confirmSound = dis.readUTF();
            config.system.cancelSound = dis.readUTF();
            config.system.warnSound = dis.readUTF();
            config.system.equipSound = dis.readUTF();
            config.system.shopSound = dis.readUTF();
            config.system.readSound = dis.readUTF();
            config.system.saveSound = dis.readUTF();
            AppData.getInstance().getCurProject().getDataManager().saveModel(Model.CONFIG, config);
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
//            System.out.println("没有可加载的Config");
            AppData.getInstance().getLogger().e("没有可加载的Config");
        }
    }

    private boolean saveLua() {
        Config config = null;
        if (AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG).length <= 0) {
            config = new Config();
        } else {
            config = (Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
        }
        LuaTable lt = new LuaTable();
        lt.addNode("\n");
        lt.addNode("name", config.term.name);
        lt.addNode("\n");
        lt.addNode("help", config.term.help);
        lt.addNode("\n");
        lt.addNode("about", config.term.about);
        lt.addNode("\n");
        lt.addNode("gold", config.term.money);
        lt.addNode("\n");
        lt.addNode("hp", config.term.hp);
        lt.addNode("\n");
        lt.addNode("sp", config.term.sp);
        lt.addNode("\n");
        lt.addNode("atk", config.term.atk);
        lt.addNode("\n");
        lt.addNode("def", config.term.def);
        lt.addNode("\n");
        lt.addNode("matk", config.term.magicAtk);
        lt.addNode("\n");
        lt.addNode("mdef", config.term.magicDef);
        lt.addNode("\n");
        lt.addNode("stre", config.term.stre);
        lt.addNode("\n");
        lt.addNode("inte", config.term.inte);
        lt.addNode("\n");
        lt.addNode("agil", config.term.agil);
        lt.addNode("\n");
        lt.addNode("dext", config.term.dext);
        lt.addNode("\n");
        lt.addNode("vita", config.term.body);
        lt.addNode("\n");
        lt.addNode("luck", config.term.luck);
        lt.addNode("\n");
        lt.addNode("helm", config.term.helm);
        lt.addNode("\n");
        lt.addNode("armour", config.term.armour);
        lt.addNode("\n");
        lt.addNode("weapon", config.term.weapon);
        lt.addNode("\n");
        lt.addNode("shield", config.term.shield);
        lt.addNode("\n");
        lt.addNode("boots", config.term.boots);
        lt.addNode("\n");
        lt.addNode("jewelry", config.term.jewelry);
        lt.addNode("\n");
        lt.addNode("item", config.term.item);
        lt.addNode("\n");
        lt.addNode("equip", config.term.equip);
        lt.addNode("\n");
        lt.addNode("skill", config.term.skill);
        lt.addNode("\n");
        if (config.system.skin == null || config.system.skin.equals("")) {
            lt.addNode("skin", "nil");
        } else {
            lt.addNode("skin", "/image/skin/" + config.system.skin);
        }
        lt.addNode("\n");
        if (config.system.titleBackground == null || config.system.titleBackground.equals("")) {
            lt.addNode("titleBackground", "nil");
        } else {
            lt.addNode("titleBackground", "/image/title/" + config.system.titleBackground);
        }
        lt.addNode("\n");
        if (config.system.titleMusic == null || config.system.titleMusic.equals("")) {
            lt.addNode("titleMusic", "nil");
        } else {
            lt.addNode("titleMusic", "/audio/music/" + config.system.titleMusic);
        }
        lt.addNode("\n");
        if (config.system.startBattleSound == null || config.system.startBattleSound.equals("")) {
            lt.addNode("startBattleSound", "nil");
        } else {
            lt.addNode("startBattleSound", "/audio/sound/" + config.system.startBattleSound);
        }
        lt.addNode("\n");
        if (config.system.battleMusic == null || config.system.battleMusic.equals("")) {
            lt.addNode("battleMusic", "nil");
        } else {
            lt.addNode("battleMusic", "/audio/music/" + config.system.battleMusic);
        }
        lt.addNode("\n");
        if (config.system.winBattleSound == null || config.system.winBattleSound.equals("")) {
            lt.addNode("winSound", "nil");
        } else {
            lt.addNode("winSound", "/audio/sound/" + config.system.winBattleSound);
        }
        lt.addNode("\n");
        if (config.system.escapeSound == null || config.system.escapeSound.equals("")) {
            lt.addNode("escapeSound", "nil");
        } else {
            lt.addNode("escapeSound", "/audio/sound/" + config.system.escapeSound);
        }
        lt.addNode("\n");
        lt.addNode("startAniIndex", config.system.startAniIndex);
        lt.addNode("\n");
        lt.addNode("endAniIndex", config.system.endAniIndex);
        lt.addNode("\n");
        if (config.system.selectedSound == null || config.system.selectedSound.equals("")) {
            lt.addNode("selectedSound", "nil");
        } else {
            lt.addNode("selectedSound", "/audio/sound/" + config.system.selectedSound);
        }
        lt.addNode("\n");
        if (config.system.confirmSound == null || config.system.confirmSound.equals("")) {
            lt.addNode("confirmSound", "nil");
        } else {
            lt.addNode("confirmSound", "/audio/sound/" + config.system.confirmSound);
        }
        lt.addNode("\n");
        if (config.system.cancelSound == null || config.system.cancelSound.equals("")) {
            lt.addNode("cancelSound", "nil");
        } else {
            lt.addNode("cancelSound", "/audio/sound/" + config.system.cancelSound);
        }
        lt.addNode("\n");
        if (config.system.warnSound == null || config.system.warnSound.equals("")) {
            lt.addNode("warnSound", "nil");
        } else {
            lt.addNode("warnSound", "/audio/sound/" + config.system.warnSound);
        }
        lt.addNode("\n");
        if (config.system.equipSound == null || config.system.equipSound.equals("")) {
            lt.addNode("equipSound", "nil");
        } else {
            lt.addNode("equipSound", "/audio/sound/" + config.system.equipSound);
        }
        lt.addNode("\n");
        if (config.system.shopSound == null || config.system.shopSound.equals("")) {
            lt.addNode("shopSound", "nil");
        } else {
            lt.addNode("shopSound", "/audio/sound/" + config.system.shopSound);
        }
        lt.addNode("\n");
        if (config.system.readSound == null || config.system.readSound.equals("")) {
            lt.addNode("readSound", "nil");
        } else {
            lt.addNode("readSound", "/audio/sound/" + config.system.readSound);
        }
        lt.addNode("\n");
        if (config.system.saveSound == null || config.system.saveSound.equals("")) {
            lt.addNode("saveSound", "nil");
        } else {
            lt.addNode("saveSound", "/audio/sound/" + config.system.saveSound);
        }
        lt.addNode("\n");
        LuaTable ltId = new LuaTable();
        for (int i = 0; i < config.system.initPlayers.size(); i++) {
            ltId.addNode(config.system.initPlayers.get(i).getIndex());
        }
        lt.addNode("playersIndex", ltId);
        lt.addNode("\n");
        lt.addNode("curMapIndex", config.system.curMapIndex);
        lt.addNode("\n");
        lt.addNode("row", config.system.row);
        lt.addNode("\n");
        lt.addNode("col", config.system.col);
        lt.addNode("\n");
        lt.addNode("face", config.system.face);
        lt.addNode("\n");
        LuaTable ltAttrs = new LuaTable();
        ltAttrs.addNode("\n");
        for (int i = 0; i < config.system.attributes.size(); i++) {
            LuaTable ltAttr = new LuaTable();
            ltAttr.addNode("index", config.system.attributes.get(i).id);
            ltAttr.addNode("name", config.system.attributes.get(i).name);
            ltAttr.addNode("desc", config.system.attributes.get(i).description);
            ltAttrs.addNode("[" + config.system.attributes.get(i).id + "]", ltAttr);
            if (i != config.system.attributes.size() - 1) {
                ltAttrs.addNode("\n");
            }
        }
        lt.addNode("attributes", ltAttrs);
        LuaNode ln = new LuaNode("globalDictionary.config", lt);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/config.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("system.gat 写入失败");
            AppData.getInstance().getLogger().e("config.gat 写入失败");
            return false;
        }
    }

    private boolean saveBin() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        Config config = null;
        if (AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG).length <= 0) {
            config = new Config();
        } else {
            config = (Config) AppData.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
        }
//        if (config == null) {
//            return false;
//        }
        try {
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/config.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeUTF(config.term.name);
            dos.writeUTF(config.term.help);
            dos.writeUTF(config.term.about);
            dos.writeUTF(config.term.money);
            dos.writeInt(config.system.initPlayers.size());
            for (int i = 0; i < config.system.initPlayers.size(); i++) {
                dos.writeInt(config.system.initPlayers.get(i).getIndex());
            }
            dos.writeInt(config.system.attributes.size());
            for (int i = 0; i < config.system.attributes.size(); i++) {
                dos.writeInt(config.system.attributes.get(i).id);
                dos.writeUTF(config.system.attributes.get(i).name);
                dos.writeUTF(config.system.attributes.get(i).description);
            }
            dos.writeInt(config.system.curMapIndex);
            dos.writeInt(config.system.row);
            dos.writeInt(config.system.col);
            dos.writeByte(config.system.face);
            dos.writeUTF(config.term.hp);
            dos.writeUTF(config.term.sp);
            dos.writeUTF(config.term.atk);
            dos.writeUTF(config.term.def);
            dos.writeUTF(config.term.magicAtk);
            dos.writeUTF(config.term.magicDef);
            dos.writeUTF(config.term.stre);
            dos.writeUTF(config.term.inte);
            dos.writeUTF(config.term.agil);
            dos.writeUTF(config.term.dext);
            dos.writeUTF(config.term.body);
            dos.writeUTF(config.term.luck);
            dos.writeUTF(config.term.helm);
            dos.writeUTF(config.term.armour);
            dos.writeUTF(config.term.weapon);
            dos.writeUTF(config.term.shield);
            dos.writeUTF(config.term.boots);
            dos.writeUTF(config.term.jewelry);
            dos.writeUTF(config.term.item);
            dos.writeUTF(config.term.equip);
            dos.writeUTF(config.term.skill);
            dos.writeUTF(config.system.skin);
            dos.writeInt(config.system.startAniIndex);
            dos.writeUTF(config.system.titleBackground);
            dos.writeUTF(config.system.titleMusic);
            dos.writeUTF(config.system.startBattleSound);
            dos.writeUTF(config.system.battleMusic);
            dos.writeUTF(config.system.winBattleSound);
            dos.writeUTF(config.system.escapeSound);
            dos.writeInt(config.system.endAniIndex);
            dos.writeUTF(config.system.selectedSound);
            dos.writeUTF(config.system.confirmSound);
            dos.writeUTF(config.system.cancelSound);
            dos.writeUTF(config.system.warnSound);
            dos.writeUTF(config.system.equipSound);
            dos.writeUTF(config.system.shopSound);
            dos.writeUTF(config.system.readSound);
            dos.writeUTF(config.system.saveSound);
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("system.gat 写入失败");
            AppData.getInstance().getLogger().e("config.gat 写入失败");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean save() {
//        System.out.println("config save");
        AppData.getInstance().getLogger().v("config save");
        boolean value = false;
        if (saveLua() && saveBin()) {
            value = true;
        }
        return value;
    }
}
