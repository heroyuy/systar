package control;

import view.AboutView;
import engine.Control;
import engine.GameEngine;
import engine.View;
import engine.script.Event;
import game.RpgGame;
import model.Const;

/**
 *
 * 游戏关于视图控制器
 */
public class AboutControl implements Control{

    private GameEngine ge=GameEngine.getInstance();
    private RpgGame game = (RpgGame)ge.getGame();

    public void keyPressed(View view, int key) {
        if (view instanceof AboutView) {
            if (key == Const.Key.KEY_LS) {
                game.setCurView(Const.ViewId.VIEW_MENU);
            }
        }
    }

    public void dealEvent(View view, Event event) {
    }


}
