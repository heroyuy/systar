package com.soyomaker.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;

@Entity
@Table(name = "player")
public class Player {

	public static final int UP = 0;

	public static final int DOWN = 1;

	public static final int LEFT = 2;

	public static final int RIGHT = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private Long userId;

	private Integer mapId;

	private String mapName;

	private Integer x;

	private Integer y;

	private Integer avatar;

	private Integer level;

	private Integer exp;

	private Integer money;

	private Integer hp;

	private Integer sp;

	private Integer stre;

	private Integer agil;

	private Integer inte;

	private Integer vita;

	@Transient
	private Map<Integer, PlayerTask> playerTaskMap = new LinkedHashMap<Integer, PlayerTask>();// 当前任务列表

	@Transient
	private Map<Integer, Item> equipMap = new HashMap<Integer, Item>();// 装备

	/**
	 * 添加任务
	 * 
	 * @param playerTask
	 *            任务
	 */
	public void addPlayerTask(PlayerTask playerTask) {
		this.playerTaskMap.put(playerTask.getId().getTaskId(), playerTask);
	}

	public boolean canApplyTask(Task task) {
		// (1)检查任务是否已经存在
		if (this.hasPlayerTask(task.getId())) {
			return false;
		}
		// (2)检查前置任务是否完成
		if (task.getPreTask() != Task.TASK_TAG_NO_PRE_TASK) {
			// 有前置任务
			PlayerTask ptPre = this.getPlayerTask(task.getPreTask());
			if (ptPre == null || !ptPre.isFinished()) {
				// 前置任务未开始或者未完成
				return false;
			}
		}
		// (3)检查申请条件
		// TODO
		return true;
	}

	public boolean canFinishTask(Task task) {
		return true;
	}

	/**
	 * 清空任务列表
	 */
	public void clearPlayerTaskList() {
		playerTaskMap.clear();
	}

	public Integer getAgil() {
		return agil;
	}

	/**
	 * 获取攻击力
	 * 
	 * @return
	 */
	public int getAtk() {
		// TODO Atk计算公式
		int atk = 0;
		for (Item item : equipMap.values()) {
			atk += item.getAtk();
		}
		return this.getStre() + atk;
	}

	public Integer getAvatar() {
		return avatar;
	}

	/**
	 * 获取防御
	 * 
	 * @return
	 */
	public int getDef() {
		// TODO Def计算公式
		int def = 0;
		for (Item item : equipMap.values()) {
			def += item.getDef();
		}
		return def;
	}

	public Integer getExp() {
		return exp;
	}

	/**
	 * 获取闪避率
	 */
	public int getFlee() {
		// TODO Flee公式
		int flee = 0;
		for (Item item : equipMap.values()) {
			flee += item.getFlee();
		}
		return flee;
	}

	/**
	 * 获取命中率
	 * 
	 * @return
	 */
	public int getHit() {
		// TODO Hit公式
		int hit = 0;
		for (Item item : equipMap.values()) {
			hit += item.getHit();
		}
		return hit;
	}

	public Integer getHp() {
		return hp;
	}

	public Integer getId() {
		return id;
	}

	public Integer getInte() {
		return inte;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getMapId() {
		return mapId;
	}

	public String getMapName() {
		return mapName;
	}

	/**
	 * 获取角色最大HP
	 * 
	 * @return
	 */
	public int getMaxHP() {
		// TODO MaxHP计算公式
		return this.getVita() * 10;
	}

	/**
	 * 获取角色最大SP
	 * 
	 * @return
	 */
	public int getMaxSP() {
		// TODO MaxSP计算公式
		return this.getInte() * 7;
	}

	public Integer getMoney() {
		return money;
	}

	public String getName() {
		return name;
	}

	/**
	 * 获取任务
	 * 
	 * @param taskId
	 *            任务ID
	 * @return 任务
	 */
	public PlayerTask getPlayerTask(int taskId) {
		return playerTaskMap.get(taskId);
	}

	/**
	 * 获取任务列表
	 * 
	 * @return 任务列表
	 */
	public Collection<PlayerTask> getPlayerTaskList() {
		return playerTaskMap.values();
	}

	public Integer getSp() {
		return sp;
	}

	public Integer getStre() {
		return stre;
	}

	public Long getUserId() {
		return userId;
	}

	public Integer getVita() {
		return vita;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	/**
	 * 检查任务是否存在
	 * 
	 * @param taskId
	 *            任务ID
	 * @return 任务是否存在
	 */
	public boolean hasPlayerTask(int taskId) {
		return playerTaskMap.containsKey(taskId);
	}

	/**
	 * 删除任务
	 * 
	 * @param taskId
	 *            任务ID
	 */
	public void removePlayerTask(int taskId) {
		playerTaskMap.remove(taskId);
	}

	public void setAgil(Integer agil) {
		this.agil = agil;
	}

	public void setAvatar(Integer avatar) {
		this.avatar = avatar;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInte(Integer inte) {
		this.inte = inte;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSp(Integer sp) {
		this.sp = sp;
	}

	public void setStre(Integer stre) {
		this.stre = stre;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setVita(Integer vita) {
		this.vita = vita;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}
}
