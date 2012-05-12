/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.plugin;

import com.soyomaker.log.LogPrinter;
import com.soyomaker.log.LogPrinterFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 插件管理器
 * lazy start 采用懒加载，读取插件时只读取其xml信息
 * 支持多插件目录
 * 支持插件动态安装与卸载
 * 
 * @author like
 */
public class PluginManager {

    private ArrayList<Plugin> plugins = new ArrayList<Plugin>();
    private ArrayList<PluginListener> listeners = new ArrayList<PluginListener>();
    private static LogPrinter printer = LogPrinterFactory.getDefaultLogPrinter();
    private static PluginManager manager = new PluginManager();
    private ArrayList<File> dirs = new ArrayList<File>();

    private PluginManager() {
    }

    /**
     * 
     * @param args
     */
    public static void main(String args[]) {
        PluginManager pm = new PluginManager();
        pm.addPluginDir(new File("plugin"));//测试添加正常目录
//        pm.addPluginDir(new File("plugin2"));//测试包含错误文件的目录
//        pm.addPluginDir(new File("plugin3"));//测试继续添加正常目录
        try {
            pm.readPluginDirs();
//            pm.readPluginDir(new File("plugin3"));//测试重复添加目录
//            pm.readPluginDir(new File("plugin3/plugin"));//测试重复添加子目录
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (int i = 0, n = pm.getPluginNum(); i < n; i++) {
            Plugin plugin = pm.getPlugins().get(i);
            printer.v(plugin.getFile().getAbsolutePath());
            printer.e(plugin.getId());
            printer.v(plugin.getMainClass());
            printer.v(plugin.getName());
            printer.v(plugin.getDescription());
            printer.v(plugin.getAuthor());
            printer.v(plugin.getVersion());
            printer.v(plugin.getExpandPoint());
            printer.v(plugin.getMinSoftVersion());
            printer.v("-------------------------");
        }
        printer.w("插件总数：" + pm.getPluginNum());
    }

    /**
     *
     * @param o
     * @return
     */
    public boolean removePluginListener(PluginListener o) {
        return listeners.remove(o);
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean addPluginListener(PluginListener e) {
        return listeners.add(e);
    }

    /**
     *
     * @return
     */
    public static PluginManager getInstance() {
        return manager;
    }

    /**
     * 得到插件系统的插件总个数
     * @return
     */
    public int getPluginNum() {
        return plugins.size();
    }

    /**
     * 得到插件系统的整个插件列表
     * @return
     */
    public ArrayList<Plugin> getPlugins() {
        return plugins;
    }

    private void listFile(File f, ArrayList<File> files, String... filters) {
        if (f.isDirectory()) {
            for (int m = 0; m < filters.length; m++) {
                final String filter = filters[m];
                File[] t = f.listFiles(new FileFilter() {

                    public boolean accept(File dir) {
                        if (dir.isDirectory()) {
                            return true;
                        }
                        if (dir.getName().toLowerCase().endsWith(filter.toLowerCase())) {
                            return true;
                        }
                        return false;
                    }
                });
                for (int i = 0; i < t.length; i++) {
                    listFile(t[i], files, filter);
                }
            }
        } else {
            files.add(f);
        }
    }

    /**
     * 添加插件目录
     * @param dir
     */
    public void addPluginDir(File dir) {
        //当监听目录表已经存在该目录时，不重复加入插件监听目录
        for (int i = 0, n = dirs.size(); i < n; i++) {
            if (dirs.get(i).getAbsolutePath().toLowerCase().equals(dir.getAbsolutePath().toLowerCase())) {
                return;
            }
        }
        dirs.add(dir);
        for (int i = 0, n = listeners.size(); i < n; i++) {
            listeners.get(i).pluginDirAdded(dir);
        }
    }

    /**
     * 删除插件目录
     * @param dir
     */
    public void removePluginDir(File dir) {
        File remove = null;
        //检查监听目录表中是否存在该文件夹，如果已存在则进行删除
        for (int i = 0, n = dirs.size(); i < n; i++) {
            if (dirs.get(i).getAbsolutePath().toLowerCase().equals(dir.getAbsolutePath().toLowerCase())) {
                remove = dirs.get(i);
                break;
            }
        }
        if (remove != null) {
            dirs.remove(remove);
            for (int i = 0, n = listeners.size(); i < n; i++) {
                listeners.get(i).pluginDirRemoved(remove);
            }
        }
    }

    private void removePlugin(Plugin plugin) {
        Plugin p = null;
        for (int i = 0, n = plugins.size(); i < n; i++) {
            if (plugins.get(i).getFile().getAbsolutePath().equals(plugin.getFile().getAbsolutePath())) {
                p = plugins.get(i);
                break;
            }
        }
        if (p != null) {
            plugins.remove(p);
            for (int i = 0, n = listeners.size(); i < n; i++) {
                listeners.get(i).pluginRemoved(p);
            }
        }
    }

    private void addPlugin(Plugin plugin) {
        //如果已经添加过改插件，则不再进行添加
        for (int i = 0, n = plugins.size(); i < n; i++) {
            if (plugins.get(i).getFile().getAbsolutePath().equals(plugin.getFile().getAbsolutePath())) {
                return;
            }
        }
        plugins.add(plugin);
        for (int i = 0, n = listeners.size(); i < n; i++) {
            listeners.get(i).pluginAdded(plugin);
        }
    }

    /**
     * 读取插件文件夹
     * @param f 存放插件的文件夹
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DocumentException
     */
    public void readPluginDir(File f) throws IOException, ClassNotFoundException, DocumentException {
        ArrayList<File> files = new ArrayList<File>();
        listFile(f, files, "jar");//支持将插件放到各自的文件夹中

        int n = files.size();
        for (int i = 0; i < n; i++) {
            //未避免插件的类名空间相互影响，对每个插件都采用不同的PluginLoader对象，以隔绝类名空间
            PluginLoader loader = new PluginLoader();
            File file = files.get(i);
            String path = file.getAbsolutePath();
            Plugin plugin = getPlugin(loader, path);
            if (plugin != null) {
                addPlugin(plugin);
            }
        }
    }

    /**
     * 根据存放插件的文件夹列表读取插件
     * @throws MalformedURLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DocumentException
     */
    public void readPluginDirs() throws MalformedURLException, IOException, ClassNotFoundException, DocumentException {
        for (int z = 0, zn = dirs.size(); z < zn; z++) {
            File f = dirs.get(z);
            if (!f.exists() || !f.isDirectory()) {
                f.mkdir();//假如目录不存在，或者不是文件夹时，创建该文件夹
            }
            readPluginDir(f);
        }
    }

    private Plugin getPlugin(PluginLoader loader, String path) throws IOException, DocumentException, ClassNotFoundException {
        File xmlFile = new File(path.substring(0, path.lastIndexOf(".")) + ".xml");
        if (xmlFile.exists() && xmlFile.isFile()) {
            Plugin plugin = new Plugin();
            plugin.setFile(new File(path));
            plugin.setPluginLoader(loader);
            plugin.setId(getPluginNum());
            SAXReader sax = new SAXReader();
            InputStream ifile = new FileInputStream(xmlFile);
            InputStreamReader ir = new InputStreamReader(ifile, "UTF-8");
            Document document = sax.read(ir);
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator("Plugin-ExpandPoint"); i.hasNext();) {
                Element expand = (Element) i.next();
                plugin.setExpandPoint(Integer.parseInt(expand.getText()));
            }
            for (Iterator i = root.elementIterator("Plugin-MainClass"); i.hasNext();) {
                Element main = (Element) i.next();
                plugin.setMainClass(main.getText());
            }
            for (Iterator i = root.elementIterator("Plugin-Name"); i.hasNext();) {
                Element name = (Element) i.next();
                plugin.setName(name.getText());
            }
            for (Iterator i = root.elementIterator("Plugin-Author"); i.hasNext();) {
                Element author = (Element) i.next();
                plugin.setAuthor(author.getText());
            }
            for (Iterator i = root.elementIterator("Plugin-Description"); i.hasNext();) {
                Element description = (Element) i.next();
                plugin.setDescription(description.getText());
            }
            for (Iterator i = root.elementIterator("Plugin-Version"); i.hasNext();) {
                Element version = (Element) i.next();
                plugin.setVersion(version.getText());
            }
            for (Iterator i = root.elementIterator("Plugin-MinSoftVersion"); i.hasNext();) {
                Element soft = (Element) i.next();
                plugin.setMinSoftVersion(soft.getText());
            }
            return plugin;
        }
        return null;
    }
}
