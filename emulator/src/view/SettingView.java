package view;

import control.SettingControl;
import engine.BaseView;
import engine.Game;
import engine.GameEngine;
import game.RpgGame;

import model.GameData;
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
    private GameEngine ge = GameEngine.getInstance();
    private int itemWidth = 0;
    private int space = 5;//行间距
    private int num = 2;
    private int x, y;
    private EmulatorFont font = EmulatorFont.getFont(EmulatorFont.FACE_SYSTEM, EmulatorFont.STYLE_PLAIN, EmulatorFont.SIZE_LARGE);

    public void init() {
        setControl(new SettingControl());
        itemWidth = 14;
        x = (gd.screenWidth - itemWidth * 5) / 2;
        y = (gd.screenHeight - num * font.getHeight() - (num - 1) * space) / 2;
    }

    public void paint(EmulatorGraphics g) {
        g.setEmulatorFont(font);
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, Color.black);
        //绘制按钮
        Painter.drawString(g, "确定", 5, gd.screenHeight - font.getHeight(), Color.white);
        //绘制阴影
        Painter.fillRoundRect(g, x, y + gd.yIndex * (font.getHeight() + space), itemWidth * 5, font.getHeight() + space - 2, 8, Color.red);
        //绘制选项
        Painter.drawString(g, "音乐", x, y, Color.white);
        if (gd.musicOn) {
            Painter.drawString(g, "开", x + itemWidth * 3, y, Color.white);
        } else {
            Painter.drawString(g, "关", x + itemWidth * 3, y, Color.white);
        }

        Painter.drawString(g, "音效", x, y + font.getHeight() + space, Color.white);
        if (gd.soundOn) {
            Painter.drawString(g, "开", x + itemWidth * 3, y + font.getHeight() + space, Color.white);
        } else {
            Painter.drawString(g, "关", x + itemWidth * 3, y + font.getHeight() + space, Color.white);
        }
        //绘制两个三角形
        Color color = Color.white;
        if (ge.getTicker() % 12 > 6) {
            color = Color.white;
        } else {
            color = Color.blue;
        }
        Painter.drawTriangle(g, x + itemWidth * 3 - 10, y + font.getHeight() / 2 + gd.yIndex * (font.getHeight() + space), x + itemWidth * 3 - 5, y + 2 + gd.yIndex * (font.getHeight() + space), x + itemWidth * 3 - 5, y + font.getHeight() - 2 + gd.yIndex * (font.getHeight() + space), color);
        Painter.drawTriangle(g, x + itemWidth * 5 - 10, y + 2 + gd.yIndex * (font.getHeight() + space), x + itemWidth * 5 - 10, y + font.getHeight() - 2 + gd.yIndex * (font.getHeight() + space), x + itemWidth * 5 - 5, y + font.getHeight() / 2 + gd.yIndex * (font.getHeight() + space), color);

    }

    public void release() {
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd = null;
        ge = null;
        font = null;
        setControl(null);
    }
}
