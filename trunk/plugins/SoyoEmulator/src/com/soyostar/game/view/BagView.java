package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.game.control.BagControl;
import com.soyostar.game.model.Bag;

import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import com.soyostar.game.tools.Tools;
import com.soyostar.ui.Painter;
import java.awt.Color;

/**@2011.4.6 by VV
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

    public void paint(Painter painter) {
        switch (gd.bag_pageIndex) {
            case PAGE_MAIN:
                paint_Main(painter);
                break;
            case PAGE_CONFIRM:
                paint_Main(painter);
                paint_Confirm(painter);
                break;
            case PAGE_TIP:
                paint_Main(painter);
                paint_Tip(painter);
                break;
        }
    }

    public void release() {
        gd.bag_curIndex = 0;//回0
    }

    private void paint_Main(Painter painter) {

        int th = 50;//标题栏高度
        int ih = 80;//介绍栏高度
        int ch = gd.screenHeight - th - ih;//中间列表区域高度
        int gap = 10;//间距
        int cellW = 160, cellH = 30;
        /**
         * 标题
         */
      Tools.drawDialog( painter,0, 0, gd.screenWidth, th, Painter.DIALOG_DEEP);

        painter.setFontStyle(Painter.STYLE_BOLD);
        painter.setColor(Color.GREEN);
        painter.drawString("背 包", gd.screenWidth>>1, (th - painter.getFontHeight()) >>1, Const.Anchor.HT);
        /**
         * 列表
         */
        //底框
       Tools.drawDialog(painter, 0, th, gd.screenWidth, ch, Painter.DIALOG_DEEP);
        //选项卡
        Tools.drawTab(painter, 0, th, gd.screenWidth, ch, Painter.DIALOG_LIGHT, gd.bag_tabIndex, tabItems);
        //列表
        gd.bag_showNum = (ch - 5 * gap) / cellH;//可显示技能数量
        int space = ch - 3 * gap - gd.bag_showNum * cellH;//总的竖向剩余空间
        space = space / (gd.bag_showNum + 1);//间距
        painter.setColor(Color.BLACK);
       painter.setFontStyle(Painter.STYLE_PLAIN);
        Tools.drawTable(painter, (gd.screenWidth - cellW) / 2 - gap / 2, th + 3 * gap, cellW, cellH, gd.bag_showNum, space, gd.bag_tabIndex == 0 ? gd.items : gd.equips, Color.BLACK, gd.bag_topIndex, gd.bag_curIndex, Const.Anchor.HV, Painter.NODIALOG, Painter.CELL_DEEP);

        /**
         * 介绍
         */
        Tools.drawDialog(painter, 0, th + ch, gd.screenWidth, ih, Painter.DIALOG_DEEP);
        if (gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP).length > 0 && gd.player.bag.get(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP)[gd.bag_curIndex]) != null) {
            Tools.drawWordWrapString(painter, gd.player.bag.get(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP)[gd.bag_curIndex]).intro, gap / 2, th + ch + gap / 2, gd.screenWidth - gap, ih - gap, Color.WHITE);

        }
        /**
         * 滚动条
         */
        Tools.drawScrollbar(painter, Painter.SCROLLBAR_VERTICAL, (gd.screenWidth - cellW) / 2 + cellW + gap / 2, th + 3 * gap + space, th + ch - space, gd.bag_topIndex, gd.bag_showNum, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP).length);

    }

    private void paint_Confirm(Painter painter) {
        int h = 50;
        Tools.drawDialog(painter, 0, gd.screenHeight - h, gd.screenWidth, h, Painter.DIALOG_DEEP);
        painter.setColor(Color.black);
        if (gd.player.bag.get(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP)[gd.bag_curIndex]) != null) {
            if (gd.bag_tabIndex == 0) {
                painter.drawString("是否要使用物品\"" + (gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.bag_curIndex]).name) + "\"", gd.screenWidth / 2, gd.screenHeight - h + (h - painter.getFontHeight()) >>1, Painter.HT);

            } else {
                painter.drawString("是否要将装备\"" + (gd.gameObjectManager.getEquip(gd.player.bag.getList(Bag.EQUIP)[gd.bag_curIndex]).name) + "\"穿上", gd.screenWidth / 2, gd.screenHeight - h + (h - painter.getFontHeight()) >>1, Painter.HT);

            }
        }


    }

    private void paint_Tip(Painter painter) {
        int h = 50;
        Tools.drawDialog(painter, 0, gd.screenHeight - h, gd.screenWidth, h, Painter.DIALOG_DEEP);
        painter.setColor(Color.black);
        if (gd.bag_tabIndex == 0) {
            painter.drawString("使用成功", gd.screenWidth >>1, gd.screenHeight - h + (h -painter.getFontHeight()) >>1, Painter.HT);

        } else {
            painter.drawString("装备成功", gd.screenWidth >>1, gd.screenHeight - h + (h - painter.getFontHeight()) >>1, Painter.HT);

        }

    }
}


