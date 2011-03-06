package emulator;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class KeyButton extends JButton implements MouseListener {

	private Emulator emulator = null;

	private int key = 0;

	public KeyButton(Emulator emulator, int key) {
		this.emulator = emulator;
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
		emulator.getCanvas().keyPressed(key);
		System.out.println(key);
	}

	public void mouseReleased(MouseEvent e) {
		emulator.getCanvas().keyReleased(key);
		System.out.println(key);
	}

}
