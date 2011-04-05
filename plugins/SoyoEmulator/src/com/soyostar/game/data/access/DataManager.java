package com.soyostar.game.data.access;

import com.soyostar.emulator.engine.animation.Animation;
import com.soyostar.game.model.Config;
import com.soyostar.game.model.Enemy;
import com.soyostar.game.model.EnemyTroop;
import com.soyostar.game.model.Equip;
import com.soyostar.game.model.Item;
import com.soyostar.game.model.Player;
import com.soyostar.game.model.Skill;
import java.util.HashMap;

/**
 * 游戏数据管理器
 * @author wp_g4
 */
public class DataManager {

    private Config config = null;
    private Player player = null;
    private HashMap animations = null;
    private HashMap enemys = null;
    private HashMap enemyTroops = null;
    private HashMap items = null;
    private HashMap equips = null;
    private HashMap skills = null;
    private HashMap maps = null;

    //***************************************************************************************
    //Config
    //***************************************************************************************
    public Config getConfig() {
        return config;
    }

    //***************************************************************************************
    //Player
    //***************************************************************************************
    public Player getPlayer() {
        return player.getClone();
    }
    //***************************************************************************************
    //Animation
    //***************************************************************************************

    public Animation getAnimation(int index) {
        return (Animation) animations.get(index);
    }

    public Animation[] getAnimationList() {
        return (Animation[]) animations.values().toArray();
    }
    //***************************************************************************************
    //Enemy
    //***************************************************************************************

    public Enemy getEnemy(int index) {
        return (Enemy) enemys.get(index);
    }

    public Enemy[] getEnemyList() {
        return (Enemy[]) enemys.values().toArray();
    }

    //***************************************************************************************
    //EnemyTroop
    //***************************************************************************************
    public EnemyTroop getEnemyTroop(int index) {
        return (EnemyTroop) enemyTroops.get(index);
    }

    public EnemyTroop[] getEnemyTroop() {
        return (Enemy[]) enemys.values().toArray();
    }

    //***************************************************************************************
    //Enemy
    //***************************************************************************************
    public Enemy getEnemy(int index) {
        return (Enemy) enemys.get(index);
    }

    public Enemy[] getEnemyList() {
        return (Enemy[]) enemys.values().toArray();
    }

    //***************************************************************************************
    //Enemy
    //***************************************************************************************
    public Enemy getEnemy(int index) {
        return (Enemy) enemys.get(index);
    }

    public Enemy[] getEnemyList() {
        return (Enemy[]) enemys.values().toArray();
    }

    //***************************************************************************************
    //Enemy
    //***************************************************************************************
    public Enemy getEnemy(int index) {
        return (Enemy) enemys.get(index);
    }

    public Enemy[] getEnemyList() {
        return (Enemy[]) enemys.values().toArray();
    }

    private DataManager() {
    }
}
