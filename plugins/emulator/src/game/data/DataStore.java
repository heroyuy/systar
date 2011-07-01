package game.data;

import game.impl.model.Config;
import game.impl.model.Enemy;
import game.impl.model.EnemyTroop;
import game.impl.model.Equip;
import game.impl.model.Item;
import game.impl.model.Player;
import game.impl.model.Skill;
import game.impl.model.animation.Animation;
import game.impl.model.Map;
import java.util.HashMap;

/**
 *
 * 游戏数据中心
 */
public class DataStore {

    private HashMap<Integer, Config> configList = null;
    private HashMap<Integer, Animation> animationList = null;
    private HashMap<Integer, Enemy> enemyList = null;
    private HashMap<Integer, EnemyTroop> enemyTroopList = null;
    private HashMap<Integer, Item> itemList = null;
    private HashMap<Integer, Equip> equipList = null;
    private HashMap<Integer, Skill> skillList = null;
    private HashMap<Integer, Player> playerList = null;
    private HashMap<Integer, Map> mapList = null;
    public static ImageManager imageManager = new ImageManager();

    public DataStore() {
        configList = new HashMap<Integer, Config>();
        animationList = new HashMap<Integer, Animation>();
        enemyList = new HashMap<Integer, Enemy>();
        enemyTroopList = new HashMap<Integer, EnemyTroop>();
        itemList = new HashMap<Integer, Item>();
        equipList = new HashMap<Integer, Equip>();
        skillList = new HashMap<Integer, Skill>();
        playerList = new HashMap<Integer, Player>();
        mapList = new HashMap<Integer, Map>();
        init();
    }

    private void init() {
        System.out.println("初始化数据中心");
        DataFactory.loadConfigList(configList);
//        DataFactory.loadAnimationList(animationList);
        DataFactory.loadEnemyList(enemyList);
        DataFactory.loadEnemyTroopList(enemyTroopList);
        DataFactory.loadPlayerList(playerList);
        DataFactory.loadSkillList(skillList);
        DataFactory.loadItemList(itemList);
        DataFactory.loadEquipList(equipList);
    }

    public Config getConfig() {
        return configList.get(0);
    }

    public Player getPlayer() {
        return playerList.get(0).clone();
    }

    public Enemy getEnemy(int index) {
        return enemyList.get(index);
    }

    public EnemyTroop getEnemyTroop(int index) {
        return enemyTroopList.get(index);
    }

    public Animation getAnimation(int index) {
        return animationList.get(index);
    }

    public Item getItem(int index) {
        return itemList.get(index);
    }

    public Equip getEquip(int index) {
        return equipList.get(index);
    }

    public Skill getSkill(int index) {
        return skillList.get(index);
    }

    public Map getMap(int index) {
        if (!mapList.containsKey(index)) {
            mapList.put(index, DataFactory.loadMap(index));
        }
        return (Map) mapList.get(index);
    }
}
