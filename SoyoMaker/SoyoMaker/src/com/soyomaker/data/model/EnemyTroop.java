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
public class EnemyTroop extends Model {

    /**
     * 队伍名称
     */
    public String name = "";
    /**
     * 敌人列表
     */
    public ArrayList<Enemy> enemys = new ArrayList<Enemy>();
    /**
     * 事件列表
     */
    public ArrayList<Event> events = new ArrayList<Event>();
}
