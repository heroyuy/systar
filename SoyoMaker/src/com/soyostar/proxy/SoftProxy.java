/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.proxy;

import com.soyostar.MainFrame;
import com.soyostar.brush.AbBrush;
import com.soyostar.brush.CustomBrush;
import com.soyostar.brush.ShapeBrush;
import com.soyostar.model.map.Layer;
import com.soyostar.model.map.Map;
import com.soyostar.model.map.Tile;
import com.soyostar.model.map.TileSet;
import com.soyostar.project.Project;
import java.awt.Rectangle;

/**
 *
 * @author 全局数据
 */
public class SoftProxy {

    private static SoftProxy data;
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
    private Project project;

    /**
     * 
     * @return
     */
    public Project getCurProject() {
        return project;
    }

    /**
     * 
     * @param p
     */
    public void setCurProject(Project p) {
        this.project = p;
    }

    private SoftProxy() {
    }

    /**
     * 
     * @return
     */
    public synchronized static SoftProxy getInstance() {
        if (data == null) {
            data = new SoftProxy();
        }
        return data;
    }
    /**
     *
     */
    private int currentMapIndex = -1;
    /**
     *
     */
    private int currentLayerIndex = -1;

    /**
     * 
     * @return
     */
    public int getCurrentLayerIndex() {
        return currentLayerIndex;
    }

    /**
     * 
     * @param currentLayerIndex
     */
    public void setCurrentLayerIndex(int currentLayerIndex) {
        this.currentLayerIndex = currentLayerIndex;
        mf.layerTable.getSelectionModel().setSelectionInterval(currentLayerIndex, currentLayerIndex);
        mf.layerTable.updateUI();
    }

    /**
     * 
     * @return
     */
    public int getCurrentMapIndex() {
        return currentMapIndex;
    }

    /**
     * 
     * @param currentMapIndex
     */
    public void setCurrentMapIndex(int currentMapIndex) {
        this.currentMapIndex = currentMapIndex;
    }
    private MainFrame mf;
    /**
     *
     */
//    private AbBrush currentBrush;
    private Tile currentTile;

    /**
     *
     */
    public void resetBrush() {
        //FIXME: this is an in-elegant hack, but it gets the user out
        //       of custom brush mode
        //(reset the brush if necessary)
        if (getCurrentMap().getMapRender().getBrush() instanceof CustomBrush) {
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
            if (getCurrentMap().getMapRender().getBrush() instanceof ShapeBrush) {

                ((ShapeBrush) getCurrentMap().getMapRender().getBrush()).setTile(tile);
            }
        }
    }

    /**
     * 
     * @return
     */
    public Tile getCurrentTile() {
        return currentTile;
    }

    /**
     * 
     * @param mf
     */
    public void setMainFrame(MainFrame mf) {
        this.mf = mf;
    }

    /**
     * 
     * @return
     */
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
    }

    /**
     *
     * @param tileSet
     * @return
     */
    public boolean addTileSet(TileSet tileSet) {
        if (getCurrentMap() != null) {
            int id = getCurrentMap().getMaxTileSetIndex() + 1;
            tileSet.setIndex(id);
            getCurrentMap().addTileset(tileSet);
            mf.tileSetTabbedPane.setMap(getCurrentMap());
            printMapTileSet();
        } else {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return
     */
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

    /**
     * 
     */
    public void printMapLayer() {
        for (int i = 0, n = getCurrentMap().getLayerArrayList().size(); i < n; i++) {
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

    /**
     * 
     */
    public void printMap() {
//        for (int i = 0; i < Project.getInstance().getMapCounts(); i++) {
//            System.out.println(Project.getInstance().getMap(i));
//        }
//        for (int i = 0; i < DataManager.getInstance().size(Model.MAP); i++) {
//            System.out.println("ID:" + DataManager.getInstance().getModels(Model.MAP)[i]
//                    + " Map:" + DataManager.getInstance().getModels(Model.MAP)[i].toString());
//        }
//        for (int i = DataManager.getInstance().getMaxIndex(Model.MAP); i >= 0; i--) {
//            if (DataManager.getInstance().getModel(Model.MAP, i) != null) {
//                System.out.println("ID:" + DataManager.getInstance().getModel(Model.MAP, i).getIndex()
//                        + " Map:" + DataManager.getInstance().getModel(Model.MAP, i).toString());
//            }
//        }
    }

    /**
     * 
     * @param map
     */
    public void setCurrentMap(Map map) {
        if (map == null) {
            currentMapIndex = -1;
        } else {
            currentMapIndex = map.getIndex();
        }
        ShapeBrush sb = new ShapeBrush();
        sb.makeQuadBrush(new Rectangle(0, 0, 1, 1));
        setBrush(sb);
        mf.tileSetTabbedPane.setMap(map);
    }

    /**
     * 
     * @return
     */
    public Map getCurrentMap() {
        if (currentMapIndex == -1) {
            return null;
        }
        return project.getMap(currentMapIndex);
    }

    /**
     * 
     */
    public void resetCurrentMapIndex() {
        currentMapIndex = -1;
    }

    /**
     * 
     */
    public void resetCurrentLayerIndex() {
        currentLayerIndex = -1;
    }

    /**
     * 
     * @param layer
     */
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
    }

    /**
     * 
     * @return
     */
    public Layer getCurrentLayer() {
        if (getCurrentMap() == null) {
            System.out.println("当前地图为空！");
            return null;
        }
        return getCurrentMap().getLayerArrayList().get(currentLayerIndex);
    }
}
