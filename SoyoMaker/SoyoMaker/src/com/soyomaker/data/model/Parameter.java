/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

/**
 * 参数
 * @author Administrator
 */
public class Parameter {

    /**
     *
     */
    public static final String[] types = {"力量", "智力", "敏捷", "灵巧", "体力", "运气"};
    /**
     *
     */
    public static final String[] rules = {"值", "总值百分比", "当前值百分比"};
    /**
     *
     */
    public int type;//参数类型 0 力量 1 智力 2 敏捷 3 灵巧 4 体力 5 运气
    /**
     *
     */
    public String name = "";//参数名称
    /**
     *
     */
    public int rule;//修改规则 0 值 1 总值百分比 2 当前值百分比
    /**
     *
     */
    public int value;//修改数值
}
