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
public class Status extends Model {

    /**
     *
     */
    public static final String[] kinds = {"限制型", "持续型"};
    /**
     *
     */
    public static final String[] lastKinds = {"单次", "时间持续", "回合持续"};
    /**
     *
     */
    public static final String[] limitKinds = {"无限制", "不能够使用魔法", "普通攻击敌人", "普通攻击同伴", "不能行动", "不能行动和回避"};
    /**
     *
     */
    public static final int LIMIT = 0;
    /**
     *
     */
    public static final int LAST = 1;

    @Override
    public String toString() {
        return name;
    }
    /**
     * 参数类型 0 限制型 1 持续型
     */
    public int type;
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
     * 限制类型 0 无限制 1 不能够使用魔法 2 普通攻击敌人 3 普通攻击同伴 4 不能行动 5 不能行动和回避
     */
    public int lastType;
    /**
     * 持续值
     */
    public int lastValue;
    /**
     * 状态效果值
     */
    public int value;
    /**
     * 参数列表
     */
    public ArrayList<Parameter> paras = new ArrayList<Parameter>();
    /**
     * 解除条件类型 0 战斗结束解除 1 受到伤害解除 2 回合解除
     * 条件列表
     */
    public ArrayList<Condition> conds = new ArrayList<Condition>();
    /**
     * 属性列表
     */
    public ArrayList<Attribute> attrs = new ArrayList<Attribute>();
    /**
     * 状态列表
     */
    public ArrayList<Status> status = new ArrayList<Status>();
}
