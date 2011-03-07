/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.*;
import java.awt.image.BufferedImage;
import java.io.File;
import model.*;
import module.*;

public class AnimationMaker extends JDialog implements MouseListener {

    public class EditorArea extends JPanel implements MouseListener, Runnable, KeyListener {

        private static final long serialVersionUID = 1L;
        private volatile boolean isPlay = false;// 是否处于播放动画状态
        private Image image = null;// 当前帧的图片
        private int curIndex = 0;//播放时的当前帧号
        private Animation ani = null;// 当前动画

        public EditorArea() {
            super();

            this.setFocusable(true);
            addMouseListener(this);
            addKeyListener(this);
            new Thread(this).start();

        }

        public void keyReleased(KeyEvent e) {
//            System.out.println(e.getKeyChar());
        }

        public void keyPressed(KeyEvent e) {
//            System.out.println(e.getKeyChar());
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    ani.getCurFrame().offsetY--;
//                    System.out.println("up");
                    break;
                case KeyEvent.VK_LEFT:
                    ani.getCurFrame().offsetX--;
//                    System.out.println("left");
                    break;
                case KeyEvent.VK_RIGHT:
                    ani.getCurFrame().offsetX++;
//                    System.out.println("right");
                    break;
                case KeyEvent.VK_DOWN:
                    ani.getCurFrame().offsetY++;
//                    System.out.println("down");
                    break;

            }
            this.update();

        }

        public void keyTyped(KeyEvent e) {
//            System.out.println(e.getKeyChar());
        }

        public void mouseClicked(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void mouseEntered(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void mouseExited(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void mousePressed(MouseEvent e) {
            if (ani == null) {
                return;
            }
            image = ani.getCurFrame().image;
            ani.getCurFrame().offsetX = e.getX() - getWidth() / 2;
            ani.getCurFrame().offsetY = e.getY() - getHeight() / 2;
            repaint();
        }

        public void mouseReleased(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.white);
            g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());// X轴
            g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);// Y轴
            if (isPlay) {
//                if (ani.getFrame(curIndex) == null) {
//                    System.out.println("frame null " + curIndex);
//                }

                if (ani.getFrame(curIndex) != null) {

                    g.drawImage(image, getWidth() / 2 - ani.frameWidth / 2
                        + ani.getFrame(curIndex).offsetX, getHeight() / 2 - ani.frameHeight / 2
                        + ani.getFrame(curIndex).offsetY, null);
                }
            } else {
                if (ani == null || ani.getCurFrame() == null || ani.getCurFrame().image == null) {
                    return;
                }
                g.drawImage(image, getWidth() / 2 - ani.frameWidth / 2
                    + ani.getCurFrame().offsetX, getHeight() / 2 - ani.frameHeight / 2
                    + ani.getCurFrame().offsetY, null);

            }
        }

        public void update() {
            ani = aniManager.getCurAnimation();
            if (ani == null) {
                return;
            }
//            if (ani.getCurFrame().num != 0) {
//                if (ani.getCurFrame().image == null) {
//                    System.out.println("image null");
//                    Image curImage = new BufferedImage(ani.frameWidth, ani.frameHeight,
//                        BufferedImage.TYPE_INT_ARGB);
//                    Graphics g = curImage.getGraphics();
//                    int picw = ani.image.getWidth(null) / ani.frameWidth;
//                    g.drawImage(ani.image, 0, 0, ani.frameWidth, ani.frameHeight, (ani.getCurFrame().num - 1) % picw * ani.frameWidth,
//                        (ani.getCurFrame().num - 1) / picw * ani.frameHeight, (ani.getCurFrame().num - 1) % picw * ani.frameWidth + ani.frameWidth, (ani.getCurFrame().num - 1) / picw * ani.frameHeight + ani.frameHeight,
//                        null);
//                    ani.getCurFrame().image = curImage;
//                }
//            }
            image = ani.getCurFrame().image;
            repaint();
//            System.out.println("EditorArea.update");
        }

        public void play() {
            isPlay = true;
        }

        public void run() {
            try {
                while (true) {
                    if (isPlay) {
                        if (ani == null) {
                            isPlay = false;
//                            System.out.println("isPlay:" + isPlay);
                            return;
                        }
                        image = ani.getFrame(curIndex).image;
//                        repaint();
                        curIndex++;
                        if (curIndex >= ani.frameNum) {
                            curIndex = 0;
                            isPlay = false;
                        }
                        repaint();//bug已修正
                        Thread.sleep(80);//帧播放间隔为50ms
//                        if (curIndex >= ani.frameNum) {
//                            isPlay = false;
////                            Thread.sleep(500);//一个动作播放完后，停止500ms重新播放
//                            curIndex = 0;
////                            repaint();//bug已修正
////                            Thread.sleep(100);//帧播放间隔为50ms
//                        } else {
//                            image = ani.getFrame(curIndex).image;
//                            repaint();
//                            curIndex++;
//                            Thread.sleep(50);//帧播放间隔为50ms
//                            repaint();//bug已修正
//                        }
                    } else {
                        Thread.sleep(80);
                    }
                }
            } catch (InterruptedException e) {
                // TODO 自动生成 catch 块
                e.printStackTrace();
            }
        }
    }

    public class SourceLabel extends JLabel implements MouseListener {

        private static final long serialVersionUID = 1L;
        private Image curImage = null;// 临时图片
        private int curNum = 0;// 临时变量
        private Image image = null;// 动画源图片
        private Animation ani = null;// 当前动画
        private int startX = 0;
        private int startY = 0;

        public SourceLabel() {
            super();
            addMouseListener(this);
        }

        public void mouseClicked(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void mouseEntered(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void mouseExited(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void mousePressed(MouseEvent e) {
            if (ani == null) {
                return;
            }
            int x = e.getX();
            int y = e.getY();
            if (x > image.getWidth(null) || y > image.getHeight(null)) {
                return;//非图片范围内，不做处理
            }
            int row = y / ani.frameHeight;
            int col = x / ani.frameWidth;
            startX = col * ani.frameWidth;
            startY = row * ani.frameHeight;
            curNum = row
                * (new ImageIcon(image).getIconWidth() / ani.frameWidth)
                + col + 1;
            curImage = new BufferedImage(ani.frameWidth, ani.frameHeight,
                BufferedImage.TYPE_INT_ARGB);
            Graphics g = curImage.getGraphics();
            g.drawImage(image, 0, 0, ani.frameWidth, ani.frameHeight, startX,
                startY, startX + ani.frameWidth, startY + ani.frameHeight,
                null);

            System.out.println(ani.getCurFrame());
            ani.getCurFrame().image = curImage;
            ani.getCurFrame().num = curNum;
            repaint();
        }
//        public void getImage(){
//
//        }

        public void mouseReleased(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void paint(Graphics g) {
            if (ani == null) {
                return;
            }
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(image, 0, 0, null);
            g.setColor(Color.BLUE);
            g.drawRect(startX, startY, ani.frameWidth, ani.frameHeight);
            g.setColor(Color.GREEN);
            g.drawRect(startX - 1, startY - 1, ani.frameWidth + 2,
                ani.frameHeight + 2);

        }

        public void update() {
            ani = aniManager.getCurAnimation();
            if (ani == null) {
                return;
            }
            image = ani.image;
            setIcon(new ImageIcon(ani.image));
            startX = startY = 0;
            repaint();
        }
    }
    private static final long serialVersionUID = 1L;
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO 自动生成方法存根
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				AnimationMaker thisClass = new AnimationMaker();
//				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				thisClass.setVisible(true);
//			}
//		});
//	}
    private AnimationManager aniManager = AnimationManager.getInstance(); // @jve:decl-index=0:
//    private JPanel jContentPane = null;
//    private JButton jButton = null;
//    private JDialog jDialog = null; // @jve:decl-index=0:visual-constraint="710,33"
    private JPanel jContentPane1 = null;
    private JLabel jLabel = null;
    private JScrollPane jScrollPane = null;
    private JTable jTable = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel3 = null;
    private JTextField jTextField = null;
    private JComboBox jComboBox = null;
    private JComboBox jComboBox1 = null;
    private JLabel jLabel4 = null;
    private JLabel jLabel5 = null;
    private JTextField jTextField1 = null;
    private JTextField jTextField2 = null;
    private JScrollPane jScrollPane1 = null;
    private JTable jTable1 = null;
    private JButton jButton1 = null;
    private JButton jButton2 = null;
    private JLabel jLabel8 = null;
    private JButton jButton3 = null;
    private JButton jButton4 = null;
    private JButton jButton5 = null;
    private JPopupMenu jPopupMenu = null; // @jve:decl-index=0:visual-constraint="22,314"
    private JMenuItem jMenuItem = null;
    private JMenuItem jMenuItem1 = null;
    private JPopupMenu jPopupMenu1 = null; // @jve:decl-index=0:visual-constraint="127,313"
    private JMenuItem jMenuItem2 = null;
    private JMenuItem jMenuItem3 = null;
    private JDialog jDialog1 = null; // @jve:decl-index=0:visual-constraint="23,360"
    private JPanel jContentPane2 = null;
    private JLabel jLabel9 = null;
    private JTextField jTextField3 = null;
    private JButton jButton6 = null;
    private JButton jButton7 = null;
    private JDialog jDialog2 = null; // @jve:decl-index=0:visual-constraint="287,360"
    private JPanel jContentPane3 = null;
    private JLabel jLabel10 = null;
    private JLabel jLabel11 = null;
    private JTextField jTextField4 = null;
    private JTextField jTextField5 = null;
    private JButton jButton8 = null;
    private JButton jButton9 = null;
    private DefaultTableModel aniDTM = null;
    private DefaultTableModel frameDTM = null;
    private JDialog jDialog3 = null; // @jve:decl-index=0:visual-constraint="1400,32"
    private JPanel jContentPane4 = null;
    private JLabel jLabel1 = null;
    private JButton jButton10 = null;
    private JButton jButton11 = null;
    private String gamePath = Project.getProjectPath();  //  @jve:decl-index=0:
    private JLabel jLabel13 = null;
    private JLabel jLabel14 = null;
    private JLabel jLabel15 = null;
    private JLabel jLabel16 = null;
    private JLabel jLabel17 = null;
    private JLabel jLabel18 = null;
    private JLabel jLabel12 = null;
    private JTextField jTextField6 = null;
    private JScrollPane jScrollPane2 = null;
    private AnimationMaker.SourceLabel sourceLabel = null;
    private JPanel jPanel = null;
    private EditorArea editorArea = null;

    /**
     * This is the default constructor
     */
    public AnimationMaker() {
        super();
        initialize();
    }

    /**
     * This method initializes editorArea
     *
     * @return AnimationMaker.EditorArea
     */
    private EditorArea getEditorArea() {
        if (editorArea == null) {
            editorArea = new EditorArea();
        }
        return editorArea;
    }

    /**
     * This method initializes jButton
     *
     * @return javax.swing.JButton
     */
//    private JButton getJButton() {
//        if (jButton == null) {
//            jButton = new JButton();
//            jButton.setText("打开动画编辑器");
//            jButton.setSize(new Dimension(150, 30));
//            jButton.setLocation(new Point(130, 85));
//            jButton.addActionListener(new java.awt.event.ActionListener() {
//
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    getJDialog().setVisible(true);
//                }
//            });
//        }
//        return jButton;
//    }
    /**
     * This method initializes jButton1
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton1() {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setText("补帧");
            jButton1.setLocation(new Point(580, 110));
            jButton1.setPreferredSize(new Dimension(60, 2));
            jButton1.setSize(new Dimension(60, 20));
            jButton1.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog2().setVisible(true);
                }
            });
        }
        return jButton1;
    }

    /**
     * This method initializes jButton10
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton10() {
        if (jButton10 == null) {
            jButton10 = new JButton();
            jButton10.setText("确定");
            jButton10.setSize(new Dimension(60, 20));
            jButton10.setLocation(new Point(40, 210));
            jButton10.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    String name = jTextField.getText();
                    int frameNum = Integer.parseInt(jTextField6.getText());
                    int frameWidth = Integer.parseInt(jTextField1.getText());
                    int frameHeight = Integer.parseInt(jTextField2.getText());
                    String imageName = (String) jComboBox1.getSelectedItem();
                    String soundName = (String) jComboBox.getSelectedItem();
                    aniManager.addAnimation(name, frameNum, frameWidth,
                        frameHeight, imageName, soundName);
                    updateAniTable();
                    updateFrameTable();
                    sourceLabel.update();
                    editorArea.update();
                    getJDialog3().setVisible(false);
                }
            });
        }
        return jButton10;
    }

    /**
     * This method initializes jButton11
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton11() {
        if (jButton11 == null) {
            jButton11 = new JButton();
            jButton11.setText("取消");
            jButton11.setSize(new Dimension(60, 20));
            jButton11.setLocation(new Point(120, 210));
            jButton11.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog3().setVisible(false);
                }
            });
        }
        return jButton11;
    }

    /**
     * This method initializes jButton2
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton2() {
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setText("播放");
            jButton2.setLocation(new Point(580, 150));
            jButton2.setSize(new Dimension(60, 20));
            jButton2.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    editorArea.play();
                    if (aniManager.getCurAnimation() == null) {
                        return;
                    }
                    if (!aniManager.getCurAnimation().soundName.equals("")) {

                        urlMusic0 = new File(Project.getProjectPath() + "\\audio\\sound\\" + aniManager.getCurAnimation().soundName);
                        try {
                            sequencer = MidiSystem.getSequencer(false);
                            sequencer.open();
                        } catch (Exception ex) {
                        }
                        thread = new Thread(new MusicRun());
                        thread.start();
                        setMusic();
                    }

                }
            });
        }
        return jButton2;
    }
    private File urlMusic0;
    boolean play = false;
    private Sequencer sequencer;
    private Sequence sequence;
    private Thread thread;

    public void setMusic() {
        sequencer.stop();
        play = true;
        if (thread.getState() == Thread.State.TIMED_WAITING) {
            thread.interrupt();
        }
    }

    public class MusicRun extends Thread {

        @Override
        public void run() {
            while (true) {
                if (play) {
                    try {
                        sequence = MidiSystem.getSequence(urlMusic0);
                        sequencer.setSequence(sequence);
                        sequencer.start();
                        Thread.sleep(76000);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    public void stopMusic() {
        play = false;
        if (sequencer != null) {
            sequencer.stop();
        }

    }

    /**
     * This method initializes jButton3
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton3() {
        if (jButton3 == null) {
            jButton3 = new JButton();
            jButton3.setText("保存");
            jButton3.setSize(new Dimension(60, 20));
            jButton3.setLocation(new Point(580, 190));
            jButton3.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (aniManager.saveAniFile()) {
                        JOptionPane.showMessageDialog(null,
                            "保存成功");
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "保存失败");
                    }
                }
            });
        }
        return jButton3;
    }

    /**
     * This method initializes jButton4
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton4() {
        if (jButton4 == null) {
            jButton4 = new JButton();
            jButton4.setText("清除");
            jButton4.setSize(new Dimension(60, 20));
            jButton4.setLocation(new Point(580, 230));
            jButton4.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    System.out.println("clean 1");
                    if (aniManager.getCurAnimation() == null) {
                        return;
                    }
                    aniManager.getCurAnimation().getCurFrame().num = 0;
                    aniManager.getCurAnimation().getCurFrame().image = null;
                    aniManager.getCurAnimation().getCurFrame().offsetX = 0;
                    aniManager.getCurAnimation().getCurFrame().offsetY = 0;
                    editorArea.update();
                    sourceLabel.update();

                }
            });
        }
        return jButton4;
    }

    /**
     * This method initializes jButton5
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton5() {
        if (jButton5 == null) {
            jButton5 = new JButton();
            jButton5.setText("帧数");
            jButton5.setSize(new Dimension(60, 20));
            jButton5.setLocation(new Point(580, 70));
            jButton5.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog1().setVisible(true);
                }
            });
        }
        return jButton5;
    }

    /**
     * This method initializes jButton6
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton6() {
        if (jButton6 == null) {
            jButton6 = new JButton();
            jButton6.setText("确定");
            jButton6.setSize(new Dimension(60, 20));
            jButton6.setLocation(new Point(50, 60));
            jButton6.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    changeFrame();
                    getJDialog1().setVisible(false);
//                    System.out.println("ok 1 " + jTextField3.getText());

                }
            });
        }
        return jButton6;
    }

    private void changeFrame() {
        int newN = Integer.parseInt(jTextField3.getText());
        if (aniManager.getCurAnimation() == null) {
            return;
        }
        Animation anim = new Animation();
        anim.frameHeight = aniManager.getCurAnimation().frameHeight;
        anim.frameWidth = aniManager.getCurAnimation().frameWidth;
        anim.index = aniManager.getCurAnimationIndex();
        anim.imageName = aniManager.getCurAnimation().imageName;
        anim.image = aniManager.getCurAnimation().image;
        anim.soundName = aniManager.getCurAnimation().soundName;
        anim.name = aniManager.getCurAnimation().name;
        anim.frames = new model.Frame[newN];
        for (int i = 0; i < newN; i++) {
            anim.frames[i] = new model.Frame();
            if (i < aniManager.getCurAnimation().frames.length) {
                anim.frames[i].image = aniManager.getCurAnimation().frames[i].image;
                anim.frames[i].num = aniManager.getCurAnimation().frames[i].num;
                anim.frames[i].offsetX = aniManager.getCurAnimation().frames[i].offsetX;
                anim.frames[i].offsetY = aniManager.getCurAnimation().frames[i].offsetY;
            }

        }
        for (int i = 0; i < aniManager.getCurAnimation().frameNum; i++) {
            aniManager.getCurAnimation().frames[i] = null;
        }
        aniManager.getCurAnimation().frameNum = newN;
        aniManager.getCurAnimation().frames = new model.Frame[newN];
        for (int i = 0; i < newN; i++) {
            aniManager.getCurAnimation().frames[i] = new model.Frame();
            aniManager.getCurAnimation().frames[i].image = anim.frames[i].image;
            aniManager.getCurAnimation().frames[i].num = anim.frames[i].num;
            aniManager.getCurAnimation().frames[i].offsetX = anim.frames[i].offsetX;
            aniManager.getCurAnimation().frames[i].offsetY = anim.frames[i].offsetY;
        }
//        System.out.println("a max " + aniManager.getAnimationMaxNum());
        updateAniTable();
        updateFrameTable();
    }

    /**
     * This method initializes jButton7
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton7() {
        if (jButton7 == null) {
            jButton7 = new JButton();
            jButton7.setText("取消");
            jButton7.setSize(new Dimension(60, 20));
            jButton7.setLocation(new Point(150, 60));
            jButton7.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog1().setVisible(false);
//                    System.out.println("cancle 1");
                }
            });
        }
        return jButton7;
    }

    /**
     * This method initializes jButton8
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton8() {
        if (jButton8 == null) {
            jButton8 = new JButton();
            jButton8.setText("确定");
            jButton8.setSize(new Dimension(60, 20));
            jButton8.setLocation(new Point(50, 60));
            jButton8.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {

                    System.out.println("" + jTextField4.getText() + "," + jTextField5.getText());
                    buzhen();
                    getJDialog2().setVisible(false);
                }
            });
        }
        return jButton8;
    }

    private void buzhen() {
        int first = Integer.parseInt(jTextField4.getText());
        int end = Integer.parseInt(jTextField5.getText());
        for (int i = first + 1; i < end; i++) {
            aniManager.getCurAnimation().frames[i].num = aniManager.getCurAnimation().frames[first].num;
            aniManager.getCurAnimation().frames[i].image = aniManager.getCurAnimation().frames[first].image;
            aniManager.getCurAnimation().frames[i].offsetX = aniManager.getCurAnimation().frames[first].offsetX + (i - first) * (aniManager.getCurAnimation().frames[end].offsetX - aniManager.getCurAnimation().frames[first].offsetX) / (end - first);
            aniManager.getCurAnimation().frames[i].offsetY = aniManager.getCurAnimation().frames[first].offsetY + (i - first) * (aniManager.getCurAnimation().frames[end].offsetY - aniManager.getCurAnimation().frames[first].offsetY) / (end - first);
        }
        editorArea.update();
    }

    /**
     * This method initializes jButton9
     *
     * @return javax.swing.JButton
     */
    private JButton getJButton9() {
        if (jButton9 == null) {
            jButton9 = new JButton();
            jButton9.setText("取消");
            jButton9.setSize(new Dimension(60, 20));
            jButton9.setLocation(new Point(150, 60));
            jButton9.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog2().setVisible(false);
//                    System.out.println("cancle 2");
                }
            });
        }
        return jButton9;
    }

    /**
     * This method initializes jComboBox
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox() {
        if (jComboBox == null) {
            jComboBox = new JComboBox();
            jComboBox.setSize(new Dimension(160, 20));
            jComboBox.setLocation(new Point(50, 170));
        }
        return jComboBox;
    }

    /**
     * This method initializes jComboBox1
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJComboBox1() {
        if (jComboBox1 == null) {
            jComboBox1 = new JComboBox();
            jComboBox1.setSize(new Dimension(160, 20));
            jComboBox1.setLocation(new Point(50, 130));
        }
        return jComboBox1;
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
//    private JPanel getJContentPane() {
//        if (jContentPane == null) {
//            jContentPane = new JPanel();
//            jContentPane.setLayout(null);
//            jContentPane.add(getJButton(), null);
//        }
//        return jContentPane;
//    }
    private void updateLable() {
//        getJContentPane1();//防空指针 待修改
//        if (aniManager.isEmpty()) {
//            return;
//        }
        if (aniManager.getCurAnimation() == null) {
            return;
        }
        jLabel18.setText("" + aniManager.getCurAnimation().getCurIndex());
        jLabel16.setText("" + aniManager.getCurAnimation().name);
        jLabel15.setText("" + aniManager.getCurAnimation().index);
    }

    /**
     * This method initializes jContentPane1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane1() {
        if (jContentPane1 == null) {
            jLabel18 = new JLabel();
            jLabel18.setText("");
            jLabel18.setSize(new Dimension(55, 20));
            jLabel18.setLocation(new Point(585, 20));
            jLabel17 = new JLabel();
            jLabel17.setText("当前帧：");
            jLabel17.setSize(new Dimension(55, 20));
            jLabel17.setLocation(new Point(530, 20));
            jLabel16 = new JLabel();
            jLabel16.setText("");
            jLabel16.setSize(new Dimension(150, 20));
            jLabel16.setLocation(new Point(360, 20));
            jLabel15 = new JLabel();
            jLabel15.setText("");
            jLabel15.setSize(new Dimension(60, 20));
            jLabel15.setLocation(new Point(240, 20));
            jLabel14 = new JLabel();
            jLabel14.setText("名称：");
            jLabel14.setSize(new Dimension(40, 20));
            jLabel14.setLocation(new Point(320, 20));
            jLabel13 = new JLabel();
            jLabel13.setText("编号：");
            jLabel13.setSize(new Dimension(40, 20));
            jLabel13.setLocation(new Point(200, 20));
            jLabel8 = new JLabel();
            jLabel8.setText("帧列表");
            jLabel8.setLocation(new Point(200, 60));
            jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel8.setSize(new Dimension(60, 20));
            jLabel5 = new JLabel();
            jLabel5.setText("帧高：");
            jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel5.setLocation(new Point(120, 90));
            jLabel5.setSize(new Dimension(40, 20));
            jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel4 = new JLabel();
            jLabel4.setText("帧宽：");
            jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel4.setSize(new Dimension(40, 20));
            jLabel4.setLocation(new Point(10, 90));
            jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel3 = new JLabel();
            jLabel3.setText("动画：");
            jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel3.setSize(new Dimension(40, 20));
            jLabel3.setLocation(new Point(10, 130));
            jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel2 = new JLabel();
            jLabel2.setText("音效：");
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel2.setSize(new Dimension(40, 20));
            jLabel2.setLocation(new Point(10, 170));
            jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel = new JLabel();
            jLabel.setText("动画列表");
            jLabel.setLocation(new Point(10, 10));
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setSize(new Dimension(150, 20));
            jContentPane1 = new JPanel();
            jContentPane1.setLayout(null);
            jContentPane1.add(jLabel, null);
            jContentPane1.add(getJScrollPane(), null);
            jContentPane1.add(getJScrollPane1(), null);
            jContentPane1.add(getJButton1(), null);
            jContentPane1.add(getJButton2(), null);
            jContentPane1.add(jLabel8, null);
            jContentPane1.add(getJButton3(), null);
            jContentPane1.add(getJButton4(), null);
            jContentPane1.add(getJButton5(), null);
            jContentPane1.add(jLabel13, null);
            jContentPane1.add(jLabel14, null);
            jContentPane1.add(jLabel15, null);
            jContentPane1.add(jLabel16, null);
            jContentPane1.add(jLabel17, null);
            jContentPane1.add(jLabel18, null);
            jContentPane1.add(getJScrollPane2(), null);
            jContentPane1.add(getJPanel(), null);
        }
        return jContentPane1;
    }

    /**
     * This method initializes jContentPane2
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane2() {
        if (jContentPane2 == null) {
            jLabel9 = new JLabel();
            jLabel9.setText("帧数：");
            jLabel9.setSize(new Dimension(40, 20));
            jLabel9.setLocation(new Point(20, 20));
            jContentPane2 = new JPanel();
            jContentPane2.setLayout(null);
            jContentPane2.add(jLabel9, null);
            jContentPane2.add(getJTextField3(), null);
            jContentPane2.add(getJButton6(), null);
            jContentPane2.add(getJButton7(), null);
        }
        return jContentPane2;
    }

    /**
     * This method initializes jContentPane3
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane3() {
        if (jContentPane3 == null) {
            jLabel11 = new JLabel();
            jLabel11.setText("尾帧：");
            jLabel11.setSize(new Dimension(40, 20));
            jLabel11.setLocation(new Point(130, 20));
            jLabel10 = new JLabel();
            jLabel10.setText("首帧：");
            jLabel10.setSize(new Dimension(40, 20));
            jLabel10.setLocation(new Point(20, 20));
            jContentPane3 = new JPanel();
            jContentPane3.setLayout(null);
            jContentPane3.add(jLabel10, null);
            jContentPane3.add(jLabel11, null);
            jContentPane3.add(getJTextField4(), null);
            jContentPane3.add(getJTextField5(), null);
            jContentPane3.add(getJButton8(), null);
            jContentPane3.add(getJButton9(), null);
        }
        return jContentPane3;
    }

    /**
     * This method initializes jContentPane4
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane4() {
        if (jContentPane4 == null) {
            jLabel12 = new JLabel();
            jLabel12.setText("帧数：");
            jLabel12.setSize(new Dimension(40, 20));
            jLabel12.setLocation(new Point(10, 50));
            jLabel1 = new JLabel();
            jLabel1.setText("名称：");
            jLabel1.setSize(new Dimension(40, 20));
            jLabel1.setLocation(new Point(10, 10));
            jContentPane4 = new JPanel();
            jContentPane4.setLayout(null);
            jContentPane4.add(getJTextField(), null);
            jContentPane4.add(jLabel4, null);
            jContentPane4.add(getJTextField1(), null);
            jContentPane4.add(jLabel5, null);
            jContentPane4.add(getJTextField2(), null);
            jContentPane4.add(jLabel3, null);
            jContentPane4.add(getJComboBox1(), null);
            jContentPane4.add(getJComboBox(), null);
            jContentPane4.add(jLabel2, null);
            jContentPane4.add(jLabel1, null);
            jContentPane4.add(getJButton10(), null);
            jContentPane4.add(getJButton11(), null);
            jContentPane4.add(jLabel12, null);
            jContentPane4.add(getJTextField6(), null);
        }
        initJComboBoxs();
        return jContentPane4;
    }

//    /**
//     * This method initializes jDialog
//     *
//     * @return javax.swing.JDialog
//     */
//    private JDialog getJDialog() {
//        if (jDialog == null) {
//            jDialog = new JDialog(this);
//            jDialog.setSize(new Dimension(655, 500));
//            jDialog.setTitle("动画编辑器");
//            jDialog.setModal(true);
//            jDialog.setResizable(false);
//            jDialog.setContentPane(getJContentPane1());
//            aniManager.init();
//            updateAniTable();
//            updateFrameTable();
//            jTable.setModel(aniDTM);
//            jTable1.setModel(frameDTM);
//        }
//        return jDialog;
//    }
    /**
     * This method initializes jDialog1
     *
     * @return javax.swing.JDialog
     */
    private JDialog getJDialog1() {
        if (jDialog1 == null) {
            jDialog1 = new JDialog(this);
            jDialog1.setSize(new Dimension(250, 130));
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialog1.getSize();
            jDialog1.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialog1.setTitle("修改帧数");
            jDialog1.setResizable(false);
            jDialog1.setModal(true);
            jDialog1.setContentPane(getJContentPane2());
        }
        return jDialog1;
    }

    /**
     * This method initializes jDialog2
     *
     * @return javax.swing.JDialog
     */
    private JDialog getJDialog2() {
        if (jDialog2 == null) {
            jDialog2 = new JDialog(this);
            jDialog2.setSize(new Dimension(250, 130));
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialog2.getSize();
            jDialog2.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialog2.setModal(true);
            jDialog2.setResizable(false);
            jDialog2.setTitle("补帧");
            jDialog2.setContentPane(getJContentPane3());
        }
        return jDialog2;
    }

    /**
     * This method initializes jDialog3
     *
     * @return javax.swing.JDialog
     */
    private JDialog getJDialog3() {
        if (jDialog3 == null) {
            jDialog3 = new JDialog(this);
            jDialog3.setSize(new Dimension(230, 270));
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialog3.getSize();
            jDialog3.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialog3.setModal(true);
            jDialog3.setResizable(false);
            jDialog3.setTitle("新建动画");
            jDialog3.setContentPane(getJContentPane4());
        }
        return jDialog3;
    }

    /**
     * This method initializes jMenuItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem() {
        if (jMenuItem == null) {
            jMenuItem = new JMenuItem();
            jMenuItem.setText("新建");
            jMenuItem.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog3().setVisible(true);
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
            jMenuItem1.setText("删除");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {

                    delRow();
                }
            });
        }
        return jMenuItem1;
    }

    private void delRow() {
//        int num = jTable.getSelectedRow();

        if (curAniIndex != -1) {
            aniManager.delAnimation(curAniIndex);
            System.out.println("num: " + curAniIndex);
            aniDTM.removeRow(curAniIndex);
            if (aniDTM.getRowCount() == 0) {
                aniDTM.addRow(new String[]{"请新建动画档"});
            }
            curAniIndex = 0;
            aniManager.setCurAnimationIndex(curAniIndex);
            updateAniTable();
            updateFrameTable();
            editorArea.update();
            sourceLabel.update();
//            curRow = 0;
//            curIndex = Integer.parseInt(((String) aniDTM.getValueAt(curRow, 0)));
//            switchEnemy(curIndex);
//            index--;
        }

    }

    /**
     * This method initializes jMenuItem2
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem2() {
        if (jMenuItem2 == null) {
            jMenuItem2 = new JMenuItem();
            jMenuItem2.setText("清除");
            jMenuItem2.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                }
            });
        }
        return jMenuItem2;
    }

    /**
     * This method initializes jMenuItem3
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem3() {
        if (jMenuItem3 == null) {
            jMenuItem3 = new JMenuItem();
            jMenuItem3.setText("删除");
            jMenuItem3.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                }
            });
        }
        return jMenuItem3;
    }

    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(1);
            jPanel = new JPanel();
            jPanel.setLayout(gridLayout);
            jPanel.setSize(new Dimension(300, 200));
            jPanel.setLocation(new Point(270, 60));
            jPanel.add(getEditorArea(), null);
        }
        return jPanel;
    }

    /**
     * This method initializes jPopupMenu
     *
     * @return javax.swing.JPopupMenu
     */
    private JPopupMenu getJPopupMenu() {
        if (jPopupMenu == null) {
            jPopupMenu = new JPopupMenu();
            jPopupMenu.setSize(new Dimension(95, 143));
            jPopupMenu.add(getJMenuItem());
            jPopupMenu.add(getJMenuItem1());
        }
        return jPopupMenu;
    }

    /**
     * This method initializes jPopupMenu1
     *
     * @return javax.swing.JPopupMenu
     */
    private JPopupMenu getJPopupMenu1() {
        if (jPopupMenu1 == null) {
            jPopupMenu1 = new JPopupMenu();
            jPopupMenu1.setSize(new Dimension(94, 40));
            jPopupMenu1.add(getJMenuItem2());
            jPopupMenu1.add(getJMenuItem3());
        }
        return jPopupMenu1;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setSize(new Dimension(150, 420));
            jScrollPane.setLocation(new Point(10, 40));
            jScrollPane.setViewportView(getJTable());
        }
        return jScrollPane;
    }

    /**
     * This method initializes jScrollPane1
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setSize(new Dimension(60, 170));
            jScrollPane1.setLocation(new Point(200, 90));
            jScrollPane1.setViewportView(getJTable1());
        }
        return jScrollPane1;
    }

    /**
     * This method initializes jScrollPane2
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane2() {
        if (jScrollPane2 == null) {
            sourceLabel = new SourceLabel();
            jScrollPane2 = new JScrollPane();
            jScrollPane2.setSize(new Dimension(440, 180));
            jScrollPane2.setViewportView(sourceLabel);
            jScrollPane2.setLocation(new Point(200, 280));
        }
        return jScrollPane2;
    }

    /**
     * This method initializes jTable
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable() {
        if (jTable == null) {
            jTable = new JTable();
            jTable.addMouseListener(this);
        }
        return jTable;
    }

    /**
     * This method initializes jTable1
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable1() {
        if (jTable1 == null) {
            jTable1 = new JTable();
            jTable1.addMouseListener(this);
        }
        return jTable1;
    }

    /**
     * This method initializes jTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setSize(new Dimension(160, 20));
            jTextField.setLocation(new Point(50, 10));
        }
        return jTextField;
    }

    /**
     * This method initializes jTextField1
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField1() {
        if (jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setLocation(new Point(50, 90));
            jTextField1.setSize(new Dimension(50, 20));
        }
        return jTextField1;
    }

    /**
     * This method initializes jTextField2
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField2() {
        if (jTextField2 == null) {
            jTextField2 = new JTextField();
            jTextField2.setLocation(new Point(160, 90));
            jTextField2.setSize(new Dimension(50, 20));
        }
        return jTextField2;
    }

    /**
     * This method initializes jTextField3
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField3() {
        if (jTextField3 == null) {
            jTextField3 = new JTextField();
            jTextField3.setLocation(new Point(70, 20));
            jTextField3.setSize(new Dimension(150, 20));
        }
        return jTextField3;
    }

    /**
     * This method initializes jTextField4
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField4() {
        if (jTextField4 == null) {
            jTextField4 = new JTextField();
            jTextField4.setLocation(new Point(60, 20));
            jTextField4.setSize(new Dimension(60, 20));
        }
        return jTextField4;
    }

    /**
     * This method initializes jTextField5
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField5() {
        if (jTextField5 == null) {
            jTextField5 = new JTextField();
            jTextField5.setLocation(new Point(170, 20));
            jTextField5.setSize(new Dimension(60, 20));
        }
        return jTextField5;
    }

    /**
     * This method initializes jTextField6
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField6() {
        if (jTextField6 == null) {
            jTextField6 = new JTextField();
            jTextField6.setLocation(new Point(50, 50));
            jTextField6.setSize(new Dimension(50, 20));
        }
        return jTextField6;
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(655, 505);
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
            screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
        this.setTitle("动画编辑器");
        this.setModal(true);
        this.setResizable(false);
        this.setContentPane(getJContentPane1());
        aniManager.init();
        updateAniTable();
        updateFrameTable();
        jTable.setFocusable(false);//必须设为不获得焦点，否者编辑栏的键盘响应不可用
        jTable.setModel(aniDTM);
        jTable1.setFocusable(false);
        jTable1.setModel(frameDTM);
    }

    private void initJComboBoxs() {
        // 清除原选项

        jComboBox1.removeAll();// 图片
        jComboBox.removeAll();// 音效
        // 初始化图片列表
        System.out.println("初始化图片列表");
        File file = new File(gamePath + "\\image\\animation\\");
        File[] f = file.listFiles();
        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i].getName());
            jComboBox1.addItem(f[i].getName());
        }
        // 初始化音效列表
        System.out.println("初始化音效列表");
        file = new File(gamePath + "\\audio\\sound\\");
        f = file.listFiles();
        jComboBox.addItem("无");
        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i].getName());
            jComboBox.addItem(f[i].getName());
        }
    }

    public void mouseClicked(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseEntered(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    public void mouseExited(MouseEvent e) {
        // TODO 自动生成方法存根
    }
    private int curAniIndex;

    public void mousePressed(MouseEvent e) {
        System.out.println(e.getButton());
        if (e.getSource().equals(jTable)) {
            // 动画列表事件
            if (e.getButton() == MouseEvent.BUTTON1) {
                // 左键事件
                if (jTable.getSelectedRow() != -1) {
                    curAniIndex = jTable.getSelectedRow();
                    aniManager.setCurAnimationIndex(curAniIndex);
//
//                    System.out.println("imageName " + aniManager.getCurAnimation().imageName);
//                    System.out.println("musicName " + aniManager.getCurAnimation().soundName);
//                    System.out.println("name " + aniManager.getCurAnimation().name);
                }

                updateAniTable();
                updateFrameTable();
                editorArea.update();
                sourceLabel.update();
                updateLable();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // 右键事件
                getJPopupMenu().show(jTable, e.getX(), e.getY());
            }

        } else if (e.getSource().equals(jTable1)) {
            // 帧列表事件
            if (e.getButton() == MouseEvent.BUTTON1) {
                // 左键事件
                Animation ani = aniManager.getCurAnimation();
                if (ani != null && jTable1.getSelectedRow() > -1) {
                    ani.setCurIndex(jTable1.getSelectedRow());
//                    System.out.println("x " + ani.getCurFrame().offsetX);
//                    System.out.println("y " + ani.getCurFrame().offsetY);
//                    System.out.println("num " + ani.getCurFrame().num);
                    editorArea.update();
                    updateLable();
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // 右键事件
                getJPopupMenu1().show(jTable1, e.getX(), e.getY());
            }


        }


    }

    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成方法存根
    }

    private void updateAniTable() {
        if (aniDTM == null) {
            aniDTM = new DefaultTableModel(new String[]{"动画名称"}, 0) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
        // 清除原数据
        int len = aniDTM.getRowCount();
        for (int i = 0; i < len; i++) {
            aniDTM.removeRow(aniDTM.getRowCount() - 1);
        }
        // 添加新数据
        String[] data = aniManager.getAllAnimations();

        if (data == null || data.length == 0) {
            aniDTM.addRow(new String[]{"右键新建动画档"});
        } else {
            for (int i = 0; i < data.length; i++) {
                aniDTM.addRow(new String[]{data[i]});
            }
        }
    }

    private void updateFrameTable() {
        if (frameDTM == null) {
            frameDTM = new DefaultTableModel(new String[]{"帧"}, 0) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
        // 清除原数据
        int len = frameDTM.getRowCount();
        for (int i = 0; i < len; i++) {
            frameDTM.removeRow(frameDTM.getRowCount() - 1);
        }
        // 添加新数据
//        if (aniManager.isEmpty()) {
//            return;
//        }
        Animation ani = aniManager.getCurAnimation();
        if (ani == null || ani.frames == null || ani.frames.length == 0) {
            return;
        }
        model.Frame[] data = ani.frames;
        if (data == null) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            frameDTM.addRow(new String[]{i + ""});
        }

    }
} // @jve:decl-index=0:visual-constraint="74,17"

