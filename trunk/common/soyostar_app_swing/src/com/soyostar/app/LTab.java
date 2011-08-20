/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.app;

import com.soyostar.app.event.TouchEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 */
public class LTab extends Widget {

	private List<TabContent> tabList = new ArrayList<TabContent>();
	private Image[] titleBackground = new Image[3];// 依次是未选中、选中和禁用的背景
	private int titleWidth = 60;
	private int titleHeight = 30;
	private int selectedIndex = 0;
	private int titleTextColor = Color.BLACK;
	private int titleTextSize = 12;

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public void addTab(String title, Widget tabWidget) {
		tabList.add(new TabContent(title, tabWidget));
	}

	public void removeTab(int index) {
		tabList.remove(index);
	}

	public void setTitleSize(int titleWidth, int titleHeight) {
		if (titleWidth != this.titleWidth || titleHeight != this.titleHeight) {
			this.titleWidth = titleWidth;
			this.titleHeight = titleHeight;
			updateTitleBackground();
		}
	}

	public int getTitleHeight() {
		return titleHeight;
	}

	public void setTitleHeight(int titleHeight) {
		if (titleHeight != this.titleHeight) {
			this.titleHeight = titleHeight;
			updateTitleBackground();
		}
	}

	public int getTitleWidth() {
		return titleWidth;
	}

	public void setTitleWidth(int titleWidth) {
		if (titleWidth != this.titleWidth) {
			this.titleWidth = titleWidth;
			updateTitleBackground();
		}
	}

	public void setTitleBackground(Image unSelected, Image selected,
			Image disabled) {
		titleBackground[0] = unSelected;
		titleBackground[1] = selected;
		titleBackground[2] = disabled;
	}

	@Override
	public void paint(Painter painter) {
		super.paint(painter);
		// 绘制title
		painter.setColor(titleTextColor);
		painter.setTextSize(titleTextSize);
		for (int i = 0; i < tabList.size(); i++) {
			if (i == selectedIndex) {
				painter.drawImage(titleBackground[1], i * titleWidth, 0,
						Painter.LT);
			} else {
				painter.drawImage(titleBackground[0], i * titleWidth, 0,
						Painter.LT);
			}
			painter.drawString(tabList.get(i).title, i * titleWidth
					+ titleWidth / 2, titleHeight / 2, Painter.HV);
		}
		// 绘制内容区域
		Widget contentWidget = tabList.get(selectedIndex).tabWidget;
		if (contentWidget.isVisible()) {

			// 设置坐标系
			((GraphicsPainter) painter).setBasePoint(0, titleHeight);
			// 设置裁剪区
			Rect clip = painter.getClip();
			((GraphicsPainter) painter).clipRect(0, 0,
					contentWidget.getWidth(), contentWidget.getHeight());
			((GraphicsPainter) painter).setCurClip(painter.getClip());
			contentWidget.paintWidget(painter);
			// 还原裁剪区
			((GraphicsPainter) painter).forceClip(clip);
			// 还原坐标系
			((GraphicsPainter) painter).setBasePoint(0, -titleHeight);
		}
	}

	@Override
	public boolean dispatchTouchEvent(TouchEvent te) {
		if (!isEnabled()) {
			return false;
		}
		boolean state = false;
		int tex = te.getX();
		int tey = te.getY();

		if (tey <= titleHeight) {
			// 如果事件发生在title区
			if (tex <= (tabList.size() + 1) * titleWidth) {
				// 事件发生在title上
				if (te.getType() == TouchEvent.TOUCH_DOWN) {
					for (int i = 0; i < tabList.size(); i++) {
						if (tex >= i * titleWidth && tex < (i + 1) * titleWidth) {
							selectedIndex = i;
							break;
						}
					}
				}

				state = true;
			} else {
				// 事件发生在title区的，但不在title上
				state = false;
			}

		} else {
			// 事件发生在内容区
			Widget tabWidget = tabList.get(selectedIndex).tabWidget;
			tabWidget.dispatchTouchEvent(new TouchEvent(te.getX()
					- tabWidget.getX(), te.getY() - titleHeight
					- tabWidget.getY(), te.getType()));
			state = true;
		}
		return state;
	}

	private void updateTitleBackground() {
		for (int i = 0; i < titleBackground.length; i++) {
			if (titleBackground[i] != null) {
				titleBackground[i].scale(titleWidth, titleHeight);
			}
		}
	}

	private class TabContent {

		public TabContent(String title, Widget tabWidget) {
			this.title = title;
			this.tabWidget = tabWidget;
		}

		public String title = null;
		public Widget tabWidget = null;
	}
}
