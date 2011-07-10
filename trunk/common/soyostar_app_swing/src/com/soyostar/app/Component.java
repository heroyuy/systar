package com.soyostar.app;

import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.KeyEvent;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * 组件。所有组件的父类。
 * @author wp_g4
 */
public class Component {

    JComponent content = null;
    Painter painter = null;

    /**
     * 默认的 Component 构造方法。
     */
    public Component() {
        content = new JPanel() {

            @Override
            public void paint(Graphics g) {
                if (painter == null || ((GraphicsPainter) painter).getGraphics() != g) {
                    painter = new GraphicsPainter(g);
                }
                Component.this.paint(painter);
                this.paintChildren(g);
            }
        };
        content.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                Component.this.onTouchEvent(new TouchEvent(e.getX(), e.getY(), TouchEvent.TOUCH_DOWN));

            }

            public void mouseReleased(MouseEvent e) {

                Component.this.onTouchEvent(new TouchEvent(e.getX(), e.getY(), TouchEvent.TOUCH_UP));

            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        content.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {

                Component.this.onTouchEvent(new TouchEvent(e.getX(), e.getY(), TouchEvent.TOUCH_MOVE));

            }

            public void mouseMoved(MouseEvent e) {
            }
        });
        content.setLayout(null);
    }

    /**
     * 将组件移到新位置。
     * @param x 父级坐标空间中新位置左上角的 x 坐标
     */
    public void setX(int x) {
        content.setLocation(x, content.getY());
    }

    /**
     * 返回组件原点的当前 x 坐标。
     * @return 组件原点的当前 x 坐标。
     */
    public int getX() {
        return content.getX();
    }

    /**
     * 将组件移到新位置。
     * @param y 父级坐标空间中新位置左上角的 y 坐标
     */
    public void setY(int y) {
        content.setLocation(content.getX(), y);
    }

    /**
     * 返回组件原点的当前 y 坐标。
     * @return 组件原点的当前 y 坐标。
     */
    public int getY() {
        return content.getY();
    }

    /**
     * 调整组件的大小，使其宽度为 width。
     * @param width 组件的新宽度，单位是像素
     */
    public void setWidth(int width) {
        content.setSize(width, content.getHeight());
        sizeChanged(width, content.getHeight());
    }

    /**
     * 返回此组件的当前宽度。
     * @return 此组件的当前宽度。
     */
    public int getWidth() {
        return content.getWidth();
    }

    /**
     *  调整组件的大小，使其高度为 height。
     * @param height 组件的新高度，单位是像素
     */
    public void setHeight(int height) {
        content.setSize(content.getWidth(), height);
        sizeChanged(content.getWidth(), height);
    }

    /**
     * 返回此组件的当前高度。
     * @return 此组件的当前高度。
     */
    public int getHeight() {
        return content.getHeight();
    }

    /**
     * 调整组件的大小，使其宽度为 width，高度为 height。
     * @param width 组件的新宽度，单位是像素
     * @param height 组件的新高度，单位是像素
     */
    public void setSize(int width, int height) {
        content.setSize(width, height);
        sizeChanged(width, height);
    }

    /**
     * 将组件移到新位置。通过此组件父级坐标空间中的 x 和 y 参数来指定新位置的左上角。
     * @param x 父级坐标空间中新位置左上角的 x 坐标
     * @param y 父级坐标空间中新位置左上角的 y 坐标
     */
    public void setLocation(int x, int y) {
        content.setLocation(x, y);
    }

    /**
     * 重绘此组件。此方法会尽快调用此组件的 paint 方法。
     */
    public final void repaint() {
        if (content != null) {
            content.repaint();
        }
    }

    /**
     * 绘制组件。应用程序不应直接调用 paint，而是应该使用 repaint 方法来安排重绘组件。
     * @param painter 在其中进行绘制的 Painter 上下文
     */
    public void paint(Painter painter) {
    }

    /**
     * 组件尺寸改变
     * @param width
     * @param height
     */
    public void sizeChanged(int width, int height) {
    }

    /**
     * 触屏事件发生时调用
     * @param touchEvent 触屏事件
     */
    public void onTouchEvent(TouchEvent touchEvent) {
    }

    /**
     * 按键事件发生时调用
     * @param keyEvent 按键事件
     */
    public void onKeyEvent(KeyEvent keyEvent) {
    }
}
