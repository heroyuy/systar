/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.model.map;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 一条脚本命令
 * @author Administrator
 */
public class Command implements Cloneable {
    //*************************************流程控制类*开始

    /**
     *
     */
    public static final int PUBLIC_EVENT = 100000;//公共事件
    /**
     *
     */
    public static final int WAIT = 100001;//等待
    //*************************************流程控制类*结束
    //*************************************游戏表现类*开始
    /**
     *
     */
    public static final int CHANGE_MAP = 101000;//切换地图
    /**
     *
     */
    public static final int MOVE = 101001;//强制移动
    /**
     *
     */
    public static final int SHOW_COUNT_DOWN = 101002;//显示倒计时
    /**
     *
     */
    public static final int PLAY_MUSIC = 101003;//播放音乐
    /**
     *
     */
    public static final int STOP_MUSIC = 101004;//停止音乐
    /**
     *
     */
    public static final int OPEN_SHOP = 101005;//开启商店
    /**
     *
     */
    public static final int SHOW_MESSAGE = 101006;//显示对话
    /**
     *
     */
    public static final int SHOW_INPUT = 101007;//显示输入框
    /**
     *
     */
    public static final int SHOW_CHOOSE = 101008;//显示选项框
    /**
     *
     */
    public static final int CHANGE_FACE = 101009;//改变面向
    /**
     *
     */
    public static final int START_BATTLE = 101010;//开启战斗
    /**
     *
     */
    public static final int STOP_BATTLE = 101011;//结束战斗
    /**
     *
     */
    public static final int SHOW_IMAGE = 101012;//显示图片
    /**
     *
     */
    public static final int REMOVE_IMAGE = 101013;//移除图片
    /**
     *
     */
    public static final int MOVE_IMAGE = 101014;//移动图片
    /**
     *
     */
    public static final int ROTATE_IMAGE = 101015;//旋转图片
    /**
     *
     */
    public static final int CHANGE_IMAGE_COLOR = 101016;//更改图片色调
    /**
     *
     */
    public static final int SHOW_ANIMATION_BY_LOCATION = 101017;//显示动画
    /**
     *
     */
    public static final int SHOW_ANIMATION_BY_TARGET = 101018;//显示动画
    /**
     *
     */
    public static final int CHANGE_SKIN = 101019;//更换皮肤
    /**
     *
     */
    public static final int CHANGE_SCREEN_COLOR = 101020;//更改屏幕色调
    /**
     *
     */
    public static final int SCREEN_SHOCK = 101021;//画面震动
    /**
     *
     */
    public static final int OPEN_SYSTEM_MENU = 101022;//打开系统菜单
    /**
     *
     */
    public static final int OPEN_RECORD_MENU = 101023;//打开存档菜单
    /**
     *
     */
    public static final int OPEN_MAIN_MENU = 101024;//回主菜单
    //*************************************游戏表现类*结束
    //*************************************数据处理类*开始
    /**
     *
     */
    public static final int OPERATE_SWITCH = 102000;//开关操作
    /**
     *
     */
    public static final int GET_SWITCH_STATE = 102001;//获取开关值
    /**
     *
     */
    public static final int OPERATE_VAR = 102002;//变量操作
    /**
     *
     */
    public static final int GET_VAR_STATE = 102003;//获取变量值
    /**
     *
     */
    public static final int OPERATE_POWER = 102004;//玩家属性操作
    /**
     *
     */
    public static final int GET_POWER_STATE = 102005;//获取玩家属性
    /**
     *
     */
    public static final int CHANGE_PLAYER_NAME = 102006;//改变玩家名称
    /**
     *
     */
    public static final int GET_PLAYER_NAME = 102007;//获取玩家名称
    /**
     *
     */
    public static final int OPERATE_SKILL = 102008;//技能操作
    /**
     *
     */
    public static final int GET_SKILL_STATE = 102009;//获取技能状态
    /**
     *
     */
    public static final int OPERATE_EQUIP = 102010;//装备操作
    /**
     *
     */
    public static final int GET_EQUIP_STATE = 102011;//获取装备状态
    /**
     *
     */
    public static final int CHNAGE_PLAYER_MODEL = 102012;//改变玩家模型
    /**
     *
     */
    public static final int OPERATE_MONEY = 102013;//金钱操作
    /**
     *
     */
    public static final int GET_MONEY_NUMBER = 102014;//获取金钱数量
    /**
     *
     */
    public static final int OPERATE_ITEM_LIST = 102015;//物品列表操作
    /**
     *
     */
    public static final int GET_ITEM_NUMBER = 102016;//获取物品数量
    /**
     *
     */
    public static final int OPERATE_EQUIP_LIST = 102017;//装备列表操作
    /**
     *
     */
    public static final int GET_EQUIP_NUMBER = 102018;//获取装备数量
    /**
     *
     */
    public static final int OPERATE_ENEMY_POWER = 102019;//敌人属性操作
    /**
     *
     */
    public static final int GET_ENEMY_POWER = 102020;//获取敌人属性
    /**
     *
     */
    public static final int CHANGE_ENEMY_MODEL = 102021;//改变敌人模型
    //*************************************数据处理类*结束
    //*************************************辅助命令类*开始
    /**
     *
     */
    public static final int BREAK = 010001;//跳出循环 do break end
    /**
     *
     */
    public static final int EXIT_SCRIPT = 010002;//结束脚本 do return end
    /**
     *
     */
    public static final int WHILE = 010004;//循环命令 while true do
    /**
     *
     */
    public static final int IF = 010005;//条件命令
    //*************************************辅助命令类*结束
    /**
     *
     */
    public static final int INVALID = -1;
    /**
     *
     */
    public static final HashMap<Integer, String> COMMAND_MAP = new HashMap<Integer, String>();

    static {
        COMMAND_MAP.put(PUBLIC_EVENT, "公共事件");
        COMMAND_MAP.put(WAIT, "等待");
        COMMAND_MAP.put(CHANGE_MAP, "切换地图");
        COMMAND_MAP.put(MOVE, "强制移动");
        COMMAND_MAP.put(SHOW_COUNT_DOWN, "显示倒计时");
        COMMAND_MAP.put(PLAY_MUSIC, "播放音乐");
        COMMAND_MAP.put(STOP_MUSIC, "停止音乐");
        COMMAND_MAP.put(OPEN_SHOP, "开启商店");
        COMMAND_MAP.put(SHOW_MESSAGE, "显示对话");
        COMMAND_MAP.put(SHOW_INPUT, "显示输入框");
        COMMAND_MAP.put(SHOW_CHOOSE, "显示选项框");
        COMMAND_MAP.put(CHANGE_FACE, "改变面向");
        COMMAND_MAP.put(START_BATTLE, "开启战斗");
        COMMAND_MAP.put(STOP_BATTLE, "结束战斗");
        COMMAND_MAP.put(SHOW_IMAGE, "显示图片");
        COMMAND_MAP.put(REMOVE_IMAGE, "移除图片");
        COMMAND_MAP.put(MOVE_IMAGE, "移动图片");
        COMMAND_MAP.put(ROTATE_IMAGE, "旋转图片");
        COMMAND_MAP.put(CHANGE_IMAGE_COLOR, "更改图片色调");
        COMMAND_MAP.put(SHOW_ANIMATION_BY_LOCATION, "显示动画");
        COMMAND_MAP.put(SHOW_ANIMATION_BY_TARGET, "显示动画");
        COMMAND_MAP.put(CHANGE_SKIN, "更换皮肤");
        COMMAND_MAP.put(CHANGE_SCREEN_COLOR, "更改屏幕色调");
        COMMAND_MAP.put(SCREEN_SHOCK, "画面震动");
        COMMAND_MAP.put(OPEN_SYSTEM_MENU, "打开系统菜单");
        COMMAND_MAP.put(OPEN_RECORD_MENU, "打开存档菜单");
        COMMAND_MAP.put(OPEN_MAIN_MENU, "回主菜单");
        COMMAND_MAP.put(OPERATE_SWITCH, "开关操作");
        COMMAND_MAP.put(GET_SWITCH_STATE, "获取开关值");
        COMMAND_MAP.put(OPERATE_VAR, "变量操作");
        COMMAND_MAP.put(GET_VAR_STATE, "获取变量值");
        COMMAND_MAP.put(OPERATE_POWER, "玩家属性操作");
        COMMAND_MAP.put(GET_POWER_STATE, "获取玩家属性");
        COMMAND_MAP.put(CHANGE_PLAYER_NAME, "改变玩家名称");
        COMMAND_MAP.put(GET_PLAYER_NAME, "获取玩家名称");
        COMMAND_MAP.put(OPERATE_SKILL, "技能操作");
        COMMAND_MAP.put(GET_SKILL_STATE, "获取技能状态");
        COMMAND_MAP.put(OPERATE_EQUIP, "装备操作");
        COMMAND_MAP.put(GET_EQUIP_STATE, "获取装备状态");
        COMMAND_MAP.put(CHNAGE_PLAYER_MODEL, "改变玩家模型");
        COMMAND_MAP.put(OPERATE_MONEY, "金钱操作");
        COMMAND_MAP.put(GET_MONEY_NUMBER, "获取金钱数量");
        COMMAND_MAP.put(OPERATE_ITEM_LIST, "物品列表操作");
        COMMAND_MAP.put(GET_ITEM_NUMBER, "获取物品数量");
        COMMAND_MAP.put(OPERATE_EQUIP_LIST, "装备列表操作");
        COMMAND_MAP.put(GET_EQUIP_NUMBER, "获取装备数量");
        COMMAND_MAP.put(OPERATE_ENEMY_POWER, "敌人属性操作");
        COMMAND_MAP.put(GET_ENEMY_POWER, "获取敌人属性");
        COMMAND_MAP.put(CHANGE_ENEMY_MODEL, "改变敌人模型");
        COMMAND_MAP.put(INVALID, "空操作");
        COMMAND_MAP.put(BREAK, "跳出循环");
        COMMAND_MAP.put(EXIT_SCRIPT, "退出脚本");
        COMMAND_MAP.put(WHILE, "循环操作");
        COMMAND_MAP.put(IF, "条件操作");
    }
    private static final String HEAD = "Interpreter:doCommand";
    private int scriptId = INVALID;
    private ArrayList<String> parameters = new ArrayList<String>();
    private ArrayList<Command> commands = new ArrayList<Command>();//if 和 while要用到

    /**
     *
     * @return
     */
    public int commandSize() {
        return commands.size();
    }

    /**
     *
     * @param o
     * @return
     */
    public boolean removeCommand(Command o) {
        return commands.remove(o);
    }

    /**
     * 
     * @param index
     * @param element
     */
    public void addCommand(int index, Command element) {
        commands.add(index, element);
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean addCommand(Command e) {
        return commands.add(e);
    }

    /**
     *
     * @param index
     * @return
     */
    public Command getCommand(int index) {
        return commands.get(index);
    }

    /**
     *
     * @return
     */
    public int parametersSize() {
        return parameters.size();
    }

    /**
     *
     * @param index
     * @return
     */
    public String getParameter(int index) {
        return parameters.get(index);
    }

    @Override
    public Command clone() throws CloneNotSupportedException {
        Command clone = (Command) super.clone();
        clone.parameters = new ArrayList<String>();
        for (int i = 0; i < parameters.size(); i++) {
            clone.parameters.add(parameters.get(i));
        }
        return clone;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        switch (scriptId) {
            case INVALID:
                sb.append("");
                break;
            case BREAK:
                sb.append("do break end");
                break;
            case EXIT_SCRIPT:
                sb.append("do return end");
                break;
            case WHILE:
                sb.append("while true do");
                break;
            case IF:
                if (Integer.parseInt(parameters.get(0)) == 0) {
                    sb.append("if ").append("Interpreter:doCommand(102001,").append(parameters.get(1)).append(")").append("==").append(parameters.get(2)).append(" then");
                } else {
                    sb.append("if ").append("Interpreter:doCommand(102003,").append(parameters.get(1)).append(")").append(parameters.get(2)).append(parameters.get(3)).append(" then");
                }
                break;
            default:
                sb.append(HEAD);
                sb.append("(").append(scriptId);
                for (int i = 0; i < parameters.size(); i++) {
                    sb.append(",");
                    sb.append(parameters.get(i));
                }
                sb.append(")");
                break;
        }
        return sb.toString();
    }
    private static final String[] CHANGE_FACE_STRING = {"上", "下", "左", "右"};
    private static final String[] CHANGE_MAP_FACE_STRING = {"保持不变", "上", "下", "左", "右"};
    private static final String[] POWER_TYPE_STRING = {"力量", "敏捷", "智力", "体力", "灵巧", "幸运", "MaxHp", "MaxSp", "HP", "SP", "等级", "经验值"};
    private static final String[] OPERATE_TYPE_STRING = {"等于", "加", "减", "乘", "除", "模"};
    private static final String[] VALUE_TYPE_STRING = {"常量", "变量", "开关"};
    private static final String[] ROTATE_DIRECTION_STRING = {"顺时针", "逆时针"};
    private static final String[] BATTLE_TYPE_STRING = {"死亡模式", "非死亡模式"};
    private static final String[] MESSAGE_POSITION_STRING = {"左", "右"};
    private static final String[] TARGET_TYPE_STRING = {"玩家", "敌人", "NPC"};

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(COMMAND_MAP.get(scriptId));
        if (!parameters.isEmpty()) {
            sb.append(" | ");
        }
        switch (scriptId) {
            case BREAK:
                break;
            case CHANGE_ENEMY_MODEL:
                sb.append("敌人ID：").append(parameters.get(0));
                sb.append(" 新行走图：").append(parameters.get(1));
                break;
            case CHANGE_FACE:
                sb.append("面向：").append(CHANGE_FACE_STRING[Integer.parseInt(parameters.get(0))]);
                break;
            case CHANGE_IMAGE_COLOR:
                sb.append("图片名称：").append(parameters.get(0));
                sb.append(" alpha：").append(parameters.get(1));
                sb.append(" red：").append(parameters.get(2));
                sb.append(" green：").append(parameters.get(3));
                sb.append(" blue：").append(parameters.get(4));
                sb.append(" 时间：").append(parameters.get(5)).append("ms");
                break;
            case CHANGE_MAP:
                sb.append("地图ID：").append(parameters.get(0));
                sb.append(" 行号：").append(parameters.get(1));
                sb.append(" 列号：").append(parameters.get(2));
                sb.append(" 面向：").append(CHANGE_MAP_FACE_STRING[Integer.parseInt(parameters.get(0))]);
                break;
            case CHANGE_PLAYER_NAME:
                sb.append("玩家ID：").append(parameters.get(0));
                sb.append(" 新名称：").append(parameters.get(1));
                break;
            case CHANGE_SCREEN_COLOR:
                sb.append("alpha：").append(parameters.get(0));
                sb.append(" red：").append(parameters.get(1));
                sb.append(" green：").append(parameters.get(2));
                sb.append(" blue：").append(parameters.get(3));
                sb.append(" 时间：").append(parameters.get(4)).append("ms");
                break;
            case CHANGE_SKIN:
                sb.append("新皮肤：").append(parameters.get(0));
                break;
            case CHNAGE_PLAYER_MODEL:
                sb.append("玩家ID：").append(parameters.get(0));
                sb.append(" 新行走图：").append(parameters.get(1));
                break;
            case EXIT_SCRIPT:
                break;
            case GET_ENEMY_POWER:
                sb.append("敌人ID：").append(parameters.get(0));
                sb.append(" 属性类型：").append(POWER_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                break;
            case GET_EQUIP_NUMBER:
                sb.append("装备ID：").append(parameters.get(0));
                break;
            case GET_EQUIP_STATE:
                sb.append("玩家ID：").append(parameters.get(0));
                sb.append(" 装备ID：").append(parameters.get(1));
                break;
            case GET_ITEM_NUMBER:
                sb.append("物品ID：").append(parameters.get(0));
                break;
            case GET_MONEY_NUMBER:
                break;
            case GET_PLAYER_NAME:
                sb.append("玩家ID：").append(parameters.get(0));
                break;
            case GET_POWER_STATE:
                sb.append("玩家ID：").append(parameters.get(0));
                sb.append(" 属性类型：").append(POWER_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                break;
            case GET_SKILL_STATE:
                sb.append("玩家ID：").append(parameters.get(0));
                sb.append(" 技能ID：").append(parameters.get(1));
                break;
            case GET_SWITCH_STATE:
                sb.append("开关ID：").append(parameters.get(0));
                break;
            case GET_VAR_STATE:
                sb.append("变量ID：").append(parameters.get(0));
                break;
            case IF:
//                if (parameters.get(0).equals("0")) {
//                }
//                if (parameters.get(0).equals("1")) {
//                }
                break;
            case INVALID:
                break;
            case MOVE:
                sb.append("目的地行号：").append(parameters.get(0));
                sb.append(" 目的地列号：").append(parameters.get(1));
                break;
            case MOVE_IMAGE:
                sb.append("图片名称：").append(parameters.get(0));
                sb.append(" x坐标：").append(parameters.get(1));
                sb.append(" y坐标：").append(parameters.get(2));
                sb.append(" 时间：").append(parameters.get(3)).append("ms");
                break;
            case OPEN_MAIN_MENU:
                break;
            case OPEN_RECORD_MENU:
                break;
            case OPEN_SHOP:
                sb.append("出售列表：").append(parameters.get(0));
                break;
            case OPEN_SYSTEM_MENU:
                break;
            case OPERATE_ENEMY_POWER:
                sb.append("敌人ID：").append(parameters.get(0));
                sb.append(" 属性类型：").append(POWER_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                sb.append(" 操作类型：").append(OPERATE_TYPE_STRING[Integer.parseInt(parameters.get(2))]);
                sb.append(" 值类型：").append(VALUE_TYPE_STRING[Integer.parseInt(parameters.get(3))]);
                sb.append(" 值：").append(parameters.get(4));
                break;
            case OPERATE_EQUIP:
                sb.append("玩家ID：").append(parameters.get(0));
                sb.append(" 装备列表：").append(parameters.get(1));
                break;
            case OPERATE_EQUIP_LIST:
                sb.append("装备ID：").append(parameters.get(0));
                sb.append(" 操作类型：").append(OPERATE_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                sb.append(" 值类型：").append(VALUE_TYPE_STRING[Integer.parseInt(parameters.get(2))]);
                sb.append(" 值：").append(parameters.get(3));
                break;
            case OPERATE_ITEM_LIST:
                sb.append("物品ID：").append(parameters.get(0));
                sb.append(" 操作类型：").append(OPERATE_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                sb.append(" 值类型：").append(VALUE_TYPE_STRING[Integer.parseInt(parameters.get(2))]);
                sb.append(" 值：").append(parameters.get(3));
                break;
            case OPERATE_MONEY:
                sb.append(" 操作类型：").append(OPERATE_TYPE_STRING[Integer.parseInt(parameters.get(0))]);
                sb.append(" 值类型：").append(VALUE_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                sb.append(" 值：").append(parameters.get(2));
                break;
            case OPERATE_POWER:
                sb.append("玩家ID：").append(parameters.get(0));
                sb.append(" 属性类型：").append(POWER_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                sb.append(" 操作类型：").append(OPERATE_TYPE_STRING[Integer.parseInt(parameters.get(2))]);
                sb.append(" 值类型：").append(VALUE_TYPE_STRING[Integer.parseInt(parameters.get(3))]);
                sb.append(" 值：").append(parameters.get(4));
                break;
            case OPERATE_SKILL:
                sb.append("玩家ID：").append(parameters.get(0));
                sb.append(" 技能ID：").append(parameters.get(1));
                sb.append(" 值：").append(parameters.get(2).equals("true") ? "领悟" : "遗忘");
                break;
            case OPERATE_SWITCH:
                sb.append("开关ID：").append(parameters.get(0));
                sb.append(" 开关值：").append(parameters.get(1));
                break;
            case OPERATE_VAR:
                sb.append("变量ID：").append(parameters.get(0));
                sb.append(" 操作类型：").append(OPERATE_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                sb.append(" 值类型：").append(VALUE_TYPE_STRING[Integer.parseInt(parameters.get(2))]);
                sb.append(" 值：").append(parameters.get(3));
                break;
            case PLAY_MUSIC:
                sb.append("音乐名称：").append(parameters.get(0));
                sb.append(" 是否循环：").append(parameters.get(1));
                break;
            case PUBLIC_EVENT:
                sb.append("脚本ID：").append(parameters.get(0));
                break;
            case REMOVE_IMAGE:
                sb.append("图片名称：").append(parameters.get(0));
                break;
            case ROTATE_IMAGE:
                sb.append("图片名称：").append(parameters.get(0));
                sb.append(" 旋转方向：").append(ROTATE_DIRECTION_STRING[Integer.parseInt(parameters.get(1))]);
                sb.append(" 旋转度数：").append(parameters.get(2));
                sb.append(" 时间：").append(parameters.get(3)).append("ms");
                break;
            case SCREEN_SHOCK:
                sb.append("震动幅度：").append(parameters.get(0));
                sb.append(" 震动时间：").append(parameters.get(1)).append("ms");
                sb.append(" 震动次数：").append(parameters.get(2));
                break;
            case SHOW_ANIMATION_BY_LOCATION:
                sb.append("动画ID：").append(parameters.get(0));
                sb.append(" x坐标：").append(parameters.get(1));
                sb.append(" y坐标：").append(parameters.get(2));
                sb.append(" 播放次数：").append(parameters.get(3));
                break;
            case SHOW_ANIMATION_BY_TARGET:
                sb.append("动画ID：").append(parameters.get(0));
                sb.append(" 目标类型：").append(TARGET_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                sb.append(" 目标ID：").append(parameters.get(2));
                sb.append(" 播放次数：").append(parameters.get(3));
                break;
            case SHOW_CHOOSE:
                sb.append("选项列表：").append(parameters.get(0));
                break;
            case SHOW_COUNT_DOWN:
                sb.append("倒计时：").append(parameters.get(0)).append("ms");
                break;
            case SHOW_IMAGE:
                sb.append("图片名称：").append(parameters.get(0));
                sb.append(" x坐标：").append(parameters.get(1));
                sb.append(" y坐标：").append(parameters.get(2));
                sb.append(" 显示时间：").append(MESSAGE_POSITION_STRING[Integer.parseInt(parameters.get(1))]).append("ms");
                break;
            case SHOW_INPUT:
                sb.append("输入框提醒文本：").append(parameters.get(0));
                break;
            case SHOW_MESSAGE:
                sb.append("姓名：").append(parameters.get(0));
                sb.append(" 内容：").append(parameters.get(1));
                sb.append(" 半身像文件：").append(parameters.get(2));
                sb.append(" 半身像位置：").append(MESSAGE_POSITION_STRING[Integer.parseInt(parameters.get(3))]);
                break;
            case START_BATTLE:
                sb.append("敌人队伍ID：").append(parameters.get(0));
                sb.append(" 战斗类型：").append(BATTLE_TYPE_STRING[Integer.parseInt(parameters.get(1))]);
                break;
            case STOP_BATTLE:
                break;
            case STOP_MUSIC:
                sb.append("音乐名称：").append(parameters.get(0));
                break;
            case WAIT:
                sb.append("等待时间：").append(parameters.get(0)).append("ms");
                break;
            case WHILE:
                break;
            default:
                for (int i = 0; i < parameters.size(); i++) {
                    sb.append(parameters.get(i));
                    if (i != parameters.size() - 1) {
                        sb.append(",");
                    }
                }
                break;
        }
        return sb.toString();
    }

    /**
     *
     * @return
     */
    public int getScriptId() {
        return scriptId;
    }

    /**
     *
     * @param scriptId
     */
    public void setScriptId(int scriptId) {
        this.scriptId = scriptId;
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean addParameter(String e) {
        return parameters.add(e);
    }
}
