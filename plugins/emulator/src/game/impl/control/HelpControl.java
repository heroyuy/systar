package game.impl.control;

import game.impl.view.HelpView;
import game.Control;
import engine.GameEngine;
import game.View;
import engine.script.GameEvent;
import game.RpgGame;
import game.impl.model.Const;

/**
 *
 * 游戏帮助视图控制器
 */
public class HelpControl implements Control {

    private GameEngine ge=GameEngine.getInstance();
    private RpgGame game = (RpgGame)ge.getGame();

    public void keyPressed(View view, int key) {
        if (view instanceof HelpView) {
            if (key == Const.Key.KEY_LS) {
                game.setCurView(Const.ViewId.VIEW_MENU);
            }
        }
    }

    public void dealEvent(View view, GameEvent event) {
    }
}
