package model;

/**
 *
 * 敌人队伍
 */
public class EnemyTroop {

    public int siteA = -1;//A位置处的敌人编号，-1表示此位置没有敌人
    public int siteB = -1;//B位置处的敌人编号，-1表示此位置没有敌人
    public int siteC = -1;//C位置处的敌人编号，-1表示此位置没有敌人
    public int siteD = -1;//D位置处的敌人编号，-1表示此位置没有敌人
    public int index;//编号
    public String name;//名称
    public Goods[] itemList;

    public EnemyTroop() {
    }


}
