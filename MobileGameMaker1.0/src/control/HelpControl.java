package control;

import view.HelpView;
import emulator.engine.Control;
import emulator.engine.GameEngine;
import emulator.engine.View;
import emulator.engine.script.Event;
import game.RpgGame;
import emulator.model.Const;

/**
 *
 * ”Œœ∑∞Ô÷˙ ”Õºøÿ÷∆∆˜
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

    public void dealEvent(View view, Event event) {
    }
}
