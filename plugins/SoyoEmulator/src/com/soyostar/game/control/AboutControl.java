package com.soyostar.game.control;

import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.emulator.engine.View;
import com.soyostar.emulator.engine.script.Event;
import com.soyostar.game.RpgGame;
import com.soyostar.game.model.Const;
import com.soyostar.game.view.AboutView;

/*@2011.4.5 by vv
 *
 * 游戏关于视图控制器
 */
public class AboutControl implements Control {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    public void keyPressed(View view, int key) {
        if (view instanceof AboutView) {
            if (key == Const.Key.KEY_LS) {
                game.setCurView(Const.ViewId.VIEW_MENU);
            }
        }
    }

    public void dealEvent(View view, Event event) {
    }

    public void dealKeyEvent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void dealMotion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void dealGameEvent(Event event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateModel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
