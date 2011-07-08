package game.scene.menu;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.Layer;
import com.soyostar.app.Painter;
import engine.GameEngine;
import game.Const;
import game.RpgGame;
import game.impl.model.GameData;
import game.util.Skin;

/**
 *
 * @author Administrator
 */
public class MenuLayer extends Layer {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);
    private Skin s = new Skin("res/image/skin/001-Blue01.png");

    @Override
    public void paintSelf(Painter p) {
        super.paintSelf(p);

        p.setColor(Color.BLACK);

        p.fillRect(0, 0, ge.getScreenWidth(), ge.getScreenHeight());
        p.drawImage(s.createAlphaBg(getWidth(), getHeight(), false), 0, 0, Painter.LT);
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
}