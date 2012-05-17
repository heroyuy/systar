/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker;

import com.soyomaker.brush.AbBrush;
import com.soyomaker.brush.TileLayerBrush;
import com.soyomaker.brush.TileBrush;
import com.soyomaker.model.map.Layer;
import com.soyomaker.model.map.Map;
import com.soyomaker.model.map.Tile;
import com.soyomaker.model.map.TileSet;
import com.soyomaker.project.Project;
import com.soyomaker.render.MapRender;
import java.awt.Rectangle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author 全局数据
 */
public class AppData {

    private static AppData data;
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
    /**
     *
     */
    private Project project;
    private int currentMapIndex = -1;
    private int currentLayerIndex = -1;
    private int currentAnimationIndex = -1;
    private AppMainFrame mf;
    private Tile currentTile;
    private int cpuNums = Runtime.getRuntime().availableProcessors();
    private Executor executor = Executors.newFixedThreadPool(cpuNums);
//    private Executor executor = Executors.newCachedThreadPool();

    /**
     *
     * @return
     */
    public Executor getExecutor() {
        return executor;
    }

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

    private AppData() {
    }

    /**
     * 生成游戏
     */
    public void buildGame() {
        mf.saveGameData();
    }

    /**
     * 
     * @return
     */
    public synchronized static AppData getInstance() {
        if (data == null) {
            data = new AppData();
        }
        return data;
    }

    /**
     *
     * @return
     */
    public int getCurrentAnimationIndex() {
        return currentAnimationIndex;
    }

    /**
     *
     * @param currentAnimationIndex
     */
    public void setCurrentAnimationIndex(int currentAnimationIndex) {
        this.currentAnimationIndex = currentAnimationIndex;
    }

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

    /**
     * 
     * @param mf
     */
    public void setMainFrame(AppMainFrame mf) {
        this.mf = mf;
    }

    /**
     * 
     * @return
     */
    public AppMainFrame getMainFrame() {
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
        if (getCurrentMap().getMapRender() == null) {
            return;
        }
        MapRender mapRender = getCurrentMap().getMapRender();
        Rectangle redraw = mapRender.getCursorSelectionLayer().getBounds();
        mapRender.repaintRegion(redraw);
        Rectangle brushRedraw = brush.getShape().getBounds();
        mapRender.getCursorSelectionLayer().resize(brushRedraw.width, brushRedraw.height, 0, 0);
        mapRender.getCursorSelectionLayer().selectRegion(brush.getShape());
        mapRender.setBrush(brush);
    }

    /**
     *
     * @param tileSet
     * @return
     */
    public boolean addTileSet(TileSet tileSet) {
        if (getCurrentMap() != null) {
            //检查是否地图已经加载过此图集，通过文件名来查找
            for (int i = 0; i < getCurrentMap().getTileSets().size(); i++) {
                TileSet t = getCurrentMap().getTileSets().get(i);
                if (t.getTilebmpFile().getName().equals(tileSet.getTilebmpFile().getName())) {
                    return false;
                }
            }
            int id = getCurrentMap().getAvailableTileSetIndex();
            tileSet.setIndex(id);
            getCurrentMap().addTileset(tileSet);
            getCurProject().addTileSet(tileSet);
            mf.tileSetTabbedPane.setMap(getCurrentMap());
            return true;
        } else {
            return false;
        }
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
     * @param map
     */
    public void setCurrentMap(Map map) {
        if (map == null) {
            currentMapIndex = -1;
        } else {
            currentMapIndex = map.getIndex();
        }
        TileBrush sb = new TileBrush();
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
//                System.out.println("当前地图为空！");
//                iLogger.w("当前地图为空！");
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
//            System.out.println("当前地图为空！");
//            iLogger.w("当前地图为空！");
            return null;
        }
        return getCurrentMap().getLayerArrayList().get(currentLayerIndex);
    }
}
