package com.soyostar.data.dao;

import com.soyostar.data.model.Model;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ModelStore<M extends Model> {

    private Map<Integer, M> data = null;
    private int availableIndex = 0;

    public ModelStore() {
        data = new HashMap<Integer, M>();
    }

    /**
     * 获取指定编号的模型
     * @param index
     * @return
     */
    public M getModel(int index) {
        return data.get(index);
    }

    /**
     * 获取所有模型
     * @return
     */
    public M[] getModels(Class<M> c) {
        M[] ms = (M[]) Array.newInstance(c, data.size());
        int i = 0;
        for (M m : data.values()) {
            ms[i++] = m;
        }
        return ms;

    }

    /**
     * 保存模型
     * @param model
     */
    public void saveModel(M model) {
        data.put(model.getIndex(), model);
    }

    /**
     * 删除模型
     * @param index
     */
    public void delModel(int index) {
        data.remove(index);
    }

    /**
     * 删除所有模型
     */
    public void delAllModel() {
        data.clear();
    }

    /**
     * 分配正确的模型编号
     * @return
     */
    public int allocateIndex() {
        int max = getMaxIndex();
        if (max >= availableIndex) {
            availableIndex = max + 1;
        }
        return availableIndex++;
    }

    public int getMaxIndex() {
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
