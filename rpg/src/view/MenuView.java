package view;

import engine.BaseView;
import engine.GameEngine;
import control.MenuControl;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;
import model.Const;
import model.Const.Color;
import model.GameData;
import system.Painter;
import java.io.IOException;
import control.MenuControl;

/**
 *
 * 游戏菜单
 */
public class MenuView extends BaseView {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private int itemWidth = 0;
    private int space = 5;//行间距
    private int num = Const.Str.MENU_MENU.length;
    private Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    public static final byte START = 0;
    public static final byte CONTINUE = 1;
    public static final byte SETTING = 2;
    public static final byte HELP = 3;
    public static final byte ABOUT = 4;
    public static final byte EXIT = 5;
    private Image jthuang = null,
        back = null,
        kuang = null,
        menu = null,
        jt = null;

    public void init() {
        setControl(new MenuControl());
        itemWidth = font.stringWidth(Const.Str.MENU_MENU[0]);
        try {
            jthuang = Image.createImage("/image/skin/jthuang.png");
            back = Image.createImage("/image/skin/back.png");
            kuang = Image.createImage("/image/skin/kuang.png");
            jt = Image.createImage("/image/skin/jt.png");
            menu = Image.createImage("/image/skin/menu.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(back, 0, 0, 0);
        g.drawImage(kuang, gd.screenWidth / 2, gd.screenHeight - 30, Graphics.VCENTER | Graphics.HCENTER);
        g.drawRegion(menu, 0, gd.xIndex*19, 65, 19,0, gd.screenWidth / 2, gd.screenHeight - 34, Graphics.VCENTER | Graphics.HCENTER);
        g.drawRegion(jt, 0, 0, jt.getWidth(), jt.getHeight(), 2,
            gd.screenWidth / 2 - 40 + ((ge.getTicker() % 5 == 0) ? 0 : -3), gd.screenHeight - 30, Graphics.BOTTOM | Graphics.HCENTER);
        g.drawRegion(jt, 0, 0, jt.getWidth(), jt.getHeight(), 0,
            gd.screenWidth / 2 + 40 + ((ge.getTicker() % 5 == 0) ? 0 : 3), gd.screenHeight - 30, Graphics.BOTTOM | Graphics.HCENTER);
//        g.setColor(~0);
//        g.drawString("手游之星@2010 demo", gd.screenWidth / 2, gd.screenHeight, Graphics.BOTTOM | Graphics.HCENTER);
    }

    public void release() {
//        gd.xIndex = 0;
//        gd.yIndex = 0;
        gd = null;
        font = null;
        setControl(null);
    }
}
