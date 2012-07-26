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
public class Enemy extends Model {

    @Override
    public String toString() {
        return name + " ID:" + getIndex() + "";
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
     *
     */
    public int attackDistance = 0;
    /**
     *
     */
    public String attackSound = "";
    /**
     *
     */
    public String onAttackSound = "";
    /**
     *
     */
    public String deadSound = "";
    /**
     * 行走图
     */
    public String charImg = "";
    /**
     * 动作图
     */
    public String actionImg = "";
    /**
     *  等级
     */
    public int lev;
    /**
     * 力量
     */
    public int stre;
    /**
     * 智力
     */
    public int inte;
    /**
     * 敏捷
     */
    public int agil;
    /**
     * 灵巧
     */
    public int dex;
    /**
     * 体力
     */
    public int body;
    /**
     * 幸运
     */
    public int luck;
    /**
     * 经验
     */
    public int exp;
    /**
     * 金币
     */
    public int money;
    /**
     *
     */
    public int attributeId = -1;

    /**
     *
     */
    public static class ItemInfo {

        /**
         *
         */
        public Item item;
        /**
         *
         */
        public int rate;
    }
    /**
     * 宝物列表
     */
    public ArrayList<ItemInfo> treasures = new ArrayList<ItemInfo>();
    /**
     * 0 静止 1 普通攻击 2 防御	3 使用物品 5 使用技能 6 逃跑
     * 动作列表
     */
    public ArrayList<Action> actions = new ArrayList<Action>();
}
