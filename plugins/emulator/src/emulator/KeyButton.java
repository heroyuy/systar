package emulator;

import com.soyostar.app.Component;
import com.soyostar.app.KeyEvent;
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

        Component component = Emulator.getDefaultEmulator().getCurApp().getContentPanel();
        if (component != null) {
            component.onKeyEvent(new KeyEvent(key, KeyEvent.KEY_DOWN));
        }
    }

    public void mouseReleased(MouseEvent e) {

        Component component = Emulator.getDefaultEmulator().getCurApp().getContentPanel();
        if (component != null) {
            component.onKeyEvent(new KeyEvent(key, KeyEvent.KEY_UP));
        }
    }
}
