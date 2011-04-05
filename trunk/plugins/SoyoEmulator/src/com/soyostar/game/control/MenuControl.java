package com.soyostar.game.control;

import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.emulator.engine.script.Event;
import com.soyostar.game.model.GameData;

public class MenuControl implements Control {

    GameEngine ge = GameEngine.getInstance();
    GameData gd = GameData.getGameData();

    public void dealKeyEvent() {
        if (ge.getKeyManager().isAnyKeyPressed()) {
            gd.menu.text = "有按键事件";
        }
    }

    public void dealMotion() {
        if (ge.getPointerManager().isScreenTouched()) {
            gd.menu.text = "有触屏事件";
        } else {
            gd.menu.text="";
        }
        ge.getPointerManager().clear();
    }

    public void dealGameEvent(Event event) {
    }
}
