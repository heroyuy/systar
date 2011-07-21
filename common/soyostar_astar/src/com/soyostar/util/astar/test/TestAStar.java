package com.soyostar.util.astar.test;

import com.soyostar.util.astar.AStar;

/**
 *
 * @author wp_g4
 */
public class TestAStar {

    public static void main(String[] args) {
        boolean[][] mapData = {
            {true, false, false, true},
            {true, true, false, true},
            {true, true, false, true},
            {false, true, true, true}};
        AStar aStar = new AStar(mapData);
//        aStar.searchPath(0, 0, 0, 3);
        int[] paths = aStar.searchDirections(0, 0, 0, 3);
        for (int p : paths) {
            System.out.print(p + " ");
        }
    }
}
