package com.soyomaker.emulator.util;

import java.util.ArrayList;
import java.util.Collection;

import com.soyomaker.emulator.net.NetTransceiver;
import com.soyomaker.lang.GameObject;

public class Net {

	private static final String MSG_ID_LOGIN_REGISTER = "101001";

	private static final String MSG_ID_LOGIN_LOGIN = "101002";

	private static final String MSG_ID_PLAYER_CREATE = "102001";

	private static final String MSG_ID_PLAYER_DELETE = "102002";

	private static final String MSG_ID_PLAYER_LIST = "102003";

	private static final String MSG_ID_PLAYER_CHOOSE = "102004";

	private static final String MSG_ID_MAP_MOVE = "103001";

	private static final String MSG_ID_CHAT_SEND = "104001";

	private static final String MSG_ID_TASK_APPLY = "105001";

	private static final String MSG_ID_TASK_GIVE_UP = "105002";

	private static final String MSG_ID_TASK_LIST = "105003";

	private static final String MSG_ID_TASK_NEXT_STEP = "105004";

	private static Net instance = new Net();

	public static Net getInstance() {
		return instance;
	}

	private Net() {

	}

	/**
	 * 注册101001
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	public void register(String username, String password) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_LOGIN_REGISTER);
		msg.putString("username", username);
		msg.putString("password", password);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 登录 101002
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	public void login(String username, String password) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_LOGIN_LOGIN);
		msg.putString("username", username);
		msg.putString("password", password);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 创建角色 102001
	 * 
	 * @param name
	 *            角色呢称
	 */
	public void createPlayer(String name) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_PLAYER_CREATE);
		msg.putString("name", name);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 删除角色 102002
	 * 
	 * @param playerId
	 *            角色ID
	 */
	public void deletePlayer(int playerId) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_PLAYER_DELETE);
		msg.putInt("playerId", playerId);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 获取角色列表 102003
	 */
	public void listPlayer() {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_PLAYER_LIST);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 选择角色列表 102004
	 */
	public void choosePlayer(int playerId) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_PLAYER_CHOOSE);
		msg.putInt("playerId", playerId);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 移动角色 103001
	 */
	public void move(String stepsStr) {
		Collection<Integer> steps = new ArrayList<Integer>();
		String[] strs = stepsStr.split(",");
		for (String string : strs) {
			steps.add(Integer.parseInt(string));
		}
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_MAP_MOVE);
		msg.putIntArray("steps", steps);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 发送聊天内容 104001
	 */
	public void sendChatContent(String content) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_CHAT_SEND);
		msg.putString("content", content);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 申请任务 105001
	 */
	public void applyTask(int id) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_TASK_APPLY);
		msg.putInt("id", id);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 放弃任务 105002
	 */
	public void giveUpTask(int id) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_TASK_GIVE_UP);
		msg.putInt("id", id);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 得到任务列表 105003
	 */
	public void listTask() {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_TASK_LIST);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 任务下一步 105004
	 */
	public void nextStepTask(int id) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_TASK_NEXT_STEP);
		msg.putInt("id", id);
		NetTransceiver.getInstance().sendMessage(msg);
	}
}
