package com.soyomaker.emulator.core;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;
import org.xml.sax.SAXException;

import com.soyomaker.emulator.api.ImageFactory;
import com.soyomaker.emulator.api.SMLog;
import com.soyostar.xml.NoSuchXMLObjectException;
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

	private boolean showFps = true;// 是否显示FPS

	private long time = 0;

	Emulator emulator = null;

	boolean running;// 运行标识

	int keyX = -1;// 事件的x坐标

	int keyY = -1;// 事件的y坐标

	int keyType = -1;// 事件的类型 : 0按下 1移动 2离开

	private LuaState luaState = null;

	private GameEngine() {
		loadConfig();
		luaState = LuaStateFactory.newLuaState();
		luaState.openLibs();
	}

	public int getActualFps() {
		return actualFps;
	}

	public int getHeight() {
		return height;
	}

	public int getRatedFps() {
		return ratedFps;
	}

	public int getWidth() {
		return width;
	}

	public int getSystemMilliTime() {
		return (int) (System.currentTimeMillis() - time);
	}

	public boolean isShowFps() {
		return showFps;
	}

	/**
	 * 辅助方法：加载配置文件
	 */
	private void loadConfig() {
		try {
			XMLObject config = XMLParser.parse(new File("config/emulator.xml"));
			width = config.getFirstXMLObject("width").getIntValue();
			height = config.getFirstXMLObject("height").getIntValue();
			ratedFps = config.getFirstXMLObject("fps").getIntValue();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchXMLObjectException e) {
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
		luaState.getGlobal("onTouch");
		try {
			luaState.pushObjectValue(keyX);
			luaState.pushObjectValue(keyY);
			luaState.pushObjectValue(keyType);
		} catch (LuaException e) {
			e.printStackTrace();
		}
		luaState.call(3, 0);
		keyX = keyY = keyType = -1;
	}

	/**
	 * 绘图：线程间接调用
	 * 
	 * @param painter
	 */
	void paintGame(Painter painter) {
		// 此处调用lua的paint(painter)方法
		luaState.getGlobal("paint");
		try {
			luaState.pushObjectValue(painter);
		} catch (LuaException e) {
			e.printStackTrace();
		}
		luaState.call(1, 0);
	}

	void pause() {

	}

	private void registerMethods() {
		try {

			// --注册GameEngine
			luaState.pushObjectValue(this);
			luaState.setGlobal("smGameEngine");

			// --注册Random
			luaState.pushObjectValue(new Random());
			luaState.setGlobal("smRandom");

			// --注册SMLog
			luaState.pushObjectValue(new SMLog());
			luaState.setGlobal("smLog");

			// --注册ImageFactory
			luaState.pushObjectValue(new ImageFactory());
			luaState.setGlobal("smImageFactory");

		} catch (LuaException e) {
			e.printStackTrace();
		}
	}

	void resume() {

	}

	@Override
	public void run() {
		time = System.currentTimeMillis();
		// 此处调用lua的onstart()方法
		luaState.getGlobal("onStart");
		luaState.call(0, 0);
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
			luaState.getGlobal("onStop");
			luaState.call(0, 0);
		}

	}

	public void setRatedFps(int ratedFps) {
		this.ratedFps = ratedFps;
	}

	public void setShowFps(boolean showFps) {
		this.showFps = showFps;
	}

	void start() {
		running = true;
		registerMethods();
		luaState.LdoFile("game/smscript/game.lua");
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
		luaState.getGlobal("update");
		luaState.call(0, 0);
	}

}
