/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.data;

import com.soyomaker.AppData;
import com.soyomaker.config.Configuration;
import com.soyomaker.data.dao.ConfigDao;
import com.soyomaker.data.dao.Dao;
import com.soyomaker.data.dao.EnemyDao;
import com.soyomaker.data.dao.EnemyTroopDao;
import com.soyomaker.data.dao.ItemDao;
import com.soyomaker.data.dao.PlayerDao;
import com.soyomaker.data.dao.SkillDao;
import com.soyomaker.data.dao.BuffDao;
import com.soyomaker.data.dao.VocationDao;
import com.soyomaker.data.listener.DataChangeListener;
import com.soyomaker.data.listener.DataChangedEvent;
import com.soyomaker.data.model.Config;
import com.soyomaker.data.model.Enemy;
import com.soyomaker.data.model.EnemyTroop;
import com.soyomaker.data.model.Equip;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Model;
import com.soyomaker.data.model.Player;
import com.soyomaker.data.model.Skill;
import com.soyomaker.data.model.Buff;
import com.soyomaker.data.model.Vocation;
import com.soyomaker.util.FileUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class DataManager {

    private HashMap<Integer, Dao> daos = null;
    private Class[] modelClass = {
        Config.class,
        Buff.class,
        Skill.class,
        Item.class,
        Equip.class,
        Vocation.class,
        Player.class,
        Enemy.class,
        EnemyTroop.class
    };
    /**
     *
     */
    protected final List<DataChangeListener> dataChangeListeners = new LinkedList();

    /**
     *
     */
    public DataManager() {
        daos = new HashMap<Integer, Dao>();

        daos.put(Model.STATUS, new BuffDao());
        daos.put(Model.SKILL, new SkillDao());
        daos.put(Model.ITEM, new ItemDao());
        daos.put(Model.VOCATION, new VocationDao());
        daos.put(Model.PLAYER, new PlayerDao());
        daos.put(Model.CONFIG, new ConfigDao());
        daos.put(Model.ENEMY, new EnemyDao());
        daos.put(Model.ENEMYTROOP, new EnemyTroopDao());
    }

    /**
     *
     * @return
     */
    public static ImageIcon[] listStatusIconName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/icon/status");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
//        String[] s = f.list();
        ImageIcon[] ii = new ImageIcon[s.length];
        for (int i = 0; i < s.length; i++) {
            ii[i] = new ImageIcon(AppData.getInstance().getCurProject().getPath() + "/image/icon/status/"
                    + s[i], s[i]);
        }
        return ii;
    }

    /**
     *
     * @return
     */
    public static ImageIcon[] listEquipIconName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/icon/equip");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
//        String[] s = f.list();
        ImageIcon[] ii = new ImageIcon[s.length];
        for (int i = 0; i < s.length; i++) {
            ii[i] = new ImageIcon(AppData.getInstance().getCurProject().getPath() + "/image/icon/equip/"
                    + s[i], s[i]);
        }
        return ii;
    }

    /**
     *
     * @return
     */
    public static ImageIcon[] listSkillIconName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/icon/skill");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
//        String[] s = f.list();
        ImageIcon[] ii = new ImageIcon[s.length];
        for (int i = 0; i < s.length; i++) {
            ii[i] = new ImageIcon(AppData.getInstance().getCurProject().getPath() + "/image/icon/skill/"
                    + s[i], s[i]);
        }
        return ii;
    }

    /**
     *
     * @return
     */
    public static ImageIcon[] listItemIconName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/icon/item");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
//        String[] s = f.list();
        ImageIcon[] ii = new ImageIcon[s.length];
        for (int i = 0; i < s.length; i++) {
            ii[i] = new ImageIcon(AppData.getInstance().getCurProject().getPath() + "/image/icon/item/"
                    + s[i], s[i]);
        }
        return ii;
    }

    /**
     *
     * @return
     */
    public static String[] listWalkImageName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/character");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
        return s;
    }

    /**
     *
     * @return
     */
    public static String[] listSkinImageName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/skin");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
        return s;
    }

    /**
     *
     * @return
     */
    public static String[] listBattleImageName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/battle");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
        return s;
    }

    /**
     *
     * @return
     */
    public static String[] listBattlerImageName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/battler");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
        return s;
    }

    /**
     *
     * @return
     */
    public static String[] listPictureImageName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/picture");
        if (!f.exists()) {
            f.mkdirs();
        }

        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
        return s;
    }

    /**
     *
     * @return
     */
    public static String[] listTitleBackgroundName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/image/title");
        if (!f.exists()) {
            f.mkdirs();
        }

        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.PICTURE);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
        return s;
    }

    /**
     *
     * @return
     */
    public static String[] listSoundName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/audio/sound");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.SOUND);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
        return s;
    }

    /**
     *
     * @return
     */
    public static String[] listMusicName() {
        File f = new File(AppData.getInstance().getCurProject().getPath() + "/audio/music");
        if (!f.exists()) {
            f.mkdirs();
        }
        ArrayList<File> files = new ArrayList<File>();
        FileUtil.addListFile(f, files, Configuration.Filter.MUSIC);
        String[] s = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            s[i] = files.get(i).getName();
        }
        return s;
    }

    /**
     * 
     * @param listener
     */
    public void addDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    /**
     * 
     * @param listener
     */
    public void removeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.remove(listener);
    }

    /**
     *
     * @param player
     */
    protected void firePlayerAdded(Player player) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).playerAdded(event, player);
        }
    }

    /**
     *
     * @param index
     */
    protected void firePlayerRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).playerRemoved(event, index);
        }
    }

    /**
     *
     * @param config
     */
    protected void fireConfigChanged(Config config) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).configChanged(event, config);
        }
    }

    /**
     *
     * @param layer
     */
    protected void fireVocationAdded(Vocation layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;
        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).vocationAdded(event, layer);
        }
    }

    /**
     *
     * @param index
     */
    protected void fireVocationRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).vocationRemoved(event, index);
        }
    }

    /**
     *
     * @param layer
     */
    protected void fireStatusAdded(Buff layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).statusAdded(event, layer);
        }
    }

    /**
     *
     * @param index
     */
    protected void fireStatusRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).statusRemoved(event, index);
        }
    }

    /**
     *
     * @param layer
     */
    protected void fireSkillAdded(Skill layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).skillAdded(event, layer);
        }
    }

    /**
     *
     * @param index
     */
    protected void fireSkillRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).skillRemoved(event, index);
        }
    }

    /**
     *
     * @param layer
     */
    protected void fireEnemyAdded(Enemy layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).enemyAdded(event, layer);
        }
    }

    /**
     *
     * @param index
     */
    protected void fireEnemyRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).enemyRemoved(event, index);
        }
    }

    /**
     *
     * @param layer
     */
    protected void fireEnemyTroopAdded(EnemyTroop layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).enemyTroopAdded(event, layer);
        }
    }

    /**
     *
     * @param index
     */
    protected void fireEnemyTroopRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).enemyTroopRemoved(event, index);
        }
    }

    /**
     *
     * @param layer
     */
    protected void fireEquipAdded(Equip layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).equipAdded(event, layer);
        }
    }

    /**
     *
     * @param index
     */
    protected void fireEquipRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).equipRemoved(event, index);
        }
    }

    /**
     *
     * @param layer
     */
    protected void fireItemAdded(Item layer) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).itemAdded(event, layer);
        }
    }

    /**
     *
     * @param index
     */
    protected void fireItemRemoved(int index) {
        Iterator iterator = dataChangeListeners.iterator();
        DataChangedEvent event = null;

        while (iterator.hasNext()) {
            if (event == null) {
                event = new DataChangedEvent(this);
            }
            ((DataChangeListener) iterator.next()).itemRemoved(event, index);
        }
    }

    /**
     *
     * @param type
     * @param model
     */
    public void saveModel(int type, Model model) {
        daos.get(type).saveModel(model);
        switch (type) {
            case Model.CONFIG:
                fireConfigChanged((Config) model);
                break;
            case Model.STATUS:
                fireStatusAdded((Buff) model);
                break;
            case Model.VOCATION:
                fireVocationAdded((Vocation) model);
                break;
            case Model.PLAYER:
                firePlayerAdded((Player) model);
                break;
            case Model.SKILL:
                fireSkillAdded((Skill) model);
                break;
            case Model.ENEMY:
                fireEnemyAdded((Enemy) model);
                break;
            case Model.ENEMYTROOP:
                fireEnemyTroopAdded((EnemyTroop) model);
                break;
            case Model.EQUIP:
                fireEquipAdded((Equip) model);
                break;
            case Model.ITEM:
                fireItemAdded((Item) model);
                break;
        }
    }

    /**
     *
     * @param type
     * @param index
     */
    public void removeModel(int type, int index) {
        daos.get(type).removeModel(index);
        switch (type) {
            case Model.PLAYER:
                firePlayerRemoved(index);
                break;
            case Model.STATUS:
                fireStatusRemoved(index);
                break;
            case Model.VOCATION:
                fireVocationRemoved(index);
                break;
            case Model.ENEMY:
                fireEnemyRemoved(index);
                break;
            case Model.ENEMYTROOP:
                fireEnemyTroopRemoved(index);
                break;
            case Model.EQUIP:
                fireEquipRemoved(index);
                break;
            case Model.ITEM:
                fireItemRemoved(index);
                break;
            case Model.SKILL:
                fireSkillRemoved(index);
                break;
        }
    }

    /**
     *
     * @param type
     */
    public void removeAllModel(int type) {
        switch (type) {
            case Model.STATUS:
                for (int i = 0; i < daos.get(type).getModels(Buff.class).length; i++) {
                    Model model = daos.get(type).getModels(Buff.class)[i];
                    removeModel(Model.STATUS, model.getIndex());
                }
                break;
            case Model.VOCATION:
                for (int i = 0; i < daos.get(type).getModels(Vocation.class).length; i++) {
                    Model model = daos.get(type).getModels(Vocation.class)[i];
                    removeModel(Model.VOCATION, model.getIndex());
                }
                break;
            case Model.SKILL:
                for (int i = 0; i < daos.get(type).getModels(Skill.class).length; i++) {
                    Model model = daos.get(type).getModels(Skill.class)[i];
                    removeModel(Model.SKILL, model.getIndex());
                }
                break;
            case Model.ENEMY:
                for (int i = 0; i < daos.get(type).getModels(Enemy.class).length; i++) {
                    Model model = daos.get(type).getModels(Enemy.class)[i];
                    removeModel(Model.ENEMY, model.getIndex());
                }
                break;
            case Model.ENEMYTROOP:
                for (int i = 0; i < daos.get(type).getModels(EnemyTroop.class).length; i++) {
                    Model model = daos.get(type).getModels(EnemyTroop.class)[i];
                    removeModel(Model.ENEMYTROOP, model.getIndex());
                }
                break;
            case Model.EQUIP:
                for (int i = 0; i < daos.get(type).getModels(Equip.class).length; i++) {
                    Model model = daos.get(type).getModels(Equip.class)[i];
                    removeModel(Model.EQUIP, model.getIndex());
                }
                break;
            case Model.ITEM:
                for (int i = 0; i < daos.get(type).getModels(Item.class).length; i++) {
                    Model model = daos.get(type).getModels(Item.class)[i];
                    removeModel(Model.ITEM, model.getIndex());
                }
                break;
            case Model.PLAYER:
                for (int i = 0; i < daos.get(type).getModels(Player.class).length; i++) {
                    Model pmodel = daos.get(type).getModels(Player.class)[i];
                    removeModel(Model.PLAYER, pmodel.getIndex());
                }
                break;
        }
    }

    /**
     * 清除所有的数据
     */
    public void clearAllData() {
        removeAllModel(Model.ENEMYTROOP);
        removeAllModel(Model.ENEMY);
        removeAllModel(Model.PLAYER);
        removeAllModel(Model.VOCATION);
        removeAllModel(Model.EQUIP);
        removeAllModel(Model.ITEM);
        removeAllModel(Model.SKILL);
        removeAllModel(Model.STATUS);
        removeAllModel(Model.CONFIG);
    }

    /**
     *
     * @param type
     * @param index
     * @return
     */
    public Model getModel(int type, int index) {
        return daos.get(type).getModel(index);
    }

    /**
     * 
     * @param type
     * @return
     */
    public Model[] getModels(int type) {
        return daos.get(type).getModels(modelClass[type]);
    }

    /**
     *
     * @param type
     * @return
     */
    public int size(int type) {
        return daos.get(type).size();
    }

    /**
     *
     * @param type
     * @return
     */
    public int getMaxIndex(int type) {
        return daos.get(type).getMaxIndex();
    }

    /**
     *
     * @param type
     * @return
     */
    public int allocateIndex(int type) {
        return daos.get(type).allocateIndex();
    }

    /**
     *
     * @param path
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void init(String path) throws FileNotFoundException, IOException {
        if (path.endsWith(File.separator)) {
            path = path.substring(0, path.length() - 1);
        }
        this.path = path;
        //顺序读取
        daos.get(Model.STATUS).load();
        daos.get(Model.SKILL).load();
        daos.get(Model.ITEM).load();
        daos.get(Model.EQUIP).load();
        daos.get(Model.VOCATION).load();
        daos.get(Model.PLAYER).load();
        daos.get(Model.CONFIG).load();
        daos.get(Model.ENEMY).load();
        daos.get(Model.ENEMYTROOP).load();
    }

    /**
     *
     * @return 是否保存成功
     */
    public boolean save() {
        Iterator i = daos.values().iterator();
        boolean res = true;
        while (i.hasNext()) {
            Dao dao = (Dao) i.next();
            res = res && dao.save();
        }
        return res;
    }

    /**
     *
     * @return 是否保存成功
     */
    public boolean saveSoft() {
        Iterator i = daos.values().iterator();
        boolean res = true;
        while (i.hasNext()) {
            Dao dao = (Dao) i.next();
            res = res && dao.saveSoft();
        }
        return res;
    }
    private String path = "测试/data";

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }
}
