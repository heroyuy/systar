/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.emulator.framework;

import com.soyostar.game.db.DataManager;
import com.soyostar.game.model.Bag;
import com.soyostar.game.model.GameData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DataBase {

    private GameData gd = GameData.getGameData();
    private DataManager dm = DataManager.getInstance();

//    /**
//     *
//     * @param args 测试
//     */
//    public static void main(String[] args) {
//        DataBase db = new DataBase();
//        db.saveDB();
//        db.loadDB();
//    }//end main()
    public boolean saveDB() {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            File f = new File("product/data.base");
            if (!f.exists()) {
                f.createNewFile();
            }
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(gd.curMap.index);
            dos.writeInt(gd.player.lev);
            dos.writeByte(gd.player.face);
            dos.writeInt(gd.player.row);
            dos.writeInt(gd.player.col);
            dos.writeInt(gd.player.money);
            dos.writeInt(gd.player.exp);
            dos.writeInt(gd.player.agil);
            dos.writeInt(gd.player.atk);
            dos.writeInt(gd.player.def);
            dos.writeInt(gd.player.flee);
            dos.writeInt(gd.player.inte);
            dos.writeInt(gd.player.stre);
            dos.writeInt(gd.player.maxHp);
            dos.writeInt(gd.player.maxSp);
            dos.writeInt(gd.player.equipArmour);
            dos.writeInt(gd.player.equipBoots);
            dos.writeInt(gd.player.equipHelm);
            dos.writeInt(gd.player.equipJewelry);
            dos.writeInt(gd.player.equipShield);
            dos.writeInt(gd.player.equipWeapon);
            dos.writeInt(gd.player.bag.getList(Bag.EQUIP).length);
            for (int i = 0, n = gd.player.bag.getList(Bag.EQUIP).length; i < n; i++) {
                dos.writeInt(gd.player.bag.getList(Bag.EQUIP)[i]);
                dos.writeInt(gd.player.bag.getNum(Bag.EQUIP, gd.player.bag.getList(Bag.EQUIP)[i]));
            }
            dos.writeInt(gd.player.bag.getList(Bag.ITEM).length);
            for (int i = 0, n = gd.player.bag.getList(Bag.ITEM).length; i < n; i++) {
                dos.writeInt(gd.player.bag.getList(Bag.ITEM)[i]);
                dos.writeInt(gd.player.bag.getNum(Bag.ITEM, gd.player.bag.getList(Bag.ITEM)[i]));
            }
            dos.writeInt(gd.player.bag.getList(Bag.SKILL).length);
            for (int i = 0, n = gd.player.bag.getList(Bag.SKILL).length; i < n; i++) {
                dos.writeInt(gd.player.bag.getList(Bag.SKILL)[i]);
                dos.writeInt(gd.player.bag.getNum(Bag.SKILL, gd.player.bag.getList(Bag.SKILL)[i]));
            }
            dos.writeInt(gd.player.hp);
            dos.writeInt(gd.player.sp);
            for (int i = 0; i < 100; i++) {
                dos.writeInt(gd.vars[i]);
            }

            for (int i = 0; i < 100; i++) {
                dos.writeBoolean(gd.switchs[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadDB() {

        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            File f = new File("product/data.base");
            if (!f.exists()) {
                return false;
            }
            if (dm.getPlayer() == null) {
                return false;
            }
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int mapIndex = dis.readInt();
            System.out.println("mapIndex:" + mapIndex);
            gd.curMap = dm.getMap(mapIndex);
            gd.player = dm.getPlayer().getClone();
            int lev = dis.readInt();
            System.out.println("lev:" + lev);
            gd.player.lev = lev;
            byte face = dis.readByte();
            System.out.println("face:" + face);
            gd.player.face = face;
            gd.player.changeToward(face);
            int row = dis.readInt();
            System.out.println("row:" + row);
            gd.player.row = row;
            int col = dis.readInt();
            System.out.println("col:" + col);
            gd.player.col = col;
            gd.player.money = dis.readInt();
            System.out.println("money:" + gd.player.money);
            gd.player.exp = dis.readInt();
            System.out.println("exp:" + gd.player.exp);
            gd.player.agil = dis.readInt();
            gd.player.atk = dis.readInt();
            gd.player.def = dis.readInt();
            gd.player.flee = dis.readInt();
            gd.player.inte = dis.readInt();
            gd.player.stre = dis.readInt();
            gd.player.maxHp = dis.readInt();
            gd.player.maxSp = dis.readInt();
            gd.player.equipArmour = dis.readInt();
            System.out.println("equipArmour:" + gd.player.equipArmour);
            gd.player.equipBoots = dis.readInt();
            System.out.println("equipBoots:" + gd.player.equipBoots);
            gd.player.equipHelm = dis.readInt();
            System.out.println("equipHelm:" + gd.player.equipHelm);
            gd.player.equipJewelry = dis.readInt();
            System.out.println("equipJewelry:" + gd.player.equipJewelry);
            gd.player.equipShield = dis.readInt();
            System.out.println("equipShield:" + gd.player.equipShield);
            gd.player.equipWeapon = dis.readInt();
            System.out.println("equipWeapon:" + gd.player.equipWeapon);
            int equipNum = dis.readInt();
            for (int i = 0; i < equipNum; i++) {
                gd.player.bag.add(Bag.EQUIP, dis.readInt(), dis.readInt());
            }
            int itemNum = dis.readInt();
            for (int i = 0; i < itemNum; i++) {
                gd.player.bag.add(Bag.EQUIP, dis.readInt(), dis.readInt());
            }
            int skillNum = dis.readInt();
            for (int i = 0; i < skillNum; i++) {
                gd.player.bag.add(Bag.EQUIP, dis.readInt(), dis.readInt());
            }
            gd.player.setLocation();
            gd.player.hp = dis.readInt();
            System.out.println("hp:" + gd.player.hp);
            gd.player.sp = dis.readInt();
            System.out.println("sp:" + gd.player.sp);
            for (int i = 0; i < 100; i++) {
                gd.vars[i] = dis.readInt();
            }
            for (int i = 0; i < 100; i++) {
                gd.switchs[i] = dis.readBoolean();
            }
            gd.curMap.resetRegion(gd.player);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
