/*
 * Copyright 2010-2011 Soyostar Software, Inc. All rights reserved.
 */
package com.soyomaker.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/** */
/**
 * 读取properties文件
 * @author cokey
 *
 */
public class Configuration {

    /**
     *
     */
    public static class Filter {

        private static final Configuration prePro = new Configuration("config" + File.separator + "filter.properties");
        /**
         *
         */
        public static final String[] PICTURE = prePro.getValue("picture", "png").split(",");
        /**
         * 
         */
        public static final String[] MUSIC = prePro.getValue("music", "mp3,wav,ogg").split(",");
        /**
         *
         */
        public static final String[] SOUND = prePro.getValue("sound", "mp3,wav,ogg").split(",");
    }

    /**
     *
     */
    public static class Prefix {

        private static final Configuration prePro = new Configuration("config" + File.separator + "prefix.properties");
        //以下是各模型类的ID前缀
        /**
         *
         */
        public static final int MAP = Integer.parseInt(prePro.getValue("map", "0"));
        /**
         *
         */
        public static final int VOCATION = Integer.parseInt(prePro.getValue("vocation", "0"));
        /**
         *
         */
        public static final int PLAYER = Integer.parseInt(prePro.getValue("player", "0"));
        /**
         *
         */
        public static final int SKILL = Integer.parseInt(prePro.getValue("skill", "0"));
        /**
         *
         */
        public static final int ITEM = Integer.parseInt(prePro.getValue("item", "0"));
        /**
         *
         */
        public static final int EQUIP = Integer.parseInt(prePro.getValue("equip", "0"));
        /**
         *
         */
        public static final int ENEMY = Integer.parseInt(prePro.getValue("enemy", "0"));
        /**
         *
         */
        public static final int ENEMYTROOP = Integer.parseInt(prePro.getValue("enemytroop", "0"));
        /**
         *
         */
        public static final int STATUS = Integer.parseInt(prePro.getValue("status", "0"));
        /**
         *
         */
        public static final int NPC = Integer.parseInt(prePro.getValue("npc", "0"));
        /**
         *
         */
        public static final int ANIMATION = Integer.parseInt(prePro.getValue("animation", "0"));
        /**
         *
         */
        public static final int SCRIPT = Integer.parseInt(prePro.getValue("script", "0"));
        /**
         *
         */
        public static final int ATTRIBUTE = Integer.parseInt(prePro.getValue("attribute", "0"));
        //以下是各模型类的ID掩码
        /**
         *
         */
        public static final int MAP_MASK = MAP * 1000000;
        /**
         *
         */
        public static final int VOCATION_MASK = VOCATION * 1000000;
        /**
         *
         */
        public static final int PLAYER_MASK = PLAYER * 1000000;
        /**
         *
         */
        public static final int SKILL_MASK = SKILL * 1000000;
        /**
         *
         */
        public static final int ITEM_MASK = ITEM * 1000000;
        /**
         *
         */
        public static final int EQUIP_MASK = EQUIP * 1000000;
        /**
         *
         */
        public static final int ENEMY_MASK = ENEMY * 1000000;
        /**
         *
         */
        public static final int ENEMYTROOP_MASK = ENEMYTROOP * 1000000;
        /**
         *
         */
        public static final int STATUS_MASK = STATUS * 1000000;
        /**
         *
         */
        public static final int NPC_MASK = NPC * 1000000;
        /**
         *
         */
        public static final int ANIMATION_MASK = ANIMATION * 1000000;
        /**
         *
         */
        public static final int SCRIPT_MASK = SCRIPT * 1000000;
        /**
         *
         */
        public static final int ATTRIBUTE_MASK = ATTRIBUTE * 1000000;
    }
    private Properties propertie;
    private FileInputStream inputFile;

    /** */
    /**
     * 初始化Configuration类
     * @param filePath 要读取的配置文件的路径+名称
     */
    public Configuration(String filePath) {
        propertie = new Properties();
        try {
            inputFile = new FileInputStream(filePath);
            propertie.load(inputFile);
            inputFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
//            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("装载文件--->失败!");
//            ex.printStackTrace();
        }
    }

    /**
     * 重载函数，得到key的值
     * @param key 取得其值的键
     * @return key的值
     */
    public String getValue(String key) {
        if (propertie.containsKey(key)) {
            String value = propertie.getProperty(key);//得到某一属性的值
            return value;
        } else {
            return "";
        }
    }

    /**
     *
     * @param key
     * @param defaultString
     * @return
     */
    public String getValue(String key, String defaultString) {
        return propertie.getProperty(key, defaultString);//得到某一属性的值
    }
}
