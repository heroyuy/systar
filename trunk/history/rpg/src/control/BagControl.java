package control;

import engine.Control;
import engine.GameEngine;
import engine.View;
import engine.script.Event;
import engine.script.ScriptEngine;
import game.RpgGame;
import model.Bag;
import model.Const;
import model.GameData;
import view.BagView;

/**
 *
 * @author Administrator
 */
public class BagControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();

    public void keyPressed(View view, int key) {
        if (view instanceof BagView) {
            switch (gd.bag_pageIndex) {
                case BagView.PAGE_MAIN:
                    keyPressed_Main(key);
                    break;
                case BagView.PAGE_CONFIRM:
                    keyPressed_Confirm(key);
                    break;
                case BagView.PAGE_TIP:
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
                if (gd.bag_curIndex > 0) {
                    gd.bag_curIndex--;
                    if (gd.bag_curIndex < gd.bag_topIndex) {
                        gd.bag_topIndex--;
                    }
                }
                break;
            case Const.Key.KEY_DOWN:
                if (gd.bag_curIndex < gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP).length - 1) {
                    gd.bag_curIndex++;
                    if (gd.bag_curIndex > gd.bag_topIndex + gd.bag_showNum - 1) {
                        gd.bag_topIndex++;
                    }
                }
                break;
            case Const.Key.KEY_LEFT:
            case Const.Key.KEY_RIGHT:
                gd.bag_tabIndex = (gd.bag_tabIndex + 1) % 2;
                gd.bag_curIndex = gd.bag_topIndex = 0;
                break;
            case Const.Key.KEY_LS:
                if (gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP).length > 0 && gd.player.bag.get(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP, gd.player.bag.getList(gd.bag_tabIndex == 0 ? Bag.ITEM : Bag.EQUIP)[gd.bag_curIndex]) != null) {
                    gd.bag_pageIndex = BagView.PAGE_CONFIRM;
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
                break;
            case Const.Key.KEY_RS:
                gd.bag_pageIndex = BagView.PAGE_MAIN;
                break;
        }
    }

    private void keyPressed_Tip(int key) {

        switch (key) {
            case Const.Key.KEY_LS:
            case Const.Key.KEY_RS:
                gd.bag_pageIndex = BagView.PAGE_MAIN;
                break;
        }
    }

}
