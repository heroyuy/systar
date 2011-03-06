package control;

import view.MenuView;
import engine.Control;
import engine.GameEngine;
import engine.View;
import engine.script.Event;
import game.RpgGame;
import model.Const;
import model.GameData;
import system.DataBase;
//import view.*;

/**
 * 游戏菜单视图的控制器
 */
public class MenuControl implements Control {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private RpgGame game = (RpgGame) ge.getGame();
    private DataBase db = new DataBase();
    public void keyPressed(View view, int key) {
        if (view instanceof MenuView) {
            if (key == Const.Key.KEY_LEFT) {
                gd.xIndex--;
                if (gd.xIndex < 0) {
                    gd.xIndex = Const.Str.MENU_MENU.length - 1;
                }
            } else if (key == Const.Key.KEY_RIGHT) {
                gd.xIndex++;
                if (gd.xIndex > Const.Str.MENU_MENU.length - 1) {
                    gd.xIndex = 0;
                }
            } else if (key == Const.Key.KEY_FIRE || key == Const.Key.KEY_5) {
                switch (gd.xIndex) {
                    case MenuView.START:
                        //新游戏
                        newGame();
//                        System.out.println("ok");
                        game.setCurView(Const.ViewId.VIEW_MAP);
//                        game.setCurView(Const.Tag.VIEW_BATTLE);//测试战斗
                        break;
                    case MenuView.CONTINUE:
                        //继续游戏
                        loadGame();
                        
                        game.setCurView(Const.ViewId.VIEW_MAP);
                        break;
                    case MenuView.SETTING:
                        //游戏设置
                        game.setCurView(Const.ViewId.VIEW_SETTING);
                        break;
                    case MenuView.HELP:
                        //游戏帮助
                        game.setCurView(Const.ViewId.VIEW_HELP);
                        break;
                    case MenuView.ABOUT:
                        //关于游戏
                        game.setCurView(Const.ViewId.VIEW_ABOUT);
                        break;
                    case MenuView.EXIT:
                        //退出游戏
                        game.exit();
                        break;
                }
            }
            ge.clearKey();
        }
    }

    public void dealEvent(View view, Event event) {
    }

    private void newGame() {
        long time = System.currentTimeMillis();
        System.out.println("newGame");
        gd.gameObjectManager.init();
        System.out.println("init完成");
        gd.player = gd.gameObjectManager.getPlayer().getClone();
        System.out.println("getPlayer完成");
        gd.curMap = gd.gameObjectManager.getMap(gd.player.curMapIndex);
        System.out.println("getMap完成");
        gd.player.setLocation();
        gd.player.init();
        gd.curMap.resetRegion(gd.player);
        time = System.currentTimeMillis() - time;
        System.out.println("time: " + time);
    }

    private void loadGame() {
        db.loadDB();
    }
}
