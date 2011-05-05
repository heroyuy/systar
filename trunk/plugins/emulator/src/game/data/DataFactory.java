package game.data;

import game.impl.model.Config;
import game.impl.model.Enemy;
import game.impl.model.EnemyTroop;
import game.impl.model.Equip;
import game.impl.model.Item;
import game.impl.model.Player;
import game.impl.model.Skill;
import game.impl.model.animation.Animation;
import game.impl.model.map.Map;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 游戏对象工厂
 */
public class DataFactory {

    public static void loadConfigList(HashMap<Integer, Config> configList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_CONFIG, 0);
        FileConnector.release();
    }

    public static void loadAnimationList(HashMap<Integer, Animation> animationList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_ANIMATION, 0);
        FileConnector.release();
    }

    public static void loadEnemyList(HashMap<Integer, Enemy> enemyList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_ENEMY, 0);
        FileConnector.release();
    }

    public static void loadEnemyTroopList(HashMap<Integer, EnemyTroop> enemyTroopList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_ENEMYTROOP, 0);
        FileConnector.release();
    }

    public static void loadPlayerList(HashMap<Integer, Player> playerList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_PLAYER, 0);
        FileConnector.release();
    }

    public static void loadSkillList(HashMap<Integer, Skill> skillList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_SKILL, 0);
        FileConnector.release();
    }

    public static void loadItemList(HashMap<Integer, Item> itemList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_ITEM, 0);
        FileConnector.release();
    }

    public static void loadEquipList(HashMap<Integer, Equip> equipList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_EQUIP, 0);
        FileConnector.release();
    }

    public static Map loadMap(int index) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_CONFIG, 0);
        Map map = null;
        FileConnector.release();
        return map;
    }
    private static DataInputStream dis = null;

    private static class FileConnector {

        public static final byte FILE_TYPE_CONFIG = 0;
        public static final byte FILE_TYPE_ANIMATION = 1;
        public static final byte FILE_TYPE_ENEMY = 2;
        public static final byte FILE_TYPE_ENEMYTROOP = 3;
        public static final byte FILE_TYPE_ITEM = 4;
        public static final byte FILE_TYPE_EQUIP = 5;
        public static final byte FILE_TYPE_SKILL = 6;
        public static final byte FILE_TYPE_PLAYER = 7;
        public static final byte FILE_TYPE_MAP = 8;
        public static FileInputStream fis = null;
        public static DataInputStream dis = null;

        public static DataInputStream openDataInputStream(byte type, int index) {
            try {
                switch (type) {
                    case FILE_TYPE_CONFIG:
                        fis = new FileInputStream(new File("/data/system.gat"));
                        break;
                    case FILE_TYPE_ANIMATION:
                        fis = new FileInputStream(new File("/data/ani.gat"));
                        break;
                    case FILE_TYPE_ENEMY:
                        fis = new FileInputStream(new File("/data/enemy.gat"));
                        break;
                    case FILE_TYPE_ENEMYTROOP:
                        fis = new FileInputStream(new File("/data/enemytroop.gat"));
                        break;
                    case FILE_TYPE_ITEM:
                        fis = new FileInputStream(new File("/data/item.gat"));
                        break;
                    case FILE_TYPE_EQUIP:
                        fis = new FileInputStream(new File("/data/equip.gat"));
                        break;
                    case FILE_TYPE_SKILL:
                        fis = new FileInputStream(new File("/data/skill.gat"));
                        break;
                    case FILE_TYPE_PLAYER:
                        fis = new FileInputStream(new File("/data/player.gat"));
                        break;
                    case FILE_TYPE_MAP:
                        fis = new FileInputStream(new File("/data/map/map" + index + ".gat"));
                        break;
                    default:
                        fis = null;
                }
                dis = new DataInputStream(fis);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return dis;
        }

        public static void release() {
            try {
                dis.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DataFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
