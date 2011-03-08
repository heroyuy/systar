package engine;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PluginLoader extends URLClassLoader {

    private Class[] plugins = null;
    private MapEditor mapEditor = null;

    public PluginLoader(MapEditor mapEditor) {
        /*
         * 插件加载器
         *
         * 1.遍历“plugin”目录，以获得所有jar文件
         *
         * 2.遍历jar文件，得到jar中的所有.class文件
         *
         * 3.加载jar中的所有类
         *
         * 4.获取插件入口类的 Class 对象
         */
        super(new URL[0]);
        this.mapEditor = mapEditor;
        File f = new File("plugin");
        File[] fs = f.listFiles();
        if (fs != null) {
            plugins = new Class[fs.length];
            for (int i = 0; i < fs.length; i++) {
                try {

                    String path = fs[i].getAbsolutePath();
                    String[] className = getClass(path);
                    path = "file:///" + path;
                    loadJar(path);
                    String name = fs[i].getName();
                    name = name.substring(0, name.indexOf("."));
                    for (String cn : className) {
                        Class c = findClass(cn);
//                        if (c.newInstance() instanceof Plugin) {
                        if (cn.equals("plugin." + cn)) {
                            System.out.println("plugin:" + cn);
                            plugins[i] = c;
                        }
//                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public JButton[] getButton() throws Exception {

        JButton[] button = null;
        if (plugins != null) {
            button = new JButton[plugins.length];
            Class[] c = {MapEditor.class};
            for (int i = 0; i < plugins.length; i++) {
                Constructor co = plugins[i].getConstructor(c);
                button[i] = ((Plugin) co.newInstance(mapEditor)).getButton();
            }
        }
        return button;
    }

    private String[] getClass(String jar) throws IOException {
        // 获取指定Jar包中的所有类文件,并按“包名.类名”的格式返回
        String[] className = null;
        if (jar.endsWith(".jar")) {
            Vector<String> vector = new Vector<String>();
            JarFile file = new JarFile(jar);
            Enumeration<JarEntry> entrys = file.entries();
            while (entrys.hasMoreElements()) {
                JarEntry je = entrys.nextElement();
                String name = je.getName();
                if (name.endsWith(".class")) {
                    name = name.substring(0, name.indexOf(".class")).replaceAll("/", ".");
                    vector.add(name);
                }
            }
            file.close();
            className = new String[vector.size()];
            for (int i = 0; i < className.length; i++) {
                className[i] = vector.elementAt(i);
            }
        }
        return className;
    }

    @SuppressWarnings("unchecked")
    public JMenu[] getMenu() throws Exception {

        JMenu[] menu = null;
        if (plugins != null) {
            menu = new JMenu[plugins.length];
            Class[] c = {MapEditor.class};
            for (int i = 0; i < plugins.length; i++) {
                Constructor co = plugins[i].getConstructor(c);
                menu[i] = ((Plugin) co.newInstance(mapEditor)).getMenu();
            }
        }
        return menu;

    }

    public JMenuItem[] getMenuItem() throws Exception {

        JMenuItem[] menuItem = null;
        if (plugins != null) {
            menuItem = new JMenuItem[plugins.length];
            Class[] c = {MapEditor.class};
            for (int i = 0; i < plugins.length; i++) {
                Constructor co = plugins[i].getConstructor(c);
                menuItem[i] = ((Plugin) co.newInstance(mapEditor)).getMenuItem();
            }
        }
        return menuItem;
    }

    public JMenu[] getPopMenu() throws Exception {

        JMenu[] menu = null;
        if (plugins != null) {
            menu = new JMenu[plugins.length];
            Class[] c = {MapEditor.class};
            for (int i = 0; i < plugins.length; i++) {
                Constructor co = plugins[i].getConstructor(c);
                menu[i] = ((Plugin) co.newInstance(mapEditor)).getPopMenu();
            }
        }
        return menu;

    }

    private void loadJar(String str) {
        try {
            URL url = new URL(str);
            addURL(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
