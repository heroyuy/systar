/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Skill;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class SkillDao implements Dao {

    private HashMap skills = null;

    public Skill getSkill(int index) {
        return (Skill) skills.get(index);
    }

    public Skill[] getSkillList() {
        return (Skill[]) skills.values().toArray();
    }

    public void load() {
    }

    public void save() {
    }
}
