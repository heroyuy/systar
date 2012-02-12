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
public class Event {

    /**
     *
     */
    public static final String[] conditionTypes = {"回合", "敌人", "角色", "开关", "变量"};
    /**
     *
     */
    public static final String[] eventTypes = {"战斗", "回合", "即时"};
    /**
     * 条件类型 0 回合 1 敌人 2 角色 3 开关	4 变量
     * 条件列表
     */
    public ArrayList<Condition> conditions = new ArrayList<Condition>();
    /**
     * 0 战斗 1 回合 2 即时
     * 事件类型
     */
    public int eventType;
    /**
     * 脚本ID
     */
    public int scriptIndex;
}
