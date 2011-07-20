package game.impl.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public class Player extends Sprite implements Cloneable {

    public String name;//名称
    public String intro;//介绍
    public String headImg;//头像
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

    @Override
    public Player clone() {
        Player p = null;
        try {
            p = (Player) super.clone();
            if (this.levList != null) {
                p.levList = new int[this.levList.length];
                System.arraycopy(this.levList, 0, p.levList, 0, p.levList.length);
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
