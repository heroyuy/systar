package emulator.model;

import system.ArrayList;

/**
 *
 * 背包
 */
public class Bag {

    public static final byte ITEM = 0;
    public static final byte EQUIP = 1;
    public static final byte SKILL = 2;
    GameData gd = GameData.getGameData();
    /**
     * 背包可以放三种东西，普通物品、装备和技能
     * list[0] 普通物品
     * list[1] 装备
     * list[2] 技能
     */
    private ArrayList[] list = new ArrayList[3];

    public Bag() {
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList();
        }
    }
/**
 * 获取背包中的物品
 * @param type 物品类型 可取Bag.ITEM Bag.EQUIP Bag.SKILL
 * @param index 物品的编号
 * @return 如果背包中有此物品则返回此物品，否则返回null
 */
    public BaseItem get(byte type, int index) {
        if (list[type].has(index)) {
            return gd.gameObjectManager.getBaseItem(type, index);
        } else {
            return null;
        }
    }
/**
 * 获取背包中的物品数量
 * @param type 物品类型 可取Bag.ITEM Bag.EQUIP Bag.SKILL
 * @param index 物品的编号
 * @return 如果背包中有此物品则返回此物品，否则返回0
 */
    public int getNum(byte type, int index) {
        return gd.gameObjectManager.getBaseItem(type, index).num;
    }
/**
 * 检查背包中是否有指定类型指定编号的物品
 * @param type 物品类型 可取Bag.ITEM Bag.EQUIP Bag.SKILL
 * @param index 物品的编号
 * @return 如果背包中有此物品则返回true，否则返回false
 */
    public boolean has(byte type, int index) {
        return list[type].has(index);
    }
/**
 * 向背包中添加num个类型为type编号为index的物品
 * @param type 物品类型 可取Bag.ITEM Bag.EQUIP Bag.SKILL
 * @param index 物品的编号
 * @param num 要添加的数量
 */
    public void add(byte type, int index, int num) {
        if (!has(type, index)) {
            list[type].add(index);
        }
        gd.gameObjectManager.getBaseItem(type, index).num += num;
    }

    public void del(byte type, int index, int num) {
        if (has(type, index)) {
            gd.gameObjectManager.getBaseItem(type, index).num -= num;
            if (gd.gameObjectManager.getBaseItem(type, index).num <= 0) {
                gd.gameObjectManager.getBaseItem(type, index).num = 0;
                list[type].remove(index);
            }
        }
    }

    public int[] getList(int type){
        return list[type].toArray();
    }


}
