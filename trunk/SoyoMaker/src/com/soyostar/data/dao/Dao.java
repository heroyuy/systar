package com.soyostar.data.dao;

import com.soyostar.data.model.Model;


/**
 * 
 * @author Administrator
 * @param <M>
 */
public abstract class Dao<M extends Model> extends ModelStore<M> {

    /**
     * 
     */
    public abstract void load();

    /**
     * 
     * @return
     */
    public abstract boolean save();
}
