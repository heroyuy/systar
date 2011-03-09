package view;

import emulator.engine.BaseView;
import control.AboutControl;

import emulator.model.GameData;
import control.AboutControl;
import emulator.EmulatorFont;
import emulator.EmulatorGraphics;
import java.awt.Color;
import system.Painter;

/**
 *
 * 游戏关于视图
 */
public class AboutView extends BaseView {

    private GameData gd = GameData.getGameData();
    private EmulatorFont font = EmulatorFont.getEmulatorFont(EmulatorFont.FACE_SYSTEM, EmulatorFont.STYLE_PLAIN, EmulatorFont.SIZE_SMALL);
    private String text = "";
    private int itemWidth = 0;
    private int x, y;

    public void init() {
        setControl(new AboutControl());
        text = gd.gameObjectManager.getConfig().about;
        itemWidth = font.stringWidth(text);
        x = (gd.screenWidth - itemWidth) / 2;
        y = (gd.screenHeight - font.getHeight()) / 2;
    }

    public void paint(EmulatorGraphics g) {
        g.setEmulatorFont(font);
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, Color.black);
        Painter.drawDialog(g, 0, 0, gd.screenWidth, gd.screenHeight, Painter.DIALOG_DEEP);
        Painter.drawWordWrapString(g, text, 10, 20, gd.screenWidth - 20, gd.screenHeight - 20, Color.white);
        //绘制按钮
        Painter.drawString(g, "确定", 5, gd.screenHeight - font.getHeight(), Color.white);

    }

    public void release() {
//        gd.xIndex = 0;
//        gd.yIndex = 0;
        gd = null;
        font = null;
        setControl(null);
    }
}
