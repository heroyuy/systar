package com.soyomaker.emulator.app;

import com.soyomaker.emulator.ui.Painter;

public class Game implements IGame, Runnable {

	private LuaAdapter luaAdapter = null;

	private boolean running = false;

	private Event event = null;

	private long time = 0;

	private boolean needDisplay = true;

	public int getHeight() {
		return GameInfo.getInstance().getHeight();
	}

	public String getPath() {
		return GameInfo.getInstance().getGamePath();
	}

	public int getRunningTime() {
		return (int) (System.currentTimeMillis() - time);
	}

	public int getWidth() {
		return GameInfo.getInstance().getWidth();
	}

	public boolean isNeedDisplay() {
		return needDisplay;
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
		luaAdapter.onInput(value);
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
				if (event != null) {
					luaAdapter.onTouch(event.getX(), event.getY(),
							event.getType());
					event = null;
				}
				// 更新游戏
				luaAdapter.update();

				// 重绘界面
				if (isNeedDisplay()) {
					UIScreen.getInstance().requestRepaint();
				}

				// 垃圾收集
				luaAdapter.callLuaGC();

				t = System.currentTimeMillis() - t;
				GameInfo gameInfo = GameInfo.getInstance();
				if (t < 1000 * 1.0 / gameInfo.getRatedFps()) {
					gameInfo.setActualFps(gameInfo.getRatedFps());
					this.sleep((int) (1000 * 1.0 / gameInfo.getRatedFps() - t));
				} else {
					gameInfo.setActualFps((int) (1000 * 1.0 / t));
				}

			}

			// 此处调用lua的onStop()方法
			luaAdapter.onStop();
		}
	}

	public void setNeedDisplay(boolean needDisplay) {
		this.needDisplay = needDisplay;
	}

	public void showInputDialog() {
		UIScreen.getInstance().showInputDialog(true);
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
		luaAdapter = new LuaAdapter(this);
		running = true;
		new Thread(this).start();
	}

	@Override
	public void stop() {
		running = false;
	}

}
