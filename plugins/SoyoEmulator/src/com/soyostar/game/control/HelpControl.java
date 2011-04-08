/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyostar.game.control;

import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.emulator.engine.KeyManager;
import com.soyostar.emulator.engine.View;
import com.soyostar.emulator.engine.script.Event;
import com.soyostar.game.RpgGame;
import com.soyostar.game.model.Const;
import com.soyostar.game.view.HelpView;


/**@2011.4.6 byVV
 *
 * 游戏帮助视图控制器
 */
public class HelpControl implements Control {

    private GameEngine ge=GameEngine.getInstance();
    private RpgGame game = (RpgGame)ge.getGame();


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

