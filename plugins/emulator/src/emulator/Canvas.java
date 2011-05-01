package emulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public abstract class Canvas extends JPanel implements MouseMotionListener, MouseListener {

    private Graphics g = null;
    private EmulatorGraphics eg = null;

    public Canvas() {
        super.setSize(1, 1);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

//    @Override
//    public final void setSize(int width, int height) {
//        try {
//            throw new IllegalAccessException("非法修改Canvas大小");
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    @Override
//    public final void setLocation(int x, int y) {
//        try {
//            throw new IllegalAccessException("非法修改Canvas坐标");
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.g != g) {
            this.g = g;
            eg = new EmulatorGraphics(this.g);
        }
        paint(eg);
    }

    public abstract void paint(EmulatorGraphics g);

    public abstract void keyPressed(int key);

    public abstract void keyReleased(int key);

    public abstract void onTouchEvent(MotionEvent me);

    public void mouseDragged(MouseEvent e) {
        onTouchEvent(new MotionEvent(e.getX(), e.getY(), MotionEvent.MOTION_MOVE));
    }

    public void mousePressed(MouseEvent e) {
        onTouchEvent(new MotionEvent(e.getX(), e.getY(), MotionEvent.MOTION_DOWN));
    }

    public void mouseReleased(MouseEvent e) {
        onTouchEvent(new MotionEvent(e.getX(), e.getY(), MotionEvent.MOTION_UP));
    }

    public abstract void sizeChanged(int width, int height);

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
