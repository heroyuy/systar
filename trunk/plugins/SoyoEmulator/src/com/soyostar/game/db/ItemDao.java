/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Item;
import com.soyostar.ui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class ItemDao implements Dao {

    private DataInputStream dis = null;
    private HashMap items = null;

    public Item getItem(int index) {
        return (Item) items.get(index);
    }

    public Item[] getItemList() {
        return (Item[]) items.values().toArray();
    }

    public void load() {
        items = new HashMap();


        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_ITEM, 0);

        try {
            int itemSum = dis.readInt();
            Item item = null;
            for (int i = 0; i < itemSum; i++) {
                item = new Item();
                item.kind = dis.readInt();
                item.index = dis.readInt();
                item.name = dis.readUTF();
                item.intro = dis.readUTF();
                item.icon = Image.createImage("product/image/icon/item/" + dis.readUTF());
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
                items.put(item.index, item);
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("item.gat readError!");
        }
    }

    public void save() {
    }
}
