/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Model;
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
public class ItemDao extends Dao<Item> {

    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();

    /**
     *
     * @throws FileNotFoundException 
     * @throws IOException
     */
    @Override
    public void load() throws FileNotFoundException, IOException {
        printer.v("item load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;

        f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/item.gat");
        fis = new FileInputStream(f);
        dis = new DataInputStream(fis);
        int itemSum = dis.readInt();
        Item item = null;
        for (int i = 0; i < itemSum; i++) {
            item = new Item();
            item.setIndex(dis.readInt());
            item.name = dis.readUTF();
            item.intro = dis.readUTF();
            item.icon = dis.readUTF();
            item.lev = dis.readInt();
            item.type = dis.readInt();
            item.price = dis.readInt();
            AppData.getInstance().getCurProject().getDataManager().saveModel(Model.ITEM, item);
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
            f = new File(AppData.getInstance().getCurProject().getPath() + "/softdata/item.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Item[] items = this.getModels(Item.class);
            Item item = null;
            for (int i = 0; i < size(); i++) {
                item = items[i];
                dos.writeInt(item.getIndex());
                dos.writeUTF(item.name);
                dos.writeUTF(item.intro);
                dos.writeUTF(item.icon);
                dos.writeInt(item.lev);
                dos.writeInt(item.type);
                dos.writeInt(item.price);
            }
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
//            System.out.println("item.gat 写入失败!");
            printer.e("item.gat 写入失败!");
            return false;
        }
    }

    private boolean saveLua() {
        Item[] items = this.getModels(Item.class);
        Item item = null;
        LuaTable lts = new LuaTable();
        for (int i = 0; i < size(); i++) {
            item = items[i];
            LuaTable lt = new LuaTable();
            lt.addNode("\n");
            lt.addNode("index", Configuration.Prefix.ITEM_MASK + item.getIndex() + 1);
            lt.addNode("\n");
            lt.addNode("name", item.name);
            lt.addNode("\n");
            lt.addNode("desc", item.intro);
            lt.addNode("\n");
            if (item.icon == null || item.icon.equals("")) {
                lt.addNode("icon", "nil");
            } else {
                lt.addNode("icon", "/image/icon/item/" + item.icon);
            }
            lt.addNode("\n");
            lt.addNode("type", item.type);
            lt.addNode("\n");
            lt.addNode("lev", item.lev);
            lt.addNode("\n");
            lt.addNode("price", item.price);
            lt.addNode("\n");
            LuaTable ltId = new LuaTable();
            for (int j = 0; j < item.skillList.size(); j++) {
                ltId.addNode(Configuration.Prefix.SKILL_MASK + item.skillList.get(i).getIndex() + 1);
            }
            lt.addNode("skillList", ltId);
            lt.addNode("\n");
            lts.addNode("[" + (Configuration.Prefix.ITEM_MASK + item.getIndex() + 1) + "]", lt);
            if (i != size() - 1) {
                lts.addNode("\n");
            }
        }
        LuaNode ln = new LuaNode("Dictionary.items", lts);
        try {
            LuaFileUtil.writeToFile(ln, AppData.getInstance().getCurProject().getPath() + "/data/item.gat");
            return true;
        } catch (IOException ex) {
//            System.out.println("item.gat 写入失败");
            printer.e("item.gat 写入失败!");
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean save() {
//        System.out.println("item save");
        printer.v("item save");
        boolean value = false;
        if (saveLua()) {
            value = true;
        }
        return value;
    }

    @Override
    public boolean saveSoft() {
        printer.v("item save");
        boolean value = false;
        if (saveBin()) {
            value = true;
        }
        return value;
    }
}
