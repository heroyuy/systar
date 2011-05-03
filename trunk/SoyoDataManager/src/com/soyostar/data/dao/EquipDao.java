package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.Equip;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wp_g4
 * 测试通过
 */
public class EquipDao extends Dao<Equip> {

    public void load() {

        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/equip.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int equipSum = dis.readInt();
            Equip equip = null;
            for (int i = 0; i < equipSum; i++) {
                equip = new Equip();
                equip.kind = dis.readInt();
                equip.setIndex(dis.readInt());
                equip.name = dis.readUTF();
                equip.intro = dis.readUTF();
                equip.icon = "/image/icon/equip/" + dis.readUTF();
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
                saveModel(equip);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
            System.out.println("没有可加载的Equip");
        }
    }

    public void save() {

        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/equip.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Equip[] equips = this.getModels(Equip.class);
            Equip equip = null;
            for (int i = 0; i < size(); i++) {
                equip = equips[i];
                dos.writeInt(equip.kind);
                dos.writeInt(equip.getIndex());
                dos.writeUTF(equip.name);
                dos.writeUTF(equip.intro);
                dos.writeUTF(equip.icon);
                dos.writeInt(equip.agil);
                dos.writeInt(equip.stre);
                dos.writeInt(equip.inte);
                dos.writeInt(equip.maxHp);
                dos.writeInt(equip.maxSp);
                dos.writeInt(equip.lev);
                dos.writeInt(equip.atk);
                dos.writeInt(equip.def);
                dos.writeInt(equip.flee);
                dos.writeInt(equip.price);
            }
            dos.close();
            fos.close();
            f = null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("equip.gat writeError!");
        }
    }
//    //单元测试
//
//    public static void main(String[] args) {
//        new EquipDao().load();
//    }
}
