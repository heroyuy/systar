package com.soyomaker.data;

import java.util.HashMap;

import com.soyomaker.data.model.Animation;
import com.soyomaker.data.model.Config;
import com.soyomaker.data.model.Enemy;
import com.soyomaker.data.model.EnemyTroop;
import com.soyomaker.data.model.Equip;
import com.soyomaker.data.model.Item;
import com.soyomaker.data.model.Map;
import com.soyomaker.data.model.NPC;
import com.soyomaker.data.model.Player;
import com.soyomaker.data.model.Skill;
import com.soyomaker.data.model.Status;
import com.soyomaker.data.model.Vocation;

public class DataManager {

	private static DataManager instance;

	/************************ DataHolder ************************/
	private HashMap<Integer, Vocation> vocationMap = new HashMap<Integer, Vocation>();
	private HashMap<Integer, Player> playerMap = new HashMap<Integer, Player>();
	private HashMap<Integer, Skill> skillMap = new HashMap<Integer, Skill>();
	private HashMap<Integer, Item> itemMap = new HashMap<Integer, Item>();
	private HashMap<Integer, Equip> equipMap = new HashMap<Integer, Equip>();
	private HashMap<Integer, Enemy> enemyMap = new HashMap<Integer, Enemy>();
	private HashMap<Integer, EnemyTroop> enemyTroopMap = new HashMap<Integer, EnemyTroop>();
	private HashMap<Integer, Status> statuseMap = new HashMap<Integer, Status>();
	private HashMap<Integer, Map> mapMap = new HashMap<Integer, Map>();
	private HashMap<Integer, NPC> npcMap = new HashMap<Integer, NPC>();
	private HashMap<Integer, Animation> animationMap = new HashMap<Integer, Animation>();
	private Config config = null;

	/************************ DataLoader ************************/
	DataLoader dataLoader = new DataLoader();

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	private DataManager() {

	}

	/************************************ 获取方法 ******************************************/

	public Vocation getVocation(int id) {
		if (!vocationMap.containsKey(id)) {

		}
		return vocationMap.get(id);
	}

	public Map getMap(int id) {
		if (!mapMap.containsKey(id)) {
			mapMap.put(id, dataLoader.loadMap(id));
		}
		return mapMap.get(id);
	}
}
