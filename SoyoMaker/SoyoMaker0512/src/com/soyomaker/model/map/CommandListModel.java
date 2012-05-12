/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

import javax.swing.AbstractListModel;

/**
 *
 * @author Administrator
 */
public class CommandListModel extends AbstractListModel {

    private Script script = null;

    /**
     *
     * @param script
     */
    public void setScript(Script script) {
        this.script = script;
    }

    /**
     *
     * @param id
     */
    public void removeCommand(int id) {
        script.removeCommand(id);
        this.fireIntervalRemoved(this, id, id);
    }

    /**
     *
     * @param e
     */
    public void addCommand(Command e) {
        script.addCommand(e);
        this.fireIntervalAdded(this, script.commandsSize() - 1, script.commandsSize() - 1);
    }

    public int getSize() {
        return script.commandsSize();
    }

    public Object getElementAt(int index) {
        if (index < 0 || index > script.commandsSize() - 1) {
            return null;
        }
        return script.getCommand(index);
    }
}
