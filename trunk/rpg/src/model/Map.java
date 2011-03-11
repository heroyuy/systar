package model;

import engine.script.Command;
import engine.script.Script;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * 地图
 */
public class Map {

    public int index;//编号
    public String name;//名称
    public String musicName;
    public Image image;//地图全图
    public int layerNum;//图层数
    public int rowNum;//行数
    public int colNum;//列数
    public int cellWidth;//单元格宽度
    public int cellHeight;//单元格高度
    public int scriptNum;//脚本数
    public int[][][] data;//贴图编和数据
    public int[][] way;//通行度
    public byte[][] scriptType;//脚本类型
    public Script[] script;//脚本
    public int x, y;//要绘制的地图region的x,y坐标
    private GameData gd = GameData.getGameData();

    public void setImage(Image image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int maxRow = height / cellHeight;
        int maxCol = width / cellWidth;
        System.out.println("############################################");
        System.out.println("width:" + width);
        System.out.println("height:" + height);
        System.out.println("maxRow:" + maxRow);
        System.out.println("maxCol:" + maxCol);
        //正在修改
        this.image = Image.createImage(cellWidth * colNum, cellHeight * rowNum);
        Graphics g = this.image.getGraphics();
        for (int i = 0; i < layerNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                for (int k = 0; k < colNum; k++) {
//                    System.out.println("***********************");
                    int num = data[i][j][k];
                    int row = (num - 1) / maxCol;
                    int col = (num - 1) % maxCol;
//                    System.out.println("num:" + num);
//                    System.out.println("row:" + row);
//                    System.out.println("col" + col);
                    if (num != 0) {
                        Image tempImg = Image.createImage(image, col * cellWidth, row * cellHeight, cellWidth, cellHeight, 0);
                        g.drawImage(tempImg, k * cellWidth, j * cellHeight, 0);
                    }
                }
            }
        }
//        int ww = (gd.screenWidth - cellWidth * colNum) / 2;
//        int hh = (gd.screenHeight - cellHeight * rowNum) / 2;
//        for (int i = 0; i < layerNum; i++) {
//            for (int j = 0; j < rowNum; j++) {
//                for (int k = 0; k < colNum; k++) {
////                    System.out.println("***********************");
//                    int num = data[i][j][k];
//                    int row = (num - 1) / maxCol;
//                    int col = (num - 1) % maxCol;
////                    System.out.println("num:" + num);
////                    System.out.println("row:" + row);
////                    System.out.println("col" + col);
//                    if (num != 0) {
//                        Image tempImg = Image.createImage(image, col * cellWidth, row * cellHeight, cellWidth, cellHeight, 0);
//                        g.drawImage(tempImg, cellWidth * colNum < gd.screenWidth ? (ww + k * cellWidth) : k * cellWidth, cellHeight * rowNum < gd.screenHeight ? (hh + j * cellHeight) : j * cellHeight, 0);
//                    }
//                }
//            }
//        }
    }

    /**
     * 返回指定单元格上的脚本
     * @param row 单元格行号
     * @param col 单元格列号
     * @return 指定单元格上的脚本，如果不存在则返回null
     */
    public Script getScript(int row, int col) {

        int num = script.length;
        Script tempScript = null;
        for (int i = 0; i < num; i++) {
            if (script[i].row == row && script[i].col == col) {
                tempScript = script[i];
                break;
            }
        }

        return tempScript;
    }

    public void init() {
    }

    public void resetRegion(Player player) {
//        System.out.println("Map.resetRegion");
        x = player.x - GameData.getGameData().screenWidth / 2;
        y = player.y - GameData.getGameData().screenHeight / 2;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x + GameData.getGameData().screenWidth > cellWidth * colNum) {
            x = cellWidth * colNum - GameData.getGameData().screenWidth;
        }
        if (y + GameData.getGameData().screenHeight > cellHeight * rowNum) {
            y = cellHeight * rowNum - GameData.getGameData().screenHeight;
        }
        if(image.getWidth()<=GameData.getGameData().screenWidth){
            x=0;
        }

        if(image.getHeight()<=GameData.getGameData().screenHeight){
            y=0;
        }
    }
//    public Map initMap(int index) {
//        Map map = new Map();
//        try {
//
//            InputStream is = getClass().getResourceAsStream("/data/map/map" + index + ".gat");
//            DataInputStream dis = new DataInputStream(is);
//            map.index = dis.readInt();
//            map.name = dis.readUTF();
//            String imageName = dis.readUTF();
//
//            map.musicName = dis.readUTF();
//            map.rowNum = dis.readByte();
//            System.out.println("row " + map.rowNum);
//            map.colNum = dis.readByte();
//            System.out.println("col " + map.colNum);
//            map.cellWidth = dis.readByte();
//            map.cellHeight = dis.readByte();
//            map.layerNum = dis.readByte();
//
//            map.data = new int[map.layerNum][map.rowNum][map.colNum];
//            for (int i = 0; i < map.layerNum; i++) {
//                for (int j = 0; j < map.rowNum; j++) {
//                    for (int k = 0; k < map.colNum; k++) {
//                        map.data[i][j][k] = dis.readInt();
//                    }
//                }
//            }
//            map.way = new int[map.rowNum][map.colNum];
//            for (int j = 0; j < map.rowNum; j++) {
//                for (int k = 0; k < map.colNum; k++) {
//                    map.way[j][k] = dis.readByte();
//                }
//            }
//
//            map.scriptType = new byte[map.rowNum][map.colNum];
//            for (int j = 0; j < map.rowNum; j++) {
//                for (int k = 0; k < map.colNum; k++) {
//                    map.scriptType[j][k] = dis.readByte();
//                }
//            }
//            int sum = dis.readInt();
//            map.script = new Script[sum];
//            for (int i = 0; i < sum; i++) {
//                map.script[i] = new Script();
//                map.script[i].type = dis.readByte();
//                map.script[i].row = dis.readInt();
//                map.script[i].col = dis.readInt();
//                map.script[i].command = new Command[dis.readInt()];
//                for (int j = 0; j < map.script[i].command.length; j++) {
//                    map.script[i].command[j] = new Command();
//                    map.script[i].command[j].type = dis.readByte();
//                    switch (map.script[i].command[j].type) {
//                        case Command.ENDIF:
//                        case Command.ENDWHILE:
//                        case Command.BREAK:
//                        case Command.CONTINUE:
//                        case Command.EXIT:
//                        case Command.GAMEOVER:
//                            continue;
//                        default:
//                            System.out.println("hava param");
//                            map.script[i].command[j].param = dis.readUTF();
//                            map.script[i].command[j].nextIndex = dis.readInt();
//                            break;
//                    }
//
//                }
//            }
//            map.setImage(Image.createImage("/image/tileset/" + imageName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Error er) {
//            er.printStackTrace();
//        }
//        return map;
//    }
}
