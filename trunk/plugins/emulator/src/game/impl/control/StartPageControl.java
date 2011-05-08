/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.control;

import emulator.MotionEvent;
import engine.GameEngine;
import engine.script.GameEvent;
import game.AbControl;
import game.Const;
import game.RpgGame;

/**
 *
 * @author Administrator
 */
public class StartPageControl extends AbControl {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();

    public void dealKeyEvent(int key) {
        rpgGame.setCurrentControl(Const.ControlId.MENU);
        ge.clearKey();
    }

    public void onTouchEvent(MotionEvent me) {
        rpgGame.setCurrentControl(Const.ControlId.MENU);
        ge.clearMotionEvent();
    }

    public void dealGameEvent(GameEvent event) {
    }

    public void updateModel() {
    }
}
