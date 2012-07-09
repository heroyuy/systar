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
    public static final String[] types = {"主动技能", "被动技能"};
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
     * 技能类型
     * 0 主动技能 1 被动技能
     */
    public int type;
    /**
     * 限制等级
     */
    public int lev;
    /**
     * 消耗sp
     */
    public int sp;
    /**
     * 0 无 1 己单体 2 己全体 3 敌单体 4 敌全体 5 己单体(hp=0) 6 己全体(hp=0) 7 使用者 8 其它
     * 使用目标
     */
    public int target;
    /**
     * 使用者动画
     */
    public int userAniIndex = -1;
    /**
     * 目标动画
     */
    public int targetAniIndex = -1;
    /**
     * 攻击距离
     */
    public int attackDistance = 0;
    /**
     * 公共事件编号
     */
    public int eventIndex = -1;
    public int attributeId = -1;

    @Override
    public String toString() {
        return name + " ID:" + getIndex() + "";
    }
    /**
     * 状态
     */
    public ArrayList<Effect> effects = new ArrayList<Effect>();
    /**
     * 状态
     */
    public ArrayList<BuffInfo> status = new ArrayList<BuffInfo>();

    public static class BuffInfo {

        public Buff buff;
        public int rate;//状态有效度
    }
}
