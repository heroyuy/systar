package com.soyostar.game.db;

import com.soyostar.game.model.Skill;
import com.soyostar.ui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class SkillDao implements Dao {

    private DataInputStream dis = null;
    private HashMap skills = null;

    public Skill getSkill(int index) {
        return (Skill) skills.get(index);
    }

    public Skill[] getSkillList() {
        return (Skill[]) skills.values().toArray();
    }

    public void load() {
        skills = new HashMap();

        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_SKILL, 0);

        try {
            int skillSum = dis.readInt();
            Skill skill = null;
            for (int i = 0; i < skillSum; i++) {
                skill = new Skill();
                skill.index = dis.readInt();
                skill.kind = dis.readInt();
                skill.name = dis.readUTF();
                skill.intro = dis.readUTF();
                skill.icon = Image.createImage("product/image/icon/skill/" + dis.readUTF());
                skill.hp = dis.readInt();
                skill.sp = dis.readInt();
                skill.lev = dis.readInt();
//                    skill.atk = dis.readInt();
                skill.def = dis.readInt();
                skill.price = dis.readInt();
                skill.dpy = dis.readInt();
                skill.speed = dis.readInt();
                skill.aniIndex = dis.readInt();
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("skill.gat readError!");
        }
    }

    public void save() {
    }
}
