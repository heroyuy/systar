package game.impl.model;

import game.AbModel;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public class Skill extends AbModel {

    public int kind;//种类
    public String name;//名称
    public String intro;//介绍
    public String icon;//图标
    public int hp;//增加/减少血量
    public int sp;//增加/减少魔法值
    public int lev;//需要等级
    public int atk;//增加/减少攻击力
    public int def;//增加/减少防御
    public int dpy = 0;// 伤害分散度
    public int aniIndex = 0;//技能动画id
    public int speed = 0;
    public int price;//购买价格

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
