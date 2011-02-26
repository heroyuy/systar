package view;

import control.EquipControl;
import engine.BaseView;
import javax.microedition.lcdui.Graphics;
import model.Const;
import model.Const.Color;
import model.GameData;
import system.Painter;

/**
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

    public void paint(Graphics g) {
        switch (gd.equip_pageIndex) {
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
    }

    private void paint_Main(Graphics g) {

        Painter.drawDialog(g, 0, 0, gd.screenWidth, gd.screenHeight, Painter.DIALOG_LIGHT);

        String[] items = {Const.Str.KINDS[0] + ": " + ((gd.player.equipHelm == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipHelm).name),
            Const.Str.KINDS[1] + ": " + ((gd.player.equipArmour == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipArmour).name),
            Const.Str.KINDS[2] + ": " + ((gd.player.equipWeapon == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipWeapon).name),
            Const.Str.KINDS[3] + ": " + ((gd.player.equipShield == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipShield).name),
            Const.Str.KINDS[4] + ": " + ((gd.player.equipBoots == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipBoots).name),
            Const.Str.KINDS[5] + ": " + ((gd.player.equipJewelry == -1) ? "空" : gd.gameObjectManager.getEquip(gd.player.equipJewelry).name)};
        Painter.drawTable(g, 22, 40, 175, 30, 6, 10, items, 0, 0, gd.equip_selectIndex, Const.Anchor.LV, Painter.NODIALOG, Painter.CELL_DEEP);

    }

    private void paint_Confirm(Graphics g) {
        int h = 50;
        Painter.drawDialog(g, 0, gd.screenHeight - h, gd.screenWidth, h, Painter.DIALOG_DEEP);
        g.setColor(Color.black);
        g.drawString("是否要将装备\"" + (gd.gameObjectManager.getEquip(gd.equip_curEquip).name) + "\"脱下", gd.screenWidth / 2, gd.screenHeight - h + (h - g.getFont().getHeight()) / 2, Graphics.TOP | Graphics.HCENTER);

    }

    private void paint_Tip(Graphics g) {
        int h = 50;
        Painter.drawDialog(g, 0, gd.screenHeight - h, gd.screenWidth, h, Painter.DIALOG_DEEP);
        g.setColor(Color.black);
        g.drawString("装备成功脱下", gd.screenWidth / 2, gd.screenHeight - h + (h - g.getFont().getHeight()) / 2, Graphics.TOP | Graphics.HCENTER);

    }
}
