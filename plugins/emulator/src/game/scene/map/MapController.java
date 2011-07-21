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

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
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
        mapBackground.setBackgroundImage(gd.curMap.background);
        mapBackground.setTouchListener(this);
        mapForeground = new Widget();
        mapForeground.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        mapForeground.setVisible(true);
        mapForeground.setBackgroundImage(gd.curMap.foreground);
        mapForeground.setTouchListener(this);

        spriteLayer_player = new SpriteLayer(gd.player);
        spriteLayer_player.setLocation(gd.player.col * gd.curMap.cellWidth + gd.curMap.cellWidth / 2 - gd.player.getCurStepImage().getWidth() / 2, gd.player.row * gd.curMap.cellHeight + gd.curMap.cellHeight - gd.player.getCurStepImage().getHeight());
        spriteLayer_player.setVisible(true);
        spriteLayer_npcs = new SpriteLayer[gd.curMap.npcList.size()];
        int index = 0;
        for (Npc npc : gd.curMap.npcList.values()) {
            spriteLayer_npcs[index] = new SpriteLayer(npc);
            spriteLayer_npcs[index].setLocation(npc.col * gd.curMap.cellWidth + gd.curMap.cellWidth / 2 - npc.getCurStepImage().getWidth() / 2, npc.row * gd.curMap.cellHeight + gd.curMap.cellHeight - npc.getCurStepImage().getHeight());
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
        for (Npc npc : gd.curMap.npcList.values()) {
            npc.update();
        }
        fpsLabel.setText(ge.getFps() + "");
        if (!gd.curMap.npcList.isEmpty()) {
            if (ge.getTicker() % 10 == 0) {
                int num = new Random().nextInt(4);
                int index = new Random().nextInt(gd.curMap.npcList.size());
                MoveAction me = null;
                switch (num) {
                    case 0:
                        me = MoveAction.createMoveUpAction(spriteLayer_npcs[index], (Npc) gd.curMap.npcList.values().toArray()[index]);

                        break;
                    case 1:
                        me = MoveAction.createMoveDownAction(spriteLayer_npcs[index], (Npc) gd.curMap.npcList.values().toArray()[index]);

                        break;
                    case 2:
                        me = MoveAction.createMoveLeftAction(spriteLayer_npcs[index], (Npc) gd.curMap.npcList.values().toArray()[index]);

                        break;
                    case 3:
                        me = MoveAction.createMoveRightAction(spriteLayer_npcs[index], (Npc) gd.curMap.npcList.values().toArray()[index]);

                        break;
                }
                me.activate();
                ((Npc) gd.curMap.npcList.values().toArray()[index]).addMoveAction(me);
            }
        }
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {
        if (t.equals(mapForeground) && te.getType() == TouchEvent.TOUCH_DOWN) {
            boolean[][] ways = new boolean[gd.curMap.rowNum][gd.curMap.colNum];
            for (int i = 0; i < ways.length; i++) {
                for (int j = 0; j < ways[i].length; j++) {
                    ways[i][j] = gd.curMap.ways[i][j];
                }

            }
            for (Npc npc : gd.curMap.npcList.values()) {
                ways[npc.row][npc.col] = false;
            }
            for (int i = 0; i < ways.length; i++) {
                for (int j = 0; j < ways[i].length; j++) {
                    System.out.print(ways[i][j] + " ");
                }
                System.out.println("");
            }
            aStar.setMapData(ways);
            //起点
            int sRow = gd.player.row;
            int sCol = gd.player.col;
            //终点
            int eRow = te.getY() / gd.curMap.cellHeight;
            int eCol = te.getX() / gd.curMap.cellWidth;
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
