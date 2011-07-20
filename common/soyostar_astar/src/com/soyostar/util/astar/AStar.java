package com.soyostar.util.astar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author wp_g4
 */
public final class AStar {

    private Map<String, Node> nodeList = new HashMap<String, Node>();
    private Map<String, Node> openList = new HashMap<String, Node>();
    private Map<String, Node> closeList = new HashMap<String, Node>();

    /**
     * 构造一个新的A星工具实例
     */
    public AStar() {
    }

    /**
     * 构造一个新的A星工具实例
     * @param mapData A星工具的地图数据
     */
    public AStar(int[][] mapData) {
        setMapData(mapData);
    }

    /**
     * 设置A星工具的地图数据
     * @param mapData A星工具的地图数据
     */
    public void setMapData(int[][] mapData) {
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                if (mapData[i][j] == 0) {
                    //只有值为0的方格是可通行的
                    //key的格式为 [行号列号]，比如一个方格行号为2，列号为12，则key为 "212"
                    nodeList.put(i + "" + j, new Node(i, j));
                }
            }
        }
    }

    /**
     * 搜索从起点到终点的可通行路径
     * @param startRow 起点所在行号
     * @param startCol 起点所在列号
     * @param endRow 终点所在行号
     * @param endCol 终点所在列号
     * @return 起点到终点的可通行路径
     */
    public int[][] searchPath(int startRow, int startCol, int endRow, int endCol) {
        //清空open列表和colse列表
        openList.clear();
        closeList.clear();
        //所有node的 gValue,fValue归0，parentNode归null，计算hValue。
        for (Node node : nodeList.values()) {
            node.gValue = node.fValue = 0;
            node.hValue = (Math.abs(endRow - node.row)) + (Math.abs(endCol - node.col));
            node.parentNode = null;
        }
        //将起点添加到open表
        Node startNode = getNode(startRow, startCol);
        openNode(startNode);
        Node endNode = getNode(endRow, endCol);
        while (!isOpened(endNode)) {
            //如果终点已经被添加到open列表则搜索成功
            //获取open列表中的最优点，即fValue最小的点
            Node optimalNode = getOptimalNode();
            //获取最优点可直接到达的点
            List<Node> accessibleNodeList = getAccessibleNodeList(optimalNode);
            for (Node node : accessibleNodeList) {
                calculateNode(node, optimalNode);
            }
            closeNode(optimalNode);

        }
        //获取路径，从终点开始沿父结点寻找,直到起点
        Stack<Node> tempPathList = new Stack<Node>();
        Node temp = endNode;
        while (temp != null && !temp.equals(startNode)) {
            tempPathList.push(temp);
            temp = temp.parentNode;
        }
        List<Node> pathList = new ArrayList<Node>();
        while (tempPathList.size() > 0) {
            pathList.add(tempPathList.pop());
        }
        printNodeList(pathList);
        return null;
    }

    private Node getNode(int row, int col) {
        return nodeList.get(row + "" + col);
    }

    private Node getNode(String key) {
        return nodeList.get(key);
    }

    private void openNode(Node node) {
        closeList.remove(node.row + "" + node.col);
        openList.put(node.row + "" + node.col, node);
    }

    private void closeNode(Node node) {
        openList.remove(node.row + "" + node.col);
        closeList.put(node.row + "" + node.col, node);
    }

    private boolean isOpened(Node node) {
        return openList.containsKey(node.row + "" + node.col);
    }

    private boolean isClosed(Node node) {
        return closeList.containsKey(node.row + "" + node.col);
    }

    /**
     * 从open列表中获取最优点，fValue最小
     * @return 最优点，即fValue最小的点
     */
    private Node getOptimalNode() {
        Node temp = null;
        for (Node node : openList.values()) {
            if (temp == null || temp.fValue > node.fValue) {
                temp = node;
            }
        }
        return temp;
    }

    /**
     * 获取指定结点可以直接到达的结点
     * @param node 指定结点
     * @return 目标结点
     */
    private List<Node> getAccessibleNodeList(Node node) {
        List<Node> accessibleNodeList = new ArrayList<Node>();
        List<String> keyList = new ArrayList<String>();
        //上
        keyList.add((node.row - 1) + "" + node.col);
        //下
        keyList.add((node.row + 1) + "" + node.col);
        //左
        keyList.add(node.row + "" + (node.col - 1));
        //右
        keyList.add(node.row + "" + (node.col + 1));
        for (String key : keyList) {
            Node temp = getNode(key);
            if (temp != null && !isClosed(temp)) {
                //如果存在这个结点并且结点未关闭
                accessibleNodeList.add(temp);
            }
        }
        return accessibleNodeList;
    }

    /**
     * 根据最优点确定其周围的一个点的属性
     * @param node
     * @param optimalNode
     */
    private void calculateNode(Node node, Node optimalNode) {
        //假设最优点为其周围的点的父结点，计算结点的fvalue
        int tempFValue = node.hValue + optimalNode.gValue + 1;
        if (node.parentNode == null || tempFValue < node.fValue) {
            //如果此结点还没访问过或者新的fValue更小
            node.parentNode = optimalNode;
            node.gValue = optimalNode.gValue + 1;
            node.fValue = node.gValue + node.hValue;
            //将此结点加入open列表
            openNode(node);
        }
    }

    private void printNodeList(Collection<Node> nodeList) {
        System.out.println("--------------------->");
        for (Node node : nodeList) {
            System.out.println("row:" + node.row + " col:" + node.col);
        }
    }
}
