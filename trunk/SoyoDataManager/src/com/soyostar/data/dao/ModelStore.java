package com.soyostar.data.dao;

import com.soyostar.data.model.Config;
import com.soyostar.data.model.Model;
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
        if (model instanceof Config) {
            Config c = (Config) model;
            System.out.println(c.getIndex() + c.about);
        }
        Model m = Clone.clone(model);
        if (m instanceof Config) {
            Config c = (Config) m;
            System.out.println(c.getIndex() + c.about);
        }
        data.put(model.getIndex(), Clone.clone(model));
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
