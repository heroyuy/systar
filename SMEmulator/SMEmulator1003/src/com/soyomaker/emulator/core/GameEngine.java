package com.soyomaker.emulator.core;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.soyostar.xml.XMLObject;
import com.soyostar.xml.XMLParser;

public class GameEngine implements Runnable {

	private static GameEngine instance = new GameEngine();

	public static final int TOUCH_TYPE_DOWN = 0;

	public static final int TOUCH_TYPE_MOVE = 1;

	public static final int TOUCH_TYPE_UP = 2;

	public static GameEngine getInstance() {
		return instance;
	}

	private int width = 0;// 屏幕宽度

	private int height = 0;// 屏幕高度

	private int ratedFps = 0;// 额定fps

	private int actualFps = 0;// 实际fps

	private boolean showFps = false;// 是否显示FPS

	private boolean showStatusBar = true;// 是否显示状态栏

	private String luaFilePath = null;

	private String globalGameName = null;

	private long time = 0;

	Emulator emulator = null;

	boolean running;// 运行标识

	int keyX = -1;// 事件的x坐标

	int keyY = -1;// 事件的y坐标

	int keyType = -1;// 事件的类型 : 0按下 1移动 2离开

	private LuaAdapter luaAdapter = null;

	private GameEngine() {
		loadConfig();
	}

	public int getActualFps() {
		return actualFps;
	}

	public int getHeight() {
		return height;
	}

	float getLuaMemory() {
		return luaAdapter.getLuaMemory();
	}

	public int getRatedFps() {
		return ratedFps;
	}

	public int getSystemMilliTime() {
		return (int) (System.currentTimeMillis() - time);
	}

	public int getWidth() {
		return width;
	}

	public boolean isShowFps() {
		return showFps;
	}

	public boolean isShowStatusBar() {
		return showStatusBar;
	}

	/**
	 * 辅助方法：加载配置文件
	 */
	private void loadConfig() {
		try {
			XMLObject emulatorXMLObject = XMLParser.parse(new File(
					"config/emulator.xml"));
			width = Integer.parseInt(emulatorXMLObject.getChild(0).getValue());
			height = Integer.parseInt(emulatorXMLObject.getChild(1).getValue());
			ratedFps = Integer.parseInt(emulatorXMLObject.getChild(2)
					.getValue());
			showStatusBar = Boolean.parseBoolean(emulatorXMLObject.getChild(3)
					.getValue());
			XMLObject gameXMLObject = emulatorXMLObject.getChild(4);
			luaFilePath = gameXMLObject.getChild(0).getValue();
			globalGameName = gameXMLObject.getChild(1).getValue();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按键事件：线程直接调用
	 */
	private void onTouch() {
		if (keyX == -1 || keyY == -1 || keyType == -1) {
			return;
		}
		// 此处调用lua的onTouch(x,y,type)方法
		luaAdapter.onTouch(keyX, keyY, keyType);
		keyX = keyY = keyType = -1;
	}

	/**
	 * 绘图：线程间接调用
	 * 
	 * @param painter
	 */
	void paintGame(Painter painter) {
		// 此处调用lua的paint(painter)方法
		luaAdapter.paint(painter);
	}

	void pause() {

	}

	void resume() {

	}

	@Override
	public void run() {
		time = System.currentTimeMillis();
		// 此处调用lua的onstart()方法
		luaAdapter.onStart();
		while (running) {

			long t = 0;

			while (running) {

				t = System.currentTimeMillis();

				onTouch();// 处理事件

				updateGame();// 更新游戏

				emulator.repaintGame();// 重绘界面

				t = System.currentTimeMillis() - t;

				if (t < 1000 * 1.0 / ratedFps) {
					actualFps = ratedFps;
					try {
						Thread.sleep((long) (1000 * 1.0 / ratedFps - t));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					actualFps = (int) (1000 * 1.0 / t);
				}

			}

			// 此处调用lua的onStop()方法
			luaAdapter.onStop();
		}

	}

	public void setRatedFps(int ratedFps) {
		this.ratedFps = ratedFps;
	}

	public void setShowFps(boolean showFps) {
		this.showFps = showFps;
	}

	void start() {
		luaAdapter = LuaAdapter.newInstance(luaFilePath, globalGameName);
		running = true;
		new Thread(this).start();
	}

	void stop() {
		running = false;
	}

	/**
	 * 更新游戏：线程直接调用
	 */
	private void updateGame() {
		// 此处调用lua的update()方法
		luaAdapter.update();
	}

}
