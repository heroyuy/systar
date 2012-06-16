/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.model;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class EnemyTroop extends Model {

    @Override
    public String toString() {
        return name + " ID:" + getIndex() + "";
    }
    /**
     * 队伍名称
     */
    public String name = "";
    /**
     * 敌人列表
     */
    public ArrayList<Enemy> enemys = new ArrayList<Enemy>();
    /**
     * 位置列表
     */
    public ArrayList<Point> points = new ArrayList<Point>();
}
