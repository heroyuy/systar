/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.control;

import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.emulator.engine.KeyManager;

import com.soyostar.emulator.engine.script.Event;
import com.soyostar.game.RpgGame;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;


/**@2011.4.6 by VV
 *
 * 游戏设置视图的控制器
 */
public class SettingControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

  

    public void dealKeyEvent() {
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_UP)) {
            gd.yIndex--;
            if (gd.yIndex < 0) {
                gd.yIndex = 1;
            }
        }else if(ge.getKeyManager().isPressKey(KeyManager.KEY_DOWN)){
              gd.yIndex++;
                if (gd.yIndex > 1) {
                    gd.yIndex = 0;
                }
        }else if(ge.getKeyManager().isPressKey(KeyManager.KEY_LEFT)){
             if (gd.yIndex == 0) {
                    gd.musicOn = !gd.musicOn;
                } else {
                    gd.soundOn = !gd.soundOn;
                }
        }else if(ge.getKeyManager().isPressKey(KeyManager.KEY_RIGHT)){
              if (gd.yIndex == 0) {
                    gd.musicOn = !gd.musicOn;
                } else {
                    gd.soundOn = !gd.soundOn;
                }
        }else if(ge.getKeyManager().isPressKey(KeyManager.KEY_LS)){
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
