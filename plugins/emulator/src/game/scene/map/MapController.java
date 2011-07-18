/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.map;

import com.soyostar.app.Color;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.RpgGame;
import game.actions.MoveAction;
import game.impl.model.GameData;

/**
 *
 * @author Administrator
 */
public class MapController extends AbController implements TouchListener {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
    private MapLayer mapLayer = null;
    private SpriteLayer spriteLayer = null;

    public MapController(Render render) {
        super(render);
        mapLayer = new MapLayer();
        mapLayer.setBackground(Color.GREEN);
        mapLayer.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        mapLayer.setVisible(true);
        mapLayer.setCurMap(gd.curMap);
        mapLayer.setTouchListener(this);
        spriteLayer = new SpriteLayer(gd.player);
        spriteLayer.setLocation(gd.player.col * gd.curMap.cellWidth + gd.curMap.cellWidth / 2 - gd.player.getCurStepImage().getWidth() / 2, gd.player.row * gd.curMap.cellHeight + gd.curMap.cellHeight / 2 - gd.player.getCurStepImage().getHeight());

        spriteLayer.setVisible(true);
    }

    public void onObtain() {
        addWidget(mapLayer);
        mapLayer.addWidget(spriteLayer);
    }

    public void onLose() {
    }

    public void updateModel() {
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {
        if (t.equals(mapLayer) && te.getType() == TouchEvent.TOUCH_DOWN) {
            MoveAction me=MoveAction.createMoveDownAction(spriteLayer, gd.player);
            me.activate();
            gd.actionManager.addAction(me);
//            gd.actionManager.addAction(MoveAction.createMoveDownAction(spriteLayer, gd.player));
//            gd.actionManager.addAction(MoveAction.createMoveRightAction(spriteLayer, gd.player));
//            gd.actionManager.addAction(MoveAction.createMoveRightAction(spriteLayer, gd.player));

        }
        return true;

    }
}
