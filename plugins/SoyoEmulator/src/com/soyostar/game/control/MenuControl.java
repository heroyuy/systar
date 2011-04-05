package com.soyostar.game.control;

import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.emulator.engine.script.Event;
import com.soyostar.game.model.GameData;
/**
 *
 * 游戏菜单视图的控制器
 */
public class MenuControl implements Control {

    GameEngine ge = GameEngine.getInstance();
    GameData gd = GameData.getGameData();

    public void dealKeyEvent() {
        gd.menu.text = "有按键事件";

    }

    public void dealMotion() {
        gd.menu.text = "有触屏事件";
        ge.getPointerManager().clear();
    }

    public void dealGameEvent(Event event) {
    }

    public void updateModel() {
        gd.menu.text = "";
    }
}
