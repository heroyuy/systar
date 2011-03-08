package engine;

import java.util.logging.Level;
import java.util.logging.Logger;
import module.*;
//import engine.util.GarbagePanel;
import model.Map;
//import model.MapList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.event.*;
import javax.swing.*;
//import javax.swing.plaf.basic.*;
import java.util.*;
import engine.util.*;
//import java.util.logging.Level;
import model.*;
import converter.*;
import java.net.URI;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import emulator.*;

public class MapEditor extends JFrame implements ActionListener, ChangeListener {

    private BufferedImage bf;
    // 用于绘制地图的Label

    class MapCanvas extends JLabel implements
        MouseMotionListener, MouseListener {

        private static final long serialVersionUID = 1L;
        public BufferedImage mapImg = null;

        MapCanvas() {
            super();
            addMouseListener(this);
            addMouseMotionListener(this);
            this.add(getPm());
            this.add(getPm2());
        }
        public Graphics2D bfg = null;

        private void initOffImg() {
            // 实例化offImg并获得脱屏画笔
//            System.out.println("useMemory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024);
//            System.out.println("freeMemory:" + Runtime.getRuntime().freeMemory());
            mapImg = bfg.getDeviceConfiguration().createCompatibleImage(map.getCellWidth() * map.getCol(), map.getCellHeight() * map.getRow(),
                Transparency.TRANSLUCENT);//设置offImg背景为透明
            // 根据地图的数组将地图绘制出来,代码比较复杂，需要小心分析
            bfg.dispose();
            bfg = mapImg.createGraphics();
            switch (type) {
                case 0:
                default:
                    for (int i = map.getRow() - 1; i >= 0; i--) {
                        for (int j = map.getCol() - 1; j >= 0; j--) {
                            for (int m = 0; m < map.getLayerNum(); m++) {
                                int x = (map.getMap()[m][i][j] - 1)
                                    % (map.getImageWidth() / map.getCellWidth());
                                int y = (map.getMap()[m][i][j] - 1)
                                    / (map.getImageWidth() / map.getCellWidth());
                                bfg.drawImage(map.getImage(), j * map.getCellWidth(), i
                                    * map.getCellHeight(), (j + 1)
                                    * map.getCellWidth(), (i + 1)
                                    * map.getCellHeight(), x * map.getCellWidth(),
                                    y * map.getCellHeight(), (x + 1)
                                    * map.getCellWidth(), (y + 1)
                                    * map.getCellHeight(), null);
                            }
                        }
                    }

                    break;
                case 1:
                    for (int i = map.getRow() - 1; i >= 0; i--) {
                        for (int j = map.getCol() - 1; j >= 0; j--) {
                            for (int m = 0; m < map.getLayerNum(); m++) {
                                int x = (map.getMap()[m][i][j] - 1)
                                    % (map.getImageWidth() / map.getCellWidth());
                                int y = (map.getMap()[m][i][j] - 1)
                                    / (map.getImageWidth() / map.getCellWidth());
                                if (currentLayer != 0) {
                                    if (m == currentLayer || m == currentLayer - 1) {
                                        bfg.drawImage(map.getImage(), j * map.getCellWidth(), i
                                            * map.getCellHeight(), (j + 1)
                                            * map.getCellWidth(), (i + 1)
                                            * map.getCellHeight(), x * map.getCellWidth(),
                                            y * map.getCellHeight(), (x + 1)
                                            * map.getCellWidth(), (y + 1)
                                            * map.getCellHeight(), null);
                                    }
                                } else {
                                    if (m == currentLayer) {
                                        bfg.drawImage(map.getImage(), j * map.getCellWidth(), i
                                            * map.getCellHeight(), (j + 1)
                                            * map.getCellWidth(), (i + 1)
                                            * map.getCellHeight(), x * map.getCellWidth(),
                                            y * map.getCellHeight(), (x + 1)
                                            * map.getCellWidth(), (y + 1)
                                            * map.getCellHeight(), null);
                                    }
                                }
                            }
                        }
                    }

                    break;
                case 2:
                    for (int i = map.getRow() - 1; i >= 0; i--) {
                        for (int j = map.getCol() - 1; j >= 0; j--) {
                            for (int m = 0; m < map.getLayerNum(); m++) {
                                int x = (map.getMap()[m][i][j] - 1)
                                    % (map.getImageWidth() / map.getCellWidth());
                                int y = (map.getMap()[m][i][j] - 1)
                                    / (map.getImageWidth() / map.getCellWidth());
                                if (m == currentLayer) {
                                    bfg.drawImage(map.getImage(), j * map.getCellWidth(), i
                                        * map.getCellHeight(), (j + 1)
                                        * map.getCellWidth(), (i + 1)
                                        * map.getCellHeight(), x * map.getCellWidth(),
                                        y * map.getCellHeight(), (x + 1)
                                        * map.getCellWidth(), (y + 1)
                                        * map.getCellHeight(), null);
                                }
                            }
                        }
                    }

                    break;
            }

            //图像处理。如缩放等
            if (isPreview) {
                bf = mapImg;
                bf = ImageUtil.rotateImage(mapImg, getJSliderPreview2().getValue());
                bf = ImageUtil.resizeImageScale(bf, getJSliderPreview().getValue());//默认缩放50%
            }

            /*
             * 画网格
             */
            if (isMapGrid) {
                bfg.setColor(Color.BLACK);
                for (int j = 0; j < map.getRow(); j++) {
                    bfg.drawLine(0, j * map.getCellHeight(), map.getCol() * map.getCellWidth(), j * map.getCellHeight());
                }
                for (int k = 0; k < map.getCol(); k++) {
                    bfg.drawLine(k * map.getCellWidth(), 0, k * map.getCellWidth(), map.getRow() * map.getCellHeight());
                }
            }

            if (isCollide) {
//                bfg.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
//                    BasicStroke.JOIN_MITER));
                bfg.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER));
                for (int i = 0; i < map.getRow(); i++) {
                    for (int j = 0; j < map.getCol(); j++) {
                        if (map.getWay()[i][j] == 1) {
                            bfg.setColor(Color.red);
                            bfg.drawRect(j * map.getCellWidth(), i * map.getCellHeight(), map.getCellWidth(), map.getCellHeight());
                            bfg.setColor(Color.BLACK);
                            bfg.drawString("1", j * map.getCellWidth() + 4, i * map.getCellHeight() + map.getCellHeight() - 4);
                        }
                    }
                }
            }

            if (isEvent) {
                bfg.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER));
                if (Project.getPlayer().getMapIndex() == currentMapIndex) {

                    bfg.setColor(new Color(240, 240, 240));
                    bfg.drawRect(Project.getPlayer().getStartX() * map.getCellWidth(), Project.getPlayer().getStartY() * map.getCellHeight(), map.getCellWidth(), map.getCellHeight());
                    bfg.drawString("S", Project.getPlayer().getStartX() * map.getCellWidth() + 4, Project.getPlayer().getStartY() * map.getCellHeight() + map.getCellHeight() - 4);
                }

                bfg.setColor(Color.ORANGE);
                for (int i = 0; i < map.getScriptList().length; i++) {
                    for (int j = 0; j < map.getScriptList()[0].length; j++) {
                        Script temp = map.getScriptList()[i][j];
                        if (temp != null) {
                            bfg.drawRect(temp.getCol() * map.getCellWidth() + 2, temp.getRow() * map.getCellHeight() + 2, map.getCellWidth() - 4, map.getCellHeight() - 4);
                        }
                    }
                }
            }
            if (isChoose) {
                // 画粗框

                bfg.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER));
                bfg.setColor(new Color(GetRandom(0, 255), GetRandom(0, 255), GetRandom(0, 255)));
                bfg.drawRect(mData.getStartX(), mData.getStartY(), mData.getEndX() - mData.getStartX(), mData.getEndY() - mData.getStartY());
            }
            if (!isChoose
                && !isEvent && !isCollide
                && getJSliderSet().getValue() != 0) {
                bfg.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER));
                bfg.drawImage(tileCanvas.getTileImg(), nowX * map.getCellWidth(), nowY * map.getCellHeight(),
                    nowX * map.getCellWidth() + data.getEndX() - data.getStartX(), nowY * map.getCellHeight() + data.getEndY() - data.getStartY(),
                    data.getStartX(), data.getStartY(), data.getEndX(), data.getEndY(), null);
                bfg.setColor(Color.WHITE);
                bfg.drawRect(nowX * map.getCellWidth(), nowY * map.getCellHeight(), data.getEndX() - data.getStartX(), data.getEndY() - data.getStartY());
            } else if (isEvent || isCollide) {
                bfg.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER));
                bfg.setColor(Color.WHITE);
                bfg.drawRect(nowX * map.getCellWidth(), nowY * map.getCellHeight(), map.getCellWidth(), map.getCellHeight());
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // 处理鼠标点击时事件
//            if (map.getVisible() == false) {
//                return;
//            }
            if (isEvent) {
                if (eventType == 1) {

                    if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                        if (e.getClickCount() >= 2) {//双击打开编辑事件对话框
                            eventX = e.getX() / map.getCellWidth();
                            eventY = e.getY() / map.getCellHeight();
                            if (eventX < map.getCol() && eventY < map.getRow()) {
                                editNewEvent();
                            }

                        }
                    }
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // 处理鼠标拖动时事件
//            if (map.getVisible() == false) {
//                return;
//            }
            if (isChoose) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                    if (map != null) {
                        int x = e.getX();
                        int y = e.getY();
                        mData.setB_X(x);
                        mData.setB_Y(y);
                        mData.update(map);
                        repaint();
                    }
                }
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                }
            } else if (!isEvent) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                    if (map != null) {
                        int x = e.getX() / map.getCellWidth();
                        int y = e.getY() / map.getCellHeight();
                        if (x < 0) {
                            x = 0;
                        }
                        if (y < 0) {
                            y = 0;
                        }
                        nowX = x;
                        nowY = y;
                        if (!isCollide) {
                            for (int i = 0; i <= data.getEndRow() - data.getStartRow(); i++) {
                                for (int j = 0; j <= data.getEndCol() - data.getStartCol(); j++) {
                                    int num = (data.getStartRow() + i) * map.getImageCol()
                                        + data.getStartCol() + j + 1;// 图块编号从1开始
                                    map.setMap(currentLayer, y + i, x + j, num);
                                }
                            }

                        } else {
                            //1表示不可通行
                            map.setWay(y, x, 1);
                        }
//                        for (int i = 0; i <= data.getEndRow() - data.getStartRow(); i++) {
//                            for (int j = 0; j <= data.getEndCol() - data.getStartCol(); j++) {
//                                int num = (data.getStartRow() + i) * map.getImageCol()
//                                    + data.getStartCol() + j + 1;// 图块编号从1开始
//                                if (!isCollide) {
//                                    map.setMap(currentLayer, y + i, x + j, num);
//                                } else {
//                                    //1表示不可通行
//                                    map.setWay(y, x, 1);
//                                }
//                            }
//                        }
                    }
                }
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    if (map != null) {
                        int x = e.getX() / map.getCellWidth();
                        int y = e.getY() / map.getCellHeight();
                        if (x < 0) {
                            x = 0;
                        }
                        if (y < 0) {
                            y = 0;
                        }
                        nowX = x;
                        nowY = y;
                        for (int i = 0; i <= data.getEndRow() - data.getStartRow(); i++) {
                            for (int j = 0; j <= data.getEndCol() - data.getStartCol(); j++) {
                                if (!isCollide) {
                                    map.setMap(currentLayer, y + i, x + j, 0);
                                } else {
                                    //1表示不可通行
                                    map.setWay(y, x, 0);
                                }
                            }
                        }
                    }
                }
                repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO 自动生成方法存根
        }
        private int nowX = 0, nowY = 0;//跟随鼠标

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO 自动生成方法存根
            if (map == null) {
                return;
            }
            int xm = e.getX() / map.getCellWidth();
            int ym = e.getY() / map.getCellHeight();
            if (xm < 0) {
                xm = 0;
            }
            if (ym < 0) {
                ym = 0;
            }
            if (!isPa) {
                nowX = xm;
                nowY = ym;
            }
            if (!isChoose) {
                repaint();
            }
            if (xm < map.getCol() && ym < map.getRow()) {
                this.setToolTipText("(" + ym + "," + xm + ")" + map.getMap()[currentLayer][ym][xm]);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
//            if (map.getVisible() == false) {
//                return;
//            }
            isPa = false;//false 表示鼠标继续跟随 ture表示鼠标不跟随
            copyUndo();
            // 处理鼠标按下时事件

            if (isChoose) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                    if (map != null) {
                        int x = e.getX();
                        int y = e.getY();
                        mData.setA_X(x);
                        mData.setA_Y(y);
                    }
                }
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                }
            }
            if (!isChoose && !isEvent) {
                if (isFill) {
                    if (map != null) {
                        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                            for (int i = 0; i <= data.getEndRow() - data.getStartRow(); i++) {
                                for (int j = 0; j <= data.getEndCol() - data.getStartCol(); j++) {

                                    if (!isCollide) {
                                        int num = (data.getStartRow() + i) * map.getImageCol()
                                            + data.getStartCol() + j + 1;// 图块编号从1开始
                                        int teX, teY;
                                        teX = Math.max(1, data.getEndRow() - data.getStartRow());
                                        teY = Math.max(1, data.getEndCol() - data.getStartCol());//防by0
                                        for (int m = 0; m < map.getRow() / teX; m++) {
                                            for (int n = 0; n < map.getCol() / teY; n++) {
                                                map.setMap(currentLayer, i + m * (data.getEndCol() - data.getStartCol() + 1),
                                                    j + n * (data.getEndRow() - data.getStartRow() + 1), num);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                        }
                    }
                    repaint();
                } else {
                    if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                        if (map != null) {
                            int x = e.getX() / map.getCellWidth();
                            int y = e.getY() / map.getCellHeight();
                            if (x < 0) {
                                x = 0;
                            }
                            if (y < 0) {
                                y = 0;
                            }
                            if (!isCollide) {
                                for (int i = 0; i <= data.getEndRow() - data.getStartRow(); i++) {
                                    for (int j = 0; j <= data.getEndCol() - data.getStartCol(); j++) {
                                        int num = (data.getStartRow() + i) * map.getImageCol()
                                            + data.getStartCol() + j + 1;// 图块编号从1开始
                                        map.setMap(currentLayer, y + i, x + j, num);
                                    }
                                }

                            } else {
                                //1表示不可通行
                                map.setWay(y, x, 1);
                            }
//                            for (int i = 0; i <= data.getEndRow() - data.getStartRow(); i++) {
//                                for (int j = 0; j <= data.getEndCol() - data.getStartCol(); j++) {
//                                    int num = (data.getStartRow() + i) * map.getImageCol()
//                                        + data.getStartCol() + j + 1;// 图块编号从1开始
//                                    if (!isCollide) {
//                                        map.setMap(currentLayer, y + i, x + j, num);
//                                    } else {
//                                        //1表示不可通行
//                                        map.setWay(y, x, 1);
//                                    }
//                                }
//                            }
                        }
//                        repaint(nowX * map.getCellWidth(), nowY * map.getCellHeight(), data.getEndX() - data.getStartX(), data.getEndY() - data.getStartY());
                    }
                    if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                        if (map != null) {
                            int x = e.getX() / map.getCellWidth();
                            int y = e.getY() / map.getCellHeight();
                            if (x < 0) {
                                x = 0;
                            }
                            if (y < 0) {
                                y = 0;
                            }
                            if (!isCollide) {
                                for (int i = 0; i <= data.getEndRow() - data.getStartRow(); i++) {
                                    for (int j = 0; j <= data.getEndCol() - data.getStartCol(); j++) {
                                        map.setMap(currentLayer, y + i, x + j, 0);
                                    }
                                }

                            } else {
                                //1表示不可通行
                                map.setWay(y, x, 0);
                            }


                        }
//                        repaint(nowX * map.getCellWidth(), nowY * map.getCellHeight(), data.getEndX() - data.getStartX(), data.getEndY() - data.getStartY());
                    }
                }
            }
            repaint();

            copyRedo();
            nIndex++;
            if (nIndex > 9) {
                nIndex = 0;
            }
        }
        private JPopupMenu pm2;
        private JMenuItem jmi5 = null;
        private JMenuItem jmi6 = null;
        private JMenuItem jmi7 = null;
        private JMenuItem jmi8 = null;
        private JMenuItem jmi9 = null;
        private JMenuItem jmi10 = null;

        private JPopupMenu getPm2() {
            if (pm2 == null) {
                pm2 = new JPopupMenu();
                pm2.add(getJmi5());
                pm2.addSeparator();
                pm2.add(getJmi6());
                pm2.add(getJmi7());
                pm2.add(getJmi8());
                pm2.add(getJmi9());
                pm2.addSeparator();
                pm2.add(getJmi10());
            }
            return pm2;
        }

        private JMenuItem getJmi5() {
            if (jmi5 == null) {
                jmi5 = new JMenuItem();
                jmi5.setText("编辑事件");
//                jmi1.addActionListener(this);
                jmi5.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        editNewEvent();
                    }
                });
            }
            return jmi5;
        }

        private void editNewEvent() {
            System.out.println("新建事件");
            eventDia = new EventManager(mapEditor);
//            eventDia.updateList();
            eventDia.setVisible(true);
        }
        private EventManager eventDia;

        private JMenuItem getJmi6() {
            if (jmi6 == null) {
                jmi6 = new JMenuItem();
                jmi6.setText("剪切");
//                jmi1.addActionListener(this);
                jmi6.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        System.out.println("剪切");
                        isPa = false;
//                        int tempEventX = eventX;
//                        int tempEventY = eventY;
                        cutScript = map.getScriptList()[eventY][eventX];
                        cutType = map.getScriptType()[eventY][eventX];
                        delScript();
                    }
                });
            }
            return jmi6;
        }
        private Script cutScript;

        private void delScript() {
            Script s = map.getScriptList()[eventY][eventX];
            if (s != null) {
                map.getScriptList()[eventY][eventX] = null;
                System.out.println("del");

            }
            map.getScriptType()[eventY][eventX] = cutType;
            mapCanvas.updateCanvas();
        }

        private JMenuItem getJmi7() {
            if (jmi7 == null) {
                jmi7 = new JMenuItem();
                jmi7.setText("复制");
//                jmi1.addActionListener(this);
                jmi7.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        System.out.println("复制");
                        isPa = false;
                        cutScript = map.getScriptList()[eventY][eventX];
                        cutType = map.getScriptType()[eventY][eventX];
                    }
                });
            }
            return jmi7;
        }
        private byte cutType = 0;

        private JMenuItem getJmi8() {
            if (jmi8 == null) {
                jmi8 = new JMenuItem();
                jmi8.setText("粘贴");
//                jmi1.addActionListener(this);
                jmi8.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {

                        isPa = false;
                        if (cutScript != null) {
                            Script s = new Script(nowX, nowY);
                            for (int i = 0; i < cutScript.size(); i++) {
                                s.addString(cutScript.getString(i));
                            }
                            map.getScriptList()[nowY][nowX] = s;
                            map.getScriptType()[eventY][eventX] = cutType;
                            mapCanvas.updateCanvas();
                            System.out.println("粘贴");

                        }

                    }
                });
            }
            return jmi8;
        }

        private JMenuItem getJmi9() {
            if (jmi9 == null) {
                jmi9 = new JMenuItem();
                jmi9.setText("删除");
//                jmi1.addActionListener(this);
                jmi9.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        System.out.println("删除");
                        isPa = false;
                        delScript();

                    }
                });
            }
            return jmi9;
        }

        private JMenuItem getJmi10() {
            if (jmi10 == null) {
                jmi10 = new JMenuItem();
                jmi10.setText("设置主角初期位置");
//                jmi1.addActionListener(this);
                jmi10.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
//                        System.out.println("设置主角初期位置");
                        getSetHeroDialog();
                        initHeroDir();
                        setHeroDialog.setVisible(true);
                    }
                });
            }
            return jmi10;
        }

        private void initHeroDir() {
            switch (Project.getPlayer().getDir()) {
                case 0:
                    jrb1.setSelected(true);
                    break;
                case 1:
                    jrb2.setSelected(true);
                    break;
                case 2:
                    jrb3.setSelected(true);
                    break;
                case 3:
                    jrb4.setSelected(true);
                    break;
            }
        }
        private JDialog setHeroDialog;

        private JDialog getSetHeroDialog() {
            if (setHeroDialog == null) {
                setHeroDialog = new JDialog();
                setHeroDialog.setSize(170, 200);
                Dimension screenSize =
                    Toolkit.getDefaultToolkit().getScreenSize();
                Dimension labelSize = setHeroDialog.getSize();
                setHeroDialog.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                    screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
                setHeroDialog.setTitle("设置主角面向");
                setHeroDialog.setModal(true);
                setHeroDialog.setContentPane(getSetHeroPanel());
            }
            return setHeroDialog;
        }
        private JPanel setHeroPanel;
        private JPanel jp4;
        private JRadioButton jrb1, jrb2, jrb3, jrb4;
        private ButtonGroup jbg = new ButtonGroup();
        private int heroDir = 0;

        private JPanel getJpanel4() {
            if (jp4 == null) {
                jp4 = new JPanel();
                jp4.setLayout(null);
                jp4.setBounds(10, 10, 140, 110);
                jrb1 = new JRadioButton("向上");
                jrb1.setBounds(20, 20, 60, 20);
//                jrb1.setSelected(true);
                jrb1.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        heroDir = 0;
                    }
                });
                jrb2 = new JRadioButton("向下");
                jrb2.setBounds(20, 40, 60, 20);
                jrb2.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        heroDir = 1;
                    }
                });
                jrb3 = new JRadioButton("向左");
                jrb3.setBounds(20, 60, 60, 20);
                jrb3.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        heroDir = 2;
                    }
                });
                jrb4 = new JRadioButton("向右");
                jrb4.setBounds(20, 80, 60, 20);
                jrb4.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        heroDir = 3;
                    }
                });
                jbg.add(jrb1);
                jbg.add(jrb2);
                jbg.add(jrb3);
                jbg.add(jrb4);
                jp4.add(jrb1, null);
                jp4.add(jrb2, null);
                jp4.add(jrb3, null);
                jp4.add(jrb4, null);
                jp4.setBorder(BorderFactory.createTitledBorder("面向"));
            }
            return jp4;
        }

        private JPanel getSetHeroPanel() {
            if (setHeroPanel == null) {
                setHeroPanel = new JPanel();
                setHeroPanel.setLayout(null);
                setHeroPanel.add(getJpanel4());
                setHeroPanel.add(getJbOk());
                setHeroPanel.add(getJbCancle());
            }
            return setHeroPanel;
        }
        private JButton ok2, cancle2;

        private JButton getJbOk() {
            if (ok2 == null) {
                ok2 = new JButton("确定");
                ok2.setBounds(10, 140, 60, 20);
                ok2.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {

//                        System.out.println("currentMapIndex " + currentMapIndex);
//                        System.out.println("heroDir " + heroDir);
                        System.out.println(eventX + "," + eventY);
                        Project.getPlayer().setStartX(eventX);
                        Project.getPlayer().setStartY(eventY);
                        Project.getPlayer().setDir(heroDir);
                        Project.getPlayer().setMapIndex(currentMapIndex);
                        dm.savePlayer();
                        setHeroDialog.setVisible(false);
                    }
                });
            }
            return ok2;
        }

        private JButton getJbCancle() {
            if (cancle2 == null) {
                cancle2 = new JButton("取消");
                cancle2.setBounds(90, 140, 60, 20);
                cancle2.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        setHeroDialog.setVisible(false);
                    }
                });
            }
            return cancle2;
        }
        private JPopupMenu pm;
        private JMenuItem jmi1 = null;
        private JMenuItem jmi2 = null;
        private JMenuItem jmi3 = null;
        private JMenuItem jmi4 = null;

        private JPopupMenu getPm() {
            if (pm == null) {
                pm = new JPopupMenu();
                pm.add(getJmi1());
                pm.add(getJmi2());
                pm.add(getJmi3());
                pm.add(getJmi4());
            }
            return pm;
        }
        int[][] copyMap;
        int[][] copyWay;

        private JMenuItem getJmi1() {
            if (jmi1 == null) {
                jmi1 = new JMenuItem();
                jmi1.setText("复制");
//                jmi1.addActionListener(this);
                jmi1.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        isPa = false;
                        copyMap = new int[mData.getEndRow() - mData.getStartRow() + 1][mData.getEndCol() - mData.getStartCol() + 1];
                        copyWay = new int[mData.getEndRow() - mData.getStartRow() + 1][mData.getEndCol() - mData.getStartCol() + 1];
                        for (int i = 0; i < mData.getEndRow() - mData.getStartRow() + 1; i++) {
                            for (int j = 0; j < mData.getEndCol() - mData.getStartCol() + 1; j++) {
                                copyMap[i][j] = map.getMap()[currentLayer][i + mData.getStartRow()][j + mData.getStartCol()];
                                if (isAll) {
                                    copyWay[i][j] = map.getWay()[i + mData.getStartRow()][j + mData.getStartCol()];
                                }
                            }
                        }
                    }
                });
            }
            return jmi1;
        }

        private JMenuItem getJmi2() {
            if (jmi2 == null) {
                jmi2 = new JMenuItem();
                jmi2.setText("粘贴");
                jmi2.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        isPa = false;
                        if (copyMap != null) {

                            for (int j = 0; j < mData.getEndCol() - mData.getStartCol() + 1; j++) {
                                for (int i = 0; i < mData.getEndRow() - mData.getStartRow() + 1; i++) {
                                    map.setMap(currentLayer, nowY + i,
                                        nowX + j, copyMap[i][j]);
                                    if (isAll) {
                                        map.setWay(nowY + i,
                                            nowX + j, copyWay[i][j]);
                                    }
                                }
                            }
                        }
                        mapCanvas.updateCanvas();
                    }
                });
            }
            return jmi2;
        }

        private JMenuItem getJmi3() {
            if (jmi3 == null) {
                jmi3 = new JMenuItem();
                jmi3.setText("剪切");
                jmi3.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        isPa = false;
                        copyMap = new int[mData.getEndRow() - mData.getStartRow() + 1][mData.getEndCol() - mData.getStartCol() + 1];
                        for (int i = 0; i < mData.getEndRow() - mData.getStartRow() + 1; i++) {
                            for (int j = 0; j < mData.getEndCol() - mData.getStartCol() + 1; j++) {
                                copyMap[i][j] = map.getMap()[currentLayer][i + mData.getStartRow()][j + mData.getStartCol()];
                                map.setMap(currentLayer, i + mData.getStartRow(), j + mData.getStartCol(), 0);
                                if (isAll) {
                                    map.setWay(i + mData.getStartRow(), j + mData.getStartCol(), 0);
                                }
                            }
                        }
                        mapCanvas.updateCanvas();
                    }
                });
            }
            return jmi3;
        }

        private JMenuItem getJmi4() {
            if (jmi4 == null) {
                jmi4 = new JMenuItem();
                jmi4.setText("清除");
                jmi4.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        isPa = false;
                        for (int i = 0; i < mData.getEndRow() - mData.getStartRow() + 1; i++) {
                            for (int j = 0; j < mData.getEndCol() - mData.getStartCol() + 1; j++) {
                                map.setMap(currentLayer, i + mData.getStartRow(), j + mData.getStartCol(), 0);
                                if (isAll) {
                                    map.setWay(i + mData.getStartRow(), j + mData.getStartCol(), 0);
                                }
                            }
                        }
                        mapCanvas.updateCanvas();
                    }
                });
            }
            return jmi4;
        }
        private boolean isPa;

        @Override
        public void mouseReleased(MouseEvent e) {
            // 处理鼠标弹起时事件
//            if (map.getVisible() == false) {
//                return;
//            }
            if (isChoose) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                    if (map != null) {
                        int x = e.getX();
                        int y = e.getY();
                        mData.setB_X(x);
                        mData.setB_Y(y);
                        mData.update(map);
                        repaint();
                    }
                }
                if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                    pm.show(this, e.getX(), e.getY());
                    isPa = true;
                }
            }
            if (isEvent) {
                if (eventType == 0) {
//                    System.out.println("event");
                    if (eventType == 0) {
                        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
                            eventX = e.getX() / map.getCellWidth();
                            eventY = e.getY() / map.getCellHeight();
                            if (eventX < map.getCol() && eventY < map.getRow()) {
                                getEventDialog();
                                jtxEvent.setText("");
                                if (map.getScriptList()[eventY][eventX] != null) {
                                    for (int i = 0, n = map.getScriptList()[eventY][eventX].size(); i < n; i++) {
                                        jtxEvent.append(map.getScriptList()[eventY][eventX].getString(i) + "\n");
                                    }
                                }
                                jdEvent.setVisible(true);
                            }
                        }
                    }
                }
                if (eventType == 1) {
                    if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
                        eventX = e.getX() / map.getCellWidth();
                        eventY = e.getY() / map.getCellHeight();
                        if (eventX < map.getCol() && eventY < map.getRow()) {
                            pm2.show(this, e.getX(), e.getY());
                            isPa = true;
                        }

                    }
                }

            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (map != null) {
                initOffImg();
                g.drawImage(mapImg, 0, 0, Color.white, null);
            }
            if (isPreview) {
                getPreviewLabel().updateCanvas();
            }
        }

        public void updateCanvas() {
            // 为了显示出滚动条
            ImageIcon temp = null;
            mapImg = new BufferedImage(map.getCellWidth() * map.getCol(), map.getCellHeight() * map.getRow(), BufferedImage.TYPE_INT_RGB);
            bfg = mapImg.createGraphics();
            temp = new ImageIcon(mapImg);
            setIcon(temp);
            repaint();
        }
    }

    public int GetRandom(int rs, int re)//获取随机数
    {
        int tmp = rs + Math.abs(new Random().nextInt() % (re - rs + 1));
        return tmp;
    }
    // 用于显示地图图块的Label

    class TileCanvas extends JLabel implements MouseListener,
        MouseMotionListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        public Image TileImg = null;

        TileCanvas() {
            super();
            addMouseListener(this);
            addMouseMotionListener(this);

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // 处理鼠标点击时事件
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // 处理鼠标拖动时事件

            if (map != null) {
                int x = e.getX();
                int y = e.getY();
                if (x < map.getImageWidth() && y < map.getImageHeight()) {
                    data.setB_X(x);
                    data.setB_Y(y);
                    data.update(map);
                }
                repaint();
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // 处理鼠标进入时事件
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // 处理鼠标退出时事件
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO 自动生成方法存根
            if (map != null) {
                int xtm = e.getX() / map.getCellWidth();
                int ytm = e.getY() / map.getCellHeight();
                if (xtm < map.getImageCol() && ytm < map.getImageRow()) {
                    this.setToolTipText("(" + ytm + "," + xtm + ")" + (xtm + map.getImageCol() * ytm + 1));
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // 处理鼠标按下时事件

            if (map != null) {
                int x = e.getX();
                int y = e.getY();
                if (x < map.getImageWidth() && y < map.getImageHeight()) {
                    data.setA_X(x);
                    data.setA_Y(y);
                }

            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // 处理鼠标弹起时事件
            if (map != null) {
                int x = e.getX();
                int y = e.getY();
                if (x < map.getImageWidth() && y < map.getImageHeight()) {
                    data.setB_X(x);
                    data.setB_Y(y);
                    data.update(map);
                }

                repaint();
            }
            System.gc();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (map != null) {
                g.drawImage(TileImg, 0, 0, Color.white, null);
                /*
                 * 画网格
                 */
                if (isTileGrid) {
                    g.setColor(Color.BLACK);
                    for (int j = 0; j < map.getImageRow(); j++) {
                        g.drawLine(0, j * map.getCellHeight(), map.getImageCol() * map.getCellWidth(), j * map.getCellHeight());
                    }
                    for (int k = 0; k < map.getImageCol(); k++) {
                        g.drawLine(k * map.getCellWidth(), 0, k * map.getCellWidth(), map.getImageRow() * map.getCellHeight());
                    }
                }
                // 画粗框
                Graphics2D gg = (Graphics2D) g;
                gg.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER));
                // 2.0F是笔的粗细
                // CAP_ROUND是线条端点
                // JOIN_ROUND是点划线模式

                gg.setColor(new Color(GetRandom(0, 255), GetRandom(0, 255), GetRandom(0, 255)));
                gg.drawRect(data.getStartX(), data.getStartY(), data.getEndX() - data.getStartX(), data.getEndY() - data.getStartY());
            }
        }
        /**
         * 取得绘制地图的元件 将地图元件按设定的单元格大小裁剪 将裁剪后的地图元件显示出来
         *
         */
        BufferedImage tempImg;

        public BufferedImage getTileImg() {
            return ImageUtil.effect_transparent(bmpImg, getJSliderSet().getValue());
        }
        BufferedImage bmpImg;

        public void dealPic(int typp) {//将某种颜色处理为透明
            if (typp == 0) {
                bmpImg = tempImg;
                return;
            }
            int h = tempImg.getHeight();
            int w = tempImg.getWidth();
            int argb, r = 0, g = 0, b = 0, a = 0;
            bmpImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D bg = bmpImg.createGraphics();
            bg.getDeviceConfiguration().createCompatibleImage(w, h,
                Transparency.TRANSLUCENT);//设置offImg背景为透明
            bg.dispose();
            for (int i = h - 1; i >= 0; i--) {
                for (int j = w - 1; j >= 0; j--) {
                    argb = tempImg.getRGB(j, i);

                    a = ((argb & 0xff000000) >> 24); // alpha channel
                    r = ((argb & 0x00ff0000) >> 16); // red channel
                    g = ((argb & 0x0000ff00) >> 8); // green channel
                    b = (argb & 0x000000ff); // blue channel
//                    if (typp == 1) {
                    //该算法默认图片右下角点为背景色
                    if (argb == tempImg.getRGB(w - 1, h - 1)) {
                        bmpImg.setRGB(j, i, 0x00000000);
                    } else {
                        bmpImg.setRGB(j, i, (a << 24) | (r << 16) | (g << 8) | b);
                    }
//                    }
                }
            }
        }

//        public Image CreateRGBImage(int[] pixs, int w, int h, boolean flag) {
//            BufferedImage buffImg = null;
//            if(flag){
//                buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//            }else{
//                buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//            }
//            buffImg.setRGB(0, 0, w, h, pixs, 0, pixs.length);
//            return buffImg;
//        }
        public void updateCanvas() {
            // 取得原图片
//            if (map == null && map.getVisible() == false) {
//                return;
//
//            }
            File file = new File(Project.getProjectPath() + "\\image\\tileset\\" + map.getImageName());
            try {
                tempImg = ImageIO.read(file);
            } catch (Exception ee) {
            }

//            if (isRotate) {
//                tempImg = ImageUtil.rotateImage(tempImg);
//            }
            ImageIcon temp;
            int typ = 0;
            if (file.getName().endsWith(".bmp")) {
                typ = 1;
            }
            if (file.getName().endsWith(".jpg")) {
                typ = 2;
            }
            dealPic(typ);
            temp = new ImageIcon(bmpImg);
            // 计算实际应该显示的图片大小
            int xSize = temp.getIconWidth() / map.getCellWidth()
                * map.getCellWidth();
            int ySize = temp.getIconHeight() / map.getCellHeight()
                * map.getCellHeight();

            // 得到应该显示的图片
            CropImageFilter cropFilter = new CropImageFilter(0, 0, xSize, ySize);
            TileImg = createImage(new FilteredImageSource(bmpImg.getSource(),
                cropFilter));
//             根据裁剪后的图片建立的Icon
            temp = new ImageIcon(TileImg);
            setIcon(temp);
            map.setImage(TileImg);
            map.setImageWidth(xSize);
            map.setImageHeight(ySize);
            repaint();
        }
    }

    private JSplitPane getJSplitPane1() {
        if (jSplitPane1 == null) {
            jSplitPane1 = new JSplitPane();
            jSplitPane1.setDividerSize(8);
            jSplitPane1.setOneTouchExpandable(true);
            jSplitPane1.setPreferredSize(new Dimension(260, 395));
            jSplitPane1.setTopComponent(getJScrollPane());
            jSplitPane1.setBottomComponent(getJScrollPane3());
            jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        }
        return jSplitPane1;
    }
    private JScrollPane jScrollPane3 = null;

    private JScrollPane getJScrollPane3() {
        if (jScrollPane3 == null) {
            jScrollPane3 = new JScrollPane();
        }
        jScrollPane3.setPreferredSize(new Dimension(260, 0));
        jScrollPane3.setViewportView(getJTable());

        return jScrollPane3;
    }
    private MapTable jTable = null;

    private JTable getJTable() {
        if (jTable == null) {
            jTable = new MapTable();
//            jTable.setRowSelectionInterval(0, 0);
        }
        return jTable;
    }
    public int currentMapIndex = 0;

// 用于显示地图的Table
    class MapTable extends JTable implements MouseListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private DefaultTableModel mapModel = null;

        MapTable() {
            super();
            String[] col = {"编号", "地图"};
//            mapModel = new DefaultTableModel(col, 0);
            mapModel = new javax.swing.table.DefaultTableModel(col, 0) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            setModel(mapModel);
            String[] temp = {"", ""};
            mapModel.addRow(temp);
            addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO 自动生成方法存根

            currentMapIndex = jTable.getSelectedRow();
//            System.out.println("currentMapIndex = " + currentMapIndex);
            System.out.println(mapVector.isEmpty());
            if (map != null && (!mapVector.isEmpty())) {
                if (getJTable().getSelectedRow() != -1) {
                    map = (Map) mapVector.get(currentMapIndex);
                    undoMap = null;
                    redoMap = null;
                    undoMap = new int[10][map.getLayerNum()][map.getRow()][map.getCol()];
                    redoMap = new int[10][map.getLayerNum()][map.getRow()][map.getCol()];
                    currentLayer = 0;
                    layerNum = map.getLayerNum();
                    getJMenu1().repaint();
                    initMapCanvas();
                }
            }
            if (e.getClickCount() >= 2) {
                setMapNews();
            }
        }

        public void mouseEntered(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void mouseExited(MouseEvent e) {
            // TODO 自动生成方法存根
        }

        public void mousePressed(MouseEvent e) {
//            // 单击右键弹出菜单,左键选中地图
            if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                getJPopupMenu();

                jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        public void mouseReleased(MouseEvent e) {
            // TODO 自动生成方法存根
//            if (jTable.getSelectedRow() == -1) {
//                jMenuItem26.setEnabled(false);
//            } else {
//                jMenuItem26.setEnabled(true);
//            }
        }

        public void updateTable() {
            // TODO 自动生成方法存根
            int mapNum = mapModel.getRowCount();
            for (int i = 0; i < mapNum; i++) {
                mapModel.removeRow(0);
            }
            for (int i = 0, n = mapVector.size(); i < n; i++) {
                String[] temp = {"" + i, ((Map) mapVector.get(i)).getName()};
                mapModel.addRow(temp);
            }
            if (mapVector.isEmpty()) {
                String[] temp = {"", ""};
                mapModel.addRow(temp);
            }
        }
    }

    public void TableUpdateCanvas() {
        if (jTable != null) {
            jTable.updateTable();
        }
    }
    private JPopupMenu jPopupMenu;

    private JPopupMenu getJPopupMenu() {
        if (jPopupMenu == null) {
            jPopupMenu = new JPopupMenu();
            jPopupMenu.setSize(new Dimension(139, 61));

            jPopupMenu.add(getMapItem());
            jPopupMenu.add(getJMenuItem());
            jPopupMenu.add(getJMenuItem3());
            try {
                JMenu[] menu = null;
                if (pl.getMenuItem() != null) {
                    menu = pl.getPopMenu();
                }
                if (menu != null) {
                    int ii = menu.length;
                    for (int i = 0; i
                        < ii; i++) {
                        if (menu[i] != null) {
                            jPopupMenu.add(menu[i]);
                        }
                    }
                }
            } catch (Exception e) {
                // TODO 自动生成 catch 块
            }
            jPopupMenu.add(getJMenuItem26());
//            jPopupMenu.add(getJMenuItem27());
//            jPopupMenu.add(getJMenuItem28());
//            jPopupMenu.add(getJMenuItem29());
        }
        return jPopupMenu;
    }

    private JMenuItem getJMenuItem27() {
        if (jMenuItem27 == null) {
            jMenuItem27 = new JMenuItem();
            jMenuItem27.setText("复制");
        }
        return jMenuItem27;
    }

    /**
     * This method initializes jMenuItem28
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem28() {
        if (jMenuItem28 == null) {
            jMenuItem28 = new JMenuItem();
            jMenuItem28.setText("粘贴");
        }
        return jMenuItem28;
    }
    private JMenuItem jMenuItem26, jMenuItem29, jMenuItem28, jMenuItem27;

//    private JMenuItem getJMenuItem25() {
//        if (jMenuItem25 == null) {
//            jMenuItem25 = new JMenuItem();
//            jMenuItem25.setText("设置");
//        }
//        return jMenuItem25;
//    }
    private JMenuItem getJMenuItem26() {
        if (jMenuItem26 == null) {
            jMenuItem26 = new JMenuItem();
            jMenuItem26.setText("删除地图");
            jMenuItem26.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!mapVector.isEmpty()) {
                        mapVector.remove(currentMapIndex);
                        System.out.println("mapsize:" + mapVector.size());
                        jTable.updateTable();
                        currentMapIndex = 0;
                        if (mapVector.isEmpty()) {
//                            jTable.updateTable();
                            mapCanvas.setVisible(false);
                            tileCanvas.setVisible(false);
                        } else {
                            map = mapVector.mapAt(0);
                            undoMap = new int[10][map.getLayerNum()][map.getRow()][map.getCol()];
                            redoMap = new int[10][map.getLayerNum()][map.getRow()][map.getCol()];
                            layerNum = map.getLayerNum();
                            currentLayer = 0;
                            getJMenu1().repaint();
                            initMapCanvas();
                        }
                    }
                }
            });
        }
        return jMenuItem26;
    }

    private JMenuItem getJMenuItem29() {
        if (jMenuItem29 == null) {
            jMenuItem29 = new JMenuItem();
            jMenuItem29.setText("移动");
        }
        return jMenuItem29;
    }
    private static final long serialVersionUID = 1L;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自动生成方法存根
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                MapEditor thisClass = new MapEditor();
                thisClass.setLAF(substance);
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }
    //设置外观风格的方法

    public void setLAF(String s) {
        try {
            UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);//更新控件的外观
        } catch (Exception e) {
        }
    }
//    private static String substance = "org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel";//这里修改默认皮肤
    private static String substance = "org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel";//这里修改默认皮肤
    private JPanel jContentPane = null;
    private JMenuBar jJMenuBar = null;
    private JMenu jMenu_File = null;
    private JMenu jMenu_Lay = null;
    private JMenu jMenu_Set = null;
    private JMenu jMenu_Plugin = null;
    private JMenu jMenu_pic = null;
    private JMenu jMenu_help = null;
    private JMenu jMenu_edit = null;
    private JMenuItem jMenuItem = null;
    private JMenuItem jUndoItem = null;
    private JMenuItem jRedoItem = null;
    private JRadioButtonMenuItem jMenuPicItem1 = null;
    private JRadioButtonMenuItem jMenuPicItem2 = null;
    private JRadioButtonMenuItem jMenuPicItem3 = null;
    private JRadioButtonMenuItem[] menuItem = new JRadioButtonMenuItem[0];
    private ButtonGroup buttonGroup = new ButtonGroup();
    private ButtonGroup buttonGroup2 = new ButtonGroup();
    private ButtonGroup buttonGroup3 = new ButtonGroup();
    private ButtonGroup buttonGroup4 = new ButtonGroup();
    private JSplitPane jSplitPane = null, jSplitPane1 = null;
    private Map map = null;  //  @jve:decl-index=0:
    private JDialog jDialog = null; // @jve:decl-index=0:visual-constraint="868,16"
    private JPanel jContentPane1 = null;
    private JPanel jContentPane2 = null;
    private JLabel jLabelcolor = null;
    private JLabel jLmapName = null;
    private JLabel jLcellW = null;
    private JLabel jLcellH = null;
    private JLabel jLmapImg = null;
    private JLabel jLLayNum = null;//图层数
    private JTextField jTmapName = null;
    private JTextField jTcellW = null;
    private JTextField jTcellH = null;
    private JTextField jTLayNum = null;//图层数
    private JComboBox jComboBox = null, jComboBox2 = null;
    private JButton jButton = null;
    private JButton jButton1 = null;
    private JScrollPane jScrollPane = null;
    private JPanel jPanel = null;
    private JScrollPane jScrollPane1 = null;
    private JPanel jPanel1 = null;
    private MapEditor.TileCanvas tileCanvas = null;
    private JLabel jLabel4 = null;
    private JLabel jLabel5 = null;
    private JTextField jTmapCol = null;
    private JTextField jTmapRow = null;
    private Data data = null; // @jve:decl-index=0:
    private Data mData = null;
    private int currentLayer = 0;
    public static MapEditor.MapCanvas mapCanvas = null;
    private JMenuItem jMenuItem3 = null;
    private PluginLoader pl = null; // @jve:decl-index=0:
    private JButton slButton = null;
    private JButton scButton = null;
    private JButton mapGridButton = null;
    private JButton TileGridButton = null;
    private JButton newButton = null;
    private JButton saveButton = null;
    private JButton undoButton = null;
    private JButton redoButton = null;
    private JCheckBox PrevCheck = null;
//    private JMenuItem ImgItem = null;
    private JRadioButton editCheck = null;
    private JRadioButton fillCheck = null;
    private JToolBar toolBar = null;
    private JToolBar tileToolBar = null;
    private JSlider jpb = null;
    private JSlider jpb2 = null;
    private JSlider jpb3 = null;
    private boolean isMapGrid = false;
    private boolean isTileGrid = false;
    private boolean isCollide = false;
    private boolean isPreview = false;
    private boolean isChoose = false;
    private boolean isFill = false;
    private boolean isAll = false;
    private int type = 0;
    private JDialog PreviewDialog = null;
    private JPanel PreviewPane = null;
    private JPanel PreviewPane2 = null;
    private JScrollPane jScrollPane2 = null;
    private Preview PreviewLabel = null;
    private JRadioButton jLayItemOption = null;
    private JMenuItem jItemExit = null;
    private JMenuItem jItemSet = null;
    private JMenuItem jItemHelp = null;
    private JMenuItem jItemAbout = null;
    private JButton jbColor = null;
    private JDialog jdSet = null;
    private JDialog jdHelp = null;
    private JDialog jdAbout = null;
    private JLabel jlbAbout = null;
    private JLabel jlbAbout2 = null;
    private JTextArea jtxHelp = null;
    private JPanel jpHelp = null;
    private JPanel jpAbout = null;
    private JScrollPane jtxSp = null;
//    private ImageManager im = null; // @jve:decl-index=0:visual-constraint="852,149"
    private int eventType = 1;
    private static MapEditor mapEditor = null;

    public static MapEditor getInstance() {
        if (mapEditor == null) {
            mapEditor = new MapEditor();
        }
        return mapEditor;
    }

    /**
     * This is the default constructor
     */
    public MapEditor() {
        super();
        mapEditor = this;
        try {
            enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        } catch (Exception e) {
        }
        pl = new PluginLoader(this);
        initialize();
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        boolean enable = false;
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
//处理Jframe关闭事件
            int option = JOptionPane.showConfirmDialog(this, "确定要退出吗？", "关闭前请注意保存", JOptionPane.YES_NO_OPTION);
            enable = (option == 1);
//            if (option == 1) {
//                super.processWindowEvent(e);
//            }
//            if(option == 3){
//                super.processWindowEvent(e);
//            }
        }


        if (!enable) {
//忽略其他事件，交给JFrame处理
            super.processWindowEvent(e);
        }
    }

    public void copyUndo() {
        if (map == null) {
            return;
        }
        for (int k = map.getLayerNum() - 1; k
            >= 0; k--) {
            for (int i = map.getRow() - 1; i
                >= 0; i--) {
                for (int j = map.getCol() - 1; j
                    >= 0; j--) {
                    undoMap[nIndex][k][i][j] = map.getMap()[k][i][j];
                }
            }
        }
    }

    public void copyRedo() {
        if (map == null) {
            return;
        }
        for (int k = map.getLayerNum() - 1; k
            >= 0; k--) {
            for (int i = map.getRow() - 1; i
                >= 0; i--) {
                for (int j = map.getCol() - 1; j
                    >= 0; j--) {
                    redoMap[nIndex][k][i][j] = map.getMap()[k][i][j];


                }
            }
        }
    }
    // 初始化图像管理器
//
//    private void creatIM() {
//        if (im == null) {
//            im = new ImageManager(this);
//        }
//    }
//    private JMenuItem imageItem;
//
//    private JMenuItem getJMenuItem15() {
//        if (imageItem == null) {
//            imageItem = new JMenuItem();
//            imageItem.setText("图片管理");
//            imageItem.addActionListener(new java.awt.event.ActionListener() {
//
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    if (!isHasProject) {
//                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
//                        return;
//                    }
////                    creatIM();
//                    im.setVisible(true);
//                }
//            });
//        }
//        return imageItem;
//    }

    private JDialog getHelpDialog() {
        if (jdHelp == null) {
            jdHelp = new JDialog(this);
            jdHelp.setSize(334, 352);
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jdHelp.getSize();
            jdHelp.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jdHelp.setTitle("帮助");
            jdHelp.setModal(true);
            jdHelp.setContentPane(getHelpPanel());
        }
        return jdHelp;
    }
    private EventManager jdEvent;

    private JDialog getEventDialog() {
        if (jdEvent == null) {
            jdEvent = new EventManager(this);
            jdEvent.setSize(340, 360);
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jdEvent.getSize();
            jdEvent.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jdEvent.setTitle("填写脚本:");
            jdEvent.setModal(true);
            jdEvent.setContentPane(getEventPanel());
        }
        return jdEvent;
    }
    private JPanel jpEvent;
    private JTextArea jtxEvent;
    private JScrollPane jtxEventSp;
    private JButton jbEventOk;
    private JButton jbEventCancle;

    private JPanel getEventPanel() {
        if (jpEvent == null) {
            jpEvent = new JPanel();
            jpEvent.setLayout(null);
            jtxEvent = new JTextArea();
            jtxEventSp = new JScrollPane(jtxEvent);
            jtxEventSp.setBounds(10, 10, 310, 270);
            jpEvent.add(jtxEventSp, null);
            jpEvent.add(getEventOk(), null);
            jpEvent.add(getEventCancle(), null);
        }
        return jpEvent;
    }
    public int eventX = 0, eventY = 0;

    private JButton getEventOk() {
        if (jbEventOk == null) {
            jbEventOk = new JButton();
            jbEventOk.setText("确定");
            jbEventOk.setBounds(27, 295, 50, 20);
//            jItemExit.addActionListener(this);
            jbEventOk.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
//map.getScriptList().addScript(eventX, eventY);
                    if (!jtxEvent.getText().equals("")) {

//                        map.getScriptList()[eventY][eventX].setIsNull(false);
                        String[] ss = jtxEvent.getText().split("\n");
                        int n = ss.length;

                        int t = 0;
                        for (int i = 0; i < n; i++) {
                            if (!ss[i].equals("")) {
                                t++;
                            }
                        }
                        if (t == 0) {
                            map.getScriptList()[eventY][eventX] = null;
                            jdEvent.setVisible(false);
                            mapCanvas.repaint();
                            return;
                        }
                        Script s = new Script(eventX, eventY);
                        for (int i = 0; i < n; i++) {
                            s.addString(ss[i]);
                        }

                        map.getScriptList()[eventY][eventX] = s;

//                        map.getScriptList()[eventY][eventX].addString(jtxEvent.getText());
                    } else {
                        map.getScriptList()[eventY][eventX] = null;
                    }
                    jdEvent.setVisible(false);
                    mapCanvas.repaint();
                }
            });
        }
        return jbEventOk;
    }
//    public ScriptList sl = new ScriptList();

    private JButton getEventCancle() {
        if (jbEventCancle == null) {
            jbEventCancle = new JButton();
            jbEventCancle.setText("取消");
            jbEventCancle.setBounds(254, 295, 50, 20);
//            jItemExit.addActionListener(this);
            jbEventCancle.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    jdEvent.setVisible(false);
                }
            });

        }
        return jbEventCancle;
    }

    private JPanel getHelpPanel() {
        if (jpHelp == null) {
            jpHelp = new JPanel();
            jpHelp.setLayout(new BorderLayout());
            jtxHelp = new JTextArea();
            jtxHelp.append(
                "疑难解答:\n"
                + "正在收集中，如遇bug请在论坛回帖，或\n"
                + "Email至397093109@qq.com\n"
                + "更多帮助请关注手游之星网\n"
                + "网址：www.soyostar.com\n"
                + "qq群 56126581欢迎你的加入\n"
                + "操作：\n"
                + "新建工程->新建地图->绘制地图->设置碰撞->设置主角位置->编辑事件->编辑游戏数据->编辑游戏动画->测试\n"
                + "打开项目文件夹 Game.jar即为生成的游戏包,enjoy it!");
            jtxSp = new JScrollPane(jtxHelp);
            jpHelp.add(jtxSp, BorderLayout.CENTER);

        }
        return jpHelp;
    }

    private JPanel getAboutPanel() {
        if (jpAbout == null) {
            jpAbout = new JPanel();
            jlbAbout = new JLabel();
            jlbAbout2 = new JLabel();
            jlbAbout.setText(
                "单击访问手游之星论坛");
            jlbAbout.addMouseListener(new MouseListener() {

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        java.awt.Desktop.getDesktop().browse(new URI("http://www.soyostar.com"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
//                        Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    try {
//                        Runtime.getRuntime().exec("cmd /c start www.soyostar.com");
//                    } catch (Exception ee) {
//                        // TODO Auto-generated catch block
//                        System.out.println("Error");
//                    }
                    // 处理鼠标点击时事件
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // 处理鼠标进入时事件
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // 处理鼠标退出时事件
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }
            });
            jlbAbout2.setText(version);
            jpAbout.add(jlbAbout2);
            jpAbout.add(jlbAbout);

        }
        return jpAbout;
    }

    private JDialog getAboutDialog() {
        if (jdAbout == null) {
            jdAbout = new JDialog(this);
            jdAbout.setSize(234, 122);
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jdAbout.getSize();
            jdAbout.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jdAbout.setTitle("关于");
            jdAbout.setResizable(false);
            jdAbout.setModal(true);
            jdAbout.setContentPane(getAboutPanel());
        }
        return jdAbout;
    }

    private JMenuItem getJMenuItemExit() {
        if (jItemExit == null) {
            jItemExit = new JMenuItem();
            jItemExit.setText("退出");
//            jItemExit.addActionListener(this);
            jItemExit.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        return jItemExit;
    }

    private JMenu getJMenuHelp() {
        if (jMenu_help == null) {
            jMenu_help = new JMenu();
            jMenu_help.setText("帮助(H)");
            jMenu_help.setMnemonic('H');

            jItemHelp = new JMenuItem();
            jItemHelp.setText("帮助");
            jItemHelp.setAccelerator(KeyStroke.getKeyStroke("F1"));
//            jItemHelp.addActionListener(this);
            jItemHelp.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getHelpDialog().setVisible(true);
//                    try {
//                        Runtime.getRuntime().exec("hh "+"Help.chm");
//                    } catch (IOException ee) {
//                        System.out.println("帮助文件丢失");
//                    }

                }
            });
            jMenu_help.add(jItemHelp);
            jItemAbout = new JMenuItem();
            jItemAbout.setText("关于");
//            jItemAbout.addActionListener(this);
            jItemAbout.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getAboutDialog().setVisible(true);
                }
            });
            jMenu_help.add(jItemAbout);
        }
        return jMenu_help;
    }

    private JRadioButton getJMenuLayOption() {
        if (jLayItemOption == null) {
            jLayItemOption = new JRadioButton();
            jLayItemOption.setText("碰撞");
            jLayItemOption.setToolTipText("切换碰撞编辑");
            jLayItemOption.setFocusable(false);
//            jLayItemOption.addActionListener(this);
            jLayItemOption.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    slButton.setText("状态：设置碰撞");
                    isCollide = true;
                    isChoose = false;
                    isFill = false;
//                    isNumShow = true;
                    isMapGrid = true;
                    isEvent = false;
                    if (map != null) {
                        mapCanvas.repaint();

                    }
                }
            });

        }
        return jLayItemOption;
    }

    private JToolBar getSbToolBar() {
        if (toolBar == null) {
            toolBar = new JToolBar("状态栏");
            GarbagePanel garbagePanel = new GarbagePanel();
            toolBar.add(getLeftButton());
            toolBar.add(getCenterButton());
            toolBar.addSeparator(new Dimension(52, 20));
            toolBar.add(garbagePanel);
            toolBar.setRollover(false);
            toolBar.setFloatable(false);
        }
        return toolBar;
    }

    private JToolBar getToolBar2() {
        if (tileToolBar == null) {
            tileToolBar = new JToolBar("工具栏");
            tileToolBar.add(getNewButton());
            tileToolBar.add(getOpenButton());
            tileToolBar.add(getSaveButton());
            tileToolBar.add(getUndoButton());
            tileToolBar.add(getRedoButton());
            tileToolBar.add(getTileGridButton());
//            tileToolBar.add(getRotateButton());
            tileToolBar.add(getMapGridButton());
            tileToolBar.addSeparator();
            tileToolBar.add(getPreviewButton());
            buttonGroup3.add(getEditButton());
            buttonGroup3.add(getFillButton());
            buttonGroup3.add(getPenButton());
            buttonGroup3.add(getEventButton());
            buttonGroup3.add(getJMenuLayOption());
            tileToolBar.add(getPenButton());
            tileToolBar.add(getFillButton());
            tileToolBar.add(getEditButton());
            tileToolBar.add(getEventButton());
            tileToolBar.add(getJMenuLayOption());
            try {
                JButton[] toolButton = null;
                if (pl.getMenuItem() != null) {
                    toolButton = pl.getButton();
                }
                if (toolButton != null) {
                    int ii = toolButton.length;
                    for (int i = 0; i
                        < ii; i++) {
                        if (toolButton[i] != null) {
                            tileToolBar.add(toolButton[i]);

                        }
                    }
                }
            } catch (Exception e) {
                // TODO 自动生成 catch 块
            }
        }
        return tileToolBar;

    }

    public JButton getLeftButton() {
        if (slButton == null) {
            slButton = new JButton();
            slButton.setText("状态：就绪");
            slButton.setEnabled(false);

        }
        return slButton;

    }

    public JButton getCenterButton() {
        if (scButton == null) {
            scButton = new JButton();
            scButton.setText("当前图层：未开始");
            scButton.setEnabled(false);

        }
        return scButton;

    }

    public JButton getMapGridButton() {
        if (mapGridButton == null) {
            //sbRightButton = new JButton("切换网格显示");
            ImageIcon img = new ImageIcon("icon/grid2.png");
            mapGridButton = new JButton(img);
            mapGridButton.setToolTipText("切换map网格显示");
            mapGridButton.setFocusable(false);
//            mapGridButton.setEnabled(false);
//            mapGridButton.addActionListener(this);
            mapGridButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    isMapGrid = !isMapGrid;

                    if (map != null) {
                        mapCanvas.repaint();
                    }
                }
            });
        }
        return mapGridButton;
    }

    public JButton getTileGridButton() {
        if (TileGridButton == null) {
            //sbRightButton = new JButton("切换网格显示");
            ImageIcon img = new ImageIcon("icon/grid.png");
            TileGridButton = new JButton(img);
            TileGridButton.setToolTipText("切换tile网格显示");
            TileGridButton.setFocusable(false);
//            TileGridButton.setEnabled(false);
//            TileGridButton.addActionListener(this);
            TileGridButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    isTileGrid = !isTileGrid;
                    if (map != null) {
                        tileCanvas.repaint();
                    }
                }
            });
        }
        return TileGridButton;
    }

    private JButton getNewButton() {
        if (newButton == null) {
            //sbRightButton = new JButton("切换网格显示");
            ImageIcon img = new ImageIcon("icon/new.png");
            newButton = new JButton(img);
            newButton.setToolTipText("新建工程");
            newButton.setFocusable(false);
//            newButton.addActionListener(this);
            newButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    getJDialog();
//                    initJComboBox();
//                    jDialog.setVisible(true);
                    getJDialogNewPro();
                    jDialogNewPro.setVisible(true);
                }
            });
        }
        return newButton;

    }

    public MapList getMapVector() {
        return mapVector;
    }
    private JButton openButton = null;

    private JButton getOpenButton() {
        if (openButton == null) {
            //sbRightButton = new JButton("切换网格显示");
            ImageIcon img = new ImageIcon("icon/open.png");
            openButton = new JButton(img);
            openButton.setToolTipText("打开工程");
            openButton.setFocusable(false);
            openButton.addActionListener(this);
            openButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    openProject();
                }
            });
        }
        return openButton;
    }

    private void openProject() {
        JFileChooser jf = new JFileChooser(new File("."));
        jf.setFileFilter(new MEFileFilter());
        jf.showOpenDialog(getJContentPane());
        File f = jf.getSelectedFile();
        if (f != null && f.getName().endsWith(".project")) {
            System.out.println("open " + f.getParent());
            if (loadProject(f)) {
                Project.setProjectPath(f.getParent());
                changeTitle();
                initMapVector();
                File file = new File(f.getParent() + "\\data\\map");
                int sum = file.listFiles().length;
//                System.out.println("sum " + sum);
                File[] fi = file.listFiles();
//                tempMapV = new MapList();
                for (int i = 0; i
                    < sum; i++) {
                    if (fi[i].getName().endsWith(".gat")) {
                        loadMapVector(fi[i]);
                    }
                }
////                System.out.println(tempMapV.size());
//                for (int i = 0; i < tempMapV.size(); i++) {
//                    mapVector.addMap(tempMapV.mapAt(i));
//                }
////                mapVector = tempMapV;

//                System.out.println("mapVectorIsEmpty " + mapVector.isEmpty());
                if (!mapVector.isEmpty()) {
                    map = mapVector.mapAt(0);
//                    System.out.println(map.getLayerNum());
                    undoMap = new int[10][map.getLayerNum()][map.getRow()][map.getCol()];
                    redoMap = new int[10][map.getLayerNum()][map.getRow()][map.getCol()];
                    layerNum = map.getLayerNum();
                    currentLayer = 0;
                    getJMenu1().repaint();
                    initMapCanvas();
                }
                creatAM();
//                creatIM();
                creatDM();
//                creatMM();

                jTable.updateTable();
                JOptionPane.showMessageDialog(getJContentPane(),
                    "打开成功");
            } else {
                // 导入失败
                JOptionPane.showMessageDialog(getJContentPane(),
                    "打开失败");
            }
        }
    }

    private JButton getSaveButton() {
        if (saveButton == null) {
            //sbRightButton = new JButton("切换网格显示");
            ImageIcon img = new ImageIcon("icon/save.png");
            saveButton = new JButton(img);
            saveButton.setToolTipText("保存工程");
            saveButton.setFocusable(false);
            saveButton.addActionListener(this);
            saveButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!isHasProject) {
                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
                        return;
                    }
                    savePro();
                }
            });
        }
        return saveButton;
    }

    private void savePro() {
        if (saveProject()) {
            JOptionPane.showMessageDialog(getJContentPane(), "保存成功");
        } else {
            JOptionPane.showMessageDialog(getJContentPane(), "保存失败");
        }
    }

    public JButton getUndoButton() {
        if (undoButton == null) {
            ImageIcon img = new ImageIcon("icon/undo.png");
            undoButton = new JButton(img);
            undoButton.setToolTipText("撤销");
            undoButton.setFocusable(false);
//            undoButton.addActionListener(this);
            undoButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (map != null) {
                        nIndex--;
                        if (nIndex < 0) {
                            nIndex = 0;

                        }
                        for (int k = 0; k
                            < map.getLayerNum(); k++) {
                            for (int i = 0; i
                                < map.getRow(); i++) {
                                System.arraycopy(undoMap[nIndex][k][i], 0, map.getMap()[k][i], 0, map.getCol());
                            }
                        }
                        if (map != null) {
                            mapCanvas.updateCanvas();
                        }
                    }
                }
            });
        }
        return undoButton;
    }

    public JButton getRedoButton() {
        if (redoButton == null) {
            ImageIcon img = new ImageIcon("icon/redo.png");
            redoButton = new JButton(img);
            redoButton.setToolTipText("恢复");
            redoButton.setFocusable(false);
//            redoButton.addActionListener(this);
            redoButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (map != null) {
                        for (int k = 0; k
                            < map.getLayerNum(); k++) {
                            for (int i = 0; i
                                < map.getRow(); i++) {
                                System.arraycopy(redoMap[nIndex][k][i], 0, map.getMap()[k][i], 0, map.getCol());
                            }
                        }
                        nIndex++;

                        if (nIndex > 9) {
                            nIndex = 9;
                        }
                        if (map != null) {
                            mapCanvas.updateCanvas();
                        }
                    }
                }
            });

        }
        return redoButton;

    }
    private JRadioButton eventButton;

    public JRadioButton getEventButton() {
        if (eventButton == null) {
            eventButton = new JRadioButton("事件");
            eventButton.setToolTipText("切换事件编辑");
            eventButton.setFocusable(false);
//            editCheck.addActionListener(this);
            eventButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    slButton.setText("状态：设置事件");
                    isEvent = true;
                    isMapGrid = true;
                    isChoose = false;
                    isFill = false;
                    isCollide = false;

                    if (map != null) {
                        mapCanvas.updateCanvas();
                    }
                }
            });

        }
        return eventButton;

    }
    private boolean isEvent;

    public JRadioButton getEditButton() {
        if (editCheck == null) {
            editCheck = new JRadioButton("选择");
            editCheck.setToolTipText("切换选择编辑");
            editCheck.setFocusable(false);
//            editCheck.addActionListener(this);
            editCheck.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    slButton.setText("状态：选择模式");
                    isChoose = true;
                    isFill = false;
                    isCollide = false;
                    isEvent = false;
                    if (map != null) {
                        mapCanvas.updateCanvas();

                    }
                }
            });

        }
        return editCheck;

    }
    private JRadioButton penCheck;

    public JRadioButton getPenButton() {
        if (penCheck == null) {
            penCheck = new JRadioButton("绘图");
            penCheck.setToolTipText("切换绘图编辑");
            penCheck.setFocusable(false);
            penCheck.setSelected(true);
            penCheck.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    slButton.setText("状态：绘图模式");
                    isChoose = false;
                    isFill = false;
                    isCollide = false;
                    isEvent = false;

                    if (map != null) {
                        mapCanvas.updateCanvas();
                    }
                }
            });

        }
        return penCheck;

    }

    public JRadioButton getFillButton() {
        if (fillCheck == null) {
            fillCheck = new JRadioButton("填充");
            fillCheck.setToolTipText("切换填充编辑");
            fillCheck.setFocusable(false);
//            fillCheck.addActionListener(this);
            fillCheck.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    slButton.setText("状态：填充模式");
                    isFill = true;
                    isChoose = false;
                    isCollide = false;
                    isEvent = false;
                    if (map != null) {
                        mapCanvas.updateCanvas();
                    }
                }
            });
        }
        return fillCheck;

    }

    public JCheckBox getPreviewButton() {
        if (PrevCheck == null) {
            PrevCheck = new JCheckBox("预览");
            PrevCheck.setToolTipText("切换预览显示");
            PrevCheck.setFocusable(false);
//            PrevCheck.addActionListener(this);
            PrevCheck.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!isHasProject) {
                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
                        PrevCheck.setSelected(false);
                        return;
                    }
                    if (PrevCheck.isSelected()) {
                        isPreview = true;
                    } else {
                        isPreview = false;
                    }

                    getPreviewDialog();
                    PreviewDialog.setVisible(isPreview);
                }
            });
        }
        return PrevCheck;

    }
    private JMenuItem jMusicMenuItem;
//    private MusicManager mum;
//
//    private void creatMM() {
//        mum = new MusicManager(this);
//    }

//    private JMenuItem getMusicMenuItem() {
//        if (jMusicMenuItem == null) {
//            jMusicMenuItem = new JMenuItem();
//            jMusicMenuItem.setText("音乐管理");
//            jMusicMenuItem.addActionListener(new java.awt.event.ActionListener() {
//
//                @Override
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    if (!isHasProject) {
//                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
//                        return;
//                    }
//                    creatMM();
//                    mum.setVisible(true);
//
//                }
//            });
//        }
//        return jMusicMenuItem;
//
//    } //    public JMenuItem getRotateButton() {
    //        if (rotateButton == null) {
    //            rotateButton = new JMenuItem("原图翻转");
    ////            rotateButton.setFocusable(false);
    ////            rotateButton.setEnabled(false);
    ////            rotateButton.addActionListener(this);
    //            rotateButton.addActionListener(new java.awt.event.ActionListener() {
    //
    //                @Override
    //                public void actionPerformed(java.awt.event.ActionEvent e) {
    //                    isRotate = !isRotate;
    //
    //
    //                    if (map != null) {
    //                        tileCanvas.updateCanvas();
    //
    //
    //                    }
    //                }
    //            });
    //
    //
    //        }
    //        return rotateButton;
    //
    //
    //    }
    private JDialog getPreviewDialog() {
        if (PreviewDialog == null) {
            PreviewDialog = new JDialog(this);
            PreviewDialog.setBounds(900, 120, 334, 384);
        }

        PreviewDialog.addWindowListener(new WindowAdapter() {                  //窗口关闭事件监听与实现

            @Override
            public void windowClosing(WindowEvent evt) {
                PrevCheck.setSelected(false);
            }
        });
        if (map != null) {
            initPreTitle();
            PreviewDialog.setContentPane(getPreviewPanel());
        }
        return PreviewDialog;
    }

    private void initPreTitle() {
        PreviewDialog.setTitle("预览 地图：" + map.getName());
    }

    private JPanel getPreviewPanel() {
        if (PreviewPane == null) {
            PreviewPane = new JPanel();
            PreviewPane.setLayout(new BorderLayout());
            PreviewPane.add(getJScrollPane2(), BorderLayout.CENTER);
            PreviewPane.add(getJSliderPreview(), BorderLayout.NORTH);
            PreviewPane.add(getJSliderPreview2(), BorderLayout.SOUTH);
        }
        return PreviewPane;

    }

    private JSlider getJSliderPreview2() {
        if (jpb2 == null) {
            jpb2 = new JSlider(0, 360);
            jpb2.setValue(0);
            jpb2.setPaintTicks(true);
            jpb2.setPaintLabels(true);
            jpb2.setMajorTickSpacing(60);
            jpb2.addChangeListener(this);
        }
        return jpb2;
    }

    private JPanel getPreview() {
        if (PreviewPane2 == null) {
            PreviewPane2 = new JPanel();
            PreviewPane2.add(getPreviewLabel());
        }
        return PreviewPane2;

    }

    private JSlider getJSliderPreview() {
        if (jpb == null) {
            jpb = new JSlider(-90, 90);
            jpb.setValue(-50);
            jpb.setPaintTicks(true);
            jpb.setPaintLabels(true);
            jpb.setMajorTickSpacing(30);
            jpb.addChangeListener(this);

        }
        return jpb;
    }

    private JSlider getJSliderSet() {
        if (jpb3 == null) {
            jpb3 = new JSlider(0, 250);
            jpb3.setValue(125);
            jpb3.setBounds(60, 50, 200, 40);
            jpb3.setPaintTicks(true);
            jpb3.setPaintLabels(true);
            jpb3.setMajorTickSpacing(50);
            jpb3.addChangeListener(this);

        }
        return jpb3;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (map != null) {
            mapCanvas.updateCanvas();
        }
        getJSliderSet().setToolTipText("当前透明度为：" + jpb3.getValue());
        getJSliderPreview().setToolTipText("预览缩放比例当前为：" + jpb.getValue());
        getJSliderPreview2().setToolTipText("旋转角当前为：" + jpb2.getValue());
//        getPreviewDialog().repaint();

    }

    private Preview getPreviewLabel() {
        if (PreviewLabel == null) {
            PreviewLabel = new Preview();

        }
        return PreviewLabel;

    }
    //预览

    private JScrollPane getJScrollPane2() {
        if (jScrollPane2 == null) {
            jScrollPane2 = new JScrollPane(getPreview());
            jScrollPane2.setPreferredSize(new Dimension(250, 250));

        }
        return jScrollPane2;


    }

    class Preview extends JLabel {

        private static final long serialVersionUID = 1L;

        public Preview() {
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (map != null) {
//                for (int i = 0; i < map.getLayerNum(); i++) {
                g.drawImage(bf, 0, 0, Color.WHITE, null);
//                }
            }
        }

        public void updateCanvas() {
            if (map == null) {
                return;
            }
            ImageIcon temp = null;
            temp = new ImageIcon(bf);
            setIcon(temp);
            repaint();
        }
    }
    /**
     * This method initializes jButton
     *
     * @return javax.swing.JButton
     */
    public int layerNum = 1;//默认为1层
    public int[][][][] undoMap;//撤销用的缓存数组
    public int[][][][] redoMap;//重做用的缓存数组
    public int nIndex = 0;

    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setText("确定");
            jButton.setLocation(new Point(255, 40));
            jButton.setSize(new Dimension(60, 20));
//            jButton.addActionListener(this);
            jButton.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    String mapName = jTmapName.getText();
                    boolean temb = true;
                    File[] f = new File(Project.getProjectPath() + "\\" + "data\\map").listFiles();
                    int ii = f.length;
                    for (int i = 0; i
                        < ii; i++) {
                        if (f[i].getName().equals(mapName + ".gat")) {
                            JOptionPane.showMessageDialog(getJContentPane(), "已有该地图，请重命名");
                            temb = false;

                            break;
                        }
                    }
                    if (jTLayNum.getText().equals("") || jTLayNum.getText().equals("0") || jTmapRow.getText().equals("") || jTmapRow.getText().equals("0")
                        || jTmapCol.getText().equals("") || jTmapCol.getText().equals("0") || jTcellW.getText().equals("") || jTcellW.getText().equals("0")
                        || jTcellH.getText().equals("") || jTcellH.getText().equals("0")) {
                        JOptionPane.showMessageDialog(getJContentPane(), "地图参数无效，请重新设置");
                        temb = false;
                    }
                    if (temb) {

                        String imageName = (String) jComboBox.getSelectedItem();
                        String musicName = (String) jComboBox3.getSelectedItem();
                        if (musicName == null) {
                            musicName = "";
                        }
                        int row = Integer.parseInt(jTmapRow.getText());
                        int col = Integer.parseInt(jTmapCol.getText());
                        int cellWidth = Integer.parseInt(jTcellW.getText());
                        int cellHeight = Integer.parseInt(jTcellH.getText());
                        layerNum = Integer.parseInt(jTLayNum.getText());
                        map = new Map(mapName, imageName, row, col, cellWidth,
                            cellHeight, layerNum);
                        map.setMusicName(musicName);
                        map.setIndex(mapVector.size());
                        mapVector.addMap(map);
                        jTable.updateTable();
                        mapCanvas.setVisible(true);
                        tileCanvas.setVisible(true);
                        undoMap = new int[10][layerNum][row][col];
                        redoMap = new int[10][layerNum][row][col];
//                        sl = new ScriptList();
//                        map.setScriptList(sl);
                        getEventDialog();
                        jtxEvent.setText("");
                        getJMenu1().repaint();
                        getPicJMenu().repaint();
                        currentLayer = 0;
                        slButton.setText("状态：绘图模式");
                        scButton.setText("当前图层：图层" + (currentLayer + 1));
                        initMapCanvas();
                        isFill = false;
                        isCollide = false;
                        isEvent = false;
                        isChoose = false;
                        isMapGrid = false;
                        penCheck.setSelected(true);
//                        ImgItem.setEnabled(true);
                        jDialog.setVisible(false);
                    }
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
            jButton1.setText("取消");
            jButton1.setSize(new Dimension(60, 20));
            jButton1.setLocation(new Point(255, 100));
//            jButton1.addActionListener(this);
            jButton1.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    jDialog.setVisible(false);
                }
            });
        }
        return jButton1;

    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJSplitPane(), BorderLayout.CENTER);

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
            jLabel5 = new JLabel();
            jLabel5.setText("地图高度(单元格数):");
            jLabel5.setBounds(10, 100, 120, 20);
            jLabel4 = new JLabel();
            jLabel4.setText("地图宽度(单元格数):");
            jLabel4.setBounds(10, 70, 120, 20);
            jLmapImg = new JLabel();
            jLmapImg.setText("地图图块:");
            jLmapImg.setBounds(10, 160, 60, 20);
            jLcellH = new JLabel();
            jLcellH.setText("单元格高度:");
            jLcellH.setBounds(130, 40, 80, 20);
            jLcellW = new JLabel();
            jLcellW.setText("单元格宽度:");
            jLcellW.setBounds(10, 40, 75, 20);
            jLmapName = new JLabel();
            jLmapName.setText("地图名称:");
            jLmapName.setBounds(10, 10, 60, 20);
            jLLayNum = new JLabel();
            jLLayNum.setText("图层数:");
            jLLayNum.setBounds(10, 130, 60, 20);
            JLabel jMapMusic = new JLabel();
            jMapMusic.setText("背景音乐:");
            jMapMusic.setBounds(10, 190, 60, 20);
            jContentPane1 = new JPanel();
            jContentPane1.setLayout(null);
            jContentPane1.add(jLmapName, null);
            jContentPane1.add(jLcellW, null);
            jContentPane1.add(jLcellH, null);
            jContentPane1.add(jLmapImg, null);
            jContentPane1.add(jMapMusic, null);
            jTmapName = new JTextField();
            jTmapName.setBounds(80, 10, 160, 20);
            jContentPane1.add(jTmapName, null);
            jTcellW = new JTextField();
            jTcellW.setBounds(80, 40, 40, 20);
            jContentPane1.add(jTcellW, null);
            jTcellH = new JTextField();
            jTcellH.setBounds(200, 40, 40, 20);
            jContentPane1.add(jTcellH, null);
            jComboBox = new JComboBox();
            jComboBox.setBounds(80, 160, 160, 20);
            jComboBox3 = new JComboBox();
            jComboBox3.setBounds(80, 190, 160, 20);
            jContentPane1.add(jComboBox, null);
            jContentPane1.add(jComboBox3, null);
            jContentPane1.add(getJButton(), null);
            jContentPane1.add(getJButton1(), null);
            jContentPane1.add(jLabel4, null);
            jContentPane1.add(jLabel5, null);
            jTmapCol = new JTextField();
            jTmapCol.setBounds(130, 70, 110, 20);
            jContentPane1.add(jTmapCol, null);
            jTmapRow = new JTextField();
            jTmapRow.setBounds(130, 100, 110, 20);
            jContentPane1.add(jTmapRow, null);
            jContentPane1.add(jLLayNum, null);
            jTLayNum = new JTextField();
            jTLayNum.setBounds(80, 130, 40, 20);
            jContentPane1.add(jTLayNum, null);
        }
        return jContentPane1;
    }
    private JTextField jTmapName2, jTcellW2, jTcellH2, jTmapCol2, jTmapRow2, jTLayNum2;
    private JLabel jLmapImg2;

    private JPanel getJContentPane3() {
        if (jContentPane3 == null) {
            jContentPane3 = new JPanel();
            jContentPane3.setLayout(null);
            jLabel5 = new JLabel();
            jLabel5.setText("地图高度(单元格数):");
            jLabel5.setBounds(10, 100, 120, 20);
            jLabel4 = new JLabel();
            jLabel4.setText("地图宽度(单元格数):");
            jLabel4.setBounds(10, 70, 120, 20);
            jLcellH = new JLabel();
            jLcellH.setText("单元格高度:");
            jLcellH.setBounds(130, 40, 80, 20);
            jLcellW = new JLabel();
            jLcellW.setText("单元格宽度:");
            jLcellW.setBounds(10, 40, 75, 20);
//            initTextFiled();
            jTmapCol2 = new JTextField();
            jTmapCol2.setBounds(130, 70, 110, 20);
//            jTmapCol2.setText("" + map.getCol());

            jTmapRow2 = new JTextField();
//            jTmapRow2.setText("" + map.getRow());
            jTmapRow2.setBounds(130, 100, 110, 20);

            jTmapName2 = new JTextField();
//            jTmapName2.setText(map.getName());
            jTmapName2.setBounds(80, 10, 160, 20);
            jTcellW2 = new JTextField();
//            jTcellW2.setText("" + map.getCellWidth());
            jTcellW2.setBounds(80, 40, 40, 20);

            jTcellH2 = new JTextField();
//            jTcellH2.setText("" + map.getCellHeight());
            jTcellH2.setBounds(200, 40, 40, 20);

            jComboBox2 = new JComboBox();
            jComboBox2.setBounds(80, 160, 160, 20);
            jComboBox4 = new JComboBox();
            jComboBox4.setBounds(80, 190, 160, 20);
            jTLayNum2 = new JTextField();
//            jTLayNum2.setText("" + map.getLayerNum());
            jTLayNum2.setBounds(80, 130, 40, 20);
            jContentPane3.add(jTmapCol2, null);

            jContentPane3.add(jTmapRow2, null);
            jLmapName = new JLabel();
            jLmapName.setText("地图名称:");
            jLmapName.setBounds(10, 10, 60, 20);

            jContentPane3.add(jTmapName2, null);

            jContentPane3.add(jTcellW2, null);

            jContentPane3.add(jTcellH2, null);
            jLLayNum = new JLabel();
            jLLayNum.setText("图层数:");
            jLLayNum.setBounds(10, 130, 60, 20);
            jLmapImg2 = new JLabel();
            jLmapImg2.setText("地图图块:");
            jLmapImg2.setBounds(10, 160, 60, 20);
            JLabel jMapMusic = new JLabel();
            jMapMusic.setText("背景音乐:");
            jMapMusic.setBounds(10, 190, 60, 20);
            jContentPane3.add(jMapMusic, null);
            jContentPane3.add(jComboBox2, null);
            jContentPane3.add(jComboBox4, null);
            jContentPane3.add(jLmapImg2, null);
            jContentPane3.add(jLmapName, null);
            jContentPane3.add(jLcellW, null);
            jContentPane3.add(jLcellH, null);
            jContentPane3.add(getJButton2(), null);
            jContentPane3.add(getJButton3(), null);
            jContentPane3.add(jLabel4, null);
            jContentPane3.add(jLabel5, null);
            jContentPane3.add(jLLayNum, null);

            jContentPane3.add(jTLayNum2, null);

        }

        return jContentPane3;
    }

    private void initTextFiled() {
        if (mapVector.isEmpty()) {//地图为空时不应初始化
            return;
        }
        System.out.println(map.getName());
        jTmapCol2.setText("" + map.getCol());
        jTmapRow2.setText("" + map.getRow());
        jTmapName2.setText(map.getName());
        jTcellW2.setText("" + map.getCellWidth());
        jTcellH2.setText("" + map.getCellHeight());
        jTLayNum2.setText("" + map.getLayerNum());
    }
    private JButton jButton2;

    private JButton getJButton2() {
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setText("确定");
            jButton2.setLocation(new Point(255, 40));
            jButton2.setSize(new Dimension(60, 20));
//            jButton.addActionListener(this);
            jButton2.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    boolean temb = true;
                    if (jTLayNum2.getText().equals("") || jTLayNum2.getText().equals("0") || jTcellW2.getText().equals("") || jTcellW2.getText().equals("0")
                        || jTcellH2.getText().equals("") || jTcellH2.getText().equals("0")) {
                        JOptionPane.showMessageDialog(getJContentPane(), "地图参数无效，请重新设置");
                        temb = false;
                    }
                    if (temb) {
                        String imageName = (String) jComboBox2.getSelectedItem();
                        String musicName = (String) jComboBox4.getSelectedItem();
                        map.setImage(imageName);
                        map.setMusicName(musicName);
                        String mapName = jTmapName2.getText();
                        map.setName(mapName);
                        int cellWidth = Integer.parseInt(jTcellW2.getText());
                        map.setCellWidth(cellWidth);
                        int cellHeight = Integer.parseInt(jTcellH2.getText());
                        map.setCellHeight(cellHeight);
                        upDateMap();
                        getJMenu1().repaint();
                        getPicJMenu().repaint();

                        getPreviewDialog();
                        initPreTitle();
                        initMapCanvas();
                        jDialog1.setVisible(false);
                    }
                }
            });
        }
        return jButton2;

    }
    private int newMap[][][], newWay[][];

    public void upDateMap() {
        newMap = new int[Integer.parseInt(jTLayNum2.getText())][Integer.parseInt(jTmapRow2.getText())][Integer.parseInt(jTmapCol2.getText())];
        newWay = new int[Integer.parseInt(jTmapRow2.getText())][Integer.parseInt(jTmapCol2.getText())];

        for (int ii = 0; ii
            < ((map.getMap().length < newMap.length) ? map.getMap().length : newMap.length); ii++) {
            for (int jj = 0; jj
                < ((map.getMap()[0].length < newMap[0].length) ? map.getMap()[0].length : newMap[0].length); jj++) {
                System.arraycopy(map.getMap()[ii][jj], 0, newMap[ii][jj], 0, ((map.getMap()[0][0].length < newMap[0][0].length) ? map.getMap()[0][0].length : newMap[0][0].length));
            }
        }
        for (int ii = 0; ii
            < ((map.getWay().length < newWay.length) ? map.getWay().length : newWay.length); ii++) {
            System.arraycopy(map.getWay()[ii], 0, newWay[ii], 0, ((map.getWay()[0].length < newWay[0].length) ? map.getWay()[0].length : newWay[0].length));

        }
        map.setCol(newMap[0][0].length);
        map.setRow(newMap[0].length);
        map.setLayerNum(newMap.length);
        layerNum = map.getLayerNum();
        upDateArray(
            newMap);
        map.update(newMap, newWay);

    }

    private void upDateArray(int[][][] ma) {
        undoMap = null;
        redoMap = null;
        undoMap = new int[10][ma.length][ma[0].length][ma[0][0].length];
        redoMap = new int[10][ma.length][ma[0].length][ma[0][0].length];
    }
    private JButton jButton3;

    private JButton getJButton3() {
        if (jButton3 == null) {
            jButton3 = new JButton();
            jButton3.setText("取消");
            jButton3.setSize(new Dimension(60, 20));
            jButton3.setLocation(new Point(255, 100));
//            jButton.addActionListener(this);
            jButton3.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    jDialog1.setVisible(false);
                }
            });

        }
        return jButton3;
    }
    private JPanel jContentPane3;
    private JDialog jDialog1;

    private JDialog getJDialog1() {
        if (jDialog1 == null) {
            jDialog1 = new JDialog(this);
            jDialog1.setSize(334, 262);
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialog1.getSize();
            jDialog1.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialog1.setTitle("地图属性修改");
            jDialog1.setResizable(false);
            jDialog1.setModal(true);
            jDialog1.setContentPane(getJContentPane3());

        }
        return jDialog1;

    }

    /**
     * This method initializes jDialog
     *
     * @return javax.swing.JDialog
     */
    private JDialog getJDialog() {
        if (jDialog == null) {
            jDialog = new JDialog(this);
            jDialog.setSize(334, 262);
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialog.getSize();
            jDialog.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialog.setTitle("新建地图");
            jDialog.setResizable(false);
            jDialog.setModal(true);
            jDialog.setContentPane(getJContentPane1());

        }
        return jDialog;
    }
    private JLabel jT = null;
    private JRadioButton jcl1 = null;
    private JRadioButton jcl2 = null;

    private JRadioButton getjcl2Button() {
        if (jcl2 == null) {
            jcl2 = new JRadioButton("仅包含图层");
            jcl2.setToolTipText("选择内容仅包含图层");
            jcl2.setFocusable(false);
            jcl2.setSelected(true);
            jcl2.setBounds(10, 55, 120, 20);
            jcl2.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    isAll = false;
                    if (map != null) {
                        mapCanvas.updateCanvas();
                    }
                }
            });
        }
        return jcl2;

    }

    private JRadioButton getjcl1Button() {
        if (jcl1 == null) {
            jcl1 = new JRadioButton("包含图层和碰撞");
            jcl1.setToolTipText("选择内容包含图层和碰撞");
            jcl1.setFocusable(false);
//            penCheck.addActionListener(this);
            jcl1.setBounds(10, 30, 120, 20);
            jcl1.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    isAll = true;
                    if (map != null) {
                        mapCanvas.updateCanvas();
                    }
                }
            });

        }
        return jcl1;
    }

    private JPanel getJContentPane2() {
        if (jContentPane2 == null) {
            jContentPane2 = new JPanel();
            jLabelcolor = new JLabel("背景色");
            jLabelcolor.setBounds(10, 10, 50, 20);
            jbColor = new JButton("设置");
            jbColor.setBounds(70, 10, 60, 20);
            jT = new JLabel("透明度");
            jT.setBounds(10, 50, 50, 20);
//            jChoseLabel = new JLabel("选择模式");
//            jChoseLabel.setBounds(10, 100, 65, 20);
//            jbColor.addActionListener(this);
            jbColor.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Color c = JColorChooser.showDialog(getJContentPane(), "选择背景色", Color.GRAY);
                    jPanel.setBackground(c);
                    jPanel1.setBackground(c);
                }
            });
            jContentPane2.setLayout(null);
            jContentPane2.add(jLabelcolor, null);
            jContentPane2.add(jbColor, null);
            jContentPane2.add(jT, null);
//            jContentPane2.add(jChoseLabel, null);
            jContentPane2.add(getJSliderSet(), null);
            jContentPane2.add(getRadioButtonJpanel(), null);
            jContentPane2.add(getRadioButtonJpanel2(), null);
//            jContentPane2.add(getjcl2Button(), null);
        }
        return jContentPane2;
    }
    private JPanel radioButtonJpanel;
    private JPanel radioButtonJpanel2;

    private JPanel getRadioButtonJpanel2() {
        if (radioButtonJpanel2 == null) {
            radioButtonJpanel2 = new JPanel();
            radioButtonJpanel2.setLayout(null);
            radioButtonJpanel2.setBounds(165, 100, 140, 95);
            buttonGroup5.add(getjcl3Button());
            buttonGroup5.add(getjcl4Button());
            radioButtonJpanel2.add(getjcl3Button(), null);
            radioButtonJpanel2.add(getjcl4Button(), null);
            radioButtonJpanel2.setBorder(BorderFactory.createTitledBorder("事件模式"));

        }
        return radioButtonJpanel2;
    }

    private JRadioButton getjcl3Button() {
        if (jcl3 == null) {
            jcl3 = new JRadioButton("常规模式");
            jcl3.setToolTipText("常规模式");
            jcl3.setFocusable(false);
//            penCheck.addActionListener(this);
            jcl3.setBounds(10, 30, 120, 20);
            jcl3.setSelected(true);
            jcl3.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    eventType = 1;
                }
            });

        }
        return jcl3;
    }
    private JRadioButton jcl3, jcl4;

    private JRadioButton getjcl4Button() {
        if (jcl4 == null) {
            jcl4 = new JRadioButton("脚本模式");
            jcl4.setToolTipText("脚本模式");
            jcl4.setFocusable(false);
//            penCheck.addActionListener(this);
            jcl4.setBounds(10, 55, 120, 20);
            jcl4.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    eventType = 0;
                }
            });

        }
        return jcl4;
    }
    private ButtonGroup buttonGroup5 = new ButtonGroup();

    private JPanel getRadioButtonJpanel() {
        if (radioButtonJpanel == null) {
            radioButtonJpanel = new JPanel();
            radioButtonJpanel.setLayout(null);
            radioButtonJpanel.setBounds(10, 100, 140, 95);
            buttonGroup4.add(getjcl1Button());
            buttonGroup4.add(getjcl2Button());
            radioButtonJpanel.add(getjcl1Button(), null);
            radioButtonJpanel.add(getjcl2Button(), null);
            radioButtonJpanel.setBorder(BorderFactory.createTitledBorder("选择模式"));
        }
        return radioButtonJpanel;
    }
//    private JLabel jChoseLabel;

    private JDialog getJDialogSet() {
        if (jdSet == null) {
            jdSet = new JDialog(this);
            jdSet.setSize(324, 232);
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jdSet.getSize();
            jdSet.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jdSet.setTitle("设置");
            jdSet.setResizable(false);
            jdSet.setModal(true);
            jdSet.setContentPane(getJContentPane2());
        }
        return jdSet;

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
            jJMenuBar.add(getEditJMenu());
            jJMenuBar.add(getPicJMenu());
            jJMenuBar.add(getJMenu1());
            jJMenuBar.add(getSetJMenu());
            jJMenuBar.add(getPluginJMenu());
            jJMenuBar.add(getGameMenu());
            try {
                JMenu[] menus = null;
                if (pl.getMenu() != null) {
                    menus = pl.getMenu();
                }

                if (menus != null) {
                    int ii = menus.length;
                    for (int i = 0; i
                        < ii; i++) {
                        if (menus[i] != null) {
                            jJMenuBar.add(menus[i]);
                        }
                    }
                }
            } catch (Exception e) {
                // TODO 自动生成 catch 块
            }
            jJMenuBar.add(getJMenuHelp());
        }
        return jJMenuBar;
    }
    private JMenu gameMenu;

    private JMenu getGameMenu() {
        if (gameMenu == null) {
            gameMenu = new JMenu("游戏(G)");
            gameMenu.setMnemonic('G');
            gameMenu.add(getJMenuItem17());
            gameMenu.add(getGameItem());
            gameMenu.add(getJMenuItem18());

            gameMenu.add(getJMenuItem1());
        }
        return gameMenu;
    }
//    private Process openG;
    private JMenuItem jMenuItem17 = null;
    private JMenuItem jMenuItem1 = null;
//    Process gameP;

    private void copyGameFiles(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i
            < files.length; i++) {
            if (files[i].getName().equals("生成的游戏")) {
                continue;
            }
            if (files[i].isFile()) {
                try {
                    FileInputStream input = new FileInputStream(files[i]);
                    FileOutputStream output = new FileOutputStream("product"
                        + file.getPath().substring(Project.getProjectPath().length()) + "\\"
                        + (files[i].getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                } catch (IOException e) {
                }
            } else {
                File tempFile = new File("product"
                    + files[i].getPath().substring(Project.getProjectPath().length()));
                tempFile.mkdirs();
                copyGameFiles(
                    files[i]);
            }
        }
    }
    //数据完整性检查

//    private boolean checkFile(File file, String check) {
//        if (file.getName().equals(check)) {
//            return true;
//        }
//        return false;
//    }
    private String checkData() {
        File f = new File(Project.getProjectPath() + "\\data");
        File[] ff = f.listFiles();
        int n[] = new int[9];
        for (int i = 0; i < ff.length; i++) {
            if (ff[i].getName().equals("ani.gat")) {
                n[0] = 1;
                continue;
            }
            if (ff[i].getName().equals("enemy.gat")) {
                n[1] = 1;
                continue;
            }
            if (ff[i].getName().equals("enemytroop.gat")) {
                n[2] = 1;
                continue;
            }
            if (ff[i].getName().equals("equip.gat")) {
                n[3] = 1;
                continue;
            }
            if (ff[i].getName().equals("item.gat")) {
                n[4] = 1;
                continue;
            }
            if (ff[i].getName().equals("map")) {
                n[5] = 1;
                continue;
            }
            if (ff[i].getName().equals("player.gat")) {
                n[6] = 1;
                continue;
            }
            if (ff[i].getName().equals("system.gat")) {
                n[7] = 1;
                continue;
            }
            if (ff[i].getName().equals("skill.gat")) {
                n[8] = 1;
                continue;
            }
        }
        if (n[0] != 1) {
            return "动画文件缺失！请用动画编辑器编辑";
        }
        if (n[1] != 1) {
            return "敌人文件缺失！请用数据编辑器编辑";
        }
        if (n[2] != 1) {
            return "队伍文件缺失！请用数据编辑器编辑";
        }
        if (n[3] != 1) {
            return "装备文件缺失！请用数据编辑器编辑";
        }
        if (n[4] != 1) {
            return "物品文件缺失！请用数据编辑器编辑";
        }
        if (n[5] != 1) {
            return "地图文件缺失！请用数据编辑器编辑";
        }
        if (n[6] != 1) {
            return "角色文件缺失！请用数据编辑器编辑";
        }
        if (n[7] != 1) {
            return "系统文件缺失！请用数据编辑器编辑";
        }
        if (n[8] != 1) {
            return "技能文件缺失！请用数据编辑器编辑";
        }



//        //检查文件名
//        if (!checkFile(ff[0], "ani.gat")
//            || !checkFile(ff[1], "enemy.gat")
//            || !checkFile(ff[2], "enemytroop.gat")
//            || !checkFile(ff[3], "equip.gat")
//            || !checkFile(ff[4], "item.gat")
//            || !checkFile(ff[5], "map")
//            || !checkFile(ff[6], "player.gat")
//            || !checkFile(ff[7], "skill.gat")
//            || !checkFile(ff[8], "system.gat")) {
//            return false;
//        }
        File mf = new File(Project.getProjectPath() + "\\data\\map");
        File[] mff = mf.listFiles();
        if (mff.length < 1) {
            return "地图文件缺失!请新建并保存地图";//加入没有地图，返回false
        }
        return "生成成功！";
    }
    //脚本转换函数

    private void converterScript() {
//        FinalScript fs = new FinalScript();
        File f = new File(Project.getProjectPath() + "\\data\\map");
        File[] ff = f.listFiles();
        if (mapFinalVector != null) {
            mapFinalVector.delAllMap();
        }
        mapFinalVector = new MapList();
        for (int i = 0; i
            < ff.length; i++) {
            if (ff[i].getName().endsWith(".gat")) {
                loadFinalMapVector(ff[i]);
            }
        }
        int sum = mapFinalVector.size();
        System.out.println("mapFinalVector length: " + sum);
//        int[] n = new int[sum];
        for (int i = 0; i < sum; i++) {
            Map mapF = mapFinalVector.mapAt(i);
            int row = mapF.getRow();
            int col = mapF.getCol();
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    if (mapF.getScriptType()[j][k] != 0) {
//                        n[i]++;
                        CommandSet cs = Converter.convert(mapFinalVector.mapAt(i), j, k);
                        mapF.setCommandSet(cs);
                        System.out.println(cs);
                    }
                }
            }
            mapFinalVector.setMap(mapF, i);
        }


//        for (int i = 0; i < sum; i++) {
//            System.out.println("n[i]: " + n[i]);
//        }
    }
    private MapList mapFinalVector;

    private void loadFinalMapVector(File file) {
        mapFinalVector.addMap(loadMap(file));
    }

    private void saveFinalMap(Map myMap) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            File f = new File("product" + "\\" + "data\\map");
            String path = f.getAbsolutePath();
            fos = new FileOutputStream(path + "/" + "map" + myMap.getIndex() + ".gat");
            dos = new DataOutputStream(fos);
            dos.writeInt(myMap.getIndex());
            dos.writeUTF(myMap.getName());// 写入地图名称
            dos.writeUTF(myMap.getImageName());// 写入源图片名称
            dos.writeUTF(myMap.getMusicName());// 
            dos.writeByte(myMap.getRow());// 写入地图行数
            dos.writeByte(myMap.getCol());// 写入地图列数
            dos.writeByte(myMap.getCellWidth());// 写入单元格宽度
            dos.writeByte(myMap.getCellHeight());// 写入单元格高度
            dos.writeByte(myMap.getLayerNum());// 写入图层数
            // 写入地图图块编号数据
            for (int i = 0; i
                < myMap.getLayerNum(); i++) {
                for (int j = 0; j
                    < myMap.getRow(); j++) {
                    for (int k = 0; k
                        < myMap.getCol(); k++) {
                        dos.writeInt(myMap.getMap()[i][j][k]);
                    }
                }
            }
            // 写入地图通行度数据
            for (int j = 0; j
                < myMap.getRow(); j++) {
                for (int k = 0; k
                    < myMap.getCol(); k++) {
                    dos.writeByte(myMap.getWay()[j][k]);
                }
            }
            int sum = 0;
            for (int j = 0; j
                < myMap.getRow(); j++) {
                for (int k = 0; k
                    < myMap.getCol(); k++) {
                    byte t = myMap.getScriptType()[j][k];
                    if (t != 0) {
                        sum++;
                    }
                    dos.writeByte(t);
                }
            }
            dos.writeInt(sum);//脚本语句数
            //写入commandset
            for (int j = 0; j
                < myMap.getRow(); j++) {
                for (int k = 0; k
                    < myMap.getCol(); k++) {
                    byte t = myMap.getScriptType()[j][k];
                    if ((t != 0) && (myMap.getScriptList()[j][k] != null)) {
                        CommandSet sc = myMap.getCommandSet()[j][k];
                        int si = sc.command.length;
                        dos.writeByte(t);
                        dos.writeInt(j);
                        dos.writeInt(k);
                        dos.writeInt(si);
                        System.out.println("si " + si);
                        for (int i = 0; i
                            < si; i++) {
                            Command co = sc.command[i];
                            Byte b = co.type;
                            System.out.println(b);
                            dos.writeByte(b);
                            switch (b) {
                                case Command.ENDWHILE:
                                case Command.BREAK:
                                case Command.CONTINUE:
                                    dos.writeUTF("");
                                    dos.writeInt(co.nextIndex);
                                    continue;
                                case Command.ENDIF:
                                case Command.EXIT:
                                case Command.GAMEOVER:
                                    dos.writeUTF("");
                                    dos.writeInt(-1);
                                    continue;
                                default:
                                    System.out.println("hava param");
                                    dos.writeUTF(co.param);
                                    dos.writeInt(co.nextIndex);
                                    break;
                            }
                        }
                    }
                }
            }
            fos.close();
            dos.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("saveFinalMapVector Error");
        }
    }
//    private Emulator emulator = null;
//
//    private Emulator getEmulator() {
//        if (emulator == null) {
//            emulator = new Emulator(this);
//        }
//        return emulator;
//    }
    private JMenuItem createGameItem = null;

    private JMenuItem getGameItem() {
        if (createGameItem == null) {
            createGameItem = new JMenuItem();
            createGameItem.setText("生成游戏");
            createGameItem.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!checkData().equals("生成成功！")) {
                        JOptionPane.showMessageDialog(mapEditor, checkData());
                        return;
                    }
                    FileUtil.createDir(Project.getProjectPath() + "\\生成的游戏");
                    FileUtil.delAllFile(Project.getProjectPath() + "\\生成的游戏");
                    FileUtil.delAllFile("product\\image");
                    FileUtil.delAllFile("product\\data");
                    FileUtil.delAllFile("product\\audio");


                    copyGameFiles(new File(Project.getProjectPath()));//将源数据拷贝到product文件夹下
                    converterScript();//将原始转换为最终脚本
                    for (int i = 0, n = mapFinalVector.size(); i
                        < n; i++) {
                        saveFinalMap(mapFinalVector.mapAt(i));//重新存入地图
                    }
                    if (new File("product\\Game.jar").exists()) {
                        FileUtil.copy(new File("product\\Game.jar"), new File(Project.getProjectPath() + "\\生成的游戏"));
                        if (new File(Project.getProjectPath() + "\\生成的游戏\\Game.jar").exists()) {
                            JOptionPane.showMessageDialog(mapEditor, "生成成功！");
                        } else {
                            JOptionPane.showMessageDialog(mapEditor, "生成失败！");
                        }
                    } else {
                        System.out.println("Game.jar不存在");
                    }
                }
            });
        }
        return createGameItem;
    }

    private JMenuItem getJMenuItem17() {
        if (jMenuItem17 == null) {
            jMenuItem17 = new JMenuItem();
            jMenuItem17.setText("测试");
            jMenuItem17.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {


                    if (!getIsHasProject() || Project.getProjectName().equals("")) {
                        JOptionPane.showMessageDialog(mapEditor, "请先新建项目");
                        return;
                    }

                    if (!checkData().equals("生成成功！")) {
                        JOptionPane.showMessageDialog(mapEditor, checkData());
                        return;
                    }
                    FileUtil.createDir(Project.getProjectPath() + "\\生成的游戏");
                    FileUtil.delAllFile(Project.getProjectPath() + "\\生成的游戏");
                    FileUtil.delAllFile("product\\image");
                    FileUtil.delAllFile("product\\data");
                    FileUtil.delAllFile("product\\audio");


                    copyGameFiles(new File(Project.getProjectPath()));//将源数据拷贝到product文件夹下
                    converterScript();//将原始转换为最终脚本
                    for (int i = 0, n = mapFinalVector.size(); i
                        < n; i++) {
                        saveFinalMap(mapFinalVector.mapAt(i));//重新存入地图
                    }
                    Emulator emulator = new Emulator(mapEditor);
                    emulator.setVisible(true);
                    emulator.start();
//                    try {
//                        java.awt.Desktop.getDesktop().open(new File("command\\game.vbs"));
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                    try {
//                        Thread.sleep(2000);//延迟两秒再复制，等待生产
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
//                    }

//                    try {
//                    FileUtil.copy(new File("product\\Game.jar"), new File(Project.getProjectPath()));
//
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
////                        Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
//                    }

                }
            });
        }
        return jMenuItem17;
    }

    private JMenuItem getJMenuItem1() {
        if (jMenuItem1 == null) {
            jMenuItem1 = new JMenuItem();
            jMenuItem1.setText("打开游戏文件夹");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    System.out.println("open");
//                    String command = "command\\open.bat";

                    if (!getIsHasProject() || Project.getProjectName().equals("")) {
                        JOptionPane.showMessageDialog(mapEditor, "请先新建项目");
                        return;
                    }
//                    if (openG != null) {
//                        openG.destroy();
//                    }
                    try {
                        java.awt.Desktop.getDesktop().open(new File(Project.getProjectPath() + "\\生成的游戏"));
                    } catch (IOException ex) {
                        Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    try {
//                        String[] cmd = new String[5];
//                        cmd[0] = "cmd";
//                        cmd[1] = "/c";
//                        cmd[2] = "start";
//                        cmd[3] = " ";
//                        cmd[4] = Project.getProjectPath() + "\\product ";
////                        cmd[4] = Project.getProjectDirName() + "\\product";
//                        Runtime.getRuntime().exec(cmd);
//                    } catch (IOException ee) {
//                        ee.printStackTrace();
//                    }

//                    try {
//                        openG = Runtime.getRuntime().exec(command);
//
//                    } catch (Exception ee) {
//                        // TODO Auto-generated catch block
//                        System.out.println("open Error");
//                    }

                }
            });
        }
        return jMenuItem1;
    }
    private JMenuItem jMenuItem18;

    /**
     * This method initializes jMenuItem18
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem18() {
        if (jMenuItem18 == null) {
            jMenuItem18 = new JMenuItem();
            jMenuItem18.setText("更改标题");
            jMenuItem18.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    System.out.println("open");
                    if (!getIsHasProject()) {
                        JOptionPane.showMessageDialog(mapEditor, "请先新建项目");
                        return;
                    }
                    getChangeNameDialog();
                    changeDialog.setVisible(true);
                }
            });
        }
        return jMenuItem18;
    }
    private JDialog changeDialog;

    private JDialog getChangeNameDialog() {
        if (changeDialog == null) {
            changeDialog = new JDialog();
            changeDialog.setSize(280, 121);
//        this.setSize(240, 160);
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = changeDialog.getSize();
            changeDialog.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            changeDialog.setTitle("更改标题");
            changeDialog.setModal(true);
            changeDialog.setResizable(false);
            changeDialog.setContentPane(getNamePanel());
        }
        return changeDialog;
    }
    private JPanel changePanel;
    private JTextField jt;

    private JPanel getNamePanel() {
        if (changePanel == null) {
            changePanel = new JPanel();
            changePanel.setLayout(null);
            JLabel jtb = new JLabel("标题：");
            jtb.setBounds(10, 5, 60, 20);
            jt = new JTextField();
            jt.setBounds(10, 30, 250, 20);
            jt.setText(Project.getProjectName());
            changePanel.add(jt, null);
            changePanel.add(jtb, null);
            changePanel.add(getOK(), null);
            changePanel.add(getCancle(), null);
        }
        return changePanel;
    }
    private JButton ok, cancle;

    private JButton getOK() {
        if (ok == null) {
            ok = new JButton("确定");
            ok.setBounds(130, 60, 60, 20);
            ok.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    changeName();
                    changeDialog.setVisible(false);

                }
            });
        }
        return ok;
    }

    private JButton getCancle() {
        if (cancle == null) {
            cancle = new JButton("取消");
            cancle.setBounds(200, 60, 60, 20);
            cancle.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    changeDialog.setVisible(false);
                }
            });
        }
        return cancle;
    }

    private void changeName() {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
//                System.out.println("save: " + newFile.getParent());
////                            ImageUtil.writeImage(newImg, newFile, "png");
            Project.setProjectName(jt.getText());
            File newFile = new File(Project.getProjectPath() + "\\" + "Game.project");
//                String path = newFile.getAbsolutePath();
            fos = new FileOutputStream(newFile);
            dos = new DataOutputStream(fos);
            dos.writeUTF(Project.getProjectName());

            fos.close();
            dos.close();
        } catch (Exception ee) {
            System.out.println("new Error");
        }
        changeTitle();
    }

    /**
     * This method initializes jMenu
     *
     * @return javax.swing.JMenu
     */
    private JMenu getJMenu() {
        if (jMenu_File == null) {
            jMenu_File = new JMenu();
            jMenu_File.setText("文件(F)");
            jMenu_File.setMnemonic('F');
            jMenu_File.add(getNewPro());
            jMenu_File.add(getOpenPro());
            jMenu_File.add(getSavePro());
//            jMenu_File.add(getClosePro());
//            jMenu_File.add(getJMenuItem());
//            jMenu_File.add(getJMenuItem3());
            jMenu_File.add(getJMenuItemExit());
        }
        return jMenu_File;
    }

    private JMenu getSetJMenu() {
        if (jMenu_Set == null) {
            jMenu_Set = new JMenu();
        }
        jMenu_Set.setText("工具(T)");
        jMenu_Set.setMnemonic('T');
        jMenu_Set.add(getdataItem());
        jMenu_Set.add(getAnimationItem());
//        jMenu_Set.add(getJMenuItem15());
//        jMenu_Set.add(getMusicMenuItem());
//        jMenu_Set.add(getMapItem());
//        jMenu_Set.add(getPicSaveItem());
//        jMenu_Set.add(getRotateButton());
//        jMenu_Set.add(getRepaintItem());
        jMenu_Set.add(getSetItem());
        return jMenu_Set;
    }

    private JMenu getPluginJMenu() {
        if (jMenu_Plugin == null) {
            jMenu_Plugin = new JMenu();
        }
        jMenu_Plugin.setText("插件(P)");
        jMenu_Plugin.setMnemonic('P');
        try {
            JMenuItem[] menuItems = null;
            if (pl.getMenuItem() != null) {
                menuItems = pl.getMenuItem();
            }
            if (menuItems != null) {
                int ii = menuItems.length;
                for (int i = 0; i
                    < ii; i++) {
                    if (menuItems[i] != null) {
                        jMenu_Plugin.add(menuItems[i]);
                    }
                }
            }
        } catch (Exception e) {
            // TODO 自动生成 catch 块
        }
        return jMenu_Plugin;
    }

    private JMenu getPicJMenu() {
        if (jMenu_pic == null) {
            jMenu_pic = new JMenu();
        }
        jMenu_pic.setText("视图(V)");
        jMenu_pic.setMnemonic('V');
        buttonGroup2.add(getJMenuPicItem1());
        buttonGroup2.add(getJMenuPicItem2());
        buttonGroup2.add(getJMenuPicItem3());
        jMenu_pic.add(getJMenuPicItem1());
        jMenu_pic.add(getJMenuPicItem2());
        jMenu_pic.add(getJMenuPicItem3());
        return jMenu_pic;

    }

    private JMenuItem getUndoItem() {
        if (jUndoItem == null) {
            jUndoItem = new JMenuItem();
        }
        jUndoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
            InputEvent.CTRL_MASK));
        jUndoItem.setText("撤销");
        jUndoItem.addActionListener(this);
        jUndoItem.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (map != null) {
                    nIndex--;
                    if (nIndex < 0) {
                        nIndex = 0;
                    }
                    for (int k = 0; k
                        < map.getLayerNum(); k++) {
                        for (int i = 0; i
                            < map.getRow(); i++) {
                            System.arraycopy(undoMap[nIndex][k][i], 0, map.getMap()[k][i], 0, map.getCol());
                        }
                    }
                    mapCanvas.updateCanvas();
                }
            }
        });
        return jUndoItem;

    }

    private JMenuItem getRedoItem() {
        if (jRedoItem == null) {
            jRedoItem = new JMenuItem();
        }
        jRedoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
            InputEvent.CTRL_MASK));
        jRedoItem.setText("恢复");
//        jRedoItem.addActionListener(this);
        jRedoItem.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (map != null) {
                    for (int k = 0; k
                        < map.getLayerNum(); k++) {
                        for (int i = 0; i
                            < map.getRow(); i++) {
                            System.arraycopy(redoMap[nIndex][k][i], 0, map.getMap()[k][i], 0, map.getCol());
                        }
                    }
                    mapCanvas.updateCanvas();
                    nIndex++;

                    if (nIndex > 9) {
                        nIndex = 9;
                    }
                }
            }
        });
        return jRedoItem;
    }

    private JMenu getEditJMenu() {
        if (jMenu_edit == null) {
            jMenu_edit = new JMenu();
        }
        jMenu_edit.add(getUndoItem());
        jMenu_edit.add(getRedoItem());
        jMenu_edit.setMnemonic('E');
        jMenu_edit.setText("编辑(E)");

        return jMenu_edit;
    }

    private JRadioButtonMenuItem getJMenuPicItem1() {
        if (jMenuPicItem1 == null) {
            jMenuPicItem1 = new JRadioButtonMenuItem();
        }
        jMenuPicItem1.setText("当前及下一层");
        jMenuPicItem1.setAccelerator(KeyStroke.getKeyStroke("F2"));
//        jMenuPicItem1.addActionListener(this);
        jMenuPicItem1.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                type = 1;

                if (map != null) {
                    mapCanvas.updateCanvas();
                }
            }
        });
        return jMenuPicItem1;

    }

    private JRadioButtonMenuItem getJMenuPicItem2() {
        if (jMenuPicItem2 == null) {
            jMenuPicItem2 = new JRadioButtonMenuItem();
        }
        jMenuPicItem2.setText("全部层");
        jMenuPicItem2.setAccelerator(KeyStroke.getKeyStroke("F3"));
        jMenuPicItem2.setSelected(true);
//        jMenuPicItem2.addActionListener(this);
        jMenuPicItem2.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                type = 0;
                if (map != null) {
                    mapCanvas.updateCanvas();
                }
            }
        });
        return jMenuPicItem2;
    }

    private JRadioButtonMenuItem getJMenuPicItem3() {
        if (jMenuPicItem3 == null) {
            jMenuPicItem3 = new JRadioButtonMenuItem();
        }
        jMenuPicItem3.setText("当前层");
        jMenuPicItem3.setAccelerator(KeyStroke.getKeyStroke("F4"));
//        jMenuPicItem3.addActionListener(this);
        jMenuPicItem3.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                type = 2;
                if (map != null) {
                    mapCanvas.updateCanvas();
                }
            }
        });
        return jMenuPicItem3;

    }

    /**
     * This method initializes jMenu1
     *
     * @return javax.swing.JMenu
     */
    public JMenu getJMenu1() {
        if (jMenu_Lay == null) {
            jMenu_Lay = new JMenu();
        }
        jMenu_Lay.removeAll();
        jMenu_Lay.setText("图层(L)");
        jMenu_Lay.setMnemonic('L');
        menuItem = new JRadioButtonMenuItem[layerNum];
        int ii = menuItem.length;
        for (int i = 0; i
            < ii; i++) {
            JRadioButtonMenuItem tempItem = new JRadioButtonMenuItem();
            tempItem.setText("图层" + (i + 1));
            tempItem.setAccelerator(KeyStroke.getKeyStroke("F" + (i + 5)));
            tempItem.addActionListener(this);
            menuItem[i] = tempItem;
        }
        for (int i = 0; i
            < ii; i++) {
            buttonGroup.add(menuItem[i]);
        }
        for (int i = 0; i
            < ii; i++) {
            jMenu_Lay.add(menuItem[i]);
        }
        menuItem[0].setSelected(true);
        return jMenu_Lay;
    }
    private DataManager dm = null;
    // 初始化数据管理器

    private void creatDM() {
        if (dm == null) {
            dm = new DataManager(this);
            dm.load();
        }

    }

    public void actionPerformed(ActionEvent e) {
        System.gc();
        int ii = menuItem.length;
        for (int i = 0; i
            < ii; i++) {
            if (e.getSource() == menuItem[i]) {
                currentLayer = i;
                break;
            }
        }
//        isCollide = false;
//        isFill = false;
        if (map != null) {
            mapCanvas.updateCanvas();
        }
        slButton.setText("状态：绘图模式");
        scButton.setText("当前图层：图层" + (currentLayer + 1));

    }

    /**
     * This method initializes jMenuItem
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem() {
        if (jMenuItem == null) {
            jMenuItem = new JMenuItem();
            jMenuItem.setText("新建地图");
//            jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
//                InputEvent.CTRL_MASK));
//            jMenuItem.addActionListener(this);
            jMenuItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!isHasProject) {
                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
                        return;
                    }

                    getJDialog();
                    initJComboBox();
                    initJComboBox3();
                    jDialog.setVisible(true);

                }
            });

        }
        return jMenuItem;
    }
    private JMenuItem newProjectItem;

    private JMenuItem getNewPro() {
        if (newProjectItem == null) {
            newProjectItem = new JMenuItem();
            newProjectItem.setText("新建工程");
            newProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_MASK));
//            jMenuItem.addActionListener(this);
            newProjectItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    System.out.println("new");
                    getJDialogNewPro();
                    jDialogNewPro.setVisible(true);

                }
            });
        }
        return newProjectItem;
    }

    private JButton getJButtonOpe() {
        if (jButtonOpe == null) {
            jButtonOpe = new JButton();
            jButtonOpe.setText("...");
            jButtonOpe.setSize(new Dimension(25, 20));
            jButtonOpe.setLocation(new Point(305, 40));
            jButtonOpe.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    JFileChooser jf = new JFileChooser();// 创建一个文件对话框
                    jf.setCurrentDirectory(new File("."));// 设置当前目录
                    jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 设置只能选择文件夹
                    jf.showOpenDialog(jContentPane);// 显示“打开”文件对话框
                    File file = jf.getSelectedFile();
                    if (file != null) {
                        jTextField1.setText(file.getPath());
                    }
                }
            });
        }
        return jButtonOpe;
    }

    private JButton getJButtonOk() {
        if (OkButton == null) {
            OkButton = new JButton();
            OkButton.setText("确定");
            OkButton.setSize(new Dimension(60, 20));
            OkButton.setLocation(new Point(195, 70));
            OkButton.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (jTextField.getText().equals("") || jTextField1.getText().equals("") || jTextField2.getText().equals("")) {
                        JOptionPane.showMessageDialog(getJContentPane(), "输入不能为空，请重新输入！");
                        return;
                    }
                    File f = new File(jTextField1.getText() + "\\"
                        + jTextField2.getText());// 根据输入的路径新建一个文件
                    if (!f.exists()) {
                        f.mkdirs();// 如果此文件夹不存在则创建此文件夹
                    }

//                    projectPath = f.getPath();
                    Project.setProjectDirName(jTextField2.getText());
                    Project.setProjectName(jTextField.getText());
                    Project.setProjectPath(f.getPath());
                    System.out.println(Project.getProjectPath());
                    changeTitle();
                    copyFiles(
                        new File("res"));
                    FileOutputStream fos = null;
                    DataOutputStream dos = null;
                    try {
//                System.out.println("save: " + newFile.getParent());
////                            ImageUtil.writeImage(newImg, newFile, "png");
                        File newFile = new File(Project.getProjectPath() + "\\" + "Game.project");
//                String path = newFile.getAbsolutePath();
                        fos = new FileOutputStream(newFile);
                        dos = new DataOutputStream(fos);
                        dos.writeUTF(Project.getProjectName());
                        isHasProject = true;

                        fos.close();
                        dos.close();
                    } catch (Exception ee) {
                        System.out.println("new Error");
                    }
                    initMapVector();
                    creatAM();
//                    creatIM();
                    creatDM();
//                    creatMM();

                    jDialogNewPro.setVisible(false);
                }
            });
        }
        return OkButton;
    }
    private boolean isHasProject = false;

    public boolean getIsHasProject() {
        return isHasProject;
    }

    private void initMapVector() {
//        for (int i = 0, n = mapVector.size(); i
//            < n; i++) {
//            mapVector.delMap(i);
//
//        }
        if (mapVector != null) {
            mapVector.delAllMap();
        }
//        if (mapFinalVector != null) {
//            mapFinalVector.delAllMap();
//        }
        mapVector = new MapList();
//        mapFinalVector = new MapList();
        currentMapIndex = 0;
//        currentLayer = 0;
        jTable.updateTable();

    } //    private static String projectPath = ".";// 保存游戏目录  //  @jve:decl-index=0:
    // 将文件夹复制到游戏工作目录下

    private void copyFiles(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i
            < files.length; i++) {
            if (files[i].isFile()) {
                try {
                    FileInputStream input = new FileInputStream(files[i]);
                    FileOutputStream output = new FileOutputStream(Project.getProjectPath()
                        + file.getPath().substring(3) + "\\"
                        + (files[i].getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                } catch (IOException e) {
                }
            } else {
                File tempFile = new File(Project.getProjectPath()
                    + files[i].getPath().substring(3));
                tempFile.mkdirs();
                copyFiles(
                    files[i]);
            }
        }
    }
    /**
     * This method initializes jButton2
     *
     * @return javax.swing.JButton
     */
    private JButton calcleButton, OkButton;

    private JButton getJButtonCal() {
        if (calcleButton == null) {
            calcleButton = new JButton();
            calcleButton.setText("取消");
            calcleButton.setSize(new Dimension(60, 20));
            calcleButton.setLocation(new Point(270, 70));
            calcleButton.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    jDialogNewPro.setVisible(false);
                }
            });
        }
        return calcleButton;

    }
    private JButton jButtonOpe;
    private JTextField jTextField1 = null;
    private JDialog jDialogNewPro;

    private JDialog getJDialogNewPro() {
        if (jDialogNewPro == null) {
            jDialogNewPro = new JDialog(this);
            jDialogNewPro.setSize(new Dimension(355, 133));
            Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
            Dimension labelSize = jDialogNewPro.getSize();
            jDialogNewPro.setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
            jDialogNewPro.setTitle("新建工程");
            jDialogNewPro.setModal(true);
            jDialogNewPro.setResizable(false);
            jDialogNewPro.setContentPane(getJContentPane4());
        }
        return jDialogNewPro;
    }
    /**
     * This method initializes jContentPane1
     *
     * @return javax.swing.JPanel
     */
    private JLabel jLabel2;

    private JPanel getJContentPane4() {
        if (jContentPane4 == null) {
            jLabel1 = new JLabel();
            jLabel1.setText("位置：");
            jLabel1.setSize(new Dimension(40, 20));
//            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
//            jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel1.setLocation(new Point(5, 40));
            jLabel2 = new JLabel();
            jLabel2.setText("文件夹名：");
            jLabel2.setLocation(new Point(5, 10));
//            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
//            jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel2.setSize(new Dimension(80, 20));
            jLabel = new JLabel();
            jLabel.setText("标题：");
            jLabel.setLocation(new Point(190, 10));
//            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
//            jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            jLabel.setSize(new Dimension(40, 20));
            jContentPane4 = new JPanel();
            jContentPane4.setLayout(null);
            jContentPane4.add(jLabel, null);
            jContentPane4.add(jLabel1, null);
            jContentPane4.add(jLabel2, null);
            jContentPane4.add(getJTextField(), null);
            jContentPane4.add(getJTextField1(), null);
            jContentPane4.add(getJTextField2(), null);
            jContentPane4.add(getJButtonOpe(), null);
            jContentPane4.add(getJButtonCal(), null);
            jContentPane4.add(getJButtonOk(), null);
        }
        return jContentPane4;

    }

    /**
     * This method initializes jTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setLocation(new Point(230, 10));
            jTextField.setSize(new Dimension(100, 20));
//            jTextField.setText("newProject");
        }
        return jTextField;
    }
    private JTextField jTextField2;

    private JTextField getJTextField2() {
        if (jTextField2 == null) {
            jTextField2 = new JTextField();
            jTextField2.setLocation(new Point(70, 10));
            jTextField2.setSize(new Dimension(100, 20));

        }
        return jTextField2;
    }

    /**
     * This method initializes jTextField1
     *
     * @return javax.swing.JTextField
     */
    private JTextField getJTextField1() {
        if (jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setLocation(new Point(70, 40));
            jTextField1.setSize(new Dimension(230, 20));
        }
        return jTextField1;
    }
    private JTextField jTextField;
    private JLabel jLabel, jLabel1;
    private JPanel jContentPane4 = null;
    private JMenuItem saveProjectItem;

    private JMenuItem getSavePro() {
        if (saveProjectItem == null) {
            saveProjectItem = new JMenuItem();
            saveProjectItem.setText("保存工程");
            saveProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
//            jMenuItem.addActionListener(this);
            saveProjectItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!isHasProject) {
                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
                        return;
                    }
                    savePro();
                }
            });
        }
        return saveProjectItem;
    }

    private boolean saveProject() {
        boolean judge = false;
        try {
//            dm.saveIO();
            FileUtil.delAllFile(Project.getProjectPath() + "\\" + "data\\map");
            System.out.println("mapsize: " + mapVector.size());
            for (int i = 0, n = mapVector.size(); i
                < n; i++) {
//                if (mapVector.mapAt(i) instanceof Map) {
                saveMapVector(mapVector.mapAt(i));
//                }
            }
            judge = true;
        } catch (Exception ex) {
            judge = false;
            ex.printStackTrace();
        }
        return judge;
    }

    private void saveMapVector(Map myMap) {

        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            File f = new File(Project.getProjectPath() + "\\" + "data\\map");
            String path = f.getAbsolutePath();
            fos = new FileOutputStream(path + "/" + "map" + myMap.getIndex() + ".gat");
            dos = new DataOutputStream(fos);
//            dos.writeUTF(this.version);
            dos.writeInt(myMap.getIndex());
            dos.writeUTF(myMap.getName());// 写入地图名称
            dos.writeUTF(myMap.getImageName());// 写入源图片名称
            dos.writeUTF(myMap.getMusicName());// 
            dos.writeByte(myMap.getRow());// 写入地图行数
            dos.writeByte(myMap.getCol());// 写入地图列数
            dos.writeByte(myMap.getCellWidth());// 写入单元格宽度
            dos.writeByte(myMap.getCellHeight());// 写入单元格高度
            dos.writeByte(myMap.getLayerNum());// 写入图层数
            // 写入地图图块编号数据
            for (int i = 0; i
                < myMap.getLayerNum(); i++) {
                for (int j = 0; j
                    < myMap.getRow(); j++) {
                    for (int k = 0; k
                        < myMap.getCol(); k++) {
                        dos.writeInt(myMap.getMap()[i][j][k]);

                    }
                }
            }
            // 写入地图通行度数据
            for (int j = 0; j
                < myMap.getRow(); j++) {
                for (int k = 0; k
                    < myMap.getCol(); k++) {
                    dos.writeByte(myMap.getWay()[j][k]);
                }
            }
            int sum = 0;
            for (int j = 0; j
                < myMap.getRow(); j++) {
                for (int k = 0; k
                    < myMap.getCol(); k++) {
                    byte t = myMap.getScriptType()[j][k];
//                    System.out.print(t+" ");
                    if (t != 0) {
                        sum++;
                    }
                    dos.writeByte(t);
                }
            }
            dos.writeInt(sum);
            for (int j = 0; j
                < myMap.getRow(); j++) {
                for (int k = 0; k
                    < myMap.getCol(); k++) {
                    byte t = myMap.getScriptType()[j][k];
//                    System.out.print(t + " ");
//                    dos.writeByte(t);
                    if ((t != 0) && (myMap.getScriptList()[j][k] != null)) {
                        int si = myMap.getScriptList()[j][k].size();

                        dos.writeInt(j);
                        dos.writeInt(k);
                        dos.writeInt(si);
                        for (int i = 0; i
                            < si; i++) {
                            dos.writeUTF(myMap.getScriptList()[j][k].getString(i));
                        }
                    }
                }
            }
            fos.close();
            dos.close();
        } catch (Exception e) {
            System.out.println("saveMapVector Error");
        }
    }
    // 用于打开文件时的过滤器，文件格式为.png
    private JMenuItem OpenProjectItem;

    private JMenuItem getOpenPro() {
        if (OpenProjectItem == null) {
            OpenProjectItem = new JMenuItem();
            OpenProjectItem.setText("打开工程");
            OpenProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
//            jMenuItem.addActionListener(this);
            OpenProjectItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    openProject();

                }
            });
        }
        return OpenProjectItem;

    }

    private Map loadMap(File file) {
        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
//            dis.readUTF();
            int mapIndex = dis.readInt();
            String mapName = dis.readUTF();
            String imageName = dis.readUTF();
            String musicName = dis.readUTF();
            int row = dis.readByte();
            int col = dis.readByte();
            int cellWidth = dis.readByte();
            int cellHeight = dis.readByte();
            int layerNumL = dis.readByte();
            model.Map mapL = new model.Map(mapName, imageName, row, col, cellWidth,
                cellHeight, layerNumL);
            mapL.setMusicName(musicName);
            mapL.setIndex(mapIndex);
            undoMap = new int[10][layerNumL][row][col];
            redoMap = new int[10][layerNumL][row][col];
            // 读取地图图块编号数据
            for (int i = 0; i
                < mapL.getLayerNum(); i++) {
                for (int j = 0; j
                    < mapL.getRow(); j++) {
                    for (int k = 0; k
                        < mapL.getCol(); k++) {
                        mapL.setMap(i, j, k, dis.readInt());
                    }
                }
            }
            // 读取地图通行度数据
            for (int j = 0; j
                < mapL.getRow(); j++) {
                for (int k = 0; k
                    < mapL.getCol(); k++) {
                    mapL.setWay(j, k, dis.readByte());
                }
            }
            for (int j = 0; j
                < mapL.getRow(); j++) {
                for (int k = 0; k
                    < mapL.getCol(); k++) {
                    byte t = dis.readByte();
                    mapL.getScriptType()[j][k] = t;
                }
            }
            int sum = dis.readInt();
            for (int j = 0; j
                < mapL.getRow(); j++) {
                for (int k = 0; k
                    < mapL.getCol(); k++) {
//                    byte t = dis.readByte();
//                    mapL.getScriptType()[j][k] = t;
                    if (mapL.getScriptType()[j][k] != 0) {

                        dis.readInt();
                        dis.readInt();
                        int n = dis.readInt();
                        Script s = new Script(k, j);
                        for (int i = 0; i
                            < n; i++) {
                            s.addString(dis.readUTF());
                        }
                        mapL.getScriptList()[j][k] = s;
                    }
                }
            }
            fis.close();
            dis.close();
            return mapL;
        } catch (Exception e) {
        }
        return null;
    }

    private void loadMapVector(File file) {
        mapVector.addMap(loadMap(file));
    }
//    private MapList tempMapV;

    private boolean loadProject(File f) {
        boolean judge = false;
        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(f);
            dis = new DataInputStream(fis);
            Project.setProjectName(dis.readUTF());
            penCheck.setSelected(true);
            isHasProject = true;
            isChoose = false;
            isFill = false;
            isCollide = false;
            isEvent = false;
            fis.close();
            dis.close();
            judge = true;
        } catch (Exception e) {
            judge = false;
        }
        return judge;

    }
    // 用于打开文件时的过滤器，文件格式为.map

    class MEFileFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            // TODO 自动生成方法存根
            return f.getName().toLowerCase().endsWith(".project") || f.isDirectory();
        }

        @Override
        public String getDescription() {
            // TODO 自动生成方法存根
            return "MobileGameMaker工程 (*.project)";
        }
    }
//    private JMenuItem CloseProjectItem;
//
//    private JMenuItem getClosePro() {
//        if (CloseProjectItem == null) {
//            CloseProjectItem = new JMenuItem();
//            CloseProjectItem.setText("关闭工程");
////            CloseProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
////                    InputEvent.CTRL_MASK));
////            jMenuItem.addActionListener(this);
//            CloseProjectItem.addActionListener(new java.awt.event.ActionListener() {
//
//                @Override
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    System.out.println("close");
//                    if (!isHasProject) {
//                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
//                        return;
//                    }
//
//                    Project.setProjectPath(".");
//                    Project.setProjectName("");
//                    initTitle();
//                    initMapVector();
//                    jTable.updateTable();
//                    isHasProject = false;
////                    mapCanvas.set
//                    currentLayer = 0;
//                    layerNum = 1;
//                    penCheck.setSelected(true);
//                    slButton.setText("状态：就绪");
//                    scButton.setText("当前图层：未开始");
//                    isChoose = false;
//                    isFill = false;
//                    isCollide = false;
//                    isEvent = false;
//                    getJMenu1().repaint();
//
//
////                    map = new engine.Map("", "", 1, 1, 1, 1, 1);
////                    map.setIsVisible(false);
////                    getJPanel();
////                    getJPanel1();
//                    mapCanvas.updateCanvas();
//////                    tileCanvas.TileImg = null;
////                    tileCanvas.TileImg = null;
//                    tileCanvas.updateCanvas();
////                    tileCanvas.TileImg = null;
////                    tileCanvas = null;
////                    mapCanvas.bfg = null;
////                    mapCanvas.mapImg = null;
////                    mapCanvas = null;
//
////                    mapCanvas = null;
////                    tileCanvas  = null;
////                    mapCanvas = new MapCanvas();
////                    tileCanvas = new TileCanvas();
////                    initMapCanvas();
//                    System.gc();
//                }
//            });
//        }
//        return CloseProjectItem;
//    }
    private JMenuItem mapItem;

    private JMenuItem getMapItem() {
        if (mapItem == null) {
            mapItem = new JMenuItem();
            mapItem.setText("地图设置");
//            jMenuItem.addActionListener(this);
            mapItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    setMapNews();


                }
            });

        }
        return mapItem;
    }

    private void setMapNews() {
        if (map != null) {

            getJDialog1();
            initJComboBox2();
            initJComboBox4();
            initTextFiled();
            jDialog1.setVisible(true);
            jTable.updateTable();
        } else {
            JOptionPane.showMessageDialog(getJContentPane(), "地图不存在，请新建或导入地图");
        }
    }

    private JMenuItem getSetItem() {
        if (jItemSet == null) {
            jItemSet = new JMenuItem();
            jItemSet.setText("选项");
//            jItemSet.addActionListener(this);
            jItemSet.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialogSet();
                    jdSet.setVisible(true);
                }
            });
        }
        return jItemSet;
    }
//
//    private JMenuItem getPicSaveItem() {
//        if (ImgItem == null) {
//            ImgItem = new JMenuItem();
//            ImgItem.setText("预览生成");
////            ImgItem.addActionListener(this);
//            ImgItem.addActionListener(new java.awt.event.ActionListener() {
//
//                @Override
//                public void actionPerformed(java.awt.event.ActionEvent e) {
////                    try {
//                    if (map != null) {
//                        try {
//                            File file = new File(".\\preview\\" + map.getName() + ".png");
//                            ImageUtil.writeImage(bf, file, "png");//因为bf为透明背景，故将生成具有透明背景色的地图，可当作tile的图片用
//                            JOptionPane.showMessageDialog(getJContentPane(), "生成预览图成功");
//                        } catch (Exception ex) {
//                            JOptionPane.showMessageDialog(getJContentPane(), "生成预览图失败");
//                        }
//                    }
//                }
//            });
//        }
//        return ImgItem;
//    }
    private JMenuItem dataItem;

    private JMenuItem getdataItem() {
        if (dataItem == null) {
            dataItem = new JMenuItem();
            dataItem.setText("数据管理");
//            dataItem.addActionListener(this);
            dataItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!isHasProject) {
                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
                        return;
                    }
                    creatDM();
                    dm.setVisible(true);
                }
            });
        }
        return dataItem;
    }
    private AnimationMaker am = null;

    private void creatAM() {
        if (am == null) {
            am = new AnimationMaker();
        }
    }
    private JMenuItem animationItem;

    private JMenuItem getAnimationItem() {
        if (animationItem == null) {
            animationItem = new JMenuItem();
            animationItem.setText("动画编辑");
//            dataItem.addActionListener(this);
            animationItem.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (!isHasProject) {
                        JOptionPane.showMessageDialog(getJContentPane(), "请先新建项目");
                        return;
                    }
                    creatAM();
                    am.setVisible(true);
                }
            });
        }
        return animationItem;
    }
//    private JMenuItem getRepaintItem() {
//        if (jRepaintItem == null) {
//            jRepaintItem = new JMenuItem();
//            jRepaintItem.setText("刷新");
////            jRepaintItem.addActionListener(this);
//            jRepaintItem.addActionListener(new java.awt.event.ActionListener() {
//
//                @Override
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    if (map != null) {
//                        initMapCanvas();
//                        getPreviewDialog().repaint();
//                        getJMenu1().repaint();
//                        getPicJMenu().repaint();
//
//
//                    }
//                }
//            });
//
//
//        }
//        return jRepaintItem;
//
//
//    }

    /**
     * This method initializes jMenuItem3
     *
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getJMenuItem3() {
        if (jMenuItem3 == null) {
            jMenuItem3 = new JMenuItem();
            jMenuItem3.setText("保存地图");
//            jMenuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
//                InputEvent.CTRL_MASK));
//            jMenuItem3.addActionListener(this);
            jMenuItem3.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (map != null) {
                        saveMap();
                    } else {
                        JOptionPane.showMessageDialog(getJSplitPane(),
                            "地图不存在，请新建或导入地图");
                    }
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
//            tileCanvas.setText("目前没有可加载的地图，请创建新地图");
            jPanel = new JPanel();
            jPanel.setLayout(new BorderLayout());
//        }
            tileCanvas = new TileCanvas();
            jPanel.setBackground(Color.gray);
            jPanel.add(tileCanvas, BorderLayout.NORTH);
        }
//        }
        return jPanel;
    }

    /**
     * This method initializes jPanel1
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel1() {
        if (jPanel1 == null) {

            jPanel1 = new JPanel();
            jPanel1.setLayout(new BorderLayout());
//        }
            mapCanvas = new MapCanvas();
            jPanel1.setBackground(Color.GRAY);
            jPanel1.add(mapCanvas, BorderLayout.NORTH);
        }
        return jPanel1;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setPreferredSize(new Dimension(3, 350));
            jScrollPane.setViewportView(getJPanel());
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
            jScrollPane1.setViewportView(getJPanel1());
        }
        return jScrollPane1;
    }

    /**
     * This method initializes jSplitPane
     *
     * @return javax.swing.JSplitPane
     */
    private JSplitPane getJSplitPane() {
        if (jSplitPane == null) {
            jSplitPane = new JSplitPane();
            jSplitPane.setDividerSize(8);
//            jSplitPane.setUI((SplitPaneUI)(SplitPaneUI.createUI(uidefs)));
            jSplitPane.setOneTouchExpandable(true);
//            System.out.println(SplitPane.);


            jSplitPane.setLeftComponent(getJSplitPane1());
            jSplitPane.setRightComponent(getJScrollPane1());
        }
        return jSplitPane;

    }
    /**
     * This method initializes this
     *
     * @return void
     */
    public String version = "MobileGameMaker v1.0beta";

    private void initialize() {
        this.setSize(900, 650);
        this.setJMenuBar(getJJMenuBar());
        this.setContentPane(getJContentPane());
        this.add(getSbToolBar(), BorderLayout.SOUTH);
        this.add(getToolBar2(), BorderLayout.NORTH);
//        this.add(new BasicArrowButton(BasicArrowButton.NORTH), BorderLayout.NORTH);
        initTitle();
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = this.getSize();
        this.setLocation(screenSize.width / 2 - (labelSize.width / 2),
            screenSize.height / 2 - (labelSize.height / 2));//设置位置屏幕中部
        Image image = this.getToolkit().getImage("icon/icon.png");
        this.setIconImage(image);
        data = new Data();
        mData = new Data();
        mapVector = new MapList();

    }

    private void initTitle() {
        this.setTitle(version);
    }

    public void changeTitle() {
        this.setTitle(Project.getProjectName() + " - " + version);
    }
    public MapList mapVector;

    private void initJComboBox4() {
        jComboBox4.removeAllItems();
        File[] f = new File(Project.getProjectPath() + "\\audio\\music").listFiles();
        int ii = f.length;
        jComboBox4.addItem(map.getMusicName());
        for (int i = 0; i
            < ii; i++) {
//            if (f[i].getName().endsWith(".png")) {
            //不限制文件格式
            jComboBox4.addItem(f[i].getName());
//            }
        }
    }

    private void initJComboBox2() {
        jComboBox2.removeAllItems();
        File[] f = new File(Project.getProjectPath() + "\\image\\tileset").listFiles();
        int ii = f.length;
        jComboBox2.addItem(map.getImageName());
        for (int i = 0; i
            < ii; i++) {
//            if (f[i].getName().endsWith(".png")) {
            //不限制文件格式
            jComboBox2.addItem(f[i].getName());
//            }
        }
    }
    private JComboBox jComboBox3, jComboBox4;

    private void initJComboBox() {
        jComboBox.removeAllItems();
        File[] f = new File(Project.getProjectPath() + "\\image\\tileset").listFiles();
        int ii = f.length;
        for (int i = 0; i
            < ii; i++) {
//            if (f[i].getName().endsWith(".png")) {
            //不限制文件格式
            jComboBox.addItem(f[i].getName());
//            }
        }
    }

    private void initJComboBox3() {
        jComboBox3.removeAllItems();
        File[] f = new File(Project.getProjectPath() + "\\audio\\music").listFiles();
        int ii = f.length;
        for (int i = 0; i
            < ii; i++) {
//            if (f[i].getName().endsWith(".png")) {
            //不限制文件格式
            jComboBox3.addItem(f[i].getName());
//            }
        }
    }

    public void initMapCanvas() {
        // TODO 自动生成方法存根
        tileCanvas.updateCanvas();
        mapCanvas.updateCanvas();
    }

    private void saveMap() {
        JFileChooser jf = new JFileChooser();
        jf.setCurrentDirectory(new File(Project.getProjectPath()));
        jf.setAcceptAllFileFilterUsed(false);
        MyFileFilter txtFilter = new MyFileFilter(".txt", "txt 文件 (*.txt)");
        jf.addChoosableFileFilter(txtFilter);
        int fresult;
        fresult = jf.showSaveDialog(this);
        if (fresult == JFileChooser.APPROVE_OPTION) { // 用户点击了“确定”按钮
            File file = jf.getSelectedFile(); //获得文件名
            // 获得被选中的过滤器
            MyFileFilter filter = (MyFileFilter) jf.getFileFilter();
            // 获得过滤器的扩展名
            String ends = filter.getEnds();
            File newFile = null;
            if (file.getAbsolutePath().toUpperCase().endsWith(ends.toUpperCase())) {
                // 如果文件是以选定扩展名结束的，则使用原名
                newFile = file;
            } else {
                // 否则加上选定的扩展名
                newFile = new File(file.getAbsolutePath() + ends);
            }
            try {
                FileWriter fw = new FileWriter(newFile);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("编辑器版本：" + version);
                pw.println("地图编号：" + currentMapIndex);
                pw.println("地图名称：" + map.getName());
                pw.println("图块名称：" + map.getImageName());
                pw.println("背景音乐：" + map.getMusicName());
                pw.println("图层数：" + map.getLayerNum());
                pw.println("地图单元格宽度：" + map.getCellWidth() + " 地图单元格高度："
                    + map.getCellHeight());
                pw.println("地图宽度(单元格数)：" + map.getCol() + " 地图高度(单元格数)："
                    + map.getRow());
                for (int i = 0; i
                    < map.getLayerNum(); i++) {
                    pw.println("图层" + (i + 1) + "：");
                    pw.print("{");
                    for (int j = 0; j
                        < map.getRow(); j++) {
                        pw.print("{");
                        for (int k = 0; k
                            < map.getCol(); k++) {
                            pw.print(map.getMap()[i][j][k]);
                            if (k != map.getCol() - 1) {
                                pw.print(",");
                            }
                        }
                        pw.print("}");

                        if (j != map.getRow() - 1) {
                            pw.println(",");
                        }
                    }
                    pw.println("};");
                }
                pw.println("通行度：");
                pw.print("{");
                for (int j = 0; j
                    < map.getRow(); j++) {
                    pw.print("{");
                    for (int k = 0; k
                        < map.getCol(); k++) {
                        pw.print(map.getWay()[j][k]);
                        if (k != map.getCol() - 1) {
                            pw.print(",");

                        }
                    }
                    pw.print("}");
                    if (j != map.getRow() - 1) {
                        pw.println(",");
                    }
                }
                pw.println("};");

//                int si = map.getScriptList().getScriptList().size();
//                pw.println("脚本总数：" + si);
//                for (int i = 0; i
//                    < si; i++) {
//                    Script temp = (Script) map.getScriptList().getScriptList().elementAt(i);
//                    pw.println("第" + temp.getRow() + "行第" + temp.getCol()
//                        + "列");
//                    for (int j = 0; j
//                        < temp.size(); j++) {
//                        pw.println("脚本内容：" + temp.elementAt(j));
//                    }
//                }
                System.gc();
                pw.flush();
                pw.close();
                fw.close();
            } catch (Exception e) {
                // TODO 自动生成 catch 块
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;

    }
} // @jve:decl-index=0:visual-constraint="27,-30"

