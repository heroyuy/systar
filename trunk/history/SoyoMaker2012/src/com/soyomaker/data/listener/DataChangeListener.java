/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.listener;

import com.soyomaker.data.model.Config;
import com.soyomaker.data.model.Enemy;
import com.soyomaker.data.model.EnemyTroop;
import com.soyomaker.data.model.Equip;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Player;
import com.soyomaker.data.model.Skill;
import com.soyomaker.data.model.Status;
import com.soyomaker.data.model.Vocation;
import java.util.EventListener;

/**
 *
 * @author Administrator
 */
public interface DataChangeListener extends EventListener {

    /**
     *
     * @param sce
     * @param config
     */
    public void configChanged(DataChangedEvent sce, Config config);

    /**
     *
     * @param sce
     * @param player
     */
    public void playerAdded(DataChangedEvent sce, Player player);

    /**
     *
     * @param sce
     * @param id
     */
    public void playerRemoved(DataChangedEvent sce, int id);

    /**
     *
     * @param sce
     * @param vocation
     */
    public void vocationAdded(DataChangedEvent sce, Vocation vocation);

    /**
     *
     * @param sce
     * @param id
     */
    public void vocationRemoved(DataChangedEvent sce, int id);

    /**
     *
     * @param sce
     * @param status
     */
    public void statusAdded(DataChangedEvent sce, Status status);

    /**
     *
     * @param sce
     * @param id
     */
    public void statusRemoved(DataChangedEvent sce, int id);

    /**
     *
     * @param sce
     * @param skill
     */
    public void skillAdded(DataChangedEvent sce, Skill skill);

    /**
     *
     * @param sce
     * @param id
     */
    public void skillRemoved(DataChangedEvent sce, int id);

    /**
     *
     * @param sce
     * @param enemy
     */
    public void enemyAdded(DataChangedEvent sce, Enemy enemy);

    /**
     *
     * @param sce
     * @param id
     */
    public void enemyRemoved(DataChangedEvent sce, int id);

    /**
     *
     * @param sce
     * @param enemytroop
     */
    public void enemyTroopAdded(DataChangedEvent sce, EnemyTroop enemytroop);

    /**
     *
     * @param sce
     * @param id
     */
    public void enemyTroopRemoved(DataChangedEvent sce, int id);

    /**
     *
     * @param sce
     * @param equip
     */
    public void equipAdded(DataChangedEvent sce, Equip equip);

    /**
     *
     * @param sce
     * @param id
     */
    public void equipRemoved(DataChangedEvent sce, int id);

    /**
     *
     * @param sce
     * @param item
     */
    public void itemAdded(DataChangedEvent sce, Item item);

    /**
     *
     * @param sce
     * @param id
     */
    public void itemRemoved(DataChangedEvent sce, int id);
}
