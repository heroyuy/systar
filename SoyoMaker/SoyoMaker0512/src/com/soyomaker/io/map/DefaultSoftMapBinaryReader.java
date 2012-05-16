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
import com.soyomaker.model.map.Tile;
import com.soyomaker.model.map.TileLayer;
import com.soyomaker.model.map.TileSet;
import com.soyomaker.AppData;
import com.soyomaker.util.TileCutter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author Administrator
 */
public class DefaultSoftMapBinaryReader implements IMapReader {

    /**
     * 
     * @param mapFile
     * @return
     * @throws Exception
     */
    public Map readMap(String mapFile) throws Exception {
        DataInputStream dis = null;
        FileInputStream fis = null;
        Map map = new Map();
        fis = new FileInputStream(mapFile);
        dis = new DataInputStream(fis);
        map.setIndex(dis.readInt());
//        System.out.print("地图序号：" + map.getIndex());
        map.setName(dis.readUTF());
//        System.out.print(" 地图名称：" + map.getName());
        map.setMusicName(dis.readUTF());
        //暂时为了兼容第六版本的程序这样操作，以后要去掉
//        if (SoftInformation.minorVersion > 6) {
        map.setBattleBackground(dis.readUTF());
        map.setBattleMusicName(dis.readUTF());
//        }
//        System.out.print(" 音乐名称：" + map.getMusicName());
        map.setHeight(dis.readInt());
//        System.out.print(" 地图高度：" + map.getHeight());
        map.setWidth(dis.readInt());
//        System.out.print(" 地图宽度：" + map.getWidth());
        map.setTileWidth(dis.readInt());
//        System.out.print(" 瓷砖宽度：" + map.getTileWidth());
        map.setTileHeight(dis.readInt());
//        System.out.println(" 瓷砖高度：" + map.getTileHeight());
        TileSet is = null;
        int tilesetNums = dis.readInt();
//        System.out.println("图集总数：" + tilesetNums);
        for (int i = 0; i < tilesetNums; i++) {
            is = new TileSet();
            is.setMap(map);
            is.setIndex(dis.readInt());
//            System.out.println("图集ID：" + is.getIndex());
            is.setName(dis.readUTF());
            is.setAutoTile(dis.readBoolean());
//            System.out.println("图集名称：" + is.getName());
            String path = dis.readUTF();
            is.setTilebmpFile(new File(AppData.getInstance().getCurProject().getPath() + path));
//            System.out.println("图集文件：" + AppData.getInstance().getCurProject().getPath() + path);
            is.importTileBitmap(new File(AppData.getInstance().getCurProject().getPath() + path),
                    new TileCutter(map.getTileWidth(), map.getTileHeight()));
            map.addTileset(is);
            AppData.getInstance().getCurProject().addTileSet(is);
        }
        Layer layer = null;
        int layerNums = dis.readInt();
//        System.out.println("图层总数：" + layerNums);
        for (int i = 0; i < layerNums; i++) {
            int type = dis.readByte();
            String name = dis.readUTF();
//            System.out.println("图层类型：" + type);
            switch (type) {
                case Layer.COLLIDELAYER:
                    layer = new CollideLayer(map.getWidth(), map.getHeight());
                    layer.addLayerChangeListener(data.getMainFrame());
                    layer.setName(name);
                    map.addLayer(layer);
                    map.setCollideLayer((CollideLayer) layer);
                    dis.readInt();//读取层位置，由于现在是按顺序写入，读出，暂时无用
                    for (int j = 0; j < map.getHeight(); j++) {
                        for (int k = 0; k < map.getWidth(); k++) {
                            ((CollideLayer) layer).setCollideAt(k, j, !dis.readBoolean());
                        }
                    }
                    break;
                case Layer.SPRITELAYER:
                    layer = new SpriteLayer(map.getWidth(), map.getHeight());
                    layer.addLayerChangeListener(data.getMainFrame());
                    layer.setName(name);
                    map.addLayer(layer);
                    map.setSpriteLayer((SpriteLayer) layer);
                    for (int j = 0; j < map.getHeight(); j++) {
                        for (int k = 0; k < map.getWidth(); k++) {
                            int index = dis.readInt();
                            if (index == -1) {
                                continue;
                            }
                            INpcReader npcReader = new DefaultSoftNpcBinaryReader();
                            Npc npc = npcReader.readNpc(AppData.getInstance().getCurProject().getPath() + File.separatorChar
                                    + "softdata" + File.separatorChar + "npc" + File.separatorChar + "npc" + index + ".gat");
                            AppData.getInstance().getCurProject().addNpc(npc);
                            ((SpriteLayer) layer).setNpcAt(k, j, npc);
                        }
                    }
                    break;
                case Layer.TILELAYER:
                    layer = new TileLayer(map.getWidth(), map.getHeight());
                    layer.addLayerChangeListener(data.getMainFrame());
                    layer.setName(name);
                    map.addLayer(layer);
                    dis.readInt();
                    for (int j = 0; j < map.getHeight(); j++) {
                        for (int k = 0; k < map.getWidth(); k++) {
                            int tilesetid = dis.readInt();
                            if (tilesetid == -1) {
                                continue;
                            }
                            Tile tile = map.getTileSetByID(tilesetid).getTile(dis.readInt());
                            ((TileLayer) layer).setTileAt(k, j, tile);
                        }
                    }
                    break;
            }

        }
        dis.close();
        fis.close();
        return map;
    }
    private AppData data = AppData.getInstance();
}
