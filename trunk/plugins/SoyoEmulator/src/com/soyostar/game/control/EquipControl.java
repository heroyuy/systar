/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soyostar.game.control;

import com.soyostar.emulator.engine.Control;
import com.soyostar.emulator.engine.GameEngine;
import com.soyostar.emulator.engine.KeyManager;
import com.soyostar.emulator.engine.View;
import com.soyostar.emulator.engine.script.Event;
import com.soyostar.emulator.engine.script.ScriptEngine;
import com.soyostar.game.RpgGame;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import com.soyostar.game.view.EquipView;

/**@2011.4.6 by VV
 *
 * @author Administrator
 */
public class EquipControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    ;

    private void dealKeyEvent_Main() {

        if (ge.getKeyManager().isPressKey(KeyManager.KEY_UP)) {
            gd.equip_selectIndex = (gd.equip_selectIndex + 6 - 1) % 6;
            confirmCurEquip();
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_DOWN)) {
            gd.equip_selectIndex = (gd.equip_selectIndex + 1) % 6;
            confirmCurEquip();
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_LS)) {


            if (gd.equip_curEquip != -1) {
                gd.equip_pageIndex = EquipView.PAGE_CONFIRM;
            }
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_RS)) {
            game.setCurView(Const.ViewId.VIEW_MAP);
        }
    }

    private void dealKeyEvent_Confirm() {

        if (ge.getKeyManager().isPressKey(KeyManager.KEY_LS)) {
            //取下装备
            takeOffEquip();
            gd.equip_pageIndex = EquipView.PAGE_TIP;
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_RS)) {

            gd.equip_pageIndex = EquipView.PAGE_MAIN;

        }
    }

    private void dealKeyEvent_Tip() {

        if (ge.getKeyManager().isPressKey(KeyManager.KEY_LS)) {
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_RS)) {
            gd.equip_pageIndex = EquipView.PAGE_MAIN;

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

    public void dealKeyEvent() {
        switch (gd.equip_pageIndex) {
            case EquipView.PAGE_MAIN:
                dealKeyEvent_Main();
                break;
            case EquipView.PAGE_CONFIRM:
                dealKeyEvent_Confirm();
                break;
            case EquipView.PAGE_TIP:
                dealKeyEvent_Tip();
                break;
        }
    }

    public void dealMotion() {
    }

    public void dealGameEvent(Event event) {
    }

    public void updateModel() {
    }
}
