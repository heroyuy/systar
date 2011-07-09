package game.scene.menu;

import com.soyostar.app.Rect;
import com.soyostar.app.event.TouchEvent;
import com.soyostar.app.event.TouchListener;
import engine.GameEngine;
import engine.Render;
import game.AbController;
import game.Const;
import game.RpgGame;
import game.impl.model.GameData;
import game.util.TouchDelegate;

/**
 *
 * @author Administrator
 */
public class MenuController extends AbController implements TouchListener {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel("game.impl.model.GameData");
    private TouchDelegate touchDelegate = TouchDelegate.getDefaultTouchDelegate();
    private MenuLayer menuLayer = null;

    public MenuController(Render render) {
        super(render);
    }

    @Override
    public void onObtain() {
        menuLayer = new MenuLayer();
        menuLayer.setVisible(true);
        menuLayer.setSize(ge.getScreenWidth(), ge.getScreenHeight());
        menuLayer.setTouchListener(this);
        addWidget(menuLayer);

        touchDelegate.clearAllTouchRect();
        for (int i = 0; i < Const.Text.MENU.length; i++) {
            touchDelegate.addTouchRect(new Rect((ge.getScreenWidth() - gd.menuState.menuWidth) / 2, (ge.getScreenHeight() - Const.Text.MENU.length * gd.menuState.menuHeight - (Const.Text.MENU.length - 1) * gd.menuState.gap) / 2 + i * (gd.menuState.menuHeight + gd.menuState.gap), gd.menuState.menuWidth, gd.menuState.menuHeight));

        }
    }

    public void updateModel() {
    }

    public void onLose() {
    }

    public boolean onTouchEvent(Object t, TouchEvent te) {

        if (te.getType() == TouchEvent.TOUCH_DOWN) {
            int index = touchDelegate.getTouchRectIndex(te.getX(), te.getY());
            if (index != -1) {
                gd.menuState.menuIndex = index;
            }
        }
        return true;
    }
}
