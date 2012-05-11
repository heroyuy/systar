package com.soyomaker.emulator.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.soyomaker.emulator.ui.Image;
import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.utils.ImageFactory;

public class InputDialog extends JPanel {

	public interface InputListener {
		public void onInput(String value);
	}

	private static final long serialVersionUID = -4866903349128579049L;
	private JTextField contentField;
	private InputListener inputListener = null;
	private JLabel tipLabel;

	/**
	 * Create the panel.
	 */
	public InputDialog(int width, int height) {
		// width = 640;
		// height = 100;
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setSize(new Dimension(width, height));
		setLayout(null);

		JButton confirmButton = new JButton("确定");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputListener != null) {
					inputListener.onInput(contentField.getText());
				}
			}
		});
		confirmButton.setSize(new Dimension(80, 80));
		confirmButton.setBounds(550 + width - 640, 10, 80, 80);
		add(confirmButton);

		tipLabel = new JLabel("请输入内容：");
		tipLabel.setBounds(10, 10, 530 + width - 640, 32 + (height - 100) / 2);
		add(tipLabel);

		contentField = new JTextField();
		contentField.setBounds(10, 50 + (height - 100) / 2, 530 + width - 640, 40 + (height - 100) / 2);
		add(contentField);
		contentField.setColumns(10);

		MouseAdapter mouseAdapter = new MouseAdapter() {
		};
		addMouseMotionListener(mouseAdapter);
		addMouseListener(mouseAdapter);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Painter p = new Painter((Graphics2D) g);
		String inputImage = GameInfo.getInstance().getGamePath() + "/image/input/input.png";
		Image img = ImageFactory.getInstance().createImage(inputImage);
		p.drawImage(img, 0, 0, Painter.LT);
	}

	public void setContent(String content) {
		contentField.setText(content);
	}

	public void setInputListener(InputListener inputListener) {
		this.inputListener = inputListener;
	}

	public void setTip(String tip) {
		tipLabel.setText(tip);
	}
}
