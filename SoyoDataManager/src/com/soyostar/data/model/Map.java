package com.soyostar.data.model;

import java.util.ArrayList;

/**
 * 地图
 */
public class Map extends Model {

    public String name;//名称
    public int imageSetNum = -1;
    public ArrayList<ImageSet> imageSets = null;
    public String musicName;
    public int rowNum;//行数
    public int colNum;//列数
    public int cellWidth;//单元格宽度
    public int cellHeight;//单元格高度
    public int layerNum;//图层数
    public ArrayList<Layer> layers = null;
    public boolean[][] way;//通行度
    public byte[][] scriptType;//脚本类型
    public int scriptNum;//脚本数
    public ArrayList<Script> scripts = null;//脚本
}
