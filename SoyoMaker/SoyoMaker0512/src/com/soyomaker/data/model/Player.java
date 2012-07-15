/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

/**
 *
 * @author Administrator
 */
public class Player extends Model {

    /**
     * 
     */
    public static final int STRE = 0;
    /**
     *
     */
    public static final int INTE = 1;
    /**
     *
     */
    public static final int AGIL = 2;
    /**
     *
     */
    public static final int DEX = 3;
    /**
     *
     */
    public static final int BODY = 4;
    /**
     *
     */
    public static final int LUCK = 5;
    /**
     *
     */
    public static final int EXP = 6;
    /**
     *
     */
    public static final String[] types = {"力量", "敏捷", "智力", "灵巧", "体力", "幸运", "经验值"};

    @Override
    public String toString() {
        return name + " ID:" + getIndex();
    }
    /**
     *
     */
    public String name = "";
    /**
     *
     */
    public String intro = "";
    /**
     * 头像
     */
    public String headImg = "";
    /**
     * 行走图
     */
    public String charImg = "";
    /**
     * 战斗图
     */
    public String battleImg = "";
    /**
     *  初始等级
     */
    public int startLev;
    /**
     *  最大等级
     */
    public int maxLev = 100;
    /**
     *  职业编号
     */
    public int vocationIndex = -1;
    public int attributeId = -1;
    public int attackDistance = 0;

    /**
     *
     */
    public class Power {

        /**
         *
         */
        public int a = 0;
        /**
         *
         */
        public int b = 0;
        /**
         *
         */
        public int c = 0;
        /**
         *
         */
        public int d = 0;
    }
    /**
     *
     */
    public Power expPower = new Power();
    /**
     *
     */
    public Power strePower = new Power();
    /**
     *
     */
    public Power intePower = new Power();
    /**
     *
     */
    public Power agilPower = new Power();
    /**
     *
     */
    public Power dexPower = new Power();
    /**
     *
     */
    public Power bodyPower = new Power();
    /**
     *
     */
    public Power luckPower = new Power();
}
