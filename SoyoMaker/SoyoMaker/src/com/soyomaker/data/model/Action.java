/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.util.ArrayList;

/**
 * 动作
 * @author Administrator
 */
public class Action {

    /**
     *
     */
    public static final int USE_ITEM = 3;
    /**
     *
     */
    public static final int USE_SKILL = 4;
    /**
     *
     */
    public static final String[] types = {"静止", "普通攻击", "防御", "使用物品", "使用技能", "逃跑"};
    /**
     * 0 静止 1 普通攻击 2 防御	3 使用物品 5 使用技能 6 逃跑
     * 动作类型
     */
    public int actionType;
    /**
     * 0 回合 1 HP 2 等级 3 开关 4 变量
     * 条件列表
     */
    public ArrayList<Condition> conds = new ArrayList<Condition>();
    /**
     * 参数列表
     */
    public ArrayList<Integer> paras = new ArrayList<Integer>();
    /**
     * 触发概率
     */
    public int rate;
}
