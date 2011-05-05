/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.model.map;

import game.AbModel;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Map extends AbModel {

    public String name;                     //地图名称
    public String musicName;                //音乐名称
    public int rowNum;                      //行数
    public int colNum;                      //列数
    public int cellWidth;                   //单元格宽度
    public int cellHeight;                  //单元格高度
    public ArrayList<Layer> layers = new ArrayList<Layer>();
    public ArrayList<TileSet> tilesets = new ArrayList<TileSet>();

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
