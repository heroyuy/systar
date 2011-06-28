/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.control;

import com.soyostar.app.KeyEvent;
import com.soyostar.app.TouchEvent;
import engine.GameEngine;
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

    public void onTouchEvent(TouchEvent me) {
        rpgGame.setCurrentControl(Const.ControlId.MENU);
        ge.clearTouchEvent();
    }

    public void onKeyEvent(KeyEvent ke) {
        if (ke.getType() == KeyEvent.KEY_DOWN) {
            rpgGame.setCurrentControl(Const.ControlId.MENU);
        }
        ge.clearKeyEvent();
    }

    public void updateModel() {
    }
}
