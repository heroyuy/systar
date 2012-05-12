/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

/**
 *
 * @author Administrator
 */
public class Npc implements Cloneable {

    private int index = -1;             //npc序号
    private int mapId = 0;              //初始地图序号
    private int row = 0;                //初始行号
    private int col = 0;                //初始列号
    private String name = "";           //npc名称
    private NpcState npcState = new NpcState();

    @Override
    public String toString() {
        return name + " ID:" + getIndex() + "";
    }

    @Override
    public Npc clone() throws CloneNotSupportedException {
        Npc clone = (Npc) super.clone();
        clone.setNpcState((NpcState) npcState.clone());
        return clone;
    }

    /**
     *
     * @return
     */
    public NpcState getNpcState() {
        return npcState;
    }

    /**
     *
     * @param npcState
     */
    public void setNpcState(NpcState npcState) {
        this.npcState = npcState;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

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
