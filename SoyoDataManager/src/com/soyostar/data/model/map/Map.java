/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data.model.map;

import com.soyostar.data.model.Model;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Map extends Model {

    public String name;                     //地图名称
    public String musicName;                //音乐名称
    public int rowNum;                      //行数
    public int colNum;                      //列数
    public int cellWidth;                   //单元格宽度
    public int cellHeight;                  //单元格高度
    public ArrayList<Layer> layers = null;
    public ArrayList<TileSet> tilesets = null;
}
