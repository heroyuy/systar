package game;

import view.AboutView;
import view.HelpView;
import view.MapView;
import view.MenuView;
import view.SettingView;
import emulator.model.Const;
import emulator.engine.BaseView;
import emulator.engine.Game;
import emulator.model.DataHandler;
import view.BagView;
import view.BattleView;
import view.EquipView;
import view.ShopView;
import view.SkillView;
import view.StateView;

public class RpgGame extends Game {

    private BaseView curView = null;

    public RpgGame() {
        super();
    }

    public void setCurView(byte index) {
        System.out.println("«–ªª ”Õº£∫"+index);
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
            case Const.ViewId.VIEW_MAP:
                curView = new MapView();
                curView.init();
                setCurView(curView);
                break;
            case Const.ViewId.VIEW_SETTING:
                curView = new SettingView();
                curView.init();
                setCurView(curView);
                break;
            case Const.ViewId.VIEW_HELP:
                curView = new HelpView();
                curView.init();
                setCurView(curView);
                break;
            case Const.ViewId.VIEW_ABOUT:
                curView = new AboutView();
                curView.init();
                setCurView(curView);
                break;
            case Const.ViewId.VIEW_BATTLE:
                curView = new BattleView();
                curView.init();
                setCurView(curView);
                break;
            case Const.ViewId.VIEW_SKILL:
                curView = new SkillView();
                curView.init();
                setCurView(curView);
                break;
            case Const.ViewId.VIEW_BAG:
                curView = new BagView();
                curView.init();
                setCurView(curView);
                break;

            case Const.ViewId.VIEW_STATE:
                curView = new StateView();
                curView.init();
                setCurView(curView);
                break;

            case Const.ViewId.VIEW_EQUIP:
                curView = new EquipView();
                curView.init();
                setCurView(curView);
                break;
            case Const.ViewId.VIEW_SHOP:
                curView = new ShopView();
                curView.init();
                setCurView(curView);
                break;


        }
    }

    public void start() {
        setCurView(Const.ViewId.VIEW_MENU);
        setDataHandler(new DataHandler());
    }
}
