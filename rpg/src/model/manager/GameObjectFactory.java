package model.manager;

import engine.animation.Animation;
import engine.script.Command;
import engine.script.Script;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import model.Config;
import model.Enemy;
import model.EnemyTroop;
import model.Equip;
import model.Frame;
import model.Item;
import model.Map;
import model.Player;
import model.Skill;

/**
 *
 * 游戏对象工厂
 */
public class GameObjectFactory {

    private static FileBridge fb = new FileBridge();
    private static DataInputStream dis = null;

    protected static Config createConfig() {
        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_SYSTEM, 0);
        Config config = new Config();
        try {
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
        } catch (IOException e) {
            System.out.println("system.gat读取失败");
        }
        return config;
    }

    protected static Animation[] createAnimationList() {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_ANIMATION, 0);
        Animation[] animationList = null;
        try {
            int animationSum = dis.readInt();
            animationList = new Animation[animationSum];
            for (int i = 0; i < animationSum; i++) {
                animationList[i] = new Animation();
                animationList[i].index = dis.readInt();
                animationList[i].name = dis.readUTF();
                animationList[i].imageName = dis.readUTF();
                animationList[i].image = Image.createImage("/image/animation/" + animationList[i].imageName);
                animationList[i].soundName = dis.readUTF();
                animationList[i].frameWidth = dis.readInt();
                animationList[i].frameHeight = dis.readInt();
                animationList[i].frameNum = dis.readInt();
                animationList[i].frames = new Frame[animationList[i].frameNum];
                for (int j = 0; j < animationList[i].frameNum; j++) {
                    animationList[i].frames[j] = new Frame();
                    animationList[i].frames[j].num = dis.readInt();
                    animationList[i].frames[j].offsetX = dis.readInt();
                    animationList[i].frames[j].offsetY = dis.readInt();
                }
            }
            dis.close();
        } catch (IOException e) {
            System.out.println("ani.gat readError!");
        }
        return animationList;
    }

    protected static Enemy[] createEnemyList() {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_ENEMY, 0);
        Enemy[] enemyList = null;
        try {
            int enemySum = dis.readInt();
            enemyList = new Enemy[enemySum];
            for (int i = 0; i < enemySum; i++) {
                enemyList[i] = new Enemy();
                enemyList[i].index = dis.readInt();
                enemyList[i].name = dis.readUTF();
                enemyList[i].intro = dis.readUTF();
                enemyList[i].BattImg = Image.createImage("/image/battler/" + dis.readUTF());
                enemyList[i].agil = dis.readInt();
                enemyList[i].stre = dis.readInt();
                enemyList[i].inte = dis.readInt();
                enemyList[i].maxHp = dis.readInt();
                enemyList[i].maxSp = dis.readInt();
                System.out.println("maxSp " + enemyList[i].maxSp);
                enemyList[i].lev = dis.readInt();
                enemyList[i].atk = dis.readInt();
                enemyList[i].def = dis.readInt();
                enemyList[i].exp = dis.readInt();
                enemyList[i].money = dis.readInt();
                enemyList[i].hp = enemyList[i].maxHp;
                enemyList[i].sp = enemyList[i].maxSp;
                int skillSum = dis.readInt();
                System.out.println("skillSum: " + skillSum);
                enemyList[i].skillList = new int[skillSum];
                for (int j = 0; j < skillSum; j++) {
                    enemyList[i].skillList[j] = dis.readInt();
                    System.out.println("enemyList[i].skillList[j] " + enemyList[i].skillList[j]);
                }
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("enemy.gat readError!");
        }
        return enemyList;
    }

    protected static EnemyTroop[] createEnemyTroopList() {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_ENEMYTROOP, 0);
        EnemyTroop[] enemyTroopList = null;
        try {
            int enemyTroopSum = dis.readInt();
            enemyTroopList = new EnemyTroop[enemyTroopSum];
            for (int i = 0; i < enemyTroopSum; i++) {
                enemyTroopList[i] = new EnemyTroop();
                enemyTroopList[i].index = dis.readInt();
                enemyTroopList[i].name = dis.readUTF();
                enemyTroopList[i].siteA = dis.readInt();
                enemyTroopList[i].siteB = dis.readInt();
                enemyTroopList[i].siteC = dis.readInt();
                enemyTroopList[i].siteD = dis.readInt();
                int itemSum = dis.readInt();
                enemyTroopList[i].itemList = new int[itemSum];
                for (int j = 0; j < itemSum; j++) {
                    enemyTroopList[i].itemList[j] = dis.readInt();

                }
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("enemytroop.gat readError!");
        }
        return enemyTroopList;
    }

    protected static Item[] createItemList() {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_ITEM, 0);
        Item[] itemList = null;
        try {
            int itemSum = dis.readInt();
            itemList = new Item[itemSum];
            for (int i = 0; i < itemSum; i++) {
                itemList[i] = new Item();
                itemList[i].kind = dis.readInt();
                itemList[i].index = dis.readInt();
                itemList[i].name = dis.readUTF();
                itemList[i].intro = dis.readUTF();
                itemList[i].icon = Image.createImage("/image/icon/item/" + dis.readUTF());
                itemList[i].agil = dis.readInt();
                itemList[i].stre = dis.readInt();
                itemList[i].inte = dis.readInt();
                itemList[i].hp = dis.readInt();
                itemList[i].sp = dis.readInt();
                itemList[i].maxHp = dis.readInt();
                itemList[i].maxSp = dis.readInt();
                itemList[i].lev = dis.readInt();
                itemList[i].atk = dis.readInt();
                itemList[i].def = dis.readInt();
                itemList[i].flee = dis.readInt();
                itemList[i].exp = dis.readInt();
                itemList[i].price = dis.readInt();
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("item.gat readError!");
        }
        return itemList;
    }

    protected static Equip[] createEquipList() {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_EQUIP, 0);
        Equip[] equipList = null;
        try {
            int equipSum = dis.readInt();
            equipList = new Equip[equipSum];
            for (int i = 0; i < equipSum; i++) {
                equipList[i] = new Equip();
                equipList[i].kind = dis.readInt();
                equipList[i].index = dis.readInt();
                equipList[i].name = dis.readUTF();
                equipList[i].intro = dis.readUTF();
                equipList[i].icon = Image.createImage("/image/icon/equip/" + dis.readUTF());
                equipList[i].agil = dis.readInt();
                equipList[i].stre = dis.readInt();
                equipList[i].inte = dis.readInt();
                equipList[i].maxHp = dis.readInt();
                equipList[i].maxSp = dis.readInt();
                equipList[i].lev = dis.readInt();
                equipList[i].atk = dis.readInt();
                equipList[i].def = dis.readInt();
                equipList[i].flee = dis.readInt();
                equipList[i].price = dis.readInt();
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("equip.gat readError!");
        }
        return equipList;
    }

    protected static Skill[] createSkillList() {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_SKILL, 0);
        Skill[] skillList = null;
        try {
            int skillSum = dis.readInt();
            skillList = new Skill[skillSum];
            for (int i = 0; i < skillSum; i++) {
                skillList[i] = new Skill();
                skillList[i].index = dis.readInt();
                skillList[i].kind = dis.readInt();
                skillList[i].name = dis.readUTF();
                skillList[i].intro = dis.readUTF();
                skillList[i].icon = Image.createImage("/image/icon/skill/" + dis.readUTF());
                skillList[i].hp = dis.readInt();
                skillList[i].sp = dis.readInt();
                skillList[i].lev = dis.readInt();
//                    skillList[i].atk = dis.readInt();
                skillList[i].def = dis.readInt();
                skillList[i].price = dis.readInt();
                skillList[i].dpy = dis.readInt();
                skillList[i].speed = dis.readInt();
                skillList[i].aniIndex = dis.readInt();
            }
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("skill.gat readError!");
        }
        return skillList;
    }

    protected static Map createMap(int index) {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_MAP, index);
        Map map = new Map();
        try {
            map.index = dis.readInt();
            map.name = dis.readUTF();
            String imageName = dis.readUTF();

            map.musicName = dis.readUTF();
            map.rowNum = dis.readByte();
            System.out.println("row " + map.rowNum);
            map.colNum = dis.readByte();
            System.out.println("col " + map.colNum);
            map.cellWidth = dis.readByte();
            map.cellHeight = dis.readByte();
            map.layerNum = dis.readByte();

            map.data = new int[map.layerNum][map.rowNum][map.colNum];
            for (int i = 0; i < map.layerNum; i++) {
                for (int j = 0; j < map.rowNum; j++) {
                    for (int k = 0; k < map.colNum; k++) {
                        map.data[i][j][k] = dis.readInt();
                    }
                }
            }
            map.way = new int[map.rowNum][map.colNum];
            for (int j = 0; j < map.rowNum; j++) {
                for (int k = 0; k < map.colNum; k++) {
                    map.way[j][k] = dis.readByte();
                }
            }

            map.scriptType = new byte[map.rowNum][map.colNum];
            for (int j = 0; j < map.rowNum; j++) {
                for (int k = 0; k < map.colNum; k++) {
                    map.scriptType[j][k] = dis.readByte();
                }
            }
            int sum = dis.readInt();
            map.script = new Script[sum];
            for (int i = 0; i < sum; i++) {
                map.script[i] = new Script();
                map.script[i].type = dis.readByte();
                map.script[i].row = dis.readInt();
                map.script[i].col = dis.readInt();
                map.script[i].command = new Command[dis.readInt()];
                for (int j = 0; j < map.script[i].command.length; j++) {
                    map.script[i].command[j] = new Command();
                    map.script[i].command[j].type = dis.readByte();
                    map.script[i].command[j].param = dis.readUTF();
                    map.script[i].command[j].nextIndex = dis.readInt();
//                    switch (map.script[i].command[j].type) {
//                        case Command.ENDIF:
//                        case Command.ENDWHILE:
//                        case Command.BREAK:
//                        case Command.CONTINUE:
//                        case Command.EXIT:
//                        case Command.GAMEOVER:
//                            continue;
//                        default:
//                            System.out.println("hava param");
//                            map.script[i].command[j].param = dis.readUTF();
//                            map.script[i].command[j].nextIndex = dis.readInt();
//                            break;
//                    }

                }
            }
            map.setImage(Image.createImage("/image/tileset/" + imageName));
            dis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return map;
    }

    static Player createPlayer() {

        dis = fb.getDataInputStream(FileBridge.FILE_TYPE_PLAYER, 0);
        Player player = new Player();
        try {
            player.name = dis.readUTF();
            player.intro = dis.readUTF();
            player.headImg = Image.createImage("/image/battler/" + dis.readUTF());
            player.setCharImg(Image.createImage("/image/character/" + dis.readUTF()));
            player.stre = dis.readInt();
            player.agil = dis.readInt();
            player.inte = dis.readInt();
            player.hp = dis.readInt();
            player.sp = dis.readInt();
            player.maxLev = dis.readInt();
            player.atk = dis.readInt();
            player.def = dis.readInt();
            player.flee = dis.readInt();
            player.streByLev = dis.readInt();
            player.agilByLev = dis.readInt();
            player.inteByLev = dis.readInt();

            player.levList = new int[player.maxLev];
            for (int i = 0; i < player.levList.length; i++) {
                player.levList[i] = dis.readInt();
            }
            player.money = dis.readInt();
            player.curMapIndex = dis.readInt();
            player.row = dis.readInt();
            player.col = dis.readInt();
            player.face = dis.readInt();
            System.out.println("row:"+player.row+"col:"+player.col+"face:"+player.face);
            //测试 可修改
//            player.init();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("找不到player.gat文件");
        }

        return player;
    }
}
