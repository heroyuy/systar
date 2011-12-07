package com.soyostar.lua;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LuaTable {

	private List<LuaNode> childList = new LinkedList<LuaNode>();

	public void addChild(byte child) {
		childList.add(new LuaNode(child));
	}
	
	public void addChild(String key,byte child) {
		childList.add(new LuaNode(key,child));
	}

	public void addChild(byte[] childs) {
		for (byte child : childs) {
			childList.add(new LuaNode(child));
		}
	}

	public void addChild(boolean child) {
		childList.add(new LuaNode(child));
	}
	
	public void addChild(String key,boolean child) {
		childList.add(new LuaNode(key,child));
	}

	public void addChild(boolean[] childs) {
		for (boolean child : childs) {
			childList.add(new LuaNode(child));
		}
	}

	public void addChild(short child) {
		childList.add(new LuaNode(child));
	}
	
	public void addChild(String key,short child) {
		childList.add(new LuaNode(key,child));
	}

	public void addChild(short[] childs) {
		for (short child : childs) {
			childList.add(new LuaNode(child));
		}
	}

	public void addChild(int child) {
		childList.add(new LuaNode(child));
	}

	public void addChild(String key,int child) {
		childList.add(new LuaNode(key,child));
	}
	
	public void addChild(int[] childs) {
		for (int child : childs) {
			childList.add(new LuaNode(child));
		}
	}

	public void addChild(long child) {
		childList.add(new LuaNode(child));
	}

	public void addChild(String key,long child) {
		childList.add(new LuaNode(key,child));
	}
	
	public void addChild(long[] childs) {
		for (long child : childs) {
			childList.add(new LuaNode(child));
		}
	}

	public void addChild(float child) {
		childList.add(new LuaNode(child));
	}
	
	public void addChild(String key,float child) {
		childList.add(new LuaNode(key,child));
	}

	public void addChild(float[] childs) {
		for (float child : childs) {
			childList.add(new LuaNode(child));
		}
	}

	public void addChild(double child) {
		childList.add(new LuaNode(child));
	}
	
	public void addChild(String key,double child) {
		childList.add(new LuaNode(key,child));
	}

	public void addChild(double[] childs) {
		for (double child : childs) {
			childList.add(new LuaNode(child));
		}
	}

	public void addChild(String child) {
		childList.add(new LuaNode(child));
	}

	public void addChild(String key,String child) {
		childList.add(new LuaNode(key,child));
	}
	
	public void addChild(String[] childs) {
		for (String child : childs) {
			childList.add(new LuaNode(child));
		}
	}
	
	public void addChild(LuaTable child) {
		childList.add(new LuaNode(child));
	}

	public void addChild(String key,LuaTable child) {
		childList.add(new LuaNode(key,child));
	}
	
	public void addChild(LuaTable[] childs) {
		for (LuaTable child : childs) {
			childList.add(new LuaNode(child));
		}
	}




	public String toString() {
		StringBuffer sb = new StringBuffer();
		// (1)、拼接自身
		sb.append("{}");
		// (2)、拼接子对象
		StringBuffer childSb = new StringBuffer();
		for (int i = 0; i < childList.size(); i++) {
			LuaNode child = childList.get(i);
			// a 清空
			childSb.setLength(0);
			// b 内容
			childSb.append(child);
			// c 分隔符
			if (i != childList.size() - 1) {
				childSb.append(",");
			}
			sb.insert(sb.length() - 1, childSb);
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LuaTable lt = new LuaTable();
		lt.addChild(1);
		lt.addChild("2");
		LuaTable ltId = new LuaTable();
		ltId.addChild("id",101.001);
		ltId.addChild("playerId");
		lt.addChild(ltId);
		lt.addChild(false);
		System.out.println(lt);
	}

}
