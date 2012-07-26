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
public class Item extends Model {

    public static final int COMMON = 0;
    public static final int COST = 1;
    public static final int HELM = 2;
    public static final int JEWELRY = 3;
    public static final int WEAPON = 4;
    public static final int SHIELD = 5;
    public static final int ARMOUR = 6;
    public static final int BOOTS = 7;

    @Override
    public String toString() {
        return name + " ID:" + getIndex() + "";
    }
    /**
     * 
     */
    public static final String[] types = {"普通物品", "消耗品", "头盔", "饰品", "武器", "盾牌", "铠甲", "战靴"};
    /**
     * 名称
     */
    public String name = "";
    /**
     * 介绍
     */
    public String intro = "";
    /**
     * 图标
     */
    public String icon = "";
    /**
     * 价格
     */
    public int price;
    /**
     *  限制等级
     */
    public int lev;
    /**
     * 类型
     */
    public int type;
    /**
     *
     */
    public ArrayList<Skill> skillList = new ArrayList<Skill>();
}
