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
	 * 任务命令文本的命令间分隔符,正则表达式，“|”
	 */
	private static final String TASK_COMMAND_SEPARATOR_COMMAND = "\\|";

	/**
	 * 任务命令文本的参数间分隔符
	 */
	private static final String TASK_COMMAND_SEPARATOR_PARAM = ":";

	/**
	 * 切换地图 1:307:6:7
	 */
	public static final int TYPE_SWITCH_MAP = 1;

	/**
	 * 操作NPC 3:6:1
	 */
	public static final int TYPE_OPERATE_NPC = 3;

	/**
	 * 解析字符串为任务命令
	 * 
	 * @param command
	 * @return
	 */
	public static TaskCommand parseStringToCommand(String command) {
		command = command.trim();
		TaskCommand taskCommand = new TaskCommand();
		String[] strs = command.split(TASK_COMMAND_SEPARATOR_PARAM);
		// (1)解析type
		taskCommand.setType(Integer.parseInt(strs[0]));
		// (2)解析参数
		List<String> params = new ArrayList<String>();
		for (int i = 1; i < strs.length; i++) {
			params.add(strs[i]);
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
			String[] commands = multiCommand
					.split(TASK_COMMAND_SEPARATOR_COMMAND);
			for (String cmd : commands) {
				taskComamndList.add(TaskCommand.parseStringToCommand(cmd));
			}
		}
		return taskComamndList;
	}

	/**
	 * 命令类型
	 */
	public int type;

	/**
	 * 命令参数列表
	 */
	public List<String> paramList;

	private TaskCommand() {

	}

	public List<String> getParamList() {
		return paramList;
	}

	public int getType() {
		return type;
	}

	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}

	public void setType(int type) {
		this.type = type;
	}

}
