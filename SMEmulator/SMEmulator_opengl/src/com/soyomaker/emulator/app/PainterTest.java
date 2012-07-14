package com.soyomaker.emulator.app;

import java.util.ArrayList;
import java.util.List;

import com.soyomaker.emulator.ui.Color;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.ui.Rect;
import com.soyomaker.emulator.ui.Texture;

public class PainterTest {

	private List<Texture> list = new ArrayList<Texture>();

	private Texture title = new Texture("game/image/title/001-Title01.jpg");

	public PainterTest() {
		// list.add(new Texture("game/image/title/001-Title01.jpg"));
		// list.add(new Texture("game/image/title/title.png"));
		// list.add(new Texture("game/image/face/关平.png"));
		// list.add(new Texture("game/image/battle/forest_autumn.png"));
		// list.add(new Texture("game/image/battle/forest_winter.png"));
		// list.add(new Texture("game/image/battle/hill_spring.png"));
		//
		// for (int i = 1; i < 49; i++) {
		// String name = "game/image/tileset/" + i + "号墙壁.png";
		// list.add(new Texture(name));
		// }
		Painter p = title.createPainter();
		p.setColor(Color.CYAN);
		p.fillRect(10, 10, 200, 100);
//		title.disposePainter();
//		p = title.createPainter();
		p.setColor(Color.BLUE);
		p.drawString("FBO测试", 0, 0);
		title.disposePainter();
	}

	public void test(Painter painter) {
		// 绘制直线
		painter.setColor(Color.GREEN);
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
			painter.drawTexture(list.get(i), i * 10, i * 10);
		}
		painter.setColor(Color.WHITE);
		// 再次绘图
		painter.drawTexture(title, 100, 100);
		// 再次绘制字符串
		painter.setColor(Color.BLUE);
		painter.drawString("我是123wp_g4", 500, 500);
	}

}
