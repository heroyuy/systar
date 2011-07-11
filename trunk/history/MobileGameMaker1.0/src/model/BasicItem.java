package model;

import java.awt.Image;

/*
 * 技能类、物品类、装备类的父类
 */
public class BasicItem {

	private int index = 0;// 编号

	private String name = null;// 名称

	private int kind = 0;

	private String imgName = null;// 图标名称

	private Image img = null;// 图标

	private int lev = 0;// 使用些项目需要的等级

	/*
	 * 物品属性是指此物品能给使用者附加多少属性或对敌人造成多少属性伤害
	 */

	private int hp = 0;// 血量

	private int sp = 0;// 魔法值

	private int maxhp = 0;// 最大血量

	private int maxsp = 0;// 最大魔法值

	private int stre = 0;// 力量

	private int agil = 0;// 敏捷

	private int inte = 0;// 智力

	private int atk = 0;// 物理攻击

	private int def = 0;// 物理防御

	private int matk = 0;// 魔法攻击

	private int mdef = 0;// 魔法防御

	private int aspeed = 0;// 攻击速度

	private int mspeed = 0;// 移动速度

	private int hit = 0;// 命中率

	private int flee = 0;// 闪避率

	private String remark = null;

	public int getAgil() {
		return agil;
	}

	public int getAspeed() {
		return aspeed;
	}

	public int getAtk() {
		return atk;
	}

	public int getDef() {
		return def;
	}

	public int getFlee() {
		return flee;
	}

	public int getHit() {
		return hit;
	}

	public int getHp() {
		return hp;
	}

	public Image getImg() {
		return img;
	}

	public String getImgName() {
		return imgName;
	}

	public int getIndex() {
		return index;
	}

	public int getInte() {
		return inte;
	}

	public int getKind() {
		return kind;
	}

	public int getLev() {
		return lev;
	}

	public int getMatk() {
		return matk;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public int getMaxsp() {
		return maxsp;
	}

	public int getMdef() {
		return mdef;
	}

	public int getMspeed() {
		return mspeed;
	}

	public String getName() {
		return name;
	}

	public String getRemark() {
		return remark;
	}

	public int getSp() {
		return sp;
	}

	public int getStre() {
		return stre;
	}

	public void setAgil(int agil) {
		this.agil = agil;
	}

	public void setAspeed(int aspeed) {
		this.aspeed = aspeed;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setFlee(int flee) {
		this.flee = flee;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setInte(int inte) {
		this.inte = inte;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public void setMatk(int matk) {
		this.matk = matk;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public void setMaxsp(int maxsp) {
		this.maxsp = maxsp;
	}

	public void setMdef(int mdef) {
		this.mdef = mdef;
	}

	public void setMspeed(int mspeed) {
		this.mspeed = mspeed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public void setStre(int stre) {
		this.stre = stre;
	}

}
