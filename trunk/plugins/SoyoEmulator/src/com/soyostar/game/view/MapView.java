package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.game.RpgGame;
import com.soyostar.game.control.MapControl;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import com.soyostar.game.tools.Tools;
import com.soyostar.ui.Painter;
import java.awt.Color;

/**
 *
 * 地图视图
 */
public class MapView extends BaseView {

    public static final int PAGE_MAP = 0;// 地图行走页面
    public static final int PAGE_MENU = 1;// 菜单界面
    public static final int PAGE_DIALOG = 2;// 对话框界面
    public static final int PAGE_WAIT = 3;// 等待界面
    public static final int MENU_STATE = 0;// 状态
    public static final int MENU_BAG = 1;// 物品
    public static final int MENU_EQUIP = 2;// 装备
    public static final int MENU_SKILL = 3;// 技能
    public static final int MENU_SAVE = 4;// 保存
    public static final int MENU_RETURN = 5;// 返回
    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    public void init() {
        MapControl mc = new MapControl();
        setControl((Control) mc);
    }

    /**
     * 绘图
     */
    public void paint(Painter painter) {
        painter.setColor(Color.black);
        painter.fillRect( 0, 0, gd.screenWidth, gd.screenHeight);
        drawMap(painter);
        drawPlayer(painter);
        switch (gd.pageIndex) {
            case PAGE_MAP:
                break;
            case PAGE_MENU:
                drawMenu(painter);
                break;
            case PAGE_DIALOG:
                drawDialog(painter);
                break;
            case PAGE_WAIT:
                painter.setColor( Color.white);
                painter.drawString("等待" + (gd.waitTime - gd.waitIndex) + "秒", 5, gd.screenHeight - painter.getClipHeight(),painter.LT);
                break;

        }
    }

    public void release() {
        gd.pageIndex = PAGE_MAP;
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd.menuIndex = -1;
    }

    private void drawDialog(Painter painter) {
        Tools.drawDialog(painter, 0, gd.screenHeight - painter.getClipHeight()* 3 - 20,
                gd.screenWidth, painter.getClipHeight()* 3+ 20, Painter.DIALOG_DEEP);
       painter.setFontStyle(Painter.STYLE_PLAIN);
        // 对话人
       painter.setColor( Color.white);
        painter.drawString( gd.dialog_name + ":", 10, gd.screenHeight
                - painter.getClipHeight()* 3 - 10,Painter.LT);
        // 两行对话内容
        painter.drawString( gd.dialog_content[gd.dialog_index], 10,
                gd.screenHeight -painter.getClipHeight() * 2 - 10,Painter.LT);
        if (gd.dialog_index + 1 < gd.dialog_content.length) {
            painter.drawString( gd.dialog_content[gd.dialog_index + 1], 10,
                    gd.screenHeight - painter.getClipHeight() - 10, Painter.LT);
        }
    }

    private void drawMap(Painter painter) {
        painter.setColor(Color.black);
        painter.fillRect(0, 0, gd.screenWidth, gd.screenHeight);
//        System.out.println("x:" + gd.curMap.x);
//        System.out.println("y:" + gd.curMap.y);
        painter.drawRegion(gd.curMap.image, gd.curMap.image.getWidth() <= gd.screenWidth ? 0 : gd.curMap.x, gd.curMap.image.getHeight() <= gd.screenHeight ? 0 : gd.curMap.y,
                gd.screenWidth,
                gd.screenHeight, 0, gd.curMap.image.getWidth() <= gd.screenWidth ? -gd.curMap.x : 0, gd.curMap.image.getHeight() <= gd.screenHeight ? -gd.curMap.y : 0, 0);
    }

    private void drawPlayer(Painter painter) {
        painter.drawImage(gd.player.getCurCharImg(), gd.player.x - gd.curMap.x, gd.player.y - gd.curMap.y, Painter.LT);
    }

    private void drawMenu(Painter painter) {
        int w = 160, h = 30, gap = 5;
        Tools.drawTable(painter, (gd.screenWidth - w) / 2, (gd.screenHeight - Const.Str.MENU_MAP.length * h - (Const.Str.MENU_MAP.length - 1) * gap) / 2, w, h, Const.Str.MENU_MAP.length, gap, Const.Str.MENU_MAP, Color.BLACK, 0, gd.map_menuIndex, Const.Anchor.HV, Painter.NODIALOG, Painter.CELL_LIGHT);
    }
}
