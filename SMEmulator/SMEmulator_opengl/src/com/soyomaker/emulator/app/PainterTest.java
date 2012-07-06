package com.soyomaker.emulator.app;

import com.soyomaker.emulator.ui.Color;
import com.soyomaker.emulator.ui.Painter;

public class PainterTest {

	public void test(Painter painter) {
		painter.setColor(Color.RED);
		painter.drawLine(100, 100, 250, 250);
		painter.drawLine(100, 250, 250, 100);
	}

}
