package view;

import model.GameData;
import engine.BaseView;
import engine.GameEngine;
import game.RpgGame;

import javax.microedition.lcdui.*;
//import engine.model.*;
import system.Painter;
import control.MapControl;
import model.Const;

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
        setControl(mc);
    }

    /**
     * 绘图
     */
    public void paint(Graphics g) {
        Painter.fillRect(g, 0, 0, gd.screenWidth, gd.screenHeight, 0x000000);
        drawMap(g);
        drawPlayer(g);
        switch (gd.pageIndex) {
            case PAGE_MAP:
                break;
            case PAGE_MENU:
                drawMenu(g);
                break;
            case PAGE_DIALOG:
                drawDialog(g);
                break;
            case PAGE_WAIT:
                Painter.drawString(g, "等待" + (gd.waitTime - gd.waitIndex) + "秒", 5, gd.screenHeight - g.getFont().getHeight(),
                    0xffffff);
                break;

        }
    }

    public void release() {
        gd.pageIndex = PAGE_MAP;
        gd.xIndex = 0;
        gd.yIndex = 0;
        gd.menuIndex = -1;
    }

    private void drawDialog(Graphics g) {
        Painter.drawDialog(g, 0, gd.screenHeight - g.getFont().getHeight() * 3 - 20,
            gd.screenWidth, g.getFont().getHeight() * 3 + 20, Painter.DIALOG_DEEP);
        g.setFont(Const.Font.FONTSMALL_PLAIN);
        // 对话人
        Painter.drawString(g, gd.dialog_name + ":", 10, gd.screenHeight
            - g.getFont().getHeight() * 3 - 10, 0xffffff);
        // 两行对话内容
        Painter.drawString(g, gd.dialog_content[gd.dialog_index], 10,
            gd.screenHeight - g.getFont().getHeight() * 2 - 10, 0xffffff);
        if (gd.dialog_index + 1 < gd.dialog_content.length) {
            Painter.drawString(g, gd.dialog_content[gd.dialog_index + 1], 10,
                gd.screenHeight - g.getFont().getHeight() - 10, 0xffffff);
        }
    }

    private void drawMap(Graphics g) {
        g.setColor(0);
        g.fillRect(0, 0, gd.screenWidth, gd.screenHeight);
//        System.out.println("x:" + gd.curMap.x);
//        System.out.println("y:" + gd.curMap.y);
        g.drawRegion(gd.curMap.image, gd.curMap.image.getWidth() <= gd.screenWidth ? 0 : gd.curMap.x, gd.curMap.image.getHeight() <= gd.screenHeight ? 0 : gd.curMap.y,
            gd.screenWidth,
            gd.screenHeight, 0, gd.curMap.image.getWidth() <= gd.screenWidth ? -gd.curMap.x : 0, gd.curMap.image.getHeight() <= gd.screenHeight ? -gd.curMap.y : 0, 0);
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(gd.player.getCurCharImg(), gd.player.x - gd.curMap.x, gd.player.y - gd.curMap.y,
            Graphics.TOP | Graphics.LEFT);
    }

    private void drawMenu(Graphics g) {
        int w = 160, h = 30, gap = 5;
        Painter.drawTable(g, (gd.screenWidth - w) / 2, (gd.screenHeight - Const.Str.MENU_MAP.length * h - (Const.Str.MENU_MAP.length - 1) * gap) / 2, w, h, Const.Str.MENU_MAP.length, gap, Const.Str.MENU_MAP, Const.Color.BLACK, 0, gd.map_menuIndex, Const.Anchor.HV, Painter.NODIALOG, Painter.CELL_LIGHT);
    }
}
