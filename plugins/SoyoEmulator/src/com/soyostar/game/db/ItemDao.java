/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Item;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class ItemDao implements Dao {

    private HashMap items = null;

    public Item getItem(int index) {
        return (Item) items.get(index);
    }

    public Item[] getItemList() {
        return (Item[]) items.values().toArray();
    }

    public void load() {
    }

    public void save() {
    }
}
