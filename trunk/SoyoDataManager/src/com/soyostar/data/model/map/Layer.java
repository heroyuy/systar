/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.model.map;

import com.soyostar.data.model.Model;

/**
 *
 * @author Administrator
 */
public class Layer  extends Model{

    public static final byte TILELAYER = 0;
    public static final byte COLLIDELAYER = 1;
    public static final byte SPRITELAYER = 2;
    public byte type = 0;
    public int deepth = 0;//深度
}
