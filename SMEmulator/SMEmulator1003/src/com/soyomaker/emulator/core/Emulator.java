package com.soyomaker.emulator.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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

	private JPanel contentPanel = null;

	private Painter painter = null;

	/**
	 * Create the dialog.
	 */
	public Emulator() {
		ge.emulator = this;
		setResizable(false);
		setTitle("SoyoMakerEmulator");
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel() {

			private static final long serialVersionUID = 7608812515686704871L;

			public void paint(Graphics g) {
				super.paint(g);
				if (painter == null || painter.getGraphics() != g) {
					painter = new Painter(g);
				}
				if (ge.running) {
					ge.paintGame(painter);
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
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(ge.getWidth(), ge
				.getHeight()));
		pack();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("菜单");
		menuBar.add(mnNewMenu);

		JMenuItem menuItem = new JMenuItem("运行");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		mnNewMenu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("停止");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopGame();
			}
		});
		mnNewMenu.add(menuItem_1);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				stopGame();
			}
		});
	}

	void repaintGame() {
		contentPanel.repaint();
	}

	/**
	 * 开始游戏,由模拟器调用
	 */
	private void startGame() {
		ge.start();
	}

	/**
	 * 停止游戏,由模拟器调用
	 */
	private void stopGame() {
		ge.stop();
	}

}
