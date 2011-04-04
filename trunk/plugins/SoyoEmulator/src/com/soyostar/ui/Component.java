package com.soyostar.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * 
 * @author Administrator
 */
public class Component {

    private Painter painter = null;
    private Motion motion = null;
    private MotionListener motionListener = null;
    private JPanel view = null;

    public JComponent getView() {
        return view;
    }

    private class ComponentView extends JPanel implements MouseListener,
            MouseMotionListener {

        private ComponentView() {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            setLayout(null);
        }

        private ComponentView(int x, int y) {
            setLocation(x, y);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            setLayout(null);
        }

        private ComponentView(int x, int y, int width, int height) {
            setLocation(x, y);
            setSize(width, height);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            setLayout(null);
        }

        public void mouseClicked(MouseEvent e) {
            // 暂不实现
        }

        public void mousePressed(MouseEvent e) {
            if (motionListener != null) {
                motion.setX(e.getX());
                motion.setY(e.getY());
                motionListener.onMotionDown(motion);
            }
        }

        public void mouseReleased(MouseEvent e) {

            if (motionListener != null) {
                motion.setX(e.getX());
                motion.setY(e.getY());
                motionListener.onMotionUp(motion);
            }
        }

        public void mouseEntered(MouseEvent e) {
            // 暂不实现
        }

        public void mouseExited(MouseEvent e) {
            // 暂不实现
        }

        public void mouseDragged(MouseEvent e) {

            if (motionListener != null) {
                motion.setX(e.getX());
                motion.setY(e.getY());
                motionListener.onMotionMove(motion);
            }
        }

        public void mouseMoved(MouseEvent e) {
            // 暂不实现
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            painter.setGraphics(g);
            Component.this.paint(painter);
            super.paintChildren(g);
        }
    }

    public void setMotionListener(MotionListener motionListener) {
        this.motionListener = motionListener;
    }

    public Component() {
        motion = new Motion();
        view = new ComponentView();
        painter = new Painter();
    }

    public Component(int x, int y) {
        motion = new Motion();
        view = new ComponentView(x, y);
        painter = new Painter();
    }

    public Component(int x, int y, int width, int height) {
        motion = new Motion();
        view = new ComponentView(x, y, width, height);
        painter = new Painter();
    }

    public void setX(int x) {
        view.setLocation(x, view.getY());
    }

    public void setY(int y) {
        view.setLocation(view.getX(), y);
    }

    public void setWidth(int width) {
        view.setSize(width, view.getHeight());
    }

    public void setHeight(int height) {
        view.setSize(view.getWidth(), height);
    }

    public int getX() {
        return view.getX();
    }

    public int getY() {
        return view.getY();
    }

    public int getWidth() {
        return view.getWidth();
    }

    public int getHeight() {
        return view.getHeight();
    }

    public final void repaint() {
        view.repaint();
    }

    public void paint(Painter painter) {
    }

    public void keyPressed(int key) {
    }

    public void keyReleased(int key) {
    }
}
