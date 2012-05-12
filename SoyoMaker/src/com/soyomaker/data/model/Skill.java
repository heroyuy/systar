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
public class Skill extends Model {

    /**
     *
     */
    public static final String[] targets = {"无", "已单体", "已全体", "敌单体", "敌全体", "已单体（HP=0）", "已全体（HP=0）", "使用者", "其它"};
    /**
     *
     */
    public static final String[] limits = {"无限制", "仅菜单", "仅战斗", "不能使用", "其它"};
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
    public String icon = "";
    /**
     *  限制等级
     */
    public int lev;
    /**
     * 0 无 1 己单体 2 己全体 3 敌单体 4 敌全体 5 己单体(hp=0) 6 己全体(hp=0) 7 使用者 8 其它
     * 使用目标
     */
    public int target;
    /**
     * 0 无限制 1 仅菜单 2 仅战斗 3 不能使用	4 其它
     * 使用限制
     */
    public int limit;
    /**
     * 使用者动画
     */
    public int userAniIndex = -1;
    /**
     * 目标动画
     */
    public int targetAniIndex = -1;
    /**
     * 菜单使用时音效
     */
    public String menuUseSound = "";
    /**
     * 公共事件编号
     */
    public int eventIndex = -1;

    @Override
    public String toString() {
        return name + " ID:" + getIndex() + "";
    }

    /**
     *
     */
    public void addDefault() {
        Cost cost = new Cost();
        cost.costType = 0;
        cost.costName = "SP消耗";
        costs.add(cost);
        Effect effect = new Effect();
        effect.effectType = 0;
        effect.effectName = "基本伤害";
        effects.add(effect);
        Factor f0 = new Factor();
        f0.factorType = 0;
        f0.factorName = "攻击关系度";
        factors.add(f0);
        Factor f1 = new Factor();
        f1.factorType = 1;
        f1.factorName = "精神关系度";
        factors.add(f1);
        Factor f2 = new Factor();
        f2.factorType = 2;
        f2.factorName = "敏捷关系度";
        factors.add(f2);
        Factor f3 = new Factor();
        f3.factorType = 3;
        f3.factorName = "速度补正值";
        factors.add(f3);
        Factor f4 = new Factor();
        f4.factorType = 4;
        f4.factorName = "分散度";
        factors.add(f4);
        Factor f5 = new Factor();
        f5.factorType = 5;
        f5.factorName = "成功率";
        factors.add(f5);
    }
    /**
     * 0 Sp消耗
     * 消耗
     */
    public ArrayList<Cost> costs = new ArrayList<Cost>();
    /**
     * 效果
     */
    public ArrayList<Effect> effects = new ArrayList<Effect>();
    /**
     * 因素
     */
    public ArrayList<Factor> factors = new ArrayList<Factor>();
    /**
     * 属性
     */
    public ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    /**
     * 状态
     */
    public ArrayList<Status> status = new ArrayList<Status>();
}
