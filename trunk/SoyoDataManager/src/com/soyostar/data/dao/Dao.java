package com.soyostar.data.dao;

import com.soyostar.data.model.Model;

public abstract class Dao<M extends Model> extends ModelStore<M> {

    public abstract void load();

    public abstract boolean save();
}
