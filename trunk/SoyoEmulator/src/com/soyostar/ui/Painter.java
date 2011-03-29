package com.soyostar.ui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 绘图类，相当于android的Canvas+Paint,j2me和swing中的Graphics
 * 
 * @author Administrator
 * 
 */
public class Painter {

	public static final byte LT = 0;

	public static final byte LV = 1;

	public static final byte LB = 2;

	public static final byte HT = 3;

	public static final byte HV = 4;

	public static final byte HB = 5;

	public static final byte RT = 6;

	public static final byte RV = 7;

	public static final byte RB = 8;

	private Graphics g = null;

	protected void setGraphics(Graphics g) {
		this.g = g;
	}
//
//	protected Painter(Graphics g) {
//		this.g = g;
//	}
//	
	protected Painter() {
	}

	public void setColor(Color color) {
		g.setColor(color);
	}

	public void fillRect(int x, int y, int width, int height) {
		g.fillRect(x, y, width, height);
	}

	public void fillRect(Rect rect) {
		fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}

	// public void drawString(String str, int x, int y, int anchor) {
	// if (anchor < LT || anchor > RB) {
	// throw new ArithmeticException("参数anchor的值错误");
	// }
	//
	// android.graphics.Rect r = new android.graphics.Rect();
	// paint.getTextBounds(str, 0, str.length(), r);
	// float h = r.height();
	// switch (anchor) {
	// case LT:
	// paint.setTextAlign(Align.LEFT);
	// canvas.drawText(str, x, y + h, paint);
	// break;
	// case LV:
	// paint.setTextAlign(Align.LEFT);
	// canvas.drawText(str, x, y + h / 2, paint);
	// break;
	// case LB:
	// paint.setTextAlign(Align.LEFT);
	// canvas.drawText(str, x, y, paint);
	// break;
	// case HT:
	// paint.setTextAlign(Align.CENTER);
	// canvas.drawText(str, x, y + h, paint);
	// break;
	// case HV:
	// paint.setTextAlign(Align.CENTER);
	// canvas.drawText(str, x, y + h / 2, paint);
	// break;
	// case HB:
	// paint.setTextAlign(Align.CENTER);
	// canvas.drawText(str, x, y, paint);
	// break;
	// case RT:
	// paint.setTextAlign(Align.RIGHT);
	// canvas.drawText(str, x, y + h, paint);
	// break;
	// case RV:
	// paint.setTextAlign(Align.RIGHT);
	// canvas.drawText(str, x, y + h / 2, paint);
	// break;
	// case RB:
	// paint.setTextAlign(Align.RIGHT);
	// canvas.drawText(str, x, y, paint);
	// break;
	// }
	// }
	public void drawLine(int startX, int startY, int stopX, int stopY) {
		g.drawLine(startX, startY, stopX, stopY);
	}

	public void drawLine(Line line) {
		g.drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(),
				line.getEndPoint().getX(), line.getEndPoint().getY());
	}
}
