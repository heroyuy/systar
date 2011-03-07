package  frame.event.ui_event;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import  frame.event.ui_event.dialog_game.*;
import  frame.event.ui_event.dialog_player.*;
import  frame.event.ui_event.dialog_process.*;

/**
 * 
 * @author Administrator
 */
public class UI_Event_Manager {

	public static final byte PROCESS = 0;// 流程控制

	public static final byte GAME = 1;// 游戏控制

	public static final byte PLAYER = 2;// 玩家控制

	private Hashtable[] list = new Hashtable[3]; // 流程控制类对话框容器 //游戏控制类对话框容器
													// //角色控制类对话框容器

	private Dialog_Main dialog_Main = null;

	public UI_Event_Manager(Dialog_Main dialog_Main) {
		this.dialog_Main = dialog_Main;
		initList_Process();
		initList_Game();
		initList_Player();
	}

	public Event_Dialog getEvent_Dialog(String text) {
		Event_Dialog event_Dialog = null;
		event_Dialog = (Event_Dialog) list[0].get(text);
		if (event_Dialog == null) {
			event_Dialog = (Event_Dialog) list[1].get(text);
		}
		if (event_Dialog == null) {
			event_Dialog = (Event_Dialog) list[2].get(text);
		}
		return event_Dialog;
	}

	protected String[] getTexts(byte type) {
		Enumeration e = list[type].keys();
		Vector v = new Vector();
		while (e.hasMoreElements()) {
			v.add(e.nextElement());
		}
		String[] t = new String[v.size()];
		for (int i = 0; i < t.length; i++) {
			t[i] = (String) v.elementAt(i);
		}
		v = null;
		e = null;
		return t;
	}

	private void initList_Game() {
		list[1] = new Hashtable();
		list[1].put("变量操作", new Dialog_Var(dialog_Main));
		list[1].put("开关操作", new Dialog_Switch(dialog_Main));
		list[1].put("显示对话", new Dialog_Dialog(dialog_Main));
		list[1].put("播放音乐", new Dialog_Music(dialog_Main));
		list[1].put("强制等待", new Dialog_Wait(dialog_Main));
		list[1].put("强制移动", new Dialog_Move(dialog_Main));
		list[1].put("切换地图", new Dialog_Map  (dialog_Main));
		list[1].put("改变面向", new Dialog_Face(dialog_Main));
		list[1].put("物品商店", new Dialog_ItemShop(dialog_Main));
		list[1].put("装备商店", new Dialog_EquipShop(dialog_Main));
		list[1].put("开启战斗", new Dialog_Fight(dialog_Main));
		list[1].put("结束游戏", new Dialog_ExitGame(dialog_Main));

	}

	private void initList_Player() {
		list[2] = new Hashtable();
		list[2].put("等级", new Dialog_Level(dialog_Main));
		list[2].put("经验", new Dialog_Exp(dialog_Main));
		list[2].put("血量", new Dialog_Hp(dialog_Main));
		list[2].put("魔法", new Dialog_Sp(dialog_Main));
		list[2].put("金钱", new Dialog_Money(dialog_Main));
		list[2].put("物品", new Dialog_Item(dialog_Main));
		list[2].put("装备", new Dialog_Equip(dialog_Main));
		list[2].put("技能", new Dialog_Skill(dialog_Main));
		list[2].put("属性", new Dialog_Attribute(dialog_Main));
	}

	private void initList_Process() {
		list[0] = new Hashtable();
		list[0].put("循环结构", new Dialog_While(dialog_Main));
		list[0].put("条件结构", new Dialog_If(dialog_Main));
		list[0].put("跳出循环", new Dialog_Break(dialog_Main));
		list[0].put("下次循环", new Dialog_Continue(dialog_Main));
		list[0].put("结束脚本", new Dialog_ExitScript(dialog_Main));
		list[0].put("脚本注释", new Dialog_Note(dialog_Main));
	}
}
