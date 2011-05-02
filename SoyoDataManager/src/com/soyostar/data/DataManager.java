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
import com.soyostar.data.model.Model;
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

    private HashMap<Integer, Dao> daos = null;

    public Model getModel(int type, int index) {
        return daos.get(type).getModel(index);
    }

    public Model[] getModels(int type) {
        return daos.get(type).getModels();
    }

    public void saveModel(int type, Model model) {
        daos.get(type).saveModel(model);
    }

    public void delModel(int type, int index) {
        daos.get(type).delModel(index);
    }

    public void delAllModel(int type) {
        daos.get(type).delAllModel();
    }

    public int size(int type) {
        return daos.get(type).size();
    }

    public int getMaxIndex(int type) {
        return daos.get(type).getMaxIndex();
    }

    public int allocateIndex(int type) {
        return daos.get(type).allocateIndex();
    }
    private static DataManager instance = null;

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private DataManager() {
        daos = new HashMap<Integer, Dao>();
        daos.put(Model.CONFIG, new ConfigDao());
        daos.put(Model.PLAYER, new PlayerDao());
        daos.put(Model.PLAYER, new MapDao());
        daos.put(Model.SKILL, new SkillDao());
        daos.put(Model.ITEM, new ItemDao());
        daos.put(Model.EQUIP, new EquipDao());
        daos.put(Model.ANIMATION, new AnimationDao());
        daos.put(Model.ENEMY, new EnemyDao());
        daos.put(Model.ENEMYTROOP, new EnemyTroopDao());
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
