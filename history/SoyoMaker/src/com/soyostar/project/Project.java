/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.project;

import com.soyostar.listener.ProjectChangeListener;
import com.soyostar.listener.ProjectChangedEvent;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.MapSet;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
    private static Project project;
    /**
     *
     */
    private MapSet mapSetBase = new MapSet();//所有的地图

    public MapSet getMapSetBase() {
        return mapSetBase;
    }

    public void setMapSetBase(MapSet mapSetBase) {
        this.mapSetBase = mapSetBase;
    }

    public static Project getInstance() {
        if (project == null) {
            project = new Project();
        }
        return project;
    }

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
     */
    protected void fireMapRemoved(int index) {
        Iterator iterator = projectChangeListeners.iterator();
        ProjectChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new ProjectChangedEvent(this);
            }
            ((ProjectChangeListener) iterator.next()).mapRemoved(event,index);
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
