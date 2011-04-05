/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Enemy;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class EnemyDao implements Dao {

    private HashMap enemys = null;

    public Enemy getEnemy(int index) {
        return (Enemy) enemys.get(index);
    }

    public Enemy[] getEnemyList() {
        return (Enemy[]) enemys.values().toArray();
    }

    public void load() {
    }

    public void save() {
    }
}
