/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.scene.startpage;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.Painter;
import engine.GameEngine;
import game.RpgGame;
import game.View;
import game.impl.model.GameData;

/**
 *
 * @author Administrator
 */
public class StartPageView implements View {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = null;
    private Image player = null;

    public void init() {
        gd = (GameData) rpgGame.getModel(0);
        player = Image.createImage("res/image/battler/" + gd.player.headImg);

    }

    public void paint(Painter p) {
        p.setColor(Color.BLACK);
        p.fillRect(0, 0, ge.getScreenWidth(), ge.getScreenHeight());
        p.drawImage(player,  ge.getScreenWidth() / 2, ge.getScreenHeight() / 2, Painter.HV);
//        p.setColor(Color.WHITE);
        p.setColor(0x12345678);
        p.setTextSize(20);
        p.drawString("欢迎界面", ge.getScreenWidth() / 2, ge.getScreenHeight() / 2, Painter.HV);
    }

    public void release() {
    }
}
