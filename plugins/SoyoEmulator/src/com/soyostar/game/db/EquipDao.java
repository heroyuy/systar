/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Equip;
import com.soyostar.ui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class EquipDao implements Dao {

    private DataInputStream dis = null;
    private HashMap equips = null;

    public Equip getEquip(int index) {
        return (Equip) equips.get(index);
    }

    public Equip[] getEquipList() {
        return (Equip[]) equips.values().toArray();
    }

    public void load() {
        equips = new HashMap();

        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_EQUIP, 0);
        try {
            int equipSum = dis.readInt();
            Equip equip = null;
            for (int i = 0; i < equipSum; i++) {
                equip = new Equip();
                equip.kind = dis.readInt();
                equip.index = dis.readInt();
                equip.name = dis.readUTF();
                equip.intro = dis.readUTF();
                equip.icon = Image.createImage("product/image/icon/equip/" + dis.readUTF());
                equip.agil = dis.readInt();
                equip.stre = dis.readInt();
                equip.inte = dis.readInt();
                equip.maxHp = dis.readInt();
                equip.maxSp = dis.readInt();
                equip.lev = dis.readInt();
                equip.atk = dis.readInt();
                equip.def = dis.readInt();
                equip.flee = dis.readInt();
                equip.price = dis.readInt();
                equips.put(equip.index, equip);
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("equip.gat readError!");
        }
    }

    public void save() {
    }
}
