package com.soyostar.game.control;

import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.emulator.engine.KeyManager;
import com.soyostar.emulator.engine.View;
import com.soyostar.emulator.engine.script.Event;
import com.soyostar.emulator.engine.script.ScriptEngine;
import com.soyostar.game.RpgGame;
import com.soyostar.game.model.Bag;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import com.soyostar.game.view.SkillView;

/**
 *@2011.4.8 by VV
 * @author Administrator
 */
public class SkillControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

   

    public void dealKeyEvent() {
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_UP)) {
            if (gd.skill_curIndex > 0) {
                gd.skill_curIndex--;
                if (gd.skill_curIndex < gd.skill_topIndex) {
                    gd.skill_topIndex--;
                }
            }
        }
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_DOWN)) {

            if (gd.skill_curIndex < gd.player.bag.getList(Bag.SKILL).length - 1) {
                gd.skill_curIndex++;
                if (gd.skill_curIndex > gd.skill_topIndex + gd.skill_showNum - 1) {
                    gd.skill_topIndex++;
                }
            }
        }
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_RS)) {
            game.setCurView(Const.ViewId.VIEW_MAP);
        }
    }

    public void dealMotion() {
    }

    public void dealGameEvent(Event event) {
    }

    public void updateModel() {
    }
}
