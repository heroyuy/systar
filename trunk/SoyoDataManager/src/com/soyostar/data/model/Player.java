package com.soyostar.data.model;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public final class Player extends Model {

    public String name;//名称
    public String intro;//介绍
    public String headImg;//头像
    public String charImg;//头像
    public int stre;//力量
    public int agil;//敏捷
    public int inte;//智力
    public int hp;//血量
    public int sp;//魔法值
    public int lev;//等级
    public int atk;//攻击
    public int def;//防御
    public int flee;//闪避
    public int streByLev;// 力量成长
    public int agilByLev;// 敏捷成长
    public int inteByLev;// 智力成长
    public int[] levList = null;//经验表
    public int money;//金钱
    public int curMapIndex;// 角色当前所在地图编号
    public int row;// 角色所在行号
    public int col;// 角色当前所在列号
    public int face;// 面向
}
