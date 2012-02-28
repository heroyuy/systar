package com.soyomaker.emulator.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import com.soyomaker.emulator.utils.ColorFactory;
import com.soyomaker.plugin.IPlugin;

public class Emulator extends JDialog implements IPlugin {

	private static final long serialVersionUID = -8809949650600479176L;

	private boolean exitWhenStop = false;// 是否在结束游戏时退出程序（作为插件时不退出，作为独立程序时要退出）

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Emulator dialog = new Emulator();
		dialog.exitWhenStop = true;
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		dialog.startGame();
		System.out.println(System.getProperties());
	}

	private GameEngine ge = GameEngine.getInstance();

	private Timer timer = null;

	private Painter painter;
	private JPanel contentPanel = null;

	private JProgressBar progressBar;
	private JLabel labelLuaMemory;
	private JMenuItem menuItemStop;

	/**
	 * Create the dialog.
	 */
	public Emulator() {
		// 大小不可拖动改变
		setResizable(false);
		ge.emulator = this;
		// 标题
		setTitle("SoyoMakerEmulator");
		// 菜单
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("菜单");
		menuBar.add(mnNewMenu);

		menuItemStop = new JMenuItem("停止");
		menuItemStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopGame();
			}
		});
		mnNewMenu.add(menuItemStop);

		// 游戏区域
		contentPanel = new JPanel() {

			private static final long serialVersionUID = 7608812515686704871L;

			public void paint(Graphics g) {
				super.paint(g);
				if (painter == null || painter.getGraphics() != g) {
					painter = new Painter(g);
				}
				// 清屏
				painter.setColor(ColorFactory.getInstance().BLACK);
				painter.fillRect(0, 0, ge.getWidth(), ge.getHeight());
				// 绘制游戏
				ge.setShowFps(true);
				if (ge.running) {
					ge.paintGame(painter);
					// 显示FPS
					if (ge.isShowFps()) {
						painter.setColor(ColorFactory.getInstance().WHITE);
						painter.setTextSize(20);
						painter.drawString("FPS:" + ge.getActualFps() + "", 20,
								ge.getHeight() - 10, Painter.LB);
					}
				}
			}

		};

		// 鼠标事件监听
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				ge.keyX = e.getX();
				ge.keyY = e.getY();
				ge.keyType = GameEngine.TOUCH_TYPE_MOVE;
			}

			public void mousePressed(MouseEvent e) {
				ge.keyX = e.getX();
				ge.keyY = e.getY();
				ge.keyType = GameEngine.TOUCH_TYPE_DOWN;
			}

			public void mouseReleased(MouseEvent e) {
				ge.keyX = e.getX();
				ge.keyY = e.getY();
				ge.keyType = GameEngine.TOUCH_TYPE_UP;
			}
		};
		contentPanel.addMouseListener(mouseAdapter);
		contentPanel.addMouseMotionListener(mouseAdapter);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(ge.getWidth(), ge
				.getHeight()));

		// 状态栏
		if (ge.isShowStatusBar()) {

			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
					null, null));
			panel.setPreferredSize(new Dimension(10, 20));
			panel.setSize(new Dimension(0, 20));
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(null);

			JLabel lblNewLabel = new JLabel("内存占用");
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel.setSize(new Dimension(0, 20));
			lblNewLabel.setPreferredSize(new Dimension(48, 20));
			lblNewLabel.setLocation(new Point(5, 0));
			lblNewLabel.setBounds(10, 0, 54, 15);
			panel.add(lblNewLabel);

			JLabel lblJava = new JLabel("Java:");
			lblJava.setBounds(100, 0, 31, 15);
			panel.add(lblJava);

			progressBar = new JProgressBar();
			progressBar.setBounds(141, 1, 146, 14);
			progressBar.setStringPainted(true);
			panel.add(progressBar);

			JLabel lblLua = new JLabel("Lua:");
			lblLua.setBounds(340, 0, 25, 15);
			panel.add(lblLua);

			labelLuaMemory = new JLabel("0KB");
			labelLuaMemory.setBounds(375, 0, 123, 15);
			panel.add(labelLuaMemory);

			// 更新内存状态
			timer = new Timer(1000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// Lua
					if (ge.running) {
						labelLuaMemory.setText(convertMemoryInfo(ge
								.getLuaMemory()));
					}
					// Java
					long totalMemory = Runtime.getRuntime().totalMemory();
					long freeMemory = Runtime.getRuntime().freeMemory();
					progressBar.setMaximum((int) totalMemory);
					progressBar.setValue((int) (totalMemory - freeMemory));
					progressBar.setString(convertMemoryInfo(totalMemory
							- freeMemory)
							+ "/" + convertMemoryInfo(totalMemory));
				}
			});
			timer.start();
		}

		// 窗口事件监听
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				stopGame();
			}
		});
		pack();
	}

	void repaintGame() {
		contentPanel.paintImmediately(contentPanel.getX(), contentPanel.getY(),
				contentPanel.getWidth(), contentPanel.getHeight());
	}

	/**
	 * 开始游戏,由模拟器调用
	 */
	private void startGame() {
		ge.startByEmulator();
	}

	/**
	 * 停止游戏,由模拟器调用
	 */
	private void stopGame() {
		ge.stopByEmulator();
		this.setVisible(false);
		if (this.exitWhenStop) {
			System.exit(0);
		}
	}

	private String convertMemoryInfo(float memory) {
		String res = "";
		float temp = 0;
		DecimalFormat dcmFmt = new DecimalFormat("0.00");
		if ((temp = memory / 1024 / 1024 / 1024) > 1) {
			res = dcmFmt.format(temp) + "GB";
		} else if ((temp = memory / 1024 / 1024) > 1) {
			res = dcmFmt.format(temp) + "MB";
		} else if ((temp = memory / 1024) > 1) {
			res = dcmFmt.format(temp) + "KB";
		} else {
			res = dcmFmt.format(temp) + "B";
		}
		return res;
	}

	void stopByEngine() {
		this.setVisible(false);
	}

	@Override
	public void start() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.startGame();
	}
}
