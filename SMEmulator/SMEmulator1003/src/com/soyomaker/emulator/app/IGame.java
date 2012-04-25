/**
 * 
 */
package com.soyomaker.emulator.app;

import com.soyomaker.emulator.ui.Painter;

/**
 * @author Administrator
 * 
 */
public interface IGame {

	public void start();

	public void stop();

	public void runLoop();

	public void onEvent(Event e);

	public void onPaint(Painter painter);
}
