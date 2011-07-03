/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.map;

import com.soyostar.app.KeyEvent;
import com.soyostar.app.TouchEvent;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.RpgGame;
import game.impl.model.GameData;

/**
 *
 * @author Administrator
 */
public class MapController extends AbController {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);

    public MapController(Render render) {
        super(render);
    }

    @Override
    public void onObtain() {
        gd.mapState.curMap = gd.dataStore.getMap(gd.player.curMapIndex);
        System.out.println("gd.mapState.curMap.name->" + gd.mapState.curMap.name);
    }

    public void onTouchEvent(TouchEvent te) {
    }

    public void updateModel() {
    }

    public void onKeyEvent(KeyEvent ke) {
    }

    public void onLose() {
    }
}
