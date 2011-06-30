package game.impl.view.menu;

import com.soyostar.app.Color;
import com.soyostar.app.Painter;
import engine.GameEngine;
import game.Const;
import game.RpgGame;
import game.View;
import game.impl.model.GameData;
import game.util.Skin;

/**
 *
 * @author Administrator
 */
public class MenuView implements View {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);
    private Skin s = new Skin();

    public void init() {
    }

    public void paint(Painter p) {
        p.setColor(Color.BLACK);
        p.fillRect(0, 0, ge.getScreenWidth(), ge.getScreenHeight());
        p.setColor(Color.WHITE);
        p.drawLine(0, 20, 100, 20);
        p.drawLine(0, 20 + p.getTextSize(), 100, 20 + p.getTextSize());
        p.drawString("测试Test123", 0, 20+p.getTextSize(), Painter.LT);
//        p.drawImage(s.getBackgroud(), 0, 0, Painter.LT);
        int num = Const.Text.MENU.length;
        for (int i = 0; i < num; i++) {
            p.setColor(Color.GREEN);
            p.drawRect((ge.getScreenWidth() - gd.menuState.menuWidth) / 2, (ge.getScreenHeight() - num * gd.menuState.menuHeight - (num - 1) * gd.menuState.gap) / 2 + i * (gd.menuState.menuHeight + gd.menuState.gap), gd.menuState.menuWidth, gd.menuState.menuHeight);
            if (gd.menuState.menuIndex == i) {
                p.setColor(Color.GRAY);
                p.fillRect((ge.getScreenWidth() - gd.menuState.menuWidth) / 2, (ge.getScreenHeight() - num * gd.menuState.menuHeight - (num - 1) * gd.menuState.gap) / 2 + i * (gd.menuState.menuHeight + gd.menuState.gap), gd.menuState.menuWidth, gd.menuState.menuHeight);
            }
            p.setColor(Color.GREEN);
            p.drawString(Const.Text.MENU[i], ge.getScreenWidth() / 2, (ge.getScreenHeight() - num * gd.menuState.menuHeight - (num - 1) * gd.menuState.gap) / 2 + i * (gd.menuState.menuHeight + gd.menuState.gap) + gd.menuState.menuHeight / 2, Painter.HV);

        }
    }

    public void release() {
    }
}
