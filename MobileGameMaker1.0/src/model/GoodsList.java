package model;

import java.util.Vector;


//玩家和敌人身上保存一张物品表，用于保存角色、敌人当前所拥有的物品
public class GoodsList extends Vector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addItem(Goods item) {
			addElement(item);
	}

	public void delItem(int index) {
		//删除技能列表中指定技能编号的技能
					removeElementAt(index);
	}

	public Goods getItem(int index) {
		//返回技能列表中指定技能编号的技能
		Goods item = null;
		if (hasItem(index)) {
			for (int i = 0; i < size(); i++) {
				item = (Goods) elementAt(i);
				if (item.getIndex()==index)
					break;
			}
		}
		return item;

	}

	public boolean hasItem(int index) {
		//检查是否有指定编号的技能
		boolean judge = false;
		Goods item = null;
		for (int i = 0; i < size(); i++) {
			item = (Goods) elementAt(i);
			if (item.getIndex()==index) {
				judge = true;
				break;
			}
		}
		return judge;
	}
	
	public Goods itemAt(int num){
		//获取技能表中指定位置的技能
		return (Goods)elementAt(num);
	}
	
	public int getMaxIndex(){
		int max=0;
		Goods item=null;
		for(int i=0;i<size();i++){
			item=itemAt(i);
			if(item.getIndex()>max)max=item.getIndex();
		}
		return max;
	}
}
