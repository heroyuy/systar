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

	public void onEvent(Event e);

	public void onInput(String value);

	public void onKey(String value);
	
	public void onLowMemory();

	public void onPaint(Painter painter);

	public void start();

	public void stop();
}
