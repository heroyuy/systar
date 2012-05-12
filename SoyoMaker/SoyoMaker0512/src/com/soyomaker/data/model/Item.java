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
public class Item extends Treasure {

    /**
     *
     */
    public static final String[] targets = {"无", "已单体", "已全体", "敌单体", "敌全体", "已单体（HP=0）", "已全体（HP=0）", "使用者", "其它"};
    /**
     *
     */
    public static final String[] limits = {"无限制", "仅菜单", "仅战斗", "不能使用", "其它"};
    /**
     * 是否可消耗
     */
    public boolean consumable;
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

    /**
     *
     */
    public void addDefault() {
        Cost cost = new Cost();
        cost.costType = 0;
        cost.costName = "自身消耗";
        costs.add(cost);
        Effect e0 = new Effect();
        e0.effectType = 0;
        e0.effectName = "基本伤害值";
        Effect e1 = new Effect();
        e1.effectType = 1;
        e1.effectName = "HP恢复率";
        Effect e2 = new Effect();
        e2.effectType = 2;
        e2.effectName = "HP恢复量";
        Effect e3 = new Effect();
        e3.effectType = 3;
        e3.effectName = "SP恢复率";
        Effect e4 = new Effect();
        e4.effectType = 4;
        e4.effectName = "SP恢量复";
        Effect e5 = new Effect();
        e5.effectType = 5;
        e5.effectName = "HP上限";
        Effect e6 = new Effect();
        e6.effectType = 6;
        e6.effectName = "SP上限";
        Effect e7 = new Effect();
        e7.effectType = 7;
        e7.effectName = "攻击力";
        Effect e8 = new Effect();
        e8.effectType = 8;
        e8.effectName = "防御力";
        Effect e9 = new Effect();
        e9.effectType = 9;
        e9.effectName = "力量";
        Effect e10 = new Effect();
        e10.effectType = 10;
        e10.effectName = "智力";
        Effect e11 = new Effect();
        e11.effectType = 11;
        e11.effectName = "敏捷";
        Effect e12 = new Effect();
        e12.effectType = 12;
        e12.effectName = "体力";
        Effect e13 = new Effect();
        e13.effectType = 13;
        e13.effectName = "灵巧";
        Effect e14 = new Effect();
        e14.effectType = 14;
        e14.effectName = "运气";
        Effect e15 = new Effect();
        e15.effectType = 15;
        e15.effectName = "魔法攻击力";
        Effect e16 = new Effect();
        e16.effectType = 16;
        e16.effectName = "魔法防御力";
        effects.add(e0);
        effects.add(e1);
        effects.add(e2);
        effects.add(e3);
        effects.add(e4);
        effects.add(e5);
        effects.add(e6);
        effects.add(e7);
        effects.add(e8);
        effects.add(e9);
        effects.add(e10);
        effects.add(e11);
        effects.add(e12);
        effects.add(e13);
        effects.add(e14);
        effects.add(e15);
        effects.add(e16);
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
    }
    /**
     * 0 自身消耗
     * 消耗
     */
    public ArrayList<Cost> costs = new ArrayList<Cost>();
    /**
     * 0 基本伤害 1 hp回复量 2 hp回复率 3 sp回复量 4 sp回复率
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
