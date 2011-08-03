package game.scene.map;

import com.soyostar.app.Color;
import com.soyostar.app.LLabel;
import com.soyostar.app.LMessageBoard;
import com.soyostar.app.LTextArea;
import com.soyostar.app.Layer;
import com.soyostar.app.Painter;
import com.soyostar.app.Widget;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import game.AbController;
import game.actions.MoveAction;
import game.impl.model.Npc;
import game.impl.model.Character;
import game.impl.state.MapState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author wp_g4
 */
public class MapController extends AbController implements TouchListener {

    private Layer mapBackground = null;
    private Widget mapForeground = null;
    private Layer spriteLayer = null;
    private java.util.Map<Character, GLSprite> lSprites = null;
    private GLWave lWave = null;
    private GLDialog dialog = null;
    private LLabel fpsLabel = null;
    private LMessageBoard lmb = null;
    private int x = 0, y = 0;//当前视窗在地图上的坐标

    public MapController() {
        gd.mapState.curMap = gd.player.curMap;
        mapBackground = new Layer();
        mapBackground.setBackground(Color.GREEN);
        mapBackground.setSize(gd.mapState.curMap.colNum * gd.mapState.curMap.cellWidth, gd.mapState.curMap.rowNum * gd.mapState.curMap.cellHeight);
        mapBackground.setVisible(true);
        mapBackground.setBackgroundImage(gd.mapState.curMap.background);
        mapBackground.setTouchListener(this);

        spriteLayer = new Layer();
        spriteLayer.setSize(gd.mapState.curMap.colNum * gd.mapState.curMap.cellWidth, gd.mapState.curMap.rowNum * gd.mapState.curMap.cellHeight);
        spriteLayer.setVisible(true);

        mapForeground = new Widget();
        mapForeground.setSize(gd.mapState.curMap.colNum * gd.mapState.curMap.cellWidth, gd.mapState.curMap.rowNum * gd.mapState.curMap.cellHeight);
        mapForeground.setVisible(true);
        mapForeground.setBackgroundImage(gd.mapState.curMap.foreground);
        mapForeground.setTouchListener(this);

        gd.mapState.sprites = new ArrayList<Character>();
        gd.mapState.sprites.add(gd.player);
        for (Npc npc : gd.mapState.curMap.npcList.values()) {
            gd.mapState.sprites.add(npc);
        }
        lSprites = new HashMap<Character, GLSprite>();
        for (Character sprite : gd.mapState.sprites) {
            lSprites.put(sprite, new GLSprite(sprite));
        }
        String txt = "你深邃的眼眸 想要透漏什么密码 犹豫的嘴角 躲在严肃的背影下 压抑的空气 回绕闭塞的城堡里 谜一般的天鹅 有你说不尽的故事 孤独的身影 只有钟声陪伴 进了城堡却敲不进你的心 冷淡的表情 只剩风霜遮掩我的身躯 遮住了天地遮不住你的情 你在等待着谁 建筑了城堡 等待着天鹅的栖息 藏不住你空虚的心灵 你在眺望着谁 拥有了世界";
        dialog = new GLDialog(gd.player.headImage, txt);
        fpsLabel = new LLabel();
        fpsLabel.setSize(80, 20);
        fpsLabel.setLocation(0, 0);
        fpsLabel.setTextColor(Color.WHITE);
        fpsLabel.setTextAnchor(Painter.HV);
        fpsLabel.setVisible(true);

        lmb = new LMessageBoard();
        lmb.setSize(250, 120);
        lmb.setTextSize(15);
        lmb.setTextColor(Color.BLUE);
        lmb.setVisible(true);
    }

    public void onObtain() {
        addWidget(mapBackground);
        for (GLSprite lSprite : lSprites.values()) {
            spriteLayer.addWidget(lSprite);
        }
        mapBackground.addWidget(spriteLayer);
        mapBackground.addWidget(mapForeground);
        mapBackground.addWidget(fpsLabel);
        mapBackground.addWidget(lmb);
    }

    public void onLose() {
    }

    public void updateModel() {
        switch (gd.mapState.sceneState) {
            case MapState.STATE_MAP:
                updateScene_Map();
                break;
            case MapState.STATE_MENU:
                updateScene_Menu();
                break;
            case MapState.STATE_DIALOG:
                updateScene_Dialog();
                break;
        }

        fpsLabel.setLocation(x, y);
        fpsLabel.setText("fps:" + ge.getFps());
        lmb.setLocation(x + 20, y + 100);
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {

        switch (gd.mapState.sceneState) {
            case MapState.STATE_MAP:
                onTouchEvent_Map(t, te);
                break;
            case MapState.STATE_MENU:
                break;
            case MapState.STATE_DIALOG:
                break;
        }

        return true;
    }

    private void updateScene_Map() {
        //所有NPC更新
        for (Npc npc : gd.mapState.curMap.npcList.values()) {
            npc.update();
        }
        //更新所有精灵组件的位置
        for (Character sprite : gd.mapState.sprites) {
            lSprites.get(sprite).setLocation(sprite.x, sprite.y);
            lSprites.get(sprite).setZ(sprite.row);
        }
        //根据player的位置确定视窗口的位置
        if (gd.mapState.curMap.cellWidth * gd.mapState.curMap.colNum > ge.getScreenWidth()) {
            x = gd.player.x - ge.getScreenWidth() / 2;
            if (x < 0) {
                x = 0;
            } else if (x > gd.mapState.curMap.cellWidth * gd.mapState.curMap.colNum - ge.getScreenWidth()) {
                x = gd.mapState.curMap.cellWidth * gd.mapState.curMap.colNum - ge.getScreenWidth();
            }
        }
        if (gd.mapState.curMap.cellHeight * gd.mapState.curMap.rowNum > ge.getScreenHeight()) {
            y = gd.player.y - ge.getScreenHeight() / 2;
            if (y < 0) {
                y = 0;
            } else if (y > gd.mapState.curMap.cellHeight * gd.mapState.curMap.rowNum - ge.getScreenHeight()) {
                y = gd.mapState.curMap.cellHeight * gd.mapState.curMap.rowNum - ge.getScreenHeight();
            }
        }
        mapBackground.setLocation(-x, -y);

        //探查并处理NPC事件
        if (!gd.player.moving) {
            Npc npc = null;//触发发事件的NPC
            List<Npc> npcs = searchNpc();
            //确定触发事件的Npc
            if (npcs.size() > 0) {
                if (npcs.contains(gd.mapState.tarfetNpc)) {
                    //玩家走到了目标NPC附近
                    npc = gd.mapState.tarfetNpc;
                } else {
                    for (Npc n : npcs) {
                        if (n.curNpcState.stateType == Npc.TYPE_NEAR) {
                            npc = n;
                            break;
                        }
                    }
                }
            }
            if (npc != null) {
                gd.mapState.focusNpc = npc;
                gd.mapState.sceneState = MapState.STATE_DIALOG;
            }
        }
    }

    private void updateScene_Menu() {
    }

    private void updateScene_Dialog() {
        //所有NPC把没走完的路走完
        for (Npc npc : gd.mapState.curMap.npcList.values()) {
            npc.move();
        }
        if (!gd.mapState.hasDialog) {
            mapBackground.addWidget(dialog);
            dialog.setLocation(x + (ge.getScreenWidth() - 600) / 2, y + ge.getScreenHeight() - 150);
            gd.mapState.hasDialog = true;
        }
    }

    private List<Npc> searchNpc() {
        //每当玩家走完一格,检查周围所有NPC
        List<Npc> npcs = new ArrayList<Npc>();
        Npc npc = null;
        //上
        npc = gd.mapState.curMap.getNpc(gd.player.row - 1, gd.player.col);
        if (npc != null) {
            npcs.add(npc);
        }
        //左
        npc = gd.mapState.curMap.getNpc(gd.player.row, gd.player.col - 1);
        if (npc != null) {
            npcs.add(npc);
        }
        //右
        npc = gd.mapState.curMap.getNpc(gd.player.row, gd.player.col + 1);
        if (npc != null) {
            npcs.add(npc);
        }
        //下
        npc = gd.mapState.curMap.getNpc(gd.player.row + 1, gd.player.col);
        if (npc != null) {
            npcs.add(npc);
        }
        return npcs;
    }

    private void onTouchEvent_Map(Object t, TouchEvent te) {
        if (t.equals(mapForeground) && te.getType() == TouchEvent.TOUCH_DOWN) {
            int[][] areaIds = new int[gd.mapState.curMap.rowNum][];
            for (int i = 0; i < areaIds.length; i++) {
                areaIds[i] = Arrays.copyOf(gd.mapState.curMap.areaIds[i], gd.mapState.curMap.areaIds[i].length);
            }

            gd.mapState.aStar.setMapData(areaIds);
            //起点
            int sRow = gd.player.row;
            int sCol = gd.player.col;
            //终点
            int eRow = te.getY() / gd.mapState.curMap.cellHeight;
            int eCol = te.getX() / gd.mapState.curMap.cellWidth;
            gd.mapState.tarfetNpc = gd.mapState.curMap.getNpc(eRow, eCol);
            System.out.println("sRow:" + sRow + " sCol:" + sCol + " eRow:" + eRow + " eCol:" + eCol);
            lmb.append("寻路起点：(" + sRow + "," + sCol + ") 终点：(" + eRow + "," + eCol + ")");
            int[] paths = gd.mapState.aStar.searchDirections(sRow, sCol, eRow, eCol);
            for (int p : paths) {
                System.out.print(p + " ");
            }
            System.out.println("");
            MoveAction me = null;
            List<MoveAction> moveActions = new ArrayList<MoveAction>();
            for (int p : paths) {
                me = new MoveAction(gd.player, p);
                me.activate();
                moveActions.add(me);
            }
            gd.player.setMoveAction(moveActions);
            mapBackground.removeWidget(lWave);
            lWave = new GLWave(eCol * gd.mapState.curMap.cellWidth, eRow * gd.mapState.curMap.cellHeight);
            mapBackground.addWidget(lWave);
        }
    }
}
