package engine.script;

/**
 *
 * 数据处理接口
 */
public interface IDataHandler {

    /****************************************/
    // $VAR[0]
    public int getVar(int index);

    public void setVar(int index, int value);

    /****************************************/
    // $SWITCH[0]
    public boolean getSwitch(int index);

    public void setSwitch(int index, boolean value);

    /****************************************/
    // $EXP
    public int getExp();

    public void setExp(int value);

    /****************************************/
    // $MONEY
    public int getMoney();

    public void setMoney(int value);

    /****************************************/
    // $LEV
    public int getLevel();

    public void setLevel(int value);

    /****************************************/
    // $MAXHP
    public int getMaxHp();

    public void setMaxHp(int value);

    /****************************************/
    // $HP
    public int getHp();

    public void setHp(int value);

    /****************************************/
    // $MAPSP
    public int getMaxSp();

    public void setMaxSp(int value);

    /****************************************/
    // $SP
    public int getSp();

    public void setSp(int value);

    /****************************************/
    // $STRE
    public int getStre();

    public void setStre(int value);

    /****************************************/
    // $AGIL
    public int getAgil();

    public void setAgil(int value);

    /****************************************/
    // $INTE
    public int getInte();

    public void setInte(int value);

    /****************************************/
    // $ITEM[0]
    public int getItemNum(int index);

    public void setItemNum(int index, int value);

    /****************************************/
    // $EQUIP[0]
    public int getEquipNum(int index);

    public void setEquipNum(int index, int value);

    /****************************************/
    // $SKILL[0]
    public boolean getSkillStatus(int index);

    public void setSkillStatus(int index, boolean value);

    /****************************************/
}
