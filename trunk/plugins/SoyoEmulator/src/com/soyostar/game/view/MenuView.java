/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.game.control.MenuControl;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import com.soyostar.ui.Image;
import com.soyostar.ui.Painter;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *@2011.4.5 by vv
 * 游戏菜单
 */
public class MenuView extends BaseView {

    GameData gd = GameData.getGameData();
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
        this.setControl(new MenuControl());

        jthuang = ge.getImageManager().getImage("image/skin/jthuang.png");
        back = ge.getImageManager().getImage("image/skin/back.png");
        kuang = ge.getImageManager().getImage("image/skin/kuang.png");
        jt = ge.getImageManager().getImage("image/skin/jt.png");
        menu = ge.getImageManager().getImage("image/skin/menu.png");

    }

    public void release() {
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd = null;

        setControl(null);
    }
    int test = 0;

    public void paint(Painter painter) {
        itemWidth = painter.stringWidth(Const.Str.MENU_MENU[0]);

        painter.drawImage(back, 0, 0, 0);
        painter.drawImage(kuang, gd.screenWidth / 2, gd.screenHeight - 30, Painter.HV);
        painter.drawRegion(menu, 0, gd.xIndex * 19, 65, 19, 0, gd.screenWidth / 2, gd.screenHeight - 34, Painter.HV);
        painter.drawRegion(jt, 0, 0, jt.getWidth(), jt.getHeight(), 2,
                gd.screenWidth / 2 - 40 + ((ge.getTicker() % 5 == 0) ? 0 : -3), gd.screenHeight - 30, Painter.HB);
        painter.drawRegion(jt, 0, 0, jt.getWidth(), jt.getHeight(), 0,
                gd.screenWidth / 2 + 40 + ((ge.getTicker() % 5 == 0) ? 0 : 3), gd.screenHeight - 30, Painter.HB);
    }
}
