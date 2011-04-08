package com.soyostar.game;

import com.soyostar.emulator.engine.BaseView;
import com.soyostar.emulator.engine.Game;
import com.soyostar.game.model.Const;
import com.soyostar.game.model.DataHandler;
import com.soyostar.game.view.BagView;
import com.soyostar.game.view.MenuView;

public class RpgGame extends Game {

    private BaseView curView = null;

    public RpgGame() {
        super();
    }

    public void setCurView(byte index) {
        System.out.println("切换视图：" + index);
        curView = getCurView();
        if (curView != null) {
            curView.release();
        }
        setCurView(null);
        switch (index) {
            case Const.ViewId.VIEW_MENU:
                curView = new MenuView();
                curView.init();
                setCurView(curView);
              
                break;
//            case Const.ViewId.VIEW_MAP:
//                curView = new MapView();
//                curView.init();
//                setCurView(curView);
//                break;
//            case Const.ViewId.VIEW_SETTING:
//                curView = new SettingView();
//                curView.init();
//                setCurView(curView);
//                break;
//            case Const.ViewId.VIEW_HELP:
//                curView = new HelpView();
//                curView.init();
//                setCurView(curView);
//                break;
//            case Const.ViewId.VIEW_ABOUT:
//                curView = new AboutView();
//                curView.init();
//                setCurView(curView);
//                break;
//            case Const.ViewId.VIEW_BATTLE:
//                curView = new BattleView();
//                curView.init();
//                setCurView(curView);
//                break;
//            case Const.ViewId.VIEW_SKILL:
//                curView = new SkillView();
//                curView.init();
//                setCurView(curView);
//                break;
//            case Const.ViewId.VIEW_BAG:
//                curView = new BagView();
//                curView.init();
//                setCurView(curView);
//                break;
//
//            case Const.ViewId.VIEW_STATE:
//                curView = new StateView();
//                curView.init();
//                setCurView(curView);
//                break;
//
//            case Const.ViewId.VIEW_EQUIP:
//                curView = new EquipView();
//                curView.init();
//                setCurView(curView);
//                break;
//            case Const.ViewId.VIEW_SHOP:
//                curView = new ShopView();
//                curView.init();
//                setCurView(curView);
//                break;


        }
    }

    public void start() {
        setCurView(Const.ViewId.VIEW_MENU);

        setDataHandler(new DataHandler());
    }
}
