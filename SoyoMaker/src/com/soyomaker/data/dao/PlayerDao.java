/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Player;
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
public class PlayerDao extends Dao<Player> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    @Override
    public void load() throws FileNotFoundException, IOException {

        printer.v("player load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;

        f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/player.gat");
        fis = new FileInputStream(f);
        dis = new DataInputStream(fis);
        int playerSum = dis.readInt();
        Player player = null;
        for (int i = 0; i < playerSum; i++) {
            player = new Player();
            player.setIndex(dis.readInt());
            player.name = dis.readUTF();
            player.intro = dis.readUTF();
            player.headImg = dis.readUTF();
            player.charImg = dis.readUTF();
            player.battleImg = dis.readUTF();
            player.vocationIndex = dis.readInt();
            player.startLev = dis.readInt();
            player.maxLev = dis.readInt();
            player.agilPower.a = dis.readInt();
            player.agilPower.b = dis.readInt();
            player.agilPower.c = dis.readInt();
            player.agilPower.d = dis.readInt();
            player.bodyPower.a = dis.readInt();
            player.bodyPower.b = dis.readInt();
            player.bodyPower.c = dis.readInt();
            player.bodyPower.d = dis.readInt();

            player.dexPower.a = dis.readInt();
            player.dexPower.b = dis.readInt();
            player.dexPower.c = dis.readInt();
            player.dexPower.d = dis.readInt();

            player.expPower.a = dis.readInt();
            player.expPower.b = dis.readInt();
            player.expPower.c = dis.readInt();
            player.expPower.d = dis.readInt();

            player.intePower.a = dis.readInt();
            player.intePower.b = dis.readInt();
            player.intePower.c = dis.readInt();
            player.intePower.d = dis.readInt();

            player.luckPower.a = dis.readInt();
            player.luckPower.b = dis.readInt();
            player.luckPower.c = dis.readInt();
            player.luckPower.d = dis.readInt();

            player.maxHpPower.a = dis.readInt();
            player.maxHpPower.b = dis.readInt();
            player.maxHpPower.c = dis.readInt();
            player.maxHpPower.d = dis.readInt();

            player.maxSpPower.a = dis.readInt();
            player.maxSpPower.b = dis.readInt();
            player.maxSpPower.c = dis.readInt();
            player.maxSpPower.d = dis.readInt();

            player.strePower.a = dis.readInt();
            player.strePower.b = dis.readInt();
            player.strePower.c = dis.readInt();
            player.strePower.d = dis.readInt();
            AppData.getInstance().getCurProject().getDataManager().saveModel(Model.PLAYER, player);
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
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/player.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Player[] players = this.getModels(Player.class);
            Player player = null;
            for (int i = 0; i < size(); i++) {
                player = players[i];
                dos.writeInt(player.getIndex());
                dos.writeUTF(player.name);
                dos.writeUTF(player.intro);
                dos.writeUTF(player.headImg);
                dos.writeUTF(player.charImg);
                dos.writeUTF(player.battleImg);
                dos.writeInt(player.vocationIndex);
                dos.writeInt(player.startLev);
                dos.writeInt(player.maxLev);

                dos.writeInt(player.agilPower.a);
                dos.writeInt(player.agilPower.b);
                dos.writeInt(player.agilPower.c);
                dos.writeInt(player.agilPower.d);

                dos.writeInt(player.bodyPower.a);
                dos.writeInt(player.bodyPower.b);
                dos.writeInt(player.bodyPower.c);
                dos.writeInt(player.bodyPower.d);

                dos.writeInt(player.dexPower.a);
                dos.writeInt(player.dexPower.b);
                dos.writeInt(player.dexPower.c);
                dos.writeInt(player.dexPower.d);

                dos.writeInt(player.expPower.a);
                dos.writeInt(player.expPower.b);
                dos.writeInt(player.expPower.c);
                dos.writeInt(player.expPower.d);

                dos.writeInt(player.intePower.a);
                dos.writeInt(player.intePower.b);
                dos.writeInt(player.intePower.c);
                dos.writeInt(player.intePower.d);

                dos.writeInt(player.luckPower.a);
                dos.writeInt(player.luckPower.b);
                dos.writeInt(player.luckPower.c);
                dos.writeInt(player.luckPower.d);

                dos.writeInt(player.maxHpPower.a);
                dos.writeInt(player.maxHpPower.b);
                dos.writeInt(player.maxHpPower.c);
                dos.writeInt(player.maxHpPower.d);

                dos.writeInt(player.maxSpPower.a);
                dos.writeInt(player.maxSpPower.b);
                dos.writeInt(player.maxSpPower.c);
                dos.writeInt(player.maxSpPower.d);

                dos.writeInt(player.strePower.a);
                dos.writeInt(player.strePower.b);
                dos.writeInt(player.strePower.c);
                dos.writeInt(player.strePower.d);
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("player.gat 写入失败!");
            printer.e("player.gat 写入失败!");
            return false;
        }
    }

    private boolean saveLua() {
        Player[] players = this.getModels(Player.class);
        Player player = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            LuaTable lt = new LuaTable();
            player = players[i];
            lt.addNode("\n");
            lt.addNode("index", Configuration.Prefix.PLAYER_MASK + player.getIndex() + 1);
            lt.addNode("\n");
            lt.addNode("name", player.name);
            lt.addNode("\n");
            lt.addNode("intro", player.intro);
            lt.addNode("\n");
            if (player.headImg == null || player.headImg.equals("")) {
                lt.addNode("headImg", "nil");
            } else {
                lt.addNode("headImg", "/image/face/" + player.headImg);
            }
            lt.addNode("\n");
            if (player.charImg == null || player.charImg.equals("")) {
                lt.addNode("charImg", "nil");
            } else {
                lt.addNode("charImg", "/image/character/" + player.charImg);
            }
            lt.addNode("\n");
            if (player.battleImg == null || player.battleImg.equals("")) {
                lt.addNode("battleImg", "nil");
            } else {
                lt.addNode("battlerImg", "/image/battler/" + player.battleImg);
            }
            lt.addNode("\n");
            if (player.vocationIndex == -1) {
                lt.addNode("vocationIndex", -1);
            } else {
                lt.addNode("vocationIndex", Configuration.Prefix.VOCATION_MASK + player.vocationIndex + 1);
            }
            lt.addNode("\n");
            lt.addNode("startLev", player.startLev);
            lt.addNode("\n");
            lt.addNode("maxLev", player.maxLev);
            lt.addNode("\n");
            LuaTable exps = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                exps.addNode(player.expPower.a * j + player.expPower.b * j * j + player.expPower.c * j * j * j + player.expPower.d);
            }
            lt.addNode("expList", exps);
            lt.addNode("\n");
            LuaTable maxHps = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                maxHps.addNode(player.maxHpPower.a * j + player.maxHpPower.b * j * j + player.maxHpPower.c * j * j * j + player.maxHpPower.d);
            }
            lt.addNode("maxHpList", maxHps);
            lt.addNode("\n");
            LuaTable maxSps = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                maxSps.addNode(player.maxSpPower.a * j + player.maxSpPower.b * j * j + player.maxSpPower.c * j * j * j + player.maxSpPower.d);
            }
            lt.addNode("maxSpList", maxSps);
            lt.addNode("\n");
            LuaTable strs = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                strs.addNode(player.strePower.a * j + player.strePower.b * j * j + player.strePower.c * j * j * j + player.strePower.d);
            }
            lt.addNode("strList", strs);
            lt.addNode("\n");
            LuaTable agis = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                agis.addNode(player.agilPower.a * j + player.agilPower.b * j * j + player.agilPower.c * j * j * j + player.agilPower.d);
            }
            lt.addNode("agiList", agis);
            lt.addNode("\n");
            LuaTable ints = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                ints.addNode(player.intePower.a * j + player.intePower.b * j * j + player.intePower.c * j * j * j + player.intePower.d);
            }
            lt.addNode("intList", ints);
            lt.addNode("\n");
            LuaTable vits = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                vits.addNode(player.bodyPower.a * j + player.bodyPower.b * j * j + player.bodyPower.c * j * j * j + player.bodyPower.d);
            }
            lt.addNode("vitList", vits);
            lt.addNode("\n");
            LuaTable dexs = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                dexs.addNode(player.dexPower.a * j + player.dexPower.b * j * j + player.dexPower.c * j * j * j + player.dexPower.d);
            }
            lt.addNode("dexList", dexs);
            lt.addNode("\n");
            LuaTable lucs = new LuaTable();
            for (int j = 0; j < player.maxLev; j++) {
                lucs.addNode(player.luckPower.a * j + player.luckPower.b * j * j + player.luckPower.c * j * j * j + player.luckPower.d);
            }
            lt.addNode("lucList", lucs);
            lts.addNode("[" + (Configuration.Prefix.PLAYER_MASK + player.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("Dictionary.players", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/player.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("player.gat 写入失败");
            printer.e("player.gat 写入失败!");
            return false;
        }
    }

    @Override
    public boolean save() {
//        System.out.println("player save");
        printer.v("player save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("player save");
        boolean value = false;
        if (saveBin()) {
            value = true;
        }
        return value;
    }
}
