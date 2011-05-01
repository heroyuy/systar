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
public class TestView2 implements View {

    public void init() {
        System.out.println(this.getClass().getName() + "->init");
    }

    public void paint(EmulatorGraphics eg) {
        System.out.println(this.getClass().getName() + "->paint");
        eg.setColor(Color.blue);
        eg.drawString("HelloWorld!!", 2, 2, 0);
    }

    public void release() {
    }
}
