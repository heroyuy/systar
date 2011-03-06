package view;

import control.BagControl;
import emulator.EmulatorGraphics;
import engine.BaseView;
import engine.GameEngine;
import java.awt.Color;
import model.Bag;
import model.Const;
import model.GameData;
import system.Painter;

/**
 *
 * @author Administrator
 */
public class BagView extends BaseView {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private String[] tabItems = {"物品", "装备"};
    public static final byte PAGE_MAIN = 0;
    public static final byte PAGE_CONFIRM = 1;
    public static final byte PAGE_TIP = 2;

    public void init() {
        BagControl bc = new BagControl();
        setControl(bc);
        gd.buildItems();
    }

    public void paint(EmulatorGraphics g) {
        switch (gd.bag_pageIndex) {
            case PAGE_MAIN:
                paint_Main(g);
                break;
            case PAGE_CONFIRM:
                paint_Main(g);
                paint_Confirm(g);
                break;
            case PAGE_TIP:
                paint_Main(g);
                paint_Tip(g);
                break;
        }
    }

    public void release() {
        gd.bag_curIndex = 0;//回0
    }

    private void paint_Main(EmulatorGraphics g) {

        int th = 50;//标题栏高度
        int ih = 80;//介绍栏高度
        int ch = gd.screenHeight - th - ih;//中间列表区域高度
        int gap = 10;//间距
        int cellW = 160, cellH = 30;
        /**
         * 标题
         */
        Painter.drawDialog(g, 0, 0, gd.screenWidth, th, Painter.DIALOG_DEEP);
        g.setEmulatorFont(Const.Font.FONTLARGE_BOLD);
        g.setColor(Color.GREEN);
        g.drawString("背 包", gd.screenWidth / 2, (th - g.getEmulatorFont().getHeight()) / 2, Const.Anchor.HT);
        /**
         * 列表
         */
        //底框
        Painter.drawDialog(g, 0, th, gd.screenWidth, ch, Painter.DIALOG_DEEP);
        //选项卡
        Painter.drawTab(g, 0, th, gd.screenWidth, ch, Painter.DIALOG_LIGHT, gd.bag_tabIndex, tabItems);
        //列表
        gd.bag_showNum = (ch - 5 * gap) / cellH;//可显示技能数量
        int space = ch - 3 * gap - gd.bag_showNum * cellH;//总的竖向剩余空间
        space = space / (gd.bag_showNum + 1);//间距
        g.setColor(Color.BLACK);
        g.setEmulatorFont(Const.Font.FONTSMALL_PLAIN);
        Painter.drawTable(g, (gd.screenWidth - cellW) / 2 - gap / 2, th + 3 * gap, cellW, cellH, gd.bag_showNum, space, gd.bag_tabIndex == 0 ? gd.items : gd.equips, Color.BLACK, gd.bag_topIndex, gd.bag_curIndex, Const.Anchor.HV, Painter.NODIALOG, Painter.CELL_DEEP);

        /**
         * 介绍
         */
        Painter.drawDialog(g, 0, th + ch, gd.screenWidth, ih, Painter.DIALOG_DEEP);
        if (gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP).length > 0 && gd.player.bag.get(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP)[gd.bag_curIndex]) != null) {
            Painter.drawWordWrapString(g, gd.player.bag.get(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP)[gd.bag_curIndex]).intro, gap / 2, th + ch + gap / 2, gd.screenWidth - gap, ih - gap, Color.WHITE);

        }
        /**
         * 滚动条
         */
        Painter.drawScrollbar(g, Painter.SCROLLBAR_VERTICAL, (gd.screenWidth - cellW) / 2 + cellW + gap / 2, th + 3 * gap + space, th + ch - space, gd.bag_topIndex, gd.bag_showNum, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP).length);

    }

    private void paint_Confirm(EmulatorGraphics g) {
        int h = 50;
        Painter.drawDialog(g, 0, gd.screenHeight - h, gd.screenWidth, h, Painter.DIALOG_DEEP);
        g.setColor(Color.black);
        if (gd.player.bag.get(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP)[gd.bag_curIndex]) != null) {
            if (gd.bag_tabIndex == 0) {
                g.drawString("是否要使用物品\"" + (gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.bag_curIndex]).name) + "\"", gd.screenWidth / 2, gd.screenHeight - h + (h - g.getEmulatorFont().getHeight()) / 2, EmulatorGraphics.HT);

            } else {
                g.drawString("是否要将装备\"" + (gd.gameObjectManager.getEquip(gd.player.bag.getList(Bag.EQUIP)[gd.bag_curIndex]).name) + "\"穿上", gd.screenWidth / 2, gd.screenHeight - h + (h - g.getEmulatorFont().getHeight()) / 2, EmulatorGraphics.HT);

            }
        }


    }

    private void paint_Tip(EmulatorGraphics g) {
        int h = 50;
        Painter.drawDialog(g, 0, gd.screenHeight - h, gd.screenWidth, h, Painter.DIALOG_DEEP);
        g.setColor(Color.black);
        if (gd.bag_tabIndex == 0) {
            g.drawString("使用成功", gd.screenWidth / 2, gd.screenHeight - h + (h - g.getEmulatorFont().getHeight()) / 2, EmulatorGraphics.HT);

        } else {
            g.drawString("装备成功", gd.screenWidth / 2, gd.screenHeight - h + (h - g.getEmulatorFont().getHeight()) / 2, EmulatorGraphics.HT);

        }

    }
}
