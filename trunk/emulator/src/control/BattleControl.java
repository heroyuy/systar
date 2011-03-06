package control;

import model.Item;
import model.Bag;
import view.BattleView;
import engine.Control;
import engine.GameEngine;
import engine.View;
import engine.script.Event;
import game.RpgGame;
import model.Const;
import model.GameData;
//import model.*;
import engine.animation.AnimationPlayer;
import system.Tools;
import system.Painter;

/**
 *
 * @author Administrator
 */
/*       战斗采用旋转菜单模式：
 *              攻击
 *
 *      物品             技能
 *
 *          装备      逃跑
 *
 */
public class BattleControl implements Control, Runnable {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();
    private GameData gd = GameData.getGameData();
    public static final int MAINMENU = 0, //默认界面
        ENEMY_COMMON_SELECT = 1,//攻击界面
        MAGIC_SELECT = 5,//技能界面
        ITEMS_SELECT = 2,//物品界面
        //        DEF_SELECT = 3,//装备界面
        ESCAPE = 4;//逃跑界面
    public static int Select_Menu = 0;//当前界面
    private boolean isDef = false;
    public static final int Magic_Main = 0, //技能主界面
        Magic_Chose_Eny = 1;//技能选择敌人界面
    public static final int Item_Main = 0,//物品主界面
        Item_Chose_Eny = 1;//物品选择敌人界面
//    public static Timer timer;//用timer实现多任务调度
    private Thread thread;

    public BattleControl() {
        start();
    }

    private void start() {
//        System.out.println("isWin " + gd.isWin);
//        System.out.println("isFail " + gd.isFail);
        gd.isRuning = true;
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
//        System.out.println("gd.isRuning:"+gd.isRuning);
        while (gd.isRuning) {
//            System.out.println("gd.isRuning:"+gd.isRuning);
//            System.out.println("isWin2 "+gd.isWin);
            switch (gd.attackType) {
                case -2:
                    enemyMagicAttack(gd.enemyShould);

                    break;
                case -1:
                    enemyCommondAttack(gd.enemyShould);
                    break;
                case 0:
                    break;
                case 1:
                    heroCommondAttack();
                    break;
                case 2:
                    heroMagicSingleAid();
                    break;
                case 3:
                    heroMagicSingleAttack();
                    break;
                case 4:
                    heroMagicMultuplyAttack();
                    break;
                case 5:
                    heroItemSingleAttack();
                    break;
                case 6:
                    heroItemMultiplyAttack();
                    break;
                case 7:
                    heroUseItem();
                    break;
            }
            if (isChangeAttackType) {
                isChangeAttackType = false;
                continue;
            }
            gd.attackType = 0;
            if (gd.isWin) {
                System.out.println("isWin");

                gd.isRuning = false;
                BattleView.curState = BattleView.END;
//                game.setCurView(Const.ViewId.VIEW_MAP);
//                game.finishEvent();

            } else if (gd.isFail) {
                System.out.println("isFail");
                gd.isRuning = false;
                game.setCurView(Const.ViewId.VIEW_MAP);
                game.finishEvent();
            }
            if ((gd.enemy[0] == null) && (gd.enemy[1] == null) && (gd.enemy[2] == null) && (gd.enemy[3] == null)) {
                gd.isWin = true;
            }
            try {
                Thread.sleep(50);
            } catch (Exception e) {
//            e.printStackTrace();
            }
        }
    }

    private void heroItemSingleAttack() {
        gd.enemy[gd.Select_Item_Eny].changeHp = gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.Select_Good]).hp;
//        AnimationPlayer.getInstance().playAnimation(gd.gameObjectManager.getAnimation(gd.gameObjectManager.getSkill(gd.player.skillList[gd.Select_Magic]).aniIndex), gd.enemy[gd.Select_Magic_Eny].BattX + 5, gd.enemy[gd.Select_Magic_Eny].BattY - 10);
        gd.player.sp -= gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.Select_Good]).sp;
        gd.isChangeHp = true;
        gd.upDecreaseHP = 0;
        for (int i = 0; i < 4; i++) {
            gd.upDecreaseHP -= 3;
            sleep();
        }
        gd.isChangeHp = false;
        gd.enemy[gd.Select_Item_Eny].hp -= gd.enemy[gd.Select_Item_Eny].changeHp;
        gd.enemy[gd.Select_Item_Eny].changeHp = 0;
        if (gd.enemy[gd.Select_Item_Eny].hp <= 0) {
            gd.enemy[gd.Select_Item_Eny].setIsDead(true);
            gd.enemy[gd.Select_Item_Eny] = null;
        }
        Select_Menu = MAINMENU;
        delItem();
        gd.Select_Good = 0;
        gd.Select_Item_Main = Item_Main;
        gd.player.waitTime = 0;
    }

    private void heroItemMultiplyAttack() {
        for (int i = 0; i < gd.enemy.length; i++) {
            if (gd.enemy[i] == null) {
                continue;
            }
            gd.enemy[i].changeHp = gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.Select_Good]).hp;
        }

        gd.player.sp -= gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.Select_Good]).sp;
        gd.isChangeHp = true;
        gd.upDecreaseHP = 0;
        for (int i = 0; i < 4; i++) {
            gd.upDecreaseHP -= 3;
            sleep();
        }
        gd.isChangeHp = false;
        for (int i = 0; i < gd.enemy.length; i++) {
            if (gd.enemy[i] == null) {
                continue;
            }
            gd.enemy[i].hp -= gd.enemy[i].changeHp;
            gd.enemy[i].changeHp = 0;
            if (gd.enemy[i].hp <= 0) {
                gd.enemy[i].setIsDead(true);
                gd.enemy[i] = null;
            }

        }
        Select_Menu = MAINMENU;
        delItem();
        gd.Select_Good = 0;
        gd.Select_Item_Main = Item_Main;
        gd.player.waitTime = 0;
    }

    private void heroMagicSingleAttack() {

        gd.enemy[gd.Select_Magic_Eny].changeHp = gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).hp;

//        try {
        AnimationPlayer.getInstance().playAnimation(gd.gameObjectManager.getAnimation(gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).aniIndex),
            gd.enemy[gd.Select_Magic_Eny].BattX + gd.enemy[gd.Select_Magic_Eny].BattImg.getWidth() / 2, gd.enemy[gd.Select_Magic_Eny].BattY);
//        } catch (Exception e) {
//            System.out.println("攻击动画播放异常");
////                e.printStackTrace();
//        }
        gd.player.sp -= gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).sp;
        gd.isChangeHp = true;
        gd.upDecreaseHP = 0;
        for (int i = 0; i < 4; i++) {
            gd.upDecreaseHP -= 3;
            sleep();
        }
        gd.isChangeHp = false;
        gd.enemy[gd.Select_Magic_Eny].hp -= gd.enemy[gd.Select_Magic_Eny].changeHp;
        gd.enemy[gd.Select_Magic_Eny].changeHp = 0;
        if (gd.enemy[gd.Select_Magic_Eny].hp <= 0) {
            gd.enemy[gd.Select_Magic_Eny].setIsDead(true);
            gd.enemy[gd.Select_Magic_Eny] = null;
        }
        Select_Menu = MAINMENU;
        gd.Select_Magic = 0;
        gd.Select_Magic_Main = Magic_Main;
        gd.player.waitTime = 0;

    }

    private void heroMagicMultuplyAttack() {

        for (int i = 0; i < gd.enemy.length; i++) {
            if (gd.enemy[i] == null) {
                continue;
            }
//            try {
            AnimationPlayer.getInstance().playAnimation(gd.gameObjectManager.getAnimation(gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).aniIndex),
                gd.enemy[i].BattX + gd.enemy[i].BattImg.getWidth() / 2, gd.enemy[i].BattY);
//            } catch (Exception e) {
//                System.out.println("攻击动画播放异常");
////                e.printStackTrace();
//            }

            gd.enemy[i].changeHp = gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).hp;
        }

        gd.player.sp -= gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).sp;
        gd.isChangeHp = true;
        gd.upDecreaseHP = 0;
        for (int i = 0; i < 4; i++) {
            gd.upDecreaseHP -= 3;
            sleep();
        }
        gd.isChangeHp = false;
        for (int i = 0; i < gd.enemy.length; i++) {
            if (gd.enemy[i] == null) {
                continue;
            }
            gd.enemy[i].hp -= gd.enemy[i].changeHp;
            gd.enemy[i].changeHp = 0;
            if (gd.enemy[i].hp <= 0) {
                gd.enemy[i].setIsDead(true);
                gd.enemy[i] = null;
            }

        }
        Select_Menu = MAINMENU;
        gd.Select_Magic = 0;
        gd.Select_Magic_Main = Magic_Main;
        gd.player.waitTime = 0;

    }

    private void heroMagicSingleAid() {
        gd.player.changeHp = gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).hp;
//        try {
        AnimationPlayer.getInstance().playAnimation(gd.gameObjectManager.getAnimation(gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).aniIndex),
            gd.player.heroBattX + 10, gd.player.heroBattY);
//        } catch (Exception e) {
//            System.out.println("加血动画播放异常");
////            e.printStackTrace();
//        }

        gd.player.sp -= gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).sp;
        gd.isChangeHp = true;
        gd.upDecreaseHP = 0;
        for (int i = 0; i < 4; i++) {
            gd.upDecreaseHP -= 3;
            sleep();
        }
        gd.isChangeHp = false;
        gd.player.hp += gd.player.changeHp;
        gd.player.changeHp = 0;
        if (gd.player.hp > gd.player.maxHp) {
            gd.player.hp = gd.player.maxHp;
        }
        Select_Menu = MAINMENU;
        gd.Select_Magic = 0;
        gd.Select_Magic_Main = Magic_Main;
        gd.player.waitTime = 0;
    }
    //待修改，此为测试

    private void heroUseItem() {


        if (gd.player.bag.getList(Bag.ITEM).length > 0) {
            Select_Menu = MAINMENU;
            Item item = gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.Select_Good]);
            if (item.hp != 0) {
                gd.player.changeHp = item.hp;
            } else {
                gd.player.changeHp = item.sp;
            }


            gd.isChangeHp = true;
            gd.upDecreaseHP = 0;
            for (int i = 0; i < 4; i++) {
                gd.upDecreaseHP -= 3;
                sleep();
            }
            gd.isChangeHp = false;
            useItem();
            gd.Select_Good = 0;
            gd.player.waitTime = 0;
        }
    }

    public void keyPressed(View view, int key) {

        if (view instanceof BattleView) {
            switch (BattleView.curState) {
                case BattleView.OPEN:
                    break;
                case BattleView.MAIN:
                    if (gd.player.waitTime < 360) {//当不在英雄行动序列时，不响应按键
                        return;
                    }
                    switch (key) {
                        case Const.Key.KEY_UP:
                        case Const.Key.KEY_2:
                            up();
                            break;
                        case Const.Key.KEY_DOWN:
                        case Const.Key.KEY_8:
                            down();
                            break;
                        case Const.Key.KEY_LEFT:
                        case Const.Key.KEY_4:
                            left();
                            break;
                        case Const.Key.KEY_RIGHT:
                        case Const.Key.KEY_6:
                            right();
                            break;
                        case Const.Key.KEY_5:
                        case Const.Key.KEY_FIRE:
                            fire();
                            break;
                        case Const.Key.KEY_RS:
                            back();
                            break;
                    }
                    break;

                case BattleView.END:
                    switch (key) {
                        case Const.Key.KEY_FIRE:
                            switch (gd.winSelect) {
                                case 0:
                                    gd.winSelect = 1;
                                    gd.player.addExp(gd.allExp);
                                    gd.player.money += gd.allMoney;
                                    break;
                                case 1:
                                    gd.winSelect = 0;
                                    for (int i = 0; i < gd.gameObjectManager.getEnemyTroop(gd.enemyTroopID).itemList.length; i++) {
                                        gd.player.bag.add(Bag.ITEM, gd.gameObjectManager.getEnemyTroop(gd.enemyTroopID).itemList[i], 1);
                                    }
                                    game.setCurView(Const.ViewId.VIEW_MAP);
                                    game.finishEvent();
                                    break;
                            }

                            break;
                    }
                    System.out.println("gd.winSelect " + gd.winSelect);
                    break;

            }

        }
//        game.getCurView().repaint();
        ge.clearKey();
    }

    private void up() {
//        System.out.println("up");
        switch (Select_Menu) {
            case MAINMENU:
                gd.select--;
                if (gd.select < 0) {
                    gd.select = 4;
                }
                break;
            case ENEMY_COMMON_SELECT:
                gd.Select_Eny--;
                if (gd.Select_Eny < 0) {
                    gd.Select_Eny = 3;
                }
                while (gd.enemy[gd.Select_Eny] == null) {
                    gd.Select_Eny--;
                    if (gd.Select_Eny < 0) {
                        gd.Select_Eny = 3;
                    }
                }

                break;
            case MAGIC_SELECT:
                switch (gd.Select_Magic_Main) {
                    case Magic_Main:
                        if (gd.player.bag.getList(Bag.SKILL).length > 0) {
//                            gd.Select_Magic--;
//                            if (gd.Select_Magic < 0) {
//                                gd.Select_Magic = 0;
//                            }
                            gd.Select_Magic--;
                            if (gd.Select_Magic < gd.Top_Magic) {
                                if (gd.Top_Magic == 0) {
                                    gd.Select_Magic++;
                                } else {
                                    gd.Top_Magic--;
                                }

                            }
                        }

                        Painter.resetTipStringPos();
                        break;
                    case Magic_Chose_Eny:
                        gd.Select_Magic_Eny--;
                        if (gd.Select_Magic_Eny < 0) {
                            gd.Select_Magic_Eny = 3;
                        }
                        while (gd.enemy[gd.Select_Magic_Eny] == null) {
                            gd.Select_Magic_Eny--;
                            if (gd.Select_Magic_Eny < 0) {
                                gd.Select_Magic_Eny = 3;
                            }
                        }
                        break;
                }
                break;
            case ITEMS_SELECT:

                switch (gd.Select_Item_Main) {
                    case Item_Main:
                        if (gd.player.bag.getList(Bag.ITEM).length > 0) {
                            gd.Select_Good--;
                            if (gd.Select_Good < gd.Top_Good) {
                                if (gd.Top_Good == 0) {
                                    gd.Select_Good++;
                                } else {
                                    gd.Top_Good--;
                                }

                            }
                        }

                        Painter.resetTipStringPos();
                        break;
                    case Item_Chose_Eny:
                        gd.Select_Item_Eny--;
                        if (gd.Select_Item_Eny < 0) {
                            gd.Select_Item_Eny = 3;
                        }
                        while (gd.enemy[gd.Select_Item_Eny] == null) {
                            gd.Select_Item_Eny--;
                            if (gd.Select_Item_Eny < 0) {
                                gd.Select_Item_Eny = 3;
                            }
                        }
                        break;
                }
                break;
        }
    }
    private boolean isChangeAttackType = false;

    public void enemyMagicAttack(int id) {//敌人魔法攻击
        int len = gd.enemy[id].skillList.length;
        int temp = Tools.GetRandom(0, len - 1);
        System.out.println("temp: " + temp);
        if (gd.enemy[id].sp < gd.gameObjectManager.getSkill(gd.enemy[id].skillList[temp]).sp) {//魔法不足
            gd.attackType = -1;
            isChangeAttackType = true;
            System.out.println("changeAttackType");
            return;
        }
//        System.out.println("skillname " + gd.gameObjectManager.getSkill(gd.enemy[id].skillList[temp]).name);
//        System.out.println("aniIndex " + gd.gameObjectMana  ger.getSkill(gd.player.skillList[temp]).aniIndex);
        AnimationPlayer.getInstance().playAnimation(
            gd.gameObjectManager.getAnimation(gd.gameObjectManager.getSkill(gd.enemy[id].skillList[temp]).aniIndex),
            gd.player.heroBattX + 10, gd.player.heroBattY);
//        System.out.println("gd.enemy[id].name " + gd.enemy[id].name);
//
//        System.out.println("gd.enemy[id].skillList[temp] " + gd.enemy[id].skillList[temp]);

        gd.player.changeHp = gd.gameObjectManager.getSkill(gd.enemy[id].skillList[temp]).hp;
        gd.enemy[id].sp -= gd.gameObjectManager.getSkill(gd.enemy[id].skillList[temp]).sp;
//        System.out.println("have used skill");
        //随机放魔法列表中的一个技能
        if (isDef) {
            //防御状态下伤害减半
            gd.player.changeHp = gd.player.changeHp / 2;//伤害减半
            isDef = false;//状态持续一回合
        }
        gd.isChangeHp = true;
        gd.upDecreaseHP = 0;
        for (int i = 0; i < 4; i++) {
            gd.upDecreaseHP -= 3;
            sleep();
        }
//        sleep();
        gd.isChangeHp = false;
        gd.player.hp -= gd.player.changeHp;
        if (gd.player.hp <= 0) {
            gd.isFail = true;
        }
        gd.enemy[id].waitTime = 0;//进度回0
    }

    public void enemyCommondAttack(int id) {//敌人普通攻击
        for (int j = 0; j < 4; j++) {
            enemyMove(id);
        }
        if (gd.player.isMiss()) {
            gd.isMiss = true;
            gd.upMiss = 0;
            for (int i = 0; i < 4; i++) {
                enemyBack(id);
                gd.upMiss -= 3;
            }
            gd.isMiss = false;
            System.out.println("miss");
        } else {
            gd.player.changeHp = gd.enemy[id].atk - gd.player.def;
            if (gd.player.changeHp <= 0) {
                gd.player.changeHp = 1;
            }
            if (isDef) {
                //防御状态下伤害减半
                gd.player.changeHp = gd.player.changeHp / 2;//伤害减半
                isDef = false;//状态持续一回合
            }
            gd.isChangeHp = true;
            gd.upDecreaseHP = 0;
            for (int i = 0; i < 4; i++) {
                enemyBack(id);
                gd.upDecreaseHP -= 3;
            }
        }

        sleep();
        gd.isChangeHp = false;
        gd.player.hp -= gd.player.changeHp;
        if (gd.player.hp <= 0) {
            gd.isFail = true;
        }
        gd.enemy[id].waitTime = 0;//进度回0
    }

    private void down() {
//        System.out.println("down");
        switch (Select_Menu) {
            case MAINMENU:
                gd.select++;
                if (gd.select > 4) {
                    gd.select = 0;
                }
                break;
            case ENEMY_COMMON_SELECT:
                gd.Select_Eny++;
                if (gd.Select_Eny > 3) {
                    gd.Select_Eny = 0;
                }
                while (gd.enemy[gd.Select_Eny] == null) {
                    gd.Select_Eny++;
                    if (gd.Select_Eny > 3) {
                        gd.Select_Eny = 0;
                    }
                }
                break;
            case MAGIC_SELECT:
                switch (gd.Select_Magic_Main) {
                    case Magic_Main:
                        if (gd.player.bag.getList(Bag.SKILL).length > 0) {
                            if (gd.Select_Magic < gd.player.bag.getList(Bag.SKILL).length - 1) {
                                gd.Select_Magic++;
                                if (gd.Select_Magic > gd.Top_Magic + 7 - 1) {
                                    if (gd.Top_Magic == gd.player.bag.getList(Bag.SKILL).length - 7) {
                                        gd.Select_Magic--;
                                    } else {
                                        gd.Top_Magic++;
                                    }
                                }
                            }
                        }
                        Painter.resetTipStringPos();
                        break;
                    case Magic_Chose_Eny:
                        gd.Select_Magic_Eny++;
                        if (gd.Select_Magic_Eny > 3) {
                            gd.Select_Magic_Eny = 0;
                        }
                        while (gd.enemy[gd.Select_Magic_Eny] == null) {
                            gd.Select_Magic_Eny++;
                            if (gd.Select_Magic_Eny > 3) {
                                gd.Select_Magic_Eny = 0;
                            }
                        }
                }
                break;
            case ITEMS_SELECT:

                switch (gd.Select_Item_Main) {
                    case Item_Main:
                        if (gd.player.bag.getList(Bag.ITEM).length > 0) {
                            if (gd.Select_Good < gd.player.bag.getList(Bag.ITEM).length - 1) {
                                gd.Select_Good++;
                                if (gd.Select_Good > gd.Top_Good + 7 - 1) {
                                    if (gd.Top_Good == gd.player.bag.getList(Bag.ITEM).length - 7) {
                                        gd.Select_Good--;
                                    } else {
                                        gd.Top_Good++;
                                    }
                                }
                            }
                        }

                        Painter.resetTipStringPos();
                        break;
                    case Item_Chose_Eny:
                        gd.Select_Item_Eny++;
                        if (gd.Select_Item_Eny > 3) {
                            gd.Select_Item_Eny = 0;
                        }
                        while (gd.enemy[gd.Select_Item_Eny] == null) {
                            gd.Select_Item_Eny++;
                            if (gd.Select_Item_Eny > 3) {
                                gd.Select_Item_Eny = 0;
                            }
                        }
                }
                break;
        }
    }

    private void left() {
//        System.out.println("left");
        switch (Select_Menu) {
            case MAINMENU:
                gd.select++;
                if (gd.select > 4) {
                    gd.select = 0;
                }
                break;
            case ENEMY_COMMON_SELECT:
                gd.Select_Eny++;
                if (gd.Select_Eny > 3) {
                    gd.Select_Eny = 0;
                }
                while (gd.enemy[gd.Select_Eny] == null) {
                    gd.Select_Eny++;
                    if (gd.Select_Eny > 3) {
                        gd.Select_Eny = 0;
                    }
                }
                break;
            case MAGIC_SELECT:
                switch (gd.Select_Magic_Main) {
                    case Magic_Main:
                        if (gd.player.bag.getList(Bag.SKILL).length > 0) {
//                            gd.Select_Good++;
//                            if (gd.Select_Good > gd.player.bag.getList(Bag.ITEM).length - 1) {
//                                gd.Select_Good = gd.player.bag.getList(Bag.ITEM).length - 1;
//                            }
                            if (gd.Select_Magic < gd.player.bag.getList(Bag.SKILL).length - 1) {
                                gd.Select_Magic++;
                                if (gd.Select_Magic > gd.Top_Magic + 7 - 1) {
                                    if (gd.Top_Magic == gd.player.bag.getList(Bag.SKILL).length - 7) {
                                        gd.Select_Magic--;
                                    } else {
                                        gd.Top_Magic++;
                                    }
                                }
                            }
                        }
//                        if (gd.player.skillList.length > 0) {
//                            gd.Select_Magic++;
//                            if (gd.Select_Magic > gd.player.skillList.length - 1) {
//                                gd.Select_Magic = gd.player.skillList.length - 1;
//                            }
//                        }
                        Painter.resetTipStringPos();
                        break;
                    case Magic_Chose_Eny:
                        gd.Select_Magic_Eny++;
                        if (gd.Select_Magic_Eny > 3) {
                            gd.Select_Magic_Eny = 0;
                        }
                        while (gd.enemy[gd.Select_Magic_Eny] == null) {
                            gd.Select_Magic_Eny++;
                            if (gd.Select_Magic_Eny > 3) {
                                gd.Select_Magic_Eny = 0;
                            }
                        }
                }
                break;
            case ITEMS_SELECT:

                switch (gd.Select_Item_Main) {
                    case Item_Main:
                        if (gd.player.bag.getList(Bag.ITEM).length > 0) {
//                            gd.Select_Good++;
//                            if (gd.Select_Good > gd.player.bag.getList(Bag.ITEM).length - 1) {
//                                gd.Select_Good = gd.player.bag.getList(Bag.ITEM).length - 1;
//                            }
                            if (gd.Select_Good < gd.player.bag.getList(Bag.ITEM).length - 1) {
                                gd.Select_Good++;
                                if (gd.Select_Good > gd.Top_Good + 7 - 1) {
                                    if (gd.Top_Good == gd.player.bag.getList(Bag.ITEM).length - 7) {
                                        gd.Select_Good--;
                                    } else {
                                        gd.Top_Good++;
                                    }
                                }
                            }
                        }

                        Painter.resetTipStringPos();
                        break;
                    case Item_Chose_Eny:
                        gd.Select_Item_Eny++;
                        if (gd.Select_Item_Eny > 3) {
                            gd.Select_Item_Eny = 0;
                        }
                        while (gd.enemy[gd.Select_Item_Eny] == null) {
                            gd.Select_Item_Eny++;
                            if (gd.Select_Item_Eny > 3) {
                                gd.Select_Item_Eny = 0;
                            }
                        }
                }
                break;

        }
    }

    private void right() {
//        System.out.println("right");
        switch (Select_Menu) {
            case MAINMENU:
                gd.select--;
                if (gd.select < 0) {
                    gd.select = 4;
                }

                break;
            case ENEMY_COMMON_SELECT:
                gd.Select_Eny--;
                if (gd.Select_Eny < 0) {
                    gd.Select_Eny = 3;
                }
                while (gd.enemy[gd.Select_Eny] == null) {
                    gd.Select_Eny--;
                    if (gd.Select_Eny < 0) {
                        gd.Select_Eny = 3;
                    }
                }
                break;
            case MAGIC_SELECT:
                switch (gd.Select_Magic_Main) {
                    case Magic_Main:
                        if (gd.player.bag.getList(Bag.SKILL).length > 0) {
//                            gd.Select_Magic--;
//                            if (gd.Select_Magic < 0) {
//                                gd.Select_Magic = 0;
//                            }
                            gd.Select_Magic--;
                            if (gd.Select_Magic < gd.Top_Magic) {
                                if (gd.Top_Magic == 0) {
                                    gd.Select_Magic++;
                                } else {
                                    gd.Top_Magic--;
                                }

                            }
                        }
                        Painter.resetTipStringPos();
                        break;
                    case Magic_Chose_Eny:
                        gd.Select_Magic_Eny--;
                        if (gd.Select_Magic_Eny < 0) {
                            gd.Select_Magic_Eny = 3;
                        }
                        while (gd.enemy[gd.Select_Magic_Eny] == null) {
                            gd.Select_Magic_Eny--;
                            if (gd.Select_Magic_Eny < 0) {
                                gd.Select_Magic_Eny = 3;
                            }
                        }
                        break;
                }
                break;
            case ITEMS_SELECT:

                switch (gd.Select_Item_Main) {
                    case Item_Main:
                        if (gd.player.bag.getList(Bag.ITEM).length > 0) {
//                            gd.Select_Good--;
//                            if (gd.Select_Good < 0) {
//                                gd.Select_Good = 0;
//                            }
                            gd.Select_Good--;
                            if (gd.Select_Good < gd.Top_Good) {
                                if (gd.Top_Good == 0) {
                                    gd.Select_Good++;
                                } else {
                                    gd.Top_Good--;
                                }

                            }
                        }
                        Painter.resetTipStringPos();
                        break;
                    case Item_Chose_Eny:
                        gd.Select_Item_Eny--;
                        if (gd.Select_Item_Eny < 0) {
                            gd.Select_Item_Eny = 3;
                        }
                        while (gd.enemy[gd.Select_Item_Eny] == null) {
                            gd.Select_Item_Eny--;
                            if (gd.Select_Item_Eny < 0) {
                                gd.Select_Item_Eny = 3;
                            }
                        }
                        break;
                }
                break;
        }
    }

    private void fire() {
//        System.out.println("fire");
        switch (Select_Menu) {
            case MAINMENU:
                switch (gd.select) {
                    case 0:
                        Select_Menu = ENEMY_COMMON_SELECT; //进入选择敌人界面
                        break;
                    case 1:
                        Select_Menu = ITEMS_SELECT; //进入选择物品界面
                        break;
                    case 2:
                        isDef = true;
                        gd.player.waitTime = 0;
                        Select_Menu = MAINMENU;
                        break;
                    case 3:
                        Select_Menu = ESCAPE; //逃跑
                        game.setCurView(Const.ViewId.VIEW_MAP);
                        game.finishEvent();
                        break;
                    case 4:
                        Select_Menu = MAGIC_SELECT; //进入选择技能界面
                        break;
                }
                break;
            case ENEMY_COMMON_SELECT:
                gd.attackType = 1; //1为物理攻击

                break;
            case MAGIC_SELECT:
                switch (gd.Select_Magic_Main) {
                    case Magic_Main:
                        if (gd.player.bag.getList(Bag.SKILL).length > 0) {
                            //等级和魔法值要满足技能释放条件才能用魔法
                            if (gd.player.sp >= gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).sp && gd.player.lev >= gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).lev) {
                                switch (gd.gameObjectManager.getSkill(gd.player.bag.getList(Bag.SKILL)[gd.Select_Magic]).kind) {
                                    case 0://单体攻击性技能
                                        gd.Select_Magic_Main = Magic_Chose_Eny;
                                        break;
                                    case 1://全体攻击性技能
                                        gd.attackType = 4;
                                        break;
                                    case 2://辅助性技能
                                        gd.attackType = 2;
                                        break;
                                }
                            }
                        }
                        break;
                    case Magic_Chose_Eny:
                        gd.attackType = 3;
                        break;
                }
                break;
            case ITEMS_SELECT:
                switch (gd.Select_Item_Main) {
                    case Item_Main:
                        switch (gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.Select_Good]).kind) {
                            case 0://单体攻击性
                                gd.Select_Item_Main = Item_Chose_Eny;
                                break;
                            case 1://全体攻击性
                                gd.attackType = 6;
                                break;
                            case 2://辅助性
                                gd.attackType = 7;
                                break;
                        }
//                        gd.attackType = 7; //2物品回复

                        break;
                    case Item_Chose_Eny:
                        gd.attackType = 5;
                        break;
                }
                break;
        }
        System.out.println(
            "gd.attackType: " + gd.attackType);
    }

    public void useItem() {
        Item item = gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[gd.Select_Good]);
        gd.player.stre += item.stre;//增加/减少力量
        gd.player.agil += item.agil;//增加/减少敏捷
        gd.player.inte += item.inte;//增加/减少智力
        gd.player.hp += item.hp;//增加/减少血量
        gd.player.sp += item.sp;//增加/减少魔法值
        gd.player.maxHp += item.maxHp;//增加/减少最大血量
        gd.player.maxSp += item.maxSp;//增加/减少最大魔法值
        gd.player.lev += item.lev;//增加等级
        gd.player.atk += item.atk;//增加/减少攻击
        gd.player.def += item.def;//增加/减少防御
        gd.player.flee += item.flee;//增加/减少闪避
        gd.player.exp += item.exp;//增加经验值
        if (gd.player.hp > gd.player.maxHp) {
            gd.player.hp = gd.player.maxHp;
        }
        if (gd.player.sp > gd.player.maxSp) {
            gd.player.sp = gd.player.maxSp;
        }
        delItem();
    }

    private void delItem() {
        gd.player.bag.del(Bag.ITEM, gd.Select_Good, 1);
//        Vector itemVector = new Vector();
//        for (int i = 0; i < gd.player.bag.getList(Bag.ITEM).length; i++) {
//            itemVector.addElement(gd.gameObjectManager.getItem(gd.player.bag.getList(Bag.ITEM)[i]));
//        }
//        itemVector.removeElementAt(gd.Select_Good);
//        gd.player.bag.getList(Bag.ITEM) = new int[itemVector.size()];
//        for (int i = 0; i < gd.player.bag.getList(Bag.ITEM).length; i++) {
//            gd.player.bag.getList(Bag.ITEM)[i] = ((Item) itemVector.elementAt(i)).index;
//        }
    }

    private void back() {
//        System.out.println("back");
        switch (Select_Menu) {
            case ENEMY_COMMON_SELECT:
                Select_Menu = MAINMENU;
                break;
            case MAGIC_SELECT:
                switch (gd.Select_Magic_Main) {
                    case Magic_Main:
                        Select_Menu = MAINMENU;
                        break;
                    case Magic_Chose_Eny:
                        gd.Select_Magic_Main = Magic_Main;
                        break;
                }
                break;
            case ITEMS_SELECT:
                switch (gd.Select_Item_Main) {
                    case Item_Main:
                        Select_Menu = MAINMENU;
                        break;
                    case Item_Chose_Eny:
                        gd.Select_Item_Main = Item_Main;
                        break;
                }
                Select_Menu = MAINMENU;
                break;
        }
    }

    private void enemyMove(int id) {
        gd.enemy[id].BattX += (gd.player.heroBattX - 120 + 35 * id) / 4;
        gd.enemy[id].BattY += (gd.player.heroBattY - 50 - 12 * id) / 4;
        sleep();
    }

    private void enemyBack(int id) {
        gd.enemy[id].BattX -= (gd.player.heroBattX - 120 + 35 * id) / 4;
        gd.enemy[id].BattY -= (gd.player.heroBattY - 50 - 12 * id) / 4;
        sleep();
    }

    private void heroMove() {
        gd.player.heroBattX += (gd.enemy[gd.Select_Eny].BattX - 150) / 4;
        gd.player.heroBattY += (gd.enemy[gd.Select_Eny].BattY - 180) / 4;
        sleep();
    }

    private void heroBack() {
        gd.player.heroBattX -= (gd.enemy[gd.Select_Eny].BattX - 150) / 4;
        gd.player.heroBattY -= (gd.enemy[gd.Select_Eny].BattY - 180) / 4;
        sleep();
    }

    private void sleep() {
        game.getCurView().repaint();
        try {
            Thread.sleep(1000 / 12);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }

    private void heroCommondAttack() {
        for (int i = 0; i
            < 4; i++) {
            heroMove();
        }
        gd.enemy[gd.Select_Eny].changeHp = gd.player.atk - gd.enemy[gd.Select_Eny].def;
        gd.isChangeHp = true;
        gd.upDecreaseHP = 0;
        for (int i = 0; i
            < 4; i++) {
            heroBack();
            gd.upDecreaseHP -= 3;

        }
        sleep();
        gd.isChangeHp = false;
        gd.enemy[gd.Select_Eny].hp -= gd.enemy[gd.Select_Eny].changeHp;
        gd.enemy[gd.Select_Eny].changeHp = 0; //对每个活着的怪物初始化减血量
        if (gd.enemy[gd.Select_Eny].hp <= 0) {
            gd.enemy[gd.Select_Eny].setIsDead(true);
            gd.enemy[gd.Select_Eny] = null;
            //假如一个怪物死亡将其置为null
        }
        Select_Menu = MAINMENU;
        gd.player.waitTime = 0;
    }

    public void dealEvent(View view, Event event) {
    }
}
