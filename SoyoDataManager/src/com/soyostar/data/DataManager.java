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

    //***************************************************************************************
    //Config
    //***************************************************************************************
    public Config getConfig() {
        return ((ConfigDao) daos.get("config")).getModel(0);
    }

    public void saveConfig(Config config) {
        ((ConfigDao) daos.get("config")).saveModel(config);
    }

    public void delConfig() {
        ((ConfigDao) daos.get("config")).delAllModel();
    }

    //***************************************************************************************
    //Player
    //***************************************************************************************
    public Player getPlayer() {
        return ((PlayerDao) daos.get("player")).getModel(0);
    }

    public void savePlayer(Player player) {
        ((PlayerDao) daos.get("player")).saveModel(player);
    }

    public void delPlayer() {
        ((PlayerDao) daos.get("player")).delAllModel();
    }
    //***************************************************************************************
    //Animation
    //***************************************************************************************

    public Animation getAnimation(int index) {
        return ((AnimationDao) daos.get("animation")).getModel(index);
    }

    public Animation[] getAnimationList() {
        return ((AnimationDao) daos.get("animation")).getModels();
    }

    public void saveAnimation(Animation animation) {
        ((AnimationDao) daos.get("animation")).saveModel(animation);
    }

    public int allocateAnimationIndex() {
        return ((AnimationDao) daos.get("animation")).allocateIndex();
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
