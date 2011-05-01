package emulator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class KeyButton extends JButton implements MouseListener {

    private int key = 0;

    public KeyButton(int key) {
        this.key = key;
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

        Canvas canvas = Emulator.getDefaultEmulator().getCurApp().getCanvas();
        if (canvas != null) {
            canvas.keyPressed(key);
        }
    }

    public void mouseReleased(MouseEvent e) {
        Canvas canvas = Emulator.getDefaultEmulator().getCurApp().getCanvas();
        if (canvas != null) {
            canvas.keyReleased(key);
        }
    }
}
