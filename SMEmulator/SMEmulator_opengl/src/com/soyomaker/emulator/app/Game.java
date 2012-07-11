package com.soyomaker.emulator.app;

import static org.lwjgl.opengl.GL11.glClear;

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

	public static void main(String[] args) {
		new Game().start();
	}

	// private LuaAdapter luaAdapter = null;

	private Painter painter = null;

	private PainterTest pt = null;

	private long t = 0;

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

		// 关闭深度测试
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		// 关闭光照
		GL11.glDisable(GL11.GL_LIGHTING);
		// 启用混合
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		// 视口
		GL11.glViewport(0, 0, width, height);
		// 设置投影变换
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		// 重置投影矩阵
		GL11.glLoadIdentity();
		// 转换坐标系（opengl坐标原点在左下角，转换为java坐标系，原点在右上角）
		GL11.glOrtho(0, width, height, 0, 1, -1);
	}

	private void dealEvent() {
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

	private void paintView() {
		// 设置模型变换
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		// 重置矩阵
		GL11.glLoadIdentity();
		// 清屏
		glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		// 游戏绘制
		// luaAdapter.paint(painter);
		pt.test(painter);
	}

	private void showDebugInfo() {
		// 计算FPS
		int delta = (int) (getTime() - t);
		GameInfo gameInfo = GameInfo.getInstance();
		if (delta < 1000 * 1.0 / gameInfo.getRatedFPS()) {
			gameInfo.setActualFPS(gameInfo.getRatedFPS());
			this.sleep((int) (1000 * 1.0 / gameInfo.getRatedFPS() - delta));
		} else {
			gameInfo.setActualFPS((int) (1000 * 1.0 / delta));
			if (gameInfo.getActualFps() < gameInfo.getRatedFPS()) {
				SMLog.getInstance().info("FPS警告:" + gameInfo.getActualFps());
			}
		}
		// 显示FPS和内存
		painter.setColor(Color.WHITE);
		String fpsStr = "帧率FPS:" + GameInfo.getInstance().getActualFps();
		painter.drawString(fpsStr, 10, getHeight() - painter.getTextSize() - 10);
		// String memoryStr = " LuaMemory:" + luaAdapter.getLuaMemory() + "K";
		// painter.drawString(memoryStr, 20 + painter.stringWidth(fpsStr),
		// getHeight() - painter.getTextSize() - 10);
		t = getTime();
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void start() {
		GameInfo.getInstance().setGamePath(DEFAULT_GAME_PATH);
		// luaAdapter = new LuaAdapter(this);
		painter = new Painter();
		pt = new PainterTest();
		t = getTime();
		// luaAdapter.onStart();
		while (!Display.isCloseRequested()) {
			// 处理事件
			dealEvent();
			// 更新模型
			updateModel();
			// 绘制视图
			paintView();
			// 显示debug信息
			showDebugInfo();
			// 提交更新
			Display.update();

		}
		// luaAdapter.onStop();
		Display.destroy();
	}

	private void updateModel() {
		// luaAdapter.update();
	}
}
