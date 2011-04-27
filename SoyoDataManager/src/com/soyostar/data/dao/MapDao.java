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
                map.imageSets = new ImageSet[map.imageSetNum];
                for (int i = 0; i < map.imageSets.length; i++) {
                    map.imageSets[i] = new ImageSet();
                    map.imageSets[i].id = dis.readInt();
                    map.imageSets[i].path = dis.readUTF();
                }
                map.musicName = dis.readUTF();
                map.rowNum = dis.readInt();
                map.colNum = dis.readInt();
                map.cellWidth = dis.readInt();
                map.cellHeight = dis.readInt();
                map.layerNum = dis.readInt();
                map.layers = new Layer[map.layerNum];
                for (int i = 0; i < map.layers.length; i++) {
                    map.layers[i] = new Layer();
                    map.layers[i].deepth = dis.readInt();
                    map.layers[i].tileds = new Tiled[map.rowNum][map.colNum];
                    for (int j = 0; j < map.rowNum; j++) {
                        for (int k = 0; k < map.colNum; k++) {
                            map.layers[i].tileds[j][k] = new Tiled();
                            map.layers[i].tileds[j][k].imageSetId = dis.readInt();
                            map.layers[i].tileds[j][k].tiledId = dis.readInt();
                        }
                    }
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
                map.scripts = new Script[map.scriptNum];
                for (int i = 0; i < map.scripts.length; i++) {
                    map.scripts[i] = new Script();
                    map.scripts[i].type = dis.readByte();
                    map.scripts[i].row = dis.readInt();
                    map.scripts[i].col = dis.readInt();
                    map.scripts[i].face = dis.readByte();
                    map.scripts[i].moveRule = dis.readByte();
                    map.scripts[i].moveSpeed = dis.readInt();
                    map.scripts[i].commandNum = dis.readInt();
                    map.scripts[i].commands = new String[map.scripts[i].commandNum];
                    for (int j = 0; j < map.scripts[i].commands.length; j++) {
                        map.scripts[i].commands[j] = dis.readUTF();
                    }
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
                dos.writeInt(map.imageSetNum);
                for (int i = 0; i < map.imageSets.length; i++) {
                    dos.writeInt(map.imageSets[i].id);
                    dos.writeUTF(map.imageSets[i].path);
                }
                dos.writeUTF(map.musicName);
                dos.writeInt(map.rowNum);
                dos.writeInt(map.colNum);
                dos.writeInt(map.cellWidth);
                dos.writeInt(map.cellHeight);
                dos.writeInt(map.layerNum);
                for (int i = 0; i < map.layers.length; i++) {
                    dos.writeInt(map.layers[i].deepth);
                    for (int j = 0; j < map.rowNum; j++) {
                        for (int k = 0; k < map.colNum; k++) {
                            dos.writeInt(map.layers[i].tileds[j][k].imageSetId);
                            dos.writeInt(map.layers[i].tileds[j][k].tiledId);
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
                dos.writeInt(map.scriptNum);
                for (int i = 0; i < map.scripts.length; i++) {
                    dos.writeByte(map.scripts[i].type);
                    dos.writeInt(map.scripts[i].row);
                    dos.writeInt(map.scripts[i].col);
                    dos.writeByte(map.scripts[i].face);
                    dos.writeByte(map.scripts[i].moveRule);
                    dos.writeInt(map.scripts[i].moveSpeed);
                    dos.writeInt(map.scripts[i].commandNum);
                    for (int j = 0; j < map.scripts[i].commands.length; j++) {
                        dos.writeUTF(map.scripts[i].commands[j]);
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
