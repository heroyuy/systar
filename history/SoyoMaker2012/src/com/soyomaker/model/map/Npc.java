/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

import com.soyomaker.project.Project;
import com.soyomaker.AppData;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Npc {

    private int index = -1;             //npc序号
    private int mapId = 0;             //初始地图序号
    private int row = 0;                //初始行号
    private int col = 0;                //初始列号
    private ArrayList<NpcState> npcStates = new ArrayList<NpcState>();//npc状态数量

    /**
     * 
     * @return
     */
    public int getMapId() {
        return mapId;
    }

    /**
     * 
     * @param mapId
     */
    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    /**
     * 
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * 
     * @param id
     */
    public void setIndex(int id) {
        index = id;
    }

    /**
     * 
     * @param cur
     * @return
     */
    public NpcState getNpcState(int cur) {
        if (cur < 0 || cur >= npcStates.size()) {
            return null;
        }
        return npcStates.get(cur);
    }

    /**
     * 
     * @return
     */
    public ArrayList<NpcState> getNpcStates() {
        return npcStates;
    }

    /**
     * 
     * @param state
     */
    public void addNpcState(NpcState state) {
        AppData.getInstance().getCurProject().addScript(state.getScript());//给script file分配一个全局id
        npcStates.add(state);
    }

    /**
     * 
     * @param state
     */
    public void removeNpcState(NpcState state) {
        npcStates.remove(state);
    }

    /**
     * 
     * @param id
     */
    public void removeNpcState(int id) {
        npcStates.remove(id);
    }

    /**
     * 
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * 
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * 
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * 
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }
}
