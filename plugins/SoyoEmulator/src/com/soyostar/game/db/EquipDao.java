/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Equip;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class EquipDao implements Dao {

    private HashMap equips = null;

    public Equip getEquip(int index) {
        return (Equip) equips.get(index);
    }

    public Equip[] getEquipList() {
        return (Equip[]) equips.values().toArray();
    }

    public void load() {
    }

    public void save() {
    }
}
