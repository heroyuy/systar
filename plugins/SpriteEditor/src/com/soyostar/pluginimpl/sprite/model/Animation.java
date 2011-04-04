/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.pluginimpl.sprite.model;

import com.soyostar.pluginimpl.sprite.listener.AnimationChangeListener;
import com.soyostar.pluginimpl.sprite.listener.AnimationChangedEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Animation {

    private Animation() {
        actions = new ArrayList<Action>();
        tilesets = new ArrayList<TileSet>();
        frames = new ArrayList<Frame>();
    }
    private static Animation ani;

    public static Animation getInstance() {
        if (ani == null) {
            ani = new Animation();
        }
        return ani;
    }
    private String name;
    private String path;
    private byte type = -1;
    public static final byte MANUAL = 0;
    public static final byte AUTOMATIC = 1;
    private int frameWidth;
    private int frameHeight;
    private ArrayList<Action> actions;
    private ArrayList<TileSet> tilesets;
    private ArrayList<Frame> frames;
    private final List animationChangeListeners = new LinkedList();

    /**
     * Adds a change listener. The listener will be notified when the map
     * changes in certain ways.
     *
     * @param listener the change listener to add
     * @see MapChangeListener#mapChanged(MapChangedEvent)
     */
    public void addAnimationChangeListener(AnimationChangeListener listener) {
        animationChangeListeners.add(listener);
    }

    /**
     * Removes a change listener.
     * @param listener the listener to remove
     */
    public void removeAnimationChangeListener(AnimationChangeListener listener) {
        animationChangeListeners.remove(listener);
    }

    /**
     * Notifies all registered map change listeners about a change.
     */
    protected void fireAnimationChanged() {
        Iterator iterator = animationChangeListeners.iterator();
        AnimationChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new AnimationChangedEvent(this);
            }
            ((AnimationChangeListener) iterator.next()).animationChanged(event);
        }
    }

    /**
     * Notifies all registered map change listeners about the removal of a
     * tileset.
     *
     * @param index the index of the removed tileset
     */
    protected void fireActionRemoved(int index) {
        Iterator iterator = animationChangeListeners.iterator();
        AnimationChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new AnimationChangedEvent(this);
            }
            ((AnimationChangeListener) iterator.next()).actionRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about the addition of a
     * tileset.
     *
     * @param layer the new layer
     */
    protected void fireActionAdded(Action act) {
        Iterator iterator = animationChangeListeners.iterator();
        AnimationChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new AnimationChangedEvent(this);
            }
            ((AnimationChangeListener) iterator.next()).actionAdded(event, act);
        }
    }

    /**
     * Notifies all registered map change listeners about the removal of a
     * tileset.
     *
     * @param index the index of the removed tileset
     */
    protected void fireTileSetRemoved(int index) {
        Iterator iterator = animationChangeListeners.iterator();
        AnimationChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new AnimationChangedEvent(this);
            }
            ((AnimationChangeListener) iterator.next()).tileSetRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about the addition of a
     * tileset.
     *
     * @param layer the new layer
     */
    protected void fireTileSetAdded(TileSet act) {
        Iterator iterator = animationChangeListeners.iterator();
        AnimationChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new AnimationChangedEvent(this);
            }
            ((AnimationChangeListener) iterator.next()).tileSetAdded(event, act);
        }
    }

    /**
     * Notifies all registered map change listeners about the removal of a
     * tileset.
     *
     * @param index the index of the removed tileset
     */
    protected void fireFrameRemoved(int index) {
        Iterator iterator = animationChangeListeners.iterator();
        AnimationChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new AnimationChangedEvent(this);
            }
            ((AnimationChangeListener) iterator.next()).frameRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about the addition of a
     * tileset.
     *
     * @param layer the new layer
     */
    protected void fireFrameAdded(Frame frame) {
        Iterator iterator = animationChangeListeners.iterator();
        AnimationChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new AnimationChangedEvent(this);
            }
            ((AnimationChangeListener) iterator.next()).frameAdded(event, frame);
        }
    }

    /**
     *
     * @return
     */
    public Iterator getActions() {
        return actions.iterator();
    }

    public int getActionsCount() {
        return actions.size();
    }

    public void addAction(Action act) {
        actions.add(act);
        fireActionAdded(act);
    }

    public void addAction() {
        Action act = new Action();
        act.setName("新建动作");
        actions.add(act);
        fireActionAdded(act);
    }

    public void removeAction(int index) {
        actions.remove(index);
        fireActionRemoved(index);
    }

    public void removeAction(Action act) {
        final int index = actions.indexOf(act);
        if (index >= 0) {
            actions.remove(index);
            fireActionRemoved(index);
        } else {
            System.out.println("删除动作失败:错误的动作！");
        }
    }

    /**
     *
     */
    public void removeAllActions() {
        actions.clear();
        fireAnimationChanged();
    }

    public Action getAction(int i) {
        if (i < 0 || i >= actions.size()) {
            return null;
        }
        return actions.get(i);
    }

    public Iterator getFrames() {
        return frames.iterator();
    }

    public int getFramesCount() {
        return frames.size();
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
        fireFrameAdded(frame);
    }

    public void addFrame() {
        Frame act = new Frame();
        act.setName("新建帧");
        frames.add(act);
        fireFrameAdded(act);
    }

    public void removeFrame(int index) {
        frames.remove(index);
        fireFrameRemoved(index);
    }

    public void removeFrame(Frame act) {
        final int index = frames.indexOf(act);
        if (index >= 0) {
            frames.remove(index);
            fireFrameRemoved(index);
        } else {
            System.out.println("删除帧失败:错误的帧！");
        }
    }

    /**
     *
     */
    public void removeAllFrames() {
        frames.clear();
        fireAnimationChanged();
    }

    public Frame getFrame(int i) {
        if (i < 0 || i >= frames.size()) {
            return null;
        }
        return frames.get(i);
    }

    /**
     *
     * @return
     */
    public Iterator getTileSets() {
        return tilesets.iterator();
    }

    public int getTileSetsCount() {
        return tilesets.size();
    }

    public void addTileSet(TileSet act) {
        tilesets.add(act);
        fireTileSetAdded(act);
    }

    public void addTileSet() {
        TileSet act = new TileSet();
        act.setName("新建图集");
        tilesets.add(act);
        fireTileSetAdded(act);
    }

    public void removeTileSet(int index) {
        tilesets.remove(index);
        fireTileSetRemoved(index);
    }

    public void removeTileSet(TileSet act) {
        final int index = tilesets.indexOf(act);
        if (index >= 0) {
            tilesets.remove(index);
            fireTileSetRemoved(index);
        } else {
            System.out.println("删除图集失败:错误的图集！");
        }
    }

    /**
     *
     */
    public void removeAllTileSets() {
        tilesets.clear();
        fireAnimationChanged();
    }

    public TileSet getTileSet(int i) {
        if (i < 0 || i >= tilesets.size()) {
            return null;
        }
        return tilesets.get(i);
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        assert type == AUTOMATIC;
        this.frameHeight = frameHeight;
        fireAnimationChanged();
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        assert type == AUTOMATIC;
        this.frameWidth = frameWidth;
        fireAnimationChanged();
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        fireAnimationChanged();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        fireAnimationChanged();
    }
}
