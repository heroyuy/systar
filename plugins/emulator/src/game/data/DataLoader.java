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
class DataLoader {

    public MapLoader mapLoader = new MapLoader();

    public void loadConfig(Config config) {
        try {
            FileInputStream fis = new FileInputStream(new File("res/data/system.gat"));
            DataInputStream dis = new DataInputStream(fis);
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
            dis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("加载Config出错");
        }
    }

    public void loadAnimationList(HashMap<Integer, Animation> animationList) {

        try {
            FileInputStream fis = new FileInputStream(new File("res/data/ani.gat"));
            DataInputStream dis = new DataInputStream(fis);
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
            dis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("加载Animation出错");
        }
    }

    public void loadEnemyList(HashMap<Integer, Enemy> enemyList) {

        try {
            FileInputStream fis = new FileInputStream(new File("res/data/enemy.gat"));
            DataInputStream dis = new DataInputStream(fis);
            int enemySum = dis.readInt();
            System.out.println("enemySum:" + enemySum);
            Enemy enemy = null;
            for (int i = 0; i < enemySum; i++) {
                enemy = new Enemy();
                enemy.setIndex(dis.readInt());
                enemy.name = dis.readUTF();
                enemy.intro = dis.readUTF();
                String path = "res" + dis.readUTF();
                System.out.println("path:" + path);
                enemy.battlerImage = Image.createImage(path);
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
            dis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("加载Enemy出错");
        }

    }

    public void loadEnemyTroopList(HashMap<Integer, EnemyTroop> enemyTroopList) {
        try {
            FileInputStream fis = new FileInputStream(new File("res/data/enemytroop.gat"));
            DataInputStream dis = new DataInputStream(fis);
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

            dis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("加载EnemyTroop出错");
        }

    }

    public void loadPlayerData(PlayerData playerData) {

        try {
            FileInputStream fis = new FileInputStream(new File("res/data/player.gat"));
            DataInputStream dis = new DataInputStream(fis);
            playerData.name = dis.readUTF();
            playerData.intro = dis.readUTF();
            playerData.battlerImage = dis.readUTF();
            playerData.characterImage = dis.readUTF();
            playerData.stre = dis.readInt();
            playerData.agil = dis.readInt();
            playerData.inte = dis.readInt();
            playerData.maxHp = dis.readInt();
            playerData.maxSp = dis.readInt();
            playerData.lev = dis.readInt();
            playerData.atk = dis.readInt();
            playerData.def = dis.readInt();
            playerData.flee = dis.readInt();
            playerData.streByLev = dis.readInt();
            playerData.agilByLev = dis.readInt();
            playerData.inteByLev = dis.readInt();

            playerData.expList = new int[playerData.lev];
            for (int i = 0; i < playerData.expList.length; i++) {
                playerData.expList[i] = dis.readInt();
            }
            playerData.money = dis.readInt();
            playerData.mapIndex = dis.readInt();
            playerData.row = dis.readInt();
            playerData.col = dis.readInt();
            playerData.face = dis.readByte();
            dis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("加载playerData出错");
        }

    }

    public void loadSkillList(HashMap<Integer, Skill> skillList) {
        try {
            FileInputStream fis = new FileInputStream(new File("res/data/skill.gat"));
            DataInputStream dis = new DataInputStream(fis);
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
            dis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("加载Skill出错");
        }


    }

    public void loadItemList(HashMap<Integer, Item> itemList) {
        try {
            FileInputStream fis = new FileInputStream(new File("res/data/item.gat"));
            DataInputStream dis = new DataInputStream(fis);
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
            dis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("加载Item出错");
        }

    }

    public void loadEquipList(HashMap<Integer, Equip> equipList) {

        try {
            FileInputStream fis = new FileInputStream(new File("res/data/equip.gat"));
            DataInputStream dis = new DataInputStream(fis);
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
            dis.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("加载Equip出错");
        }

    }

    public Map loadMap(int index) {
        return mapLoader.getMap(index);
    }

    public class FileConnector {

        public final byte FILE_TYPE_CONFIG = 0;
        public final byte FILE_TYPE_ANIMATION = 1;
        public final byte FILE_TYPE_ENEMY = 2;
        public final byte FILE_TYPE_ENEMYTROOP = 3;
        public final byte FILE_TYPE_ITEM = 4;
        public final byte FILE_TYPE_EQUIP = 5;
        public final byte FILE_TYPE_SKILL = 6;
        public final byte FILE_TYPE_PLAYER = 7;
        public final byte FILE_TYPE_MAP = 8;
        public final byte FILE_TYPE_NPC = 9;
        public FileInputStream fis = null;
        public DataInputStream dis = null;

        public DataInputStream openDataInputStream(byte type, int index) {
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
                    case FILE_TYPE_NPC:
                        fis = new FileInputStream(new File("res/data/npc/npc" + index + ".gat"));
                        break;
                    default:
                        fis = null;
                }
                dis = new DataInputStream(fis);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
            return dis;
        }

        public void release() {
            try {
                dis.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
