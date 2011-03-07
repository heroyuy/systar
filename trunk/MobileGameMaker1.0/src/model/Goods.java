package model;

//物品类
public class Goods extends BasicItem {

	public static final int RECOVER = 2;// 辅助自身

	public static final int SATK = 0;// 单体攻击

	public static final int AATK = 1;// 全体攻击

	public static final int OTHER = 3;// 其它

	private int num = 0;// 物品数量

	private int price = 0;// 物品价格


	private int exp = 0;// 使用物品得到的经验值

	public int getExp() {
		return exp;
	}

	public int getNum() {
		return num;
	}

	public int getPrice() {
		return price;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
