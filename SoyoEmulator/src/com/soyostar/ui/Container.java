package com.soyostar.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Container extends Component {

	public Container() {
		super();
	}

	public Container(int x, int y) {
		super(x, y);
	}

	public Container(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void add(Component component) {
		System.out.println("add");
		getView().add(component.getView());
//		getView().add(new TCB(0,0,10,50));
	}

	public void remove(Component component) {
		getView().remove(component.getView());
	}

	public void removeAll() {
		getView().removeAll();
	}

	public void paint(Painter painter) {
		super.paint(painter);
		painter.setColor(Color.gray);
		painter.fillRect(0, 0, getWidth(), getHeight());
	}

}
