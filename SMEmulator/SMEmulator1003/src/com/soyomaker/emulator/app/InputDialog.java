package com.soyomaker.emulator.app;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InputDialog extends JPanel {

	public interface InputListener {
		public void onInput(String value);
	}

	private static final long serialVersionUID = -4866903349128579049L;
	private JTextField textField;
	private InputListener inputListener = null;

	/**
	 * Create the panel.
	 */
	public InputDialog(int width, int height) {
//		width = 640;
//		height = 100;
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setSize(new Dimension(width, height));
		setLayout(null);

		JButton confirmButton = new JButton("确定");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputListener != null) {
					inputListener.onInput(textField.getText());
				}
			}
		});
		confirmButton.setSize(new Dimension(80, 80));
		confirmButton.setBounds(550 + width - 640, 10, 80, 80);
		add(confirmButton);

		JLabel lblNewLabel = new JLabel("请输入内容：");
		lblNewLabel.setBounds(10, 10, 530 + width - 640,
				32 + (height - 100) / 2);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(10, 50 + (height - 100) / 2, 530 + width - 640,
				40 + (height - 100) / 2);
		add(textField);
		textField.setColumns(10);

		MouseAdapter mouseAdapter=new MouseAdapter() {
		};
		addMouseMotionListener(mouseAdapter);
		addMouseListener(mouseAdapter);
	}

	public void setInputListener(InputListener inputListener) {
		this.inputListener = inputListener;
	}
}
