/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.project;

import com.soyomaker.data.DataManager;
import com.soyomaker.listener.ProjectChangeListener;
import com.soyomaker.listener.ProjectChangedEvent;
import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Clip;
import com.soyomaker.model.animation.Picture;
import com.soyomaker.model.map.Layer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.Npc;
import com.soyomaker.model.map.Script;
import com.soyomaker.model.map.SpriteLayer;
import com.soyomaker.model.map.TileSet;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Administrator
 */
public class Project {

    private String name = "";//项目标题名
    private String path = "";//路径名
    private String createSoftVersion = "";//创建项目的软件版本
    private String createTime = "";//创建项目的时间
    private String lastSaveSoftVersion = "";//保存项目的软件版本
    private String lastSaveTime = "";//保存项目的时间
    private final List projectChangeListeners = new LinkedList();
    //FIXME应该加入一个MapManager类来管理map,npc和scriptfile
    private HashMap<Integer, Map> maps = new HashMap<Integer, Map>();
    private HashMap<Integer, Npc> npcs = new HashMap<Integer, Npc>();
    private HashMap<Integer, Script> scripts = new HashMap<Integer, Script>();
    //FIXME应该加入一个AnimationManager类来管理animation,picture和clip
    private HashMap<Integer, Animation> anis = new HashMap<Integer, Animation>();
    private ArrayList<Picture> pictures = new ArrayList<Picture>();
    private ArrayList<Clip> clips = new ArrayList<Clip>();
    private ArrayList<TileSet> tilesets = new ArrayList<TileSet>();
    private DataManager dataManager = new DataManager();

    /**
     *
     * @param lastSaveSoftVersion
     */
    public void setLastSaveSoftVersion(String lastSaveSoftVersion) {
        this.lastSaveSoftVersion = lastSaveSoftVersion;
    }

    /**
     *
     * @param lastSaveTime
     */
    public void setLastSaveTime(String lastSaveTime) {
        this.lastSaveTime = lastSaveTime;
    }

    /**
     *
     * @return
     */
    public String getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     *
     * @return
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     *
     * @param time
     */
    public void setCreateTime(String time) {
        this.createTime = time;
    }

    /**
     *
     * @return
     */
    public String getLastSaveSoftVersion() {
        return lastSaveSoftVersion;
    }

    /**
     *
     * @return
     */
    public ArrayList<Clip> getClips() {
        return clips;
    }

    /**
     *
     * @param clips
     */
    public void setClips(ArrayList<Clip> clips) {
        this.clips = clips;
    }

    /**
     *
     * @param clip
     */
    public void addClip(Clip clip) {
        clips.add(clip);
    }

    /**
     *
     * @param id
     */
    public void removeClip(int id) {
        clips.remove(id);
    }

    /**
     *
     * @return
     */
    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    /**
     *
     * @param pic
     */
    public void addPicture(Picture pic) {
        pictures.add(pic);
        firePictureAdded(pic);
    }

    /**
     *
     * @param index
     */
    public void removePicture(int index) {
        pictures.remove(index);
        firePictureRemoved(index);
    }

    /**
     *
     * @param index
     * @return
     */
    public Picture getPicture(int index) {
        if (index < 0 || index > pictures.size() - 1) {
            return null;
        }
        return pictures.get(index);
    }

    /**
     *
     * @return
     */
    public ArrayList<TileSet> getTileSets() {
        return tilesets;
    }

    /**
     *
     * @return
     */
    public HashMap<Integer, Animation> getAnimations() {
        return anis;
    }

    /**
     * 
     * @return
     */
    public HashMap<Integer, Map> getMaps() {
        return maps;
    }

    /**
     * 
     * @return
     */
    public HashMap<Integer, Npc> getNpcs() {
        return npcs;
    }

    /**
     * 
     * @return
     */
    public HashMap<Integer, Script> getScripts() {
        return scripts;
    }

    /**
     * 
     * @return
     */
    public DataManager getDataManager() {
        return dataManager;
    }

    /**
     * 
     * @param ani
     */
    public void addAnimation(Animation ani) {
        int id = getAnimationMaxIndex() + 1;
        anis.put(id, ani);
        ani.setIndex(id);
        fireAnimationAdded(ani);
    }

    /**
     * 
     * @param ani
     * @param id
     */
    public void addAnimation(Animation ani, int id) {
        anis.put(id, ani);
        fireAnimationAdded(ani);
    }

    /**
     * 
     * @param map
     */
    public void addMap(Map map) {
        int id = getMapMaxIndex() + 1;
        maps.put(id, map);
        map.setIndex(id);
        fireMapAdded(map);
    }

    /**
     * 
     * @param map
     * @param id
     */
    public void addMap(Map map, int id) {
        maps.put(id, map);
        fireMapAdded(map);
    }

    /**
     *
     * @param script
     */
    public void replaceScript(Script script) {
        if (scripts.containsKey(script.getIndex())) {
            scripts.put(script.getIndex(), script);
        } else {
            int id = getScriptMaxIndex() + 1;
            scripts.put(id, script);
            script.setIndex(id);
            fireScriptAdded(script);
        }
    }

    /**
     *
     * @param npc
     */
    public void replaceNpc(Npc npc) {
        if (npcs.containsKey(npc.getIndex())) {
            npcs.put(npc.getIndex(), npc);
        } else {
            int id = getNpcMaxIndex() + 1;
            npcs.put(id, npc);
            npc.setIndex(id);
            fireNpcAdded(npc);
        }
    }

    /**
     * 
     * @param npc 
     */
    public void addNpc(Npc npc) {
        if (!npcs.containsValue(npc)) {
            int id = getNpcMaxIndex() + 1;
            npcs.put(id, npc);
            npc.setIndex(id);
            fireNpcAdded(npc);
        }
    }

    /**
     *
     * @param file
     * @return
     */
    public int indexOfTileSet(TileSet file) {
        for (int i = 0; i < tilesets.size(); i++) {
            if (tilesets.get(i).getTilebmpFile().getName().equals(file.getTilebmpFile().getName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param file
     */
    public void addTileSet(TileSet file) {
        for (int i = 0; i < tilesets.size(); i++) {
            if (tilesets.get(i).getTilebmpFile().getName().equals(file.getTilebmpFile().getName())) {
                return;
            }
        }
        tilesets.add(file);
    }

    /**
     * 
     * @param file 
     */
    public void addScript(Script file) {
        int id = getScriptMaxIndex() + 1;
        scripts.put(id, file);
        file.setIndex(id);
        fireScriptAdded(file);
    }

    /**
     * 
     * @param npc
     * @param id
     */
    public void addNpc(Npc npc, int id) {
        if (!npcs.containsValue(npc)) {
            npcs.put(id, npc);
            fireNpcAdded(npc);
        }
    }

    /**
     * 
     * @param file
     * @param id
     */
    public void addScript(Script file, int id) {
        scripts.put(id, file);
        fireScriptAdded(file);
    }

    /**
     *
     */
    public void removeAllTileSet() {
        tilesets.clear();
    }

    /**
     * 
     */
    public void removeAllAnimation() {
        anis.clear();
    }

    /**
     * 
     */
    public void removeAllMap() {
        maps.clear();
    }

    /**
     * 
     */
    public void removeAllNpc() {
        npcs.clear();
    }

    /**
     * 
     */
    public void removeAllSript() {
        scripts.clear();
    }

    /**
     *
     * @param index
     */
    public void removeTileSet(int index) {
        tilesets.remove(index);
    }

    /**
     * 
     * @param index
     */
    public void removeAnimation(int index) {
        anis.remove(index);
        fireAnimationRemoved(index);
    }

    /**
     * 
     * @param index
     */
    public void removeMap(int index) {
        Map map = maps.remove(index);
        if (map == null) {
            return;
        }
        //删除地图时也删除上面的npc
        for (int i = 0; i < map.getTotalLayers(); i++) {
            Layer layer = map.getLayer(i);
            if (layer instanceof SpriteLayer) {
                SpriteLayer slayer = (SpriteLayer) layer;
                for (int y = 0; y < slayer.getHeight(); y++) {
                    for (int x = 0; x < slayer.getWidth(); x++) {
                        Npc npc = slayer.getNpcAt(x, y);
                        if (npc != null) {
                            removeNpc(npc.getIndex());
                        }
                    }
                }
            }
        }
        fireMapRemoved(index);
    }

    /**
     * 
     * @param index
     */
    public void removeNpc(int index) {
        Npc npc = npcs.remove(index);
        if (npc == null) {
            return;
        }
        //删除npc时，把其中的脚本也删掉
        int sid = npc.getNpcState().getScript().getIndex();
        scripts.remove(sid);
        fireNpcRemoved(index);
    }

    /**
     * 
     * @param index
     */
    public void removeScript(int index) {
        scripts.remove(index);
        fireScriptRemoved(index);
    }

    /**
     *
     * @param id
     * @return
     */
    public TileSet getTileSet(int id) {
        return tilesets.get(id);
    }

    /**
     * 
     * @param id
     * @return
     */
    public Animation getAnimation(int id) {
        return anis.get(id);
    }

    /**
     * 
     * @param id
     * @return
     */
    public Map getMap(int id) {
        return maps.get(id);
    }

    /**
     * 
     * @param id
     * @return
     */
    public Npc getNpc(int id) {
        return npcs.get(id);
    }

    /**
     * 
     * @param id
     * @return
     */
    public Script getScript(int id) {
        return scripts.get(id);
    }

    /**
     *
     * @return
     */
    public int getTileSetCounts() {
        return tilesets.size();
    }

    /**
     * 
     * @return
     */
    public int getAnimationCounts() {
        return anis.size();
    }

    /**
     * 
     * @return
     */
    public int getMapCounts() {
        return maps.size();
    }

    /**
     * 
     * @return
     */
    public int getNpcCounts() {
        return npcs.size();
    }

    /**
     * 
     * @return
     */
    public int getScriptCounts() {
        return scripts.size();
    }

    /**
     * 
     * @return
     */
    public int getAnimationMaxIndex() {
        int max = -1;
        Set<Integer> aniset = anis.keySet();
        Iterator it = aniset.iterator();
        while (it.hasNext()) {
            Integer in = (Integer) it.next();
            if (in > max) {
                max = in;
            }
        }
        return max;
    }

    /**
     * 
     * @return
     */
    public int getMapMaxIndex() {
        int max = -1;
        Set<Integer> mapset = maps.keySet();
        Iterator it = mapset.iterator();
        while (it.hasNext()) {
            Integer in = (Integer) it.next();
            if (in > max) {
                max = in;
            }
        }
        return max;
    }

    /**
     * 
     * @return
     */
    public int getNpcMaxIndex() {
        int max = -1;
        Set<Integer> npcset = npcs.keySet();
        Iterator it = npcset.iterator();
        while (it.hasNext()) {
            Integer in = (Integer) it.next();
            if (in > max) {
                max = in;
            }
        }
        return max;
    }

    /**
     * 
     * @return
     */
    public int getScriptMaxIndex() {
        int max = -1;
        Set<Integer> scriptset = scripts.keySet();
        Iterator it = scriptset.iterator();
        while (it.hasNext()) {
            Integer in = (Integer) it.next();
            if (in > max) {
                max = in;
            }
        }
        return max;
    }

    /**
     * 
     * @param listener
     */
    public void addProjectChangeListener(ProjectChangeListener listener) {
        projectChangeListeners.add(listener);
    }

    /**
     * Removes a change listener.
     * @param listener the listener to remove
     */
    public void removeProjectChangeListener(ProjectChangeListener listener) {
        projectChangeListeners.remove(listener);
    }

    /**
     * Notifies all registered map change listeners about a change.
     */
    protected void fireProjectChanged() {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).projectChanged(event);
        }
    }

    /**
     * Notifies all registered map change listeners about a change.
     * @param map 
     */
    protected void fireMapAdded(Map map) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).mapAdded(event, map);
        }
    }

    /**
     * Notifies all registered map change listeners about a change.
     * @param index
     */
    protected void firePictureRemoved(int index) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).pictureRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about a change.
     * @param pic
     */
    protected void firePictureAdded(Picture pic) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).pictureAdded(event, pic);
        }
    }

    /**
     * Notifies all registered map change listeners about a change.
     * @param index 
     */
    protected void fireAnimationRemoved(int index) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).animationRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about a change.
     * @param ani
     */
    protected void fireAnimationAdded(Animation ani) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).animationAdded(event, ani);
        }
    }

    /**
     * Notifies all registered map change listeners about a change.
     * @param index 
     */
    protected void fireMapRemoved(int index) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).mapRemoved(event, index);
        }
    }

    /**
     * Notifies all registered npc change listeners about a change.
     * @param npc
     */
    protected void fireNpcAdded(Npc npc) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).npcAdded(event, npc);
        }
    }

    /**
     * Notifies all registered npc change listeners about a change.
     * @param file 
     */
    protected void fireScriptAdded(Script file) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).scriptAdded(event, file);
        }
    }

    /**
     * Notifies all registered map change listeners about a change.
     * @param index 
     */
    protected void fireNpcRemoved(int index) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).npcRemoved(event, index);
        }
    }

    /**
     * Notifies all registered map change listeners about a change.
     * @param index 
     */
    protected void fireScriptRemoved(int index) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).scriptRemoved(event, index);
        }
    }

    /**
     *
     * @return
     */
    public String getCreateSoftVersion() {
        return createSoftVersion;
    }

    /**
     *
     * @param softVersion
     */
    public void setCreateSoftVersion(String softVersion) {
        this.createSoftVersion = softVersion;
        fireProjectChanged();
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
        fireProjectChanged();
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
        fireProjectChanged();
    }

    /**
     *
     */
    public static final class MapFilter extends FileFilter {

        /**
         *
         */
        public static final String END = ".gat";
        /**
         *
         */
        public static final String START = "map";

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            if (f.getName().endsWith(END) && f.getName().startsWith(START)) {
                return true;
            }
            return false;
        }

        public String getDescription() {
            return "SM地图文件 (" + START + "*" + END + ")";
        }
    }

    /**
     *
     */
    public static final class ProjectFilter extends FileFilter {

        /**
         *
         */
        public static final String END = ".smproj";

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            if (f.getName().endsWith(END)) {
                return true;
            }
            return false;
        }

        public String getDescription() {
            return "SM工程文件 (*" + END + ")";
        }
    }
}
