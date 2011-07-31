package game.data;

import com.soyostar.app.Image;
import game.impl.model.Actor;
import game.impl.model.Config;
import game.impl.model.Enemy;
import game.impl.model.EnemyTroop;
import game.impl.model.Equip;
import game.impl.model.Item;
import game.impl.model.Player;
import game.impl.model.Skill;
import game.impl.model.animation.Animation;
import game.impl.model.Map;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * 游戏数据中心
 */
public class DataStore {

    private DataLoader dataLoader = null;
    private Config config = null;
    private HashMap<Integer, Animation> animationList = null;
    private HashMap<Integer, Enemy> enemyList = null;
    private HashMap<Integer, EnemyTroop> enemyTroopList = null;
    private HashMap<Integer, Item> itemList = null;
    private HashMap<Integer, Equip> equipList = null;
    private HashMap<Integer, Skill> skillList = null;
    private PlayerData playerData = null;
    private HashMap<Integer, Map> mapList = null;
    private static DataStore instance = new DataStore();

    public static DataStore getInstance() {
        return instance;
    }

    private DataStore() {
        dataLoader = new DataLoader();
        //************************************
        config = new Config();
        animationList = new HashMap<Integer, Animation>();
        enemyList = new HashMap<Integer, Enemy>();
        enemyTroopList = new HashMap<Integer, EnemyTroop>();
        itemList = new HashMap<Integer, Item>();
        equipList = new HashMap<Integer, Equip>();
        skillList = new HashMap<Integer, Skill>();
        playerData = new PlayerData();
        mapList = new HashMap<Integer, Map>();
        //*************************************
        init();
    }

    private void init() {
        System.out.println("初始化数据中心");
        dataLoader.loadConfig(config);
//      DataFactory.loadAnimationList(animationList);
        dataLoader.loadEnemyList(enemyList);
        dataLoader.loadEnemyTroopList(enemyTroopList);
        dataLoader.loadPlayerData(playerData);
        dataLoader.loadSkillList(skillList);
        dataLoader.loadItemList(itemList);
        dataLoader.loadEquipList(equipList);
    }

    public Config getConfig() {
        return config;
    }

    public Player getPlayer() {
        Player player = new Player();
        player.setCharImg("res" + playerData.characterImage);
        player.gotoMap(playerData.mapIndex, playerData.row, playerData.col, playerData.face);
        return player;
    }

    public Actor getActor() {
        Actor actor = new Actor();
        actor.name = playerData.name;
        actor.intro = playerData.intro;
        actor.battlerImage = Image.createImage("res" + playerData.battlerImage);
        actor.stre = playerData.stre;
        actor.agil = playerData.agil;
        actor.inte = playerData.inte;
        actor.hp = actor.maxHp = playerData.maxHp;
        actor.sp = actor.maxSp = playerData.maxSp;
        actor.flee = playerData.flee;
        actor.lev = playerData.lev;
        actor.atk = playerData.atk;
        actor.def = playerData.def;
        actor.money = playerData.money;
        actor.streByLev = playerData.streByLev;
        actor.agilByLev = playerData.agilByLev;
        actor.inteByLev = playerData.inteByLev;
        actor.expList = Arrays.copyOf(playerData.expList, playerData.expList.length);
        //以下为初始化
        actor.exp = 0;
        actor.skillList = new int[0];
        return actor;
    }

    public Enemy getEnemy(int index) {
        Enemy src = enemyList.get(index);
        Enemy res = new Enemy();
        res.setIndex(src.getIndex());
        res.name = src.name;
        res.intro = src.intro;
        res.battlerImage = src.battlerImage;
        res.stre = src.stre;
        res.agil = src.agil;
        res.inte = src.inte;
        res.maxHp = src.maxHp;
        res.maxSp = src.maxSp;
        res.lev = src.lev;
        res.atk = src.atk;
        res.def = src.def;
        res.exp = src.exp;
        res.money = src.money;
        res.skillList = Arrays.copyOf(src.skillList, src.skillList.length);

        return res;
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
            Map map = dataLoader.loadMap(index);
            mapList.put(index, map);
        }
        return (Map) mapList.get(index);
    }
}
