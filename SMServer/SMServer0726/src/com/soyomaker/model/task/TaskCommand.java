package com.soyomaker.model.task;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务命令
 * 
 * @author wp_g4
 * 
 */
public class TaskCommand {

	/**
	 * 切换地图 switchMap(307,6,7)
	 */
	public static final String TYPE_SWITCH_MAP = "switchMap";

	/**
	 * 操作NPC operateNpc(6,1)
	 */
	public static final String TYPE_OPERATE_NPC = "operateNpc";

	/**
	 * 解析字符串为任务命令
	 * 
	 * @param command
	 * @return
	 */
	public static TaskCommand parseStringToCommand(String command) {
		command = command.trim();
		command.replace(")", "");
		TaskCommand taskCommand = new TaskCommand();
		String[] strs = command.split("\\(");
		String strType = strs[0];
		String strParam = strs[1];
		// (1)解析type
		taskCommand.setType(strType);
		// (2)解析参数
		String[] paramStrs = strParam.split(",");
		List<String> params = new ArrayList<String>();
		for (int i = 0; i < paramStrs.length; i++) {
			params.add(paramStrs[i]);
		}
		taskCommand.setParamList(params);
		return taskCommand;
	}

	/**
	 * 解析字符串为任务命令列表
	 * 
	 * @param multiCommand
	 * @return
	 */
	public static List<TaskCommand> parseStringToCommands(String multiCommand) {
		multiCommand = multiCommand.trim();
		List<TaskCommand> taskComamndList = new ArrayList<TaskCommand>();
		if (multiCommand != null && !multiCommand.equals("")) {
			String[] commands = multiCommand.split("\\|");
			for (String cmd : commands) {
				taskComamndList.add(TaskCommand.parseStringToCommand(cmd));
			}
		}
		return taskComamndList;
	}

	/**
	 * 命令类型
	 */
	public String type;

	/**
	 * 命令参数列表
	 */
	public List<String> paramList;

	private TaskCommand() {

	}

	public List<String> getParamList() {
		return paramList;
	}

	public String getType() {
		return type;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}

	public void setType(String type) {
		this.type = type;
	}
}
