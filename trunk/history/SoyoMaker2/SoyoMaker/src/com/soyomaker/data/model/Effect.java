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
    public static final String[] effects = {"基本伤害值", "HP回复量", "HP回复率", "SP回复量", "SP回复率"};
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
    public int effectValue;//效果值
}
