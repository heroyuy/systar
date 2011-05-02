package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.Skill;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public class SkillDao extends Dao<Skill> {

    public void load() {

        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/skill.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            int skillSum = dis.readInt();
            Skill skill = null;
            for (int i = 0; i < skillSum; i++) {
                skill = new Skill();
                skill.setIndex(dis.readInt());
                skill.kind = dis.readInt();
                skill.name = dis.readUTF();
                skill.intro = dis.readUTF();
                skill.icon = "/image/icon/skill/" + dis.readUTF();
                skill.hp = dis.readInt();
                skill.sp = dis.readInt();
                skill.lev = dis.readInt();
                skill.atk = dis.readInt();
                skill.def = dis.readInt();
                skill.price = dis.readInt();
                skill.dpy = dis.readInt();
                skill.speed = dis.readInt();
                skill.aniIndex = dis.readInt();
                saveModel(skill);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("skill.gat readError!");
        }
    }

    public void save() {

        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/skill.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Skill[] skills = this.getModels();
            Skill skill = null;
            for (int i = 0; i < size(); i++) {
                skill = skills[i];
                dos.writeInt(skill.getIndex());
                dos.writeInt(skill.kind);
                dos.writeUTF(skill.name);
                dos.writeUTF(skill.intro);
                dos.writeUTF(skill.icon);
                dos.writeInt(skill.hp);
                dos.writeInt(skill.sp);
                dos.writeInt(skill.lev);
                dos.writeInt(skill.atk);
                dos.writeInt(skill.def);
                dos.writeInt(skill.price);
                dos.writeInt(skill.dpy);
                dos.writeInt(skill.speed);
                dos.writeInt(skill.aniIndex);

            }
            dos.close();
            fos.close();
            f = null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("skill.gat writeError!");
        }
    }
//            //单元测试
//    public static void main(String[] args) {
//        new SkillDao().load();
//    }
}
