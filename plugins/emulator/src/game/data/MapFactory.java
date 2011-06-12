package game.data;

import emulator.ui.EmulatorImage;
import game.impl.model.Npc;
import game.impl.model.Map;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class MapFactory {

    private int index = -1;
    private String name;                     //地图名称
    private int imageSetNum = -1;
    private ImageSet[] imageSets = null;
    private String musicName;                //音乐名称
    private int rowNum;                      //行数
    private int colNum;                      //列数
    private int cellWidth;                   //单元格宽度
    private int cellHeight;                  //单元格高度
    private int layerNum = -1;
    private Layer[] layers = null;
    private boolean[][] ways = null;
    private int npcNum = -1;
    private Npc[] npcs = null;

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
            imageSets = new ImageSet[imageSetNum];
            for (int i = 0; i < imageSets.length; i++) {
                imageSets[i] = new ImageSet();
                imageSets[i].id = dis.readInt();
                imageSets[i].path = dis.readUTF();
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
            npcNum = dis.readInt();
            npcs = new Npc[npcNum];
            for (int i = 0; i < npcs.length; i++) {
                npcs[i] = new Npc();
                npcs[i].setIndex(dis.readInt());
                npcs[i].row = dis.readInt();
                npcs[i].col = dis.readInt();
                npcs[i].setChartlet(dis.readUTF());
                npcs[i].face = dis.readByte();
                npcs[i].moveType = dis.readByte();
                npcs[i].moveSpeed = dis.readInt();
                npcs[i].transparent = dis.readBoolean();
                npcs[i].scriptType = dis.readByte();
                npcs[i].scriptNum = dis.readInt();
                npcs[i].scripts = new String[npcs[i].scriptNum];
                for (int j = 0; j < npcs[i].scripts.length; j++) {
                    npcs[i].scripts[j] = dis.readUTF();
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
        for (Npc npc : npcs) {
            map.npcList.put(npc.getIndex(), npc);
        }
        map.background = createBackground();
        map.foreground = createForeground();
        return map;
    }

    private EmulatorImage createBackground() {
        EmulatorImage eimg = EmulatorImage.createImage(this.colNum * this.cellWidth, this.rowNum * this.cellHeight);
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    private EmulatorImage createForeground() {
//        throw new UnsupportedOperationException("Not yet implemented");
         return null;
    }

    private class ImageSet {

        public int id = -1;
        public String path = null;
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
