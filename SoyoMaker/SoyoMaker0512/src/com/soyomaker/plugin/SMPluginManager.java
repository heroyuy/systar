/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.plugin;

import com.soyomaker.infomation.SoftInformation;
import com.soyomaker.log.LogPrinter;
import com.soyomaker.log.LogPrinterFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.dom4j.DocumentException;

/**
 *
 * @author Administrator
 */
public class SMPluginManager {

    /**
     *
     */
    public static final int SERVICE = 0;    //服务
    /**
     *
     */
    public static final int MENU = 1;       //菜单
    /**
     *
     */
    public static final int TOOLITEM = 2;   //工具菜单菜单项
    /**
     *
     */
    public static final int PLUGINITEM = 3; //插件菜单菜单项
    /**
     *
     */
    public static final int SIMITEM = 4;    //模拟菜单菜单项
    /**
     *
     */
    public static final int HELPITEM = 5;   //帮助菜单菜单项
    /**
     *
     */
    public static final int TOOLBUTTON = 6; //工具栏按钮
    private static SMPluginManager manager = new SMPluginManager();
    private ArrayList<JMenuItem> simPlugins = new ArrayList<JMenuItem>();
    private ArrayList<JMenuItem> toolPlugins = new ArrayList<JMenuItem>();
    private ArrayList<JMenuItem> pluginPlugins = new ArrayList<JMenuItem>();
    private ArrayList<JMenuItem> helpPlugins = new ArrayList<JMenuItem>();
    private ArrayList<JButton> toolBarPlugins = new ArrayList<JButton>();
    private ArrayList<JMenu> menuPlugins = new ArrayList<JMenu>();
    private int servicePluginsNum = 0;
    private LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();
    private PluginManager pluginManager = PluginManager.getInstance();
    private ArrayList<Plugin> plugins = new ArrayList<Plugin>();

    /**
     *
     * @return
     */
    public static SMPluginManager getInstance() {
        return manager;
    }

    /**
     *
     * @return
     */
    public ArrayList<Plugin> getPlugins() {
        return plugins;
    }

    private boolean isPluginType(int id, int view) {
        Plugin p = pluginManager.getPlugins().get(id);
        if (p.getExpandPoint() == view) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param file
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DocumentException
     */
    public void readPluginDir(File file) throws IOException, ClassNotFoundException, DocumentException {
        if (!file.exists()) {
            file.mkdirs();
        }
        pluginManager.readPluginDir(file);
        for (int i = 0; i < pluginManager.getPluginNum(); i++) {
            Plugin p = pluginManager.getPlugins().get(i);
            String softVersion = SoftInformation.getVersion();
//            System.out.println(p.getMinSoftVersion() + ":" + softVersion + " " + p.getMinSoftVersion().compareTo(softVersion));
            if (p.getMinSoftVersion().compareTo(softVersion) > 0) {
                continue;
            }
            plugins.add(p);
            if (isPluginType(i, SIMITEM)) {
                simPlugins.add(getMenuItem(i));
            }
            if (isPluginType(i, HELPITEM)) {
                helpPlugins.add(getMenuItem(i));
            }
            if (isPluginType(i, MENU)) {
                menuPlugins.add(getMenu(i));
            }
            if (isPluginType(i, PLUGINITEM)) {
                pluginPlugins.add(getMenuItem(i));
            }
            if (isPluginType(i, TOOLITEM)) {
                toolPlugins.add(getMenuItem(i));
            }
            if (isPluginType(i, TOOLBUTTON)) {
                toolBarPlugins.add(getButton(i));
            }
            if (isPluginType(i, SERVICE)) {
                servicePluginsNum++;
            }
        }
    }

    /**
     *
     * @return
     */
    public int getSimPluginNum() {
        return simPlugins.size();
    }

    /**
     *
     * @return
     */
    public int getToolPluginNum() {
        return toolPlugins.size();
    }

    /**
     *
     * @return
     */
    public int getPluginPluginNum() {
        return pluginPlugins.size();
    }

    /**
     *
     * @return
     */
    public int getHelpPluginNum() {
        return helpPlugins.size();
    }

    /**
     *
     * @return
     */
    public int getToolBarPluginNum() {
        return toolBarPlugins.size();
    }

    /**
     *
     * @return
     */
    public int getMenuPluginNum() {
        return menuPlugins.size();
    }

    /**
     *
     * @return
     */
    public ArrayList<JMenuItem> getHelpPlugins() {
        return helpPlugins;
    }

    /**
     *
     * @return
     */
    public ArrayList<JMenu> getMenuPlugins() {
        return menuPlugins;
    }

    /**
     *
     * @return
     */
    public ArrayList<JMenuItem> getPluginPlugins() {
        return pluginPlugins;
    }

    /**
     *
     * @return
     */
    public ArrayList<JButton> getToolBarPlugins() {
        return toolBarPlugins;
    }

    /**
     *
     * @return
     */
    public ArrayList<JMenuItem> getToolPlugins() {
        return toolPlugins;
    }

    /**
     *
     * @return
     */
    public ArrayList<JMenuItem> getSimPlugins() {
        return simPlugins;
    }

    private JButton getButton(int id) {
        final Plugin p = pluginManager.getPlugins().get(id);
        JButton item = new JButton(p.getName());

        item.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    if (p.getLoadState() == Plugin.NOT_LOAD) {
                        p.lazyLoad();
                    }
                    IPlugin ip = null;
                    Object o = p.getMainClassInstance();
                    ip = (IPlugin) o;
                    ip.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return item;
    }

    private JMenu getMenu(int id) {
        final Plugin p = pluginManager.getPlugins().get(id);
        JMenu item = new JMenu(p.getName());
        item.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    if (p.getLoadState() == Plugin.NOT_LOAD) {
                        p.lazyLoad();
                    }
                    IPlugin ip = null;
                    Object o = p.getMainClassInstance();
                    ip = (IPlugin) o;
                    ip.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return item;
    }

    private JMenuItem getMenuItem(int id) {
        final Plugin p = pluginManager.getPlugins().get(id);
        JMenuItem item = new JMenuItem(p.getName());
        item.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    if (p.getLoadState() == Plugin.NOT_LOAD) {
                        p.lazyLoad();
                    }
                    IPlugin ip = null;
                    Object o = p.getMainClassInstance();
                    ip = (IPlugin) o;
                    ip.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return item;
    }
}
