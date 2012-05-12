package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.Model;
import com.soyostar.data.model.Skill;
import com.soyostar.project.Project;
import com.soyostar.proxy.SoftProxy;
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

    /**
     * 
     */
    public void load() {
        System.out.println("skill load");
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        try {
            f = new File(SoftProxy.getInstance().getCurProject().getPath() + "/data/skill.gat");
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
                String s = dis.readUTF();
//                System.out.println("s: " + s);
                int temp = s.lastIndexOf("\\");
                skill.icon = s.substring(temp + 1);
//                System.out.println("skill icon:" + skill.icon);
                skill.hp = dis.readInt();
//                System.out.println("skill hp:" + skill.hp);
                skill.sp = dis.readInt();
//                System.out.println("skill sp:" + skill.sp);
                skill.lev = dis.readInt();
                skill.atk = dis.readInt();
                skill.def = dis.readInt();
                skill.price = dis.readInt();
                skill.dpy = dis.readInt();
                skill.speed = dis.readInt();
                skill.aniIndex = dis.readInt();
                SoftProxy.getInstance().getCurProject().getDataManager().saveModel(Model.SKILL, skill);
//                saveModel(skill);
            }
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
            System.out.println("没有可加载的Skill");
        }
    }

    /**
     * 
     * @return
     */
    public boolean save() {
        System.out.println("skill save");
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        try {
            f = new File(SoftProxy.getInstance().getCurProject().getPath() + "/data/skill.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeInt(size());
            Skill[] skills = this.getModels(Skill.class);
            Skill skill = null;
            for (int i = 0; i < size(); i++) {
                skill = skills[i];
                dos.writeInt(skill.getIndex());
                dos.writeInt(skill.kind);
                dos.writeUTF(skill.name);
                dos.writeUTF(skill.intro);
                dos.writeUTF("\\image\\icon\\skill\\" + skill.icon);
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
            return true;
        } catch (IOException e) {
            System.out.println("skill.gat writeError!");
            return false;
        }
    }
//            //单元测试
//    public static void main(String[] args) {
//        new SkillDao().load();
//    }
}
