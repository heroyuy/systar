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
import com.soyostar.model.map.Tile;
import com.soyostar.model.map.TileLayer;
import com.soyostar.model.map.TileSet;
import com.soyostar.project.Project;
import com.soyostar.proxy.SoftProxy;
import com.soyostar.render.MapRenderFactory;
import com.soyostar.util.Picture;
import com.soyostar.util.TileCutter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class DefaultSoftMapReader implements IMapReader {

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
        System.out.print("地图序号：" + map.getIndex());
        map.setName(dis.readUTF());
        System.out.print(" 地图名称：" + map.getName());
        map.setMusicName(dis.readUTF());
        System.out.print(" 音乐名称：" + map.getMusicName());
        map.setHeight(dis.readInt());
        System.out.print(" 地图高度：" + map.getHeight());
        map.setWidth(dis.readInt());
        System.out.print(" 地图宽度：" + map.getWidth());
        map.setTileWidth(dis.readInt());
        System.out.print(" 瓷砖宽度：" + map.getTileWidth());
        map.setTileHeight(dis.readInt());
        System.out.println(" 瓷砖高度：" + map.getTileHeight());
        TileSet is = null;
        int tilesetNums = dis.readInt();
        System.out.println("图集总数：" + tilesetNums);
        for (int i = 0; i < tilesetNums; i++) {
            is = new TileSet();
            is.setIndex(dis.readInt());
            System.out.println("图集ID：" + is.getIndex());
            is.setName(dis.readUTF());
            System.out.println("图集名称：" + is.getName());
            String path = dis.readUTF();
            System.out.println("图集文件：" + SoftProxy.getInstance().getCurProject().getPath() + path);
            is.importTileBitmap(SoftProxy.getInstance().getCurProject().getPath() + path,
                new TileCutter(map.getTileWidth(), map.getTileHeight()));
            System.out.println("同步通行度设置");
            Picture pic = data.getMainFrame().tscd.initCrossFile(path.substring(path.lastIndexOf(File.separatorChar) + 1), map.getBounds());
            if (pic != null) {
                System.out.println("pic:" + pic.imgFileName);
//                int n = newTileset.generateGaplessArrayList().size();
                for (int k = 0; k < pic.collides.length; k++) {
                    for (int j = 0; j < pic.collides[0].length; j++) {
                        is.getTile(k + pic.collides.length * j).setIsCross(!pic.collides[k][j]);
                    }
                }
            }
            map.addTileset(is);
        }
        Layer layer = null;
        int layerNums = dis.readInt();
        System.out.println("图层总数：" + layerNums);
        for (int i = 0; i < layerNums; i++) {
            int type = dis.readByte();
            String name = dis.readUTF();
            System.out.println("图层类型：" + type);
            switch (type) {
                case Layer.COLLIDELAYER:
                    layer = new CollideLayer(map.getWidth(), map.getHeight());
                    layer.addLayerChangeListener(data.getMainFrame());
                    layer.setName(name);
                    map.addLayer(layer);
                    dis.readInt();//读取层位置，由于现在是按顺序写入，读出，暂时无用
                    for (int j = 0; j < map.getHeight(); j++) {
                        for (int k = 0; k < map.getWidth(); k++) {
                            ((CollideLayer) layer).setCollideAt(k, j, dis.readBoolean());
                        }
                    }
                    break;
                case Layer.SPRITELAYER:
                    layer = new SpriteLayer(map.getWidth(), map.getHeight());
                    layer.addLayerChangeListener(data.getMainFrame());
                    layer.setName(name);
                    map.addLayer(layer);
                    for (int j = 0; j < map.getHeight(); j++) {
                        for (int k = 0; k < map.getWidth(); k++) {
                            int index = dis.readInt();
                            if (index == -1) {
                                continue;
                            }
                            INpcReader npcReader = new DefaultNpcReader();
                            Npc npc = npcReader.readNpc(SoftProxy.getInstance().getCurProject().getPath() + File.separatorChar
                                + "data" + File.separatorChar + "npc" + File.separatorChar + "npc" + index + ".gat");
                            SoftProxy.getInstance().getCurProject().addNpc(npc, npc.getIndex());
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
                            Tile tile = map.getTileSets().get(tilesetid).getTile(dis.readInt());
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
    private SoftProxy data = SoftProxy.getInstance();
}
