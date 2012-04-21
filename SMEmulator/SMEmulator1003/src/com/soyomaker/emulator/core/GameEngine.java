package com.soyomaker.emulator.core;

public class GameEngine implements Runnable {

	public static final int TOUCH_TYPE_DOWN = 0;

	public static final int TOUCH_TYPE_MOVE = 1;

	public static final int TOUCH_TYPE_UP = 2;

	private static GameEngine instance = new GameEngine();

	public static GameEngine getInstance() {
		return instance;
	}

	private int ratedFps = 20;// 额定fps

	private int actualFps = 0;// 实际fps

	private boolean showFps = false;// 是否显示FPS

	private String gamePath = null;

	private final String GAME_FILE = "/smscript/game.smlua";

	private String GAME_NAME = "globalGame";

	private long time = 0;

	private Emulator emulator = null;

	private boolean running;// 运行标识

	private int keyX = -1;// 事件的x坐标

	private int keyY = -1;// 事件的y坐标

	private int keyType = -1;// 事件的类型 : 0按下 1移动 2离开

	private LuaAdapter luaAdapter = null;

	private GameEngine() {
	}

	public int getActualFps() {
		return actualFps;
	}

	public String getGamePath() {
		return gamePath;
	}

	public int getWidth() {
		return emulator.getScreenWidth();
	}

	public int getHeight() {
		return emulator.getScreenHeight();
	}

	public int getRatedFps() {
		return ratedFps;
	}

	public int getTime() {
		return (int) (System.currentTimeMillis() - time);
	}

	public boolean isShowFps() {
		return showFps;
	}

	public void setRatedFps(int ratedFps) {
		this.ratedFps = ratedFps;
	}

	public void setShowFps(boolean showFps) {
		this.showFps = showFps;
	}

	public boolean isRunning() {
		return running;
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

				this.gc();// 垃圾收集

				t = System.currentTimeMillis() - t;

				if (t < 1000 * 1.0 / ratedFps) {
					actualFps = ratedFps;
					this.sleep((int) (1000 * 1.0 / ratedFps - t));
				} else {
					actualFps = (int) (1000 * 1.0 / t);
				}

			}

			// 此处调用lua的onStop()方法
			luaAdapter.onStop();
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
	protected void paintGame(Painter painter) {
		// 此处调用lua的paint(painter)方法
		luaAdapter.paint(painter);
	}

	protected void setGamePath(String gamePath) {
		this.gamePath = gamePath;
	}

	protected void start() {
		luaAdapter = LuaAdapter.getInstance();
		luaAdapter.init(getGamePath() + GAME_FILE, GAME_NAME);
		running = true;
		new Thread(this).start();
	}

	protected void stop() {
		running = false;
	}

	protected float getLuaMemory() {
		return luaAdapter.getLuaMemory();
	}

	protected void setEmulator(Emulator emulator) {
		this.emulator = emulator;
	}

	protected void setKeyX(int keyX) {
		this.keyX = keyX;
	}

	protected void setKeyY(int keyY) {
		this.keyY = keyY;
	}

	protected void setKeyType(int keyType) {
		this.keyType = keyType;
	}

	/**
	 * 更新游戏：线程直接调用
	 */
	private void updateGame() {
		// 此处调用lua的update()方法
		luaAdapter.update();
	}

	/**
	 * 垃圾收集：线程直接调用
	 */
	private void gc() {
		luaAdapter.callLuaGC();
	}

}
