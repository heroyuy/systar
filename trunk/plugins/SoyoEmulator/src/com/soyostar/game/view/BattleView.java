/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.view;

import model.Const;
import model.Bag;
import model.GameData;
import model.EnemyTroop;
import engine.animation.AnimationPlayer;
import engine.BaseView;
import engine.GameEngine;
import control.BattleControl;
//import model.*;
import system.Painter;
import java.io.IOException;
import system.Tools;
//import javax.microedition.lcdui.game.Sprite;
import game.RpgGame;
import control.BattleControl;
import emulator.EmulatorFont;
import emulator.EmulatorGraphics;
import emulator.EmulatorImage;
import java.awt.Color;

/**
 *
 * 游戏战斗视图
 */
/*       战斗采用旋转菜单模式：
 *              攻击
 *
 *      物品             技能
 *
 *          装备      逃跑
 * 战斗系统属于ATB战斗系统，即行动槽集满后才能行动，而行动槽聚集速度和敏捷,速度等因素有关
 * 点击攻击->选择敌人单体->攻击->
 * 点击技能->选择玩家技能->选择敌人单体->攻击->
 *                      ->攻击敌人全体
 * 点击装备->选择玩家装备->选择装备->装备->
 * 点击物品->选择玩家物品->选择物品->使用->
 * 点击逃跑->逃跑->
 *
 * 飘血线路为抛物线形式
 *
 */
public class BattleView extends BaseView {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();
    private EmulatorImage battBg = null,//战斗背景
            //        numberImg = null,//减血图片
            //        numberImg2 = null,//加血图片
            bar = null,//英雄战斗图
            hp1 = null,
            hp2 = null,
            sp1 = null,
            sp2 = null,
            hpback1 = null,
            hpback2 = null,
            //        gameOverImg = null,//战斗失败图片
            process = null,//行动条底图
            arr = null,//角色选中标记
            //        signcess = null,
            ack = null,
            def = null,
            escape = null,
            magic = null,
            item = null;
//        sign = null;
    private EmulatorFont font = EmulatorFont.getEmulatorFont(EmulatorFont.FACE_SYSTEM, EmulatorFont.STYLE_PLAIN, EmulatorFont.SIZE_SMALL);
    private EnemyTroop enemyTroop;

    public BattleView() {
    }

    public void init() {
        gd.isWin = false;
        gd.isFail = false;
        try {
            bar = EmulatorImage.createImage("product/image/skin/bar.png");
            hp1 = EmulatorImage.createImage(bar, 0, 0, 12, 12, 0);
            hp2 = EmulatorImage.createImage(bar, 12, 0, 12, 12, 0);
            sp1 = EmulatorImage.createImage(bar, 24, 0, 12, 12, 0);
            sp2 = EmulatorImage.createImage(bar, 36, 0, 12, 12, 0);
            hpback1 = EmulatorImage.createImage(bar, 48, 0, 12, 12, 0);
            hpback2 = EmulatorImage.createImage(bar, 60, 0, 12, 12, 0);
//            battBg = Image.createImage("/image/battleback/battbg.png");
            battBg = Painter.effect_transparent_Other(gd.curMap.image, 200);//200为透明度，越大越不透明
            process = EmulatorImage.createImage("product/image/skin/process.png");
            arr = EmulatorImage.createImage("product/image/skin/arr.png");
//            signcess = Image.createImage("/image/skin/signcess.png");
//            sign = Image.createImage("/image/skin/sign.png");
            ack = EmulatorImage.createImage("product/image/skin/attack.png");
            def = EmulatorImage.createImage("product/image/skin/defend.png");
            escape = EmulatorImage.createImage("product/image/skin/tp.png");
            magic = EmulatorImage.createImage("product/image/skin/skill.png");
            item = EmulatorImage.createImage("product/image/skin/goods.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        enemyTroop = gd.gameObjectManager.getEnemyTroop(gd.enemyTroopID);
        if (enemyTroop.siteA != -1) {
            gd.enemy[0] = gd.gameObjectManager.getEnemy(enemyTroop.siteA).getClone();
            gd.enemy[0].BattX = 120 - 35 * 0;
            gd.enemy[0].BattY = 50 + 12 * 0;
            gd.allExp += gd.enemy[0].exp;
            gd.allMoney += gd.enemy[0].money;
        }
        if (enemyTroop.siteB != -1) {
            gd.enemy[1] = gd.gameObjectManager.getEnemy(enemyTroop.siteB).getClone();
            gd.enemy[1].BattX = 120 - 35 * 1;
            gd.enemy[1].BattY = 50 + 12 * 1;
            gd.allExp += gd.enemy[1].exp;
            gd.allMoney += gd.enemy[1].money;
        }
        if (enemyTroop.siteC != -1) {
            gd.enemy[2] = gd.gameObjectManager.getEnemy(enemyTroop.siteC).getClone();
            gd.enemy[2].BattX = 120 - 35 * 2;
            gd.enemy[2].BattY = 50 + 12 * 2;
            gd.allExp += gd.enemy[2].exp;
            gd.allMoney += gd.enemy[2].money;
        }
        if (enemyTroop.siteD != -1) {
            gd.enemy[3] = gd.gameObjectManager.getEnemy(enemyTroop.siteD).getClone();
            gd.enemy[3].BattX = 120 - 35 * 3;
            gd.enemy[3].BattY = 50 + 12 * 3;
            gd.allExp += gd.enemy[3].exp;
            gd.allMoney += gd.enemy[3].money;
        }

        AnimationPlayer.getInstance().removeAllAnimations();
        AnimationPlayer.getInstance().start();

        setControl(new BattleControl());//注意应该把所有应该准备的资源准备好了才能开始游戏逻辑线程
    }

    private void drawMain(EmulatorGraphics g) {
        g.drawImage(ack, 60, 165 + (gd.select == 0 ? (ge.getTicker() % 5 == 0 ? 0 : 3) : 0), 0);
        g.drawImage(item, 25, 190 + (gd.select == 1 ? (ge.getTicker() % 5 == 0 ? 0 : 3) : 0), 0);
        g.drawImage(def, 40, 220 + (gd.select == 2 ? (ge.getTicker() % 5 == 0 ? 0 : 3) : 0), 0);
        g.drawImage(escape, 80, 220 + (gd.select == 3 ? (ge.getTicker() % 5 == 0 ? 0 : 3) : 0), 0);
        g.drawImage(magic, 95, 190 + (gd.select == 4 ? (ge.getTicker() % 5 == 0 ? 0 : 3) : 0), 0);
    }

    private void drawChoose(EmulatorGraphics g) {
        if (gd.attackType == 0) {
            while (gd.enemy[gd.Select_Eny] == null) {//防止越界
                gd.Select_Eny++;
                if (gd.Select_Eny > 3) {
                    gd.Select_Eny = 0;
                }
            }
            g.drawImage(arr, gd.enemy[gd.Select_Eny].BattX + gd.enemy[gd.Select_Eny].BattImg.getWidth() / 2, gd.enemy[gd.Select_Eny].BattY - 15, 0);// 箭头
//            g.drawImage(arr, gd.enemy[gd.Select_Eny].BattX + 5, gd.enemy[gd.Select_Eny].BattY - 10, 0);// 箭头
        }
    }

    private void drawItem(EmulatorGraphics g) {

        if (gd.attackType == 0) {

            switch (gd.Select_Item_Main) {
                case BattleControl.Item_Main:
                    Painter.drawDialog(g, 20, 20, 200, 225, Painter.DIALOG_LIGHT);
                    Painter.drawDialog(g, 21, 21, 198, 24, Painter.DIALOG_DEEP);
                    g.setColor(Color.black);
                    g.drawString("物品", gd.screenWidth / 2, 24, EmulatorGraphics.HT);
                    Painter.drawDialog(g, 21, 220, 198, 24, Painter.DIALOG_DEEP);
                    if (gd.player.bag.getList(Bag.ITEM).length > 0) {
                        int min = Math.min(gd.player.bag.getList(Bag.ITEM).length, 7);

                        String texts[] = new String[gd.player.bag.getList(Bag.ITEM).length];
                        for (int i = 0; i < gd.player.bag.getList(Bag.ITEM).length; i++) {
                            texts[i] = gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[i]).name + " x" + gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[i]).num;
                        }
                        Painter.drawTable(g, 25, 48, 175, 24, min, 0, texts, Color.black, gd.Top_Good, gd.Select_Good,
                                Const.Anchor.LV, Painter.DIALOG_LIGHT, Painter.CELL_DEEP);
                        Painter.drawScrollbar(g, Painter.SCROLLBAR_VERTICAL, 204, 48, 220, gd.Top_Good, 7, gd.player.bag.getList(Bag.ITEM).length);
//                        Painter.drawScrollbar(g, Painter.SCROLLBAR_VERTICAL, 200, 48, 200, 5, 5, 10);
                        Painter.drawTipString(g, gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.Select_Good]).intro, 232, 30, 210, 30, 220, 180, 20, Color.black);
                    } else {
                        Painter.drawString(g, "没有物品", 24, 50, Color.black);

                    }

                    break;
                case BattleControl.Item_Chose_Eny:
                    while (gd.enemy[gd.Select_Item_Eny] == null) {//防止越界
                        gd.Select_Item_Eny++;
                        if (gd.Select_Item_Eny > 3) {
                            gd.Select_Item_Eny = 0;
                        }
                    }
                    g.drawImage(arr, gd.enemy[gd.Select_Item_Eny].BattX + gd.enemy[gd.Select_Item_Eny].BattImg.getWidth() / 2, gd.enemy[gd.Select_Item_Eny].BattY - 15, 0);// 箭头
                    break;
            }
        }
    }

    private void drawMagic(EmulatorGraphics g) {

        if (gd.attackType == 0) {

            switch (gd.Select_Magic_Main) {
                case BattleControl.Magic_Main:
                    Painter.drawDialog(g, 20, 20, 200, 225, Painter.DIALOG_LIGHT);
                    Painter.drawDialog(g, 21, 21, 198, 24, Painter.DIALOG_DEEP);
                    g.setColor(Color.black);
                    g.drawString("技能", gd.screenWidth / 2, 24, EmulatorGraphics.HT);
                    Painter.drawDialog(g, 21, 220, 198, 24, Painter.DIALOG_DEEP);
                    if (gd.player.bag.getList(Bag.SKILL).length > 0) {
                        int min = Math.min(gd.player.bag.getList(Bag.SKILL).length, 7);
                        String texts[] = new String[gd.player.bag.getList(Bag.SKILL).length];
                        for (int i = 0; i < gd.player.bag.getList(Bag.SKILL).length; i++) {
                            texts[i] = gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[i]).name + " Lv" + gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[i]).num;
                        }
                        Painter.drawTable(g, 25, 48, 175, 24, min, 0, texts, Color.black, gd.Top_Magic, gd.Select_Magic,
                                Const.Anchor.LV, Painter.DIALOG_LIGHT, Painter.CELL_DEEP);
                        Painter.drawScrollbar(g, Painter.SCROLLBAR_VERTICAL, 204, 48, 220, gd.Top_Magic, 7, gd.player.bag.getList(Bag.SKILL).length);
                        Painter.drawTipString(g, gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).intro, 232, 30, 210, 30, 220, 180, 20, Color.black);
                    } else {
                        Painter.drawString(g, "没有技能", 24, 50, Color.black);
                    }


                    break;
                case BattleControl.Magic_Chose_Eny:
                    while (gd.enemy[gd.Select_Magic_Eny] == null) {//防止越界
                        gd.Select_Magic_Eny++;
                        if (gd.Select_Magic_Eny > 3) {
                            gd.Select_Magic_Eny = 0;
                        }
                    }
                    g.drawImage(arr, gd.enemy[gd.Select_Magic_Eny].BattX + gd.enemy[gd.Select_Magic_Eny].BattImg.getWidth() / 2, gd.enemy[gd.Select_Magic_Eny].BattY - 15, 0);// 箭头
//                    g.drawImage(arr, gd.enemy[gd.Select_Magic_Eny].BattX + 5, gd.enemy[gd.Select_Magic_Eny].BattY - 10, 0);// 箭头
                    break;
            }

        }
    }

    public void drawBg(EmulatorGraphics g) {
        Tools.cleanScreen(g);
        g.setEmulatorFont(font);
        g.drawImage(battBg, 0, 0, 0);
        /**********************背景***********************/
        g.drawRegion(hpback1, 0, 0, 12, 12, 0, 5, 270, 0);
        int j = 0;
        for (j = 0; j < 8; j++) {
            g.drawRegion(hpback2, 0, 0, 12, 12, 0, 17 + j * 12, 270, 0);
        }
        g.drawRegion(hpback1, 0, 0, 12, 12, 2, 17 + j * 12, 270, 0);
        g.drawRegion(hpback1, 0, 0, 12, 12, 0, 5, 290, 0);
        for (j = 0; j < 8; j++) {
            g.drawRegion(hpback2, 0, 0, 12, 12, 0, 17 + j * 12, 290, 0);
        }
        g.drawRegion(hpback1, 0, 0, 12, 12, 2, 17 + j * 12, 290, 0);
        /************************************************/
        /*********************血条************************/
        g.setClip(5, 270, gd.player.hp * 125 / gd.player.maxHp, 12);
        g.drawRegion(hp1, 0, 0, 12, 12, 0, 5, 270, 0);
        int i = 0;
        for (i = 0; i < 8; i++) {
            g.drawRegion(hp2, 0, 0, 12, 12, 0, 17 + i * 12, 270, 0);
        }
        g.drawRegion(hp1, 0, 0, 12, 12, 2, 17 + i * 12, 270, 0);
        g.setClip(0, 0, gd.screenWidth, gd.screenHeight);
        /************************************************/
        /*********************魔条************************/
        g.setClip(5, 290, gd.player.sp * 125 / gd.player.maxSp, 12);
        g.drawRegion(sp1, 0, 0, 12, 12, 0, 5, 290, 0);
        for (i = 0; i < 8; i++) {
            g.drawRegion(sp2, 0, 0, 12, 12, 0, 17 + i * 12, 290, 0);
        }
        g.drawRegion(sp1, 0, 0, 12, 12, 2, 17 + i * 12, 290, 0);
        g.setClip(0, 0, gd.screenWidth, gd.screenHeight);
        /************************************************/
        Painter.drawString(g, gd.player.hp + "/" + gd.player.maxHp, 140, 270, Color.ORANGE);
        Painter.drawString(g, gd.player.sp + "/" + gd.player.maxSp, 140, 290, Color.ORANGE);
    }

    private void battleFunction(EmulatorGraphics g) {
        byte somebody = 0;// 默认当前有人(角色/敌人)先读完了进度
        for (int i = 0; i < gd.enemy.length; i++) {
            if (gd.enemy[i] == null) {
                continue;
            }
            if (!gd.enemy[i].isDead()) {
                if (gd.enemy[i].waitTime >= 360) {
                    somebody = ENEMYS_TIME;
                }
            }
        }
        if (gd.player.waitTime >= 360) {
            somebody = PLAYER_TIME;
        }
        if (somebody == 0) {// 没有角色/敌人读完的话，大家一起读进度
            if ((gd.player.waitTime < 360) && (!gd.player.isDead())) {
                gd.player.waitTime += gd.player.agil;
            }
            for (int i = 0; i < gd.enemy.length; i++) {
                if (gd.enemy[i] == null) {
                    continue;
                }
                if ((gd.enemy[i].waitTime < 360) && (!gd.enemy[i].isDead())) {
                    gd.enemy[i].waitTime += gd.enemy[i].agil;
                }
            }
        } else if (somebody == PLAYER_TIME) {//角色先读完进度
            heroAction(g);

        } else if (somebody == ENEMYS_TIME) {// 敌人先读完进度
            for (int i = 0; i < gd.enemy.length; i++) {
                if (gd.enemy[i] == null) {
                    continue;
                }
                if (gd.enemy[i].waitTime >= 360) {
                    gd.attackType = gd.enemy[i].AI();//受ai函数控制
                    gd.enemyShould = i;
                    enemyAction(i, g);
                    break;// 一次只能有一个读完
                }
            }
        }
    }
    //轮到敌人时绘制

    private void enemyAction(int id, EmulatorGraphics g) {
    }
    //轮到英雄时绘制

    private void heroAction(EmulatorGraphics g) {
//        System.out.println("isRuning "+gd.isRuning);
        if (gd.attackType == 0) {//攻击敌人时，英雄头上箭头消失
            g.drawImage(arr, gd.player.heroBattX + 10, gd.player.heroBattY - 15, 0);// 箭头
        }
    }
    static final byte PLAYER_TIME = 1;
    static final byte ENEMYS_TIME = 2;

    private void moveBar(EmulatorGraphics g) {
        g.drawImage(process, gd.screenWidth / 2, 10, Const.Anchor.HV);
        int proc = gd.player.waitTime * 116 / 360;

//        g.drawRegion(Painter., 28, 0, 24, 24, 0, 40 + proc, 13, 0);
        for (int i = 0; i < gd.enemy.length; i++) {
            if (gd.enemy[i] == null) {
                continue;
            }
            if (!gd.enemy[i].isDead()) {
                int proce = gd.enemy[i].waitTime * 116 / 360;
                g.drawImage(Painter.effect_resizeImage(gd.enemy[i].BattImg, 15, 15), gd.screenWidth / 2 - process.getWidth() / 2 + proce, 12, 0);
            }
        }
        g.drawImage(Painter.effect_resizeImage(gd.player.headImg, 15, 15), gd.screenWidth / 2 - process.getWidth() / 2 + proc, 12, 0);//确保英雄在上面
    }

    //关屏动画
    private void drawClose(EmulatorGraphics g) {
        OpenBlock += 2;
        g.setColor(Color.black);
        for (int i = 0; i < 240 / 20 + 1; i++) {
            g.fillRect(i * 20, 0, OpenBlock, 320);
        }

        if (OpenBlock > 20) {
            OpenBlock = 20;
        }

        if (OpenBlock == 20) {
            OpenBlock = 0;
            curState = MAIN;
        }
    }
    public static final int OPEN = 0, MAIN = 1, END = 2;
    public static int curState = 0;
    private int OpenBlock = 0;

    public void paint(EmulatorGraphics g) {

        switch (curState) {
            case OPEN:
                drawClose(g);
                break;
            case MAIN: {
                drawBg(g);

                for (int i = 0; i < gd.enemy.length; i++) {
                    if (gd.enemy[i] == null) {
                        continue;
                    }
                    if (!gd.enemy[i].isDead()) {
                        g.drawString(gd.enemy[i].hp + "/" + gd.enemy[i].maxHp, gd.enemy[i].BattX, gd.enemy[i].BattY - 5, EmulatorGraphics.LB);//显示怪物剩余血量
//                        g.drawString(gd.enemy[i].sp + "/" + gd.enemy[i].maxSp, gd.enemy[i].BattX, gd.enemy[i].BattY - 15, Graphics.BOTTOM | Graphics.LEFT);//显示怪物剩余血量
                        //                        if (gd.enemy[i].skillList != null) {
////                            System.out.println("skillSum: " + gd.enemy[i].skillList.length);
//                            g.drawString(gd.gameObjectManager.getSkill(gd.enemy[i].skillList[0]).name, gd.enemy[i].BattX, gd.enemy[i].BattY - 15, Graphics.BOTTOM | Graphics.LEFT);//test
//                        }
//                        g.drawString(gd.enemy[i].name, gd.enemy[i].BattX, gd.enemy[i].BattY - 25, Graphics.BOTTOM | Graphics.LEFT);//test
                        g.drawImage(gd.enemy[i].BattImg, gd.enemy[i].BattX, gd.enemy[i].BattY, 0);

                    }
                }
                g.drawImage(gd.player.headImg, gd.player.heroBattX, gd.player.heroBattY, 0);      //战斗时英雄图片
                if (gd.isChangeHp) {
                    switch (gd.attackType) {
                        case -1://敌人攻击
                            if (gd.player.changeHp > 0) {
                                //画英雄受伤害文字
                                Painter.drawNumber(g, gd.player.changeHp, gd.player.heroBattX + 10, gd.player.heroBattY + gd.upDecreaseHP - 10,
                                        10, Painter.NUMBER_LEFT, Painter.REDNUM);
//                                Painter.drawString(g, "" + gd.player.changeHp, gd.player.heroBattX, gd.player.heroBattY + gd.upDecreaseHP - 10, 0xff0000);
                            }
                            break;
                        case 2:
                        case 7://物品恢复
                            if (gd.player.changeHp > 0) {
                                Painter.drawNumber(g, gd.player.changeHp, gd.player.heroBattX + 10, gd.player.heroBattY + gd.upDecreaseHP - 10,
                                        10, Painter.NUMBER_LEFT, Painter.GREENNUM);
//                                Painter.drawString(g, "" + gd.player.changeHp, gd.player.heroBattX, gd.player.heroBattY + gd.upDecreaseHP - 10, 0xff00ff);
                            }
                            break;
                        default:
                            for (int i = 0; i < gd.enemy.length; i++) {
                                if (gd.enemy[i] != null) {
                                    if (gd.enemy[i].changeHp > 0) {
                                        //画敌人受伤害文字
                                        Painter.drawNumber(g, gd.enemy[i].changeHp, gd.enemy[i].BattX + gd.enemy[i].BattImg.getWidth() / 2, gd.enemy[i].BattY + gd.upDecreaseHP - 10,
                                                10, Painter.NUMBER_LEFT, Painter.REDNUM);
//                                        Painter.drawString(g, "" + gd.enemy[i].changeHp, gd.enemy[i].BattX, gd.enemy[i].BattY + gd.upDecreaseHP - 10, 0xff0000);
                                    }
                                }
                            }
                            break;
                    }
                }
                if (gd.isMiss) {
                    g.setColor(Color.RED);
                    g.drawString("闪避", gd.player.heroBattX, gd.player.heroBattY - 10 + gd.upMiss, 0);
                }
                moveBar(g);
                battleFunction(g);
                if (gd.player.waitTime >= 360) {
                    switch (BattleControl.Select_Menu) {
                        case BattleControl.MAINMENU:
                            drawMain(g);
                            break;
                        case BattleControl.ENEMY_COMMON_SELECT:
                            drawChoose(g);
                            break;
                        case BattleControl.ITEMS_SELECT:
                            drawItem(g);
                            break;
                        case BattleControl.ESCAPE:
                            break;
                        case BattleControl.MAGIC_SELECT:
                            drawMagic(g);
                            break;
                    }
                }


                break;
            }
            case END:
                drawBg(g);
                switch (gd.winSelect) {
                    case 0:
                        Painter.drawDialog(g, 40, 100, 160, 120, Painter.DIALOG_DEEP);
                        Painter.drawString(g, "获得经验 " + gd.allExp, 70, 140, Color.black);
                        Painter.drawString(g, "获得金币 " + gd.allMoney, 70, 180, Color.black);
                        break;
                    case 1:
                        Painter.drawDialog(g, 40, 100, 160, 120, Painter.DIALOG_DEEP);
                        if (enemyTroop.itemList.length > 0) {
                            for (int i = 0; i < enemyTroop.itemList.length; i++) {
                                Painter.drawString(g, "获得物品 " + gd.gameObjectManager.getItem(enemyTroop.itemList[i]).name + " x1", 45, 120 + i * 20, Color.black);
                            }
                        } else {
                            Painter.drawString(g, "没有物品", 24, 50, Color.black);
                        }
                        break;
                }
                break;
        }
        AnimationPlayer.getInstance().play(g);
    }

    public void release() {
        gd.isRuning = false;
        gd.isFail = false;
        gd.isWin = false;
        gd.isMiss = false;
        gd.winSelect = 0;
        gd.allExp = 0;
        gd.allMoney = 0;
        BattleControl.Select_Menu = 0;//当前界面
//        BattleControl.thread = null;
        curState = OPEN;
        OpenBlock = 0;
        gd.select = 0;
        gd.Select_Eny = 0;
//        gd.Select_Equip = 0;
        gd.Select_Good = 0;
        gd.Select_Magic = 0;
        gd.Select_Magic_Eny = 0;
        gd.Select_Magic_Main = 0;
        gd.Select_Item_Eny = 0;
        gd.Select_Item_Main = 0;
//        gd.enemy = new Enemy[4];
        for (int i = 0; i < gd.enemy.length; i++) {
            gd.enemy[i] = null;
        }
        gd.player.waitTime = 0;
        gd.attackType = 0;
        gd.enemyTroopID = 0;
        gd.isChangeHp = false;
        gd.isNotHitEnemy = false;
        gd.upMiss = 0;
        gd.upDecreaseHP = 0;
//        System.out.println("isWin4 " +gd.isWin);
        gd = null;
        font = null;
        setControl(null);
    }
}
