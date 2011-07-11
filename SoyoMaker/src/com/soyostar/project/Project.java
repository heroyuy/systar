/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.project;

import com.soyostar.data.DataManager;
import com.soyostar.listener.ProjectChangeListener;
import com.soyostar.listener.ProjectChangedEvent;
import com.soyostar.model.animation.Animation;
import com.soyostar.model.animation.Picture;
import com.soyostar.model.map.Map;
//import com.soyostar.model.map.MapSet;
import com.soyostar.model.map.Npc;
import com.soyostar.model.map.ScriptFile;
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
    private String softVersion = "";//项目对应的软件版本
    private final List projectChangeListeners = new LinkedList();
    private HashMap<Integer, Map> maps = new HashMap<Integer, Map>();
    private HashMap<Integer, Npc> npcs = new HashMap<Integer, Npc>();
    private HashMap<Integer, Animation> anis = new HashMap<Integer, Animation>();
    private ArrayList<Picture> pictures = new ArrayList<Picture>();
    private HashMap<Integer, ScriptFile> scripts = new HashMap<Integer, ScriptFile>();
    private DataManager dataManager = new DataManager();

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void addPicture(Picture pic) {
        pictures.add(pic);
    }

    public void removePicture(int index) {
        pictures.remove(index);
    }

    public Picture getPicture(int index) {
        return pictures.get(index);
    }

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
    public HashMap<Integer, ScriptFile> getScripts() {
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
     * @param map
     */
    public void addAnimation(Animation ani) {
        int id = getMapMaxIndex() + 1;
        anis.put(id, ani);
        ani.setIndex(id);
        fireAnimationAdded(ani);
    }

    /**
     * 
     * @param map
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
     * @param npc 
     */
    public void addNpc(Npc npc) {
        int id = getNpcMaxIndex() + 1;
        npcs.put(id, npc);
        npc.setIndex(id);
        fireNpcAdded(npc);
    }

    /**
     * 
     * @param file 
     */
    public void addScript(ScriptFile file) {
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
        npcs.put(id, npc);
        fireNpcAdded(npc);
    }

    /**
     * 
     * @param file
     * @param id
     */
    public void addScript(ScriptFile file, int id) {
        scripts.put(id, file);
        fireScriptAdded(file);
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
    public void removeAnimation(int index) {
        anis.remove(index);
        fireAnimationRemoved(index);
    }

    /**
     * 
     * @param index
     */
    public void removeMap(int index) {
        maps.remove(index);
        fireMapRemoved(index);
    }

    /**
     * 
     * @param index
     */
    public void removeNpc(int index) {
        npcs.remove(index);
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
    public ScriptFile getScript(int id) {
        return scripts.get(id);
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
     * @param map 
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
    protected void fireScriptAdded(ScriptFile file) {
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
    public String getSoftVersion() {
        return softVersion;
    }

    /**
     *
     * @param softVersion
     */
    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
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
    public static final class Filter extends FileFilter {

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            if (f.getName().equals("Project.xml")) {
                return true;
            }
            return false;
        }

        public String getDescription() {
            return "工程文件 (Project.xml)";
        }
    }
}
