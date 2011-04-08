package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.game.control.MenuControl;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import com.soyostar.ui.Image;
import com.soyostar.ui.Painter;
import java.io.IOException;



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
        System.out.println("初始化菜单视图");
        setControl((Control) new MenuControl());
      
        try {
            jthuang = Image.createImage("product/image/skin/jthuang.png");
            back = Image.createImage("product/image/skin/back.png");
            kuang = Image.createImage("product/image/skin/kuang.png");
            jt = Image.createImage("product/image/skin/jt.png");
            menu = Image.createImage("product/image/skin/menu.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Painter painter) {
          itemWidth = painter.stringWidth(Const.Str.MENU_MENU[0]);
          painter.setFontSize(8);
          painter.setFontStyle(Painter.STYLE_PLAIN);
        painter.drawImage(back, 0, 0, 0);
       painter.drawImage(kuang, gd.screenWidth / 2, gd.screenHeight - 30, Painter.HV);
        painter.drawRegion(menu, 0, gd.xIndex * 19, 65, 19, 0, gd.screenWidth / 2, gd.screenHeight - 34, Painter.HV);
        painter.drawRegion(jt, 0, 0, jt.getWidth(), jt.getHeight(), 2,
            gd.screenWidth / 2 - 40 + ((ge.getTicker() % 5 == 0) ? 0 : -3), gd.screenHeight - 30, Painter.HB);
        painter.drawRegion(jt, 0, 0, jt.getWidth(), jt.getHeight(), 0,
            gd.screenWidth / 2 + 40 + ((ge.getTicker() % 5 == 0) ? 0 : 3), gd.screenHeight - 30, Painter.HB);
//        g.setColor(~0);
//        g.drawString("手游之星@2010 demo", gd.screenWidth / 2, gd.screenHeight, Graphics.BOTTOM | Graphics.HCENTER);
    }

    public void release() {
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd = null;
       
        setControl(null);
    }
}
