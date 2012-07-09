/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.util.ArrayList;

/**
 * 状态
 * @author Administrator
 */
public class Buff extends Model {

    /**
     *
     */
    public static final String[] lastKinds = {"单次", "时间持续", "回合持续"};
    /**
     *
     */
    public static final String[] limitKinds = {"无限制", "不能够使用技能", "不能进行攻击"};

    @Override
    public String toString() {
        return name;
    }
    /**
     * 0 无限制 1 限制技能 2 限制攻击
     */
    public int limitType;
    /**
     *
     */
    public String name = "";
    /**
     *
     */
    public String description = "";
    /**
     * 状态图标
     */
    public String icon = "";
    /**
     * 状态等级
     */
    public int lev;
    /**
     * 动画编号
     */
    public int aniIndex = -1;
    /**
     * 持续类型 0 单次 1 时间持续	2 回合持续
     */
    public int type;
    /**
     * 分别对应 
     * 持续时间 持续时间 回合数
     */
    public int typeParam;
    /**
     * 参数列表
     */
    public ArrayList<Parameter> paras = new ArrayList<Parameter>();
    /**
     * 解除条件类型 0 战斗结束解除 1 受到伤害解除 2 回合解除
     * 条件列表
     */
    public ArrayList<Condition> conds = new ArrayList<Condition>();
}
