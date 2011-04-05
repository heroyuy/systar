package com.soyostar.game.model.manager;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 文件管理器
 */
public class FileBridge {

    protected static final byte FILE_TYPE_SYSTEM = 0;
    protected static final byte FILE_TYPE_ANIMATION = 1;
    protected static final byte FILE_TYPE_ENEMY = 2;
    protected static final byte FILE_TYPE_ENEMYTROOP = 3;
    protected static final byte FILE_TYPE_ITEM = 4;
    protected static final byte FILE_TYPE_EQUIP = 5;
    protected static final byte FILE_TYPE_SKILL = 6;
    protected static final byte FILE_TYPE_PLAYER = 7;
    protected static final byte FILE_TYPE_MAP = 8;
    private InputStream is = null;
    private DataInputStream dis = null;

    protected DataInputStream getDataInputStream(byte Type, int index) {
        try {
            switch (Type) {
                case FILE_TYPE_SYSTEM:
                    is = new FileInputStream(new File("res/data/system.gat"));
                    break;
                case FILE_TYPE_ANIMATION:
                    is = new FileInputStream(new File("res/data/ani.gat"));
                    break;
                case FILE_TYPE_ENEMY:
                    is = new FileInputStream(new File("res/data/enemy.gat"));
                    break;
                case FILE_TYPE_ENEMYTROOP:
                    is = new FileInputStream(new File("res/data/enemytroop.gat"));
                    break;
                case FILE_TYPE_ITEM:
                    is = new FileInputStream(new File("res/data/item.gat"));
                    break;
                case FILE_TYPE_EQUIP:
                    is = new FileInputStream(new File("res/data/equip.gat"));
                    break;
                case FILE_TYPE_SKILL:
                    is = new FileInputStream(new File("res/data/skill.gat"));
                    break;
                case FILE_TYPE_PLAYER:
                    is = new FileInputStream(new File("res/data/player.gat"));
                    break;
                case FILE_TYPE_MAP:
                    is = new FileInputStream(new File("res/data/map/map" + index + ".gat"));
                    break;
                default:
                    is = null;
            }
            dis = new DataInputStream(is);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileBridge.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dis;
    }
}
