package com.soyomaker.emulator.app;

import com.soyomaker.emulator.ui.Painter;

public class Game implements IGame {

	@Override
	public void onEvent(Event e) {
		System.out.println("onEvent" + e);
		UIScreen.getInstance().showInputDialog(true);
	}

	@Override
	public void onInput(String value) {
		System.out.println("onInput:" + value);

	}

	@Override
	public void onPaint(Painter painter) {
		System.out.println("onPaint:" + painter);

	}

	@Override
	public void start() {
		System.out.println("start");
	}

	@Override
	public void stop() {
		System.out.println("stop");

	}

}
