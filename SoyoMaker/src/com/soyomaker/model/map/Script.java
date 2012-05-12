/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Script implements Cloneable {

    private int index = -1;             //脚本的id
    private ArrayList<Command> commands = new ArrayList<Command>();
    private NpcState npcState = null;

    @Override
    public Script clone() throws CloneNotSupportedException {
        Script clone = (Script) super.clone();
        clone.commands = new ArrayList<Command>();
        for (int i = 0; i < this.commands.size(); i++) {
            clone.commands.add((Command) commands.get(i).clone());
        }
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
     * @param index
     * @param e
     */
    public void addCommand(int index, Command e) {
        commands.add(index, e);
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean addCommand(Command e) {
        return commands.add(e);
    }

    /**
     * 
     * @param o
     * @return
     */
    public boolean removeCommand(Command o) {
        return commands.remove(o);
    }

    /**
     *
     * @param o
     * @return
     */
    public Command removeCommand(int o) {
        return commands.remove(o);
    }

    /**
     *
     * @param o
     * @return
     */
    public int indexOfCommands(Command o) {
        return commands.indexOf(o);
    }

    /**
     *
     * @return
     */
    public int commandsSize() {
        return commands.size();
    }

    /**
     *
     * @param index
     * @return
     */
    public Command getCommand(int index) {
        return commands.get(index);
    }

    /**
     *
     */
    public void clearCommands() {
        commands.clear();
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
     * @param ID
     */
    public void setIndex(int ID) {
        this.index = ID;
    }
}
