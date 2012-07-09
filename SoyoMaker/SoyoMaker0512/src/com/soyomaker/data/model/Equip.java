/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 装备
 * @author Administrator
 */
public class Equip extends Treasure {

    /**
     *
     */
    public static final String[] kinds = {"头盔", "饰品", "武器", "盾牌", "铠甲", "战靴"};
    /**
     *
     */
    public static final int HELM = 0;
    /**
     *
     */
    public static final int JEWELRY = 1;
    /**
     *
     */
    public static final int WEAPON = 2;
    /**
     *
     */
    public static final int SHIELD = 3;
    /**
     *
     */
    public static final int ARMOUR = 4;
    /**
     *
     */
    public static final int BOOTS = 5;
    /**
     * 0 头盔 1 饰品 2 武器 3 盾牌 4 铠甲	5 战靴
     * 装备类型
     */
    public int equipType;
    /**
     * 使用者动画
     */
    public int userAniIndex = -1;
    /**
     * 目标动画
     */
    public int targetAniIndex = -1;

    /**
     *
     */
    public Equip() {
    }

    /**
     *
     */
    public void addDefault() {
        Effect e0 = new Effect();
        e0.effectType = 0;
        e0.effectName = "攻击力";
        Effect e1 = new Effect();
        e1.effectType = 1;
        e1.effectName = "防御力";
        Effect e2 = new Effect();
        e2.effectType = 2;
        e2.effectName = "命中率";
        Effect e3 = new Effect();
        e3.effectType = 3;
        e3.effectName = "回避率";
        Effect e4 = new Effect();
        e4.effectType = 4;
        e4.effectName = "力量";
        Effect e5 = new Effect();
        e5.effectType = 5;
        e5.effectName = "智力";
        Effect e6 = new Effect();
        e6.effectType = 6;
        e6.effectName = "敏捷";
        Effect e7 = new Effect();
        e7.effectType = 7;
        e7.effectName = "体力";
        Effect e8 = new Effect();
        e8.effectType = 8;
        e8.effectName = "灵巧";
        Effect e9 = new Effect();
        e9.effectType = 9;
        e9.effectName = "运气";
        Effect e10 = new Effect();
        e10.effectType = 10;
        e10.effectName = "魔法攻击力";
        Effect e11 = new Effect();
        e11.effectType = 11;
        e11.effectName = "魔法防御力";
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
    }
    /**
     * 效果
     */
    public ArrayList<Effect> effects = new ArrayList<Effect>();
    /**
     * 属性
     */
    public ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    /**
     * 状态
     */
    public ArrayList<Buff> status = new ArrayList<Buff>();
}
