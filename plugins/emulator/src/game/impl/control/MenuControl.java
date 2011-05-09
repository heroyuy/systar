/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.control;

import emulator.KeyValue;
import emulator.MotionEvent;
import engine.GameEngine;
import engine.script.GameEvent;
import game.AbControl;
import game.Const;
import game.RpgGame;
import game.impl.model.GameData;

/**
 *
 * @author Administrator
 */
public class MenuControl extends AbControl {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);

    public void dealKeyEvent(int key) {
        switch (key) {
            case KeyValue.KEY_UP:
                gd.menuState.menuIndex = (gd.menuState.menuIndex + Const.Text.MENU.length - 1) % Const.Text.MENU.length;
                break;
            case KeyValue.KEY_DOWN:
                gd.menuState.menuIndex = (gd.menuState.menuIndex + 1) % Const.Text.MENU.length;
                break;
        }
        ge.clearKey();
    }

    public void onTouchEvent(MotionEvent me) {
    }

    public void dealGameEvent(GameEvent event) {
    }

    public void updateModel() {
    }
}
