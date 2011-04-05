package com.soyostar.game.db;

import com.soyostar.emulator.engine.animation.Animation;
import com.soyostar.emulator.engine.script.Command;
import com.soyostar.emulator.engine.script.Script;
import com.soyostar.game.model.Config;
import com.soyostar.game.model.Enemy;
import com.soyostar.game.model.EnemyTroop;
import com.soyostar.game.model.Equip;
import com.soyostar.game.model.Frame;
import com.soyostar.game.model.Item;
import com.soyostar.game.model.Map;
import com.soyostar.game.model.Player;
import com.soyostar.game.model.Skill;
import com.soyostar.ui.Image;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * 游戏对象工厂
 */
public class GameObjectFactory {

    private static FileBridge fb = new FileBridge();
    private static DataInputStream dis = null;






    protected static Skill[] createSkillList() {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_SKILL, 0);
        Skill[] skillList = null;
        try {
            int skillSum = dis.readInt();
            skillList = new Skill[skillSum];
            for (int i = 0; i < skillSum; i++) {
                skillList[i] = new Skill();
                skillList[i].index = dis.readInt();
                skillList[i].kind = dis.readInt();
                skillList[i].name = dis.readUTF();
                skillList[i].intro = dis.readUTF();
                skillList[i].icon = Image.createImage("product/image/icon/skill/" + dis.readUTF());
                skillList[i].hp = dis.readInt();
                skillList[i].sp = dis.readInt();
                skillList[i].lev = dis.readInt();
//                    skillList[i].atk = dis.readInt();
                skillList[i].def = dis.readInt();
                skillList[i].price = dis.readInt();
                skillList[i].dpy = dis.readInt();
                skillList[i].speed = dis.readInt();
                skillList[i].aniIndex = dis.readInt();
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("skill.gat readError!");
        }
        return skillList;
    }


}
