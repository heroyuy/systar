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
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.soyomaker.AppData;
import com.soyomaker.emulator.utils.ColorFactory;
import com.soyomaker.plugin.IPlugin;
import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

public class Emulator extends JDialog implements IPlugin {

	private static final long serialVersionUID = -8809949650600479176L;

	private static String CONFIG_PATH = "plugin/emulator/config/emulator.xml";

	private static String DEFAULT_GAME_PATH = "./game";

	public static final int TYPE_SOFTWARE = 0; // 独立程序

	public static final int TYPE_PLUGIN = 1; // 插件

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Emulator emulator = new Emulator();
		emulator.setType(TYPE_SOFTWARE);
		GameEngine ge = GameEngine.getInstance();
		ge.emulator = emulator;
		ge.setGamePath(DEFAULT_GAME_PATH);
		emulator.setVisible(true);
		emulator.startGame();
	}

	private int type = TYPE_SOFTWARE; // 模拟器的启动类型

	private int screenWidth = 480; // 屏幕宽度(如果配置不存在则默认为480)

	private int screenHeight = 320; // 屏幕高度(如果配置不存在则默认为320)

	private boolean showStatusBar = true;// 是否显示状态栏

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
		// super(AppData.getInstance().getMainFrame(), true);
		// 读取配置
		loadConfig();
		// 初始化
		init();
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

	public int getScreenHeight() {
		return screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getType() {
		return type;
	}

	private void init() {
		// 大小不可拖动改变
		setResizable(false);
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
				painter.fillRect(0, 0, getScreenWidth(), getScreenHeight());
				// 绘制游戏
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
		contentPanel.setPreferredSize(new Dimension(getScreenWidth(),
				getScreenHeight()));

		// 状态栏
		if (isShowStatusBar()) {
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
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				stopGame();
			}
		});
		pack();

		// 居中
		setLocationRelativeTo(null);
	}

	public boolean isShowStatusBar() {
		return showStatusBar;
	}

	/**
	 * 辅助方法：加载配置文件
	 */
	private void loadConfig() {
		try {
			XMLObject emulatorXMLObject = XMLParser
					.parse(new File(CONFIG_PATH));
			int width = Integer.parseInt(emulatorXMLObject.getChild(0)
					.getValue());
			int height = Integer.parseInt(emulatorXMLObject.getChild(1)
					.getValue());
			boolean showStatusBar = Boolean.parseBoolean(emulatorXMLObject
					.getChild(2).getValue());
			// 设置屏幕宽度
			setScreenWidth(width);
			// 设置屏幕高度
			setScreenHeight(height);
			// 设置是否显示状态栏
			setShowStatusBar(showStatusBar);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void repaintGame() {
		contentPanel.paintImmediately(contentPanel.getX(), contentPanel.getY(),
				contentPanel.getWidth(), contentPanel.getHeight());
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public void setShowStatusBar(boolean showStatusBar) {
		this.showStatusBar = showStatusBar;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public void start() {
		if (AppData.getInstance().getCurProject() == null) {
			JOptionPane.showMessageDialog(this, "请先打开工程");
		} else {
			ge.emulator = this;
			ge.setGamePath(AppData.getInstance().getCurProject().getPath());
			this.setType(TYPE_PLUGIN);
			this.setModal(true);
			this.startGame();
			this.setVisible(true);
		}
	}

	/**
	 * 开始游戏,由模拟器调用
	 */
	private void startGame() {
		ge.startByEmulator();
	}

	void stopByEngine() {
		this.setVisible(false);
	}

	/**
	 * 停止游戏,由模拟器调用
	 */
	private void stopGame() {
		ge.stopByEmulator();
		this.setVisible(false);
		if (type == TYPE_SOFTWARE) {
			System.exit(0);
		}
	}
}
