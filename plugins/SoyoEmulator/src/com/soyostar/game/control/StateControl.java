package com.soyostar.game.control;

import view.StateView;
import engine.Control;
import engine.GameEngine;
import engine.View;
import engine.script.Event;
import engine.script.ScriptEngine;
import game.RpgGame;
import model.Const;
import model.GameData;

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

    public void dealEvent(View view, Event event) {
    }
}
