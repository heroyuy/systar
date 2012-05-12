/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Player extends Model {

    /**
     *
     */
    public static final int MAXHP = 0;
    /**
     *
     */
    public static final int MAXSP = 1;
    /**
     * 
     */
    public static final int STRE = 2;
    /**
     *
     */
    public static final int INTE = 3;
    /**
     *
     */
    public static final int AGIL = 4;
    /**
     *
     */
    public static final int DEX = 5;
    /**
     *
     */
    public static final int BODY = 6;
    /**
     *
     */
    public static final int LUCK = 7;
    /**
     *
     */
    public static final int EXP = 8;
    /**
     *
     */
    public static final String[] types = {"最大生命值", "最大魔法值", "力量", "敏捷", "智力", "灵巧", "体力", "幸运", "经验值"};

    @Override
    public String toString() {
        return name;
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
    public Power maxHpPower = new Power();
    /**
     *
     */
    public Power maxSpPower = new Power();
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
//    /**
//     *  经验值列表
//     */
//    public ArrayList<Integer> exps = new ArrayList<Integer>();
//    /**
//     *  最大生命值列表
//     */
//    public ArrayList<Integer> maxHps = new ArrayList<Integer>();
//    /**
//     *  最大魔法值列表
//     */
//    public ArrayList<Integer> maxSps = new ArrayList<Integer>();
//    /**
//     * 力量列表
//     */
//    public ArrayList<Integer> stres = new ArrayList<Integer>();
//    /**
//     * 智力列表
//     */
//    public ArrayList<Integer> intes = new ArrayList<Integer>();
//    /**
//     * 敏捷列表
//     */
//    public ArrayList<Integer> agils = new ArrayList<Integer>();
//    /**
//     * 灵巧列表
//     */
//    public ArrayList<Integer> dexs = new ArrayList<Integer>();
//    /**
//     * 体力列表
//     */
//    public ArrayList<Integer> bodys = new ArrayList<Integer>();
//    /**
//     * 幸运列表
//     */
//    public ArrayList<Integer> lucks = new ArrayList<Integer>();
}
