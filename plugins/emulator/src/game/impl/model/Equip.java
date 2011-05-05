package game.impl.model;

import game.AbModel;

/**
 *
 * @author wp_g4
 * 测试通过
 */
public class Equip extends AbModel {

    public int kind;//种类
    public String name;//名称
    public String intro;//介绍
    public String icon;//图标
    public int stre;//增加力量
    public int agil;//增加敏捷
    public int inte;//增加智力
    public int maxHp;//增加最大血量
    public int maxSp;//增加最大魔法值
    public int lev;//需要等级
    public int atk;//增加攻击
    public int def;//增加防御
    public int flee;//增加闪避
    public int price;//购买价格

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
