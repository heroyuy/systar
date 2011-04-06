package com.soyostar.game.control;

import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.emulator.engine.KeyManager;
import com.soyostar.emulator.engine.script.Event;
import com.soyostar.game.RpgGame;
import com.soyostar.game.model.Const;

/**
 *
 * 游戏关于视图控制器
 */
public class AboutControl implements Control {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    public void dealKeyEvent() {
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_LS)) {
            game.setCurView(Const.ViewId.VIEW_MENU);
        }
    }

    public void dealMotion() {
    }

    public void dealGameEvent(Event event) {
    }

    public void updateModel() {
    }
}
