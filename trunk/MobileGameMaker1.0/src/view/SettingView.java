package view;

import control.SettingControl;
import emulator.engine.BaseView;
import emulator.engine.Game;
import emulator.engine.GameEngine;
import game.RpgGame;

import emulator.model.GameData;
import control.SettingControl;
import emulator.EmulatorFont;
import emulator.EmulatorGraphics;
import java.awt.Color;
import system.Painter;

/**
 *
 * 游戏设置
 */
public class SettingView extends BaseView {

    private GameData gd = GameData.getGameData();
//    private GameEngine ge = GameEngine.getInstance();
    private int itemWidth = 0;
    private String text = "";
//    private int space = 5;//行间距
//    private int num = 2;
    private int x, y;
    private EmulatorFont font = EmulatorFont.getEmulatorFont(EmulatorFont.FACE_SYSTEM, EmulatorFont.STYLE_PLAIN, EmulatorFont.SIZE_LARGE);

    public void init() {
        setControl(new SettingControl());
        text = "界面等待实现中";
        itemWidth = font.stringWidth(text);
        x = (gd.screenWidth - itemWidth) / 2;
        y = (gd.screenHeight - font.getHeight()) / 2;
    }

    public void paint(EmulatorGraphics g) {
        g.setEmulatorFont(font);
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, Color.black);

        Painter.drawString(g, text, x, y, Color.white);
        Painter.drawString(g, "确定", 5, gd.screenHeight - font.getHeight(), Color.white);
        

    }

    public void release() {
//        gd.xIndex = 0;
//        gd.yIndex = 0;
        gd = null;
//        ge = null;
        font = null;
        setControl(null);
    }
}
