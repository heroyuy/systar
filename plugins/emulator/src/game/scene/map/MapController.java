package game.scene.map;

import com.soyostar.app.Color;
import com.soyostar.app.Layer;
import com.soyostar.app.Widget;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.RpgGame;
import game.actions.MoveAction;
import game.impl.model.GameData;
import game.impl.model.Npc;
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
        spriteLayer_player.setLocation(gd.player.col * gd.curMap.cellWidth + gd.curMap.cellWidth / 2 - gd.player.getCurStepImage().getWidth() / 2, gd.player.row * gd.curMap.cellHeight + gd.curMap.cellHeight / 2 - gd.player.getCurStepImage().getHeight());
        spriteLayer_player.setVisible(true);
        spriteLayer_npcs = new SpriteLayer[gd.curMap.npcList.size()];
        int index = 0;
        for (Npc npc : gd.curMap.npcList.values()) {
            spriteLayer_npcs[index] = new SpriteLayer(npc.curNpcState);
            spriteLayer_npcs[index].setLocation(npc.col * gd.curMap.cellWidth + gd.curMap.cellWidth / 2 - npc.curNpcState.getCurStepImage().getWidth() / 2, npc.row * gd.curMap.cellHeight + gd.curMap.cellHeight / 2 - npc.curNpcState.getCurStepImage().getHeight());
            spriteLayer_npcs[index].setVisible(true);
            index++;
        }
    }

    public void onObtain() {
        addWidget(mapBackground);
        mapBackground.addWidget(spriteLayer_player);
        for (SpriteLayer spriteLayer : spriteLayer_npcs) {
            mapBackground.addWidget(spriteLayer);
        }
        mapBackground.addWidget(mapForeground);
    }

    public void onLose() {
    }

    public void updateModel() {
        if (ge.getTicker() % 10 == 0) {
            int num = new Random().nextInt(4);
            int index = new Random().nextInt(2);
            MoveAction me = null;
            switch (num) {
                case 0:
                    me = MoveAction.createMoveUpAction(spriteLayer_npcs[index], ((Npc) gd.curMap.npcList.values().toArray()[index]).curNpcState);

                    break;
                case 1:
                    me = MoveAction.createMoveDownAction(spriteLayer_npcs[index], ((Npc) gd.curMap.npcList.values().toArray()[index]).curNpcState);

                    break;
                case 2:
                    me = MoveAction.createMoveLeftAction(spriteLayer_npcs[index], ((Npc) gd.curMap.npcList.values().toArray()[index]).curNpcState);

                    break;
                case 3:
                    me = MoveAction.createMoveRightAction(spriteLayer_npcs[index], ((Npc) gd.curMap.npcList.values().toArray()[index]).curNpcState);

                    break;
            }
            me.activate();
            gd.actionManager.addAction(me);
        }
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {
        if (t.equals(mapForeground) && te.getType() == TouchEvent.TOUCH_DOWN) {
            MoveAction me = MoveAction.createMoveDownAction(spriteLayer_player, gd.player);
            me.activate();
            gd.actionManager.addAction(me);
        }
        return true;
    }
}
