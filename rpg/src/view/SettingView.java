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
import model.Const.Color;
import system.Painter;

/**
 *
 * 游戏设置
 */
public class SettingView extends BaseView {

    private GameData gd = GameData.getGameData();
//    private GameEngine ge = GameEngine.getInstance();
    private int itemWidth = 0;
//    private int space = 5;//行间距
//    private int num = 2;
    private int x, y;
    private Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
    private String text = "";

    public void init() {
        setControl(new SettingControl());
        text = "界面等待实现中";
        itemWidth = 14;
        x = (gd.screenWidth - itemWidth * 5) / 2;
        y = (gd.screenHeight - font.getHeight()) / 2;
    }

    public void paint(Graphics g) {
        g.setFont(font);
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, 0x000000);
        Painter.drawString(g, text, x, y, Color.white);

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
