package game.impl.view.menu;

import emulator.EmulatorGraphics;
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
    private GameData gd = null;

    public void init() {
        gd = (GameData) rpgGame.getModel(0);
        System.out.println("gd:" + gd);
        System.out.println("ge:" + ge);
        System.out.println("rpgGame:" + rpgGame);
    }

    public void paint(EmulatorGraphics eg) {
        eg.setColor(Color.black);
        eg.fillRect(0, 0, ge.getScreenWidth(), ge.getScreenHeight());
        eg.setColor(Color.white);
        eg.drawString("菜单界面", ge.getScreenWidth() / 2, ge.getScreenHeight() / 2, EmulatorGraphics.HV);
        int num = Const.Text.MENU.length;
        for (int i = 0; i < num; i++) {
            eg.setColor(Color.green);
            eg.drawRect((ge.getScreenWidth() - gd.ms.menuWidth) / 2, (ge.getScreenHeight() - num * gd.ms.menuHeight - (num - 1) * gd.ms.gap) / 2 + i * (gd.ms.menuHeight + gd.ms.gap), gd.ms.menuWidth, gd.ms.menuHeight);
        }
    }

    public void release() {
    }
}
