/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.listener;

//import com.soyostar.data.Data;
import com.soyostar.data.DataManager;
import com.soyostar.data.dao.ModelStore;
import com.soyostar.data.model.Skill;
import java.util.EventObject;

/**
 *
 * @author Administrator
 */
public class DataChangedEvent extends EventObject {

    /**
     * 
     * @param layer
     */
    public DataChangedEvent(DataManager layer) {
        super(layer);
    }

    /**
     * 
     * @return
     */
    public DataManager getData() {
        return (DataManager) getSource();
    }
}
