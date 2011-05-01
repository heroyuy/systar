/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.impl.view;

import emulator.EmulatorGraphics;
import game.View;
import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class TestView implements View {

    public void init() {
        System.out.println(this.getClass().getName() + "->init");
    }

    public void paint(EmulatorGraphics eg) {
        System.out.println(this.getClass().getName() + "->paint");
        eg.setColor(Color.black);
        eg.fillRect(0, 0, 100, 100);
        eg.setColor(Color.yellow);
        eg.drawString("HelloWorld!!", 0, 0, 0);
    }

    public void release() {
    }
}
