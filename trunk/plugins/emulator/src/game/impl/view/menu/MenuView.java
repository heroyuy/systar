package game.impl.view.menu;

import emulator.ui.EmulatorGraphics;
import engine.GameEngine;
import game.Const;
import game.RpgGame;
import game.View;
import game.impl.model.GameData;
import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class MenuView implements View {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = (GameData) rpgGame.getModel(0);

    public void init() {
    }

    public void paint(EmulatorGraphics eg) {
        eg.setColor(Color.black);
        eg.fillRect(0, 0, ge.getScreenWidth(), ge.getScreenHeight());
        int num = Const.Text.MENU.length;
        for (int i = 0; i < num; i++) {
            eg.setColor(Color.green);
            eg.drawRect((ge.getScreenWidth() - gd.menuState.menuWidth) / 2, (ge.getScreenHeight() - num * gd.menuState.menuHeight - (num - 1) * gd.menuState.gap) / 2 + i * (gd.menuState.menuHeight + gd.menuState.gap), gd.menuState.menuWidth, gd.menuState.menuHeight);
            if (gd.menuState.menuIndex == i) {
                eg.setColor(Color.gray);
                eg.fillRect((ge.getScreenWidth() - gd.menuState.menuWidth) / 2, (ge.getScreenHeight() - num * gd.menuState.menuHeight - (num - 1) * gd.menuState.gap) / 2 + i * (gd.menuState.menuHeight + gd.menuState.gap), gd.menuState.menuWidth, gd.menuState.menuHeight);
            }
            eg.setColor(Color.green);
            eg.drawString(Const.Text.MENU[i], ge.getScreenWidth() / 2, (ge.getScreenHeight() - num * gd.menuState.menuHeight - (num - 1) * gd.menuState.gap) / 2 + i * (gd.menuState.menuHeight + gd.menuState.gap) + gd.menuState.menuHeight / 2, EmulatorGraphics.HV);

        }
    }

    public void release() {
    }
}
