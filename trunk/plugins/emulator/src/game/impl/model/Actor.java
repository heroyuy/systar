package game.impl.model;

/**
 * 玩家控制的战斗者
 * @author wp_g4
 */
public class Actor extends Battler {

    public int streByLev;// 力量成长
    public int agilByLev;// 敏捷成长
    public int inteByLev;// 智力成长
    public int[] expList = null;//经验表
}
