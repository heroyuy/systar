package emulator;

import engine.GameEngine;
import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Emulator extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JDialog keyPadDialog = null; // @jve:decl-index=0:visual-constraint="356,5"
    private JPanel jContentPane1 = null;
    private KeyButton jButton_ls = null;
    private KeyButton jButton_rs = null;
    private KeyButton jButton_left = null;
    private KeyButton jButton_right = null;
    private KeyButton jButton_up = null;
    private KeyButton jButton_mid = null;
    private KeyButton jButton_down = null;
    private KeyButton jButton_1 = null;
    private KeyButton jButton_2 = null;
    private KeyButton jButton_3 = null;
    private KeyButton jButton_4 = null;
    private KeyButton jButton_5 = null;
    private KeyButton jButton_6 = null;
    private KeyButton jButton_7 = null;
    private KeyButton jButton_8 = null;
    private KeyButton jButton_9 = null;
    private KeyButton jButton_0 = null;
    private KeyButton jButton_star = null;
    private KeyButton jButton_pound = null;
    private JMenuBar jJMenuBar = null;
    private JMenu jMenu = null;
    private JMenuItem jMenuItem = null;
    private JMenuItem jMenuItem1 = null;
    private Canvas canvas = null;// 模拟器渲染层
    private JDialog optionDialog = null; // @jve:decl-index=0:visual-constraint="703,18"
    private JPanel jContentPane2 = null;
    private JButton jButton = null;
    private JButton jButton1 = null;
    private JLabel jLabel = null;
    private JLabel jLabel1 = null;
    private JTextField jTextField_width = null;
    private JTextField jTextField_height = null;
    private GameEngine ge = null;

    /**
     * @param owner
     */
    public Emulator(Frame owner) {
        super(owner);
        initialize();
    }

    public void start() {
        // 启动游戏引擎
        if (ge == null) {
            ge = GameEngine.getInstance();
            ge.setEmulator(this);
            ge.start();
        }
    }

    private void stop() {
        // 停止游戏引擎
        ge.exit();
    }

    /**
     * This method initializes jButton_0
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_0() {
        if (jButton_0 == null) {
            jButton_0 = new KeyButton(this, KeyValue.KEY_0);
            jButton_0.setText("0");
            jButton_0.setSize(new Dimension(50, 30));
            jButton_0.setLocation(new Point(40, 300));
        }
        return jButton_0;
    }

    /**
     * This method initializes jButton_1
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_1() {
        if (jButton_1 == null) {
            jButton_1 = new KeyButton(this, KeyValue.KEY_1);
            jButton_1.setText("1");
            jButton_1.setLocation(new Point(40, 150));
            jButton_1.setSize(new Dimension(50, 30));
        }
        return jButton_1;
    }

    /**
     * This method initializes jButton_2
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_2() {
        if (jButton_2 == null) {
            jButton_2 = new KeyButton(this, KeyValue.KEY_2);
            jButton_2.setText("2");
            jButton_2.setSize(new Dimension(50, 30));
            jButton_2.setLocation(new Point(115, 150));
        }
        return jButton_2;
    }

    /**
     * This method initializes jButton_3
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_3() {
        if (jButton_3 == null) {
            jButton_3 = new KeyButton(this, KeyValue.KEY_3);
            jButton_3.setText("3");
            jButton_3.setSize(new Dimension(50, 30));
            jButton_3.setLocation(new Point(190, 150));
        }
        return jButton_3;
    }

    /**
     * This method initializes jButton_4
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_4() {
        if (jButton_4 == null) {
            jButton_4 = new KeyButton(this, KeyValue.KEY_4);
            jButton_4.setText("4");
            jButton_4.setSize(new Dimension(50, 30));
            jButton_4.setLocation(new Point(40, 200));
        }
        return jButton_4;
    }

    /**
     * This method initializes jButton_5
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_5() {
        if (jButton_5 == null) {
            jButton_5 = new KeyButton(this, KeyValue.KEY_5);
            jButton_5.setText("5");
            jButton_5.setSize(new Dimension(50, 30));
            jButton_5.setLocation(new Point(115, 200));
        }
        return jButton_5;
    }

    /**
     * This method initializes jButton_6
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_6() {
        if (jButton_6 == null) {
            jButton_6 = new KeyButton(this, KeyValue.KEY_6);
            jButton_6.setText("6");
            jButton_6.setSize(new Dimension(50, 30));
            jButton_6.setLocation(new Point(190, 200));
        }
        return jButton_6;
    }

    /**
     * This method initializes jButton_7
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_7() {
        if (jButton_7 == null) {
            jButton_7 = new KeyButton(this, KeyValue.KEY_7);
            jButton_7.setText("7");
            jButton_7.setSize(new Dimension(50, 30));
            jButton_7.setLocation(new Point(40, 250));
        }
        return jButton_7;
    }

    /**
     * This method initializes jButton_8
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_8() {
        if (jButton_8 == null) {
            jButton_8 = new KeyButton(this, KeyValue.KEY_8);
            jButton_8.setText("8");
            jButton_8.setSize(new Dimension(50, 30));
            jButton_8.setLocation(new Point(115, 250));
        }
        return jButton_8;
    }

    /**
     * This method initializes jButton_9
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_9() {
        if (jButton_9 == null) {
            jButton_9 = new KeyButton(this, KeyValue.KEY_9);
            jButton_9.setText("9");
            jButton_9.setSize(new Dimension(50, 30));
            jButton_9.setLocation(new Point(190, 250));
        }
        return jButton_9;
    }

    /**
     * This method initializes jButton_down
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_down() {
        if (jButton_down == null) {
            jButton_down = new KeyButton(this, KeyValue.KEY_DOWN);
            jButton_down.setText("下");
            jButton_down.setSize(new Dimension(50, 20));
            jButton_down.setLocation(new Point(115, 110));
        }
        return jButton_down;
    }

    /**
     * This method initializes jButton_left
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_left() {
        if (jButton_left == null) {
            jButton_left = new KeyButton(this, KeyValue.KEY_LEFT);
            jButton_left.setText("左");
            jButton_left.setLocation(new Point(40, 80));
            jButton_left.setSize(new Dimension(50, 20));
        }
        return jButton_left;
    }

    /**
     * This method initializes jButton_ls
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_ls() {
        if (jButton_ls == null) {
            jButton_ls = new KeyButton(this, KeyValue.KEY_LS);
            jButton_ls.setText("左软键");
            jButton_ls.setLocation(new Point(20, 20));
            jButton_ls.setSize(new Dimension(80, 30));
        }
        return jButton_ls;
    }

    /**
     * This method initializes jButton_mid
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_mid() {
        if (jButton_mid == null) {
            jButton_mid = new KeyButton(this, KeyValue.KEY_FIRE);
            jButton_mid.setPreferredSize(new Dimension(60, 20));
            jButton_mid.setLocation(new Point(115, 80));
            jButton_mid.setSize(new Dimension(50, 20));
            jButton_mid.setMnemonic(KeyEvent.VK_UNDEFINED);
            jButton_mid.setText("中");
        }
        return jButton_mid;
    }

    /**
     * This method initializes jButton_pound
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_pound() {
        if (jButton_pound == null) {
            jButton_pound = new KeyButton(this, KeyValue.KEY_POUND);
            jButton_pound.setText("#");
            jButton_pound.setSize(new Dimension(50, 30));
            jButton_pound.setLocation(new Point(190, 300));
        }
        return jButton_pound;
    }

    /**
     * This method initializes jButton_right
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_right() {
        if (jButton_right == null) {
            jButton_right = new KeyButton(this, KeyValue.KEY_RIGHT);
            jButton_right.setText("右");
            jButton_right.setSize(new Dimension(50, 20));
            jButton_right.setLocation(new Point(190, 80));
        }
        return jButton_right;
    }

    /**
     * This method initializes jButton_rs
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_rs() {
        if (jButton_rs == null) {
            jButton_rs = new KeyButton(this, KeyValue.KEY_RS);
            jButton_rs.setText("右软键");
            jButton_rs.setSize(new Dimension(80, 30));
            jButton_rs.setLocation(new Point(180, 20));
        }
        return jButton_rs;
    }

    /**
     * This method initializes jButton_star
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_star() {
        if (jButton_star == null) {
            jButton_star = new KeyButton(this, KeyValue.KEY_STAR);
            jButton_star.setText("*");
            jButton_star.setSize(new Dimension(50, 30));
            jButton_star.setLocation(new Point(115, 300));
        }
        return jButton_star;
    }

    /**
     * This method initializes jButton_up
     *
     * @return javax.swing.JButton
     */
    private KeyButton getJButton_up() {
        if (jButton_up == null) {
            jButton_up = new KeyButton(this, KeyValue.KEY_UP);
            jButton_up.setText("上");
            jButton_up.setSize(new Dimension(50, 20));
            jButton_up.setLocation(new Point(115, 50));
        }
        return jButton_up;
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
        }
        return jContentPane;
    }

    /**
     * This method initializes jContentPane1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane1() {
        if (jContentPane1 == null) {
            jContentPane1 = new JPanel();
            jContentPane1.setLayout(null);
            jContentPane1.add(getJButton_ls(), null);
            jContentPane1.add(getJButton_rs(), null);
            jContentPane1.add(getJButton_left(), null);
            jContentPane1.add(getJButton_right(), null);
            jContentPane1.add(getJButton_up(), null);
            jContentPane1.add(getJButton_mid(), null);
            jContentPane1.add(getJButton_down(), null);
            jContentPane1.add(getJButton_1(), null);
            jContentPane1.add(getJButton_2(), null);
            jContentPane1.add(getJButton_3(), null);
            jContentPane1.add(getJButton_4(), null);
            jContentPane1.add(getJButton_5(), null);
            jContentPane1.add(getJButton_6(), null);
            jContentPane1.add(getJButton_7(), null);
            jContentPane1.add(getJButton_8(), null);
            jContentPane1.add(getJButton_9(), null);
            jContentPane1.add(getJButton_0(), null);
            jContentPane1.add(getJButton_star(), null);
            jContentPane1.add(getJButton_pound(), null);
        }
        return jContentPane1;
    }

    /**
     * This method initializes keyPadDialog
     *
     * @return javax.swing.JDialog
     */
    private JDialog getKeyPadDialog() {
        if (keyPadDialog == null) {
            keyPadDialog = new JDialog(this);
            keyPadDialog.setSize(new Dimension(300, 385));
            keyPadDialog.setTitle("键盘");
            keyPadDialog.setResizable(false);
            keyPadDialog.setContentPane(getJContentPane1());
        }
        return keyPadDialog;
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(305, 375);
        this.setJMenuBar(getJJMenuBar());
        this.setResizable(false);
        this.setTitle("模拟器");
        this.setContentPane(getJContentPane());
    }

    /**
     * This method initializes jJMenuBar
     *
     * @return javax.swing.JMenuBar
     */
    private JMenuBar getJJMenuBar() {
        if (jJMenuBar == null) {
            jJMenuBar = new JMenuBar();
            jJMenuBar.add(getJMenu());
        }
        return jJMenuBar;
    }

    /**
     * This method initializes jMenu
     *
     * @return javax.swing.JMenu
     */
    private JMenu getJMenu() {
        if (jMenu == null) {
            jMenu = new JMenu();
            jMenu.setText("菜单");
            jMenu.add(getJMenuItem());
            jMenu.add(getJMenuItem1());
        }
        return jMenu;
    }

    /**
     * This method initializes jMenuItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem() {
        if (jMenuItem == null) {
            jMenuItem = new JMenuItem();
            jMenuItem.setText("选项");
            jMenuItem.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getOptionDialog().setVisible(true);
                }
            });
        }
        return jMenuItem;
    }

    /**
     * This method initializes jMenuItem1
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem1() {
        if (jMenuItem1 == null) {
            jMenuItem1 = new JMenuItem();
            jMenuItem1.setText("键盘");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getKeyPadDialog().setLocation(getX() + getWidth() + 10,
                            getY());
                    getKeyPadDialog().setVisible(true);
                }
            });
        }
        return jMenuItem1;
    }

    public void setCanvas(Canvas canvas) {
        System.out.println("setCanvas");
        this.canvas = canvas;
        setEmulatorSize(240, 320);
        getContentPane().add(canvas, 0, 0);
    }

    /**
     * 设置模拟器的大小
     */
    private void setEmulatorSize(int width, int height) {
        this.setSize(width + 5, height + 50);

        canvas.setSize(width, height);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * This method initializes optionDialog
     *
     * @return javax.swing.JDialog
     */
    private JDialog getOptionDialog() {
        if (optionDialog == null) {
            optionDialog = new JDialog(this);
            optionDialog.setSize(new Dimension(216, 174));
            optionDialog.setTitle("选项");
            optionDialog.setContentPane(getJContentPane2());
        }
        return optionDialog;
    }

    /**
     * This method initializes jContentPane2
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane2() {
        if (jContentPane2 == null) {
            jLabel1 = new JLabel();
            jLabel1.setText("高度：");
            jLabel1.setSize(new Dimension(40, 20));
            jLabel1.setLocation(new Point(20, 60));
            jLabel = new JLabel();
            jLabel.setText("宽度：");
            jLabel.setLocation(new Point(20, 20));
            jLabel.setSize(new Dimension(40, 20));
            jContentPane2 = new JPanel();
            jContentPane2.setLayout(null);
            jContentPane2.add(getJButton(), null);
            jContentPane2.add(getJButton1(), null);
            jContentPane2.add(jLabel, null);
            jContentPane2.add(jLabel1, null);
            jContentPane2.add(getJTextField_width(), null);
            jContentPane2.add(getJTextField_height(), null);
        }
        return jContentPane2;
    }

    /**
     * This method initializes jButton
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setLocation(new Point(30, 100));
            jButton.setText("确定");
            jButton.setSize(new Dimension(60, 20));
            jButton.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    int w = Integer.parseInt(jTextField_width.getText());
                    int h = Integer.parseInt(jTextField_height.getText());
                    setEmulatorSize(w, h);
                    getOptionDialog().setVisible(false);
                }
            });
        }
        return jButton;
    }

    /**
     * This method initializes jButton1
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton1() {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setLocation(new Point(110, 100));
            jButton1.setText("取消");
            jButton1.setSize(new Dimension(60, 20));
            jButton1.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getOptionDialog().setVisible(false);
                }
            });
        }
        return jButton1;
    }

    /**
     * This method initializes jTextField_width
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_width() {
        if (jTextField_width == null) {
            jTextField_width = new JTextField();
            jTextField_width.setSize(new Dimension(120, 20));
            jTextField_width.setLocation(new Point(60, 20));
        }
        return jTextField_width;
    }

    /**
     * This method initializes jTextField_height
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField_height() {
        if (jTextField_height == null) {
            jTextField_height = new JTextField();
            jTextField_height.setSize(new Dimension(120, 20));
            jTextField_height.setLocation(new Point(60, 60));
        }
        return jTextField_height;
    }

    public void exit() {
        setVisible(false);
    }
} // @jve:decl-index=0:visual-constraint="10,10"

