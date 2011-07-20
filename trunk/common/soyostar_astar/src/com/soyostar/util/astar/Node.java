package com.soyostar.util.astar;

/**
 *
 * @author wp_g4
 */
class Node {

    public int row = 0;
    public int col = 0;
    public int fValue = 0;
    public int hValue = 0;
    public int gValue = 0;
    public Node parentNode = null;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
