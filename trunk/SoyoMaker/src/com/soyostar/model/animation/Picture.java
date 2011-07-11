/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.model.animation;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Picture {

    public String getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(String sourceImage) {
        this.sourceImage = sourceImage;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    String sourceImage;//源图片位置
    ArrayList<Tile> tiles;//切割得到的图块集
}
