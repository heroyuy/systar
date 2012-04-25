package com.soyomaker.emulator.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.utils.ColorFactory;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 7608812515686704871L;

	private static GamePanel instance = new GamePanel();

	public static GamePanel getInstance() {
		return instance;
	}

	private Graphics g = null;

	private Painter painter = null;

	private IGame game = null;

	private GamePanel() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(Config.getInstance().getWidth(),
				Config.getInstance().getHeight()));
		// 鼠标事件监听
		MouseAdapter mouseAdapter = new MouseAdapter() {

			public void mouseDragged(MouseEvent e) {
				dealEvent(new Event(e.getX(), e.getY(), Event.EVENT_TYPE_MOVE));
			}

			public void mousePressed(MouseEvent e) {
				dealEvent(new Event(e.getX(), e.getY(), Event.EVENT_TYPE_DOWN));
			}

			public void mouseReleased(MouseEvent e) {
				dealEvent(new Event(e.getX(), e.getY(), Event.EVENT_TYPE_UP));
			}
		};
		this.addMouseMotionListener(mouseAdapter);
		this.addMouseListener(mouseAdapter);
	}

	private void dealEvent(Event e) {
		game.onEvent(e);
	}

	public IGame getGame() {
		return game;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (painter == null || this.g != g) {
			painter = new Painter(g);
		}
		// 清屏
		painter.setColor(ColorFactory.getInstance().BLACK);
		painter.fillRect(0, 0, getWidth(), getHeight());
		// 绘制游戏
		game.onPaint(painter);
	}

	public void setGame(IGame game) {
		this.game = game;
	}
}
