/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.model;

import emulator.EmulatorImage;
import game.AbModel;
import game.impl.model.Npc;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class Map extends AbModel {

    //以下为游戏运行时需要直接用到的数据
    public String name;                     //地图名称
    public String musicName;                //音乐名称
    public int rowNum;                      //行数
    public int colNum;                      //列数
    public int cellWidth;                   //单元格宽度
    public int cellHeight;                  //单元格高度
    public HashMap<Integer, Npc> npcList = new HashMap<Integer, Npc>();//NPC列表
    public EmulatorImage background = null;//背景，精灵之下
    public EmulatorImage foreground = null;//前景，精灵之上

    @Override
    public void update() {
        //更新所有NPC
        for (Npc npc : npcList.values()) {
            npc.update();
        }
    }
}
