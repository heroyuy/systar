/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.ui.Painter;
import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class MenuView extends BaseView {

    public void init() {
    }

    public void release() {
    }
    int test = 0;

    public void paint(Painter painter) {
        painter.setColor(Color.black);
        painter.fillRect(0, 0, 100, 100);
        painter.setColor(Color.white);
        painter.drawString(++test + "", 10, 10, Painter.LT);
    }
}
