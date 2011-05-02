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
import com.soyostar.data.model.animation.Animation;
import com.soyostar.data.model.Config;
import com.soyostar.data.model.Enemy;
import com.soyostar.data.model.EnemyTroop;
import com.soyostar.data.model.Equip;
import com.soyostar.data.model.Item;
import com.soyostar.data.model.Player;
import com.soyostar.data.model.Skill;
import com.soyostar.data.model.map.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 游戏数据管理器
 * @author wp_g4
 */
public class DataManager {

    private HashMap<String, Dao> daos = null;

    //Config
    //***************************************************************************************
    public Config getConfig() {
        return ((ConfigDao) daos.get("Config")).getModel(0);
    }

    public void saveConfig(Config config) {
        ((ConfigDao) daos.get("Config")).saveModel(config);
    }

    public void delConfig() {
        ((ConfigDao) daos.get("Config")).delAllModel();
    }

    //Player
    //***************************************************************************************
    public Player getPlayer() {
        return ((PlayerDao) daos.get("Player")).getModel(0);
    }

    public void savePlayer(Player player) {
        ((PlayerDao) daos.get("Player")).saveModel(player);
    }

    public void delPlayer() {
        ((PlayerDao) daos.get("Player")).delAllModel();
    }
    //Animation
    //**************************************************************************

    public Animation getAnimation(int index) {
        return ((AnimationDao) daos.get("Animation")).getModel(index);
    }

    public Animation[] getAnimationList() {
        return ((AnimationDao) daos.get("Animation")).getModels();
    }

    public void saveAnimation(Animation animation) {
        ((AnimationDao) daos.get("Animation")).saveModel(animation);
    }

    public int allocateAnimationIndex() {
        return ((AnimationDao) daos.get("Animation")).allocateIndex();
    }

    public void delAnimation(int index) {
        ((AnimationDao) daos.get("Animation")).delModel(index);
    }

    public void delAllAnimation() {
        ((AnimationDao) daos.get("Animation")).delAllModel();
    }

    public int getAnimationSize() {
        return ((AnimationDao) daos.get("Animation")).size();
    }

    //Enemy
    //**************************************************************************
    public Enemy getEnemy(int index) {
        return ((EnemyDao) daos.get("Enemy")).getModel(index);
    }

    public Enemy[] getEnemyList() {
        return ((EnemyDao) daos.get("Enemy")).getModels();
    }

    public void saveEnemy(Enemy Enemy) {
        ((EnemyDao) daos.get("Enemy")).saveModel(Enemy);
    }

    public int allocateEnemyIndex() {
        return ((EnemyDao) daos.get("Enemy")).allocateIndex();
    }

    public void delEnemy(int index) {
        ((EnemyDao) daos.get("Enemy")).delModel(index);
    }

    public void delAllEnemy() {
        ((EnemyDao) daos.get("Enemy")).delAllModel();
    }

    public int getEnemySize() {
        return ((EnemyDao) daos.get("Enemy")).size();
    }
    //EnemyTroop
    //**************************************************************************

    public EnemyTroop getEnemyTroop(int index) {
        return ((EnemyTroopDao) daos.get("EnemyTroop")).getModel(index);
    }

    public EnemyTroop[] getEnemyTroopList() {
        return ((EnemyTroopDao) daos.get("EnemyTroop")).getModels();
    }

    public void saveEnemyTroop(EnemyTroop EnemyTroop) {
        ((EnemyTroopDao) daos.get("EnemyTroop")).saveModel(EnemyTroop);
    }

    public int allocateEnemyTroopIndex() {
        return ((EnemyTroopDao) daos.get("EnemyTroop")).allocateIndex();
    }

    public void delEnemyTroop(int index) {
        ((EnemyTroopDao) daos.get("EnemyTroop")).delModel(index);
    }

    public void delAllEnemyTroop() {
        ((EnemyTroopDao) daos.get("EnemyTroop")).delAllModel();
    }

    public int getEnemyTroopSize() {
        return ((EnemyTroopDao) daos.get("EnemyTroop")).size();
    }
    //Equip
    //**************************************************************************

    public Equip getEquip(int index) {
        return ((EquipDao) daos.get("Equip")).getModel(index);
    }

    public Equip[] getEquipList() {
        return ((EquipDao) daos.get("Equip")).getModels();
    }

    public void saveEquip(Equip Equip) {
        ((EquipDao) daos.get("Equip")).saveModel(Equip);
    }

    public int allocateEquipIndex() {
        return ((EquipDao) daos.get("Equip")).allocateIndex();
    }

    public void delEquip(int index) {
        ((EquipDao) daos.get("Equip")).delModel(index);
    }

    public void delAllEquip() {
        ((EquipDao) daos.get("Equip")).delAllModel();
    }

    public int getEquipSize() {
        return ((EquipDao) daos.get("Equip")).size();
    }
    //Item
    //**************************************************************************

    public Item getItem(int index) {
        return ((ItemDao) daos.get("Item")).getModel(index);
    }

    public Item[] getItemList() {
        return ((ItemDao) daos.get("Item")).getModels();
    }

    public void saveItem(Item Item) {
        ((ItemDao) daos.get("Item")).saveModel(Item);
    }

    public int allocateItemIndex() {
        return ((ItemDao) daos.get("Item")).allocateIndex();
    }

    public void delItem(int index) {
        ((ItemDao) daos.get("Item")).delModel(index);
    }

    public void delAllItem() {
        ((ItemDao) daos.get("Item")).delAllModel();
    }

    public int getItemSize() {
        return ((ItemDao) daos.get("Item")).size();
    }
    //Map
    //**************************************************************************

    public Map getMap(int index) {
        return ((MapDao) daos.get("Map")).getModel(index);
    }

    public Map[] getMapList() {
        return ((MapDao) daos.get("Map")).getModels();
    }

    public void saveMap(Map Map) {
        ((MapDao) daos.get("Map")).saveModel(Map);
    }

    public int allocateMapIndex() {
        return ((MapDao) daos.get("Map")).allocateIndex();
    }

    public void delMap(int index) {
        ((MapDao) daos.get("Map")).delModel(index);
    }

    public void delAllMap() {
        ((MapDao) daos.get("Map")).delAllModel();
    }

    public int getMapSize() {
        return ((MapDao) daos.get("Map")).size();
    }
    //Skill
    //**************************************************************************

    public Skill getSkill(int index) {
        return ((SkillDao) daos.get("Skill")).getModel(index);
    }

    public Skill[] getSkillList() {
        return ((SkillDao) daos.get("Skill")).getModels();
    }

    public void saveSkill(Skill Skill) {
        ((SkillDao) daos.get("Skill")).saveModel(Skill);
    }

    public int allocateSkillIndex() {
        return ((SkillDao) daos.get("Skill")).allocateIndex();
    }

    public void delSkill(int index) {
        ((SkillDao) daos.get("Skill")).delModel(index);
    }

    public void delAllSkill() {
        ((SkillDao) daos.get("Skill")).delAllModel();
    }

    public int getSkillSize() {
        return ((SkillDao) daos.get("Skill")).size();
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
        daos.put("Config", new ConfigDao());
        daos.put("Player", new PlayerDao());
        daos.put("Map", new MapDao());
        daos.put("Skill", new SkillDao());
        daos.put("Item", new ItemDao());
        daos.put("Equip", new EquipDao());
        daos.put("Animation", new AnimationDao());
        daos.put("Enemy", new EnemyDao());
        daos.put("EnemyTroop", new EnemyTroopDao());
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
