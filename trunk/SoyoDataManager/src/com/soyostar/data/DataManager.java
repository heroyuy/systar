package com.soyostar.data;

import com.soyostar.data.dao.AnimationDao;
import com.soyostar.data.dao.ConfigDao;
import com.soyostar.data.dao.Dao;
import com.soyostar.data.dao.EnemyDao;
import com.soyostar.data.dao.EnemyTroopDao;
import com.soyostar.data.dao.EquipDao;
import com.soyostar.data.dao.ItemDao;
import com.soyostar.data.dao.MapDao;
import com.soyostar.data.dao.PlayerDao;
import com.soyostar.data.dao.SkillDao;
import com.soyostar.data.model.Animation;
import com.soyostar.data.model.Config;
import com.soyostar.data.model.Enemy;
import com.soyostar.data.model.EnemyTroop;
import com.soyostar.data.model.Equip;
import com.soyostar.data.model.Item;
import com.soyostar.data.model.Map;
import com.soyostar.data.model.Player;
import com.soyostar.data.model.Skill;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 游戏数据管理器
 * @author wp_g4
 */
public class DataManager {

    private HashMap<String, Dao> daos = null;

    //***************************************************************************************
    //Config
    //***************************************************************************************
    public <T extends Config> T getConfig(Class<T> c) {
        return ((ConfigDao) daos.get("config")).getModel(c, 0);
    }

    public <T extends Config> void saveConfig(T t) {
        ((ConfigDao) daos.get("config")).saveModel(t);
    }

    public void delConfig() {
        ((ConfigDao) daos.get("config")).delAllModel();
    }

    //***************************************************************************************
    //Player
    //***************************************************************************************
    public <T extends Player> T getPlayer(Class<T> c) {
        return ((PlayerDao) daos.get("player")).getModel(c, 0);
    }

    public <T extends Player> void savePlayer(T t) {
        ((PlayerDao) daos.get("player")).saveModel(t);
    }

    public void delPlayer() {
        ((PlayerDao) daos.get("player")).delAllModel();
    }
    //***************************************************************************************
    //Animation
    //***************************************************************************************

    public <T extends Animation> T getAnimation(Class<T> c, int index) {
        return ((AnimationDao) daos.get("animation")).getModel(c, index);
    }

    public <T extends Animation> T[] getAnimationList(Class<T> c) {
        return ((AnimationDao) daos.get("animation")).getModels(c);
    }

    public <T extends Animation> void saveAnimation(T t) {
        ((AnimationDao) daos.get("animation")).saveModel(t);
    }

    public <T extends Animation> T newAnimation(Class<T> c) {
        return ((AnimationDao) daos.get("animation")).newModel(c);
    }

    public void delAnimation(int index) {
        ((AnimationDao) daos.get("animation")).delModel(index);
    }

    public void delAllAnimation() {
        ((AnimationDao) daos.get("animation")).delAllModel();
    }

    public int getAnimationSize() {
        return ((AnimationDao) daos.get("animation")).size();
    }

    //***************************************************************************************
    //Enemy
    //***************************************************************************************
    public <T extends Enemy> T getEnemy(Class<T> c, int index) {
        return ((EnemyDao) daos.get("enemy")).getModel(c, index);
    }

    public <T extends Enemy> T[] getEnemyList(Class<T> c) {
        return ((EnemyDao) daos.get("enemy")).getModels(c);
    }

    public <T extends Enemy> void saveEnemy(T t) {
        ((EnemyDao) daos.get("enemy")).saveModel(t);
    }

    public <T extends Enemy> T newEnemy(Class<T> c) {
        return ((EnemyDao) daos.get("enemy")).newModel(c);
    }

    public void delEnemy(int index) {
        ((EnemyDao) daos.get("enemy")).delModel(index);
    }

    public void delAllEnemy() {
        ((EnemyDao) daos.get("enemy")).delAllModel();
    }

    public int getEnemySize() {
        return ((EnemyDao) daos.get("enemy")).size();
    }
    //***************************************************************************************
    //EnemyTroop
    //***************************************************************************************

    public <T extends EnemyTroop> T getEnemyTroop(Class<T> c, int index) {
        return ((EnemyTroopDao) daos.get("enemyTroop")).getModel(c, index);
    }

    public <T extends EnemyTroop> T[] getEnemyTroopList(Class<T> c) {
        return ((EnemyTroopDao) daos.get("enemyTroop")).getModels(c);
    }

    public <T extends EnemyTroop> void saveEnemyTroop(T t) {
        ((EnemyTroopDao) daos.get("enemyTroop")).saveModel(t);
    }

    public <T extends EnemyTroop> T newEnemyTroop(Class<T> c) {
        return ((EnemyTroopDao) daos.get("enemyTroop")).newModel(c);
    }

    public void delEnemyTroop(int index) {
        ((EnemyTroopDao) daos.get("enemyTroop")).delModel(index);
    }

    public void delAllEnemyTroop() {
        ((EnemyTroopDao) daos.get("enemyTroop")).delAllModel();
    }

    public int getEnemyTroopSize() {
        return ((EnemyTroopDao) daos.get("enemyTroop")).size();
    } 
    //***************************************************************************************
    //Equip
    //***************************************************************************************

    public <T extends Equip> T getEquip(Class<T> c, int index) {
        return ((EquipDao) daos.get("equip")).getModel(c, index);
    }

    public <T extends Equip> T[] getEquipList(Class<T> c) {
        return ((EquipDao) daos.get("equip")).getModels(c);
    }

    public <T extends Equip> void saveEquip(T t) {
        ((EquipDao) daos.get("equip")).saveModel(t);
    }

    public <T extends Equip> T newEquip(Class<T> c) {
        return ((EquipDao) daos.get("equip")).newModel(c);
    }

    public void delEquip(int index) {
        ((EquipDao) daos.get("equip")).delModel(index);
    }

    public void delAllEquip() {
        ((EquipDao) daos.get("equip")).delAllModel();
    }

    public int getEquipSize() {
        return ((EquipDao) daos.get("equip")).size();
    } 
    //***************************************************************************************
    //Item
    //***************************************************************************************

    public <T extends Item> T getItem(Class<T> c, int index) {
        return ((ItemDao) daos.get("item")).getModel(c, index);
    }

    public <T extends Item> T[] getItemList(Class<T> c) {
        return ((ItemDao) daos.get("item")).getModels(c);
    }

    public <T extends Item> void saveItem(T t) {
        ((ItemDao) daos.get("item")).saveModel(t);
    }

    public <T extends Item> T newItem(Class<T> c) {
        return ((ItemDao) daos.get("item")).newModel(c);
    }

    public void delItem(int index) {
        ((ItemDao) daos.get("item")).delModel(index);
    }

    public void delAllItem() {
        ((ItemDao) daos.get("item")).delAllModel();
    }

    public int getItemSize() {
        return ((ItemDao) daos.get("item")).size();
    } 
    //***************************************************************************************
    //Skill
    //***************************************************************************************

    public <T extends Skill> T getSkill(Class<T> c, int index) {
        return ((SkillDao) daos.get("skill")).getModel(c, index);
    }

    public <T extends Skill> T[] getSkillList(Class<T> c) {
        return ((SkillDao) daos.get("skill")).getModels(c);
    }

    public <T extends Skill> void saveSkill(T t) {
        ((SkillDao) daos.get("skill")).saveModel(t);
    }

    public <T extends Skill> T newSkill(Class<T> c) {
        return ((SkillDao) daos.get("skill")).newModel(c);
    }

    public void delSkill(int index) {
        ((SkillDao) daos.get("skill")).delModel(index);
    }

    public void delAllSkill() {
        ((SkillDao) daos.get("skill")).delAllModel();
    }

    public int getSkillSize() {
        return ((SkillDao) daos.get("skill")).size();
    }
    //***************************************************************************************
    //Map
    //***************************************************************************************

    public <T extends Map> T getMap(Class<T> c, int index) {
        return ((MapDao) daos.get("map")).getModel(c, index);
    }

    public <T extends Map> T[] getMapList(Class<T> c) {
        return ((MapDao) daos.get("map")).getModels(c);
    }

    public <T extends Map> void saveMap(T t) {
        ((MapDao) daos.get("map")).saveModel(t);
    }

    public <T extends Map> T newMap(Class<T> c) {
        return ((MapDao) daos.get("map")).newModel(c);
    }

    public void delMap(int index) {
        ((MapDao) daos.get("map")).delModel(index);
    }

    public void delAllMap() {
        ((MapDao) daos.get("map")).delAllModel();
    }

    public int getMapSize() {
        return ((MapDao) daos.get("map")).size();
    }
    private static DataManager instance = null;

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private DataManager() {
        daos = new HashMap<String, Dao>();
        daos.put("config", new ConfigDao());
        daos.put("player", new PlayerDao());
        daos.put("map", new MapDao());
        daos.put("skill", new SkillDao());
        daos.put("item", new ItemDao());
        daos.put("equip", new EquipDao());
//        daos.put("animation", new AnimationDao());
        daos.put("enemy", new EnemyDao());
        daos.put("enemyTroop", new EnemyTroopDao());
    }

    public void init(String path) {
        if (path.endsWith("\\") || path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        this.path = path;

        Iterator i = daos.values().iterator();
        while (i.hasNext()) {
            Dao dao = (Dao) i.next();
            dao.load();
        }
    }

    public void save() {
        Iterator i = daos.values().iterator();
        while (i.hasNext()) {
            Dao dao = (Dao) i.next();
            dao.save();
        }
    }
    private String path = "D:/WorkSpace/netbeans/SoyoDataManager/res";

    public String getPath() {
        return path;
    }
}
