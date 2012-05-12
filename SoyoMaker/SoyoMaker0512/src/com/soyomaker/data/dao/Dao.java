/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data.dao;

import com.soyomaker.data.model.Model;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Administrator
 * @param <M>
 */
public abstract class Dao<M extends Model> extends ModelPool<M> {

    /**
     *
     * @throws FileNotFoundException 
     * @throws IOException
     */
    public abstract void load() throws FileNotFoundException, IOException;

    /**
     *
     * @return 是否保存成功
     */
    public abstract boolean save();


    /**
     *
     * @return 是否保存成功
     */
    public abstract boolean saveSoft();
}
