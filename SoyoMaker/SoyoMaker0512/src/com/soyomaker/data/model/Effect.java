/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

/**
 * 效果
 * @author Administrator
 */
public class Effect {

    /**
     *
     */
    public static final String[] effects = {"力量", "敏捷", "智力", "灵巧", "体力", "幸运", "生命值", "魔法值", "金币"};
    /**
     *
     */
    public int effectType;//效果类型 0 基本伤害值
    /**
     *
     */
    public String effectName = "";//效果名称
    /**
     *
     */
    public int effectValue;//效果值 12(纯数值) 12%(当前百分比) 12#（总量百分比）
}
