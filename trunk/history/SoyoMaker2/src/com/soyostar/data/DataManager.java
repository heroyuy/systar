package com.soyostar.data;

//import com.soyostar.data.dao.AnimationDao;
import com.soyostar.data.dao.ConfigDao;
import com.soyostar.data.dao.ConfigDao;
import com.soyostar.data.dao.Dao;
import com.soyostar.data.dao.Dao;
import com.soyostar.data.dao.EnemyDao;
import com.soyostar.data.dao.EnemyDao;
import com.soyostar.data.dao.EnemyTroopDao;
import com.soyostar.data.dao.EnemyTroopDao;
import com.soyostar.data.dao.EquipDao;
import com.soyostar.data.dao.EquipDao;
import com.soyostar.data.dao.ItemDao;
//import com.soyostar.data.dao.MapDao;
import com.soyostar.data.dao.ItemDao;
import com.soyostar.data.dao.PlayerDao;
import com.soyostar.data.dao.PlayerDao;
import com.soyostar.data.dao.SkillDao;
import com.soyostar.data.dao.SkillDao;
import com.soyostar.listener.DataChangeListener;
import com.soyostar.listener.DataChangedEvent;
import com.soyostar.data.model.Config;
import com.soyostar.data.model.Enemy;
import com.soyostar.data.model.EnemyTroop;
import com.soyostar.data.model.Equip;
import com.soyostar.data.model.Item;
import com.soyostar.data.model.Model;
import com.soyostar.data.model.Player;
import com.soyostar.data.model.Skill;
//import com.soyostar.data.model.animation.Animation;
//import com.soyostar.data.model.map.Map;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏数据管理器
 * @author wp_g4
 */
public class DataManager {

    private HashMap<Integer, Dao> daos = null;
    private Class[] modelClass = {
        Config.class,
        Player.class,
        Enemy.class,
        EnemyTroop.class,
        Equip.class,
        Item.class,
        Skill.class
    };
    /**
     * 
     */
    protected final List<DataChangeListener> dataChangeListeners = new LinkedList();

    /**
     * 
     * @param listener
     */
    public void addDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    /**
     * 
     * @param listener
     */
    public void removeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.remove(listener);
    }

    /**
     * Notifies all registered map change listeners about the addition of a
     * tileset.
     *
     * @param layer the new layer
     */
    protected void fireSkillAdded(Skill layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).skillAdded(event, layer);
        }
    }

    /**
     * Notifies all registered map change listeners about the removal of a
     * tileset.
     *
     * @param index the index of the removed tileset
     */
    protected void fireSkillRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).skillRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about the addition of a
     * tileset.
     *
     * @param layer the new layer
     */
    protected void fireEnemyAdded(Enemy layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).enemyAdded(event, layer);
        }
    }

    /**
     * Notifies all registered map change listeners about the removal of a
     * tileset.
     *
     * @param index the index of the removed tileset
     */
    protected void fireEnemyRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).enemyRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about the addition of a
     * tileset.
     *
     * @param layer the new layer
     */
    protected void fireEnemyTroopAdded(EnemyTroop layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).enemyTroopAdded(event, layer);
        }
    }

    /**
     * Notifies all registered map change listeners about the removal of a
     * tileset.
     *
     * @param index the index of the removed tileset
     */
    protected void fireEnemyTroopRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).enemyTroopRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about the addition of a
     * tileset.
     *
     * @param layer the new layer
     */
    protected void fireEquipAdded(Equip layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).equipAdded(event, layer);
        }
    }

    /**
     * Notifies all registered map change listeners about the removal of a
     * tileset.
     *
     * @param index the index of the removed tileset
     */
    protected void fireEquipRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).equipRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about the addition of a
     * tileset.
     *
     * @param layer the new layer
     */
    protected void fireItemAdded(Item layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).itemAdded(event, layer);
        }
    }

    /**
     * Notifies all registered map change listeners about the removal of a
     * tileset.
     *
     * @param index the index of the removed tileset
     */
    protected void fireItemRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).itemRemoved(event, index);
        }
    }

    /**
     * 
     * @param type
     * @param index
     * @return
     */
    public Model getModel(int type, int index) {
        return daos.get(type).getModel(index);
    }

    /**
     * 
     * @param type
     * @return
     */
    public Model[] getModels(int type) {
        return daos.get(type).getModels(modelClass[type]);
    }

    /**
     * 
     * @param type
     * @param model
     */
    public void saveModel(int type, Model model) {
        daos.get(type).saveModel(model);
        switch (type) {
            case Model.SKILL:
                fireSkillAdded((Skill) model);
                break;
            case Model.ENEMY:
                fireEnemyAdded((Enemy) model);
                break;
            case Model.ENEMYTROOP:
                fireEnemyTroopAdded((EnemyTroop) model);
                break;
            case Model.EQUIP:
                fireEquipAdded((Equip) model);
                break;
            case Model.ITEM:
                fireItemAdded((Item) model);
                break;
        }

    }

    /**
     * 
     * @param type
     * @param index
     */
    public void removeModel(int type, int index) {
        daos.get(type).removeModel(index);
        switch (type) {
            case Model.SKILL:
                fireSkillRemoved(index);
                break;
            case Model.ENEMY:
                fireEnemyRemoved(index);
                break;
            case Model.ENEMYTROOP:
                fireEnemyTroopRemoved(index);
                break;
            case Model.EQUIP:
                fireEquipRemoved(index);
                break;
            case Model.ITEM:
                fireItemRemoved(index);
                break;
        }
    }

    /**
     * 
     * @param type
     */
    public void removeAllModel(int type) {
        switch (type) {
            case Model.SKILL:
                for (int i = 0; i < daos.get(type).getModels(Skill.class).length; i++) {
                    Model model = daos.get(type).getModels(Skill.class)[i];
                    removeModel(Model.SKILL, model.getIndex());
                }
                break;
            case Model.ENEMY:
                for (int i = 0; i < daos.get(type).getModels(Enemy.class).length; i++) {
                    Model model = daos.get(type).getModels(Enemy.class)[i];
                    removeModel(Model.ENEMY, model.getIndex());
                }
                break;
            case Model.ENEMYTROOP:
                for (int i = 0; i < daos.get(type).getModels(EnemyTroop.class).length; i++) {
                    Model model = daos.get(type).getModels(EnemyTroop.class)[i];
                    removeModel(Model.ENEMYTROOP, model.getIndex());
                }
                break;
            case Model.EQUIP:
                for (int i = 0; i < daos.get(type).getModels(Equip.class).length; i++) {
                    Model model = daos.get(type).getModels(Equip.class)[i];
                    removeModel(Model.EQUIP, model.getIndex());
                }
                break;
            case Model.ITEM:
                for (int i = 0; i < daos.get(type).getModels(Item.class).length; i++) {
                    Model model = daos.get(type).getModels(Item.class)[i];
                    removeModel(Model.ITEM, model.getIndex());
                }
                break;
            case Model.PLAYER:
                for (int i = 0; i < daos.get(type).getModels(Player.class).length; i++) {
                    Model pmodel = daos.get(type).getModels(Player.class)[i];
                    removeModel(Model.PLAYER, pmodel.getIndex());
                }

                break;
            case Model.CONFIG:
                for (int i = 0; i < daos.get(type).getModels(Config.class).length; i++) {
                    Model cmodel = daos.get(type).getModels(Config.class)[i];
                    removeModel(Model.CONFIG, cmodel.getIndex());
                }

                break;
        }
//        daos.get(type).removeAllModel();
    }

    /**
     * 
     */
    public void clearAllData() {
        removeAllModel(Model.CONFIG);
        removeAllModel(Model.ENEMYTROOP);
        removeAllModel(Model.ENEMY);
        removeAllModel(Model.EQUIP);
        removeAllModel(Model.ITEM);
        removeAllModel(Model.SKILL);
        removeAllModel(Model.PLAYER);
    }

    /**
     * 
     * @param type
     * @return
     */
    public int size(int type) {
        return daos.get(type).size();
    }

    /**
     * 
     * @param type
     * @return
     */
    public int getMaxIndex(int type) {
        return daos.get(type).getMaxIndex();
    }

    /**
     * 
     * @param type
     * @return
     */
    public int allocateIndex(int type) {
        return daos.get(type).allocateIndex();
    }
//    private static DataManager instance = null;
//
//    public static DataManager getInstance() {
//        if (instance == null) {
//            instance = new DataManager();
//        }
//        return instance;
//    }

//    public void reset() {
//    }

    /**
     * 
     */
    public DataManager() {
        daos = new HashMap<Integer, Dao>();
        daos.put(Model.CONFIG, new ConfigDao());
        daos.put(Model.PLAYER, new PlayerDao());
        daos.put(Model.SKILL, new SkillDao());
        daos.put(Model.ITEM, new ItemDao());
        daos.put(Model.EQUIP, new EquipDao());
        daos.put(Model.ENEMY, new EnemyDao());
        daos.put(Model.ENEMYTROOP, new EnemyTroopDao());
    }

    /**
     * 
     * @param path
     */
    public void init(String path) {
        if (path.endsWith(File.separator)) {
            path = path.substring(0, path.length() - 1);
        }
        this.path = path;
//        System.out.println("ppppppppppp:" + path);
//        reset();
        //顺序读取
        daos.get(Model.CONFIG).load();
        daos.get(Model.PLAYER).load();
        daos.get(Model.SKILL).load();
        daos.get(Model.ITEM).load();
        daos.get(Model.EQUIP).load();
        daos.get(Model.ENEMY).load();
        daos.get(Model.ENEMYTROOP).load();
        
//        Iterator i = daos.values().iterator();
//        while (i.hasNext()) {
//            Dao dao = (Dao) i.next();
//            dao.load();
//        }
    }

    /**
     * 
     * @return
     */
    public boolean save() {
        Iterator i = daos.values().iterator();
        boolean res = true;
        while (i.hasNext()) {
            Dao dao = (Dao) i.next();
            res = res && dao.save();
        }
        return res;
    }
    private String path = "测试/data";

    /**
     * 
     * @return
     */
    public String getPath() {
        return path;
    }
}
