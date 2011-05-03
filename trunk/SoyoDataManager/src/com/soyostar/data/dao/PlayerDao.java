package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.Player;
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
public class PlayerDao extends Dao<Player> {

    public void load() {
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        Player player = null;
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/player.gat");
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            player = new Player();
            player.name = dis.readUTF();
            player.intro = dis.readUTF();
            player.headImg = dis.readUTF();
            player.charImg = dis.readUTF();
            player.stre = dis.readInt();
            player.agil = dis.readInt();
            player.inte = dis.readInt();
            player.hp = dis.readInt();
            player.sp = dis.readInt();
            player.lev = dis.readInt();
            player.atk = dis.readInt();
            player.def = dis.readInt();
            player.flee = dis.readInt();
            player.streByLev = dis.readInt();
            player.agilByLev = dis.readInt();
            player.inteByLev = dis.readInt();

            player.levList = new int[player.lev];
            for (int i = 0; i < player.levList.length; i++) {
                player.levList[i] = dis.readInt();
            }
            player.money = dis.readInt();
            player.curMapIndex = dis.readInt();
            player.row = dis.readInt();
            player.col = dis.readInt();
            player.face = dis.readInt();
            player.setIndex(0);
            saveModel(player);
            dis.close();
            fis.close();
            f = null;
        } catch (IOException e) {
            System.out.println("没有可加载的Player");
        }
    }

    public boolean save() {

        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        Player[] players = this.getModels(Player.class);
        Player player = null;
        if (players != null && players.length > 0) {
            player = players[0];
        }
        if (player == null) {
            return true;
        }
        try {
            f = new File(DataManager.getInstance().getPath() + "/data/player.gat");
            fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);
            dos.writeUTF(player.name);
            dos.writeUTF(player.intro);
            dos.writeUTF(player.headImg);
            dos.writeUTF(player.charImg);
            dos.writeInt(player.stre);
            dos.writeInt(player.agil);
            dos.writeInt(player.inte);
            dos.writeInt(player.hp);
            dos.writeInt(player.sp);
            dos.writeInt(player.lev);
            dos.writeInt(player.atk);
            dos.writeInt(player.def);
            dos.writeInt(player.flee);
            dos.writeInt(player.streByLev);
            dos.writeInt(player.agilByLev);
            dos.writeInt(player.inteByLev);

            for (int i = 0; i < player.levList.length; i++) {
                dos.writeInt(player.levList[i]);
            }
            dos.writeInt(player.money);
            dos.writeInt(player.curMapIndex);
            dos.writeInt(player.row);
            dos.writeInt(player.col);
            dos.writeInt(player.face);

            dos.close();
            fos.close();
            f = null;
            return true;
        } catch (IOException e) {
            return false;
        }
    }
//        //单元测试
//    public static void main(String[] args) {
//        new PlayerDao().load();
//    }
}
