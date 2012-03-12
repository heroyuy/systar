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

	Emulator emulator = null;

	boolean running;// 运行标识

	int keyX = -1;// 事件的x坐标

	int keyY = -1;// 事件的y坐标

	int keyType = -1;// 事件的类型 : 0按下 1移动 2离开

	private LuaAdapter luaAdapter = null;

	private GameEngine() {
	}

	/**
	 * 垃圾收集：线程直接调用
	 */
	private void gc() {
		luaAdapter.callLuaGC();
	}

	public int getActualFps() {
		return actualFps;
	}

	public String getGamePath() {
		return gamePath;
	}

	public int getHeight() {
		return emulator.getScreenHeight();
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
		return emulator.getScreenWidth();
	}

	public boolean isShowFps() {
		return showFps;
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

	public void setGamePath(String gamePath) {
		this.gamePath = gamePath;
	}

	public void setRatedFps(int ratedFps) {
		this.ratedFps = ratedFps;
	}

	public void setShowFps(boolean showFps) {
		this.showFps = showFps;
	}

	void startByEmulator() {
		luaAdapter = LuaAdapter.newInstance(getGamePath() + GAME_FILE,
				GAME_NAME);
		running = true;
		new Thread(this).start();
	}

	/**
	 * stopByLua 游戏内停止游戏
	 */
	public void stop() {
		emulator.stopByEngine();
		running = false;
	}

	void stopByEmulator() {
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
