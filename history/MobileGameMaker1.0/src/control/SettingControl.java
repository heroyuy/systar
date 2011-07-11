package control;

import view.SettingView;
import emulator.engine.Control;
import emulator.engine.GameEngine;
import emulator.engine.View;
import emulator.engine.script.Event;
import game.RpgGame;
import emulator.model.Const;
import emulator.model.GameData;

/**
 *
 * 游戏设置视图的控制器
 */
public class SettingControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge=GameEngine.getInstance();
    private RpgGame game = (RpgGame)ge.getGame();
    public void keyPressed(View view, int key) {
        if (view instanceof SettingView) {
            if (key == Const.Key.KEY_LS) {
                game.setCurView(Const.ViewId.VIEW_MENU);
            }
        }
    }
    public void dealEvent(View view, Event event) {
    }
}
