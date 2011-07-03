package game.scene.startpage;

import com.soyostar.app.Color;
import com.soyostar.app.Image;
import com.soyostar.app.Layer;
import com.soyostar.app.Painter;
import engine.GameEngine;
import game.RpgGame;
import game.impl.model.GameData;

/**
 *
 * @author Administrator
 */
public class StartPageLayer extends Layer {

    private GameEngine ge = GameEngine.getInstance();
    private RpgGame rpgGame = (RpgGame) ge.getGame();
    private GameData gd = null;
    private Image player = null;

    public StartPageLayer() {

        gd = (GameData) rpgGame.getModel(0);
        player = Image.createImage("res/image/battler/" + gd.player.headImg);
    }

    @Override
    public void paintSelf(Painter p) {
        super.paintSelf(p);
        p.drawImage(player, ge.getScreenWidth() / 2, ge.getScreenHeight() / 2, Painter.HV);
        p.setColor(Color.WHITE);
        p.setTextSize(20);
        p.drawString("欢迎界面", ge.getScreenWidth() / 2, ge.getScreenHeight() / 2, Painter.HV);
    }
}
