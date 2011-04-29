package com.soyostar.data.dao;

import com.soyostar.data.DataManager;
import com.soyostar.data.model.ImageSet;
import com.soyostar.data.model.Layer;
import com.soyostar.data.model.Map;
import com.soyostar.data.model.Script;
import com.soyostar.data.model.Tiled;
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
                map.imageSetNum = dis.readInt();
                map.imageSets = new ArrayList<ImageSet>();
                ImageSet is = null;
                for (int i = 0; i < map.imageSetNum; i++) {
                    is = new ImageSet();
                    is.id = dis.readInt();
                    is.path = dis.readUTF();
                    map.imageSets.add(is);
                }
                map.musicName = dis.readUTF();
                map.rowNum = dis.readInt();
                map.colNum = dis.readInt();
                map.cellWidth = dis.readInt();
                map.cellHeight = dis.readInt();
                map.layerNum = dis.readInt();
                map.layers = new ArrayList<Layer>();
                Layer layer = null;
                for (int i = 0; i < map.layerNum; i++) {
                    layer = new Layer();
                    layer.deepth = dis.readInt();
                    layer.tileds = new Tiled[map.rowNum][map.colNum];
                    for (int j = 0; j < map.rowNum; j++) {
                        for (int k = 0; k < map.colNum; k++) {
                            layer.tileds[j][k] = new Tiled();
                            layer.tileds[j][k].imageSetId = dis.readInt();
                            layer.tileds[j][k].tiledId = dis.readInt();
                        }
                    }
                    map.layers.add(layer);
                }

                map.way = new boolean[map.rowNum][map.colNum];
                for (int j = 0; j < map.rowNum; j++) {
                    for (int k = 0; k < map.colNum; k++) {
                        map.way[j][k] = dis.readBoolean();
                    }
                }

                map.scriptType = new byte[map.rowNum][map.colNum];
                for (int j = 0; j < map.rowNum; j++) {
                    for (int k = 0; k < map.colNum; k++) {
                        map.scriptType[j][k] = dis.readByte();
                    }
                }
                map.scriptNum = dis.readInt();
                map.scripts = new ArrayList<Script>();
                Script script = null;
                for (int i = 0; i < map.scriptNum; i++) {
                    script = new Script();
                    script.type = dis.readByte();
                    script.row = dis.readInt();
                    script.col = dis.readInt();
                    script.face = dis.readByte();
                    script.moveRule = dis.readByte();
                    script.moveSpeed = dis.readInt();
                    script.commandNum = dis.readInt();
                    script.commands = new String[script.commandNum];
                    for (int j = 0; j < script.commands.length; j++) {
                        script.commands[j] = dis.readUTF();
                    }
                    map.scripts.add(script);
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
        Map[] maps = getModels(Map.class);
        Map map = null;
        for (int ii = 0; ii < fs.length; ii++) {
            try {
                fos = new FileOutputStream(fs[ii]);
                dos = new DataOutputStream(fos);
                map = maps[ii];
                dos.writeInt(map.getIndex());
                dos.writeUTF(map.name);
                dos.writeInt(map.imageSets.size());
                for (int i = 0; i < map.imageSets.size(); i++) {
                    dos.writeInt(map.imageSets.get(i).id);
                    dos.writeUTF(map.imageSets.get(i).path);
                }
                dos.writeUTF(map.musicName);
                dos.writeInt(map.rowNum);
                dos.writeInt(map.colNum);
                dos.writeInt(map.cellWidth);
                dos.writeInt(map.cellHeight);
                dos.writeInt(map.layers.size());
                for (int i = 0; i < map.layers.size(); i++) {
                    dos.writeInt(map.layers.get(i).deepth);
                    for (int j = 0; j < map.rowNum; j++) {
                        for (int k = 0; k < map.colNum; k++) {
                            dos.writeInt(map.layers.get(i).tileds[j][k].imageSetId);
                            dos.writeInt(map.layers.get(i).tileds[j][k].tiledId);
                        }
                    }
                }

                for (int j = 0; j < map.rowNum; j++) {
                    for (int k = 0; k < map.colNum; k++) {
                        dos.writeBoolean(map.way[j][k]);
                    }
                }

                for (int j = 0; j < map.rowNum; j++) {
                    for (int k = 0; k < map.colNum; k++) {
                        dos.writeByte(map.scriptType[j][k]);
                    }
                }
                dos.writeInt(map.scripts.size());
                for (int i = 0; i < map.scripts.size(); i++) {
                    dos.writeByte(map.scripts.get(i).type);
                    dos.writeInt(map.scripts.get(i).row);
                    dos.writeInt(map.scripts.get(i).col);
                    dos.writeByte(map.scripts.get(i).face);
                    dos.writeByte(map.scripts.get(i).moveRule);
                    dos.writeInt(map.scripts.get(i).moveSpeed);
                    dos.writeInt(map.scripts.get(i).commandNum);
                    for (int j = 0; j < map.scripts.get(i).commands.length; j++) {
                        dos.writeUTF(map.scripts.get(i).commands[j]);
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
