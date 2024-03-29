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
        return getIndex() + " : " + name;
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
     * 战斗图
     */
    public String battleImg = "";
    /**
     *  等级
     */
    public int lev;
    /**
     *  最大生命值
     */
    public int maxHp;
    /**
     *  最大魔法值
     */
    public int maxSp;
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
     * 宝物列表，可能掉落的
     */
    public ArrayList<Treasure> treasures = new ArrayList<Treasure>();
    /**
     * 装备列表，身上穿的
     */
    public ArrayList<Equip> equips = new ArrayList<Equip>();
    /**
     * 0 静止 1 普通攻击 2 防御	3 使用物品 5 使用技能 6 逃跑
     * 动作列表
     */
    public ArrayList<Action> actions = new ArrayList<Action>();
    /**
     * 属性
     */
    public ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    /**
     * 状态
     */
    public ArrayList<Status> status = new ArrayList<Status>();
}
