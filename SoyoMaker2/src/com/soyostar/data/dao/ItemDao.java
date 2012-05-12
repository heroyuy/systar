package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.Item;
import com.soyostar.data.model.Model;
import com.soyostar.project.Project;
import com.soyostar.proxy.SoftProxy;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public class ItemDao extends Dao<Item> {

    /**
     * 
     */
    @Override
    public void load() {
        System.out.println("player load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(SoftProxy.getInstance().getCurProject().getPath() + "/data/item.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int itemSum = dis.readInt();
            Item item = null;
            for (int i = 0; i < itemSum; i++) {
                item = new Item();
                item.kind = dis.readInt();
                item.setIndex(dis.readInt());
                item.name = dis.readUTF();
                item.intro = dis.readUTF();
                String s = dis.readUTF();
//                System.out.println("s: " + s);
                int temp = s.lastIndexOf("\\");
                item.icon = s.substring(temp + 1);
//                System.out.println("item icon:" +item.icon);
                item.agil = dis.readInt();
                item.stre = dis.readInt();
                item.inte = dis.readInt();
                item.hp = dis.readInt();
                item.sp = dis.readInt();
                item.maxHp = dis.readInt();
                item.maxSp = dis.readInt();
                item.lev = dis.readInt();
                item.atk = dis.readInt();
                item.def = dis.readInt();
                item.flee = dis.readInt();
                item.exp = dis.readInt();
                item.price = dis.readInt();
//                saveModel(item);
                SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.ITEM, item);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
            System.out.println("没有可加载的Item");
        }
    }

    /**
     * 
     * @return
     */
    public boolean save() {
        System.out.println("item save");
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(SoftProxy.getInstance().getCurProject().getPath() + "/data/item.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Item[] items = this.getModels(Item.class);
            Item item = null;
            for (int i = 0; i < size(); i++) {
                item = items[i];
                dos.writeInt(item.kind);
                dos.writeInt(item.getIndex());
                dos.writeUTF(item.name);
                dos.writeUTF(item.intro);
                dos.writeUTF("\\image\\icon\\item\\"+item.icon);
                dos.writeInt(item.agil);
                dos.writeInt(item.stre);
                dos.writeInt(item.inte);
                dos.writeInt(item.hp);
                dos.writeInt(item.sp);
                dos.writeInt(item.maxHp);
                dos.writeInt(item.maxSp);
                dos.writeInt(item.lev);
                dos.writeInt(item.atk);
                dos.writeInt(item.def);
                dos.writeInt(item.flee);
                dos.writeInt(item.exp);
                dos.writeInt(item.price);
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
            System.out.println("item.gat writeError!");
            return false;
        }
    }
//            //单元测试
//    public static void main(String[] args) {
//        new ItemDao().load();
//    }
}
