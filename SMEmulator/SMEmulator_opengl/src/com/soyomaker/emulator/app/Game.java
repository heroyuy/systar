package com.soyomaker.emulator.app;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.soyomaker.emulator.ui.Color;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.utils.SMLog;

public class Game {

	public static String DEFAULT_GAME_PATH = "./game";

	private LuaAdapter luaAdapter = null;

	private Painter painter = null;

	public static void main(String[] args) {
		new Game().start();
	}

	private Game() {// initialize the window beforehand
		int width = getWidth();
		int height = getHeight();

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	private void start() {
		GameInfo.getInstance().setGamePath(DEFAULT_GAME_PATH);
		luaAdapter = new LuaAdapter(this);
		painter = new Painter();
		luaAdapter.onStart();
		while (!Display.isCloseRequested()) {
			long t = getTime();
			updateModel();
			dealControlEvent();
			paintView();
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
			// show fps
			showFPS();
			Display.update();

		}
		Display.destroy();
	}

	private void showFPS() {
		painter.setColor(Color.WHITE);
		painter.drawString("fps:" + GameInfo.getInstance().getActualFps(), 10, getHeight() - painter.getTextSize() - 10);
	}

	private void updateModel() {
		luaAdapter.update();
	}

	private void dealControlEvent() {
	}

	private void paintView() {
		// 清屏
		glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		// 游戏绘制
		luaAdapter.paint(painter);
	}

	public int getHeight() {
		return GameInfo.getInstance().getHeight();
	}

	public String getPath() {
		return GameInfo.getInstance().getGamePath();
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
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
