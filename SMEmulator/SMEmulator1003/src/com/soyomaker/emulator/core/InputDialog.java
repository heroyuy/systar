package com.soyomaker.emulator.core;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class InputDialog extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4170460031805333018L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public InputDialog() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 100));
		panel.setSize(new Dimension(0, 100));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 50, 549, 40);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(569, 17, 93, 73);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("请输入：");
		lblNewLabel.setBounds(10, 17, 549, 23);
		panel.add(lblNewLabel);

	}
}
