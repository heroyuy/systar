package com.soyostar.game.db;

import com.soyostar.emulator.engine.animation.Animation;
import com.soyostar.game.model.Config;
import com.soyostar.game.model.Enemy;
import com.soyostar.game.model.EnemyTroop;
import com.soyostar.game.model.Equip;
import com.soyostar.game.model.Item;
import com.soyostar.game.model.Map;
import com.soyostar.game.model.Player;
import com.soyostar.game.model.Skill;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 游戏数据管理器
 * @author wp_g4
 */
public class DataManager {

    private HashMap daos = null;

    //***************************************************************************************
    //Config
    //***************************************************************************************
    public Config getConfig() {
        return ((ConfigDao) daos.get("config")).getConfig();
    }

    //***************************************************************************************
    //Player
    //***************************************************************************************
    public Player getPlayer() {
        return ((PlayerDao) daos.get("player")).getPlayer();
    }
    //***************************************************************************************
    //Animation
    //***************************************************************************************

    public Animation getAnimation(int index) {
        return ((AnimationDao) daos.get("animation")).getAnimation(index);
    }

    public Animation[] getAnimationList() {
        return ((AnimationDao) daos.get("animation")).getAnimationList();
    }
    //***************************************************************************************
    //Enemy
    //***************************************************************************************

    public Enemy getEnemy(int index) {
        return ((EnemyDao) daos.get("enemy")).getEnemy(index);
    }

    public Enemy[] getEnemyList() {
        return ((EnemyDao) daos.get("enemy")).getEnemyList();
    }

    //***************************************************************************************
    //EnemyTroop
    //***************************************************************************************
    public EnemyTroop getEnemyTroop(int index) {
        return ((EnemyTroopDao) daos.get("enemyTroop")).getEnemyTroop(index);
    }

    public EnemyTroop[] getEnemyTroopList() {
        return ((EnemyTroopDao) daos.get("enemyTroop")).getEnemyTroopList();
    }
    //***************************************************************************************
    //Skill
    //***************************************************************************************

    public Skill getSkill(int index) {
        return ((SkillDao) daos.get("skill")).getSkill(index);
    }

    public Skill[] getSkillList() {
        return ((SkillDao) daos.get("skill")).getSkillList();
    }
    //***************************************************************************************
    //Item
    //***************************************************************************************

    public Item getItem(int index) {
        return ((ItemDao) daos.get("item")).getItem(index);
    }

    public Item[] getItemList() {
        return ((ItemDao) daos.get("item")).getItemList();
    }
    //***************************************************************************************
    //Equip
    //***************************************************************************************

    public Equip getEquip(int index) {
        return ((EquipDao) daos.get("equip")).getEquip(index);
    }

    public Equip[] getEquipList() {
        return ((EquipDao) daos.get("equip")).getEquipList();
    }
    //***************************************************************************************
    //Map
    //***************************************************************************************

    public Map getMap(int index) {
        return ((MapDao) daos.get("map")).getMap(index);
    }

    public Map[] getMapList() {
        return ((MapDao) daos.get("map")).getMapList();
    }

    private DataManager() {
        daos = new HashMap();
        daos.put("config", new ConfigDao());
        daos.put("player", new PlayerDao());
        daos.put("map", new MapDao());
        daos.put("skill", new SkillDao());
        daos.put("item", new ItemDao());
        daos.put("equip", new EquipDao());
        daos.put("animation", new AnimationDao());
        daos.put("enemy", new EnemyDao());
        daos.put("enemyTroop", new EnemyTroopDao());
    }

    public void init() {
        Iterator i = daos.values().iterator();
        while (i.hasNext()) {
            Dao dao = (Dao) i.next();
            dao.load();
        }
    }

    public void flush() {
        Iterator i = daos.values().iterator();
        while (i.hasNext()) {
            Dao dao = (Dao) i.next();
            dao.save();
        }
    }
}
