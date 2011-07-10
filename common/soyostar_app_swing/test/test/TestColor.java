/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.soyostar.app.Color;

/**
 *
 * @author Administrator
 */
public class TestColor {

    public TestColor(int color){
        printColor(color);
    }

    public static void main(String[] args) {
        new TestColor(Color.getColor(0x1234, 0xab, 0x56, 0xef));
    }

    private void printColor(int color) {
        System.out.format("color:%x\n", color);
        System.out.format("color->a:%x\n", Color.getAlpha(color));
        System.out.format("color->r:%x\n", Color.getRed(color));
        System.out.format("color->g:%x\n", Color.getGreen(color));
        System.out.format("color->b:%x\n", Color.getBlue(color));

    }
}
