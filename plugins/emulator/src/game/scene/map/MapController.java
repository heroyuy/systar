package game.scene.map;

import com.soyostar.app.Color;
import com.soyostar.app.LLabel;
import com.soyostar.app.Layer;
import com.soyostar.app.Painter;
import com.soyostar.app.Widget;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import com.soyostar.util.astar.AStar;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.RpgGame;
import game.actions.MoveAction;
import game.impl.model.GameData;
import game.impl.model.Npc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author wp_g4
 */
public class MapController extends AbController implements TouchListener {

  
    private Layer mapBackground = null;
    private Widget mapForeground = null;
    private SpriteLayer spriteLayer_player = null;
    private SpriteLayer[] spriteLayer_npcs = null;
    private LLabel fpsLabel = null;
    private AStar aStar = new AStar();

    public MapController(Render render) {
        super(render);
        mapBackground = new Layer();
        mapBackground.setBackground(Color.GREEN);
        mapBackground.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        mapBackground.setVisible(true);
        mapBackground.setBackgroundImage(gd.player.curMap.background);
        mapBackground.setTouchListener(this);
        mapForeground = new Widget();
        mapForeground.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        mapForeground.setVisible(true);
        mapForeground.setBackgroundImage(gd.player.curMap.foreground);
        mapForeground.setTouchListener(this);

        spriteLayer_player = new SpriteLayer(gd.player);
        spriteLayer_player.setLocation(gd.player.col * gd.player.curMap.cellWidth + gd.player.curMap.cellWidth / 2 - gd.player.getCurStepImage().getWidth() / 2, gd.player.row * gd.player.curMap.cellHeight + gd.player.curMap.cellHeight - gd.player.getCurStepImage().getHeight());
        spriteLayer_player.setVisible(true);
        spriteLayer_npcs = new SpriteLayer[gd.player.curMap.npcList.size()];
        int index = 0;
        for (Npc npc : gd.player.curMap.npcList.values()) {
            spriteLayer_npcs[index] = new SpriteLayer(npc);
            spriteLayer_npcs[index].setLocation(npc.col * gd.player.curMap.cellWidth + gd.player.curMap.cellWidth / 2 - npc.getCurStepImage().getWidth() / 2, npc.row * gd.player.curMap.cellHeight + gd.player.curMap.cellHeight - npc.getCurStepImage().getHeight());
            spriteLayer_npcs[index].setVisible(true);
            index++;
        }

        fpsLabel = new LLabel();
        fpsLabel.setSize(80, 20);
        fpsLabel.setLocation(0, 0);
        fpsLabel.setTextColor(Color.WHITE);
        fpsLabel.setTextAnchor(Painter.HV);
        fpsLabel.setVisible(true);
    }

    public void onObtain() {
        addWidget(mapBackground);
        mapBackground.addWidget(spriteLayer_player);
        for (SpriteLayer spriteLayer : spriteLayer_npcs) {
            mapBackground.addWidget(spriteLayer);
        }
        mapBackground.addWidget(mapForeground);
        mapBackground.addWidget(fpsLabel);
    }

    public void onLose() {
    }

    public void updateModel() {
        for (Npc npc : gd.player.curMap.npcList.values()) {
            npc.update();
        }
        fpsLabel.setText(ge.getFps() + "");
        if (!gd.player.curMap.npcList.isEmpty()) {
            if (ge.getTicker() % 10 == 0) {
                int num = new Random().nextInt(4);
                int index = new Random().nextInt(gd.player.curMap.npcList.size());
                MoveAction me = null;
                switch (num) {
                    case 0:
                        me = MoveAction.createMoveUpAction(spriteLayer_npcs[index], (Npc) gd.player.curMap.npcList.values().toArray()[index]);

                        break;
                    case 1:
                        me = MoveAction.createMoveDownAction(spriteLayer_npcs[index], (Npc) gd.player.curMap.npcList.values().toArray()[index]);

                        break;
                    case 2:
                        me = MoveAction.createMoveLeftAction(spriteLayer_npcs[index], (Npc) gd.player.curMap.npcList.values().toArray()[index]);

                        break;
                    case 3:
                        me = MoveAction.createMoveRightAction(spriteLayer_npcs[index], (Npc) gd.player.curMap.npcList.values().toArray()[index]);

                        break;
                }
                me.activate();
                ((Npc) gd.player.curMap.npcList.values().toArray()[index]).addMoveAction(me);
            }
        }
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {
        if (t.equals(mapForeground) && te.getType() == TouchEvent.TOUCH_DOWN) {
            int[][] areaIds = new int[gd.player.curMap.rowNum][gd.player.curMap.colNum];
            for (int i = 0; i < areaIds.length; i++) {
                for (int j = 0; j < areaIds[i].length; j++) {
                    areaIds[i][j] = gd.player.curMap.areaIds[i][j];
                }

            }

            aStar.setMapData(areaIds);
            //起点
            int sRow = gd.player.row;
            int sCol = gd.player.col;
            //终点
            int eRow = te.getY() / gd.player.curMap.cellHeight;
            int eCol = te.getX() / gd.player.curMap.cellWidth;
            System.out.println("sRow:" + sRow + " sCol:" + sCol + " eRow:" + eRow + " eCol:" + eCol);
            int[] paths = aStar.searchDirections(sRow, sCol, eRow, eCol);
            for (int p : paths) {
                System.out.print(p + " ");
            }
            System.out.println("");
            MoveAction me = null;
            List<MoveAction> moveActions = new ArrayList<MoveAction>();
            for (int p : paths) {
                switch (p) {
                    case 0:
                        me = MoveAction.createMoveUpAction(spriteLayer_player, gd.player);
                        break;
                    case 1:
                        me = MoveAction.createMoveDownAction(spriteLayer_player, gd.player);
                        break;
                    case 2:
                        me = MoveAction.createMoveLeftAction(spriteLayer_player, gd.player);
                        break;
                    case 3:
                        me = MoveAction.createMoveRightAction(spriteLayer_player, gd.player);
                        break;
                }
                me.activate();
                moveActions.add(me);
            }
            gd.player.setMoveAction(moveActions);
        }
        return true;
    }
}
