/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.data.model.Model;

/**
 *
 * @author Administrator
 * @param <M>
 */
public abstract class Dao<M extends Model> extends ModelPool<M> {

    /**
     *
     */
    public abstract void load();

    /**
     *
     * @return 是否保存成功
     */
    public abstract boolean save();
}
