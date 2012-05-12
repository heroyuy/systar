/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.plugin;

import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author Administrator
 */
public class PluginLoader extends URLClassLoader {

    /**
     *
     */
    public PluginLoader() {
        super(new URL[0]);
    }

    /**
     *
     * @param url
     */
    public void addPluginURL(URL url) {
        this.addURL(url);
    }

    /**
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> findPluginClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
