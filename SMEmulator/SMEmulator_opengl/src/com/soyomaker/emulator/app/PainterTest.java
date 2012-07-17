package com.soyomaker.emulator.app;

import java.util.ArrayList;
import java.util.List;

import com.soyomaker.emulator.ui.Color;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.ui.Rect;
import com.soyomaker.emulator.ui.Texture;

public class PainterTest {

	private List<Texture> list = new ArrayList<Texture>();
	private List<Texture> iconList = new ArrayList<Texture>();

	private Texture title = new Texture("game/image/title/title.png");
	private Texture skin = new Texture("game/image/skin/Window.png");
	private Texture body = null;

	public PainterTest() {
		list.add(new Texture("game/image/face/关平.png"));
		list.add(new Texture("game/image/title/title.png"));
		list.add(new Texture("game/image/battle/forest_autumn.png"));
		list.add(new Texture("game/image/battle/forest_winter.png"));
		list.add(new Texture("game/image/battle/hill_spring.png"));

		for (int i = 1; i < 49; i++) {
			String name = "game/image/tileset/" + i + "号墙壁.png";
			list.add(new Texture(name));
		}

		for (int i = 10; i < 63; i++) {
			iconList.add(new Texture("game/image/icon/equip/IconSet_" + i + ".png"));
		}
		body = skin.subTexture(0, 0, 128, 128);
	}

	public void test(Painter painter) {
		// 绘制直线
		painter.setColor(Color.GREEN);
		painter.drawLine(100, 100, 250, 250);
		painter.drawLine(100, 250, 250, 100);
		// 绘制字符串
		painter.setColor(Color.colorFromARGB("0xff00ff00"));
		painter.drawString("我是中国人", 100, 100, Painter.LT);
		// 绘制rect
		painter.drawRect(new Rect(100, 200, 300, 400));
		// 填充rect
		painter.setColor(Color.BLUE);
		painter.fillRect(new Rect(150, 250, 200, 300));
		// 绘制纹理
		for (int i = 0; i < list.size(); i++) {
			painter.drawTexture(list.get(i), i * 10, i * 10, Painter.LT);
		}
		for (int i = 0; i < iconList.size(); i++) {
			painter.drawTexture(iconList.get(i), i % 8 * 32, i / 8 * 32, Painter.LT);
		}
		painter.setColor(Color.WHITE);
		painter.drawTexture(skin, 0, 0, Painter.LT);
		painter.drawTexture(body, 128, 0, Painter.LT);
		// 再次绘图
		painter.clipRect(240, 160, 480, 320);
		painter.drawTexture(title, 0, 0, Painter.LT);
		painter.clipRect(0, 0, 960, 640);
		// 再次绘制字符串
		painter.setColor(Color.BLUE);
		painter.drawString("我是123wp_g4", 500, 500, Painter.LT);
	}

}
