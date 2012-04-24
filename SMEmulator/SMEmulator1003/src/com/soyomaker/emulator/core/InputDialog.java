package com.soyomaker.emulator.core;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5902842610051216990L;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputDialog dialog = new InputDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public InputDialog() {
		setTitle("输入框");
		setResizable(false);
		setBounds(100, 100, 640, 160);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(514, 10, 100, 100);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("请输入内容：");
		lblNewLabel.setBounds(10, 10, 494, 30);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 50, 494, 60);
		getContentPane().add(textField);
		textField.setColumns(10);

	}

}
