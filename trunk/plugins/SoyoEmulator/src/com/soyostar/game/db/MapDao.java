/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.db;

import com.soyostar.emulator.engine.script.Command;
import com.soyostar.emulator.engine.script.Script;
import com.soyostar.game.model.Map;
import com.soyostar.ui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class MapDao implements Dao {

    private DataInputStream dis = null;
    private HashMap maps = null;

    public Map getMap(int index) {
        return (Map) maps.get(index);
    }

    public Map[] getMapList() {
        return (Map[]) maps.values().toArray();
    }

    public void load() {
//        maps = new HashMap();
//
//        dis = DataManager.getInstance().getFileBridge().getDataInputStream(FileBridge.FILE_TYPE_MAP, index);
//        Map map = new Map();
//        try {
//            map.index = dis.readInt();
//            map.name = dis.readUTF();
//            String imageName = dis.readUTF();
//
//            map.musicName = dis.readUTF();
//            map.rowNum = dis.readByte();
//            System.out.println("row " + map.rowNum);
//            map.colNum = dis.readByte();
//            System.out.println("col " + map.colNum);
//            map.cellWidth = dis.readByte();
//            map.cellHeight = dis.readByte();
//            map.layerNum = dis.readByte();
//
//            map.data = new int[map.layerNum][map.rowNum][map.colNum];
//            for (int i = 0; i < map.layerNum; i++) {
//                for (int j = 0; j < map.rowNum; j++) {
//                    for (int k = 0; k < map.colNum; k++) {
//                        map.data[i][j][k] = dis.readInt();
//                    }
//                }
//            }
//            map.way = new int[map.rowNum][map.colNum];
//            for (int j = 0; j < map.rowNum; j++) {
//                for (int k = 0; k < map.colNum; k++) {
//                    map.way[j][k] = dis.readByte();
//                }
//            }
//
//            map.scriptType = new byte[map.rowNum][map.colNum];
//            for (int j = 0; j < map.rowNum; j++) {
//                for (int k = 0; k < map.colNum; k++) {
//                    map.scriptType[j][k] = dis.readByte();
//                }
//            }
//            int sum = dis.readInt();
//            map.script = new Script[sum];
//            for (int i = 0; i < sum; i++) {
//                map.script[i] = new Script();
//                map.script[i].type = dis.readByte();
//                map.script[i].row = dis.readInt();
//                map.script[i].col = dis.readInt();
//                map.script[i].command = new Command[dis.readInt()];
//                for (int j = 0; j < map.script[i].command.length; j++) {
//                    map.script[i].command[j] = new Command();
//                    map.script[i].command[j].type = dis.readByte();
//                    map.script[i].command[j].param = dis.readUTF();
//                    map.script[i].command[j].nextIndex = dis.readInt();
//                }
//            }
//            map.setImage(Image.createImage("product/image/tileset/" + imageName));
//            dis.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    public void save() {
    }
}
