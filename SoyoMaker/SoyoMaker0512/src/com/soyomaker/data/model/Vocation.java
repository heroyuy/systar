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
     * 可以物品列表
     *
     */
    public ArrayList<Item> items = new ArrayList<Item>();
    /**
     * 可以技能列表
     * 
     */
    public ArrayList<Skill> skills = new ArrayList<Skill>();
}
