package com.soyomaker.emulator.app;

import com.soyomaker.emulator.net.NetTransceiver;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.utils.SMLog;

public class Game implements IGame, Runnable {

	private LuaAdapter luaAdapter = null;

	private boolean running = false;

	private Event event = null;

	private String inputValue = null;

	private long time = 0;

	public int getHeight() {
		return GameConfig.getInstance().getHeight();
	}

	public String getPath() {
		return GameConfig.getInstance().getGamePath();
	}

	public int getTime() {
		return (int) (System.currentTimeMillis() - time);
	}

	public int getWidth() {
		return GameConfig.getInstance().getWidth();
	}

	public boolean isRunning() {
		return running;
	}

	@Override
	public void onEvent(Event e) {
		this.event = e;
	}

	@Override
	public void onInput(String value) {
		this.inputValue = value;
	}

	@Override
	public void onLowMemory() {
		System.gc();
		luaAdapter.onLowMemory();
	}

	@Override
	public void onPaint(Painter painter) {
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
				// 处理事件
				if (inputValue != null) {
					luaAdapter.onInput(inputValue);
					inputValue = null;
				}
				if (event != null) {
					luaAdapter.onTouch(event);
					event = null;
				}
				// 更新游戏
				luaAdapter.update();
				// 重绘界面
				UIScreen.getInstance().requestRepaint();
				t = System.currentTimeMillis() - t;
				GameConfig gameConfig = GameConfig.getInstance();
				if (t < 1000 * 1.0 / gameConfig.getRatedFPS()) {
					gameConfig.setActualFPS(gameConfig.getRatedFPS());
					this.sleep((int) (1000 * 1.0 / gameConfig.getRatedFPS() - t));
				} else {
					gameConfig.setActualFPS((int) (1000 * 1.0 / t));
					if (gameConfig.getActualFPS() < gameConfig.getRatedFPS()) {
						SMLog.getInstance().info("FPS警告:" + gameConfig.getActualFPS());
					}
				}
			}
			// 此处调用lua的onStop()方法
			luaAdapter.onStop();
		}
	}

	public void showInputDialog(String tip) {
		UIScreen.getInstance().showInputDialog(tip);
	}

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		// 启动网络模块
		NetTransceiver.getInstance().start();
		// 初始化Lua适配器
		luaAdapter = new LuaAdapter(this);
		// 启动游戏线程
		running = true;
		new Thread(this).start();
	}

	@Override
	public void stop() {
		running = false;
		// 停止网络模块
		NetTransceiver.getInstance().stop();
	}

}
