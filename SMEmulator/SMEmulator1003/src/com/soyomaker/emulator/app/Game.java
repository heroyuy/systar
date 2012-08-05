package com.soyomaker.emulator.app;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soyomaker.emulator.net.IHandler;
import com.soyomaker.emulator.net.NetTransceiver;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.util.Net;
import com.soyomaker.emulator.util.SMLog;
import com.soyomaker.lang.GameObject;

public class Game implements IGame, Runnable, IHandler {

	private LuaAdapter luaAdapter = null;

	private boolean running = false;

	private Queue<Event> eventQueue = null;

	private String inputValue = null;

	private long time = 0;

	private Queue<GameObject> messageQueue = null;

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
		this.eventQueue.offer(e);
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
				while (eventQueue.size() > 0) {
					luaAdapter.onTouch(eventQueue.poll());
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
		// 初始化
		eventQueue = new ConcurrentLinkedQueue<Event>();
		messageQueue = new ConcurrentLinkedQueue<GameObject>();
		luaAdapter = new LuaAdapter(this);
		// 启动网络模块
		NetTransceiver.getInstance().setHandler(this);
		NetTransceiver.getInstance().start();
		Net.getInstance().register("test", "test");
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

	@Override
	public void onMessageReceived(GameObject msg) {
		messageQueue.offer(msg);
	}

}
