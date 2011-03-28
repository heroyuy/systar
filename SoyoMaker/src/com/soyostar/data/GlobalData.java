/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.data;

import com.soyostar.MainFrame;
import com.soyostar.brush.AbBrush;
import com.soyostar.brush.CustomBrush;
import com.soyostar.brush.ShapeBrush;
import com.soyostar.model.map.Layer;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.TileLayer;
import com.soyostar.model.map.MapSet;
import com.soyostar.model.map.SelectionLayer;
import com.soyostar.model.map.Tile;
import com.soyostar.model.map.TileSet;
import com.soyostar.plugin.PluginLoader;
import java.awt.Rectangle;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author 全局数据
 */
public class GlobalData {

    private static GlobalData data;
    /**
     *
     */
    public int currentPsType = 0;//当前操作类型
    /**
     *
     */
    public static final int PS_PEN = 0;
    /**
     *
     */
    public static final int PS_ERASER = 1;
    /**
     *
     */
    public static final int PS_CHOOSE = 2;
    /**
     *
     */
    public static final int PS_FILL = 3;
//    /**
//     *
//     */
//    public static final int PS_COLIIDE = 4;
//    /**
//     *
//     */
//    public static final int PS_EVENT = 5;

    private GlobalData() {
    }

    public synchronized static GlobalData getInstance() {
        if (data == null) {
            data = new GlobalData();
        }
        return data;
    }
    /**
     *
     */
    public MapSet mapSetBase = new MapSet();//所有的地图
    /**
     *
     */
    private int currentMapIndex = -1;
    /**
     *
     */
    private int currentLayerIndex = -1;

    public int getCurrentLayerIndex() {
        return currentLayerIndex;
    }

    public void setCurrentLayerIndex(int currentLayerIndex) {
        this.currentLayerIndex = currentLayerIndex;
        mf.layerTable.getSelectionModel().setSelectionInterval(currentLayerIndex, currentLayerIndex);
        mf.layerTable.updateUI();
    }

    public int getCurrentMapIndex() {
        return currentMapIndex;
    }

    public void setCurrentMapIndex(int currentMapIndex) {
        this.currentMapIndex = currentMapIndex;
    }
    private MainFrame mf;
    /**
     *
     */
    private AbBrush currentBrush;
    private Tile currentTile;

    /**
     *
     */
    public void resetBrush() {
        //FIXME: this is an in-elegant hack, but it gets the user out
        //       of custom brush mode
        //(reset the brush if necessary)
        if (currentBrush instanceof CustomBrush) {
            ShapeBrush sb = new ShapeBrush();
            sb.makeQuadBrush(new Rectangle(0, 0, 1, 1));
//            sb.makeCircleBrush(2.0);
            sb.setTile(currentTile);
            setBrush(sb);
        }
    }

    /**
     * Changes the currently selected tile.
     *
     * @param tile the new tile to be selected
     */
    public void setCurrentTile(Tile tile) {
        resetBrush();

        if (currentTile != tile) {
            currentTile = tile;
            if (currentBrush instanceof ShapeBrush) {
                ((ShapeBrush) currentBrush).setTile(tile);
            }
        }
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setMainFrame(MainFrame mf) {
        this.mf = mf;
    }

    public MainFrame getMainFrame() {
        return mf;
    }

    /**
     *
     * @param brush
     */
    public void setBrush(AbBrush brush) {
        // Make sure a possible current highlight gets erased from screen
        if (getCurrentMap() == null) {
            return;
        }
        Rectangle redraw = getCurrentMap().getMapRender().getCursorSelectionLayer().getBounds();
        getCurrentMap().getMapRender().repaintRegion(redraw);
        Rectangle brushRedraw = brush.getBounds();
        getCurrentMap().getMapRender().getCursorSelectionLayer().resize(brushRedraw.width, brushRedraw.height, 0, 0);
        getCurrentMap().getMapRender().getCursorSelectionLayer().selectRegion(brush.getShape());
        getCurrentMap().getMapRender().setBrush(brush);
        currentBrush = brush;
    }

    public AbBrush getBrush() {
        return currentBrush;
    }

    /**
     *
     * @param tileSet
     * @return
     */
    public boolean addTileSet(TileSet tileSet) {
        if (getCurrentMap() != null) {
            getCurrentMap().addTileset(tileSet);
            mf.tileSetTabbedPane.setMap(getCurrentMap());
            printMapTileSet();
        } else {
            return false;
        }
        return true;
    }

    public boolean addLayer() {
        if (getCurrentMap() != null) {
            Layer layer = getCurrentMap().addLayer();
            layer.addLayerChangeListener(mf);
            setCurrentLayer(layer);
        } else {
            return false;
        }
        return true;
    }

    public void printMapLayer() {
        for (int i = 0, n = getCurrentMap().getLayerArrayList().size(); i < n; i++) {
//            if (getCurrentMap().getLayerArrayList().get(i) instanceof MapLayer) {
//                System.out.println("printMapLayer");
//            }
            System.out.println(getCurrentMap().getLayerArrayList().get(i).toString());
        }
    }

    /**
     *
     */
    public void printMapTileSet() {
        for (int i = 0, n = getCurrentMap().getTileSets().size(); i < n; i++) {
            System.out.println(getCurrentMap().getTileSets().get(i).toString());
        }
    }

    public boolean addMap(Map map) {

        mapSetBase.addMap(map);
        setCurrentMap(map);
        printMap();
        return true;
    }

    /**
     * 
     */
    public void printMap() {
        for (int i = 0, n = mapSetBase.size(); i < n; i++) {
            System.out.println(mapSetBase.getMap(i).toString());
        }
    }

    public void setCurrentMap(Map map) {
        if (map == null) {
            currentMapIndex = -1;
        } else {
            currentMapIndex = mapSetBase.indexOf(map);
        }
//        System.out.println("currentMapIndex:" + currentMapIndex);
        ShapeBrush sb = new ShapeBrush();
        sb.makeQuadBrush(new Rectangle(0, 0, 1, 1));
        setBrush(sb);
        mf.tileSetTabbedPane.setMap(map);
    }

    public Map getCurrentMap() {
        if (currentMapIndex == -1) {
//            System.out.println("currentMapIndex:-1");
            return null;
        }
        return mapSetBase.getMap(currentMapIndex);
    }

    public void resetCurrentMapIndex() {
        currentMapIndex = -1;
    }

    public void resetCurrentLayerIndex() {
        currentLayerIndex = -1;
    }

    public void setCurrentLayer(Layer layer) {

        if (layer == null) {
            currentLayerIndex = -1;
        } else {
            if (getCurrentMap() == null) {
                System.out.println("当前地图为空！");
                return;
            }
            currentLayerIndex = getCurrentMap().indexOfLayer(layer);
        }


        mf.layerTable.getSelectionModel().setSelectionInterval(currentLayerIndex, currentLayerIndex);
//        System.out.println("currentLayerIndex:" + currentLayerIndex);
//        printMapLayer();
    }

    public Layer getCurrentLayer() {
        if (getCurrentMap() == null) {
            System.out.println("当前地图为空！");
            return null;
        }
        return getCurrentMap().getLayerArrayList().get(currentLayerIndex);
    }
}
