package game.impl.model;

import game.AbModel;

/**
 * 游戏基本信息类
 * @author wp_g4
 * 测试通过
 */
public class Config extends AbModel {

    public String name = "";
    public String help = "";
    public String about = "";
    public String hp = "HP";
    public String sp = "SP";
    public String stre = "力量";
    public String inte = "智力";
    public String agil = "敏捷";
    public String atk = "攻击力";
    public String def = "防御力";
    public String flee = "闪避";
    public String money = "金币";
    public String helm = "头盔";
    public String armour = "铠甲";
    public String weapon = "武器";
    public String shield = "盾牌";
    public String boots = "战靴";
    public String jewelry = "饰品";

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
