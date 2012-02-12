/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

/**
 * 因数
 * @author Administrator
 */
public class Factor {

    /**
     *
     */
    public static final String[] factors = {"攻击关系度", "精神关系度", "敏捷关系度", "速度补正值", "分散度", "命中率"};
    /**
     *
     */
    public int factorType;//因数类型 0 攻击关系度 1 精神关系度 2 敏捷关系度 3 速度补正值 4 分散度 5 命中率
    /**
     *
     */
    public String factorName = "";//因数名称
    /**
     *
     */
    public int factorValue;//因数值
}
