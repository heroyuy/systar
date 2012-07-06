package com.soyomaker.emulator.app;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.utils.SMLog;

public class Game {

	public static String DEFAULT_GAME_PATH = "./game";

	private LuaAdapter luaAdapter = null;

	private Painter painter = new Painter();

	public static void main(String[] args) {
		new Game().start();
	}

	private Game() {
		try {
			Display.setTitle("Game");
			Display.setDisplayMode(new DisplayMode(getWidth(), getHeight()));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void start() {
		GameInfo.getInstance().setGamePath(DEFAULT_GAME_PATH);
		luaAdapter = new LuaAdapter(this);
		luaAdapter.onStart();
		while (!Display.isCloseRequested()) {
			long t = getTime();
			updateModel();
			dealControlEvent();
			paintView();
			Display.update();
			t = getTime() - t;
			GameInfo gameInfo = GameInfo.getInstance();
			if (t < 1000 * 1.0 / gameInfo.getRatedFPS()) {
				gameInfo.setActualFPS(gameInfo.getRatedFPS());
				this.sleep((int) (1000 * 1.0 / gameInfo.getRatedFPS() - t));
			} else {
				gameInfo.setActualFPS((int) (1000 * 1.0 / t));
				if (gameInfo.getActualFps() < gameInfo.getRatedFPS()) {
					SMLog.getInstance().info("FPS警告:" + gameInfo.getActualFps());
				}
			}
		}
		Display.destroy();
	}

	private void updateModel() {
		luaAdapter.update();
	}

	private void dealControlEvent() {
	}

	private void paintView() {
		luaAdapter.paint(painter);
	}

	public int getHeight() {
		return GameInfo.getInstance().getHeight();
	}

	public String getPath() {
		return GameInfo.getInstance().getGamePath();
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public int getWidth() {
		return GameInfo.getInstance().getWidth();
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
