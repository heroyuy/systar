package com.soyostar.game.control;

import view.SettingView;
import engine.Control;
import engine.GameEngine;
import engine.View;
import engine.script.Event;
import game.RpgGame;
import model.Const;
import model.GameData;

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
			if (key == Const.Key.KEY_UP) {
				gd.yIndex--;
				if (gd.yIndex < 0) {
					gd.yIndex = 1;
				}
			} else if (key == Const.Key.KEY_DOWN) {
				gd.yIndex++;
				if (gd.yIndex > 1) {
					gd.yIndex = 0;
				}
			} else if (key == Const.Key.KEY_LEFT) {
				if (gd.yIndex == 0) {
					gd.musicOn = !gd.musicOn;
				} else {
					gd.soundOn = !gd.soundOn;
				}
			} else if (key == Const.Key.KEY_RIGHT) {
				if (gd.yIndex == 0) {
					gd.musicOn = !gd.musicOn;
				} else {
					gd.soundOn = !gd.soundOn;
				}
			} else if (key == Const.Key.KEY_LS) {
				game.setCurView(Const.ViewId.VIEW_MENU);
			}
		}
	}

    public void dealEvent(View view, Event event) {
    }
}
