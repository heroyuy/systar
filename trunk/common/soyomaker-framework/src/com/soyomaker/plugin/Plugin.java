/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.plugin;

import java.io.File;
import java.net.MalformedURLException;

/**
 *
 * @author like
 */
public class Plugin {

    /**
     * 尚未加载类文件状态
     */
    public static final int NOT_LOAD = 0;               //尚未加载类文件
    /**
     * 正在加载中状态
     */
    public static final int LOADING = 1;                //正在加载中
    /**
     * 已经加载完毕状态
     */
    public static final int ALREADY_LOAD = 2;           //已经加载完毕
    private File file = null;                           //插件文件
    private int id = -1;                                //插件id
    private String mainClass = null;                    //入口类名称
    private String name = "";                           //插件名称
    private String author = "";                         //插件作者
    private String description = "";                    //插件描述
    private String version = "";                        //插件版本
    private int expandPoint = -1;                       //插件扩展功能
    private String minSoftVersion = "0";                //插件需要的最低软件版本
    private PluginLoader pluginPoader = null;           //插件加载器
    private int loadState = 0;                          //插件载入状态
    private Object mainClassInstance = null;            //入口类的实例

    /**
     *
     * @return
     */
    public Object getMainClassInstance() {
        return mainClassInstance;
    }

    /**
     *
     * @param mainClassInstance
     */
    public void setMainClassInstance(Object mainClassInstance) {
        this.mainClassInstance = mainClassInstance;
    }

    /**
     *
     * @return
     */
    public int getLoadState() {
        return loadState;
    }

    /**
     *
     * @param loadState
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
    }

    /**
     *
     * @return
     */
    public PluginLoader getPluginLoader() {
        return pluginPoader;
    }

    /**
     *
     * @param loader
     */
    public void setPluginLoader(PluginLoader loader) {
        this.pluginPoader = loader;
    }

    /**
     * 懒加载插件
     * @throws MalformedURLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void lazyLoad() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (loadState == NOT_LOAD) {
            loadState = LOADING;
            pluginPoader.addPluginURL(file.toURI().toURL());
            mainClassInstance = pluginPoader.findPluginClass(mainClass).newInstance();
            loadState = ALREADY_LOAD;
        }
    }

    /**
     *
     * @return
     */
    public String getMinSoftVersion() {
        return minSoftVersion;
    }

    /**
     *
     * @param minSoftVersion
     */
    public void setMinSoftVersion(String minSoftVersion) {
        this.minSoftVersion = minSoftVersion;
    }

    /**
     *
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getMainClass() {
        return mainClass;
    }

    /**
     *
     * @param main
     */
    public void setMainClass(String main) {
        this.mainClass = main;
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
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    public int getExpandPoint() {
        return expandPoint;
    }

    /**
     *
     * @param view
     */
    public void setExpandPoint(int view) {
        this.expandPoint = view;
    }
}
