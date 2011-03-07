package view;

import control.SettingControl;
import engine.BaseView;
import engine.Game;
import engine.GameEngine;
import game.RpgGame;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import model.GameData;
import control.SettingControl;
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
    private Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);

    public void init() {
        setControl(new SettingControl());
        itemWidth = 14;
        x = (gd.screenWidth - itemWidth * 5) / 2;
        y = (gd.screenHeight - num * font.getHeight() - (num - 1) * space) / 2;
    }

    public void paint(Graphics g) {
        g.setFont(font);
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, 0x000000);
        //绘制按钮
        Painter.drawString(g, "确定", 5, gd.screenHeight - font.getHeight(), 0xffffff);
        //绘制阴影
        Painter.fillRoundRect(g, x, y + gd.yIndex * (font.getHeight() + space), itemWidth * 5, font.getHeight() + space - 2, 8, 0xaaaaaa);
        //绘制选项
        Painter.drawString(g, "音乐", x, y, 0xffffff);
        if (gd.musicOn) {
            Painter.drawString(g, "开", x + itemWidth * 3, y, 0xffffff);
        } else {
            Painter.drawString(g, "关", x + itemWidth * 3, y, 0xffffff);
        }

        Painter.drawString(g, "音效", x, y + font.getHeight() + space, 0xffffff);
        if (gd.soundOn) {
            Painter.drawString(g, "开", x + itemWidth * 3, y + font.getHeight() + space, 0xffffff);
        } else {
            Painter.drawString(g, "关", x + itemWidth * 3, y + font.getHeight() + space, 0xffffff);
        }
        //绘制两个三角形
        int color = 0xffffff;
        if (ge.getTicker() % 12 > 6) {
            color = 0xffffff;
        } else {
            color = 0xc9d65f;
        }
        Painter.drawTriangle(g, x + itemWidth * 3 - 10, y + font.getHeight() / 2 + gd.yIndex * (font.getHeight() + space), x + itemWidth * 3 - 5, y + 2 + gd.yIndex * (font.getHeight() + space), x + itemWidth * 3 - 5, y + font.getHeight() - 2 + gd.yIndex * (font.getHeight() + space), color);
        Painter.drawTriangle(g, x + itemWidth * 5 - 10, y + 2 + gd.yIndex * (font.getHeight() + space), x + itemWidth * 5 - 10, y + font.getHeight() - 2 + gd.yIndex * (font.getHeight() + space), x + itemWidth * 5 - 5, y + font.getHeight() / 2 + gd.yIndex * (font.getHeight() + space), color);

    }

    public void release() {
//        gd.xIndex = 0;
//        gd.yIndex = 0;
        gd = null;
        ge = null;
        font = null;
        setControl(null);
    }
}
