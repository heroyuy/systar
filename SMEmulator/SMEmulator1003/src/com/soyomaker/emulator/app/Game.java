package com.soyomaker.emulator.app;

import com.soyomaker.emulator.ui.Painter;

public class Game implements IGame, Runnable {

	private LuaAdapter luaAdapter = null;

	private boolean running = false;

	private Event event = null;

	private String inputValue = null;

	private boolean showInputDialog = false;

	private long time = 0;

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
		showInputDialog = false;
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
					luaAdapter.onTouch(event.getX(), event.getY(), event.getType());
					event = null;
				}
				if (!showInputDialog) {
					// 更新游戏
					luaAdapter.update();
					// 重绘界面
					UIScreen.getInstance().requestRepaint();

				}
				// 垃圾收集
				luaAdapter.callLuaGC();
				t = System.currentTimeMillis() - t;
				GameInfo gameInfo = GameInfo.getInstance();
				if (t < 1000 * 1.0 / gameInfo.getRatedFPS()) {
					gameInfo.setActualFPS(gameInfo.getRatedFPS());
					this.sleep((int) (1000 * 1.0 / gameInfo.getRatedFPS() - t));
				} else {
					gameInfo.setActualFPS((int) (1000 * 1.0 / t));
				}
			}
			// 此处调用lua的onStop()方法
			luaAdapter.onStop();
		}
	}

	public void showInputDialog(String tip) {
		showInputDialog = true;
		UIScreen.getInstance().requestRepaint();
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
		luaAdapter = new LuaAdapter(this);
		running = true;
		new Thread(this).start();
	}

	@Override
	public void stop() {
		running = false;
	}

}
