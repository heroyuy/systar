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
import com.soyostar.game.model.Bag;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.GameData;
import com.soyostar.game.view.BagView;

/**@2011.4.6 by VV
 *
 * 
 */
public class BagControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    private void dealKeyEvent_Main() {
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_UP)) {
            if (gd.bag_curIndex > 0) {
                gd.bag_curIndex--;
                if (gd.bag_curIndex < gd.bag_topIndex) {
                    gd.bag_topIndex--;
                }
            }
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_DOWN)) {
            if (gd.bag_curIndex < gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP).length - 1) {
                gd.bag_curIndex++;
                if (gd.bag_curIndex > gd.bag_topIndex + gd.bag_showNum - 1) {
                    gd.bag_topIndex++;
                }
            }
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_LEFT)) {
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_RIGHT)) {
            gd.bag_tabIndex = (gd.bag_tabIndex + 1) % 2;
            gd.bag_curIndex = gd.bag_topIndex = 0;
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_LS)) {
            if (gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP).length > 0 && gd.player.bag.get(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP)[gd.bag_curIndex]) != null) {
                gd.bag_pageIndex = BagView.PAGE_CONFIRM;
            }
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_RS)) {
            game.setCurView(Const.ViewId.VIEW_MAP);
        }
    }

    private void dealKeyEvent_Confirm() {
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_LS)) {
            if (gd.bag_tabIndex == 0) {
                //使用物品
                //使用物品的代码写在这里
                gd.player.useItem(gd.player.bag.getList(Bag.ITEM)[gd.bag_curIndex]);
            } else {
                //穿装备
                gd.player.takeOnEquip(gd.player.bag.getList(Bag.EQUIP)[gd.bag_curIndex]);
            }
            gd.buildItems();
            gd.bag_pageIndex = BagView.PAGE_TIP;
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_RS)) {
            gd.bag_pageIndex = BagView.PAGE_MAIN;
        }

    }

    private void dealKeyEvent_Tip() {


        if (ge.getKeyManager().isPressKey(KeyManager.KEY_LS)) {
        } else if (ge.getKeyManager().isPressKey(KeyManager.KEY_RS)) {
            gd.bag_pageIndex = BagView.PAGE_MAIN;
        }

    }

    public void dealKeyEvent() {
        switch (gd.bag_pageIndex) {
            case BagView.PAGE_MAIN:
                dealKeyEvent_Main();
                break;
            case BagView.PAGE_CONFIRM:
                dealKeyEvent_Confirm();
                break;
            case BagView.PAGE_TIP:
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
