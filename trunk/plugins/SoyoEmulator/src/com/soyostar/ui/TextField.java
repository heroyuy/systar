package com.soyostar.ui;

import javax.swing.JTextField;

public class TextField extends Component {

	private JTextField view = null;

	public TextField() {
		view = new JTextField();
	}

	public TextField(int x, int y) {
		view = new JTextField();
		view.setLocation(x, y);
	}

	public TextField(int x, int y, int width, int height) {
		view = new JTextField();
		view.setLocation(x, y);
		view.setSize(width, height);
	}

	public JTextField getView() {
		return view;
	}

}
