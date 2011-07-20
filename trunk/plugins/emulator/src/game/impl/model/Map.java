package game.impl.model;

import com.soyostar.app.Image;
import engine.GameEngine;
import game.AbModel;
import game.RpgGame;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class Map extends AbModel {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = null;
    //以下为游戏运行时需要直接用到的数据
    public String name;                     //地图名称
    public String musicName;                //音乐名称
    public int rowNum;                      //行数
    public int colNum;                      //列数
    public int cellWidth;                   //单元格宽度
    public int cellHeight;                  //单元格高度
    public boolean[][] ways = null;
    public HashMap<Integer, Npc> npcList = new HashMap<Integer, Npc>();//NPC列表
    public Image background = null;//背景，精灵之下
    public Image foreground = null;//前景，精灵之上

    @Override
    public void update() {
        if (gd == null) {
            gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
        }
        //更新所有NPC
        for (Npc npc : npcList.values()) {
            npc.update();
        }
    }

    public boolean isAccessible(int row, int col) {
        if (gd == null) {
            gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
        }
        System.out.println("row:"+row+" col:"+col+" rowNum:"+rowNum+" colNum:"+colNum);
        boolean state = row >= 0 && row < this.rowNum && col >= 0 && col < this.colNum;
        System.out.println("state1:" + state);
        if (state) {
            state = ways[row][col];
            System.out.println("state2:" + state);
        }
        if (state && gd.player.row == row && gd.player.col == col) {
            state = false;
            System.out.println("state3:" + state);
        }
        if (state) {
            for (Npc npc : npcList.values()) {
                if (npc.row == row && npc.col == col) {
                    state = false;
                    System.out.println("state4:" + state);
                    break;
                }
            }
        }
         System.out.println("state:" + state);
        return state;
    }
}
