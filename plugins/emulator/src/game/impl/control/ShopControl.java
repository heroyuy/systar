package game.impl.control;

import game.impl.view.ShopView;
import game.Control;
import engine.GameEngine;
import game.View;
import engine.script.GameEvent;
import engine.script.ScriptEngine;
import game.RpgGame;
import game.impl.model.Const;
import game.impl.model.GameData;
import game.impl.view.ShopSubView;

/**
 *
 * @author Administrator
 */
public class ShopControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private ScriptEngine se = ScriptEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();
    private ShopSubView ssv = null;

    public void keyPressed(View view, int key) {
        if (view instanceof ShopView) {
            switch (key) {
                case Const.Key.KEY_UP:
                    if (gd.shop_curIndex > 0) {
                        gd.shop_curIndex--;
                        if (gd.shop_curIndex < gd.shop_topIndex) {
                            gd.shop_topIndex--;
                        }
                    }
                    break;
                case Const.Key.KEY_DOWN:
                    if (gd.shop_curIndex < gd.shop_itemMaxNum - 1) {
                        gd.shop_curIndex++;
                        if (gd.shop_curIndex > gd.shop_topIndex + gd.shop_showNum - 1) {
                            gd.shop_topIndex++;
                        }
                    }
                    break;
                case Const.Key.KEY_LEFT:
                case Const.Key.KEY_RIGHT:
                    gd.shop_tabIndex = (gd.shop_tabIndex + 1) % 2;
                    gd.shop_curIndex = gd.shop_topIndex = 0;
                    gd.shop_needRebuild = true;
                    break;
                case Const.Key.KEY_LS:
                case Const.Key.KEY_FIRE:
                case Const.Key.KEY_5:
                    System.out.println("---------------------->5");
                    if (gd.shop_pageIndex == ShopView.PAGE_MAIN) {
                        if (ssv == null) {
                            ssv = new ShopSubView();
                        }
                        ge.changeCanvas(ssv);
                    } else {
                        gd.shop_pageIndex = ShopView.PAGE_MAIN;
                    }
                    break;
                case Const.Key.KEY_RS:
                    System.out.println("返回地图");
                    game.setCurView(Const.ViewId.VIEW_MAP);
                    game.finishEvent();
                    break;
            }
        }
        ge.clearKey();
    }

    public void dealEvent(View view, GameEvent event) {
    }
}