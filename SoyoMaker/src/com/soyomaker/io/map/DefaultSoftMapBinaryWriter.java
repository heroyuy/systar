/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.model.map.CollideLayer;
import com.soyomaker.model.map.Layer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.SpriteLayer;
import com.soyomaker.model.map.TileLayer;
import com.soyomaker.AppData;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;

/**
 *
 * @author Administrator
 */
public class DefaultSoftMapBinaryWriter implements IMapWriter {

    public void writeMap(Map map, String filename) throws Exception {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        fos = new FileOutputStream(filename);
        dos = new DataOutputStream(fos);
//        System.out.println("地图序号：" + map.getIndex());
        dos.writeInt(map.getIndex());
//        System.out.println("地图名称：" + map.getName());
        dos.writeUTF(map.getName());
//        System.out.println("音乐名称：" + map.getMusicName());
        dos.writeUTF(map.getMusicName());
        dos.writeUTF(map.getBattleBackground());
        dos.writeUTF(map.getBattleMusicName());
        dos.writeInt(map.getHeight());
//        System.out.println("地图高度：" + map.getHeight());
        dos.writeInt(map.getWidth());
//        System.out.println("地图宽度：" + map.getWidth());
        dos.writeInt(map.getTileWidth());
//        System.out.println("瓷砖宽度：" + map.getTileWidth());
        dos.writeInt(map.getTileHeight());
//        System.out.println("瓷砖高度：" + map.getTileHeight());
        int tilesetNums = map.getTileSets().size();
//        System.out.println("图集总数：" + tilesetNums);
        dos.writeInt(tilesetNums);
        for (int i = 0; i < tilesetNums; i++) {
            dos.writeInt(map.getTileSets().get(i).getIndex());
//            System.out.println("图集ID：" + map.getTileSets().get(i).getIndex());
            dos.writeUTF(map.getTileSets().get(i).getName());
//            System.out.println("图集名称：" + map.getTileSets().get(i).getName());
            boolean auto = map.getTileSets().get(i).isAutoTile();
            dos.writeBoolean(auto);
            if (auto) {
                dos.writeUTF("\\image\\autotile\\" + map.getTileSets().get(i).getTilebmpFile().getName());
            } else {
                dos.writeUTF("\\image\\tileset\\" + map.getTileSets().get(i).getTilebmpFile().getName());
            }
        }
        int layerNums = map.getLayerArrayList().size();
//        System.out.println("图层总数：" + layerNums);
        dos.writeInt(layerNums);
        for (int i = 0; i < layerNums; i++) {
//            System.out.println("Layer " + i);
            if (map.getLayerArrayList().get(i) instanceof TileLayer) {
                dos.writeByte(Layer.TILELAYER);
                dos.writeUTF(map.getLayerArrayList().get(i).getName());
//                System.out.println("TileLayer");
                dos.writeInt(i);
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
            } else if (map.getLayerArrayList().get(i) instanceof CollideLayer) {
                dos.writeByte(Layer.COLLIDELAYER);
                dos.writeUTF(map.getLayerArrayList().get(i).getName());
//                System.out.println("CollideLayer");
                dos.writeInt(i);
                for (int j = 0; j < map.getHeight(); j++) {
                    for (int k = 0; k < map.getWidth(); k++) {
                        dos.writeBoolean(!((CollideLayer) map.getLayerArrayList().get(i)).getCollideAt(k, j));
                    }
                }
            } else if (map.getLayerArrayList().get(i) instanceof SpriteLayer) {
                dos.writeByte(Layer.SPRITELAYER);
                dos.writeUTF(map.getLayerArrayList().get(i).getName());
//                System.out.println("SpriteLayer");
                for (int j = 0; j < map.getHeight(); j++) {
                    for (int k = 0; k < map.getWidth(); k++) {
                        Npc npc = ((SpriteLayer) map.getLayerArrayList().get(i)).getNpcAt(k, j);
                        if (npc != null) {
                            dos.writeInt(npc.getIndex());
                            INpcWriter npcWriter = new DefaultSoftNpcBinaryWriter();
                            npcWriter.writeNpc(npc, AppData.getInstance().getCurProject().getPath() + File.separatorChar
                                    + "softdata" + File.separatorChar + "npc" + File.separatorChar + "npc" + npc.getIndex() + ".gat");
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
