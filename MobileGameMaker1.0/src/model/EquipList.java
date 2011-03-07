package model;

import java.util.Vector;


//玩家和敌人身上保存一张物品表，用于保存角色、敌人当前所拥有的物品
public class EquipList extends Vector {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void addEquip(Equip equip) {
			addElement(equip);
	}

	public void delEquip(int index) {
		//删除技能列表中指定技能编号的技能
					removeElementAt(index);
	}

	public Equip getEquip(int index) {
		//返回技能列表中指定技能编号的技能
		Equip equip = null;
		if (hasEquip(index)) {
			for (int i = 0; i < size(); i++) {
				equip = (Equip) elementAt(i);
				if (equip.getIndex()==index)
					break;
			}
		}
		return equip;

	}

	public boolean hasEquip(int index) {
		//检查是否有指定编号的技能
		boolean judge = false;
		Equip equip = null;
		for (int i = 0; i < size(); i++) {
			equip = (Equip) elementAt(i);
			if (equip.getIndex()==index) {
				judge = true;
				break;
			}
		}
		return judge;
	}

	public Equip equipAt(int num){
		//获取技能表中指定位置的技能
		return (Equip)elementAt(num);
	}

	public int getMaxIndex(){
		int max=0;
		Equip equip=null;
		for(int i=0;i<size();i++){
			equip=equipAt(i);
			if(equip.getIndex()>max)max=equip.getIndex();
		}
		return max;
	}
}
