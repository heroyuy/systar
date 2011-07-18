package game.data;

import com.soyostar.app.Image;
import com.soyostar.app.Painter;
import game.impl.model.Npc;
import game.impl.model.Map;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class MapFactory {

    private int index = -1;
    private String name;                     //地图名称
    private int imageSetNum = -1;
    private java.util.Map<Integer, ImageSet> imageSets = null;
    private String musicName;                //音乐名称
    private int rowNum;                      //行数
    private int colNum;                      //列数
    private int cellWidth;                   //单元格宽度
    private int cellHeight;                  //单元格高度
    private int layerNum = -1;
    private Layer[] layers = null;
    private boolean[][] ways = null;
    private int[][] npcIndexs = null;

    public void loadMapData(DataInputStream dis) {
        System.out.println("loadMapData");
        try {
            //基本属性
            System.out.println("基本属性");
            index = dis.readInt();
            name = dis.readUTF();
            musicName = dis.readUTF();
            rowNum = dis.readInt();
            colNum = dis.readInt();
            cellWidth = dis.readInt();
            cellHeight = dis.readInt();
            //图集
            System.out.println("图集");
            imageSetNum = dis.readInt();
            imageSets = new HashMap<Integer, ImageSet>();
            for (int i = 0; i < imageSetNum; i++) {
                ImageSet imageSet = new ImageSet();
                imageSet.id = dis.readInt();
                imageSet.path = dis.readUTF();
                imageSet.image = Image.createImage("res" + imageSet.path);
                imageSets.put(imageSet.id, imageSet);
            }
            //图层
            System.out.println("图层");
            layerNum = dis.readInt();
            layers = new Layer[layerNum];
            for (int i = 0; i < layers.length; i++) {
                layers[i] = new Layer();
                layers[i].deepth = dis.readInt();
                layers[i].tiles = new Tile[rowNum][colNum];
                for (int j = 0; j < rowNum; j++) {
                    for (int k = 0; k < colNum; k++) {
                        layers[i].tiles[j][k] = new Tile();
                        layers[i].tiles[j][k].imageSetId = dis.readInt();
                        if (layers[i].tiles[j][k].imageSetId == -1) {
                            layers[i].tiles[j][k].tileIndex = -1;
                        } else {
                            layers[i].tiles[j][k].tileIndex = dis.readInt();
                        }

                    }
                }
            }
            //通行度
            System.out.println("通行度");
            ways = new boolean[rowNum][colNum];
            for (int j = 0; j < rowNum; j++) {
                for (int k = 0; k < colNum; k++) {
                    ways[j][k] = dis.readBoolean();
                }
            }
            //NPC
            System.out.println("NPC");
            npcIndexs = new int[rowNum][colNum];
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < colNum; j++) {
                    npcIndexs[i][j] = dis.readInt();
                }
            }
        } catch (IOException ex) {
            System.out.println("加载Map出错");
            ex.printStackTrace();
        }
    }

    public Map getMap() {
        Map map = new Map();
        map.setIndex(index);
        map.name = name;
        map.musicName = musicName;
        map.colNum = colNum;
        map.rowNum = rowNum;
        map.cellWidth = cellWidth;
        map.cellHeight = cellHeight;
//        for (Npc npc : npcs) {
//            map.npcList.put(npc.getIndex(), npc);
//        }
        map.background = createBackground();
        map.foreground = createForeground();
        return map;
    }

    private Image createBackground() {
        Image img = Image.createImage(this.colNum * this.cellWidth, this.rowNum * this.cellHeight);
        Painter painter = img.getPainter();
        Image temp = null;
        for (Layer layer : layers) {
//            if (layer.deepth < 0) {
            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < colNum; j++) {
                    Tile tile = layer.tiles[i][j];
                    if (tile.imageSetId != -1) {
                        temp = imageSets.get(tile.imageSetId).image;
                        int tx = tile.tileIndex % (temp.getWidth()/cellWidth) * cellWidth;
                        int ty = tile.tileIndex / (temp.getWidth()/cellWidth) * cellHeight;
                        painter.drawImage(Image.copyImage(temp, tx, ty, cellWidth, cellHeight), j * cellWidth, i * cellHeight, Painter.LT);
                    }
                }
            }
//            }
        }
        return img;
    }

    private Image createForeground() {
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    private class ImageSet {

        public int id = -1;
        public String path = null;
        public Image image = null;
    }

    private class Layer {

        public int deepth = 0;
        public Tile[][] tiles = null;
    }

    private class Tile {

        public int imageSetId = -1;
        public int tileIndex = -1;
    }
}
