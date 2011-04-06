package com.soyostar.game.view;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.game.control.StateControl;
import com.soyostar.game.model.GameData;
import com.soyostar.game.tools.Tools;
import com.soyostar.ui.Image;
import com.soyostar.ui.Painter;
import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class StateView extends BaseView {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private Image head = null;

    public void init() {
        StateControl sc = new StateControl();
        setControl(sc);
        try {
            head = Image.createImage("product/image/character/head.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paint(Painter painter) {
        Tools.drawDialog(painter, 0, 0, gd.screenWidth, gd.screenHeight, Painter.DIALOG_LIGHT);
        painter.drawImage(head, 10, 20, Painter.LT);
        painter.setColor(Color.black);
        painter.drawString(gd.player.name, 20, 110, Painter.LT);
        painter.drawString("生命值 " + gd.player.hp + "/" + gd.player.maxHp, 100, 30, Painter.LT);
        painter.drawString("魔法值 " + gd.player.sp + "/" + gd.player.maxSp, 100, 60, Painter.LT);
        painter.drawString("经验值 " + gd.player.exp + "/" + gd.player.levList[gd.player.lev], 100, 90, Painter.LT);
        painter.drawString("等级 " + gd.player.lev, 20, 140, Painter.LT);
        painter.drawString("攻击 " + gd.player.atk, 20, 180, Painter.LT);
        painter.drawString("防御 " + gd.player.def, 20, 220, Painter.LT);
        painter.drawString("闪避 " + gd.player.flee, 20, 260, Painter.LT);
        painter.drawString("力量 " + gd.player.stre, 130, 140, Painter.LT);
        painter.drawString("敏捷 " + gd.player.agil, 130, 180, Painter.LT);
        painter.drawString("智力 " + gd.player.inte, 130, 220, Painter.LT);
        painter.drawString("金币 " + gd.player.money, 130, 260, Painter.LT);
    }

    public void release() {
    }
}
