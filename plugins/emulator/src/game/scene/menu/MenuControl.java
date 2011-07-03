package game.scene.menu;

import com.soyostar.app.Rect;
import com.soyostar.app.event.TouchEvent;
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
public class MenuControl extends AbController {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);
    private TouchDelegate touchDelegate = TouchDelegate.getDefaultTouchDelegate();

    public MenuControl(Render render) {
        super(render);
    }

    @Override
    public void onObtain() {
        touchDelegate.clearAllTouchRect();
        for (int i = 0; i < Const.Text.MENU.length; i++) {
            touchDelegate.addTouchRect(new Rect((ge.getScreenWidth() - gd.menuState.menuWidth) / 2, (ge.getScreenHeight() - Const.Text.MENU.length * gd.menuState.menuHeight - (Const.Text.MENU.length - 1) * gd.menuState.gap) / 2 + i * (gd.menuState.menuHeight + gd.menuState.gap), gd.menuState.menuWidth, gd.menuState.menuHeight));

        }
    }

    public void onTouchEvent(TouchEvent te) {
        if (te.getType() == TouchEvent.TOUCH_DOWN) {
            int index = touchDelegate.getTouchRectIndex(te.getX(), te.getY());
            if (index != -1) {
                gd.menuState.menuIndex = index;
            }
        }

    }

    public void updateModel() {
    }

    private void changeScene() {
        switch (gd.menuState.menuIndex) {
            case 0:
                rpgGame.setCurrentControl(Const.ControlId.MAP);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    public void onLose() {
    }
}
