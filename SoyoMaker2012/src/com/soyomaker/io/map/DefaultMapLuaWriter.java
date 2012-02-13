/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.io.map;

import com.soyomaker.AppData;
import com.soyostar.lua.LuaNode;
import com.soyostar.lua.LuaTable;
import com.soyostar.lua.util.LuaFileUtil;
import com.soyomaker.model.map.Area;
import com.soyomaker.model.map.CollideLayer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.SpriteLayer;
import com.soyomaker.model.map.Tile;
import com.soyomaker.model.map.TileLayer;
import java.io.File;
import java.util.Stack;

/**
 *
 * @author Administrator
 */
public class DefaultMapLuaWriter implements IMapWriter {

    public void writeMap(Map map, String filename) throws Exception {
        LuaTable lts = new LuaTable();
        lts.addNode("\n");
        lts.addNode("index", map.getIndex() + 1);
        lts.addNode("\n");
        lts.addNode("name", map.getName());
        lts.addNode("\n");
        if (map.getMusicName() == null || map.getMusicName().equals("")) {
            lts.addNode("musicName", "nil");
        } else {
            lts.addNode("musicName", "/audio/music/" + map.getMusicName());
        }
        lts.addNode("\n");
        if (map.getBattleBackground() == null || map.getBattleBackground().equals("")) {
            lts.addNode("battleBack", "nil");
        } else {
            lts.addNode("battleBack", "/image/battle/" + map.getBattleBackground());
        }
        lts.addNode("\n");
        if (map.getBattleMusicName() == null || map.getBattleMusicName().equals("")) {
            lts.addNode("battleMusic", "nil");
        } else {
            lts.addNode("battleMusic", "/audio/music/" + map.getBattleMusicName());
        }
        lts.addNode("\n");
        lts.addNode("rowNum", map.getHeight());
        lts.addNode("\n");
        lts.addNode("colNum", map.getWidth());
        lts.addNode("\n");
        lts.addNode("cellWidth", map.getTileWidth());
        lts.addNode("\n");
        lts.addNode("cellHeight", map.getTileHeight());
        lts.addNode("\n");
        LuaTable efs = new LuaTable();
        if (!map.getTileSets().isEmpty()) {
            efs.addNode("\n");
        }
        for (int j = 0; j < map.getTileSets().size(); j++) {
            LuaTable ef = new LuaTable();
            ef.addNode("id", map.getTileSets().get(j).getIndex() + 1);
            ef.addNode("path", "/image/tileset/" + map.getTileSets().get(j).getTilebmpFile());
            efs.addNode("[" + (map.getTileSets().get(j).getIndex() + 1) + "]", ef);
            if (j != map.getTileSets().size() - 1) {
                efs.addNode("\n");
            }
        }
        lts.addNode("tilesets", efs);
        lts.addNode("\n");
        LuaTable las = new LuaTable();
        int layerNums = map.getLayerArrayList().size();
        int sLayer = 0;
        for (int i = 0; i < layerNums; i++) {
            if (map.getLayerArrayList().get(i) instanceof SpriteLayer) {
                sLayer = i;
            }
        }
        boolean pass[][] = new boolean[map.getHeight()][map.getWidth()];
        for (int m = 0; m < map.getHeight(); m++) {
            for (int k = 0; k < map.getWidth(); k++) {
                pass[m][k] = true;
            }
        }
        int low = 0;
        for (int j = 0; j < layerNums; j++) {
            if (map.getLayerArrayList().get(j) instanceof TileLayer) {
                if (j < sLayer) {
                    low++;
                }
            }
        }
        for (int j = 0; j < layerNums; j++) {
            if (map.getLayerArrayList().get(j) instanceof TileLayer) {
                LuaTable ef = new LuaTable();
                ef.addNode("\n");
                if (j < sLayer) {
                    ef.addNode("deepth", -low + j);
                } else {
                    ef.addNode("deepth", -low + j - 1);
                }
                ef.addNode("\n");
//                LuaTable lhs = new LuaTable();
//                lhs.addNode("\n");
                for (int m = 0; m < map.getHeight(); m++) {
                    LuaTable lws = new LuaTable();
                    for (int k = 0; k < map.getWidth(); k++) {
                        LuaTable ls = new LuaTable();
                        Tile tile = ((TileLayer) map.getLayerArrayList().get(j)).getTileAt(k, m);
                        if (tile == null) {
                            ls.addNode(-1);
                            ls.addNode(-1);
                        } else {
                            ls.addNode(tile.getTileSet().getIndex() + 1);
                            ls.addNode(tile.getIndex());
                            if (!tile.isCross()) {
                                pass[m][k] = false;
                            }
                        }
                        lws.addNode(ls);
                    }
//                    lhs.addNode(lws);
//                    if (m != map.getHeight() - 1) {
//                        lhs.addNode("\n");
//                    }
                    ef.addNode(lws);
                    if (m != map.getHeight() - 1) {
                        ef.addNode("\n");
                    }
                }
//                ef.addNode(lhs);
                las.addNode(ef);
                if (j != map.getLayerArrayList().size() - 1) {
                    las.addNode("\n");
                }
            }
        }
        lts.addNode("layers", las);
        lts.addNode("\n");
        int areaId = 0;
        for (int i = 0; i < layerNums; i++) {
            if (map.getLayerArrayList().get(i) instanceof CollideLayer) {
                Area[][] area = new Area[map.getHeight()][map.getWidth()];
                for (int j = 0; j < map.getHeight(); j++) {
                    for (int k = 0; k < map.getWidth(); k++) {
                        area[j][k] = new Area();
                        area[j][k].setCol(k);
                        area[j][k].setRow(j);
                        if (((CollideLayer) map.getLayerArrayList().get(i)).getCollideAt(k, j) || !pass[j][k]) {
                            area[j][k].setArea(-1);
                        } else {
                            area[j][k].setArea(-2);
                        }
                    }
                }
                for (int j = 0; j < map.getHeight(); j++) {
                    for (int k = 0; k < map.getWidth(); k++) {
                        if (area[j][k].getArea() == -2) {
                            Stack<Area> areaStack = new Stack<Area>();
                            areaStack.add(area[j][k]);
                            while (!areaStack.isEmpty()) {
                                Area are = areaStack.pop();
                                int ax = are.getCol();
                                int ay = are.getRow();
                                if (ax >= 0 && ay >= 0 && ax < map.getWidth() && ay < map.getHeight()
                                        && are.getArea() == area[ay][ax].getArea()) {
                                    area[ay][ax].setArea(areaId);
                                    areaStack.push(new Area(ax, ay - 1));
                                    areaStack.push(new Area(ax, ay + 1));
                                    areaStack.push(new Area(ax - 1, ay));
                                    areaStack.push(new Area(ax + 1, ay));
                                }
                            }
                            areaId++;
                        }
                    }
                }
                LuaTable colhs = new LuaTable();
                colhs.addNode("\n");
                for (int j = 0; j < map.getHeight(); j++) {
                    LuaTable colws = new LuaTable();
                    for (int k = 0; k < map.getWidth(); k++) {
                        colws.addNode(area[j][k].getArea());
                    }
                    colhs.addNode(colws);
                    if (j != map.getHeight() - 1) {
                        colhs.addNode("\n");
                    }
                }
                lts.addNode("areas", colhs);
            }
        }
//        lts.addNode("\n");
//        for (int i = 0; i < layerNums; i++) {
//            if (map.getLayerArrayList().get(i) instanceof SpriteLayer) {
//                LuaTable sphs = new LuaTable();
//                for (int j = 0; j < map.getHeight(); j++) {
//                    for (int k = 0; k < map.getWidth(); k++) {
//                        Npc npc = ((SpriteLayer) map.getLayerArrayList().get(i)).getNpcAt(k, j);
//                        if (npc != null) {
//                            sphs.addNode("\n");
//                            sphs.addNode("[" + (j * map.getWidth() + k) + "]", npc.getIndex() + 1);
//                            INpcWriter npcWriter = new DefaultNpcLuaWriter();
//                            npcWriter.writeNpc(npc, AppData.getInstance().getCurProject().getPath() + File.separatorChar
//                                    + "data" + File.separatorChar + "npc" + File.separatorChar + "npc" + (npc.getIndex() + 1) + ".gat");
//                        }
//                    }
//                }
//                lts.addNode("npcs", sphs);
//            }
//        }
        LuaNode ln = new LuaNode("globalDictionary.maps[" + (map.getIndex() + 1) + "]", lts);
        LuaFileUtil.writeToFile(ln, filename);
    }
}
