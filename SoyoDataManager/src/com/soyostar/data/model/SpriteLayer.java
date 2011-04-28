/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.model;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class SpriteLayer extends Model  {

    public byte type;
    public int x;
    public int y;
    public int width;
    public int height;
    public ArrayList<Module> modules = new ArrayList<Module>();
}
