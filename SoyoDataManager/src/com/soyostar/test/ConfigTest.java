package com.soyostar.test;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.Config;

/**
 *
 * @author Administrator
 */
public class ConfigTest extends Config {

    public String myinfo = "hi!";

    public static void main(String[] args) {
        DataManager dm = DataManager.getInstance();
        dm.init("res");
        ConfigTest config1 = dm.getConfig(ConfigTest.class);
        System.out.println(config1.getIndex() + config1.about);
        config1.about = "新游戏说明";
        dm.saveConfig(config1);
        ConfigTest config2 = dm.getConfig(ConfigTest.class);
        System.out.println(config2.getIndex() + config2.about);
    }
}
