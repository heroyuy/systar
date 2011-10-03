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
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.xml.sax.SAXException;

import com.soyomaker.emulator.api.ApiManager;
import com.soyostar.xml.NoSuchXMLObjectException;
import com.soyostar.xml.XMLObject;
import com.soyostar.xml.XMLParser;

public class Emulator extends JDialog {

	// 自定义数据开始

	public static final int TOUCH_TYPE_DOWN = 0;

	public static final int TOUCH_TYPE_MOVE = 1;

	public static final int TOUCH_TYPE_UP = 2;

	private Painter painter = null;

	private ApiManager apiManager = ApiManager.getInstance();

	private LuaState luaState = apiManager.getLuaState();

	private int width = 0;// 屏幕宽度

	private int height = 0;// 屏幕高度

	private boolean running = false;

	private boolean updated = false;

	private int fps = 0;// FPS

	private int keyX = -1;// 事件的x坐标

	private int keyY = -1;// 事件的y坐标

	private int keyType = -1;// 事件的类型 : 0按下 1移动 2离开

	// 自定义数据结束

	private static final long serialVersionUID = -8809949650600479176L;

	private JPanel contentPanel = null;

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

	/**
	 * Create the dialog.
	 */
	public Emulator() {
		loadConfig();
		ApiManager.getInstance().getLuaState().LdoFile("game/smscript/game.lua");
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
				if (updated) {
					Emulator.this.paintGame(painter);
					updated = false;
				}
			}

		};
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				keyX = e.getX();
				keyY = e.getY();
				keyType = TOUCH_TYPE_DOWN;
			}

			public void mouseDragged(MouseEvent e) {
				keyX = e.getX();
				keyY = e.getY();
				keyType = TOUCH_TYPE_MOVE;
			}

			public void mouseReleased(MouseEvent e) {
				keyX = e.getX();
				keyY = e.getY();
				keyType = TOUCH_TYPE_UP;
			}
		};
		contentPanel.addMouseListener(mouseAdapter);
		contentPanel.addMouseMotionListener(mouseAdapter);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(width, height));
		pack();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("菜单");
		menuBar.add(mnNewMenu);

		JMenuItem menuItem = new JMenuItem("运行");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				running = false;// 确保结束已经启动的线程
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

	/**
	 * 开始游戏,由模拟器调用
	 */
	private void startGame() {
		// 此处调用lua的onstart()方法
		luaState.getGlobal("onStart");
		luaState.call(0, 0);
		running = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (running) {

					onTouch();// 处理事件

					updateGame();// 更新游戏

					contentPanel.repaint();// 重绘界面

					try {
						Thread.sleep((long) (1000 * 1.0 / fps));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 此处调用lua的onStop()方法
				luaState.getGlobal("onStop");
				luaState.call(0, 0);
			}
		}).start();
	}

	/**
	 * 按键事件：线程直接调用
	 */
	private void onTouch() {
		if (keyX == -1 || keyY == -1 || keyType == -1) {
			return;
		}
		// 此处调用lua的onTouch(x,y,type)方法
		luaState.getGlobal("onTouch");
		try {
			luaState.pushObjectValue(keyX);
			luaState.pushObjectValue(keyY);
			luaState.pushObjectValue(keyType);
		} catch (LuaException e) {
			e.printStackTrace();
		}
		luaState.call(3, 0);
		keyX = keyY = keyType = -1;
	}

	/**
	 * 更新游戏：线程直接调用
	 */
	private void updateGame() {
		// 此处调用lua的update()方法
		luaState.getGlobal("update");
		luaState.call(0, 0);
		updated = true;
	}

	/**
	 * 绘图：线程间接调用
	 * 
	 * @param painter
	 */
	private void paintGame(Painter painter) {
		// 此处调用lua的paint(painter)方法
		luaState.getGlobal("paint");
		try {
			luaState.pushObjectValue(painter);
		} catch (LuaException e) {
			e.printStackTrace();
		}
		luaState.call(1, 0);
	}

	/**
	 * 停止游戏,由模拟器调用
	 */
	private void stopGame() {
		running = false;
	}

	/**
	 * 辅助方法：加载配置文件
	 */
	private void loadConfig() {
		try {
			XMLObject config = XMLParser.parse(new File("config/emulator.xml"));
			width = config.getFirstXMLObject("width").getIntValue();
			height = config.getFirstXMLObject("height").getIntValue();
			fps = config.getFirstXMLObject("fps").getIntValue();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchXMLObjectException e) {
			e.printStackTrace();
		}
	}
}
