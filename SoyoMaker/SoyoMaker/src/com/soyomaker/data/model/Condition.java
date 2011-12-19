/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.util.ArrayList;

/**
 * 条件
 * @author Administrator
 */
public class Condition {

    /**
     *
     */
    public static final String[] statusConditionTypes = {"战斗结束解除", "受到伤害解除", "回合解除"};
    /**
     *
     */
    public static final String[] actionConditionTypes = {"回合", "HP", "等级", "开关", "变量"};
    /**
     *
     */
    public static final String[] actionOperateTypes = {"大于", "小于", "等于", "大于等于", "大于小于", "不等于"};
    /**
     * 条件类型
     */
    public int conditionType;
    /**
     * 条件名称
     */
    public String conditionName = "";
    /**
     * 参数列表
     */
    public ArrayList<Integer> paras = new ArrayList<Integer>();
    /**
     * 唯一参数
     */
    public int para;
}
