/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.io;

import com.soyostar.model.map.CollideLayer;
import com.soyostar.model.map.Layer;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.Npc;
import com.soyostar.model.map.SpriteLayer;
import com.soyostar.model.map.TileLayer;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author Administrator
 */
public class DefaultMapWriter implements IMapWriter {

    public void writeMap(Map map, String filename) throws Exception {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        fos = new FileOutputStream(filename);
        dos = new DataOutputStream(fos);
        dos.writeInt(map.getIndex());
        System.out.print("地图序号：" + map.getIndex());
        dos.writeUTF(map.getName());
        System.out.println("地图名称：" + map.getName());
        dos.writeUTF(map.getMusicName());
//        System.out.println("音乐名称：" + map.getMusicName());
        dos.writeInt(map.getHeight());
//        System.out.println("地图高度：" + map.getHeight());
        dos.writeInt(map.getWidth());
//        System.out.println("地图宽度：" + map.getWidth());
        dos.writeInt(map.getTileWidth());
//        System.out.println("瓷砖宽度：" + map.getTileWidth());
        dos.writeInt(map.getTileHeight());
//        System.out.println("瓷砖高度：" + map.getTileHeight());
        int tilesetNums = map.getTileSets().size();
        System.out.println("图集总数：" + tilesetNums);
        dos.writeInt(tilesetNums);
        for (int i = 0; i < tilesetNums; i++) {
            dos.writeInt(map.getTileSets().get(i).getIndex());
//            System.out.println("图集ID：" + map.getTileSets().get(i).getIndex());
            dos.writeUTF("\\image\\tileset\\" + map.getTileSets().get(i).getTilebmpFile());
//            System.out.println("图集文件：" + map.getTileSets().get(i).getTilebmpFile());
        }
        int layerNums = map.getLayerArrayList().size();
        System.out.println("瓷砖层总数：" + (layerNums - 2));
        dos.writeInt(layerNums - 2);//-2是减去碰撞层和脚本层
        int sLayer = 0;
        for (int i = 0; i < layerNums; i++) {
            if (map.getLayerArrayList().get(i) instanceof SpriteLayer) {
                sLayer = i;
            }
        }
        for (int i = 0; i < layerNums; i++) {
//            System.out.println("Layer " + i);
            if (map.getLayerArrayList().get(i) instanceof TileLayer) {
//                System.out.println("TileLayer");
                if (i < sLayer) {
                    dos.writeInt(-(Integer.MAX_VALUE - i));
                } else {
                    dos.writeInt(i);
                }

                for (int j = 0; j < map.getHeight(); j++) {
                    for (int k = 0; k < map.getWidth(); k++) {
                        if (((TileLayer) map.getLayerArrayList().get(i)).getTileAt(k, j) == null) {
                            dos.writeInt(-1);
                            continue;
                        }
                        dos.writeInt(((TileLayer) map.getLayerArrayList().get(i)).getTileAt(k, j).getTileSet().getIndex());
                        dos.writeInt(((TileLayer) map.getLayerArrayList().get(i)).getTileAt(k, j).getIndex());
                    }
                }
            }
        }
        for (int i = 0; i < layerNums; i++) {
            if (map.getLayerArrayList().get(i) instanceof CollideLayer) {
//                System.out.println("CollideLayer");
                for (int j = 0; j < map.getHeight(); j++) {
                    for (int k = 0; k < map.getWidth(); k++) {
                        dos.writeBoolean(((CollideLayer) map.getLayerArrayList().get(i)).getCollideAt(k, j));
                    }
                }
            }
        }
        for (int i = 0; i < layerNums; i++) {
            if (map.getLayerArrayList().get(i) instanceof SpriteLayer) {
//                System.out.println("SpriteLayer");
                for (int j = 0; j < map.getHeight(); j++) {
                    for (int k = 0; k < map.getWidth(); k++) {
                        Npc npc = ((SpriteLayer) map.getLayerArrayList().get(i)).getNpcAt(k, j);
                        if (npc != null) {
                            dos.writeInt(npc.getIndex());
                        } else {
                            dos.writeInt(-1);
                        }
                    }
                }
            }
        }
        dos.close();
        fos.close();
    }
}
