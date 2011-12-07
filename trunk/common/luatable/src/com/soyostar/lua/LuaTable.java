package com.soyostar.lua;

import java.util.LinkedList;
import java.util.List;

import com.soyostar.lua.util.LuaFileUtil;

/**
 * Lua表
 * 
 * @author wp_g4
 * 
 */
public class LuaTable {

	private List<LuaNode> nodeList = new LinkedList<LuaNode>();// 结点列表

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(byte node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, byte node) {
		nodeList.add(new LuaNode(key, node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(byte[] nodes) {
		for (byte node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(boolean node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, boolean node) {
		nodeList.add(new LuaNode(key, node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(boolean[] nodes) {
		for (boolean node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(short node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, short node) {
		nodeList.add(new LuaNode(key, node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(short[] nodes) {
		for (short node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(int node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, int Node) {
		nodeList.add(new LuaNode(key, Node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(int[] nodes) {
		for (int node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(long node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, long node) {
		nodeList.add(new LuaNode(key, node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(long[] nodes) {
		for (long node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(float node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, float node) {
		nodeList.add(new LuaNode(key, node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(float[] nodes) {
		for (float node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(double node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, double node) {
		nodeList.add(new LuaNode(key, node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(double[] nodes) {
		for (double node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(String node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, String node) {
		nodeList.add(new LuaNode(key, node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(String[] nodes) {
		for (String node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	/**
	 * 添加结点
	 * 
	 * @param node
	 *            结点
	 */
	public void addNode(LuaTable node) {
		nodeList.add(new LuaNode(node));
	}

	/**
	 * 添加结点
	 * 
	 * @param key
	 *            健
	 * @param node
	 *            结点
	 */
	public void addNode(String key, LuaTable node) {
		nodeList.add(new LuaNode(key, node));
	}

	/**
	 * 添加结点
	 * 
	 * @param nodes
	 *            结点数组
	 */
	public void addNode(LuaTable[] nodes) {
		for (LuaTable node : nodes) {
			nodeList.add(new LuaNode(node));
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		// (1)、拼接自身
		sb.append("{}");
		// (2)、拼接子对象
		StringBuffer NodeSb = new StringBuffer();
		for (int i = 0; i < nodeList.size(); i++) {
			LuaNode Node = nodeList.get(i);
			// a 清空
			NodeSb.setLength(0);
			// b 内容
			NodeSb.append(Node);
			// c 分隔符
			if (i != nodeList.size() - 1) {
				NodeSb.append(",");
			}
			sb.insert(sb.length() - 1, NodeSb);
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LuaTable lt = new LuaTable();
		lt.addNode(1);
		lt.addNode("2");
		LuaTable ltId = new LuaTable();
		ltId.addNode(101.001);
		ltId.addNode("playerId");
		lt.addNode("id", ltId);
		lt.addNode(false);
		System.out.println(lt);
		LuaFileUtil.writeToFile(lt, "res/sth.gat");
	}

}
