package com.soyostar.game.db;

import com.soyostar.game.model.Config;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class ConfigDao implements Dao {

    private DataInputStream dis = null;
    private Config config = null;

    public Config getConfig() {
        return config;
    }

    public void load() {
        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_SYSTEM, 0);
        config = new Config();
        try {
            config.name = dis.readUTF();
            config.help = dis.readUTF();
            config.about = dis.readUTF();
            config.hp = dis.readUTF();
            config.sp = dis.readUTF();
            config.stre = dis.readUTF();
            config.inte = dis.readUTF();
            config.agil = dis.readUTF();
            config.atk = dis.readUTF();
            config.def = dis.readUTF();
            config.flee = dis.readUTF();
            config.money = dis.readUTF();
            config.helm = dis.readUTF();
            config.armour = dis.readUTF();
            config.weapon = dis.readUTF();
            config.shield = dis.readUTF();
            config.boots = dis.readUTF();
            config.jewelry = dis.readUTF();
            dis.close();
        } catch (IOException e) {
            System.out.println("system.gat读取失败");
        }
    }

    public void save() {
    }
}
