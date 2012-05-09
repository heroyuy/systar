package com.soyomaker.emulator.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.soyomaker.emulator.app.InputDialog.InputListener;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.utils.ColorFactory;

public class UIScreen extends JPanel implements InputListener {

	private static final long serialVersionUID = 7608812515686704871L;

	private static UIScreen instance = new UIScreen();

	public static UIScreen getInstance() {
		return instance;
	}

	private IGame game = null;

	private JPanel gamePanel = null;

	private InputDialog inputDialog = null;

	private UIScreen() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(GameInfo.getInstance().getWidth(),
				GameInfo.getInstance().getHeight()));
		// 游戏渲染层
		gamePanel = new JPanel() {

			private static final long serialVersionUID = 645901037817876536L;

			private Graphics graphics = null;

			private Painter painter = null;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (this.painter == null || this.graphics != g) {
					this.painter = new Painter((Graphics2D) g);
				}
				// 绘制游戏
				game.onPaint(this.painter);
				// FPS
				GameInfo gameInfo = GameInfo.getInstance();
				if (gameInfo.isShowFPS()) {
					painter.setColor(ColorFactory.getInstance().WHITE);
					painter.drawString("FPS:" + gameInfo.getActualFps(), 10,
							gameInfo.getHeight() - 10, Painter.LB);
				}
			}
		};
		gamePanel.setBackground(Color.black);
		// 鼠标事件监听
		MouseAdapter mouseAdapter = new MouseAdapter() {

			public void mouseDragged(MouseEvent e) {
				if (inputDialog.isVisible()) {
					return;
				}
				game.onEvent(new Event(e.getX(), e.getY(),
						Event.EVENT_TYPE_MOVE));
			}

			public void mousePressed(MouseEvent e) {
				if (inputDialog.isVisible()) {
					return;
				}
				game.onEvent(new Event(e.getX(), e.getY(),
						Event.EVENT_TYPE_DOWN));
			}

			public void mouseReleased(MouseEvent e) {
				if (inputDialog.isVisible()) {
					return;
				}
				game.onEvent(new Event(e.getX(), e.getY(), Event.EVENT_TYPE_UP));
			}
		};
		gamePanel.addMouseMotionListener(mouseAdapter);
		gamePanel.addMouseListener(mouseAdapter);
		gamePanel.setSize(new Dimension(GameInfo.getInstance().getWidth(),
				GameInfo.getInstance().getHeight()));
		gamePanel.setLocation(0, 0);
		gamePanel.setLayout(null);
		this.add(gamePanel);

		inputDialog = new InputDialog(GameInfo.getInstance().getWidth(), 100);
		inputDialog.setLocation(0,
				gamePanel.getHeight() - inputDialog.getHeight());
		inputDialog.setVisible(false);
		inputDialog.setInputListener(this);
		gamePanel.add(inputDialog);
	}

	@Override
	public void onInput(String value) {
		inputDialog.setVisible(false);
		this.game.onInput(value);
	}

	public void requestRepaint() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					gamePanel.repaint();
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	public void setGame(IGame game) {
		this.game = game;
	}

	public void showInputDialog(String tip) {
		inputDialog.setTip(tip);
		inputDialog.setContent("");
		inputDialog.setVisible(true);
	}
}
