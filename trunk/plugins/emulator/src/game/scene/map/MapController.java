package game.scene.map;

import com.soyostar.app.Color;
import com.soyostar.app.LLabel;
import com.soyostar.app.Layer;
import com.soyostar.app.Painter;
import com.soyostar.app.Widget;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import com.soyostar.util.astar.AStar;
import game.AbController;
import game.actions.MoveAction;
import game.impl.model.Map;
import game.impl.model.Npc;
import game.impl.model.Character;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author wp_g4
 */
public class MapController extends AbController implements TouchListener {

    private Layer mapBackground = null;
    private Widget mapForeground = null;
    private Layer spriteLayer = null;
    private List<Character> sprites = null;
    private java.util.Map<Character, LSprite> lSprites = null;
    private LWave lWave = null;
    private LLabel fpsLabel = null;
    private Map curMap = null;
    private AStar aStar = new AStar();

    public MapController() {
        curMap = gd.player.curMap;
        mapBackground = new Layer();
        mapBackground.setBackground(Color.GREEN);
        mapBackground.setSize(curMap.colNum * curMap.cellWidth, curMap.rowNum * curMap.cellHeight);
        mapBackground.setVisible(true);
        mapBackground.setBackgroundImage(gd.player.curMap.background);
        mapBackground.setTouchListener(this);

        spriteLayer = new Layer();
        spriteLayer.setSize(curMap.colNum * curMap.cellWidth, curMap.rowNum * curMap.cellHeight);
        spriteLayer.setVisible(true);

        mapForeground = new Widget();
        mapForeground.setSize(curMap.colNum * curMap.cellWidth, curMap.rowNum * curMap.cellHeight);
        mapForeground.setVisible(true);
        mapForeground.setBackgroundImage(gd.player.curMap.foreground);
        mapForeground.setTouchListener(this);

        sprites = new ArrayList<Character>();
        sprites.add(gd.player);
        for (Npc npc : curMap.npcList.values()) {
            sprites.add(npc);
        }
        lSprites = new HashMap<Character, LSprite>();
        for (Character sprite : sprites) {
            lSprites.put(sprite, new LSprite(sprite));
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
        for (LSprite lSprite : lSprites.values()) {
            spriteLayer.addWidget(lSprite);
        }
        mapBackground.addWidget(spriteLayer);
        mapBackground.addWidget(mapForeground);
        mapBackground.addWidget(fpsLabel);
    }

    public void onLose() {
    }

    public void updateModel() {
        //所有NPC更新
        for (Npc npc : curMap.npcList.values()) {
            npc.update();
        }
        //更新所有精灵组件的位置
        for (Character sprite : sprites) {
            lSprites.get(sprite).setLocation(sprite.x, sprite.y);
            lSprites.get(sprite).setZ(sprite.row);
        }
        //根据player的位置确定视窗口的位置
        int x = 0, y = 0;
        if (curMap.cellWidth * curMap.colNum > ge.getScreenWidth()) {
            x = gd.player.x - ge.getScreenWidth() / 2;
            if (x < 0) {
                x = 0;
            } else if (x > curMap.cellWidth * curMap.colNum - ge.getScreenWidth()) {
                x = curMap.cellWidth * curMap.colNum - ge.getScreenWidth();
            }
        }
        if (curMap.cellHeight * curMap.rowNum > ge.getScreenHeight()) {
            y = gd.player.y - ge.getScreenHeight() / 2;
            if (y < 0) {
                y = 0;
            } else if (y > curMap.cellHeight * curMap.rowNum - ge.getScreenHeight()) {
                y = curMap.cellHeight * curMap.rowNum - ge.getScreenHeight();
            }
        }
        mapBackground.setLocation(-x, -y);
        fpsLabel.setLocation(x, y);
        fpsLabel.setText(  "fps:"+ge.getFps());
        if (!curMap.npcList.isEmpty()) {
            if (ge.getTicker() % 10 == 0) {
                int num = new Random().nextInt(4);
                int index = new Random().nextInt(curMap.npcList.size());
                MoveAction me = null;
                switch (num) {
                    case 0:
                        me = MoveAction.createMoveUpAction((Npc) curMap.npcList.values().toArray()[index]);

                        break;
                    case 1:
                        me = MoveAction.createMoveDownAction((Npc) curMap.npcList.values().toArray()[index]);

                        break;
                    case 2:
                        me = MoveAction.createMoveLeftAction((Npc) curMap.npcList.values().toArray()[index]);

                        break;
                    case 3:
                        me = MoveAction.createMoveRightAction((Npc) curMap.npcList.values().toArray()[index]);

                        break;
                }
                me.activate();
                ((Npc) curMap.npcList.values().toArray()[index]).addMoveAction(me);
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
                        me = MoveAction.createMoveUpAction(gd.player);
                        break;
                    case 1:
                        me = MoveAction.createMoveDownAction(gd.player);
                        break;
                    case 2:
                        me = MoveAction.createMoveLeftAction(gd.player);
                        break;
                    case 3:
                        me = MoveAction.createMoveRightAction(gd.player);
                        break;
                }
                me.activate();
                moveActions.add(me);
            }
            gd.player.setMoveAction(moveActions);
            mapBackground.removeWidget(lWave);
            lWave = new LWave(eCol * curMap.cellWidth, eRow * curMap.cellHeight);
            mapBackground.addWidget(lWave);
        }
        return true;
    }
}
