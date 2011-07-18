package game.data;

import com.soyostar.app.Image;
import game.impl.model.Config;
import game.impl.model.Enemy;
import game.impl.model.EnemyTroop;
import game.impl.model.Equip;
import game.impl.model.Item;
import game.impl.model.Player;
import game.impl.model.Skill;
import game.impl.model.animation.Animation;
import game.impl.model.animation.Layer;
import game.impl.model.animation.Module;
import game.impl.model.animation.Sequence;
import game.impl.model.animation.Tile;
import game.impl.model.animation.TileSet;
import game.impl.model.Map;
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
        Config config = null;
        try {
            config = new Config();
            config.name = dis.readUTF();
            config.help = dis.readUTF();
            config.about = dis.readUTF();
            config.hp = dis.readUTF();
            config.sp = dis.readUTF();
            config.stre = dis.readUTF();
            config.inte = dis.readUTF();
            config.agil = dis.readUTF();
            config.atk = dis.readUTF();
            config.def = dis.readUTF();
            config.flee = dis.readUTF();
            config.money = dis.readUTF();
            config.helm = dis.readUTF();
            config.armour = dis.readUTF();
            config.weapon = dis.readUTF();
            config.shield = dis.readUTF();
            config.boots = dis.readUTF();
            config.jewelry = dis.readUTF();
            //游戏只有一个Config，编号为0
            config.setIndex(0);
        } catch (IOException e) {
            System.out.println("加载Config出错");
        }
        configList.put(config.getIndex(), config);
        FileConnector.release();
    }

    public static void loadAnimationList(HashMap<Integer, Animation> animationList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_ANIMATION, 0);
        try {
            int aniNum = dis.readInt();
            Animation ani = null;
            for (int i = 0; i < aniNum; i++) {
                ani = new Animation();
                ani.setIndex(dis.readInt());
                int tsn = dis.readInt();
                for (int j = 0; j < tsn; j++) {
                    TileSet tileset = new TileSet();
                    tileset.id = dis.readInt();
                    tileset.path = dis.readUTF();
                    int tn = dis.readInt();
                    for (int k = 0; k < tn; k++) {
                        Tile tile = new Tile();
                        tile.tilesetID = tileset.id;
                        tile.x = dis.readInt();
                        tile.y = dis.readInt();
                        tile.width = dis.readInt();
                        tile.height = dis.readInt();
                        tileset.tiles.add(tile);
                    }
                    ani.tileSets.add(tileset);
                }
                int sn = dis.readInt();
                for (int j = 0; j < sn; j++) {
                    Sequence seq = new Sequence();
                    int ln = dis.readInt();
                    for (int k = 0; k < ln; k++) {
                        Layer sl = new Layer();
                        sl.type = dis.readByte();
                        sl.x = dis.readInt();
                        sl.y = dis.readInt();
                        sl.width = dis.readInt();
                        sl.height = dis.readInt();
                        int mn = dis.readInt();
                        for (int l = 0; l < mn; l++) {
                            Module module = new Module();
                            module.tileID = dis.readInt();
                            module.x = dis.readInt();
                            module.y = dis.readInt();
                            sl.modules.add(module);
                        }
                        seq.layers.add(sl);
                    }
                    seq.delay = dis.readInt();
                    ani.sequences.add(seq);
                }
                animationList.put(ani.getIndex(), ani);
            }
        } catch (IOException e) {
            System.out.println("加载Animation出错");
        }
        FileConnector.release();
    }

    public static void loadEnemyList(HashMap<Integer, Enemy> enemyList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_ENEMY, 0);
        try {
            int enemySum = dis.readInt();
            Enemy enemy = null;
            for (int i = 0; i < enemySum; i++) {
                enemy = new Enemy();
                enemy.setIndex(dis.readInt());
                enemy.name = dis.readUTF();
                enemy.intro = dis.readUTF();
                enemy.headImg = "/image/battler/" + dis.readUTF();
                enemy.agil = dis.readInt();
                enemy.stre = dis.readInt();
                enemy.inte = dis.readInt();
                enemy.maxHp = dis.readInt();
                enemy.maxSp = dis.readInt();
                enemy.lev = dis.readInt();
                enemy.atk = dis.readInt();
                enemy.def = dis.readInt();
                enemy.exp = dis.readInt();
                enemy.money = dis.readInt();
                int skillSum = dis.readInt();
                enemy.skillList = new int[skillSum];
                for (int j = 0; j < skillSum; j++) {
                    enemy.skillList[j] = dis.readInt();
                }
                enemyList.put(enemy.getIndex(), enemy);
            }
        } catch (IOException e) {
            System.out.println("加载Enemy出错");
        }
        FileConnector.release();
    }

    public static void loadEnemyTroopList(HashMap<Integer, EnemyTroop> enemyTroopList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_ENEMYTROOP, 0);
        try {
            int enemyTroopSum = dis.readInt();
            EnemyTroop enemyTroop = null;
            for (int i = 0; i < enemyTroopSum; i++) {
                enemyTroop = new EnemyTroop();
                enemyTroop.setIndex(dis.readInt());
                enemyTroop.name = dis.readUTF();
                enemyTroop.siteA = dis.readInt();
                enemyTroop.siteB = dis.readInt();
                enemyTroop.siteC = dis.readInt();
                enemyTroop.siteD = dis.readInt();
                int itemSum = dis.readInt();
                enemyTroop.itemList = new int[itemSum];
                for (int j = 0; j < itemSum; j++) {
                    enemyTroop.itemList[j] = dis.readInt();

                }
                enemyTroopList.put(enemyTroop.getIndex(), enemyTroop);
            }
        } catch (IOException e) {
            System.out.println("加载EnemyTroop出错");
        }
        FileConnector.release();
    }

    public static void loadPlayerList(HashMap<Integer, Player> playerList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_PLAYER, 0);
        Player player = null;
        try {
            player = new Player();
            player.name = dis.readUTF();
            player.intro = dis.readUTF();
            player.headImg = dis.readUTF();
            player.setCharImg("res" + dis.readUTF());
            player.stre = dis.readInt();
            player.agil = dis.readInt();
            player.inte = dis.readInt();
            player.hp = dis.readInt();
            player.sp = dis.readInt();
            player.lev = dis.readInt();
            player.atk = dis.readInt();
            player.def = dis.readInt();
            player.flee = dis.readInt();
            player.streByLev = dis.readInt();
            player.agilByLev = dis.readInt();
            player.inteByLev = dis.readInt();

            player.levList = new int[player.lev];
            for (int i = 0; i < player.levList.length; i++) {
                player.levList[i] = dis.readInt();
            }
            player.money = dis.readInt();
            player.curMapIndex = dis.readInt();
            player.row = dis.readInt();
            player.col = dis.readInt();
            player.face = dis.readInt();
            player.setIndex(0);
            playerList.put(player.getIndex(), player);
        } catch (IOException e) {
            System.out.println("加载Player出错");
        }
        FileConnector.release();
    }

    public static void loadSkillList(HashMap<Integer, Skill> skillList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_SKILL, 0);
        try {
            int skillSum = dis.readInt();
            Skill skill = null;
            for (int i = 0; i < skillSum; i++) {
                skill = new Skill();
                skill.setIndex(dis.readInt());
                skill.kind = dis.readInt();
                skill.name = dis.readUTF();
                skill.intro = dis.readUTF();
                skill.icon = "/image/icon/skill/" + dis.readUTF();
                skill.hp = dis.readInt();
                skill.sp = dis.readInt();
                skill.lev = dis.readInt();
                skill.atk = dis.readInt();
                skill.def = dis.readInt();
                skill.price = dis.readInt();
                skill.dpy = dis.readInt();
                skill.speed = dis.readInt();
                skill.aniIndex = dis.readInt();
                skillList.put(skill.getIndex(), skill);
            }
        } catch (IOException e) {
            System.out.println("加载Skill出错");
        }

        FileConnector.release();
    }

    public static void loadItemList(HashMap<Integer, Item> itemList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_ITEM, 0);
        try {
            int itemSum = dis.readInt();
            Item item = null;
            for (int i = 0; i < itemSum; i++) {
                item = new Item();
                item.kind = dis.readInt();
                item.setIndex(dis.readInt());
                item.name = dis.readUTF();
                item.intro = dis.readUTF();
                item.icon = "/image/icon/item/" + dis.readUTF();
                item.agil = dis.readInt();
                item.stre = dis.readInt();
                item.inte = dis.readInt();
                item.hp = dis.readInt();
                item.sp = dis.readInt();
                item.maxHp = dis.readInt();
                item.maxSp = dis.readInt();
                item.lev = dis.readInt();
                item.atk = dis.readInt();
                item.def = dis.readInt();
                item.flee = dis.readInt();
                item.exp = dis.readInt();
                item.price = dis.readInt();
                itemList.put(item.getIndex(), item);
            }
        } catch (IOException e) {
            System.out.println("加载Item出错");
        }
        FileConnector.release();
    }

    public static void loadEquipList(HashMap<Integer, Equip> equipList) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_EQUIP, 0);
        try {
            int equipSum = dis.readInt();
            Equip equip = null;
            for (int i = 0; i < equipSum; i++) {
                equip = new Equip();
                equip.kind = dis.readInt();
                equip.setIndex(dis.readInt());
                equip.name = dis.readUTF();
                equip.intro = dis.readUTF();
                equip.icon = "/image/icon/equip/" + dis.readUTF();
                equip.agil = dis.readInt();
                equip.stre = dis.readInt();
                equip.inte = dis.readInt();
                equip.maxHp = dis.readInt();
                equip.maxSp = dis.readInt();
                equip.lev = dis.readInt();
                equip.atk = dis.readInt();
                equip.def = dis.readInt();
                equip.flee = dis.readInt();
                equip.price = dis.readInt();
                equipList.put(equip.getIndex(), equip);
            }
        } catch (IOException e) {
            System.out.println("加载Equip出错");
        }
        FileConnector.release();
    }

    public static Map loadMap(int index) {
        dis = FileConnector.openDataInputStream(FileConnector.FILE_TYPE_MAP, index);
        mapFactory.loadMapData(dis);
        FileConnector.release();
        return mapFactory.getMap();
    }
    private static DataInputStream dis = null;
    private static MapFactory mapFactory = new MapFactory();

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
                        fis = new FileInputStream(new File("res/data/system.gat"));
                        break;
                    case FILE_TYPE_ANIMATION:
                        fis = new FileInputStream(new File("res/data/ani.gat"));
                        break;
                    case FILE_TYPE_ENEMY:
                        fis = new FileInputStream(new File("res/data/enemy.gat"));
                        break;
                    case FILE_TYPE_ENEMYTROOP:
                        fis = new FileInputStream(new File("res/data/enemytroop.gat"));
                        break;
                    case FILE_TYPE_ITEM:
                        fis = new FileInputStream(new File("res/data/item.gat"));
                        break;
                    case FILE_TYPE_EQUIP:
                        fis = new FileInputStream(new File("res/data/equip.gat"));
                        break;
                    case FILE_TYPE_SKILL:
                        fis = new FileInputStream(new File("res/data/skill.gat"));
                        break;
                    case FILE_TYPE_PLAYER:
                        fis = new FileInputStream(new File("res/data/player.gat"));
                        break;
                    case FILE_TYPE_MAP:
                        fis = new FileInputStream(new File("res/data/map/map" + index + ".gat"));
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
