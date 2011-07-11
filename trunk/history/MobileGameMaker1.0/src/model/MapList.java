package model;

import java.util.Vector;

//玩家和敌人身上保存一张物品表，用于保存角色、敌人当前所拥有的物品
public class MapList extends Vector {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void addMap(Map map) {
        addElement(map);
    }

    public void setMap(Map map, int index) {
        if (this.contains(map)) {
            this.setElementAt(map, index);
        } else {
            this.add(map);
        }
    }

    public void delMap(int index) {
        //删除技能列表中指定技能编号的技能
        removeElementAt(index);
    }

    public void delAllMap() {
        this.removeAllElements();
    }

    public Map getMap(int index) {
        //
        Map map = null;
        if (hasMap(index)) {
            for (int i = 0; i < size(); i++) {
                map = (Map) elementAt(i);
                if (map.getIndex() == index) {
                    break;
                }
            }
        }
        return map;

    }

    public boolean hasMap(int index) {
        //检查是否有指定编号的技能
        boolean judge = false;
        Map map = null;
        for (int i = 0; i < size(); i++) {
            map = (Map) elementAt(i);
            if (map.getIndex() == index) {
                judge = true;
                break;
            }
        }
        return judge;
    }

    public Map mapAt(int num) {
        return (Map) elementAt(num);
    }

    public int getMaxIndex() {
        int max = 0;
        Map map = null;
        for (int i = 0; i < size(); i++) {
            map = mapAt(i);
            if (map != null) {
                if (map.getIndex() > max) {
                    max = map.getIndex();
                }
            }

        }
        return max;
    }
}
