/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import com.soyostar.pluginimpl.sprite.listener.ActionChangeListener;
import com.soyostar.pluginimpl.sprite.listener.ActionChangedEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Action {

    private ArrayList<Sequence> sequences;
    private String name = "新建动作";
    protected final List<ActionChangeListener> actionChangeListeners = new LinkedList();

    public Action() {
        sequences = new ArrayList<Sequence>();
    }

    public void addActionChangeListener(ActionChangeListener listener) {
        actionChangeListeners.add(listener);
//        System.out.println("ls:" + layerChangeListeners.size());
    }

    public void removeActionChangeListener(ActionChangeListener listener) {
        actionChangeListeners.remove(listener);
    }

    protected void fireActionChanged() {
        ActionChangedEvent event = new ActionChangedEvent(this);
        for (ActionChangeListener listener : actionChangeListeners) {
            listener.actionChanged(event);
        }
    }

    protected void fireNameChanged(String oldName, String newName) {
        ActionChangedEvent event = new ActionChangedEvent(this);
        for (ActionChangeListener listener : actionChangeListeners) {
            listener.nameChanged(event, oldName, newName);
        }
    }

    protected void fireAddSequenceChanged(Sequence seq) {
        ActionChangedEvent event = new ActionChangedEvent(this);

        for (ActionChangeListener listener : actionChangeListeners) {
            listener.sequenceAdded(event, seq);
        }
    }

    protected void fireRemoveSequenceChanged(int index) {
        ActionChangedEvent event = new ActionChangedEvent(this);

        for (ActionChangeListener listener : actionChangeListeners) {
            listener.sequenceRemoved(event, index);
        }
    }

    /**
     * Moves the layer at <code>index</code> up one in the vector.
     *
     * @param index the index of the layer to swap up
     */
    public void swapSequenceDown(int index) {
        if (index + 1 == sequences.size()) {
            throw new RuntimeException(
                "Can't swap up when already at the top.");
        }
        Sequence hold = sequences.get(index + 1);
        sequences.set(index + 1, getSequence(index));
        sequences.set(index, hold);
        fireActionChanged();
    }

    /**
     * Moves the layer at <code>index</code> down one in the vector.
     *
     * @param index the index of the layer to swap down
     */
    public void swapSequenceUp(int index) {
        if (index - 1 < 0) {
            throw new RuntimeException(
                "Can't swap down when already at the bottom.");
        }
        Sequence hold = sequences.get(index - 1);
        sequences.set(index - 1, getSequence(index));
        sequences.set(index, hold);
        fireActionChanged();
    }

    public Sequence getSequence(int id) {
        if (id < 0 || id >= sequences.size()) {
            return null;
        }
        return sequences.get(id);
    }

    /**
     *
     * @return
     */
    public Iterator getSequences() {
        return sequences.iterator();
    }

    public int getSequencesCount() {
        return sequences.size();
    }

    public void addSequence(Sequence seq) {
        sequences.add(seq);
        fireAddSequenceChanged(seq);
    }

    public void removeSequence(Sequence seq) {
        int id = sequences.indexOf(seq);
        if (id >= 0) {
            sequences.remove(id);
            fireRemoveSequenceChanged(id);
        } else {
            System.out.println("错误的序列");
        }
    }

    public void removeSequence(int id) {
        if (id >= 0) {
            sequences.remove(id);
            fireRemoveSequenceChanged(id);
        } else {
            System.out.println("错误的序列");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String na = this.name;
        this.name = name;
        fireNameChanged(na, name);
    }
}
