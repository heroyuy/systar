/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import model.Bag;
import model.GameData;
import model.Player;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author Administrator
 */
public class DataBase {

    private GameData gd = GameData.getGameData();

    public boolean saveDB() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] data = null;
        baos = new ByteArrayOutputStream();
        dos = new DataOutputStream(baos);
        try {
            dos.writeInt(gd.curMap.index);//当前地图
            dos.writeInt(gd.player.lev);//角色等级
            dos.writeInt(gd.player.face);
            dos.writeInt(gd.player.row);
            dos.writeInt(gd.player.col);
            dos.writeInt(gd.player.money);
            dos.writeInt(gd.player.exp);
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

        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
        data = baos.toByteArray();
        RecordStore rs = null;
        try {
            rs = RecordStore.openRecordStore("soyostar", true);
            if (rs.getNumRecords() <= 0) {
                rs.addRecord(data, 0, 0);
                rs.setRecord(1, data, 0, data.length);
            } else {
                rs.setRecord(1, data, 0, data.length);
            }
        } catch (RecordStoreFullException e) {
            e.printStackTrace();
            return false;
        } catch (RecordStoreNotFoundException e) {
            System.out.println("找不到RMS");
//            e.printStackTrace();
            return false;
        } catch (RecordStoreException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                baos.close();
                dos.close();
                rs.closeRecordStore();
            } catch (RecordStoreNotOpenException e) {
                e.printStackTrace();
                return false;
            } catch (RecordStoreException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean loadDB() {
        ByteArrayInputStream bais = null;
        DataInputStream dis = null;
        byte[] data = null;
        RecordStore rs = null;
        try {
            rs = RecordStore.openRecordStore("soyostar", false);
            data = rs.getRecord(1);
        } catch (RecordStoreFullException e) {

            e.printStackTrace();
            return false;
        } catch (RecordStoreNotFoundException e) {
            System.out.println("找不到RMS");
//            e.printStackTrace();
            return false;
        } catch (RecordStoreException e) {
            e.printStackTrace();
            return false;
        }
        bais = new ByteArrayInputStream(data);
        dis = new DataInputStream(bais);
        try {
            int mapIndex = dis.readInt();
            System.out.println("mapIndex:" + mapIndex);
            gd.curMap = gd.gameObjectManager.getMap(mapIndex);
            gd.player = gd.gameObjectManager.getPlayer().getClone();
            int lev = dis.readInt();
            System.out.println("lev:" + lev);
            gd.player.lev = lev;
            int face = dis.readInt();
            System.out.println("face:" + face);
            gd.player.face = face;
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
                gd.player.bag.add(Bag.ITEM, dis.readInt(), dis.readInt());
            }
            int skillNum = dis.readInt();
            for (int i = 0; i < skillNum; i++) {
                gd.player.bag.add(Bag.SKILL, dis.readInt(), dis.readInt());
            }
            gd.player.setLocation();
            gd.player.init();
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
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                bais.close();
                dis.close();
                rs.closeRecordStore();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (RecordStoreNotOpenException e) {
                e.printStackTrace();
                return false;
            } catch (RecordStoreException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
