/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.util.ArrayList;

/**
 * 职业
 * @author Administrator
 */
public class Vocation extends Model {

    @Override
    public String toString() {
        return name;
    }
    /**
     *
     */
    public String name = "";
    /**
     * 可以装备列表
     *
     */
    public ArrayList<Equip> equips = new ArrayList<Equip>();
    /**
     * 可以物品列表
     *
     */
    public ArrayList<Item> items = new ArrayList<Item>();
    /**
     * 可以技能列表
     * 
     */
    public ArrayList<Skill> skills = new ArrayList<Skill>();
    /**
     *
     */
    public ArrayList<Attribute> attrs = new ArrayList<Attribute>();
    /**
     *
     */
    public ArrayList<Buff> status = new ArrayList<Buff>();
}
