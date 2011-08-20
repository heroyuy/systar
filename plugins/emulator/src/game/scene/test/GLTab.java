/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.test;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.ImageUtils;
import com.soyostar.app.Painter;
import com.soyostar.app.Widget;
import com.soyostar.app.event.TouchEvent;

/**
 * 
 * @author Administrator
 */
public class GLTab extends Widget {

	private Image content = null;
	private Image line = null;
	private Image devide = null;
	private Image selected0 = null;
	private Image selected1 = null;
	private Image selected2 = null;
	private Image unselected0 = null;
	private Image unselected1 = null;
	private Image unselected2 = null;
	private int w = 0;
	private int h = 0;
	private String[] titles = null;
	private int selectedIndex = 0;
	private int oldX = 0;
	private int oldY = 0;

	public GLTab(int w, int h, String[] titles) {
		content = new Image("res/image/test/tab/content.png");
		line = new Image("res/image/test/tab/line.png");
		devide = new Image("res/image/test/tab/devide.png");
		selected0 = new Image("res/image/test/tab/selected0.png");
		selected1 = new Image("res/image/test/tab/selected1.png");
		selected2 = new Image("res/image/test/tab/selected2.png");
		unselected0 = new Image("res/image/test/tab/unselected0.png");
		unselected1 = new Image("res/image/test/tab/unselected1.png");
		unselected2 = new Image("res/image/test/tab/unselected2.png");
		this.w = w;
		this.h = h;
		this.setSize(w, h);
		this.titles = titles;
		line = ImageUtils.getScaledImage(line, w, line.getHeight());
		content = ImageUtils.getScaledImage(content, w,
				h - selected0.getHeight());
	}

	@Override
	public void paint(Painter painter) {
		super.paint(painter);
		painter.setColor(Color.WHITE);
		painter.setTextSize(18);
		// title
		for (int i = 0; i < titles.length; i++) {
			if (i == 0) {
				if (selectedIndex == i) {
					painter.drawImage(selected0, i * selected0.getWidth(), 0,
							Painter.LT);
				} else {
					painter.drawImage(unselected0, i * selected0.getWidth(), 0,
							Painter.LT);
				}
			} else if (i == titles.length - 1) {
				if (selectedIndex == i) {
					painter.drawImage(selected2, i * selected0.getWidth(), 0,
							Painter.LT);
				} else {
					painter.drawImage(unselected2, i * selected0.getWidth(), 0,
							Painter.LT);
				}
			} else {
				if (selectedIndex == i) {
					painter.drawImage(selected1, i * selected0.getWidth(), 0,
							Painter.LT);
				} else {
					painter.drawImage(unselected1, i * selected0.getWidth(), 0,
							Painter.LT);
				}
			}
			// 竖向分隔线
			if (i != 0) {
				painter.drawImage(devide, i * selected0.getWidth(), 0,
						Painter.LT);
			}
			// 标题文字
			painter.drawString(titles[i],
					i * selected0.getWidth() + selected0.getWidth() / 2,
					selected0.getHeight() / 2, Painter.HV);
		}
		// 内容面板
		painter.drawImage(content, 0, selected0.getHeight(), Painter.LT);
		// 横向分隔线
		painter.drawImage(line, 0, selected0.getHeight(), Painter.LT);

	}

	@Override
	public boolean onTouchEvent(TouchEvent te) {
		if (te.getY() < selected0.getHeight()) {
			// 事件发生在标题上
			switch (te.getType()) {
			case TouchEvent.TOUCH_DOWN: {
				for (int i = 0; i < titles.length; i++) {
					if (te.getX() >= i * selected0.getWidth()
							&& te.getX() < (i + 1) * selected0.getWidth()) {
						selectedIndex = i;
						break;
					}
				}
				oldX = te.getX();
				oldY = te.getY();
			}
				break;
			default: {
				int offsetX = te.getX() - oldX;
				int offsetY = te.getY() - oldY;
				this.setLocation(this.getX() + offsetX, this.getY() + offsetY);
				oldX = te.getX();
				oldY = te.getY();
			}
				break;
			}
		}
		return true;
	}
}
