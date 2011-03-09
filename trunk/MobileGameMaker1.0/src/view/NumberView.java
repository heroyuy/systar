/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.NumberControl;
import emulator.EmulatorFont;
import emulator.EmulatorGraphics;
import emulator.engine.BaseView;
import emulator.model.Bag;
import emulator.model.GameData;
import java.awt.Color;
import system.Painter;

/**
 *
 * @author Administrator
 */
public class NumberView extends BaseView {

    private GameData gd = GameData.getGameData();
    private EmulatorFont font = EmulatorFont.getEmulatorFont(EmulatorFont.FACE_SYSTEM, EmulatorFont.STYLE_PLAIN, EmulatorFont.SIZE_SMALL);

    public void init() {
        NumberControl sc = new NumberControl();
        setControl(sc);
    }
    int time = 0;

    public void paint(EmulatorGraphics g) {
        time++;
        g.setEmulatorFont(font);
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, Color.black);
        Painter.drawDialog(g, 0, 0, gd.screenWidth, gd.screenHeight, Painter.DIALOG_DEEP);
        Painter.fillRoundRect(g, 20, 20, gd.screenWidth - 40, 20, EmulatorGraphics.LT, Color.white);
        if (time % 5 == 0) {
            g.setColor(Color.black);
            g.drawLine(22 + gd.stringBuffer.length() * font.stringWidth("0"), 21, 22 + gd.stringBuffer.length() * font.stringWidth("0"), 38);

        }
        Painter.drawString(g, "请输入要买卖的数量，按#键回退", 22, 41, Color.black);
        Painter.drawString(g, gd.stringBuffer.toString(), 22, 21, Color.black);
        Painter.drawString(g, "确定", 5, gd.screenHeight - font.getHeight() - 8, Color.black);
        Painter.drawString(g, "取消", gd.screenWidth - 10 - font.stringWidth("取消"), gd.screenHeight - font.getHeight() - 8, Color.black);
    }

    public void release() {
        gd.stringBuffer = new StringBuffer();
        gd = null;
        font = null;

        setControl(null);
    }
}
