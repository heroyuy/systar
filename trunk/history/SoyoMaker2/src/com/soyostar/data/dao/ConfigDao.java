package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.Config;
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
public class ConfigDao extends Dao<Config> {

    /**
     * 
     */
    @Override
    public void load() {
        System.out.println("config load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        Config config = null;
        try {
            f = new File(SoftProxy.getInstance().getCurProject().getPath() + "/data/system.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            config = new Config();
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
            //游戏只有一个Config，编号为0
            config.setIndex(0);
            SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.CONFIG, config);
//            saveModel(config);
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
            System.out.println("没有可加载的Config");
        }
    }

    /**
     * 
     * @return
     */
    public boolean save() {
        System.out.println("config save");
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
//        Config[] configs = this.getModels(Config.class);
        Config config =(Config) SoftProxy.getInstance().getCurProject().getDataManager().getModels(Model.CONFIG)[0];
//        if (configs != null && configs.length > 0) {
//            config = configs[configs.length - 1];
//        }
        if (config == null) {
            return false;
        }
        try {
            f = new File(SoftProxy.getInstance().getCurProject().getPath() + "/data/system.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeUTF(config.name);
            dos.writeUTF(config.help);
            dos.writeUTF(config.about);
            dos.writeUTF(config.hp);
            dos.writeUTF(config.sp);
            dos.writeUTF(config.stre);
            dos.writeUTF(config.inte);
            dos.writeUTF(config.agil);
            dos.writeUTF(config.atk);
            dos.writeUTF(config.def);
            dos.writeUTF(config.flee);
            dos.writeUTF(config.money);
            dos.writeUTF(config.helm);
            dos.writeUTF(config.armour);
            dos.writeUTF(config.weapon);
            dos.writeUTF(config.shield);
            dos.writeUTF(config.boots);
            dos.writeUTF(config.jewelry);
            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
            System.out.println("system.gat写入失败");
            return false;
        }
    }
//    //单元测试
//    public static void main(String[] args) {
//        new ConfigDao().load();
//    }
}
