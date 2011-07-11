/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.listener;

import com.soyostar.listener.DataChangedEvent;
import com.soyostar.data.model.Enemy;
import com.soyostar.data.model.EnemyTroop;
import com.soyostar.data.model.Equip;
import com.soyostar.data.model.Item;
import com.soyostar.data.model.Skill;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface DataChangeListener extends EventListener {

    /**
     * 
     * @param sce
     * @param skill
     */
    void skillAdded(DataChangedEvent sce, Skill skill);

    /**
     * 
     * @param sce
     * @param id
     */
    void skillRemoved(DataChangedEvent sce, int id);

    /**
     * 
     * @param sce
     * @param enemy
     */
    void enemyAdded(DataChangedEvent sce, Enemy enemy);

    /**
     * 
     * @param sce
     * @param id
     */
    void enemyRemoved(DataChangedEvent sce, int id);

    /**
     * 
     * @param sce
     * @param enemytroop
     */
    void enemyTroopAdded(DataChangedEvent sce, EnemyTroop enemytroop);

    /**
     * 
     * @param sce
     * @param id
     */
    void enemyTroopRemoved(DataChangedEvent sce, int id);

    /**
     * 
     * @param sce
     * @param equip
     */
    void equipAdded(DataChangedEvent sce, Equip equip);

    /**
     * 
     * @param sce
     * @param id
     */
    void equipRemoved(DataChangedEvent sce, int id);

    /**
     * 
     * @param sce
     * @param item
     */
    void itemAdded(DataChangedEvent sce, Item item);

    /**
     * 
     * @param sce
     * @param id
     */
    void itemRemoved(DataChangedEvent sce, int id);
}
