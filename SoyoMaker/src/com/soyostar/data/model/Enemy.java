package com.soyostar.data.model;

import java.util.ArrayList;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public class Enemy extends Model {

//    @Override
//    public String toString() {
//        return name;
//    }
    /**
     * 
     */
    public String name = "";//名称
    /**
     * 
     */
    public String intro = "";//介绍
    /**
     * 
     */
    public String headImg = "";//头像
    /**
     * 
     */
    public int stre;//力量
    /**
     * 
     */
    public int agil;//敏捷
    /**
     * 
     */
    public int inte;//智力
    /**
     * 
     */
    public int maxHp;//最大血量
    /**
     * 
     */
    public int maxSp;//最大魔法值
    /**
     * 
     */
    public int lev;//等级
    /**
     * 
     */
    public int atk;//攻击
    /**
     * 
     */
    public int def;//防御
    /**
     * 
     */
    public int exp;//经验值
    /**
     * 
     */
    public int money;//金钱
    /**
     * 
     */
    public ArrayList<Skill> skillList = new ArrayList<Skill>();//技能列表
}
