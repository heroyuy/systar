package game.impl.model;

import com.soyostar.app.Image;
import game.AbModel;

/**
 * 战斗者
 * @author wp_g4
 */
public abstract class Battler extends AbModel {

    public String name;//名称
    public String intro;//介绍
    public Image battlerImage;//头像
    public int stre;//力量
    public int agil;//敏捷
    public int inte;//智力
    public int maxHp;//最大血量
    public int maxSp;//最大魔法值
    public int hp;//血量
    public int sp;//魔法值
    public int flee;//闪避
    public int lev;//等级
    public int atk;//攻击
    public int def;//防御
    public int exp;//经验值
    public int money;//金钱
    public int[] skillList = null;//技能列表
    @Override
    public void update() {
    }
}
