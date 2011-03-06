package model.manager;

import model.Item;
import model.Map;
import model.Skill;
import model.Enemy;
import model.Bag;
import model.Equip;
import model.EnemyTroop;
import model.BaseItem;
import model.Config;
import model.Player;
import engine.animation.Animation;
import java.util.Hashtable;
//import model.*;
//import test.TestData;

/**
 *
 * 游戏对象管理器
 */
public class GameObjectManager {

    private Config config = null;
    private Animation[] animationList = null;
    private Enemy[] enemyList = null;
    private EnemyTroop[] enemyTroopList = null;
    private Item[] itemList = null;
    private Equip[] equipList = null;
    private Skill[] skillList = null;
    private Player player = null;
    private Hashtable mapList = new Hashtable();

    public void init() {
        System.out.println("init开始");
        config = GameObjectFactory.createConfig();
        System.out.println("config初始化完成");
        animationList = GameObjectFactory.createAnimationList();
        System.out.println("animation初始化完成");
        enemyList = GameObjectFactory.createEnemyList();
        System.out.println("enemy初始化完成");
        enemyTroopList = GameObjectFactory.createEnemyTroopList();
        System.out.println("enemyTroop初始化完成");
        itemList = GameObjectFactory.createItemList();
//        itemList = TestData.getItemList();//测试
        System.out.println("item初始化完成");
        equipList = GameObjectFactory.createEquipList();
//        equipList = TestData.getEquipList();//测试
        System.out.println("equip初始化完成");
        skillList = GameObjectFactory.createSkillList();
//        skillList = TestData.getSkillList();//测试
        System.out.println("skill初始化完成");
        player = GameObjectFactory.createPlayer();
        System.out.println("player初始化完成");
    }

    public BaseItem getBaseItem(byte type, int index) {
        BaseItem bi = null;
        switch (type) {
            case Bag.ITEM:
                bi = itemList[index];
                break;
            case Bag.EQUIP:
                bi = equipList[index];
                break;
            case Bag.SKILL:
                bi = skillList[index];
                break;
        }
        return bi;
    }

    public Config getConfig() {
        return config;
    }

    public Animation getAnimation(int index) {
        return animationList[index];
    }

    public Enemy getEnemy(int index) {
        return enemyList[index];
    }

    public EnemyTroop getEnemyTroop(int index) {
        return enemyTroopList[index];
    }

    public Item getItem(int index) {
        return itemList[index];
    }

    public Equip getEquip(int index) {
        return equipList[index];
    }

    public Skill getSkill(int index) {
        return skillList[index];
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap(int index) {
        if (mapList.get(new Integer(index)) == null) {
            Map map = GameObjectFactory.createMap(index);
            mapList.put(new Integer(map.index), map);
        }
        return (Map) mapList.get(new Integer(index));
    }
}
