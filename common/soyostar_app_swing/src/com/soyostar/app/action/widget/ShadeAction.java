package com.soyostar.app.action.widget;

import com.soyostar.app.Color;
import com.soyostar.app.Widget;
import com.soyostar.app.action.Action;

/**
 *
 * @author Administrator
 */
public class ShadeAction<T extends Widget> extends Action {

    private T target = null;
    private int[] color1s = new int[4];
    private int[] color2s = new int[4];
    private int[] colors = new int[4];
    private int speed = 0;
    private int times = -1;
    private int num = 0;

    public ShadeAction(T target, int color1, int color2, int speed, int times) {
        this.target = target;
        this.speed = speed;
        if (times < 0) {
            times = Integer.MAX_VALUE;
        }
        this.times = times;

        colors[0] = color1s[0] = Color.getAlpha(color1);
        colors[1] = color1s[1] = Color.getRed(color1);
        colors[2] = color1s[2] = Color.getGreen(color1);
        colors[3] = color1s[3] = Color.getBlue(color1);

        color2s[0] = Color.getAlpha(color2);
        color2s[1] = Color.getRed(color2);
        color2s[2] = Color.getGreen(color2);
        color2s[3] = Color.getBlue(color2);


    }

    public void run() {
        if (num < times) {

            for (int i = 0; i < 4; i++) {
                if (color1s[i] < color2s[i]) {
                    colors[i] += speed;
                    if (colors[i] >= color2s[i]) {
                        num++;
                        colors[i] = color2s[i];
                        color2s[i] = color1s[i];
                        color1s[i] = colors[i];
                    }
                } else {
                    num++;
                    colors[i] -= speed;
                    if (colors[i] <= color2s[i]) {
                        colors[i] = color2s[i];
                        color2s[i] = color1s[i];
                        color1s[i] = colors[i];
                    }
                }
            }
            target.setBackground(Color.getColor(colors[0], colors[1], colors[2], colors[3]));
        } else {
            freeze();
        }
    }
}
