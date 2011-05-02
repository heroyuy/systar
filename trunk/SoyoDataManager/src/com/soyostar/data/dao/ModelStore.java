package com.soyostar.data.dao;

import com.soyostar.data.model.Config;
import com.soyostar.data.model.Enemy;
import com.soyostar.data.model.EnemyTroop;
import com.soyostar.data.model.Equip;
import com.soyostar.data.model.Item;
import com.soyostar.data.model.Model;
import com.soyostar.data.model.Player;
import com.soyostar.data.model.Skill;
import com.soyostar.data.model.animation.Animation;
import com.soyostar.data.model.map.Map;
import com.soyostar.util.Clone;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelStore<M extends Model> {

    private HashMap<Integer, M> data = null;
    private int availableIndex = 0;

    public ModelStore() {
        data = new HashMap<Integer, M>();
    }

    /**
     * 根据key获取数据仓库中的模型,并将此模型的拷贝通过model返回，拷贝为深层拷贝
     * @param key
     * @param model
     */
    public <T extends M> T getModel(Class<T> c, int key) {
        T t = null;
        try {
            M m = data.get(key);
            if (m != null) {
                t = c.newInstance();
                Clone.clone(m, t);
                t.setIndex(key);
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(ModelStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ModelStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public <T extends M> T[] getModels(Class<T> c) {
        int len = size();
        T[] ts = (T[]) Array.newInstance(c, len);

        Iterator<M> it = data.values().iterator();
        int index = 0;
        while (it.hasNext()) {
            try {
                ts[index] = c.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(ModelStore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ModelStore.class.getName()).log(Level.SEVERE, null, ex);
            }
            Clone.clone(it.next(), ts[index]);
        }
        return ts;
    }

    /**
     * 将指定的模型的深层拷贝保存到数据仓库
     * @param model
     */
    public void saveModel(M model) {
        M res = null;
        if (model instanceof Config) {
            res = (M) new Config();
        } else if (model instanceof Player) {
            res = (M) new Player();
        } else if (model instanceof Enemy) {
            res = (M) new Enemy();
        } else if (model instanceof EnemyTroop) {
            res = (M) new EnemyTroop();
        } else if (model instanceof Item) {
            res = (M) new Item();
        } else if (model instanceof Skill) {
            res = (M) new Skill();
        } else if (model instanceof Equip) {
            res = (M) new Equip();
        } else if (model instanceof Map) {
            res = (M) new Map();
        } else if (model instanceof Animation) {
            res = (M) new Animation();
        }
        if (res != null) {
            Clone.clone(model, res);
            res.setIndex(model.getIndex());
            data.put(res.getIndex(), res);
        }
    }

    public void delModel(int index) {
        data.remove(index);
    }

    public void delAllModel() {
        data.clear();
    }

    /**
     * 申请新的模型
     * @param <T> 模型类型
     * @param c
     * @return
     */
    public <T extends M> T newModel(Class<T> c) {
        T t = null;
        try {
            t = c.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(ModelStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ModelStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        int maxIndex = getMaxIndex();
        if (maxIndex >= availableIndex) {
            availableIndex = maxIndex + 1;
        }
        t.setIndex(availableIndex);
        availableIndex++;
        return t;
    }

    /**
     * 获取数据仓库中数据模型的编号的最大值
     * @return
     */
    private int getMaxIndex() {
        int max = -1;
        Iterator<M> it = data.values().iterator();
        while (it.hasNext()) {
            M m = it.next();
            if (m.getIndex() > max) {
                max = m.getIndex();
            }
        }
        return max;
    }

    /**
     * 获取数据仓库中模型的数量
     * @return
     */
    public int size() {
        return data.size();
    }
}
