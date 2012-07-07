package com.soyomaker.emulator.app;

import java.util.ArrayList;
import java.util.List;

import com.soyomaker.emulator.ui.Color;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.ui.Rect;
import com.soyomaker.emulator.ui.Texture2D;

public class PainterTest {

	private List<Texture2D> list = new ArrayList<Texture2D>();

	public PainterTest() {
		list.add(new Texture2D("game/image/title/001-Title01.jpg"));
		list.add(new Texture2D("game/image/title/title.png"));
		list.add(new Texture2D("game/image/face/关平.png"));
		list.add(new Texture2D("game/image/battle/forest_autumn.png"));
		list.add(new Texture2D("game/image/battle/forest_winter.png"));
		list.add(new Texture2D("game/image/battle/hill_spring.png"));
		for (int i = 1; i < 2; i++) {
			// list.add(new Texture2D("game/image/tileset/" + i + "号墙壁.png"));
		}
	}

	public void test(Painter painter) {

		// 绘制直线
		painter.setColor(Color.BLACK);
		painter.drawLine(100, 100, 250, 250);
		painter.drawLine(100, 250, 250, 100);
		// 绘制字符串
		painter.setColor(new Color("0xff00ff00"));
		painter.drawString("我是中国人", 100, 100);
		// 绘制rect
		painter.drawRect(new Rect(100, 200, 300, 400));
		// 填充rect
		painter.setColor(Color.BLUE);
		painter.fillRect(new Rect(150, 250, 200, 300));
		// 绘制纹理
		for (int i = 0; i < list.size(); i++) {
			painter.drawTexture2D(list.get(i), -200, -500);
		}
		// 再次绘制字符串
		painter.drawString("我是123wp_g4", 500, 500);

	}

}
