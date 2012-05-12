/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

/**
 * 属性
 * @author Administrator
 */
public class Attribute {

    @Override
    public String toString() {
        return name;
    }
    /**
     *
     */
    public int id;
    /**
     *
     */
    public String name = "";
    /**
     *
     */
    public String description = "";
    /**
     * 属性效果值
     */
    public int value;
}
