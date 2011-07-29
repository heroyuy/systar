/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.map;

import com.soyostar.app.Color;
import com.soyostar.app.Painter;
import com.soyostar.app.Widget;

/**
 *
 * @author Administrator
 */
public class LWave extends Widget {

    private int index = 0;

    public LWave(int x, int y) {
        this.setLocation(x, y);
        this.setSize(32, 32);
        this.setVisible(true);
    }

    @Override
    public void paint(Painter painter) {
        if (index > 5) {
            this.setVisible(false);
        }
        super.paint(painter);
        painter.setColor(Color.WHITE);
        int width = (int) Math.pow(2, index);
        int height = width;
        painter.drawRoundRect((32 - width) / 2, (32 - height) / 2, width, height, 16);
        index++;

    }
}
