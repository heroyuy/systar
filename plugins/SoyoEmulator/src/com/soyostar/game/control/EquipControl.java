package com.soyostar.game.control;

import engine.Control;
import engine.GameEngine;
import engine.View;
import engine.script.Event;
import engine.script.ScriptEngine;
import game.RpgGame;
import model.Const;
import model.GameData;
import view.EquipView;

/**
 *
 * @author Administrator
 */
public class EquipControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    public void keyPressed(View view, int key) {
        if (view instanceof EquipView) {
            switch (gd.equip_pageIndex) {
                case EquipView.PAGE_MAIN:
                    keyPressed_Main(key);
                    break;
                case EquipView.PAGE_CONFIRM:
                    keyPressed_Confirm(key);
                    break;
                case EquipView.PAGE_TIP:
                    keyPressed_Tip(key);
                    break;
            }

        }
        ge.clearKey();
    }

    public void dealEvent(View view, Event event) {
    }

    private void keyPressed_Main(int key) {
        switch (key) {
            case Const.Key.KEY_UP:
                gd.equip_selectIndex = (gd.equip_selectIndex + 6 - 1) % 6;
                confirmCurEquip();
                break;
            case Const.Key.KEY_DOWN:
                gd.equip_selectIndex = (gd.equip_selectIndex + 1) % 6;
                confirmCurEquip();
                break;
            case Const.Key.KEY_LS:
                if (gd.equip_curEquip != -1) {
                    gd.equip_pageIndex = EquipView.PAGE_CONFIRM;
                }
                break;
            case Const.Key.KEY_RS:
                game.setCurView(Const.ViewId.VIEW_MAP);
                break;

        }
    }

    private void keyPressed_Confirm(int key) {
        switch (key) {
            case Const.Key.KEY_LS:
                //取下装备
                takeOffEquip();
                gd.equip_pageIndex = EquipView.PAGE_TIP;
                break;
            case Const.Key.KEY_RS:
                gd.equip_pageIndex = EquipView.PAGE_MAIN;
                break;
        }
    }

    private void keyPressed_Tip(int key) {
        switch (key) {
            case Const.Key.KEY_LS:
            case Const.Key.KEY_RS:
                gd.equip_pageIndex = EquipView.PAGE_MAIN;
                break;
        }
    }

    private void confirmCurEquip() {
        switch (gd.equip_selectIndex) {
            case 0:
                gd.equip_curEquip = gd.player.equipHelm;
                break;
            case 1:
                gd.equip_curEquip = gd.player.equipArmour;
                break;
            case 2:
                gd.equip_curEquip = gd.player.equipWeapon;
                break;
            case 3:
                gd.equip_curEquip = gd.player.equipShield;
                break;
            case 4:
                gd.equip_curEquip = gd.player.equipBoots;
                break;
            case 5:
                gd.equip_curEquip = gd.player.equipJewelry;
                break;
        }
    }

    private void takeOffEquip() {
        gd.player.takeOffEquip(gd.equip_curEquip);
        confirmCurEquip();
    }
}
