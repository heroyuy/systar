/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

/**
 * 宝物
 * @author Administrator
 */
public class Treasure extends Model {

    @Override
    public String toString() {
        return name;
    }
    /**
     *
     */
    public static final int TREASURE_ITEM = 0;
    /**
     *
     */
    public static final int TREASURE_EQUIP = 1;
    /**
     * 名称
     */
    public String name = "";
    /**
     *
     */
    public String intro = "";
    /**
     *
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
     * 掉落率
     */
    public int rate;
}
