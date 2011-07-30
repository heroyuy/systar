package game.data;

/**
 *
 * @author wp_g4
 */
 class PlayerData {

    public String name;//名称
    public String intro;//介绍
    public String battlerImage;//战斗图
    public String characterImage;//行走图
    public int stre;//力量
    public int agil;//敏捷
    public int inte;//智力
    public int maxHp;//最大血量
    public int maxSp;//最大魔法值
    public int lev;//等级
    public int atk;//攻击
    public int def;//防御
    public int flee;//闪避  <暂时不用>
    public int streByLev;// 力量成长
    public int agilByLev;// 敏捷成长
    public int inteByLev;// 智力成长
    public int[] expList = null;//经验表
    public int money;//金钱
    public int mapIndex;//所在地图编号
    public int row;//所在行号
    public int col;//所在列号
    public byte face;//面向
}
