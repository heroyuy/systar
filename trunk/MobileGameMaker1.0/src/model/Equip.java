package model;

//装备类
public class Equip extends BasicItem {

	public static final int HELM = 0;// 头盔

	public static final int WEAPON = 2;// 武器

	public static final int SHIELD = 3;// 护盾

	public static final int ARMOUR = 4;// 铠甲

	public static final int TRINKET = 1;// 饰品

	public static final int CALIGA = 5;// 战靴

	private int num = 0;// 装备数量

	private int price = 0;// 装备价格

	public int getNum() {
		return num;
	}

	public int getPrice() {
		return price;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
