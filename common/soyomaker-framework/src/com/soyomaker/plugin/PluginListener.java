/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.plugin;

import java.io.File;

/**
 *
 * @author like
 */
public interface PluginListener {

    /**
     *
     * @param dir
     */
    public void pluginDirAdded(File dir);

    /**
     *
     * @param dir
     */
    public void pluginDirRemoved(File dir);

    /**
     *
     * @param plugin
     */
    public void pluginAdded(Plugin plugin);

    /**
     *
     * @param plugin
     */
    public void pluginRemoved(Plugin plugin);
}
