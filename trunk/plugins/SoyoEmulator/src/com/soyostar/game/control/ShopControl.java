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
import com.soyostar.game.view.ShopSubView;
import com.soyostar.game.view.ShopView;

/**
 *@2011.4.8 by VV
 * @author Administrator
 */
public class ShopControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();
    private ShopSubView ssv = null;

    public void dealKeyEvent() {
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_UP)) {
            if (gd.shop_curIndex > 0) {
                gd.shop_curIndex--;
                if (gd.shop_curIndex < gd.shop_topIndex) {
                    gd.shop_topIndex--;
                }
            }
        }
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_DOWN)) {
            if (gd.shop_curIndex < gd.shop_itemMaxNum - 1) {
                gd.shop_curIndex++;
                if (gd.shop_curIndex > gd.shop_topIndex + gd.shop_showNum - 1) {
                    gd.shop_topIndex++;
                }
            }
        }
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_LEFT)) {
        }
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_RIGHT)) {
            gd.shop_tabIndex = (gd.shop_tabIndex + 1) % 2;
            gd.shop_curIndex = gd.shop_topIndex = 0;
            gd.shop_needRebuild = true;
        }
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_5)) {
            System.out.println("---------------------->5");
            if (gd.shop_pageIndex == ShopView.PAGE_MAIN) {
                if (ssv == null) {
                    ssv = new ShopSubView();
                }
                ge.changeCanvas(ssv);
            } else {
                gd.shop_pageIndex = ShopView.PAGE_MAIN;
            }
        }
        if (ge.getKeyManager().isPressKey(KeyManager.KEY_RS)) {
            System.out.println("返回地图");
            game.setCurView(Const.ViewId.VIEW_MAP);
            game.finishEvent();
        }
    }

    public void dealMotion() {
    }

    public void dealGameEvent(Event event) {
    }

    public void updateModel() {
       
    }
}
