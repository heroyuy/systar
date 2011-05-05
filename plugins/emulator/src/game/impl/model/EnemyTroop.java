package game.impl.model;

import game.AbModel;


/**
 *
 * @author wp_g4
 * 测试通过
 */
public class EnemyTroop extends AbModel {

    public int siteA = -1;//A位置处的敌人编号，-1表示此位置没有敌人
    public int siteB = -1;//B位置处的敌人编号，-1表示此位置没有敌人
    public int siteC = -1;//C位置处的敌人编号，-1表示此位置没有敌人
    public int siteD = -1;//D位置处的敌人编号，-1表示此位置没有敌人
    public String name;//名称
    public int[] itemList;

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
