/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.plugin;

/**
 *
 * @author Administrator
 */
public class Plugin {

    private int id;             //标示

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class getMain() {
        return main;
    }

    public void setMain(Class main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    private Class main;         //入口
    private String name;        //名称
    private String author;      //作者
    private String description; //描述
    private String version;     //版本
    private int view = -1;           //功能

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
    public static final int SERVICE = 0;    //服务
    public static final int MENU = 1;       //菜单
    public static final int TOOLITEM = 2;   //工具菜单菜单项
    public static final int PLUGINITEM = 3; //插件菜单菜单项
    public static final int SIMITEM = 4;    //模拟菜单菜单项
    public static final int HELPITEM = 5;   //帮助菜单菜单项
    public static final int TOOLBUTTON = 6; //工具栏按钮
}
