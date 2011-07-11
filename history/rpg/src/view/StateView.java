package view;

import control.SkillControl;
import control.StateControl;
import engine.BaseView;
import engine.GameEngine;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import model.Bag;
import model.Const;
import model.GameData;
import control.StateControl;
import system.Painter;

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
        int width = gd.player.chaImage.getWidth() / 7;
        int height = gd.player.chaImage.getHeight();
//        try {
        head = Image.createImage(gd.player.chaImage, width * 2, 0, width, height, 0);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    public void paint(Graphics g) {
        Painter.drawDialog(g, 0, 0, gd.screenWidth, gd.screenHeight, Painter.DIALOG_LIGHT);
        g.drawImage(head, 20, 40, 0);
        Painter.drawString(g, gd.player.name, 20, 110, 0);
        g.setColor(0);
        Painter.drawString(g, "生命值 " + gd.player.hp + "/" + gd.player.maxHp, 100, 30, 0);
        Painter.drawString(g, "魔法值 " + gd.player.sp + "/" + gd.player.maxSp, 100, 60, 0);
        Painter.drawString(g, "经验值 " + gd.player.exp + "/" + gd.player.levList[gd.player.lev], 100, 90, 0);
        Painter.drawString(g, "等级 " + gd.player.lev, 20, 140, 0);
        Painter.drawString(g, "攻击 " + gd.player.atk, 20, 180, 0);
        Painter.drawString(g, "防御 " + gd.player.def, 20, 220, 0);
        Painter.drawString(g, "闪避 " + gd.player.flee, 20, 260, 0);
        Painter.drawString(g, "力量 " + gd.player.stre, 130, 140, 0);
        Painter.drawString(g, "敏捷 " + gd.player.agil, 130, 180, 0);
        Painter.drawString(g, "智力 " + gd.player.inte, 130, 220, 0);
        Painter.drawString(g, "金币 " + gd.player.money, 130, 260, 0);
    }

    public void release() {
    }
}
