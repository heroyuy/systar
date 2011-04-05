/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.game.model.Player;
import com.soyostar.ui.Image;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class PlayerDao implements Dao {

    private DataInputStream dis = null;
    private Player player = null;

    public Player getPlayer() {
        return player;
    }

    public void load() {
        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_PLAYER, 0);
        player = new Player();
        try {
            player.name = dis.readUTF();
            player.intro = dis.readUTF();
            player.headImg = Image.createImage("product/image/battler/" + dis.readUTF());
            player.setCharImg(Image.createImage("product/image/character/" + dis.readUTF()));
            player.stre = dis.readInt();
            player.agil = dis.readInt();
            player.inte = dis.readInt();
            player.hp = dis.readInt();
            player.sp = dis.readInt();
            player.maxLev = dis.readInt();
            player.atk = dis.readInt();
            player.def = dis.readInt();
            player.flee = dis.readInt();
            player.streByLev = dis.readInt();
            player.agilByLev = dis.readInt();
            player.inteByLev = dis.readInt();

            player.levList = new int[player.maxLev];
            for (int i = 0; i < player.levList.length; i++) {
                player.levList[i] = dis.readInt();
            }
            player.money = dis.readInt();
            player.curMapIndex = dis.readInt();
            player.row = dis.readInt();
            player.col = dis.readInt();
            player.face = dis.readInt();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("找不到player.gat文件");
        }
    }

    public void save() {
    }
}
