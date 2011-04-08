/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.game.control.EquipControl;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import com.soyostar.game.tools.Tools;
import com.soyostar.ui.Painter;
import java.awt.Color;



/**@2011.4.6 by VV
 *
 * 装备视图
 */
public class EquipView extends BaseView {

    private GameData gd = GameData.getGameData();
    public static final byte PAGE_MAIN = 0;
    public static final byte PAGE_CONFIRM = 1;
    public static final byte PAGE_TIP = 2;

    public void init() {
        EquipControl ec = new EquipControl();
        setControl(ec);
        gd.equip_pageIndex = PAGE_MAIN;
        gd.equip_selectIndex = 0;
        gd.equip_curEquip = gd.player.equipHelm;
    }

    public void paint(Painter painter) {
        switch (gd.equip_pageIndex) {
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

        Tools.drawDialog(painter, 0, 0, gd.screenWidth, gd.screenHeight, Painter.DIALOG_LIGHT);

        String[] items = {
            Const.Str.KINDS[0] + ": " + ((gd.player.equipHelm == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipHelm).name),
            Const.Str.KINDS[1] + ": " + ((gd.player.equipJewelry == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipJewelry).name),
            Const.Str.KINDS[2] + ": " + ((gd.player.equipWeapon == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipWeapon).name),
            Const.Str.KINDS[3] + ": " + ((gd.player.equipShield == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipShield).name),
            Const.Str.KINDS[4] + ": " + ((gd.player.equipArmour == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipArmour).name),
            Const.Str.KINDS[5] + ": " + ((gd.player.equipBoots == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipBoots).name)
        };
        Tools.drawTable(painter, 22, 40, 175, 30, 6, 10, items, Color.black, 0, gd.equip_selectIndex, Const.Anchor.LV, Painter.NODIALOG, Painter.CELL_DEEP);

    }

    private void paint_Confirm(Painter painter) {
        int h = 50;
        Tools.drawDialog(painter, 0, gd.screenHeight - h, gd.screenWidth, h, Painter.DIALOG_DEEP);
        painter.setColor(Color.black);
        painter.drawString("是否要将装备\"" + (gd.gameObjectManager.getEquip(gd.equip_curEquip).name) + "\"脱下", gd.screenWidth >>1, gd.screenHeight - h + (h - painter.getFontHeight()) >>1, Painter.HT);

    }

    private void paint_Tip(Painter painter) {
        int h = 50;
        Tools.drawDialog(painter, 0, gd.screenHeight - h, gd.screenWidth, h, Painter.DIALOG_DEEP);
        painter.setColor(Color.black);
        painter.drawString("装备成功脱下", gd.screenWidth / 2, gd.screenHeight - h + (h - painter.getFontHeight()) >>1, Painter.HT);

    }
}


