/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import emulator.engine.Control;
import emulator.engine.GameEngine;
import emulator.engine.View;
import emulator.engine.script.Event;
import emulator.model.Bag;
import emulator.model.Const;
import emulator.model.GameData;
import game.RpgGame;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.NumberView;
import view.ShopView;

/**
 *
 * @author Administrator
 */
public class NumberControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    public void keyPressed(View view, int key) {
        if (view instanceof NumberView) {
            switch (key) {
                case Const.Key.KEY_0:
                    gd.stringBuffer.append("0");
                    break;
                case Const.Key.KEY_1:
                    gd.stringBuffer.append("1");
                    break;
                case Const.Key.KEY_2:
                    gd.stringBuffer.append("2");
                    break;
                case Const.Key.KEY_3:
                    gd.stringBuffer.append("3");
                    break;
                case Const.Key.KEY_4:
                    gd.stringBuffer.append("4");
                    break;
                case Const.Key.KEY_5:
                    gd.stringBuffer.append("5");
                    break;
                case Const.Key.KEY_6:
                    gd.stringBuffer.append("6");
                    break;
                case Const.Key.KEY_7:
                    gd.stringBuffer.append("7");
                    break;
                case Const.Key.KEY_8:
                    gd.stringBuffer.append("8");
                    break;
                case Const.Key.KEY_9:
                    gd.stringBuffer.append("9");
                    break;
                case Const.Key.KEY_POUND:
                    if (gd.stringBuffer.length() > 0) {
                        gd.stringBuffer.deleteCharAt(gd.stringBuffer.length() - 1);
                    }

                    break;
                case Const.Key.KEY_LS:
                    game.setCurView(Const.ViewId.VIEW_SHOP);
                    try {
                        gd.shop_itemNum = Integer.parseInt(gd.stringBuffer.toString());
                    } catch (Exception ee) {
                        gd.shop_itemNum = 1;
                    }
                    if (gd.shop_tabIndex == 0) {
                        buy();
                    } else {
                        sell();
                    }
                    gd.shop_pageIndex = ShopView.PAGE_TIP;
                    ge.switchToRenderLayer();
                    break;
                case Const.Key.KEY_RS:
                    game.setCurView(Const.ViewId.VIEW_SHOP);
                    break;
            }
            ge.clearKey();
        }
    }

    private void buy() {
        if (gd.player.money >= gd.shop_items_buy[gd.shop_curIndex].price * gd.shop_itemNum) {
            gd.player.money -= gd.shop_items_buy[gd.shop_curIndex].price * gd.shop_itemNum;
            gd.player.bag.add(gd.shop_type == 0 ? Bag.ITEM : Bag.EQUIP, gd.shop_items_buy[gd.shop_curIndex].index, gd.shop_itemNum);
            gd.shop_message = "购买成功，获得" + gd.shop_items_buy[gd.shop_curIndex].name + " " + gd.shop_itemNum + "个";
            gd.buildList_sell();
        } else {
            gd.shop_message = "金钱不足，购买失败";
        }

    }

    private void sell() {
        if (gd.player.bag.getNum(gd.shop_type == 0 ? Bag.ITEM : Bag.EQUIP, gd.shop_items_sell[gd.shop_curIndex].index) >= gd.shop_itemNum) {
            gd.player.bag.del(gd.shop_type == 0 ? Bag.ITEM : Bag.EQUIP, gd.shop_items_sell[gd.shop_curIndex].index, gd.shop_itemNum);
            gd.player.money += gd.shop_items_sell[gd.shop_curIndex].price / 2 * gd.shop_itemNum;
            gd.shop_message = "卖出成功，获得金钱" + (gd.shop_items_sell[gd.shop_curIndex].price / 2 * gd.shop_itemNum);
            gd.buildList_sell();
            gd.shop_topIndex = gd.shop_curIndex = 0;
            gd.shop_needRebuild = true;
        } else {
            gd.shop_message = gd.shop_items_sell[gd.shop_curIndex].name + "数量不足，卖出失败";
        }
    }

    public void dealEvent(View view, Event event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
