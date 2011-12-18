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

public class Emulator extends JDialog {

	private static final long serialVersionUID = -8809949650600479176L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Emulator dialog = new Emulator();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private GameEngine ge = GameEngine.getInstance();

	private Timer timer = null;

	private Painter painter;
	private JPanel contentPanel = null;

	private JProgressBar progressBar;
	private JLabel labelLuaMemory;
	private JMenuItem menuItemRun;
	private JMenuItem menuItemStop;

	/**
	 * Create the dialog.
	 */
	public Emulator() {
		setResizable(false);
		ge.emulator = this;
		setTitle("SoyoMakerEmulator");
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

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("菜单");
		menuBar.add(mnNewMenu);

		menuItemRun = new JMenuItem("运行");
		menuItemRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItemRun.setEnabled(false);
				startGame();
				menuItemStop.setEnabled(true);
			}
		});
		mnNewMenu.add(menuItemRun);

		menuItemStop = new JMenuItem("停止");
		menuItemStop.setEnabled(false);
		menuItemStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItemStop.setEnabled(false);
				stopGame();
				menuItemRun.setEnabled(true);
			}
		});
		mnNewMenu.add(menuItemStop);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				stopGame();
				System.exit(0);
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
		this.repaintGame();// 清屏
		if (ge.isShowStatusBar()) {
			labelLuaMemory.setText("0KB");
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
		menuItemStop.setEnabled(false);
		menuItemRun.setEnabled(true);
	}
}
