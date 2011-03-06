package model;

import emulator.EmulatorImage;

/**
 *
 * 物品、技能、装备的父类
 */
public abstract class BaseItem {

    /**
     * 单体攻击技能
     */
    public static final byte SKILL_SINGLE = 0;
    /**
     * 全体攻击技能
     */
    public static final byte SKILL_ALL = 1;
    /**
     * 辅助性技能
     */
    public static final byte SKILL_ASSIST = 2;
    /**
     * 单体攻击物品
     */
    public static final byte ITEM_SINGLE = 0;
    /**
     * 全体攻击物品
     */
    public static final byte ITEM_ALL = 1;
    /**
     * 辅助性物品
     */
    public static final byte ITEM_ASSIST = 2;
    /**
     * 其它物品
     */
    public static final byte ITEM_OTHER = 3;
    /**
     * 头盔
     */
    public static final byte EQUIP_HELM = 0;
    /**
     * 饰品
     */
    public static final byte EQUIP_JEWELRY = 1;
    /**
     * 武器
     */
    public static final byte EQUIP_WEAPON = 2;
    /**
     * 盾牌
     */
    public static final byte EQUIP_SHIELD = 3;
    /**
     * 铠甲
     */
    public static final byte EQUIP_ARMOUR = 4;
    /**
     * 战靴
     */
    public static final byte EQUIP_BOOTS = 5;
    public int index;//编号
    public String name;//名称
    public String intro;//介绍
    public EmulatorImage icon;//图标
    public int kind;//种类
    public int num;
    public int price;//购买价格
}
