package view;

import engine.BaseView;
import engine.GameEngine;
import java.io.IOException;
import model.GameData;
import control.StateControl;
import emulator.EmulatorGraphics;
import emulator.EmulatorImage;
import java.awt.Color;
import system.Painter;

/**
 *
 * @author Administrator
 */
public class StateView extends BaseView {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private EmulatorImage head = null;

    public void init() {
        StateControl sc = new StateControl();
        setControl(sc);
        try {
            head = EmulatorImage.createImage("product/image/character/head.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paint(EmulatorGraphics g) {
        Painter.drawDialog(g, 0, 0, gd.screenWidth, gd.screenHeight, Painter.DIALOG_LIGHT);
        g.drawImage(head, 10, 20, EmulatorGraphics.LT);
        Painter.drawString(g, gd.player.name, 20, 110, Color.black);
        g.setColor(Color.black);
        Painter.drawString(g, "生命值 " + gd.player.hp + "/" + gd.player.maxHp, 100, 30, Color.black);
        Painter.drawString(g, "魔法值 " + gd.player.sp + "/" + gd.player.maxSp, 100, 60, Color.black);
        Painter.drawString(g, "经验值 " + gd.player.exp + "/" + gd.player.levList[gd.player.lev], 100, 90, Color.black);
        Painter.drawString(g, "等级 " + gd.player.lev, 20, 140, Color.black);
        Painter.drawString(g, "攻击 " + gd.player.atk, 20, 180, Color.black);
        Painter.drawString(g, "防御 " + gd.player.def, 20, 220, Color.black);
        Painter.drawString(g, "闪避 " + gd.player.flee, 20, 260, Color.black);
        Painter.drawString(g, "力量 " + gd.player.stre, 130, 140, Color.black);
        Painter.drawString(g, "敏捷 " + gd.player.agil, 130, 180, Color.black);
        Painter.drawString(g, "智力 " + gd.player.inte, 130, 220, Color.black);
        Painter.drawString(g, "金币 " + gd.player.money, 130, 260, Color.black);
    }

    public void release() {
    }
}
