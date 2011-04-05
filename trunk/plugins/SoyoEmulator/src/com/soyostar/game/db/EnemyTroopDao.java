/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.EnemyTroop;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class EnemyTroopDao implements Dao {

    private HashMap enemyTroops = null;

    public EnemyTroop getEnemyTroop(int index) {
        return (EnemyTroop) enemyTroops.get(index);
    }

    public EnemyTroop[] getEnemyTroopList() {
        return (EnemyTroop[]) enemyTroops.values().toArray();
    }

    public void load() {
    }

    public void save() {
    }
}
