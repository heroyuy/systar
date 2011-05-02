package game.impl;

import engine.GameEngine;
import engine.script.IDataHandler;
import game.RpgGame;
import game.impl.model.Bag;
import game.impl.model.GameData;

/**
 *
 * 数据处理器
 */
public class DataHandler implements IDataHandler {

    private GameData gd = (GameData) ((RpgGame) GameEngine.getInstance().getGame()).getModel(0);

    public int getVar(int index) {
        return gd.vars[index];
    }

    public void setVar(int index, int value) {
        gd.vars[index] = value;
    }

    public boolean getSwitch(int index) {
        return gd.switchs[index];
    }

    public void setSwitch(int index, boolean value) {
        gd.switchs[index] = value;
    }

    public int getExp() {
        return gd.player.exp;
    }
    //bug已修正 不应该单纯修改经验值，增加经验值时，一旦经验值高于升级需要经验，则升级 2.26

    public void setExp(int value) {
//        gd.player.exp = value;
        gd.player.addExp(value - gd.player.exp);
    }

    public int getMoney() {
        return gd.player.money;
    }

    public void setMoney(int value) {
        gd.player.money = value;
    }

    public int getLevel() {
        return gd.player.lev;
    }

    public void setLevel(int value) {
        gd.player.lev = value;
        gd.player.updateProperties();
    }

    public int getMaxHp() {
        return gd.player.maxHp;
    }

    public void setMaxHp(int value) {
        gd.player.maxHp = value;
    }

    public int getHp() {
        return gd.player.hp;
    }

    public void setHp(int value) {
        gd.player.hp = value;
    }

    public int getMaxSp() {
        return gd.player.maxSp;
    }

    public void setMaxSp(int value) {
        gd.player.maxSp = value;
    }

    public int getSp() {
        return gd.player.sp;
    }

    public void setSp(int value) {
        gd.player.sp = value;
    }

    public int getStre() {
        return gd.player.stre;
    }

    public void setStre(int value) {
        gd.player.stre = value;
    }

    public int getAgil() {
        return gd.player.agil;
    }

    public void setAgil(int value) {
        gd.player.agil = value;
        gd.player.maxHp = gd.gameObjectManager.getPlayer().hp + gd.player.stre * 20;//初始值为
    }

    public int getInte() {
        return gd.player.inte;
    }

    public void setInte(int value) {
        gd.player.inte = value;
        gd.player.maxSp = gd.gameObjectManager.getPlayer().sp + gd.player.inte * 15;//初始值为
    }

    public int getItemNum(int index) {
        return gd.player.bag.getNum(Bag.ITEM, index);
    }

    public void setItemNum(int index, int value) {
        int num = gd.player.bag.getNum(Bag.ITEM, index);
        if (num > value) {
            //减少
            gd.player.bag.del(Bag.ITEM, index, num - value);
        } else if (num < value) {
            //增加
            gd.player.bag.add(Bag.ITEM, index, value - num);
        }
    }

    public int getEquipNum(int index) {
        return gd.player.bag.getNum(Bag.EQUIP, index);
    }

    public void setEquipNum(int index, int value) {
        int num = gd.player.bag.getNum(Bag.EQUIP, index);
        if (num > value) {
            //减少
            gd.player.bag.del(Bag.EQUIP, index, num - value);
        } else if (num < value) {
            //增加
            gd.player.bag.add(Bag.EQUIP, index, value - num);
        }
    }

    public boolean getSkillStatus(int index) {
        return gd.player.bag.has(Bag.SKILL, index);
    }

    public void setSkillStatus(int index, boolean value) {
        if (value) {
            //添加技能
            gd.player.bag.add(Bag.SKILL, index, 1);
        } else {
            //删除技能
            gd.player.bag.del(Bag.SKILL, index, 1);
        }
    }
}
