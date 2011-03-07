package model;

public class Role {

    private String name = null;//名称
    private int index = 0;// 编号
    private int hp = 0;// 血量
    private int sp = 0;// 魔法值
    private int maxhp = 0;// 最大血量
    private int maxsp = 0;// 最大魔法值
    private int stre = 0;// 力量
    private int agil = 0;// 敏捷
    private int inte = 0;// 智力
    private int lev = 0;// 等级
    private String cImgName = null;// 行走图
    private String bImgName = null;//战斗图
    private int money = 0;// 金钱
    private int atk = 0;// 物理攻击
    private int def = 0;// 物理防御
    private int matk = 0;// 魔法攻击
    private int mdef = 0;// 魔法防御
    private int aspeed = 0;// 攻击速度
    private int hit = 0;// 命中率
    private int flee = 0;// 闪避率
    private int exp = 0;// 经验值

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBImgName() {
        return bImgName;
    }

    public void setBImgName(String imgName) {
        bImgName = imgName;
    }

    public String getCImgName() {
        return cImgName;
    }

    public void setCImgName(String imgName) {
        cImgName = imgName;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getFlee() {
        return flee;
    }

    public void setFlee(int flee) {
        this.flee = flee;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getInte() {
        return inte;
    }

    public void setInte(int inte) {
        this.inte = inte;
    }

    public int getLev() {
        return lev;
    }

    public void setLev(int lev) {
        this.lev = lev;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
    }

    public int getMaxsp() {
        return maxsp;
    }

    public void setMaxsp(int maxsp) {
        this.maxsp = maxsp;
    }

    public int getMdef() {
        return mdef;
    }

    public void setMdef(int mdef) {
        this.mdef = mdef;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getStre() {
        return stre;
    }

    public void setStre(int stre) {
        this.stre = stre;
    }

    public int getAgil() {
        return agil;
    }

    public void setAgil(int agil) {
        this.agil = agil;
    }

    public int getAspeed() {
        return aspeed;
    }

    public void setAspeed(int aspeed) {
        this.aspeed = aspeed;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
