/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.SkillControl;
import engine.BaseView;
import engine.GameEngine;
import model.Bag;
import model.Const;
import model.GameData;
import control.SkillControl;
import emulator.EmulatorGraphics;
import java.awt.Color;
import system.Painter;

/**
 *
 * @author Administrator
 */
public class SkillView extends BaseView {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private String[] skills = null;

    public void init() {
        SkillControl sc = new SkillControl();
        setControl(sc);
        skills = new String[gd.player.bag.getList(Bag.SKILL).length];
        for (int i = 0; i < skills.length; i++) {
            skills[i] = gd.player.bag.get(Bag.SKILL, gd.player.bag.getList(Bag.SKILL)[i]).name;
        }
    }

    public void paint(EmulatorGraphics g) {
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
        g.drawString("技 能", gd.screenWidth / 2, (th - g.getEmulatorFont().getHeight()) / 2, Const.Anchor.HT);
        /**
         * 列表
         */
        //底框
        Painter.drawDialog(g, 0, th, gd.screenWidth, ch, Painter.DIALOG_LIGHT);
        //列表
        gd.skill_showNum = (ch - 2 * gap) / cellH;//可显示技能数量
        int space = ch - gd.skill_showNum * cellH;//总的竖向剩余空间
        space = space / (gd.skill_showNum + 1);//间距
        g.setEmulatorFont(Const.Font.FONTSMALL_PLAIN);
        Painter.drawTable(g, (gd.screenWidth - cellW) / 2 - gap / 2, th, cellW, cellH, gd.skill_showNum, space, skills, Color.BLACK, gd.skill_topIndex, gd.skill_curIndex, Const.Anchor.HV, Painter.NODIALOG, Painter.CELL_DEEP);

        /**
         * 介绍
         */
        Painter.drawDialog(g, 0, th + ch, gd.screenWidth, ih, Painter.DIALOG_DEEP);
        if (gd.player.bag.get(Bag.SKILL, gd.skill_curIndex) != null) {
            Painter.drawWordWrapString(g, gd.player.bag.get(Bag.SKILL, gd.skill_curIndex).intro, gap / 2, th + ch + gap / 2, gd.screenWidth - gap, ih - gap, Color.WHITE);

        }
        /**
         * 滚动条
         */
        Painter.drawScrollbar(g, Painter.SCROLLBAR_VERTICAL, (gd.screenWidth - cellW) / 2 + cellW + gap / 2, th + space, th + ch - space, gd.skill_topIndex, gd.skill_showNum, gd.player.bag.getList(Bag.SKILL).length);

//        if (gd.player.bag.getList(Bag.SKILL).length > 0) {
//            int min = Math.min(gd.player.bag.getList(Bag.SKILL).length, 7);
//            String texts[] = new String[gd.player.bag.getList(Bag.SKILL).length];
//            for (int i = 0; i < gd.player.bag.getList(Bag.SKILL).length; i++) {
//                texts[i] = gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[i]).name + " Lv" + gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[i]).num;
//            }
//            Painter.drawTable(g, 25, 48, 175, 24, min, 0, texts, 0, gd.Skill_Top_Magic, gd.skillSelect,
//                    Const.Anchor.LV, Painter.DIALOG_LIGHT, Painter.CELL_DEEP);
//            Painter.drawScrollbar(g, Painter.SCROLLBAR_VERTICAL, 204, 48, 220, gd.Skill_Top_Magic, 7, gd.player.bag.getList(Bag.SKILL).length);
//            Painter.drawTipString(g, gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.skillSelect]).intro, 232, 30, 210, 30, 220, 180, 20, 0);
//        } else {
//            Painter.drawString(g, "没有技能", 24, 50, 0);
//        }
    }

    public void release() {
    }
}
