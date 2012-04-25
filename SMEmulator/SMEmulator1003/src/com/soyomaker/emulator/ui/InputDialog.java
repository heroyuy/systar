package com.soyomaker.emulator.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class InputDialog extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public InputDialog(int width, int height) {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(new Color(211, 211, 211));
		width = 960;
		height = 100;
		setSize(new Dimension(width, height));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("请输入内容:");
		lblNewLabel.setBounds(10, 10, 524 + width - 640, 28);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(10, 48, 524 + width - 640, 34);
		add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setSize(new Dimension(80, 80));
		btnNewButton.setBounds(544 + width - 640, 10, 86, 73);
		add(btnNewButton);

	}
}
