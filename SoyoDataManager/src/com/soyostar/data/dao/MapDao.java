package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.map.CollideLayer;
import com.soyostar.data.model.map.Layer;
import com.soyostar.data.model.map.Map;
import com.soyostar.data.model.map.Script;
import com.soyostar.data.model.map.SpriteLayer;
import com.soyostar.data.model.map.Tile;
import com.soyostar.data.model.map.TileLayer;
import com.soyostar.data.model.map.TileSet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public class MapDao extends Dao<Map> {

    public void load() {
        DataInputStream dis = null;
        FileInputStream fis = null;
        File f = null;
        f = new File(DataManager.getInstance().getPath() + "/data/map");
        File[] fs = f.listFiles();
        //去除非地图文件 待实现
        Map map = null;
        for (int ii = 0; ii < fs.length; ii++) {
            try {
                map = new Map();
                fis = new FileInputStream(fs[ii]);
                dis = new DataInputStream(fis);
                map.setIndex(dis.readInt());
                map.name = dis.readUTF();
                map.musicName = dis.readUTF();
                map.rowNum = dis.readInt();
                map.colNum = dis.readInt();
                map.cellWidth = dis.readInt();
                map.cellHeight = dis.readInt();
                map.tilesets = new ArrayList<TileSet>();
                TileSet is = null;
                for (int i = 0, tilesetNums = dis.readInt(); i < tilesetNums; i++) {
                    is = new TileSet();
                    is.setIndex(dis.readInt());
                    is.path = dis.readUTF();
                    map.tilesets.add(is);
                }
                map.layers = new ArrayList<Layer>();
                Layer layer = null;
                for (int i = 0, layerNums = dis.readInt(); i < layerNums; i++) {
                    int type = dis.readByte();
                    switch (type) {
                        case Layer.COLLIDELAYER:
                            layer = new CollideLayer();
                            layer.deepth = dis.readInt();
                            ((CollideLayer) layer).collides = new boolean[map.rowNum][map.colNum];
                            for (int j = 0; j < map.rowNum; j++) {
                                for (int k = 0; k < map.colNum; k++) {
                                    ((CollideLayer) layer).collides[j][k] = dis.readBoolean();
                                }
                            }
                            break;
                        case Layer.SPRITELAYER:
                            layer = new SpriteLayer();
                            layer.deepth = dis.readInt();
                            ((SpriteLayer) layer).npcs = new Script[map.rowNum][map.colNum];
                            for (int j = 0; j < map.rowNum; j++) {
                                for (int k = 0; k < map.colNum; k++) {
                                    ((SpriteLayer) layer).npcs[j][k] = new Script();
                                    ((SpriteLayer) layer).npcs[j][k].type = dis.readByte();
                                    ((SpriteLayer) layer).npcs[j][k].row = dis.readInt();
                                    ((SpriteLayer) layer).npcs[j][k].col = dis.readInt();
                                    ((SpriteLayer) layer).npcs[j][k].imgPath = dis.readUTF();
                                    ((SpriteLayer) layer).npcs[j][k].face = dis.readByte();
                                    ((SpriteLayer) layer).npcs[j][k].move = dis.readByte();
                                    ((SpriteLayer) layer).npcs[j][k].speed = dis.readByte();
                                    ((SpriteLayer) layer).npcs[j][k].codes = new ArrayList<String>();
                                    int codeNums = dis.readInt();
                                    for (int ij = 0; ij < codeNums; ij++) {
                                        ((SpriteLayer) layer).npcs[j][k].codes.add(dis.readUTF());
                                    }
                                }
                            }
                            break;
                        case Layer.TILELAYER:
                            layer = new TileLayer();
                            layer.deepth = dis.readInt();
                            ((TileLayer) layer).tiles = new Tile[map.rowNum][map.colNum];
                            for (int j = 0; j < map.rowNum; j++) {
                                for (int k = 0; k < map.colNum; k++) {
                                    ((TileLayer) layer).tiles[j][k] = new Tile();
                                    ((TileLayer) layer).tiles[j][k].imageSetId = dis.readInt();
                                    ((TileLayer) layer).tiles[j][k].tiledId = dis.readInt();
                                }
                            }
                            break;
                    }
                    map.layers.add(layer);
                }
                saveModel(map);
                dis.close();
                fis.close();
                f = null;
            } catch (IOException ex) {
                Logger.getLogger(MapDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void save() {
        DataOutputStream dos = null;
        FileOutputStream fos = null;
        File f = null;
        f = new File(DataManager.getInstance().getPath() + "/data/map");
        File[] fs = f.listFiles();
        Map[] maps = getModels();
        Map map = null;
        for (int ii = 0; ii < fs.length; ii++) {
            try {
                fos = new FileOutputStream(fs[ii]);
                dos = new DataOutputStream(fos);
                map = maps[ii];
                dos.writeInt(map.getIndex());
                dos.writeUTF(map.name);
                dos.writeUTF(map.musicName);
                dos.writeInt(map.rowNum);
                dos.writeInt(map.colNum);
                dos.writeInt(map.cellWidth);
                dos.writeInt(map.cellHeight);
                int tilesetNums = map.tilesets.size();
                dos.writeInt(tilesetNums);
                for (int i = 0; i < tilesetNums; i++) {
                    dos.writeInt(map.tilesets.get(i).getIndex());
                    dos.writeUTF(map.tilesets.get(i).path);
                }
                int layerNums = map.layers.size();
                dos.writeInt(layerNums);
                for (int i = 0; i < layerNums; i++) {
                    if (map.layers.get(i) instanceof TileLayer) {
                        dos.writeByte(Layer.TILELAYER);
                        dos.writeInt(map.layers.get(i).deepth);
                        for (int j = 0; j < map.rowNum; j++) {
                            for (int k = 0; k < map.colNum; k++) {
                                dos.writeInt(((TileLayer) map.layers.get(i)).tiles[j][k].imageSetId);
                                dos.writeInt(((TileLayer) map.layers.get(i)).tiles[j][k].tiledId);
                            }
                        }
                    }
                    if (map.layers.get(i) instanceof CollideLayer) {
                        dos.writeByte(Layer.COLLIDELAYER);
                        dos.writeInt(map.layers.get(i).deepth);
                        for (int j = 0; j < map.rowNum; j++) {
                            for (int k = 0; k < map.colNum; k++) {
                                dos.writeBoolean(((CollideLayer) map.layers.get(i)).collides[j][k]);
                            }
                        }
                    }
                    if (map.layers.get(i) instanceof SpriteLayer) {
                        dos.writeByte(Layer.SPRITELAYER);
                        dos.writeInt(map.layers.get(i).deepth);
                        for (int j = 0; j < map.rowNum; j++) {
                            for (int k = 0; k < map.colNum; k++) {
                                dos.writeByte(((SpriteLayer) map.layers.get(i)).npcs[j][k].type);
                                dos.writeInt(((SpriteLayer) map.layers.get(i)).npcs[j][k].row);
                                dos.writeInt(((SpriteLayer) map.layers.get(i)).npcs[j][k].col);
                                dos.writeUTF(((SpriteLayer) map.layers.get(i)).npcs[j][k].imgPath);
                                dos.writeByte(((SpriteLayer) map.layers.get(i)).npcs[j][k].face);
                                dos.writeByte(((SpriteLayer) map.layers.get(i)).npcs[j][k].move);
                                dos.writeByte(((SpriteLayer) map.layers.get(i)).npcs[j][k].speed);
                                int codeNums = ((SpriteLayer) map.layers.get(i)).npcs[j][k].codes.size();
                                dos.writeInt(codeNums);
                                for (int ij = 0; ij < codeNums; ij++) {
                                    dos.writeUTF(((SpriteLayer) map.layers.get(i)).npcs[j][k].codes.get(ij));
                                }
                            }
                        }
                    }
                }

                dos.close();
                fos.close();
                f = null;
            } catch (IOException ex) {
                Logger.getLogger(MapDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//    //单元测试
//    public static void main(String[] args) {
//        MapDao md = new MapDao();
//        md.load();
//        System.out.println("l:" + md.maps.size());
//    }
}
