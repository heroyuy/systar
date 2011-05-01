package game.impl.control;

import game.impl.view.StateView;
import game.Control;
import engine.GameEngine;
import game.View;
import engine.script.GameEvent;
import engine.script.ScriptEngine;
import game.RpgGame;
import game.impl.model.Const;
import game.impl.model.GameData;

/**
 *
 * @author Administrator
 */
public class StateControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    public void keyPressed(View view, int key) {
        if (view instanceof StateView) {
            switch (key) {
                case Const.Key.KEY_RS:
                    game.setCurView(Const.ViewId.VIEW_MAP);
                    break;
            }
        }
        ge.clearKey();
    }

    public void dealEvent(View view, GameEvent event) {
    }
}
