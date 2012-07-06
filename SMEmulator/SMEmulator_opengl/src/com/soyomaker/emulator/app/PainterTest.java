package com.soyomaker.emulator.app;

import com.soyomaker.emulator.ui.Color;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.ui.Rect;

public class PainterTest {

	public void test(Painter painter) {
		// 绘制直线
		painter.setColor(Color.RED);
		painter.drawLine(100, 100, 250, 250);
		painter.drawLine(100, 250, 250, 100);
		// 绘制字符串
		painter.setColor(new Color("0xff333333"));
		painter.drawString("我是中国人", 100, 100);
		// 绘制rect
		painter.drawRect(new Rect(100, 200, 300, 400));
		// 填充rect
		painter.setColor(Color.BLUE);
		painter.fillRect(new Rect(150, 250, 200, 300));
	}

}
