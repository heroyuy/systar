/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.control;

import emulator.MotionEvent;
import engine.GameEngine;
import engine.script.GameEvent;
import game.AbControl;
import game.RpgGame;
import game.impl.model.GameData;

/**
 *
 * @author Administrator
 */
public class MapControl extends AbControl {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);

    @Override
    public void onObtain() {
        super.onObtain();
        gd.mapState.curMap = gd.dataStore.getMap(gd.player.curMapIndex);
        System.out.println("gd.mapState.curMap.name->"+gd.mapState.curMap.name);
    }

    public void dealKeyEvent(int key) {
    }

    public void onTouchEvent(MotionEvent me) {
    }

    public void dealGameEvent(GameEvent event) {
    }

    public void updateModel() {
    }
}
