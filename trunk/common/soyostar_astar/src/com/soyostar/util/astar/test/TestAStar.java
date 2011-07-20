package com.soyostar.util.astar.test;

import com.soyostar.util.astar.AStar;

/**
 *
 * @author wp_g4
 */
public class TestAStar {

    public static void main(String[] args) {
        int[][] mapData = {
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 0}};
        AStar aStar = new AStar(mapData);
        aStar.searchPath(0, 0, 0, 3);
    }
}
