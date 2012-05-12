/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyomaker.widget;

import com.soyomaker.model.animation.Animation;
import com.soyomaker.model.animation.Frame;
import com.soyomaker.model.animation.Clip;
import com.soyomaker.AppData;
import com.soyomaker.listener.FrameListener;
import com.soyomaker.util.ClipTransferable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 *
 * @author Administrator
 */
public class HandleFramePane extends JPanel implements DropTargetListener, Runnable, Scrollable, MouseListener, MouseMotionListener, KeyListener {

    private boolean isLoop = true;//循环播放标志
    private AppData data = AppData.getInstance();
    private Frame curFrame;//当前正在处理的Frame
    private int selectedTileIndex = -1;
    private boolean isPlay = false;//播放标志
    private Animation ani;
    private ArrayList<FrameListener> listeners = new ArrayList<FrameListener>();

    /**
     *
     */
    public HandleFramePane() {
        new Thread(this).start();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }

    /**
     *
     * @param o
     * @return
     */
    public boolean removeFrameListener(FrameListener o) {
        return listeners.remove(o);
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean addFrameListener(FrameListener e) {
        return listeners.add(e);
    }

    /**
     *
     * @return
     */
    public Frame getCurFrame() {
        return curFrame;
    }

    /**
     *
     * @param curFrame
     */
    public void setCurFrame(Frame curFrame) {
        this.curFrame = curFrame;
        repaint();
    }

    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 16;
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return (visibleRect.height / 16) * 16;
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    }

    public void dragOver(DropTargetDragEvent dtde) {
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    public void dragExit(DropTargetEvent dte) {
    }

    public void drop(DropTargetDropEvent event) {
        if (curFrame != null) {
            try {
                Transferable transferable = event.getTransferable();
                Object o = transferable.getTransferData(
                        ClipTransferable.TILE_FLAVOR);
                if (o != null) {
                    if (o instanceof Clip) {
                        Clip t = (Clip) o;
                        Point p = event.getLocation();
                        t.setFramePoint(new Point(p.x - this.getWidth() / 2, p.y - this.getHeight() / 2));
                        t.setMirror(false);
                        t.setRotation(0);
                        t.setTransparency(0);
//                        t.setRenderType(0);
                        t.setZoom(1.0f);
                        curFrame.addTile(t);
                        setSelectedTileIndex(curFrame.getTiles().size() - 1);
                    }
                }
                event.dropComplete(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @return
     */
    public Clip getSelectedTile() {
        if (curFrame == null) {
            return null;
        }
        return curFrame.getTile(selectedTileIndex);
    }

    /**
     *
     * @return
     */
    public int getSelectedTileIndex() {
        return selectedTileIndex;
    }

    /**
     *
     * @param selectedTileIndex
     */
    public void setSelectedTileIndex(int selectedTileIndex) {
        this.selectedTileIndex = selectedTileIndex;
        for (int nn = 0; nn < listeners.size(); nn++) {
            listeners.get(nn).clipSelected(curFrame.getTile(selectedTileIndex));
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = this.getParent().getSize();
        return size;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int tw = this.getWidth();
        int th = this.getHeight();

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, tw, th);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, th >> 1, tw, th >> 1);
        g2d.drawLine(tw >> 1, 0, tw >> 1, th);
        if (curFrame != null) {
            curFrame.paint(g, tw / 2, th / 2);
            if (selectedTileIndex >= 0 && selectedTileIndex <= curFrame.getTiles().size() - 1) {
                g.setColor(Color.RED);
                Clip clip = curFrame.getTile(selectedTileIndex);
                int x = 0, y = 0, w = 0, h = 0;
                switch (clip.getRotation()) {
                    case 0:
                    case 180:
                        x = (int) (clip.getFramePoint().x - clip.getW() * clip.getZoom() / 2 + tw / 2);
                        y = (int) (clip.getFramePoint().y - clip.getH() * clip.getZoom() / 2 + th / 2);
                        w = (int) (clip.getW() * clip.getZoom());
                        h = (int) (clip.getH() * clip.getZoom());
                        break;
                    case 90:
                    case 270:
                        x = (int) (clip.getFramePoint().x - clip.getH() * clip.getZoom() / 2 + tw / 2);
                        y = (int) (clip.getFramePoint().y - clip.getW() * clip.getZoom() / 2 + th / 2);
                        w = (int) (clip.getH() * clip.getZoom());
                        h = (int) (clip.getW() * clip.getZoom());
                        break;
                }
                g.drawRect(x, y, w, h);
            }
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER));
            g2d.drawOval(16, 16, 32, 32);
            g2d.setFont(new Font("Serif", Font.BOLD, 16));
            g2d.drawString("" + curFrame.getID(), 29 - curFrame.getID() / 10 * 4, 37);
        }
    }

    /**
     *
     * @return
     */
    public boolean isIsLoop() {
        return isLoop;
    }

    /**
     *
     * @param isLoop
     */
    public void setIsLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    /**
     *
     * @return
     */
    public boolean isIsPlay() {
        return isPlay;
    }

    /**
     *
     * @param isPlay
     */
    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }

    /**
     * 播放动画
     * @param loop 是否循环
     */
    public void playOrPause(boolean loop) {
        isLoop = loop;
        if (isPlay) {
            isPlay = false;
        } else {
            isPlay = true;
            ani = data.getCurProject().getAnimation(data.getCurrentAnimationIndex());
        }
        selectedTileIndex = -1;
    }

    public void run() {
        int index = 0;
        while (true) {
            if (isPlay && ani != null) {
                Frame f = ani.getFrame(index);
                setCurFrame(f);
                index++;
                if (index >= ani.getFrames().size()) {
                    index = 0;
                    if (!isLoop) {
                        isPlay = false;
                    }
                }
                try {
                    Thread.sleep(f.getDelay());
                } catch (InterruptedException ex) {
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
    private Cursor moveCursor = new Cursor(Cursor.MOVE_CURSOR);
    private Point moveOrgine = new Point();
    private Point moveTraget = new Point();

//    /**
//     *
//     */
//    public void removeSelectedTile() {
//        if (curFrame.getTile(selectedTileIndex) != null) {
//            curFrame.removeTile(selectedTileIndex);
//            selectedTileIndex = -1;
//            repaint();
//        }
//    }
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (curFrame != null) {
            for (int i = curFrame.getTiles().size() - 1; i >= 0; i--) {
                Clip t = curFrame.getTiles().get(i);
                int cx = (int) (e.getX() + t.getW() * t.getZoom() / 2 - this.getWidth() / 2);
                int cy = (int) (e.getY() + t.getH() * t.getZoom() / 2 - this.getHeight() / 2);
                if (t.frameContains(cx, cy)) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        setSelectedTileIndex(i);
                        this.moveOrgine.setLocation(e.getX(), e.getY());
                        this.moveTraget.setLocation(e.getX(), e.getY());
                        setCursor(this.moveCursor);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        curFrame.removeTile(i);
                    }
                    repaint();
                    return;
                }
            }
        }
        setSelectedTileIndex(-1);
    }

    public void mouseReleased(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());
    }

    public void mouseEntered(MouseEvent e) {
        this.requestFocus();
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        if (selectedTileIndex < 0 || selectedTileIndex > curFrame.getTiles().size() - 1) {
            return;
        }
        Clip tile = curFrame.getTiles().get(selectedTileIndex);
        this.moveTraget.setLocation(e.getX(), e.getY());
        int mx = this.moveTraget.x - this.moveOrgine.x;
        int my = this.moveTraget.y - this.moveOrgine.y;
        if (Math.abs(mx) >= 1) {
            int xx = tile.getFramePoint().x + (int) (mx);
            tile.getFramePoint().x = xx;
            this.moveOrgine.x = this.moveTraget.x;
        }
        if (Math.abs(my) >= 1) {
            int yy = tile.getFramePoint().y + (int) (my);
            tile.getFramePoint().y = yy;
            this.moveOrgine.y = this.moveTraget.y;
        }
        repaint();
    }

    public void mouseMoved(MouseEvent e) {
        if (curFrame != null) {
            for (int i = curFrame.getTiles().size() - 1; i >= 0; i--) {
                Clip clip = curFrame.getTiles().get(i);
                int cx = (int) (e.getX() + clip.getW() / 2 - this.getWidth() / 2);
                int cy = (int) (e.getY() + clip.getH() / 2 - this.getHeight() / 2);
                if (clip.frameContains(cx, cy)) {
                    this.setToolTipText("<html>切块ID:" + data.getCurProject().getClips().indexOf(clip.getOriginal())
                            + "<br>透明度:" + clip.getTransparency()
                            + "<br>镜像:" + clip.isMirror()
                            + "<br>旋转角:" + clip.getRotation()
                            + "<br>x:" + (clip.getFramePoint().x)
                            + "<br>y:" + (clip.getFramePoint().y)
                            + "<br>宽度:" + clip.getW()
                            + "<br>高度:" + clip.getH()
                            + "</html>");
                    return;
                }
            }
            this.setToolTipText(null);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (selectedTileIndex < 0 || selectedTileIndex > curFrame.getTiles().size() - 1) {
            return;
        }
        Clip tile = curFrame.getTiles().get(selectedTileIndex);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                tile.getFramePoint().y--;
                break;
            case KeyEvent.VK_DOWN:
                tile.getFramePoint().y++;
                break;
            case KeyEvent.VK_LEFT:
                tile.getFramePoint().x--;
                break;
            case KeyEvent.VK_RIGHT:
                tile.getFramePoint().x++;
                break;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }
}
